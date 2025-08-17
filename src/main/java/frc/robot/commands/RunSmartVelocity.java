package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.SparkMotorSubsystem;
import edu.wpi.first.wpilibj.XboxController;

public class RunSmartVelocity extends Command {
    private final SparkMotorSubsystem motorSubsystem;
    private final XboxController controller;

    public RunSmartVelocity(SparkMotorSubsystem subsystem, XboxController controller) {
        this.motorSubsystem = subsystem;
        this.controller = controller;
        addRequirements(subsystem);
    }

    @Override
    public void execute() {
        double joystickValue = controller.getLeftY() * -1; // קדימה אחורה, הפוך אם צריך
        double desiredVelocity = joystickValue * 5.0; // לדוגמה: תחום מהירויות בין -5 ל+5 m/s

        motorSubsystem.setSmartVelocity(desiredVelocity);
    }

    @Override
    public void end(boolean interrupted) {
        motorSubsystem.stop();
    }
}
