package frc.robot.commands;

import frc.robot.Constants;
import frc.robot.subsystems.SwerveModule;

public class SetPoower {
    SwerveModule[] moudles;
    public SetPoower(){
        moudles=new SwerveModule[]{
            new SwerveModule("FrontLeft"),
            new SwerveModule("FrontRight"),
            new SwerveModule("BackLeft"),
            new SwerveModule("BackRight"),

        };
        moudles[0].SetMotor(Constants.MyFirstSubsystemConstants.steer_Motor_ID_Front_left,Constants.MyFirstSubsystemConstants.Drive_MOTOR_ID_Front_left);
        moudles[1].SetMotor(Constants.MyFirstSubsystemConstants.steer_Motor_ID_Front_right,Constants.MyFirstSubsystemConstants.Drive_Motor_ID_Front_right);
        moudles[2].SetMotor(Constants.MyFirstSubsystemConstants.steer_Motor_ID_back_left,Constants.MyFirstSubsystemConstants.Drive_Motor_ID_back_left);
        moudles[2].SetMotor(Constants.MyFirstSubsystemConstants.steer_Motor_ID_back_right,Constants.MyFirstSubsystemConstants.Drive_Motor_ID_back_right);
        

    }
}
