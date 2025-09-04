// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.math.estimator.SwerveDrivePoseEstimator;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ModuleConstants;
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
  public double vx;
  public double vy;
  public double vrD;
  public BaseChassis (double vx, double vy, double vrD,TalonConfig lbSteer, TalonConfig lbDrive, int lbEncoder, TalonConfig lfSteer, TalonConfig lfDrive, int lfEncoder, TalonConfig rbSteer, TalonConfig rbDrive,int rbEncoder, TalonConfig rfSteer, TalonConfig rfDrive,int rfEncoder) {
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
  this.vx=vx;
  this.vy=vy;
  this.vrD=vrD;
  leftBackModule = new BaseSwerveModule(lbSteer, lbDrive, lbEncoder);
  leftFrontModule = new BaseSwerveModule(lfSteer, lfDrive, lfEncoder);
  rightBackModule = new BaseSwerveModule(rbSteer, rbDrive,  rbEncoder);
  rightFrontModule = new BaseSwerveModule(rfSteer, rfDrive, rfEncoder);
  ChassisSpeeds pizzaSpeed = new ChassisSpeeds(vx, vy, Math.toRadians(vrD));
}
  @Override
  public void periodic() {
    // This method will be called once per scheduler run



  }
  public void driveSetPower(double signPower){
    leftBackModule.driveSetPower(signPower);
    leftFrontModule.driveSetPower(signPower);
    rightBackModule.driveSetPower(signPower);
    rightFrontModule.driveSetPower(signPower);
  }
  public void setPowerForPosition(double targetPosition){
    leftBackModule.setPowerForPosition(targetPosition+ ModuleConstants.absEncoderLBcali);
    leftFrontModule.setPowerForPosition(targetPosition+ ModuleConstants.absEncoderLFcali);
    rightBackModule.setPowerForPosition(targetPosition + ModuleConstants.absEncoderRBcali);
    rightFrontModule.setPowerForPosition(targetPosition + ModuleConstants.absEncoderRFcali);
  }
}
