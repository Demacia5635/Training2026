// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Subsystem;
import frc.robot.subsystems.Motor;

/* You should consider using the more terse Command factories API instead https://docs.wpilib.org/en/stable/docs/software/commandbased/organizing-command-based.html#defining-commands */
public class MoveMotorPerTime extends Command {
  /** Creates a new MoveMotorPerTime. */
  private Motor motorSubSystem;
  private double speed;
  private double time;
  private double timer = 0;
  public MoveMotorPerTime(double time, double speed,Motor motorSubSystem) {
    // Use addRequirements() here to declare subsystem dependencies.
     this.speed = speed;
     this.time = time;
     
     addRequirements(motorSubSystem);
     
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    timer = Timer.getFPGATimestamp();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    motorSubSystem.SetPowerForMotor(speed);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    motorSubSystem.SetPowerForMotor(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return Timer.getFPGATimestamp() >= timer + time;
  }
}
