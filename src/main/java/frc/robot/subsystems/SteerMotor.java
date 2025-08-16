// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.util.sendable.SendableBuilder;
import frc.robot.Constants;

/** Add your docs here. */
public class SteerMotor extends Motor{

    public SteerMotor(int MotorID) {
        super(MotorID);
    }

    @Override
    public double getPosition() {
        return motor.getPosition().getValueAsDouble() * 360 / Constants.MotorConstants.SteerRatio;
    }

    @Override
    public void initSendable(SendableBuilder builder) {
        super.initSendable(builder);
        builder.addDoubleProperty("Position steer", this::getPosition, null);
        builder.addDoubleProperty("Velocity steer", this::getVelocity, null);
    }
}
