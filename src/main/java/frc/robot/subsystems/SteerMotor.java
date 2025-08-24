// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.controller.SimpleMotorFeedforward;
import edu.wpi.first.util.sendable.SendableBuilder;
import frc.robot.Constants;

/** Add your docs here. */
public class SteerMotor extends Motor{

    private double targetPosition = 0;
    private SimpleMotorFeedforward feedforward = new SimpleMotorFeedforward(Constants.ModuleConstants.STEER_KS, Constants.ModuleConstants.STEER_KV, 0);
    private PIDController pidController = new PIDController(Constants.ModuleConstants.STEER_KP, Constants.ModuleConstants.STEER_KI, Constants.ModuleConstants.STEER_KD);


    public SteerMotor(int MotorID) {
        super(MotorID);
    }

    @Override
    public double getPosition() {
        return motor.getPosition().getValueAsDouble() * 360 / Constants.ModuleConstants.STEER_GEAR_RATIO;
    }

    public void setTargetAngle(double angle) {
        targetPosition = angle * Constants.ModuleConstants.STEER_GEAR_RATIO / 360;
    }

    public double getTargetAngle() {
        return targetPosition * 360 / Constants.ModuleConstants.STEER_GEAR_RATIO;
    }

    public void setVelocity(double targetVelocity){
        double currentVelocity = getVelocity();
        double power = feedforward.calculateWithVelocities(currentVelocity, targetVelocity);
        power += pidController.calculate(currentVelocity, targetVelocity);
        setPower(power);
    }

    @Override
    public void initSendable(SendableBuilder builder) {
        super.initSendable(builder);
        builder.addDoubleProperty("Position steer", this::getPosition, null);
        builder.addDoubleProperty("Velocity steer", this::getVelocity, null);
        builder.addDoubleProperty("Target angle", this::getTargetAngle, this::setTargetAngle);
    }
}
