package frc.robot.commands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.MyFirstSubsystem;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.Subsystem;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;



public class GoToAngle extends Command {
    MyFirstSubsystem subsystem;

    public GoToAngle(MyFirstSubsystem subsystem) {
        this.subsystem = subsystem;
        addRequirements(subsystem);
        SmartDashboard.putNumber("Target Angle",90);
    }
    @Override
    public void execute() {
        end();
    }
    public Command end() {
        return new SequentialCommandGroup(
            new InstantCommand(() -> subsystem.steer(90,0.05)),
            new InstantCommand(() -> drive1steer135()),
            new InstantCommand(() -> drive_1steer0())
        );
    }
    
    public Command drive1steer135() {
        return new ParallelCommandGroup(
            new InstantCommand(() -> subsystem.drive(100, 0.3)),
            new InstantCommand(() -> subsystem.steer(135,0.05))
            );
        
    }
    public Command drive_1steer0() {
        return new ParallelCommandGroup(
            new InstantCommand(() -> subsystem.drive(-100, 0.3)),
            new InstantCommand(() -> subsystem.steer(0,0.05))
            );
        
    }
}