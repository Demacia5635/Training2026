// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.util.sendable.SendableBuilder;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants;
import frc.robot.subsystems.Modle;

/* You should consider using the more terse Command factories API instead https://docs.wpilib.org/en/stable/docs/software/commandbased/organizing-command-based.html#defining-commands */
public class goToPid extends Command {
  private double angle = 0;
  private Modle moudle;
  private PIDController pid;
  /** Creates a new goToPid. */
  public goToPid( Modle subsystem) {
    this.moudle = subsystem;
    

    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    pid = new PIDController( Constants.KP, Constants.KI, Constants.KD);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    moudle.setSteerPower(pid.calculate(moudle.getSteerPosition(), angle));
    double pidCaculate = pid.calculate(getAngle(), angle);
    moudle.setDrivePower(pidCaculate);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
  public void initSendable(SendableBuilder builder){
    builder.addDoubleProperty("wanted angle",this::getAngle,this::setAngle);
  }
  public double getAngle( ){
    return this.angle;
  }
  public void setAngle(double angle){
    this.angle = angle;
  }

}