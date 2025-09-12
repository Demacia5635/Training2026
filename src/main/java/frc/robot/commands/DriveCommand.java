// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;

/* You should consider using the more terse Command factories API instead https://docs.wpilib.org/en/stable/docs/software/commandbased/organizing-command-based.html#defining-commands */
public class DriveCommand extends Command {
  private Chassis chassis;
  private CommandXboxController xboxController;
  private boolean IsRed;
  private ChassisSpeeds wantSpeeds;
  private ChassisSpeeds speed;
  private double direction;
  private double lastSpeedsX;
  private double lastSpeedsY;
  private double lastwantedRot;
  double joyX;
  double joyY;


  
  /** Creates a new DriveCommand. */
  public DriveCommand() {
    // Use addRequirements() here to declare subsystem dependencies.
    this.chassis = chassis;
    xboxController = new CommandXboxController(Constants.ModuleConstants.DriveID);
    IsRed=true;
    addRequirements(chassis);
    SmartDashboard.putData(this);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    direction = IsRed ? 1:-1;
    chassis.resetGyro();
  }
    
  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    joyX = xboxController.getLeftX() * direction;
    joyY = xboxController.getLeftY() * direction;        
    double rot = xboxController.getLeftTriggerAxis() - xboxController.getRightTriggerAxis();
    double velX = Math.pow(joyX, 2) * 3.6;
    double velY = Math.pow(joyY, 2) * 3.6;
    double velRot = Math.pow(rot, 2) * 40;
    wantSpeeds = new ChassisSpeeds(velX, velY,velRot);
    chassis.setVelocities(wantSpeeds,speed);
    lastSpeedsX = velY;
    lastSpeedsY = velY;
    lastwantedRot = rot;
    speed = new ChassisSpeeds(lastSpeedsX,lastSpeedsY,lastwantedRot);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
