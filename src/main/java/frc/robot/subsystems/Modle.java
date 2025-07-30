// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix6.hardware.TalonFX;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Modle extends SubsystemBase {
  private TalonFX steerMotor;
  private TalonFX driveMotor;
  /** Creates a new Modle. */
  public Modle() {

    steerMotor = new TalonFX(Constants.steerMotorId); // Replace 1 with the actual CAN ID of your motor
    driveMotor = new TalonFX(Constants.steerMotorId); // Replace 1 with the actual CAN ID of your motor
  }
  public void setSteerPower(double power) {
    // Set the motor power
    steerMotor.set(power); // Assuming power is between -1.0 and 1.0
  }
  public double getSteerPosition() {
    // Get the current position of the motor
    return steerMotor.getPosition().getValueAsDouble()/Constants.SteerGearRatio*360; // Assuming this returns the position in degrees or rotations
  }
  
  @Override
  public void periodic() {
    SmartDashboard.putNumber("angle", getSteerPosition());
    SmartDashboard.putNumber("distans", getSteerPosition());
    // This method will be called once per scheduler run

  }

  public void setdrivePower(double power) {
    // Set the motor power
    driveMotor.set(power); // Assuming power is between -1.0 and 1.0
  }
  public double getdrivePosition() {
    // Get the current position of the motor
    return driveMotor.getPosition().getValueAsDouble()/8.4*0.1016; // Assuming this returns the position in degrees or rotations
  }
  
  
   
    // This method will be called once per scheduler run

  

}
