// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import static frc.robot.Constants.ModuleConstants.STEER_KD;
import static frc.robot.Constants.ModuleConstants.STEER_KI;
import static frc.robot.Constants.ModuleConstants.STEER_KP;
import static frc.robot.Constants.ModuleConstants.DRIVE_KP;
import static frc.robot.Constants.ModuleConstants.DRIVE_KS;
import static frc.robot.Constants.ModuleConstants.DRIVE_KV;

import com.ctre.phoenix6.hardware.CANcoder;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.controller.SimpleMotorFeedforward;
import edu.wpi.first.math.kinematics.SwerveModuleState;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

/** Add your docs here. */
public class Modula extends SubsystemBase {

    private final DriveMotor driveMotor;
    private final SteerMotor steerMotor;
    private final CANcoder absEncoder;
    private double power;

    SimpleMotorFeedforward driveFF = new SimpleMotorFeedforward(DRIVE_KS, DRIVE_KV, 0);
    PIDController drivePID = new PIDController(DRIVE_KP, 0, 0);
    PIDController steerPID = new PIDController(STEER_KP, STEER_KI, STEER_KD);

    public Modula(int[] moduleID) {
        super();
        driveMotor = new DriveMotor(moduleID[0]);
        steerMotor = new SteerMotor(moduleID[1]);
        absEncoder = new CANcoder(moduleID[2]);
        power = 0;
    }

    public void setDriveVelocity(double targetVelocity) {
        power = driveFF.calculate(targetVelocity);
        power += drivePID.calculate(driveMotor.getVelocity(), targetVelocity);
        driveMotor.setPower(power);
    }

    public void setSteerPower(double power) {
        steerMotor.setPower(power);
    }

    public void stopDrive() {
        driveMotor.stop();
    }

    public void stopSteer() {
        steerMotor.stop();
    }
    
    public double getDrivePosition() {
        return driveMotor.getPosition();
    }

    public double getSteerPosition() {
        return steerMotor.getPosition();
    }

    public double getVelocity() {
        return driveMotor.getVelocity();
    }

    public void calibrateAngle() {
        double offset = absEncoder.getAbsolutePosition().getValueAsDouble() * Constants.ModuleConstants.STEER_GEAR_RATIO * 360;
        setSteerPosition(offset);
    }

    public double getAngle() {
        return absEncoder.getAbsolutePosition().getValueAsDouble() * Constants.ModuleConstants.STEER_GEAR_RATIO * 360;
    }

    public void setSteerPosition(double targetPosition) {
        targetPosition = targetPosition * (180 / Math.PI) * Constants.ModuleConstants.STEER_GEAR_RATIO;
        while (Math.abs(steerMotor.getPosition() - targetPosition) > 0.1) {
            double error = targetPosition - steerMotor.getPosition();
            double steerPower = steerPID.calculate(error);
            setSteerPower(steerPower);
        }
        stopSteer();
    }

    public void setState(SwerveModuleState state) {
        double wantedAngle = state.angle.getRadians();
        double diff = wantedAngle - steerMotor.getPosition();
        double vel = state.speedMetersPerSecond;
        diff = MathUtil.angleModulus(diff);
        if(diff > 0.5 * Math.PI) {
            vel = -vel;
            diff = diff-Math.PI;
        } else if(diff < -0.5 * Math.PI) {
            vel = -vel;
            diff = diff + Math.PI;
        }
        setSteerPosition(steerMotor.getPosition() + diff);
        setDriveVelocity(vel);
    }
}
