// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix6.hardware.TalonFX;

import edu.wpi.first.util.sendable.SendableBuilder;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Motor extends SubsystemBase {
  /** Creates a new Motor. */
  final double gearRatio=12.8;
  TalonFX motor;//
  double angle = 90;
  // constants.MyFirstSubsystemConstants.MOTOR_ID, 
  // Constants.MyFirstSubsystemConstants.MOTOR_CAN;

  public Motor() {
    motor = new TalonFX(10);
  }
  // get motor position
  public double getPosition() {
    return (motor.getPosition().getValueAsDouble()/gearRatio)*360 % 360;
  }

  public void setPow(double pow){
    motor.set(pow);
  }

  public void setAng(double ang) {
    this.angle = ang;
  }

  public double getAng() {
    return angle;
  }

  @Override
  public void initSendable(SendableBuilder builder) {
      builder.addDoubleProperty("Position", this::getPosition, null);
      builder.addDoubleProperty("angle", this::getAng, this::setAng);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    SmartDashboard.putData(this);
  }
}
