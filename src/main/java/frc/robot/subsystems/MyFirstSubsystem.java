package frc.robot.subsystems;

import com.ctre.phoenix6.hardware.TalonFX;

import edu.wpi.first.wpilibj.motorcontrol.Talon;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class MyFirstSubsystem extends SubsystemBase {
    // Define the motor 
    TalonFX motor;
    TalonFX motor2Talon;

    // Constructor
    public MyFirstSubsystem() {
        super();
        motor = new TalonFX(Constants.MyFirstSubsystemConstants.MOTOR_ID, Constants.MyFirstSubsystemConstants.MOTOR_CAN);
        motor2Talon = new TalonFX(Constants.MyFirstSubsystemConstants.MOTOR2Talon_ID, Constants.MyFirstSubsystemConstants.MOTOR_CAN);

    }

    // Method to set the motor speed
    public void setPower(double power) {
        motor.set(power);
    }
    public void setPower2(double power) {
        motor2Talon.set(power);
    }
    // Method to stop the motor
    public void stop() {
        setPower(0);
        setPower2(0);
    }
    public void stop2() {
        setPower(0);
    }
}   


