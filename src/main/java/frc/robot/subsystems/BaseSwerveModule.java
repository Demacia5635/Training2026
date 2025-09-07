// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;


import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.motorcontrol.Talon;
import java.io.ObjectInputFilter.Config;

import com.ctre.phoenix6.hardware.CANcoder;

import frc.Demacia.utils.Motors.MotorInterface;
import frc.Demacia.utils.Motors.TalonConfig;
import frc.Demacia.utils.Motors.TalonMotor;
import frc.robot.Constants;
import frc.robot.Constants.ModuleConstants;


public class BaseSwerveModule extends SubsystemBase {
  /** Creates a new BaseSwerveModule. */
  public TalonConfig driveConfig;
  public TalonConfig steerConfig;
      public MotorInterface drive; 
    public MotorInterface steer;
    public CANcoder absEncoder;

  public BaseSwerveModule(TalonConfig steerConfig, TalonConfig driveConfig, int CANBCODER_ID) {
    this.driveConfig = driveConfig;
    this.steerConfig = steerConfig;
    drive = new TalonMotor(driveConfig);
    steer= new TalonMotor(steerConfig);
    absEncoder = new CANcoder(CANBCODER_ID);
    steer.setEncoderPosition(getAbsAngle()-ModuleConstants.offsetArr[CANBCODER_ID/3]);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
  public void setDriveVelocity(double velocity){
    drive.setVelocity(velocity*2);
  }
  public void setSteerPosition(double position){
    steer.setAngle(position);
  }
  public double getAbsAngle(){
    return absEncoder.getPosition().getValueAsDouble();
  }
}
