// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;


import frc.Demacia.utils.Motors.SparkConfig;
import frc.Demacia.utils.Motors.TalonConfig;
import edu.wpi.first.math.geometry.Translation2d;
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
    public static final int MOTOR_DRIVE_ID = 10;
    public static final String MOTOR_CAN ="rio";
    public static final int MOTOR_STEER_ID2 = 11;
    public static final String MOTOR_CAN2 ="rio";
  }
  public static final int GYRO_ID = 0;
  public static final double NumberOfWheelCyclesIn1Sec=8.14;
    public static final double SteerGearRetio=12.8;
    public static final double diameterWheel = 0.1016;
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
    public static final TalonConfig DRIVE_MOTOR_CONFIG = new TalonConfig(0, Canbus.Rio, "DRIVEMOTOR")
        .withBrake(true)
        .withCurrent(40)
        .withRampTime(0.3)
        .withVolts(12)
        .withPID(0, 0, 0, 0, 0, 0, 0);
        
        public static final TalonConfig STEER_MOTOR_CONFIG = new TalonConfig(0, Canbus.Rio, "STEERMOTOR")
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
  }
  public static class Swerve {
    public static final double MAX_SPEED_MPS =0.3; 
    public static final double MAX_ANGULAR_SPEED_RAD_PER_S =0.3;
    public static final Translation2d[] KINEMATICS = new Translation2d[]{
      new Translation2d(0.315,0.265),new Translation2d(0.310,-0.265),new Translation2d(-0.315,-0.315),new Translation2d(-0.315,0.315) } ;
    public static final double DEADBAND = 0;
    }
}

