// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix6.hardware.TalonFX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Motor extends SubsystemBase {
  /** Creates a new Motor. */
  private TalonFX motor;
  public Motor() {
    motor= new TalonFX(Constants.motorId);
  }
  public void SetPowerForMotor(double power){
    motor.set(power);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
