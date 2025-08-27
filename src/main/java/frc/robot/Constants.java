// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;


import edu.wpi.first.math.geometry.Translation2d;
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
  public static final class CAN {
    public static final int FL_DRIVE_ID = 1;
    public static final int FL_STEER_ID = 2;
    public static final int FR_DRIVE_ID = 4;
    public static final int FR_STEER_ID = 5;
    public static final int BL_DRIVE_ID = 7;
    public static final int BL_STEER_ID = 8;
    public static final int BR_DRIVE_ID = 10;
    public static final int BR_STEER_ID = 11;
    public static final int PIGEON_ID = 14;
    public static final int FL_CANcoder_ID = 3; 
    public static final int FR_CANcoder_ID = 6;
    public static final int BL_CANcoder_ID = 9;
    public static final int BR_CANcoder_ID = 12;
    public static final double FL_CANcoder_Ofset =0.3945;
    public static final double FR_CANcoder_Ofset =0.487;
    public static final double BL_CANcoder_Ofset =-0.0622;
    public static final double BR_CANcoder_Ofset =0.4;
    public static final Translation2d[] KINEMATICS = new Translation2d[]{
      new Translation2d(0.315,0.265),new Translation2d(-0.310,0.265),new Translation2d(-0.315,-0.315),new Translation2d(0.315,-0.315) } ;
    }
  public static class ModuleConstants {
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
    public static final int kDriverControllerPort = 0;
  }
  public static class Swerve {
    public static final double MAX_SPEED_MPS =0.3; 
    public static final double MAX_ANGULAR_SPEED_RAD_PER_S =0.3;
    }
} 
