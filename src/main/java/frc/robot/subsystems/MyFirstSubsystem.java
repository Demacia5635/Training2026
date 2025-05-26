package frc.robot.subsystems;

import com.ctre.phoenix6.hardware.Pigeon2;
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
        motor = new TalonFX(Constants.MyFirstSubsystemConstants.MotorId, "CANivore");
        motor.setInverted(Constants.MyFirstSubsystemConstants.MotorInverted);
        SmartDashboard.putData("Subsystem 1", this);
    }

    // Method to set the motor speed
    public void setPower(double power) {
        motor.set(power);
    }
    // Method to stop the motor
    public void stop() {
        setPower(0);
    }

    @Override
    public void initSendable(SendableBuilder builder) {
        super.initSendable(builder);
        builder.addDoubleProperty("power", ()->motor.get(), null);
        builder.addDoubleProperty("v", ()->v, null);
    }

    @Override
    public void periodic() {
        // TODO Auto-generated method stub
        super.periodic();
    }

}   