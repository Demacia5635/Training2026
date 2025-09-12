// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import static edu.wpi.first.units.Units.Radians;

import com.ctre.phoenix6.configs.GyroTrimConfigs;
import com.ctre.phoenix6.hardware.Pigeon2;

import edu.wpi.first.math.estimator.SwerveDrivePoseEstimator;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.math.kinematics.SwerveDriveKinematics;
import edu.wpi.first.math.kinematics.SwerveModulePosition;
import edu.wpi.first.math.kinematics.SwerveModuleState;
import edu.wpi.first.wpilibj.smartdashboard.Field2d;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Chassis extends SubsystemBase {
  /** Creates a new Chassis. */
  SwerveModule moduleLeftBack;
  SwerveModule moduleLeftFront;
  SwerveModule moduleRightBack;
  SwerveModule moduleRightFront;
  SwerveDriveKinematics kinematics;
  Field2d field = new Field2d();
  Pigeon2 gyro;
  SwerveDrivePoseEstimator poseEstimator;
  public Chassis() {
    moduleLeftBack = new SwerveModule("LeftBack", Constants.ModuleConstants.steer_Motor_ID_back_left, Constants.ModuleConstants.Drive_Motor_ID_back_left);
    moduleLeftFront = new SwerveModule("LeftFront", Constants.ModuleConstants.steer_Motor_ID_Front_left, Constants.ModuleConstants.Drive_MOTOR_ID_Front_left);
    moduleRightBack = new SwerveModule("RightBack", Constants.ModuleConstants.steer_Motor_ID_back_right, Constants.ModuleConstants.Drive_Motor_ID_back_right);
    moduleRightFront = new SwerveModule("RightFront", Constants.ModuleConstants.steer_Motor_ID_Front_right, Constants.ModuleConstants.Drive_Motor_ID_Front_right);
    gyro = new Pigeon2(Constants.ModuleConstants.Gyro_Id, Constants.ModuleConstants.Gyro_Can);
    kinematics=new SwerveDriveKinematics(Constants.Swerve.KINEMATICS);
    poseEstimator = new SwerveDrivePoseEstimator(kinematics, getAngle(), getModulePositions(), new Pose2d());
    field = new Field2d(); 
    SmartDashboard.putData("resetGyro", new InstantCommand(() -> resetGyro()).ignoringDisable(true));
    SmartDashboard.putData("field", field);

  }
  public void resetPose(Pose2d pose2d){
    poseEstimator.resetPose(pose2d);
  }
  public void resetGyro() {
    resetPose(new Pose2d(getPose2d().getTranslation(), Rotation2d.kZero));
  }
  public Rotation2d getAngle(){ 
    return Rotation2d.fromRadians(gyro.getYaw().getValue().in(Radians));
  }
  public double getHeading(){
    return poseEstimator.getEstimatedPosition().getRotation().getDegrees();
  }
  public Pose2d getPose2d(){
    return poseEstimator.getEstimatedPosition();
  }
  public void setVelocities(ChassisSpeeds wantSpeeds, ChassisSpeeds speeds){
    ChassisSpeeds robotSpeed = ChassisSpeeds.fromFieldRelativeSpeeds(wantSpeeds, gyro.getRotation2d());
    SwerveModuleState[] state = kinematics.toSwerveModuleStates(robotSpeed);
    moduleLeftFront.SetState(state[1]);
    moduleRightFront.SetState(state[2]);
    moduleLeftBack.SetState(state[4]);
    moduleRightBack.SetState(state[3]);
  }
  public SwerveModulePosition[] getModulePositions(){
    SwerveModulePosition[] swerveModulesPositions = new SwerveModulePosition[4];
    swerveModulesPositions [1] = new SwerveModulePosition(moduleLeftFront.getDriveMotorVelocity(), Rotation2d.fromDegrees(moduleLeftFront.getPositionOfSteer()));
    swerveModulesPositions [2] = new SwerveModulePosition(moduleRightFront.getDriveMotorVelocity(), Rotation2d.fromDegrees(moduleRightFront.getPositionOfSteer()));
    swerveModulesPositions [3] = new SwerveModulePosition(moduleLeftBack.getDriveMotorVelocity(), Rotation2d.fromDegrees(moduleLeftBack.getPositionOfSteer()));
    swerveModulesPositions [4] = new SwerveModulePosition(moduleRightBack.getDriveMotorVelocity(), Rotation2d.fromDegrees(moduleRightBack.getPositionOfSteer()));
    return swerveModulesPositions;
  }
  

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    poseEstimator.update(getAngle(), getModulePositions());
    field.setRobotPose(getPose2d());
  }
}
