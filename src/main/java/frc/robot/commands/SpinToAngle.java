// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.MyFirstSubsystem;

/* You should consider using the more terse Command factories API instead https://docs.wpilib.org/en/stable/docs/software/commandbased/organizing-command-based.html#defining-commands */
public class SpinToAngle extends Command {
  /** Creates a new SpinToAngle. */
  private double startingAngle;
  private MyFirstSubsystem sub;
  private double wantedAngle; 
  private double power;
  public SpinToAngle(MyFirstSubsystem sub, double wantedAngle) {
    this.sub = sub;
    this.wantedAngle = wantedAngle;
    this.power = 0.1;
    addRequirements(sub);
  }
  
  @Override
  public boolean isFinished() {
    return Math.abs(sub.spinToAngle() - wantedAngle) < 5; 
  }
  

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    sub.setPower2(power);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    power = 0;
  }

  // Returns true when the command should end.
  
  
  
}
