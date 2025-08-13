// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix6.hardware.CANcoder;
import com.ctre.phoenix6.hardware.TalonFX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class ModuleFrog extends SubsystemBase {
  /** Creates a new ModuleFrog. */
  private TalonFX driveMotorFrog;
  private TalonFX steerMotorFrog;
  private CANcoder frogNcoder;
  public ModuleFrog() {
    driveMotorFrog = new TalonFX(Constants.ModuleFrogConstants.driveMotorFrogID);
    steerMotorFrog = new TalonFX(Constants.ModuleFrogConstants.steerMotorFrogID);
    frogNcoder = new CANcoder(Constants.ModuleFrogConstants.frogNcoderID);
  }
  public void setDrivePowerFrog(double power){
    driveMotorFrog.set(power);
  }
  public void setSteerPowerFrog(double power){
    steerMotorFrog.set(power);
  }
  public void stop(){
    setDrivePowerFrog(0);
    setSteerPowerFrog(0);
  }
  public double getSteerAngle(){
    return steerMotorFrog.getPosition().getValueAsDouble()/Constants.ModuleFrogConstants.moduleSteerGiratio*360;
  }


  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
