// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package tag;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.smartdashboard.Field2d;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.utils.LogManager;
import frc.robot.vision.Camera;
import frc.robot.vision.Camera.CameraType;
import static frc.robot.vision.utils.VisionConstants.*;
import java.util.function.Supplier;
//pich = ty
// yaw = yx
public class tag extends SubsystemBase {
 

  private Translation2d imagefromTheCamara;
  private Translation2d angleFromJyro;
  private double speed;

  // NetworkTables communication for each camera
  private NetworkTable Table;

  // Vision processing variables
 

  /** Creates a new chasisis. */
  public tag() {}

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
