package frc.robot.subsystems;

import com.ctre.phoenix6.hardware.TalonFX;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.util.sendable.SendableBuilder;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class MyFirstSubsystem extends SubsystemBase {
    // Define the motor 
    TalonFX motor; 
    private PIDController turnController;


    // Constructor
    public MyFirstSubsystem() {
        super();
        motor = new TalonFX(Constants.MyFirstSubsystemConstants.steer_Motor_ID, Constants.MyFirstSubsystemConstants.MOTOR_CAN);
        turnController = new PIDController(Constants.MyFirstSubsystemConstants.Kp,Constants.MyFirstSubsystemConstants.Ki, Constants.MyFirstSubsystemConstants.Kd);

    }

    // Method to set the motor speed
    public void setPower(double power) {
        motor.set(power);
        turnController.setTolerance(2.0);
        turnController.setIntegratorRange(-5, 5);

    }
    // Method to stop the motor
    public void stop() {
        setPower(0);
    }
    public double GetPositiony(){
        return ((motor.getPosition().getValueAsDouble()/Constants.MyFirstSubsystemConstants.Deegree_ratio)*360)%360;
    }
    public double GetDegree(){
        return 45.0;
    }
    public void Stop() {
        setPower(0.0);
    }


    @Override
    public void initSendable(SendableBuilder builder){
        builder.addDoubleProperty("degree", this::GetDegree, null);
        builder.addDoubleProperty("CurrentDegree", this::GetPositiony, null);
    }
        public void turnToAngle(double targetAngle) {
        double currentAngle = GetPositiony();
        double turnSpeed = turnController.calculate(currentAngle, targetAngle);
        setPower(turnSpeed);
    }


    
    public void periodic() {
    SmartDashboard.putData("MyFirstSubsystem",this);
    }
}