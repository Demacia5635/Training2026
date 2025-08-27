// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.controller.SimpleMotorFeedforward;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import frc.Demacia.utils.Motors.MotorInterface;
import frc.robot.subsystems.PizzaMotor;

/* You should consider using the more terse Command factories API instead https://docs.wpilib.org/en/stable/docs/software/commandbased/organizing-command-based.html#defining-commands */
public class VelocityLoopcontrol extends Command {

  private  double targetV;
  private  PizzaMotor subsystem;
  
  /** Creates a new VelocityLoopcontrol. */
  public VelocityLoopcontrol(PizzaMotor subsystem) {
    this.subsystem = subsystem;
     addRequirements(subsystem);
     SmartDashboard.putNumber("pizza velocity error", 0);
     targetV = SmartDashboard.getNumber("pizza target velocity", 0);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize()  {

}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
  //  targetV= SmartDashboard.getNumber("target velocity", targetV);  

  subsystem.driveMotor.setVelocityWithFeedForward(targetV);
    double error = targetV - subsystem.driveMotor.getCurrentVelocity();
    SmartDashboard.putNumber("pizza velocity error", error);
    
  
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
