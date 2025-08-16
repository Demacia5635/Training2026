// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.Demacia.utils.Motors.BaseMotorConfig.Canbus;
import frc.Demacia.utils.Motors.MotorInterface;
import frc.robot.Constants;
import frc.robot.Constants.OperatorConstants;
import frc.Demacia.utils.Motors.TalonConfig;
import frc.Demacia.utils.Motors.TalonMotor;

public class PizzaMotor extends SubsystemBase {
    MotorInterface driveMotor; 

  public PizzaMotor() {
    super();
  driveMotor = new TalonMotor(Constants.MotorsConfig.TALON_CONFIG);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
  
}
