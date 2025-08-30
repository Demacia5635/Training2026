// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.util.sendable.SendableBuilder;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.Demacia.utils.Motors.TalonMotor;
import frc.robot.Constants;

public class Modules extends SubsystemBase {

  TalonMotor driveMotor = new TalonMotor(Constants.BaseConfigs.DRIVE_TALON_CONFIG);
  TalonMotor steerMotor = new TalonMotor(Constants.BaseConfigs.STEER_TALON_CONFIG);
  /** Creates a new Modules. */
  public Modules() {
    SmartDashboard.putData(this);
  }

  public void setDrivePower(double power) {
    driveMotor.set(power);
  }

  public void setSteerPower(double power) {
    steerMotor.set(power);
  }

  
  @Override
  public void initSendable(SendableBuilder builder) {
    super.initSendable(builder);
    builder.addDoubleProperty("Drive Power", driveMotor::get, this::setDrivePower);
    builder.addDoubleProperty("Steer Power", steerMotor::get, this::setSteerPower);
    builder.addDoubleProperty("Drive Position", driveMotor::getCurrentPosition, null);
    builder.addDoubleProperty("Steer Position", steerMotor::getCurrentPosition, null);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
