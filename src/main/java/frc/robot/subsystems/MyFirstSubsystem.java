package frc.robot.subsystems;

import static edu.wpi.first.units.Units.Degrees;
import static edu.wpi.first.units.Units.Inches;
import static edu.wpi.first.units.Units.Meters;
import static edu.wpi.first.units.Units.Volts;

import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.hardware.Pigeon2;
import com.ctre.phoenix6.hardware.TalonFX;
import com.revrobotics.spark.SparkLowLevel;
import com.revrobotics.spark.SparkMax;
import com.studica.frc.AHRS;
import com.studica.frc.AHRS.NavXComType;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.units.measure.Distance;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class MyFirstSubsystem extends SubsystemBase {
    // Define the motor 
    TalonFX motor;
    SparkMax spark;
    public static final double gearRatio = 16;
    Pigeon2 pigeon = new Pigeon2(Constants.MyFirstSubsystemConstants.PiegonId);
    AHRS navx = new AHRS(NavXComType.kMXP_SPI);

    // Constructor
    public MyFirstSubsystem() {
        super();
        motor = new TalonFX(Constants.MyFirstSubsystemConstants.MotorId, "CANivore");
        spark = new SparkMax(Constants.MyFirstSubsystemConstants.SparkId, SparkLowLevel.MotorType.kBrushless);
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

    public double getPiegonHeading() {
        return pigeon.getYaw().getValueAsDouble();
    }
    public double getNavxHeading() {
        return navx.getAngle();
    }

    public double getPosition() {
        return motor.getPosition().getValueAsDouble();
    }
    public double getPositionDegrees() {
        Distance.ofBaseUnits(4, Inches).in(Meters);
        return motor.getPosition().getValue().in(Degrees);
    }

    public double getSparkPosition() {
        return spark.getEncoder().getPosition();
    }
    public double getSparkVelocity() {
        return spark.getEncoder().getVelocity();
    }

    public double getVolts() {
        return motor.getMotorVoltage().getValueAsDouble();
    }

    public double getNormalizePosition() {
        return normalizeDegrees(motorRotationToDegrees(getPosition()));
    }

    @Override
    public void periodic() {
        super.periodic();
        SmartDashboard.putNumber("Motor Volt", getVolts());
        SmartDashboard.putNumber("Motor Velocity", motor.getVelocity().getValueAsDouble());
        SmartDashboard.putNumber("Motor Position", motor.getPosition().getValueAsDouble());
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