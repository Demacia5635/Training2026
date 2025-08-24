package frc.robot.subsystems;

import com.ctre.phoenix6.hardware.TalonFX;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class ModuleSubsystem extends SubsystemBase {
    TalonFX DriveMotor;
    TalonFX SteerMotor;
    private PIDController SteerController;
    private PIDController DriveController;
    public ModuleSubsystem(){
        DriveMotor = new TalonFX(Constants.MyFirstSubsystemConstants.Drive_MOTOR_ID,Constants.MyFirstSubsystemConstants.MOTOR_CAN);
        SteerMotor = new TalonFX(Constants.MyFirstSubsystemConstants.steer_Motor_ID, Constants.MyFirstSubsystemConstants.MOTOR_CAN);
        SteerController = new PIDController(Constants.MyFirstSubsystemConstants.Kp,Constants.MyFirstSubsystemConstants.Ki, Constants.MyFirstSubsystemConstants.Kd);
        DriveController = new PIDController(Constants.MyFirstSubsystemConstants.kp2,Constants.MyFirstSubsystemConstants.ki2, Constants.MyFirstSubsystemConstants.kd2);
    }
    public void setPower(){
        
    }
}
