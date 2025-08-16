// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.util.sendable.SendableBuilder;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Constants;

/** Add your docs here. */
public class DriveMotor extends Motor{

    public DriveMotor(int MotorID) {
        super(MotorID);
        SmartDashboard.putData("drive motor sub", this);
        
    }

    @Override
    public double getPosition() {
        return motor.getPosition().getValueAsDouble() * (2 * Math.PI * Constants.MotorConstants.WheelRadius) / Constants.MotorConstants.DriveRatio;
    }

    @Override
    public void initSendable(SendableBuilder builder) {
        super.initSendable(builder);
        builder.addDoubleProperty("Position drive", this::getPosition, null);
        builder.addDoubleProperty("Velocity drive", this::getVelocity, null);
    }
}
