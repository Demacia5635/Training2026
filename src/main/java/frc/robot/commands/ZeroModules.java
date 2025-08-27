package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Chassis;

public class ZeroModules extends Command {
    private final Chassis chassis;

    public ZeroModules(Chassis chassis) {
        this.chassis = chassis;
        addRequirements(chassis);
    }

    @Override
    public void initialize() {
        chassis.faceForwardAll();
    }

    @Override
    public boolean isFinished() {
        return true; // פקודה מיידית, מסתיימת מיד
    }
}
