package frc.robot.subsystems;

import com.ctre.phoenix6.StatusSignal;
import com.ctre.phoenix6.hardware.TalonFX;
import com.fasterxml.jackson.databind.deser.ValueInstantiator.Gettable;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.math.controller.PIDController;
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
    public MyFirstSubsystem () {
        super();
        steerMotor = new TalonFX(Constants.MyFirstSubsystemConstants.SMOTOR_ID, Constants.MyFirstSubsystemConstants.MOTOR_CAN);
        driveMotor = new TalonFX(Constants.MyFirstSubsystemConstants.DMOTOR_ID, Constants.MyFirstSubsystemConstants.MOTOR_CAN);
        SmartDashboard.putData("Subsystem 1", this);
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
public double getDPosition() {
    double DMotorPosition = driveMotor.getPosition().getValueAsDouble();
    return (DMotorPosition/OperatorConstants.DgearRatio)*360;
}
 @Override
 public void initSendable(SendableBuilder builder){
    builder.addDoubleProperty("drive angle", () -> getDPosition(), null);
    builder.addDoubleProperty("steering angle", () -> getSPosition(), null);
 }
 public void drive(double distanceInCm,  double kp, double ki, double kd){
    double power;
    double sumError = 0;
    double lastError = 0;
    double wheelCircumference = Math.PI * OperatorConstants.wheelDiameter;
    double rotations = distanceInCm / wheelCircumference; 
    double targetPosition = rotations*360+getDPosition();
    double currnt = getDPosition();
    double error = targetPosition - currnt;;
     while (Math.abs(error) > 10){
     currnt = getDPosition();
     error = targetPosition - currnt;
     sumError += error;
    double p = kp * error;
    double i = ki * sumError;
    double d = kd * (lastError - error);
    power = MathUtil.clamp(p+i+d,-0.5, 0.5);
    SmartDashboard.putNumber("drive Error", error);
    SmartDashboard.putNumber("drive angle", currnt);
    SmartDashboard.putNumber("drive Target ", targetPosition);
    setDPower(power);
    SmartDashboard.putNumber("drive Power", power);
    SmartDashboard.putNumber("drive velocity", driveMotor.getVelocity().getValueAsDouble());

     }
        power = 0;
        setDPower(power);
    }
    public void steer(double targetAngle,  double kp, double ki, double kd){
        targetAngle = SmartDashboard.getNumber( "steering Target", targetAngle);
        double power;
        double currnt = getSPosition();
        double sumError = 0;
        PIDController Spid = new PIDController(kp,ki,kd);
        SmartDashboard.putData("My steering PID", Spid);
        double error = targetAngle - currnt;
        while (Math.abs(error) > 3){
            currnt = getSPosition();
            double lastError = error;
            error = targetAngle - currnt;
            sumError += error;
            double p = kp * error;
            double i = ki * sumError;
            double d = kd * (lastError - error);
            power = MathUtil.clamp(p+i+d,-0.5, 0.5);
            SmartDashboard.putNumber("steering Error", error);
            SmartDashboard.putNumber("steering angle", currnt);
            SmartDashboard.putNumber("steering Target ", targetAngle);
            setSPower(power);
            SmartDashboard.putNumber("steering Power", power);
            SmartDashboard.putNumber("steering velocity", steerMotor.getVelocity().getValueAsDouble());
        }
        power = 0;
        setSPower(power);
    }
    public void setDVelocity(double targetVelocity,  double kp, double ki, double kd){
        targetVelocity = SmartDashboard.getNumber( "drive target velocity", targetVelocity);
        double power;
        double currnt = driveMotor.getVelocity().getValueAsDouble();
        double sumError = 0;
        PIDController DVpid = new PIDController(kp,ki,kd);
        SmartDashboard.putData("My drive velocity PID", DVpid);
        double error = targetVelocity - currnt;
        while (Math.abs(error) > 3){
            currnt = driveMotor.getVelocity().getValueAsDouble();
            double lastError = error;
            error = targetVelocity - currnt;
            sumError += error;
            double p = kp * error;
            double i = ki * sumError;
            double d = kd * (lastError - error);
            power = MathUtil.clamp(p+i+d,-0.5, 0.5);
            SmartDashboard.putNumber("drive velocity Error", error);
            SmartDashboard.putNumber("drive velocity", currnt);
            SmartDashboard.putNumber("drive target velocity", targetVelocity);
            setDPower(power);
            SmartDashboard.putNumber("drive power", power);
        }
        power = 0;
        setDPower(power);
    }
    
}

