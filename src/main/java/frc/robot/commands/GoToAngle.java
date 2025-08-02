package frc.robot.commands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.MyFirstSubsystem;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.Subsystem;


public class GoToAngle extends Command {
    MyFirstSubsystem subsystem;

    public GoToAngle(MyFirstSubsystem subsystem) {
        this.subsystem = subsystem;
        addRequirements(subsystem);
        SmartDashboard.putNumber("Target Angle",90);
    }

    @Override
    public void execute() {
        double target1 = 90;
        double currnt = subsystem.getSPosition();
        double error = target1 - currnt;
        double power;
        SmartDashboard.putNumber("Error", error);
        SmartDashboard.putNumber("angle", currnt);
        SmartDashboard.putNumber("Target Angle", target1);
        if (Math.abs(error) < 10){
            power = 0;
        }
        else {
            power = 0.05*Math.signum(error);
        }
        SmartDashboard.putNumber("Power", power);
        subsystem.setSPower(power);
    }
    public Command drive1steer135() {
        return new ParallelCommandGroup(
          
          
        )
    }
