package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj.XboxController;
import frc.robot.subsystems.SparkMotorSubsystem;

public class DriveSparkWithJoystick extends Command {
    private final SparkMotorSubsystem motor;
    private final XboxController controller;

    public DriveSparkWithJoystick(SparkMotorSubsystem motor, XboxController controller) {
        this.motor = motor;
        this.controller = controller;
        addRequirements(motor);
    }

    @Override
    public void execute() {
        double joystickVal = -controller.getLeftY(); // הפוך אם צריך
        if (Math.abs(joystickVal) < 0.05) joystickVal = 0;

        motor.setVelocitySmart(joystickVal * 5); // תעדכן מהירות מקסימלית
    }

    @Override
    public void end(boolean interrupted) {
        motor.stop();
    }

    @Override
    public boolean isFinished() {
        return false; // רץ כל עוד לא מבטלים
    }
}
