// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix6.configs.GyroTrimConfigs;
import com.ctre.phoenix6.hardware.Pigeon2;

import edu.wpi.first.math.kinematics.SwerveDriveKinematics;
import edu.wpi.first.wpilibj.smartdashboard.Field2d;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Chassis extends SubsystemBase {
  /** Creates a new Chassis. */
  SwerveModule moduleLeftBack;
  SwerveModule moduleLeftFront;
  SwerveModule moduleRightBack;
  SwerveModule moduleRightFront;
  SwerveDriveKinematics kinematics=new SwerveDriveKinematics(Constants.Swerve.KINEMATICS);
  Field2d field = new Field2d();
  Pigeon2 gyro = new Pigeon2(Constants.GYRO_ID);
  public Chassis() {
    moduleLeftBack=new SwerveModule();
    moduleLeftFront=new SwerveModule();
    moduleRightBack=new SwerveModule();
    moduleRightFront=new SwerveModule();
  }

  

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
