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
  /** Creates a new Modle. */
  public Modle() {

    steerMotor = new TalonFX(Constants.steirMotorId); // Replace 1 with the actual CAN ID of your motor
  }
  public void setPower(double power) {
    // Set the motor power
    steerMotor.set(power); // Assuming power is between -1.0 and 1.0
  }
  public double getPosition() {
    // Get the current position of the motor
    return steerMotor.getPosition().getValueAsDouble()/12.8*360; // Assuming this returns the position in degrees or rotations
  }
  
  @Override
  public void periodic() {
    SmartDashboard.putNumber("angle", getPosition());
    // This method will be called once per scheduler run

  }

  public void motor(double power){
    steerMotor.set(power);
  
  }
   
  

}
