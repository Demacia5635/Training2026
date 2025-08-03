// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import java.lang.module.ModuleDescriptor.Builder;

import com.ctre.phoenix6.hardware.TalonFX;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.util.sendable.SendableBuilder;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.subsystems.Modle;

/* You should consider using the more terse Command factories API instead https://docs.wpilib.org/en/stable/docs/software/commandbased/organizing-command-based.html#defining-commands */
public class PID extends Command {
  /** Creates a new PID. */ 
  private double engle = 0;
  private PIDController velocityController = new PIDController(0.004, 0.0, 0.0003);
  private double currentAngle = 0;
  private Modle module; // Assuming Module is a class that represents your subsystem

 
  public PID(Modle module) {
    SmartDashboard.putData("Subsystem 1", this);
    this.module = module; // Initialize the Module subsystem
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    currentAngle= module.getSteerPosition(); // Assuming this method returns the current angle of the motor
    double Power = velocityController.calculate(currentAngle, engle);
    module.setSteerPower(Power); // Set the motor power based on the PID calculation
  }
  
  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {module.setSteerPower(0);}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return Math.abs(engle - currentAngle) < 3; // Command is finished when within 3 degrees of target angle
    
  }
  public void initSendable(SendableBuilder Builder) {
    Builder.addDoubleProperty("Angle", this::getEngle, this::setEngle);
  }
  
  public double getEngle() {
    return this.engle;
  }
  public void setEngle(double engle) {
    this.engle = engle;
  }

}

