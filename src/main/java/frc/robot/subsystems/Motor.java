// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.hardware.TalonFX;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

/** Add your docs here. */
public class Motor extends SubsystemBase {

    protected final TalonFX motor;

    public Motor(int MotorID){
        super();
        motor = new TalonFX(MotorID, Constants.MotorConstants.MotorCANbus);
        motor.getConfigurator().apply(new TalonFXConfiguration());
        SmartDashboard.putData("motor sub",this);
    }

    public void setPower(double power) {
        motor.set(power);
    }

    public void stop() {
        motor.set(0);
    }

    public double getPosition() {
        return motor.getPosition().getValueAsDouble();
    }

    public double getVelocity(){
        return motor.getVelocity().getValueAsDouble();
    }
} 
