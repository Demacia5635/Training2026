// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.MyFirstSubsystem;

/* You should consider using the more terse Command factories API instead https://docs.wpilib.org/en/stable/docs/software/commandbased/organizing-command-based.html#defining-commands */
public class moveToMeter extends Command {
  private double startingMeter;
  private MyFirstSubsystem bum;
  private double wantedMeter; 
  private double power;
  /** Creates a new moveToMeter. */
  public moveToMeter(MyFirstSubsystem sub, double wantedMeter) {
    this.bum = sub;
    this.wantedMeter = wantedMeter;
    this.power = wantedMeter >= 0 ? 0.1 : -0.1;
    addRequirements(sub);
  }
  
  @Override
  public boolean isFinished() {
    return Math.abs(bum.moveToMeter() - wantedMeter) < 0.05;
  }
  
  
  
  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    bum.setPower(power);
  }
    
  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    power = 0;
  }

  // Returns true when the command should end.
}
