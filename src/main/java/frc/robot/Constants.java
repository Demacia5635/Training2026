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
    public static final String MOTOR_CAN = "rio";
  }
  public static class DriverConstants{
    public static final int DriverID = 0;
  }
  public static class Encoders {
    public static final int LB_ENCODER = 9;
    public static final int LF_ENCODER = 3;
    public static final int RB_ENCODER = 12;
    public static final int RF_ENCODER = 6;
  }
public static class MotorsConfig {
  public static final TalonConfig LBD_CONFIG = new TalonConfig(Constants.ModuleConstants.LBD_ID, Canbus.canivore, "LBDRIVE")
        //  .withBrake(true)
          .withCurrent(20)   						// Current Limit
          .withInvert(true)
          .withMeterMotor(OperatorConstants.DgearRatio, Constants.ModuleConstants.WHEEL_CIRCUMFERENCE) 				// Gear Ratio and Wheel Circumference
          .withPID(0.001, 0, 0, -0.1, 1.3, 0, 0) 			// kp, ki, kd, ks, kv, ka, kg
          .withRampTime(0.3) 						// time from zero to max power in seconds
          .withVolts(6); 						// max volt
  public static final TalonConfig LBS_CONFIG = new TalonConfig(Constants.ModuleConstants.LBS_ID, Canbus.canivore, "LBSTEER")
         // .withBrake(true)
          .withCurrent(20)   						// Current Limit
          .withInvert(true)
          .withMeterMotor(OperatorConstants.SgearRatio, Constants.ModuleConstants.WHEEL_CIRCUMFERENCE) 				// Gear Ratio and Wheel Circumference
          .withPID(0.001, 0, 0, -0.1, 1.3, 0, 0) 			// kp, ki, kd, ks, kv, ka, kg
          .withRampTime(0.3) 						// time from zero to max power in seconds
          .withVolts(6); 						// max volt

          public static final TalonConfig LFD_CONFIG = new TalonConfig(Constants.ModuleConstants.LFD_ID, Canbus.canivore, "LFDRIVE")
         // .withBrake(true)
          .withCurrent(20)   						// Current Limit
          .withInvert(true)
          .withMeterMotor(OperatorConstants.DgearRatio, Constants.ModuleConstants.WHEEL_CIRCUMFERENCE) 				// Gear Ratio and Wheel Circumference
          .withPID(0.001, 0, 0, -0.1, 1.3, 0, 0) 			// kp, ki, kd, ks, kv, ka, kg
          .withRampTime(0.3) 						// time from zero to max power in seconds
          .withVolts(6); 						// max volt
  public static final TalonConfig LFS_CONFIG = new TalonConfig(Constants.ModuleConstants.LFS_ID, Canbus.canivore, "LFSTEER")
         // .withBrake(true)
          .withCurrent(20)   						// Current Limit
          .withInvert(true)
          .withMeterMotor(OperatorConstants.SgearRatio, Constants.ModuleConstants.WHEEL_CIRCUMFERENCE) 				// Gear Ratio and Wheel Circumference
          .withPID(0.001, 0, 0, -0.1, 1.3, 0, 0) 			// kp, ki, kd, ks, kv, ka, kg
          .withRampTime(0.3) 						// time from zero to max power in seconds
          .withVolts(6); 						// max volt

          public static final TalonConfig RBD_CONFIG = new TalonConfig(Constants.ModuleConstants.RBD_ID, Canbus.canivore, "RBDRIVE")
          //.withBrake(true)
          .withCurrent(20)   						// Current Limit
          .withInvert(true)
          .withMeterMotor(OperatorConstants.DgearRatio, Constants.ModuleConstants.WHEEL_CIRCUMFERENCE) 				// Gear Ratio and Wheel Circumference
          .withPID(0.001, 0, 0, -0.1, 1.3, 0, 0) 			// kp, ki, kd, ks, kv, ka, kg
          .withRampTime(0.3) 						// time from zero to max power in seconds
          .withVolts(6); 						// max volt
  public static final TalonConfig RBS_CONFIG = new TalonConfig(Constants.ModuleConstants.RBS_ID, Canbus.canivore, "RBSTEER")
          //.withBrake(true)
          .withCurrent(20)   						// Current Limit
          .withInvert(true)
          .withMeterMotor(OperatorConstants.SgearRatio, Constants.ModuleConstants.WHEEL_CIRCUMFERENCE) 				// Gear Ratio and Wheel Circumference
          .withPID(0.001, 0, 0, -0.1, 1.3, 0, 0) 			// kp, ki, kd, ks, kv, ka, kg
          .withRampTime(0.3) 						// time from zero to max power in seconds
          .withVolts(6); 						// max volt

          public static final TalonConfig RFD_CONFIG = new TalonConfig(Constants.ModuleConstants.RFD_ID, Canbus.canivore, "RFDRIVE")
          //.withBrake(true)
          .withCurrent(20)   						// Current Limit
          .withInvert(true)
          .withMeterMotor(OperatorConstants.DgearRatio, Constants.ModuleConstants.WHEEL_CIRCUMFERENCE) 				// Gear Ratio and Wheel Circumference
          .withPID(0.001, 0, 0, -0.1, 1.3, 0, 0) 			// kp, ki, kd, ks, kv, ka, kg
          .withRampTime(0.3) 						// time from zero to max power in seconds
          .withVolts(6); 						// max volt
  public static final TalonConfig RFS_CONFIG = new TalonConfig(Constants.ModuleConstants.RFS_ID, Canbus.canivore, "RFSTEER")
          //.withBrake(true)
          .withCurrent(20)   						// Current Limit
          .withInvert(true)
          .withMeterMotor(OperatorConstants.SgearRatio,Constants.ModuleConstants.WHEEL_CIRCUMFERENCE) 				// Gear Ratio and Wheel Circumference
          .withPID(0.001, 0, 0, -0.1, 1.3, 0, 0) 			// kp, ki, kd, ks, kv, ka, kg
          .withRampTime(0.3) 						// time from zero to max power in seconds
          .withVolts(6); 						// max volt
}
  public static class ModuleConstants {
    public static final double absEncoderLBcali = 0.18457*Math.PI*2*(-1);
    public static final double absEncoderLFcali = (-0.43335)*Math.PI*2*(-1);
    public static final double absEncoderRBcali = (0.32666)*Math.PI*2*(-1);
    public static final double absEncoderRFcali =(-0.251465) *Math.PI*2*(-1);
    public static final double[] offsetArr = {0,absEncoderLFcali,absEncoderRFcali, absEncoderLBcali,  absEncoderRBcali };
    public static final int LBS_ID = 8;
    public static final int LBD_ID = 7;
    public static final int LFS_ID = 2;
    public static final int LFD_ID = 1;
    public static final int RBS_ID = 11;
    public static final int RBD_ID = 10;
    public static final int RFS_ID = 5;
    public static final int RFD_ID = 4;
    public static final double WHEEL_CIRCUMFERENCE = Math.PI * OperatorConstants.wheelDiameter ;

    public static final double STEER_VELOCITY_P = 2;
    public static final double ABS_ENCODER_OFFSET = 10;
  }


  

  public static class OperatorConstants {
    public static final int DriverControllerPort = 0;
    public static final double SgearRatio = 12.8;
    public static final double DgearRatio = 8.14 ;
    public static final double maxSpeedMpS = 4.80;
    public static final double wheelDiameter = 0.1016;
  }
}
