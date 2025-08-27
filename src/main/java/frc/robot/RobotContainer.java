package frc.robot;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.commands.DriveCommand;
import frc.robot.commands.ZeroHeading;
import frc.robot.commands.ZeroModules;
import frc.robot.subsystems.Chassis;

public class RobotContainer {
    private final Chassis chassis = new Chassis();
    private final XboxController xbox = new XboxController(Constants.OI.XBOX_PORT);
    private final CommandXboxController cx = new CommandXboxController(Constants.OI.XBOX_PORT);

    public RobotContainer() {
        chassis.setDefaultCommand(new DriveCommand(chassis, xbox, true));
        configureBindings();
    }

    private void configureBindings() {
        cx.a().onTrue(new ZeroHeading(chassis));
        cx.b().onTrue(new ZeroModules(chassis));
        cx.y().onTrue(new InstantCommand(() -> chassis.resetPose(new edu.wpi.first.math.geometry.Pose2d()), chassis));
        cx.x().onTrue(new InstantCommand(() -> {
            boolean currentlyField = (chassis.getDefaultCommand() instanceof DriveCommand d) ? d != null : true;
            chassis.setDefaultCommand(new DriveCommand(chassis, xbox, !(currentlyField)));
        }));
    }

    public Chassis getChassis() { return chassis; }
}
