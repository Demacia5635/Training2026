// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.PIDCommand;
import frc.robot.subsystems.MyFirstSubsystem;

/* You should consider using the more terse Command factories API instead https://docs.wpilib.org/en/stable/docs/software/commandbased/organizing-command-based.html#defining-commands */
public class MoveInSameVelocity extends Command {
  /** Creates a new MoveInSameVelocity. */
  private MyFirstSubsystem sub3= new MyFirstSubsystem();
  private double wantedSpeed;
  private double currentSpeed;
  private double startTime;
  private PIDController pid = new PIDController(0.2, 0, 0);
  
  public MoveInSameVelocity(MyFirstSubsystem sub3) {
    // Use addRequirements() here to declare subsystem dependencies.
    this.sub3=sub3;
    pid.setTolerance(0.1);
    this.wantedSpeed = SmartDashboard.getNumber("Wanted Velcity", 2);
    // pid.enableContinuousInput(-180, 180);
    addRequirements(sub3);
    SmartDashboard.putNumber("Wanted Velcity", 0);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    pid.reset();
    pid.setSetpoint(wantedSpeed);
  }
    
  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    wantedSpeed=SmartDashboard.getNumber("Wanted Velcity", 2);
    currentSpeed = sub3.getDriveMotorVelocity();
    sub3.setPower(0, pid.calculate(currentSpeed,wantedSpeed));
    startTime = Timer.getFPGATimestamp();
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    sub3.setPower(0, 0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return pid.atSetpoint() && Timer.getFPGATimestamp()> startTime + 10 ;
  }
}
