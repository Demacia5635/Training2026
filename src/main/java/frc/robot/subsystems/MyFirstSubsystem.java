package frc.robot.subsystems;

import com.ctre.phoenix6.hardware.CANcoder;
import com.ctre.phoenix6.hardware.TalonFX;
import edu.wpi.first.wpilibj.Preferences;

import edu.wpi.first.util.sendable.Sendable;
import edu.wpi.first.util.sendable.SendableBuilder;
import edu.wpi.first.wpilibj.motorcontrol.Talon;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;


public class MyFirstSubsystem extends SubsystemBase {
    // Define the motor 
    TalonFX motor;
    TalonFX motor2Talon;
     private CANcoder cancoder;

    private double steerOffset = 0.0;
    // Constructor
    public MyFirstSubsystem() {
        super();
        motor = new TalonFX(Constants.MyFirstSubsystemConstants.MOTOR_ID, Constants.MyFirstSubsystemConstants.MOTOR_CAN);
        motor2Talon = new TalonFX(Constants.MyFirstSubsystemConstants.MOTOR2Talon_ID, Constants.MyFirstSubsystemConstants.MOTOR_CAN);
        cancoder = new CANcoder(Constants.MyFirstSubsystemConstants.CANCODER_ID);
        steerOffset = Preferences.getDouble("SteerOffset", 0.0); // טוען את הכיול מהזיכרון

    }

    // Method to set the motor speed
    public void setPower(double power) {
        motor.set(power);
    }
    public void setPower2(double power) {
        motor2Talon.set(power);
    }
    public void calibrateSteerAngle() {
        steerOffset = getAbsoluteAngleDegrees(); // קובע את הזווית הנוכחית כאפס
        Preferences.setDouble("SteerOffset", steerOffset); // שומר בזיכרון
    }
    
    // Method to stop the motor
    public void stop() {
        setPower(0);
        setPower2(0);
    }
    public void stop2() {
        setPower(0);
    }
    public double getAbsoluteAngleDegrees() {
        return cancoder.getAbsolutePosition().getValueAsDouble() * 360.0;
    }
    
    public void calibrateSteerAngle() {
        steerOffset = getAbsoluteAngleDegrees(); // קובע את הזווית הנוכחית כאפס
    }
    
    public double getSteerAngleRelativeToZero() {
        return (getAbsoluteAngleDegrees() - steerOffset + 360) % 360;
    }
    
    @Override
    public void initSendable(SendableBuilder builder)
    {
        builder.addDoubleProperty("angle",this::spinToAngle,null);
        builder.addDoubleProperty("meter",this::moveToMeter,null);


    }
    @Override
public void periodic() {
    SmartDashboard.putData("ModuleSubsystem", this);

    SmartDashboard.putNumber("Absolute Angle (Cancoder)", getAbsoluteAngleDegrees());
    SmartDashboard.putNumber("Relative Angle", getSteerAngleRelativeToZero());

    SmartDashboard.putNumber("Drive Position (m)", moveToMeter());
    SmartDashboard.putNumber("Steer Angle (deg)", spinToAngle());

    SmartDashboard.putNumber("Drive Velocity (m/s)", getDriveVelocity());
    SmartDashboard.putNumber("Steer Velocity (native units)", getSteerVelocity());
}

    public double spinToAngle(){
        return motor.getPosition().getValueAsDouble()/12.8*360%360;
    }
    
    public double moveToMeter(){
        return motor2Talon.getPosition().getValueAsDouble()/8.4*0.1016*Math.PI;

    }
    public double getSteerVelocity() {
        return motor.getVelocity().getValueAsDouble();
    }
    
    public double getDriveVelocity() {
        return motor2Talon.getVelocity().getValueAsDouble();
    }
    private double kP_steer = 0.01; // אפשר לכוון מאוחר יותר

public void setSteerAngle(double desiredAngle) {
    double currentAngle = getSteerAngleRelativeToZero();
    double error = (desiredAngle - currentAngle + 540) % 360 - 180; // טווח שגיאה -180 עד 180
    double steerPower = error * kP_steer;

    // הגבלת מתח למנוע steer
    steerPower = Math.max(Math.min(steerPower, 0.5), -0.5);
    setPower(steerPower); // steer = motor
}

    
   }

