// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.MyFirstSubsystem;

/* You should consider using the more terse Command factories API instead https://docs.wpilib.org/en/stable/docs/software/commandbased/organizing-command-based.html#defining-commands */
public class GoToAngleWithPID extends Command {
   private double SteertargetAngle = 100; // init target angle
   private double new_SteertargetAngle;
   private MyFirstSubsystem subsystem;
   private int i;
   private double currentAngle = subsystem.getSPosition(); // beer
  /** Creates a new GoToAngle. */
  public GoToAngleWithPID(MyFirstSubsystem subsystem) {
    this.subsystem = subsystem;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(subsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if(SteertargetAngle != new_SteertargetAngle) {
      subsystem.SteerToAngle(SteertargetAngle);
      if(i == 0) {
        SteertargetAngle = new_SteertargetAngle;
      }
      //i++;
     } //else if (SteertargetAngle != new_SteertargetAngle && Math.abs(SteertargetAngle - currentAngle) < subsystem.getSteerErrTol() && i > 5) 
    //   i = 0;
    //   new_SteertargetAngle = Math.floor(Math.random()*361);
    //   execute();
     }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    subsystem.stop();
    System.out.println("Command ended at: " + subsystem.getSPosition() + " degrees");
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}

