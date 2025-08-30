// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.fasterxml.jackson.databind.JsonSerializable.Base;

import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class SwervePizza extends SubsystemBase {
  /** Creates a new SwervePizza. */
  public BaseChassis pizza;
  public SwervePizza() {
   pizza = new BaseChassis(4,4,45,Constants.MotorsConfig.LBS_CONFIG,Constants.MotorsConfig.LBD_CONFIG, Constants.Encoders.LB_ENCODER, Constants.MotorsConfig.LFS_CONFIG, Constants.MotorsConfig.LFD_CONFIG, Constants.Encoders.LF_ENCODER, Constants.MotorsConfig.RBS_CONFIG, Constants.MotorsConfig.RBD_CONFIG, Constants.Encoders.RB_ENCODER, Constants.MotorsConfig.RFS_CONFIG, Constants.MotorsConfig.RFD_CONFIG, Constants.Encoders.RF_ENCODER);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
