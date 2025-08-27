// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.util.sendable.SendableBuilder;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.Demacia.utils.Motors.BaseMotorConfig.Canbus;
import frc.Demacia.utils.Motors.MotorInterface;
import frc.robot.Constants;
import frc.robot.Constants.OperatorConstants;
import frc.Demacia.utils.Motors.TalonConfig;
import frc.Demacia.utils.Motors.TalonMotor;

public class PizzaMotor extends SubsystemBase {
    public MotorInterface driveMotor; 
    public MotorInterface steerMotor;

  public PizzaMotor() {
    super();
  driveMotor = new TalonMotor(Constants.MotorsConfig.DRIVE_CONFIG);
  steerMotor = new TalonMotor(Constants.MotorsConfig.STEER_CONFIG);

  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
  @Override
 public void initSendable(SendableBuilder builder){
    super.initSendable(builder);
    builder.addDoubleProperty("pizza target velocity", () -> driveMotor.getCurrentVelocity(), null);
 }
}
