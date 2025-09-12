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

    public static final double Kp = 0.005;
    public static final double Ki = 0.00;
    public static final double Kd = 0.0002;
    public static final double kp2 = 0.015;
    public static final double ki2 = 0.0000;
    public static final double kd2 = 0.001;
    public static double kS = 0.0;
    public static double kV = 0.00938;
    public static final double Degree_ratio = 6.14;
    public static final int MotorId = 7;
    public static final int Drive_MOTOR_ID_Front_left= 1;
    public static final int steer_Motor_ID_Front_left= 2;
    public static final int steer_Motor_ID_Front_right= 5;
    public static final int Drive_Motor_ID_Front_right= 4;
    public static final int steer_Motor_ID_back_left= 8;
    public static final int Drive_Motor_ID_back_left= 7;
    public static final int steer_Motor_ID_back_right=11 ;
    public static final int Drive_Motor_ID_back_right= 10;
    public static final int Cancoder_ID_Front_Left= 0;
    public static final int Cancoder_ID_Front_Right= 0;
    public static final int Cancoder_ID_Back_Left= 0;
    public static final int Cancoder_ID_Back_Right= 0;
    public static final int Gyro_Id= 14;
    public static final String MOTOR_CAN = "canivore";
    public static final double Drive_ratio = 12.8;
    public static final String Gyro_Can = "rio";
    public static final double Drive_Radios_In_Meter = 5;
    public static final int DriveID = 7;
    public static final boolean MotorInverted = true;

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

