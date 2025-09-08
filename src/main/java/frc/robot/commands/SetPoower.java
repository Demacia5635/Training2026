package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants;
import frc.robot.subsystems.SwerveModule;

public class SetPoower extends Command {
    SwerveModule[] moudles;
    private final double duration;
    Timer Timer;
    public SetPoower(){
        moudles=new SwerveModule[]{
            new SwerveModule("FrontLeft",Constants.MyFirstSubsystemConstants.steer_Motor_ID_Front_left,Constants.MyFirstSubsystemConstants.Drive_MOTOR_ID_Front_left),
            new SwerveModule("FrontRight",Constants.MyFirstSubsystemConstants.steer_Motor_ID_Front_right,Constants.MyFirstSubsystemConstants.Drive_Motor_ID_Front_right),
            new SwerveModule("BackLeft",Constants.MyFirstSubsystemConstants.steer_Motor_ID_back_left,Constants.MyFirstSubsystemConstants.Drive_Motor_ID_back_left),
            new SwerveModule("BackRight",Constants.MyFirstSubsystemConstants.steer_Motor_ID_back_right,Constants.MyFirstSubsystemConstants.Drive_Motor_ID_back_right),

        };
        duration=10;
        
    }
    @Override
    public void initialize(){
        double startTime = Timer.getFPGATimestamp();
    }
    @Override
    public void execute(){
        moudles[0].setPowerAll(0.1);
        moudles[1].setPowerAll(0.1);
        moudles[2].setPowerAll(0.1);
        moudles[3].setPowerAll(0.1);
    }
    @Override
    public boolean isFinished(){
        return Timer.getFPGATimestamp() >= duration;
        
    }
    @Override
    public void end(boolean interrupted){
        moudles[0].Stop();
        moudles[1].Stop();
        moudles[2].Stop();
        moudles[3].Stop();
    }

}
