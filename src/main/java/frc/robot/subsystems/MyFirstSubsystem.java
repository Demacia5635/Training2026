package frc.robot.subsystems;

import com.ctre.phoenix6.hardware.Pigeon2;
import com.ctre.phoenix6.hardware.TalonFX;

import edu.wpi.first.util.sendable.SendableBuilder;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class MyFirstSubsystem extends SubsystemBase {
    // Define the motor 
    TalonFX motorx;
    TalonFX motory;
    double vx = 0.0; 
    double vy = 0.0;

    // Constructor
    public MyFirstSubsystem() {
        super();
        motory = new TalonFX(Constants.MyFirstSubsystemConstants.MotorIdy, "rio");
        motorx = new TalonFX(Constants.MyFirstSubsystemConstants.MotorIdx, "rio");
    }

    // Method to set the motor speed
    public void setPowerx(double power) {
        motorx.set(power);
    }
    public void setPowery(double power) {
        motory.set(power);
    }
    // Method to stop the motor
    public void stop() {
        setPowerx(0);
        setPowery(0);
    }

    @Override
    public void initSendable(SendableBuilder builder) {
        super.initSendable(builder);
        builder.addDoubleProperty("powery", ()->motory.get(), null);
        builder.addDoubleProperty("vy", ()->vy, null);
        builder.addDoubleProperty("powerx", ()->motorx.get(), null);
        builder.addDoubleProperty("vx", ()->vx, null);
    }

    @Override
    public void periodic() {
        // TODO Auto-generated method stub
        super.periodic();
    }

}   