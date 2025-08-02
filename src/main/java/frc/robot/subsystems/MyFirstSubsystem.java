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

public class MyFirstSubsystem extends SubsystemBase {
    // Define the motor 
    TalonFX steerMotor;
     
    TalonFX driveMotor;
    

    // Constructor
    public MyFirstSubsystem() {
        super();
        steerMotor = new TalonFX(Constants.MyFirstSubsystemConstants.SMOTOR_ID, Constants.MyFirstSubsystemConstants.MOTOR_CAN);
        driveMotor = new TalonFX(Constants.MyFirstSubsystemConstants.DMOTOR_ID, Constants.MyFirstSubsystemConstants.MOTOR_CAN);
    }

    // Method to set the motor speed
    public void setSPower(double power) {
        steerMotor.set(power);
    }
    public void setDPower(double power2) {
        driveMotor.set(power2);
    }
    // Method to stop the motor
    public void stop() {
        setSPower(0);
        setDPower(0);
    }
    public double getSPosition() {
        double SMotorPosition = steerMotor.getPosition().getValueAsDouble();
        return (SMotorPosition/OperatorConstants.SgearRatio)*360;

} 
 
}

