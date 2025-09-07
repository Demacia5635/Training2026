// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

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
    public static final int Drive_MOTOR_ID_Front_left= 1;
    public static final int steer_Motor_ID_Front_left= 2;
    public static final int steer_Motor_ID_Front_right= 5;
    public static final int Drive_Motor_ID_Front_right= 4;
    public static final int steer_Motor_ID_back_left= 8;
    public static final int Drive_Motor_ID_back_left= 7;
    public static final int steer_Motor_ID_back_right=11 ;
    public static final int Drive_Motor_ID_back_right= 10;
    public static final int Gyro_Id= 14;
    public static final String MOTOR_CAN = "canivore";
    public static final double Drive_ratio = 12.8;
    public static final String Gyro_Can = "rio";
    public static final double Deegree_ratio = 6.14;
    public static final double Drive_Radios_In_Meter = 5;
    public static final double Kp = 0.005;
    public static final double Ki = 0.00;
    public static final double Kd = 0.0002;
    public static final double kp2 = 0.015;
    public static final double ki2 = 0.0000;
    public static final double kd2 = 0.001;
    public static final int DriveID = 7;
    public static double kS = 0.0;
    public static double kV = 0.00938;

  }

  public static class OperatorConstants {
    public static final int DriverControllerPort = 0;
  }

public static  double MAX_ROTATIONAL_VELOCITY;
public static  double MAX_DRIVE_VELOCITY_Y;
public static  double MAX_DRIVE_VELOCITY_X;
}
