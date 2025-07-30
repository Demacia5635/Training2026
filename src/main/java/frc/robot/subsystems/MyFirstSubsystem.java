package frc.robot.subsystems;

import com.ctre.phoenix6.hardware.TalonFX;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class MyFirstSubsystem extends SubsystemBase {
    // Define the motor 
    TalonFX motor;
    double v = 0.0;

    // Constructor
    public MyFirstSubsystem() {
        super();
        motor = new TalonFX(Constants.MyFirstSubsystemConstants.MOTOR_ID, Constants.MyFirstSubsystemConstants.MOTOR_CAN);
    }

    // Method to set the motor speed
    public void setPower(double power) {
        motor.set(power);
    }
    // Method to stop the motor
    public void stop() {
      setPower(0);

    }
}   