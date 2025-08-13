// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.Demacia.utils.Motors.BaseMotorConfig.Canbus;
import frc.robot.Constants;
import frc.robot.Constants.OperatorConstants;
import frc.Demacia.utils.Motors.TalonConfig;

public class PizzaMotor extends SubsystemBase {
    public static final TalonConfig TALON_CONFIG = new TalonConfig(Constants.MyFirstSubsystemConstants.DMOTOR_ID, Canbus.Rio, "talon example motor")
          .withBrake(true)
          .withCurrent(20)   						// Current Limit
          .withInvert(true)
          .withMeterMotor(OperatorConstants.DgearRatio, OperatorConstants.wheelDiameter*0.0254) 				// Gear Ratio and Wheel Circumference
          .withVelocities(3, 6, 10) 					// max velocity,acceleration, jerk for profiled motion
          .withPID(1, 0, 0, 0.12, 3.7, 1.2, 0) 			// kp, ki, kd, ks, kv, ka, kg
          .withRampTime(0.3) 						// time from zero to max power in seconds
          .withVolts(6); 						// max volt
  public PizzaMotor() {}

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
