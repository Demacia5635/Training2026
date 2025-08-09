// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.MyFirstSubsystem;

/* You should consider using the more terse Command factories API instead https://docs.wpilib.org/en/stable/docs/software/commandbased/organizing-command-based.html#defining-commands */
public class KsAkvCalc extends Command {
   MyFirstSubsystem subsystem;
  private double startTime;
  /** Creates a new KsAkvCalc. */
  public KsAkvCalc(MyFirstSubsystem subsystem) {
    this.subsystem = subsystem;
    addRequirements(subsystem);
    SmartDashboard.putNumber("drive velocity1", 0);
    SmartDashboard.putNumber("drive voltage1", 0);
    SmartDashboard.putNumber("drive velocity2", 0);
    SmartDashboard.putNumber("drive voltage2", 0);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    double startTime = Timer.getFPGATimestamp();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    double time = Timer.getFPGATimestamp() - startTime;
    if (time < 10) {
      subsystem.driveMotor.setVoltage(5);
      SmartDashboard.putNumber("drive velocity1", subsystem.driveMotor.getVelocity().getValueAsDouble());
      SmartDashboard.putNumber("drive voltage1", 5);
    }
    else{
      subsystem.driveMotor.setVoltage(8);
      SmartDashboard.putNumber("drive velocity2", subsystem.driveMotor.getVelocity().getValueAsDouble());
      SmartDashboard.putNumber("drive voltage2", 8);
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    subsystem.stop();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    boolean finished = Timer.getFPGATimestamp() - startTime > 30;
    return finished;
  }
}
