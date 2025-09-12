// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.hardware.TalonFXS;
import com.ctre.phoenix6.swerve.SwerveModuleConstants.DriveMotorArrangement;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.controller.SimpleMotorFeedforward;
import edu.wpi.first.math.kinematics.SwerveModuleState;
import edu.wpi.first.util.sendable.SendableBuilder;
import edu.wpi.first.wpilibj.motorcontrol.Talon;
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

  public SwerveModule(String name,int steerId, int driveId) {
    super();
    driveMotor = new TalonMotor(new TalonConfig(driveId , Canbus.CANIvore , name));
    steerMotor = new TalonMotor(new TalonConfig(steerId , Canbus.CANIvore, name));
    steerController = new PIDController(Constants.ModuleConstants.Kp, Constants.ModuleConstants.Ki, Constants.ModuleConstants.Kd);
    driveController = new PIDController(Constants.ModuleConstants.kp2, Constants.ModuleConstants.ki2, Constants.ModuleConstants.kd2);
    FF = new SimpleMotorFeedforward(Constants.ModuleConstants.kS, Constants.ModuleConstants.kV);
    SmartDashboard.putData(this);
  }


  public void setPowerDriveMotor(double power){
    driveMotor.set(power);
    driveController.setTolerance(0.1);
    driveController.setIntegratorRange(-0.1, 0.1);
  } 


  public void setPowerSteerMotor(double power){
    steerMotor.set(power);
    steerController.setTolerance(1.0);
    steerController.setIntegratorRange(-1, 1);
  }

  public double getDriveMotorVelocity(){
    return (driveMotor.getVelocity().getValueAsDouble()/NumberOfWheelCyclesIn1Sec)*diameterWheel*Math.PI;
  }

  public double getDriveMotorPosition(){
    return (driveMotor.getPosition().getValueAsDouble()/NumberOfWheelCyclesIn1Sec)*diameterWheel*Math.PI;
  }

  public double getSteerMotorVelocity(){
    return (steerMotor.getVelocity().getValueAsDouble()/Constants.ModuleConstants.Degree_ratio)*360;
  }

  public void setPositionSteer(double angle){
    steerMotor.setPosition(angle);
  }

  public double getPositionOfSteer(){
    return ((steerMotor.getPosition().getValueAsDouble()/Constants.ModuleConstants.Degree_ratio)*360)%360;
  }

  public void setVelocityDrive(double velocityTarget){
    double currentVelocity = getDriveMotorVelocity();
    double power = driveController.calculate(currentVelocity, velocityTarget) + FF.calculateWithVelocities(currentVelocity, velocityTarget);
    setPowerDriveMotor(power);
  }
  public void SetSteerAngle(double targetAngle){
    double currentAngle = getPositionOfSteer();
    double PowerToTargetAngle = steerController.calculate(currentAngle, targetAngle);
    setPowerSteerMotor(PowerToTargetAngle); 
  }
  public double getAbsouluteAngle(){
    return cancoder.getAbsolutePosition().getValueAsDouble();
  }
  public void stop(){
    driveMotor.set(0);
    steerMotor.set(0);
  }


  public void SetState(SwerveModuleState state){
        double wantedAngle = state.angle.getDegrees();
        double velocity = state.speedMetersPerSecond;
        setVelocityDrive(velocity);
        SetSteerAngle(wantedAngle);
  }

  @Override
  public void initSendable(SendableBuilder builder) {
    builder.addDoubleProperty("driveVelocity", this::getDriveMotorVelocity, null);
    builder.addDoubleProperty("steerVelocity", this::getSteerMotorVelocity, null);
    builder.addDoubleProperty("steerAngle", this::getPositionOfSteer, null);
    builder.addDoubleProperty("DriveMotorPosition", this::getDriveMotorPosition, null);

  }
  @Override
  public void periodic() {
    // This method will be called once per scheduler run

  }
}
