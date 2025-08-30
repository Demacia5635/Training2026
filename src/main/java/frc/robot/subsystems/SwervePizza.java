// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.fasterxml.jackson.databind.JsonSerializable.Base;

import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class SwervePizza extends SubsystemBase {
  /** Creates a new SwervePizza. */
  public SwervePizza() {
    BaseChassis pizza = new BaseChassis();
    Translation2d point1 = new Translation2d(1,3);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
