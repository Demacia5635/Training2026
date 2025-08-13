package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.MyFirstSubsystem;
import edu.wpi.first.wpilibj.XboxController;

public class DriveWithJoystick extends Command {
    private final MyFirstSubsystem module;
    private final XboxController controller;

    public DriveWithJoystick(MyFirstSubsystem module, XboxController controller) {
        this.module = module;
        this.controller = controller;
        addRequirements(module);
    }

    @Override
    public void execute() {
        double forwardSpeed = controller.getLeftY() * -1; // שליטה קדימה-אחורה
        double angle = controller.getLeftX() * 180; // סיבוב

        module.setDriveVelocity(forwardSpeed);
        module.setSteerAngle(angle);
    }
}
