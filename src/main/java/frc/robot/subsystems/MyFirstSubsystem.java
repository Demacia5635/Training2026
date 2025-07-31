package frc.robot.subsystems;

import com.ctre.phoenix6.hardware.TalonFX;

import edu.wpi.first.util.sendable.SendableBuilder;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class MyFirstSubsystem extends SubsystemBase {
    // Define the motor 
    TalonFX motor;
    TalonFX motor2;
    double v = 0.0; 
    double angle = 0;
    double length = 0;

    // Constructor
    public MyFirstSubsystem() {
        super();
        motor = new TalonFX(Constants.MyFirstSubsystemConstants.MOTOR_ID, Constants.MyFirstSubsystemConstants.MOTOR_CAN);
        motor2 = new TalonFX(Constants.MyFirstSubsystemConstants.MOTOR_ID2, Constants.MyFirstSubsystemConstants.MOTOR_CAN2);
        SmartDashboard.putData("Subsystem1", this);
    }

    // Method to set the motor speed
    public void setPower(double power, double power2) {
        motor.set(power);
        motor2.set(power2);
    }
    // Method to stop the motor
    public void stop() {
        setPower(0,0);
    } 
    
    @Override
    public void initSendable(SendableBuilder builder) {
        super.initSendable(builder);
        builder.addDoubleProperty("CurrentAngle", this::getPosition, null);
        builder.addDoubleProperty("WantedAngle", this::getAngle, this::setAngle);
        builder.addDoubleProperty("DriveMotorDistance", this::getMeter, null);
        builder.addDoubleProperty("WantedLength", this::getLength,this::setLength);
        builder.addDoubleProperty("DriveMotorVelocity", this::getDriveMotorVelocity, null);
        builder.addDoubleProperty("SteerMotorVelocity", this::getSteerMotorVelocity, null);
    }
    public double getDriveMotorVelocity(){
        return motor2.get()*12.8;
    }
    public double getSteerMotorVelocity(){
        return motor.get()*12.8;
    }
    public double getMeter(){
        return motor.getPosition().getValueAsDouble()/8.4*0.1016*Math.PI;
    }
    public double getLength(){
        return length;
    }
    public void setLength(double length){
        this.length=length;
    }
    public double getPosition(){
        return motor.getPosition().getValueAsDouble()/12.8*360%360;
    }
    public double getAngle(){
        return angle;
    }
    public void setAngle(double angle){
        this.angle = angle;
    }
    @Override
    public void periodic() {
        SmartDashboard.putData(this);
        
    }
}   