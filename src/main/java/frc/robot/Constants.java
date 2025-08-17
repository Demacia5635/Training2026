// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;


import frc.Demacia.utils.Motors.SparkConfig;
import frc.Demacia.utils.Motors.TalonConfig;
import frc.Demacia.utils.Motors.BaseMotorConfig.Canbus;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
  public static class MyFirstSubsystemConstants {
    public static final int SMOTOR_ID = 11;
    public static final String MOTOR_CAN = "rio";
    public static final int DMOTOR_ID = 10;
  }
public static class MotorsConfig {
  public static final TalonConfig DRIVE_CONFIG = new TalonConfig(Constants.MyFirstSubsystemConstants.DMOTOR_ID, Canbus.Rio, "driveMexicana")
          .withBrake(true)
          .withCurrent(20)   						// Current Limit
          .withInvert(true)
          .withMeterMotor(OperatorConstants.DgearRatio, OperatorConstants.wheelDiameter*0.0254) 				// Gear Ratio and Wheel Circumference
          .withPID(0.001, 0, 0, -0.1, 1.3, 0, 0) 			// kp, ki, kd, ks, kv, ka, kg
          .withRampTime(0.3) 						// time from zero to max power in seconds
          .withVolts(6); 						// max volt
  public static final TalonConfig STEER_CONFIG = new TalonConfig(Constants.MyFirstSubsystemConstants.DMOTOR_ID, Canbus.Rio, "driveMexicana")
          .withBrake(true)
          .withCurrent(20)   						// Current Limit
          .withInvert(true)
          .withMeterMotor(OperatorConstants.SgearRatio, OperatorConstants.wheelDiameter*0.0254) 				// Gear Ratio and Wheel Circumference
          .withPID(0.001, 0, 0, -0.1, 1.3, 0, 0) 			// kp, ki, kd, ks, kv, ka, kg
          .withRampTime(0.3) 						// time from zero to max power in seconds
          .withVolts(6); 						// max volt
}
  public static class ModuleConstants {
    public static final int STEER_ID = 1;
    public static final int DRIVE_ID = 2;
    public static final int CANBCODER_ID = 3;
    public static final double MAX_STEER_AMPS = 20;
    public static final double MAX_STEER_VOLTS = 8;
    public static final boolean STEER_INVERTED = false;
    public static final boolean DRIVE_INVERTED = false;
    public static final double STEER_GERA_RATIO = 150.0/7.0;
    public static final double DRIVE_GERA_RATIO = 6.75;
    public static final double STEER_KP = 0.1;
    public static final double STEER_KI = 0.0;
    public static final double STEER_KD = 0.0;
    public static final double STEER_KS = 0.1;
    public static final double STEER_KV = 0.2;
    public static final double STEER_KA = 0.01;
    public static final double DRIVE_KP = 0.1;
    public static final double DRIVE_KI = 0.0;
    public static final double DRIVE_KD = 0.0;
    public static final double DRIVE_KS = 0.1;
    public static final double DRIVE_KV = 0.2;
    public static final double DRIVE_KA = 0.01;
    public static final double STEER_RAMP = 0.2;
    public static final double DRIVE_RAMP = 0.2;
    public static final double WHEEL_CIRCUMFERENCE = Math.PI * 4 * 0.0254;

    public static final double STEER_VELOCITY_P = 2;
    public static final double ABS_ENCODER_OFFSET = 10;
  }

  public static class BaseConfigs {
    public static final TalonConfig BASE_TALON_CONFIG = new TalonConfig(0, Canbus.Rio, "base")
        .withBrake(true)
        .withCurrent(40)
        .withRampTime(0.3)
        .withVolts(12)
        .withPID(0, 0, 0, 0, 0, 0, 0);

    public static final SparkConfig BASE_SPARK_CONFIG = new SparkConfig(0, "base")
        .withBrake(true)
        .withCurrent(40)
        .withRampTime(0.3)
        .withVolts(12)
        .withPID(0, 0, 0, 0, 0, 0, 0);
  }

  public static class OperatorConstants {
    public static final int DriverControllerPort = 0;
    public static final double SgearRatio = 12.8;
    public static final double DgearRatio = 8.14 ;
    public static final double maxSpeedMpS = 4.80;
    public static final double wheelDiameter = 0.1016;
  }
}
