// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Motor;

/* You should consider using the more terse Command factories API instead https://docs.wpilib.org/en/stable/docs/software/commandbased/organizing-command-based.html#defining-commands */
public class time extends Command {
  /** Creates a new time. */
  private Motor motors;
  private double speed;
  private double time;
  private double startTime;
  public time(double speed ,double time ,Motor motors) {
    this.motors = motors;
    // Use addRequirements() here to declare subsystem dependencies.
    this.speed = speed;
    this.time = time;
    addRequirements(motors);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    this.startTime = Timer.getFPGATimestamp();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {

    motors.setPwoer2(speed);
    motors.setPwoer(speed);
  }
 
  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    // Stop the motors when the command ends
    motors.setPwoer(0);
    motors.setPwoer2(0);

  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {

    return (Timer.getFPGATimestamp() - startTime) >= time; // Check if the elapsed time is greater than or equal to the specified time
    
  }
}
