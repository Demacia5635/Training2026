// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix6.hardware.Pigeon2;
import com.ctre.phoenix6.swerve.SwerveModule;

import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.math.kinematics.Kinematics;
import edu.wpi.first.math.kinematics.SwerveDriveKinematics;
import edu.wpi.first.math.kinematics.SwerveModuleState;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.Constants.ChassisConstants;

public class ChassisModula extends SubsystemBase {
  
    private final Pigeon2 gyro;

    private Modula[] modules = new Modula[] {
        new Modula(ChassisConstants.FRONT_LEFT),
        new Modula(ChassisConstants.FRONT_RIGHT),
        new Modula(ChassisConstants.BACK_LEFT),
        new Modula(ChassisConstants.BACK_RIGHT)
    };

    private SwerveDriveKinematics kinematicsFix = new SwerveDriveKinematics(
                    ChassisConstants.KINEMATICS[0],
                    ChassisConstants.KINEMATICS[1],
                    ChassisConstants.KINEMATICS[2],
                    ChassisConstants.KINEMATICS[3]
    );



    public ChassisModula() {
        gyro = new Pigeon2(ChassisConstants.GYRO_ID, ChassisConstants.GYRO_CAN_BUS);

    }

    public void setVelocities(ChassisSpeeds speeds) {
        speeds = ChassisSpeeds.fromFieldRelativeSpeeds(speeds, new Rotation2d(getGyroAngle()));
        SwerveModuleState[] states = kinematicsFix.toSwerveModuleStates(speeds);
        setModuleStates(states);
    }

    public void setModuleStates(SwerveModuleState[] states) {
        for (int i = 0; i < states.length; i++) {
            modules[i].setState(states[i]);
        }
    }

    private double getGyroAngle() {
        return gyro.getYaw().getValueAsDouble();
    }
}
