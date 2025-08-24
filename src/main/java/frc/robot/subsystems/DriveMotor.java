// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.controller.SimpleMotorFeedforward;
import edu.wpi.first.util.sendable.SendableBuilder;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Constants;

/** Add your docs here. */
public class DriveMotor extends Motor{

    private final PIDController pidController = new PIDController(Constants.ModuleConstants.DRIVE_KP, Constants.ModuleConstants.DRIVE_KI, Constants.ModuleConstants.DRIVE_KD);
    private final SimpleMotorFeedforward feedforward = new SimpleMotorFeedforward(Constants.ModuleConstants.DRIVE_KS, Constants.ModuleConstants.DRIVE_KV, 0);

    public DriveMotor(int MotorID) {
        super(MotorID);
        SmartDashboard.putData("drive motor sub", this);
        
    }

    @Override
    public double getPosition() {
        return motor.getPosition().getValueAsDouble() * (2 * Math.PI * Constants.ModuleConstants.WHEEL_RADUIS) / Constants.ModuleConstants.DRIVE_GEAR_RATIO;
    }

    public void setVelocity(double targetVelocity) {
        double currentVelocity = getVelocity();
        double power = feedforward.calculateWithVelocities(currentVelocity, targetVelocity);
        power += pidController.calculate(currentVelocity, targetVelocity);
        setPower(power);
    }

    @Override
    public void initSendable(SendableBuilder builder) {
        super.initSendable(builder);
        builder.addDoubleProperty("Position drive", this::getPosition, null);
        builder.addDoubleProperty("Velocity drive", this::getVelocity, null);
    }
}