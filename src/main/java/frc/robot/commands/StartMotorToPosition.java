// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Motor;

/* You should consider using the more terse Command factories API instead https://docs.wpilib.org/en/stable/docs/software/commandbased/organizing-command-based.html#defining-commands */
public class StartMotorToPosition extends Command {
  private final Motor subsystem;
  private final double power;
  private final double targetPosition;
  private final double ratio;

  public StartMotorToPosition(Motor subsystem, double power, double targetPosition, double ratio) {
    this.subsystem = subsystem;
    this.power = power;
    this.targetPosition = targetPosition;
    this.ratio = ratio;
    addRequirements(subsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    subsystem.setPower(power);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    subsystem.stop();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return Math.abs((targetPosition - subsystem.getPosition()) * ratio) < 0.01;
  }
}
