package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Chassis;

public class ZeroHeading extends Command {
    private final Chassis chassis;

    public ZeroHeading(Chassis chassis) {
        this.chassis = chassis;
        addRequirements(chassis);
    }

    @Override
    public void initialize() {
        chassis.zeroHeadingToField();
    }

    @Override
    public boolean isFinished() {
        return true; // פקודה מיידית, מסתיימת מיד
    }
}
