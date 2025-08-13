// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import java.lang.ModuleLayer.Controller;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.units.measure.Velocity;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.MyFirstSubsystem;

/* You should consider using the more terse Command factories API instead https://docs.wpilib.org/en/stable/docs/software/commandbased/organizing-command-based.html#defining-commands */
public class pid extends Command {
 private PIDController controller = new PIDController(0.07, 1.7, 0.01);
  // 2° position, 10°/s velocity
 private double targetvelocity;   

 private MyFirstSubsystem subsystem;
  /** Creates a new pid. */
  public pid(MyFirstSubsystem subsystem, double targetvelocity ) {
    // Use addRequirements() here to declare subsystem dependencies.
    this.subsystem = subsystem;
    this.targetvelocity = targetvelocity;
    controller.setTolerance(0.1);
    addRequirements(subsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
   controller.reset();
   controller.setSetpoint(targetvelocity);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    double turnSpeed = controller.calculate(subsystem.getVelocity());
    subsystem.setPower(turnSpeed);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    subsystem.stop();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return controller.atSetpoint();
  }
  // public PIDController getPIDController() {
  //   // Example PIDController creation
  //   return new PIDController(1.0, 0.0, 0.0);
  // }
  
}
