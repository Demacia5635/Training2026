// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.hardware.TalonFX;

import edu.wpi.first.util.sendable.SendableBuilder;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Modle extends SubsystemBase {
  private TalonFX driveMotor;
  private TalonFX steerMotor;
  
  /** Creates a new modle. */
  public Modle() {
    driveMotor= new TalonFX(Constants.driveMotor);
    steerMotor= new TalonFX(Constants.steerMotor);
    driveMotor.getConfigurator().apply(new TalonFXConfiguration());
  }
  public double getSteerPosition(){
    return steerMotor.getPosition().getValueAsDouble()/Constants.geerRatio*360;
  }
  public void setSteerPower(double power){
    steerMotor.set(power);
  }
  public void setDrivePower(double power){
    driveMotor.set(power);
  }

  public void initSendable(SendableBuilder builder){
    builder.addDoubleProperty("wanted angle",this::getSteerPosition,null);
    builder.addDoubleProperty("velocity", this::getMotorVelocity, null);

  }
  public double getMotorVelocity(){
    return driveMotor.getVelocity().getValueAsDouble();
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
