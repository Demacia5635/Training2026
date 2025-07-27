package frc.robot.subsystems;

import com.ctre.phoenix6.hardware.TalonFX;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class MyFirstSubsystem extends SubsystemBase {
    // Define the motor 
    TalonFX motor;
    double v = 0.0;
    TalonFX motor2;
    double v2 = 0.0; 

    // Constructor
    public MyFirstSubsystem() {
        super();
        motor = new TalonFX(Constants.MyFirstSubsystemConstants.MOTOR_ID, Constants.MyFirstSubsystemConstants.MOTOR_CAN);
        motor2 = new TalonFX(Constants.MyFirstSubsystemConstants.MOTOR_ID2, Constants.MyFirstSubsystemConstants.MOTOR_CAN);
    }

    // Method to set the motor speed
    public void setPower(double power) {
        motor.set(power);
        motor2.set(Math.abs(power-0.2));
    }
    // Method to stop the motor
    public void stop() {
      setPower(0);

    }
}   