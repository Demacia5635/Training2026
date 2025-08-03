package frc.robot.commands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Motor;

public class MoveToAngle extends Command {
    private double targetAngle; // Target angle in degrees
    private Motor subsystem;

    public MoveToAngle(Motor subsystem) {
        this.subsystem = subsystem;
        addRequirements(subsystem);
        // Constructor logic if needed
    }


    @Override
    public void initialize() {
        targetAngle = SmartDashboard.getNumber("angle", 90);
        //subsystem.setPow(0.2);
    }

    @Override
    public void execute() {
        if(subsystem.getPosition() < targetAngle) {
            subsystem.setPow(0.2); // Move forward
        } else {
            subsystem.setPow(-0.2); // Move backward
        }
    }

    @Override
    public void end(boolean interrupted) {
     subsystem.setPow(0);   
    }

    @Override
    public boolean isFinished() {
        return Math.abs(subsystem.getPosition() - targetAngle) < 1.0; // Check if within 1 degree of target
    }
}
