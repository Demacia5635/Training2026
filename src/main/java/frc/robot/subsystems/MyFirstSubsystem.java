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
        builder.addDoubleProperty("pos", this::getPosition, null);
        builder.addDoubleProperty("angle", this::getAngle, this::setAngle);
        builder.addDoubleProperty("DriveAngle", this::getMeter, null);
        builder.addDoubleProperty("Length", this::getLength,this::setLength);
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