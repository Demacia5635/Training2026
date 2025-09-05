package frc.robot.Drive;

import edu.wpi.first.math.geometry.Rotation2d;

public class SwerveModuleState extends edu.wpi.first.math.kinematics.SwerveModuleState {
    public double distanceMeters;

    public SwerveModuleState(double metersPerSecond, double distanceMeters, Rotation2d angle) {
        super(metersPerSecond, angle);
        this.distanceMeters = distanceMeters;

    }
    public SwerveModuleState(double metersPerSecond, double distanceMeters, double angleRadians) {
        this(metersPerSecond, distanceMeters, new Rotation2d(angleRadians));        
    }

}
