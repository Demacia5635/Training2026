// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;

/* You should consider using the more terse Command factories API instead https://docs.wpilib.org/en/stable/docs/software/commandbased/organizing-command-based.html#defining-commands */
public class StartMotorToPosition extends Command {
  private final Modula subsystem;
  private final double drivePower;
  private final double steerPower;
  private final double targetPosition;
  private double currentPosition;

  public StartModulaToPosition(Modula, subsystem) {
    this.subsystem = subsystem;
    this.drivePower = drivePower;
    this.steerPower = steerPower;
    this.targetPosition = targetPosition;
    addRequirements(subsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    subsystem.setPowerToDrive(drivePower);
    currentPosition = subsystem.getDrivePosition();
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    subsystem.stopToDrive();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return Math.abs(targetPosition - currentPosition) < 0.01;
  }
}
