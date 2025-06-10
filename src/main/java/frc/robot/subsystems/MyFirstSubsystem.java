package frc.robot.subsystems;

import com.ctre.phoenix6.hardware.TalonFX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class MyFirstSubsystem extends SubsystemBase {
    // Define the motor 
    TalonFX motor;
    public static final double gearRatio = 16;

    // Constructor
    public MyFirstSubsystem() {
        super();
        motor = new TalonFX(Constants.MyFirstSubsystemConstants.MotorId, "CANivore");
        motor.setInverted(Constants.MyFirstSubsystemConstants.MotorInverted);
    }

    // Method to set the motor speed
    public void setPower(double power) {
        motor.set(power);
    }
    // Method to stop the motor
    public void stop() {
        setPower(0);
    }

    public double getPosition() {
        return motor.getPosition().getValueAsDouble();
    }

    public double getNormalizePosition() {
        return normalizeDegrees(motorRotationToDegrees(getPosition()));
    }

    @Override
    public void periodic() {
        super.periodic();
    }

    public static double motorRotationToDegrees(double rotations) {
        return rotations/gearRatio * 360.0;
    }
    public static double degreesToMotorRotation(double degrees) {
        return degrees / 360.0 * gearRatio;
    }

    public static double normalizeDegrees(double degrees) {
        double normalized = degrees % 360;
        return normalized < 0 ? normalized + 360 : normalized;

    }

}   