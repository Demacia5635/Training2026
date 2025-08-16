// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import frc.robot.subsystems.Motor;

/* You should consider using the more terse Command factories API instead https://docs.wpilib.org/en/stable/docs/software/commandbased/organizing-command-based.html#defining-commands */
public class StartSteerMotorToPosition extends StartMotorToPosition {
  /** Creates a new StartDriveMotorToPosition. */
  public StartSteerMotorToPosition(Motor subsystem, double power, double targetPosition) {
    super(subsystem, power, targetPosition);
    // Use addRequirements() here to declare subsystem dependencies.
  }
  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return Math.abs((targetPosition - subsystem.getPosition())) < 10  ;
  }
}
