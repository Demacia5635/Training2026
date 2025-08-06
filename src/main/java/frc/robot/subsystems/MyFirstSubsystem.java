package frc.robot.subsystems;

//import com.ctre.phoenix6.StatusSignal;
import com.ctre.phoenix6.hardware.TalonFX;
// import com.fasterxml.jackson.databind.deser.ValueInstantiator.Gettable;

import edu.wpi.first.math.controller.PIDController;
// import edu.wpi.first.units.measure.Angle;
// import edu.wpi.first.util.sendable.Sendable;
import edu.wpi.first.util.sendable.SendableBuilder;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.Constants.OperatorConstants;


public class MyFirstSubsystem extends SubsystemBase {
    private PIDController SteerController;


    // Define the motor 
    TalonFX steerMotor;
     
    TalonFX driveMotor;
    

    // Constructor
    public MyFirstSubsystem() {
        super();
        steerMotor = new TalonFX(Constants.MyFirstSubsystemConstants.SMOTOR_ID, Constants.MyFirstSubsystemConstants.MOTOR_CAN);
        driveMotor = new TalonFX(Constants.MyFirstSubsystemConstants.DMOTOR_ID, Constants.MyFirstSubsystemConstants.MOTOR_CAN);

        SteerController = new PIDController(0.005, 0.001, 0.0005);

        SteerController.setTolerance(2.0); // angle tolerance for the wheel  
        SteerController.setIntegratorRange(-5, 5);
    }
    public double getSteerErrTol() {
        return SteerController.getErrorTolerance();
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
public void SteerToAngle(double targetAngle) {
    double currentAngle = getSPosition();
    double turnSpeed = SteerController.calculate(currentAngle, targetAngle);
    setSPower(turnSpeed);
}
public void DriveToAngle(double targetAngle) {
    double currentAngle = getDPosition();
    double turnSpeed = SteerController.calculate(currentAngle, targetAngle);
    setDPower(turnSpeed);
}

public double getDPosition() {
    double DMotorPosition = driveMotor.getPosition().getValueAsDouble();
    return (DMotorPosition/OperatorConstants.DgearRatio)*360;
}
 @Override
 public void initSendable(SendableBuilder builder){
    super.initSendable(builder);
    builder.addDoubleProperty("drive angle", () -> getDPosition(), null);
    builder.addDoubleProperty("steering angle", () -> getSPosition(), null);
 }
 public void drive(double distanceInCm, double power){
    double wheelCircumference = Math.PI * OperatorConstants.wheelDiameter;
    double rotations = distanceInCm / wheelCircumference; 
    double targetPosition = rotations*360+getDPosition();
    double current = getDPosition();
    double error = targetPosition - current;
    power = power*Math.signum(error);
     while (Math.abs(error) > 10){
     current = getDPosition();
     error = targetPosition - current;
    SmartDashboard.putNumber("drive Error", error);
    SmartDashboard.putNumber("drive angle", current);
    SmartDashboard.putNumber("drive Target ", targetPosition);
    setDPower(power);
    SmartDashboard.putNumber("drive Power", power);
    SmartDashboard.putNumber("drive velocity", driveMotor.getVelocity().getValueAsDouble());

     }
        power = 0;
        setDPower(power);
    }
    public void steer(double targetAngle, double power){
        double current = getSPosition();
    double error = targetAngle - current;
    power = power*Math.signum(error);
     while (Math.abs(error) > 10){
     current = getSPosition();
     error = targetAngle - current;
    SmartDashboard.putNumber("steering Error", error);
    SmartDashboard.putNumber("steering angle", current);
    SmartDashboard.putNumber("steering Target ", targetAngle);
    setSPower(power);
    SmartDashboard.putNumber("steering Power", power);
    SmartDashboard.putNumber("steering velocity", steerMotor.getVelocity().getValueAsDouble());
     }
        power = 0;
        setSPower(power);
    }
    }
    