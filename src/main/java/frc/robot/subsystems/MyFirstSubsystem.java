package frc.robot.subsystems;

import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.hardware.TalonFX;

import edu.wpi.first.math.Num;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.util.sendable.SendableBuilder;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class MyFirstSubsystem extends SubsystemBase {
    // Define the motor 
    TalonFX motorDrive;
    TalonFX motorSteer;
    double v = 0.0; 
    double angle = 0;
    double length;
    double NumberOfWheelCyclesIn1Sec=8.14;
    double diameterWheel = 0.1016;
    double AllDegreesInCircle=360;

    // Constructor
    public MyFirstSubsystem() {
        super();
        motorDrive = new TalonFX(Constants.MyFirstSubsystemConstants.MOTOR_DRIVE_ID, Constants.MyFirstSubsystemConstants.MOTOR_CAN);
        motorSteer = new TalonFX(Constants.MyFirstSubsystemConstants.MOTOR_STEER_ID2, Constants.MyFirstSubsystemConstants.MOTOR_CAN2);
        motorDrive.getConfigurator().apply(new TalonFXConfiguration());
        motorSteer.getConfigurator().apply(new TalonFXConfiguration());
        SmartDashboard.putData("MyFirstSubsystem",this);
    }

    // Method to set the motor speed
    public void setPower(double powerSteer, double powerDrive) {
        motorDrive.set(powerDrive);
        motorSteer.set(powerSteer);
    }
    // Method to stop the motor
    public void stop() {
        setPower(0,0);
    } 
    
    @Override
    public void initSendable(SendableBuilder builder) {
        super.initSendable(builder);
        builder.addDoubleProperty("CurrentAngle", this::getMotorAngle, null);
        builder.addDoubleProperty("WantedAngle", this::getWantedAngle, this::setWantedAngle);
        builder.addDoubleProperty("DriveMotorDistance", this::getMeter, null);
        builder.addDoubleProperty("WantedLength", this::getLength,this::setLength);
        builder.addDoubleProperty("Length", null ,this::setLength);
        builder.addDoubleProperty("DriveMotorVelocity", this::getDriveMotorVelocity, this::setDriveMotorVelocity);
        builder.addDoubleProperty("SteerMotorVelocity", this::getSteerMotorVelocity, null);
    }
    public void setDriveMotorVelocity(double speed){
        this.v=speed;
    }
    public double getDriveMotorVelocity(){
        return motorDrive.getVelocity().getValueAsDouble()*diameterWheel*Math.PI;
    }
    public double getSteerMotorVelocity(){
        return motorSteer.getVelocity().getValueAsDouble()*diameterWheel*Math.PI;
    }
    public double getMeter(){
        return (motorDrive.getPosition().getValueAsDouble()/NumberOfWheelCyclesIn1Sec)*diameterWheel*Math.PI;
    }
    public double getLength(){
        return length;
    }
    public void setLength(double length){
        this.length=length;
    }
    public double getMotorAngle(){
        return motorSteer.getPosition().getValueAsDouble()/NumberOfWheelCyclesIn1Sec*AllDegreesInCircle%AllDegreesInCircle;
    }
    public double getWantedAngle(){
        return angle;
    }
    public void setWantedAngle(double angle){
        this.angle = angle;
    }
    @Override
    public void periodic() {
            
    }
}   