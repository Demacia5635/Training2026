// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.ctre.phoenix6.CANBus;
import frc.robot.utils.SparkConfig;
import frc.robot.utils.TalonConfig;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
  public static class Example {
    public static final TalonConfig TALON_CONFIG = new TalonConfig(7,new CANBus("rio"), "talon example motor")
          .withBrake(true)
          .withCurrent(20,20,0)
          .withInvert(true)
          .withMeterMotor(4*0.0254)
          .withMotionMagic(3, 6, 10)
          .withMotorRatio(12.7)
          .withPID(1, 0, 0, 0.12, 3.7, 1.2, 0)
          .withRampTime(0.3)
          .withVolts(6, 0);
    public static final SparkConfig SPARKMOTOR_CONFIG = new SparkConfig(8, "spark example motor")
        .withBrake(true)
        .withCurrent(20)
        .withInvert(false)
        .withMotorRatio(8.4)
        .withPID(2,   0.2,   0, 0.1)
        .withRadiansMotor()
        .withVelocity(20, 0, 30)
        .withRampTime(0.2)
        .withVolts(8, 0);
  }

  public static class OperatorConstants {
    public static final int kDriverControllerPort = 0;
  }
}
