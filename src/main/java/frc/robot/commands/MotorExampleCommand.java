package frc.robot.commands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.MotorExampleSubsytem;

public class MotorExampleCommand extends Command {
    /* set the spark and talon motor to the required power */

    private final MotorExampleSubsytem subsys;
    public MotorExampleCommand(MotorExampleSubsytem subsys) {
        this.subsys = subsys;
        addRequirements(subsys);
        SmartDashboard.putNumber("Set Example Talon", 0);
        SmartDashboard.putNumber("Set Example Spark", 0);
    }

    @Override
    public void initialize() {
        super.initialize();
    }

    @Override
    public void execute() {
        subsys.setTalonPower(SmartDashboard.getNumber("Set Example Talon", 0));
        subsys.setSparkPower(SmartDashboard.getNumber("Set Example Spark", 0));       
    }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public void end(boolean interrupted) {
        subsys.stopTalon();
        subsys.stopSpark();
    }

}
