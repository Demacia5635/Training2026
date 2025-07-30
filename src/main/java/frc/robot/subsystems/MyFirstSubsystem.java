package frc.robot.subsystems;

import com.ctre.phoenix6.hardware.TalonFX;

import edu.wpi.first.util.sendable.SendableBuilder;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class MyFirstSubsystem extends SubsystemBase {
    // Define the motor 
    TalonFX motor;
    double v = 0.0;
    TalonFX motor2;
    double v2 = 0.0;

    // Constructor
    public MyFirstSubsystem() {
        super();
        motor = new TalonFX(Constants.MyFirstSubsystemConstants.MOTOR_ID, Constants.MyFirstSubsystemConstants.MOTOR_CAN);
        motor2 = new TalonFX(Constants.MyFirstSubsystemConstants.MOTOR_ID2, Constants.MyFirstSubsystemConstants.MOTOR_CAN);
    }

    // Method to set the motor speed
    public void setPower(double power) {
        motor.set(power);
    }
    public void setPower2(double power) {
        motor2.set(power);
    }
    // Method to stop the motor
    public void stop() {
      setPower(0);
      setPower2(0);
    }
    public double BoardValue() { //in degrees
        return 360*motor.getPosition().getValueAsDouble()/12.8;
    }
    public double BoardValue2() { //in degrees
        return 360*motor2.getPosition().getValueAsDouble()/12.8;
    }
    public double BoardValueRad() { //in radians
        return (2*Math.PI)*motor.getPosition().getValueAsDouble()/12.8;
    }
    public double BoardValueRad2() { //in radians
        return (2*Math.PI)*motor2.getPosition().getValueAsDouble()/12.8;
    }
    // builder.addDoubleProperty("remaining", ()->target-getPosition(),null); // lambda function variable
    @Override
    public void initSendable(SendableBuilder builder) {
        // TODO Auto-generated method stub
        super.initSendable(builder);
        builder.addDoubleProperty("motor1 position in degrees", this::BoardValue, null);
        builder.addDoubleProperty("motor2 position in degrees", this::BoardValue2, null);
        builder.addDoubleProperty("motor1 position in radians", this::BoardValueRad, null);
        builder.addDoubleProperty("motor2 position in radians", this::BoardValueRad2, null);

    }
    
    @Override
    public void periodic() {
        SmartDashboard.putData("MyFirstSubsystem", this);
       



    }
}   