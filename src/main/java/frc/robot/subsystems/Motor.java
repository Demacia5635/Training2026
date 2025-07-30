// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import java.lang.constant.ConstantDescs;

import com.ctre.phoenix6.hardware.TalonFX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Motor extends SubsystemBase {
  /** Creates a new Motor. */
  private TalonFX motor, motor2;

  public Motor() {
    motor = new TalonFX(Constants.MotorId); 
    motor2 = new TalonFX(Constants.Motor2Id); 
// Replace 1 with the actual CAN ID of your motor
  }
  public void setPwoer(double power) {
    // Set the motor power
    motor.set(power);// Assuming power is between -1.0 and 1.0
  }


  public void setPwoer2(double power) {
    // Set the motor power
    motor2.set(power);// Assuming power is between -1.0 and 1.0
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
  
  
  

}