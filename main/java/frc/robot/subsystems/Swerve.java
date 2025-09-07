// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;

import java.util.concurrent.TransferQueue;

import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.kinematics.SwerveDriveKinematics;


public class Swerve extends SubsystemBase {
  /** Creates a new Swerve. */
  public Swerve() {


    Translation2d FRONT_LEFT = new Translation2d(+0.381, +0.381);
    Translation2d FRONT_RIGHT = new Translation2d(+0.381, -0.381);  
    Translation2d BACK_LEFT = new Translation2d(-0.381, +0.381);
    Translation2d BACK_RIGHT = new Translation2d(-0.381, -0.381);

    public SwerveDriveKinematics kinematics = new SwerveDriveKinematics(
      FRONT_LEFT,
      FRONT_RIGHT,
      BACK_LEFT,
      BACK_RIGHT,
      new Translation2d()
  );

  }
   
  private SwerveDriveKinematics kinematics;
    
    
  @Overridex
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
