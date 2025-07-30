package frc.robot.commands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.MyFirstSubsystem;

public class GoToAngle extends Command {
    MyFirstSubsystem subsystem;

    public GoToAngle(MyFirstSubsystem subsystem) {
        this.subsystem = subsystem;
        addRequirements(subsystem);
        SmartDashboard.putNumber("Target Angle",90);
    }

    @Override
    public void execute() {
        double target = SmartDashboard.getNumber("Target Angle", 0);
        double currnt = subsystem.getPosition2();
        double error = target - currnt;
        double power;
        SmartDashboard.putNumber("Error", error);
        if (Math.abs(error) < 3){
            power = 0;
        }
        else {
            power = 0.2*Math.signum(error);
        }
        SmartDashboard.putNumber("Power", power);
        subsystem.setPower(power);

    }
}
