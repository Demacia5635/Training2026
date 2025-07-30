package frc.robot.subsystems;

import com.ctre.phoenix6.hardware.TalonFX;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class MyFirstSubsystem extends SubsystemBase {
    // Define the motor 
    TalonFX motor;
    // define gearRatio
    double gearRatio=12.8;

    // Constructor
    public MyFirstSubsystem() {
        super();
        //    public static final int MOTOR_ID = 10;
        // public static final String MOTOR_CAN = "rio";
        motor = new TalonFX(Constants.MyFirstSubsystemConstants.MOTOR_ID, 
        Constants.MyFirstSubsystemConstants.MOTOR_CAN);
    }

    // Method to set the motor speed
    public void setPower(double power) {
        motor.set(power);
    }
    // Method to stop the motor
    public void stop() {
        setPower(0);
    }
    // get motor position
    public double getPosition() {
        return motor.getPosition().getValueAsDouble()*360/gearRatio;
    }
}   