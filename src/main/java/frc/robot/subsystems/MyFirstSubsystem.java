package frc.robot.subsystems;

import static edu.wpi.first.units.Units.Degrees;

import com.ctre.phoenix6.hardware.TalonFX;

import edu.wpi.first.util.sendable.SendableBuilder;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class MyFirstSubsystem extends SubsystemBase {
    // Define the motor 
    TalonFX motor;
    double v = 0.0; 

    // Constructor
    public MyFirstSubsystem() {
        super();
        motor = new TalonFX(Constants.MyFirstSubsystemConstants.MOTOR_IDY, Constants.MyFirstSubsystemConstants.MOTOR_CAN);
    }

    // Method to set the motor speed
    public void setPower(double power) {
        motor.set(power);
    }
    // Method to stop the motor
    public void stop() {
        setPower(0);
    }
    public double GetPositiony(){
        return motor.getPosition().getValueAsDouble();
    }
    public double GetVelocityy(){
        return motor.getVelocity().getValueAsDouble();
    }


    @Override
    public void initSendable(SendableBuilder builder){
        builder.addDoubleProperty("motor y pos(m)", this::GetPositiony, null);
        builder.addDoubleProperty("motor y v(m/s)", this::GetVelocityy, null);
    }

    @Override
    public void periodic() {
    //   SmartDashboard.putNumber("motor x pos(m)", Double.parseDouble(motorx.getPosition().toString()));
    //   SmartDashboard.putNumber("motor y pos(m)", Double.parseDouble(motory.getPosition().toString()));
    //   SmartDashboard.putNumber("motor x v(m/s)", Double.parseDouble(motorx.getVelocity().toString()));
    //   SmartDashboard.putNumber("motor y v(m/s)", Double.parseDouble(motory.getVelocity().toString()));
    SmartDashboard.putData("MyFirstSubsystem",this);
    }
}   