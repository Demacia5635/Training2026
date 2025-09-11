// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix6.swerve.SwerveModuleConstants.DriveMotorArrangement;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.controller.SimpleMotorFeedforward;
import edu.wpi.first.util.sendable.SendableBuilder;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.Demacia.utils.Motors.TalonConfig;
import frc.Demacia.utils.Motors.TalonMotor;
import frc.Demacia.utils.Motors.BaseMotorConfig.Canbus;
import frc.Demacia.utils.Sensors.Cancoder;
import frc.Demacia.utils.Sensors.CancoderConfig;
import frc.robot.Constants;
import static frc.robot.Constants.*;

public class SwerveModule extends SubsystemBase {
  /** Creates a new SwerveModule. */
  private Cancoder cancoder;
  private TalonMotor driveMotor;
  private TalonMotor steerMotor;
  private PIDController steerController;
  private PIDController driveController;
  private SimpleMotorFeedforward FF;

  public SwerveModule(int cancoderId, int steerId, int driveId, String steerName, String driveName, String canCoderName) {
    cancoder = new Cancoder(new CancoderConfig(cancoderId ,Canbus.Rio, canCoderName));
    driveMotor = new TalonMotor(new TalonConfig(driveId , Canbus.Rio, driveName));
    steerMotor = new TalonMotor(new TalonConfig(steerId , Canbus.Rio, steerName));
    steerController = new PIDController(Constants.ModuleConstants.STEER_KP, Constants.ModuleConstants.STEER_KI, Constants.ModuleConstants.STEER_KD);
    driveController = new PIDController(Constants.ModuleConstants.DRIVE_KP, Constants.ModuleConstants.DRIVE_KI, Constants.ModuleConstants.DRIVE_KD);
    FF = new SimpleMotorFeedforward(steerId, driveId)
    SmartDashboard.putData(this);
  }
  public void setPositionSteer(double angle){
    steerMotor.setPosition(angle);
  }
  public double getPositionOfSteer(){
    return (steerMotor.)
  }
  public double getDriveMotorVelocity(){
    return (driveMotor.getVelocity().getValueAsDouble()/NumberOfWheelCyclesIn1Sec)*diameterWheel*Math.PI;
  }
  public double getSteerMotorVelocity(){
    return (steerMotor.getVelocity().getValueAsDouble()/NumberOfWheelCyclesIn1Sec)*diameterWheel*Math.PI;
  }
  public void setVelocityDrive(double velocity){
    driveMotor.setVelocity(velocity);
  }
  public void setVelocitySteer(double velocity){
    steerMotor.setVelocity(velocity);
  }
  public double getAbsouluteAngle(){
    return cancoder.getAbsolutePosition().getValueAsDouble();
  }
  public void stop(){
    driveMotor.set(0);
    steerMotor.set(0);
  }
  @Override
  public void initSendable(SendableBuilder builder) {
    builder.addDoubleProperty("driveVelocity", this::getDriveMotorVelocity, null);
    builder.addDoubleProperty("steerVelocity", this::getSteerMotorVelocity, null);
    builder.addDoubleProperty("absouluteAngle", this::getAbsouluteAngle, null);

  }
  @Override
  public void periodic() {
    // This method will be called once per scheduler run

  }
}
