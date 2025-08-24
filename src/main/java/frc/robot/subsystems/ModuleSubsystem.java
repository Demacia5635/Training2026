package frc.robot.subsystems;

import com.ctre.phoenix6.hardware.TalonFX;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.util.sendable.SendableBuilder;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import com.ctre.phoenix6.hardware.TalonFX;

public class ModuleSubsystem extends SubsystemBase {
    TalonFX DriveMotor;
    TalonFX SteerMotor;
    private PIDController SteerController;
    private PIDController DriveController;
    public ModuleSubsystem(){
        super();
        DriveMotor = new TalonFX(Constants.MyFirstSubsystemConstants.Drive_MOTOR_ID,Constants.MyFirstSubsystemConstants.MOTOR_CAN);
        SteerMotor = new TalonFX(Constants.MyFirstSubsystemConstants.steer_Motor_ID, Constants.MyFirstSubsystemConstants.MOTOR_CAN);
        SteerController = new PIDController(Constants.MyFirstSubsystemConstants.Kp,Constants.MyFirstSubsystemConstants.Ki, Constants.MyFirstSubsystemConstants.Kd);
        DriveController = new PIDController(Constants.MyFirstSubsystemConstants.kp2,Constants.MyFirstSubsystemConstants.ki2, Constants.MyFirstSubsystemConstants.kd2);
        SmartDashboard.putData("MoveSubsystem", this);
    }

    public void setPower(double power){
        DriveMotor.set(0.1);
    }
    public void Stop() {
        setPower(0.0);
    }
    public double GetVelocity() {
        return DriveMotor.getVelocity().getValueAsDouble();
    }
        @Override
    public void initSendable(SendableBuilder builder) {
        builder.addDoubleProperty("velocty", this::GetVelocity, null);
    }


}
