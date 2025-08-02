package frc.robot.Drive;

import com.ctre.phoenix6.CANBus;
import edu.wpi.first.math.geometry.Translation2d;
import frc.Demacia.utils.Motors.TalonConfig;

public class Constants {

    public static final double MK4i_STEER_RATIO = 150.0 / 7.0;
    public static final double MK4_STEER_RATIO = 12 / 8;
    public static final double L1_DRIVE_RATIO = 8.14;
    public static final double L2_DRIVE_RATIO = 6.75;
    public static final double WHEEL_DIAMETER = 4 * 0.0254;

    public static final double X_POSITION = 0.35;
    public static final double Y_POSITION = 0.3;

    public static CANBus CANBUS = new CANBus("CANivre");

    public static final int GYRO_ID = 13;

    public static double CYCLE_TIME = 0.02;

    public static final double MAX_SPEED = 3.7;
    public static final double MAX_OMEGA = 6; // Radians per second
    public static final double MAX_X_ACCELERATION = 7;
    public static final double MAX_X_VELOCITY_CHANGE = MAX_X_ACCELERATION * CYCLE_TIME;
    public static final double MAX_Y_ACCELERATION = 5;
    public static final double MAX_Y_VELOCITY_CHANGE = MAX_Y_ACCELERATION * CYCLE_TIME;

    public static final ModuleConfig[] CONFIGS = {
            new ModuleConfig(1, 2, 3, X_POSITION, Y_POSITION, 22.6),
            new ModuleConfig(4, 5, 6, X_POSITION, -Y_POSITION, 35.7),
            new ModuleConfig(7, 8, 9, -X_POSITION, Y_POSITION, 50.0),
            new ModuleConfig(10, 11, 12, -X_POSITION, -Y_POSITION, -22.6)
    };

    public static final TalonConfig BASE_STEER_CONFIG = new TalonConfig(0, CANBUS, "BASE_STEER")
            .withBrake(true)
            .withCurrent(15)
            .withRadiansMotor(MK4_STEER_RATIO)
            .withInvert(true)
            .withPID(0, 0, 0, 0, 0, 0, 0)
            .withRampTime(0.2)
            .withVelocities(Math.PI * 4, Math.PI * 8, Math.PI * 16)
            .withVolts(8);
    public static final TalonConfig BASE_DRIVE_CONFIG = new TalonConfig(0, CANBUS, "BASE_DRIVE")
            .withBrake(true)
            .withCurrent(30)
            .withMeterMotor(L1_DRIVE_RATIO, Math.PI * WHEEL_DIAMETER)
            .withInvert(false)
            .withPID(0, 0, 0, 0, 0, 0, 0)
            .withRampTime(0.2)
            .withVelocities(3.5, 6.5, 10)
            .withVolts(12);

    static class ModuleConfig {
        TalonConfig steerConfig;
        TalonConfig driveConfig;
        int cancoderId;
        double cancoderOffset;
        Translation2d positionRelativeToRobotCenter;

        ModuleConfig(int steerId, int driveId, int cancoderId, double xPosition, double yPosition,
                double cancoderOffset) {
            this.cancoderId = cancoderId;
            this.cancoderOffset = cancoderOffset;
            positionRelativeToRobotCenter = new Translation2d(xPosition, yPosition);
            String name = (xPosition > 0 ? "Front" : "Back") + (yPosition > 0 ? "Left" : "Right");
            steerConfig = new TalonConfig(steerId, name + "/STEER", BASE_STEER_CONFIG);
            driveConfig = new TalonConfig(driveId, name + "/DRIVE", BASE_DRIVE_CONFIG);
        }
    }

}
