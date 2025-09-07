// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.estimator.SwerveDrivePoseEstimator;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.math.kinematics.SwerveDriveKinematics;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ModuleConstants;
import frc.robot.subsystems.BaseSwerveModule;
import frc.Demacia.utils.Motors.TalonConfig;
import frc.robot.utils; 
public class BaseChassis extends SubsystemBase {
  /** Creates a new BaseChassis. */
  public utils utils;
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
  public void driveSetvelocity(double velocity){
    leftBackModule.setDriveVelocity(velocity);
    leftFrontModule.setDriveVelocity(velocity);
    rightBackModule.setDriveVelocity(velocity);
    rightFrontModule.setDriveVelocity(velocity);
  }
  public void setPowerForPosition(double targetPosition){
    leftBackModule.setSteerPosition(targetPosition+ ModuleConstants.absEncoderLBcali);
    leftFrontModule.setSteerPosition(targetPosition+ ModuleConstants.absEncoderLFcali);
    rightBackModule.setSteerPosition(targetPosition + ModuleConstants.absEncoderRBcali);
    rightFrontModule.setSteerPosition(targetPosition + ModuleConstants.absEncoderRFcali);
  }
  // Ensure utils is defined or imported before using it
  public void hishuvim(){
    double pizzaSpeed = utils.driveparameter() * 2;
    double kivun= utils.driveparameter();   
 kinematics.toSwerveModuleStates(pizzaSpeed,pizzaSpeed,45);                                          






robotSpeeds = ChassisSpeeds.fromFieldRelativeSpeeds(pizzaSpeed, getRobotAngle());

SwerveDriveKinematics kinematics = new SwerveDriveKinematics(
  new Translation2d[] {
    new Translation2d(0.35, 0.3),
    new Translation2d(0.35, -0.3),
    new Translation2d(-0.35, 0.3),
    new Translation2d(-0.35, -0.3)});
kinematics.toSwerveModuleStates()                                          
kinematics.toChassisSpeeds() 
}
}




  
