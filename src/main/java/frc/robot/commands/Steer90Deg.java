// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Modle;

/* You should consider using the more terse Command factories API instead https://docs.wpilib.org/en/stable/docs/software/commandbased/organizing-command-based.html#defining-commands */
public class Steer90Deg extends Command {
  /** Creates a new Steer90Deg. */
  private double power;
  private double angle;
  private Modle modle;
  
  public Steer90Deg(double power, double angle , Modle modle){
    this.power = power;
    this.angle = angle;
    this.modle = modle; // Initialize the Modle subsystem
    addRequirements(modle); // Declare subsystem dependencies
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if (angle< modle.getPosition()) {
      modle.setPower(power);
      
     } // Logic to steer the robot towards the target angle
     else{
      modle.setPower(-power);
     }  
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) { modle.motor(0);}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return Math.abs(angle - modle.getPosition()) < 10; // Command is finished when within 1 degree of target angle
   
    
  }
}
