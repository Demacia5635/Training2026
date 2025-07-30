package frc.robot.subsystems;

import com.ctre.phoenix6.StatusSignal;
import com.ctre.phoenix6.hardware.TalonFX;
import com.fasterxml.jackson.databind.deser.ValueInstantiator.Gettable;

import edu.wpi.first.units.measure.Angle;
import edu.wpi.first.util.sendable.Sendable;
import edu.wpi.first.util.sendable.SendableBuilder;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.Constants.OperatorConstants;
import frc.robot.commands.GoToAngle;

public class MyFirstSubsystem extends SubsystemBase {
    // Define the motor 
    TalonFX motor;
     
    TalonFX motor2;
    

    // Constructor
    public MyFirstSubsystem() {
        super();
        motor = new TalonFX(Constants.MyFirstSubsystemConstants.MOTOR_ID, Constants.MyFirstSubsystemConstants.MOTOR_CAN);
        motor2 = new TalonFX(Constants.MyFirstSubsystemConstants.MOTOR_ID2, Constants.MyFirstSubsystemConstants.MOTOR_CAN);
        SmartDashboard.putData("Subsystem1", this);
        SmartDashboard.putData("GoToAngle", new GoToAngle(this));
    }

    // Method to set the motor speed
    public void setPower(double power) {
        motor.set(power);
    }
    public void setPower2(double power2) {
        motor2.set(power2);
    }
    // Method to stop the motor
    public void stop() {
        setPower(0);
        setPower2(0);
    }
    public double getPosition2() {
     double motorposition = motor2.getPosition().getValueAsDouble();
     return (motorposition/OperatorConstants .gearRatio)*360;
    }

    @Override
    public void initSendable(SendableBuilder builder) {
        super.initSendable(builder);
        builder.addDoubleProperty("angle", this::getPosition2, null);
}  
} 
