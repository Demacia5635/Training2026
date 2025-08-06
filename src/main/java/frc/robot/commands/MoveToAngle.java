// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.PIDCommand;
import frc.robot.subsystems.MyFirstSubsystem;

/* You should consider using the more terse Command factories API instead https://docs.wpilib.org/en/stable/docs/software/commandbased/organizing-command-based.html#defining-commands */
public class MoveToAngle extends Command {
  /** Creates a new MoveToAngle. */
  MyFirstSubsystem sub;
  double angle;
  public MoveToAngle(MyFirstSubsystem sub) {
    // Use addRequirements() here to declare subsystem dependencies.
    this.sub = sub;
    //this.angle = angle;
    addRequirements(sub);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    sub.setPower(0.05, 0);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    angle = SmartDashboard.getNumber("WantedAngle", 90);
    if(angle > sub.getMotorAngle()){
      sub.setPower(0.05,0);
    }
    else{
      sub.setPower(-0.05, 0);
    }

  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    sub.setPower(0, 0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return Math.abs(angle - sub.getMotorAngle()) < 5;
  }
}
