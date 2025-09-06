// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix6.swerve.SwerveModuleConstants.DriveMotorArrangement;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.Demacia.utils.Motors.TalonConfig;
import frc.Demacia.utils.Motors.TalonMotor;
import frc.Demacia.utils.Motors.BaseMotorConfig.Canbus;
import frc.Demacia.utils.Sensors.Cancoder;
import frc.Demacia.utils.Sensors.CancoderConfig;
import frc.robot.Constants;

public class SwerveModule extends SubsystemBase {
  /** Creates a new SwerveModule. */
  private Cancoder cancoder=new Cancoder(new CancoderConfig(3, null, getName()));
  TalonConfig driveConfig = new TalonConfig(0, Canbus.CANIvore, getName());
  private TalonMotor driveMotor=new 
  private TalonMotor steerMotor= new TalonMotor(new TalonConfig(0, getName(), null));
  public SwerveModule() {

  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
