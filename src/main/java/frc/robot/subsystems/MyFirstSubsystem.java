package frc.robot.subsystems;

import com.ctre.phoenix6.hardware.TalonFX;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class MyFirstSubsystem extends SubsystemBase {
    // Define the motor 
    private TalonFX driveMotor;
    private TalonFX steerMotor;
    private double v = 0.0; 

    // Constructor
    public MyFirstSubsystem() {
        super();
        driveMotor = new TalonFX(Constants.DRIVE_MOTOR_ID, Constants.MyFirstSubsystemConstants.MOTOR_CAN);
        steerMotor = new TalonFX(Constants.STEER_MOTOR_ID, Constants.MyFirstSubsystemConstants.MOTOR_CAN);

    }

    // Method to set the motor speed
    public void setPowerDriveMotor(double power) {
        driveMotor.set(power);
    }
    public void setPowerSteerMotor(double power) {
        steerMotor.set(power);
    }
    // Method to stop the motor
    public void stop() {
        driveMotor.set(0);
        steerMotor.set(0);

    }
}