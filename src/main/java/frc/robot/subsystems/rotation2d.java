// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;
import com.ctre.phoenix6.hardware.Pigeon2;

import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.wpilibj.SPI;
import frc.robot.Constants;

/** Add your docs here. */
public class rotation2d {
    public Rotation2d rot1;
    public Pigeon2 gyro;
    public rotation2d() {
    Pigeon2 gyro = new  Pigeon2(Constants.ChassisConstans.GYRO_ID, Constants.ChassisConstans.GYRO_CAN_BUS);
    gyro.reset();
    Rotation2d rot1 = Rotation2d.fromDegrees(0);
    }
    public void setRotation2d(double position){
        rot1.setDegrees(position);
    }
}