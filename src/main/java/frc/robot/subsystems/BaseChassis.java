// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.math.estimator.SwerveDrivePoseEstimator;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.subsystems.BaseSwerveModule;
import frc.Demacia.utils.Motors.TalonConfig;

public class BaseChassis extends SubsystemBase {
  /** Creates a new BaseChassis. */
  public TalonConfig lbSteer;
  public TalonConfig lbDrive;
  public int lbEncoder;
  public TalonConfig lfSteer;
  public TalonConfig lfDrive;
  public int lfEncoder;
  public TalonConfig rbSteer;
  public TalonConfig rbDrive;
  public int rbEncoder;
  public TalonConfig rfSteer;
  public TalonConfig rfDrive;
  public int rfEncoder;
  public BaseSwerveModule leftBackModule;
  public BaseSwerveModule leftFrontModule;
  public BaseSwerveModule rightBackModule;
  public BaseSwerveModule rightFrontModule;
  public BaseChassis (TalonConfig lbSteer, TalonConfig lbDrive, int lbEncoder, TalonConfig lfSteer, TalonConfig lfDrive, int lfEncoder, TalonConfig rbSteer, TalonConfig rbDrive,int rbEncoder, TalonConfig rfSteer, TalonConfig rfDrive,int rfEncoder) {
  this.lbDrive = lbDrive;
  this.lbSteer = lbSteer;
  this.lbEncoder = lbEncoder;
  this.lfDrive = lfDrive;
  this.lfSteer = lfSteer;
  this.lfEncoder = lfEncoder;
  this.rbDrive = rbDrive;
  this.rbSteer = rbSteer;
  this.rbEncoder = rbEncoder;
  this.rfDrive = rfDrive;
  this.rfSteer = rfSteer;
  this.rfEncoder = rfEncoder;
  leftBackModule = new BaseSwerveModule(lbSteer, lbDrive, lbEncoder);
  leftFrontModule = new BaseSwerveModule(lfSteer, lfDrive, lfEncoder);
  rightBackModule = new BaseSwerveModule(rbSteer, rbDrive,  rbEncoder);
  rightFrontModule = new BaseSwerveModule(rfSteer, rfDrive, rfEncoder);
}
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    SwerveDrivePoseEstimator poseEstimator = new SwerveDrivePoseEstimator(kinematics,gyroAngle,modulesPosition,initialPosition);
poseEstimator.update(gyroAngle, modulesPositions);
poseEstimator.getEstimatedPosition()
poseEstimator.resetPose(pose);
poseEstimator.addVisionMeasurement(pose, time);


  }
}
