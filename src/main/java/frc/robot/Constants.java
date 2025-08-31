// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;


import frc.Demacia.utils.Motors.SparkConfig;
import frc.Demacia.utils.Motors.TalonConfig;

import com.ctre.phoenix6.CANBus;
import com.ctre.phoenix6.swerve.SwerveModule;

import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.kinematics.SwerveDriveKinematics;
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

  public static class ModuleConstants {
    public static final double MAX_STEER_AMPS = 20;
    public static final double MAX_STEER_VOLTS = 8;
    public static final boolean STEER_INVERTED = false;
    public static final boolean DRIVE_INVERTED = false;
    public static final double STEER_GERA_RATIO = 150.0/7.0;
    public static final double DRIVE_GERA_RATIO = 6.75;
    public static final double STEER_KP = 0.002; 
    public static final double STEER_KI = 0.0000; 
    public static final double STEER_KD = 0.00000; 
    public static final double STEER_KS = 0.1;
    public static final double STEER_KV = 0.2;
    public static final double STEER_KA = 0;
    public static final double DRIVE_KP = 0.0002;
    public static final double DRIVE_KI = 0.0000;
    public static final double DRIVE_KD = 0.00000;
    public static final double DRIVE_KS = 0;
    public static final double DRIVE_KV = 0.00935;
    public static final double DRIVE_KA = 0;
    public static final double STEER_RAMP = 0.2;
    public static final double DRIVE_RAMP = 0.2;
    public static final double WHEEL_CIRCUMFERENCE = Math.PI * 4 * 0.0254;
    public static final double CYCLE_TIME = 0.02;

    public static final double STEER_VELOCITY_P = 2;
    public static final double ABS_ENCODER_OFFSET = 10;

    
    public static final double STEER_GEAR_RATIO = 12.8; // motor degrees to wheel degrees
    public static final double DRIVE_GEAR_RATIO = 8.14; // motor degrees to wheel degrees
    public static final double WHEEL_RADUIS = 0.05; // meters
    public static final String MOTOR_CANBUS = "rio";
  }
  public static class BaseConfigs {
    public static final TalonConfig DRIVE_TALON_CONFIG = new TalonConfig(10, Canbus.Rio, "Drive")
        .withBrake(true)
        .withCurrent(40)
        .withRampTime(0.3)
        .withVolts(12)
        .withPID(ModuleConstants.DRIVE_KP, 0, 0, ModuleConstants.DRIVE_KS, ModuleConstants.DRIVE_KV, 0, 0);

    
    public static final TalonConfig STEER_TALON_CONFIG = new TalonConfig(10, Canbus.Rio, "Steer")
        .withBrake(true)
        .withCurrent(40)
        .withRampTime(0.3)
        .withVolts(12)
        .withPID(ModuleConstants.STEER_KP, ModuleConstants.STEER_KI, ModuleConstants.STEER_KD, ModuleConstants.STEER_KS, ModuleConstants.STEER_KV, 0, 0)
        .withMotionParam(0, 0, 0);

    public static final SparkConfig BASE_SPARK_CONFIG = new SparkConfig(0, "base")
        .withBrake(true)
        .withCurrent(40)
        .withRampTime(0.3)
        .withVolts(12)
        .withPID(0, 0, 0, 0, 0, 0, 0);
  }

  public static class OperatorConstants {
    public static final int kDriverControllerPort = 0;
  }

  public static class ChassisConstants {
    public static final int[] FRONT_LEFT = new int[]{1, 2, 3}; //drive, steer, cancoder
    public static final int[] FRONT_RIGHT = new int[]{4, 5, 6};
    public static final int[] BACK_LEFT = new int[]{7, 8, 9};
    public static final int[] BACK_RIGHT = new int[]{10, 11, 12};
    public static final double FL_CANCODER_OFFSET = 0.3945;
    public static final double FR_CANCODER_OFFSET = 0.487;
    public static final double BL_CANCODER_OFFSET = 0.0622;
    public static final double BR_CANCODER_OFFSET = 0.4;
    public static final int GYRO_ID = 14;
    public static final String GYRO_CAN_BUS = "rio";

    public static final Translation2d[] KINEMATICS = new Translation2d[]{
      new Translation2d(0.315,0.265),new Translation2d(0.310,-0.265),new Translation2d(-0.315,-0.315),new Translation2d(-0.315,0.315)
    };
  }
}
