// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

/** Add your docs here. */
public class Modula extends SubsystemBase {
    private Motor driveMotor;
    private Motor steerMotor;

    public Modula() {
        super();
        driveMotor = new Motor(Constants.MotorConstants.DriveMotorID);
        steerMotor = new Motor(Constants.MotorConstants.SteerMotorID);
    }

    public void setPowerToDrive(double power) {
        driveMotor.setPower(power);
    }

    public void setPowerToSteer(double power) {
        steerMotor.setPower(power);
    }

    public void stopToSteer() {
        steerMotor.stop();
    }

    public void stopToDrive() {
        driveMotor.stop();
    }
}
