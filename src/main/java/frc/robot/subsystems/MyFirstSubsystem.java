package frc.robot.subsystems;

//import org.opencv.objdetect.Board;

import com.ctre.phoenix6.hardware.TalonFX;

import edu.wpi.first.util.sendable.SendableBuilder;
//import edu.wpi.first.wpilibj.motorcontrol.Talon;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
//import edu.wpi.first.wpilibj2.command.WaitUntilCommand;
import frc.robot.Constants;

public class MyFirstSubsystem extends SubsystemBase {
    // Define the motor 
    TalonFX drive;
    double v = 0.0;
    TalonFX steer;
    double v2 = 0.0;

    // Constructor
    public MyFirstSubsystem() {
        super();
        drive = new TalonFX(Constants.MyFirstSubsystemConstants.MOTOR_ID, Constants.MyFirstSubsystemConstants.MOTOR_CAN);
        steer = new TalonFX(Constants.MyFirstSubsystemConstants.MOTOR_ID2, Constants.MyFirstSubsystemConstants.MOTOR_CAN);
    }

    // Method to set the motor speed
    public void SteersetPower(double power) {
        steer.set(power);
    }
    public void drivesetPower(double power) {
        drive.set(power);
    }
    // Method to stop the motor
    public void stop() {
      SteersetPower(0);
      drivesetPower(0);
    }
    public double BoardValue() { //in degrees
        return 360*drive.getPosition().getValueAsDouble()/12.8;
    }
    public double BoardValue2() { //in degrees
        return 360*steer.getPosition().getValueAsDouble()/12.8;
    }
    public double BoardValueRad() { //in radians
        return (2*Math.PI)*drive.getPosition().getValueAsDouble()/12.8;
    }
    public double BoardValueRad2() { //in radians
        return (2*Math.PI)*steer.getPosition().getValueAsDouble()/12.8;
    }
    // builder.addDoubleProperty("remaining", ()->target-getPosition(),null); // lambda function variable
    @Override
    public void initSendable(SendableBuilder builder) {
        // TODO Auto-generated method stub
        super.initSendable(builder);
        builder.addDoubleProperty("motor1 position in degrees", this::BoardValue, null);
        builder.addDoubleProperty("steer position in degrees", this::BoardValue2, null);
        builder.addDoubleProperty("motor1 position in radians", this::BoardValueRad, null);
        builder.addDoubleProperty("steer position in radians", this::BoardValueRad2, null);

    }
    
    @Override
    public void periodic() {
        SmartDashboard.putData("MyFirstSubsystem", this);
       



    }
}   