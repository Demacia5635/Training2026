package frc.robot.subsystems;

import static edu.wpi.first.units.Units.Degree;

import com.ctre.phoenix6.hardware.TalonFX;

import edu.wpi.first.util.sendable.SendableBuilder;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
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
    public double getVelocity() {
        return motor.getVelocity().getValueAsDouble()/Constants.MyFirstSubsystemConstants.MOTOR_GEAR_RATIO * 0.139; // Convert to RPM
    }

    public double getposition() {
        return (motor.getPosition().getValueAsDouble() / 12.8 * 360) % 360;
    }


    @Override
    public void initSendable(SendableBuilder builder) {
        builder.addDoubleProperty("Degree", this::getposition, null);
        builder.addDoubleProperty("Speed", this::getVelocity, null);
    }

    @Override
    public void periodic() {
        SmartDashboard.putData(this);
    }
    
    
}   