package frc.robot.Drive;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.wpilibj2.command.Command;

public class DriveTo extends Command {
    double x;
    double y;
    double v;
    double omega;
    DriveSubsystem drive;
    boolean isFinal;
    Pose2d pose;

    double remaining = 0;
    double initialHeading = 0;
    Translation2d toEnd = new Translation2d(0,0);

    public static final double NON_FINAL_ERROR = 0.5;
    public static final double FINAL_ERROR = 0.05;
    public static final double KVelocity = 0.5;

    public DriveTo(double x, double y, double v, double omega, DriveSubsystem drive, boolean isFinal) {
        this.x = x;
        this.y = y;
        this.v = v;
        this.omega = omega;
        this.drive = drive;
        this.isFinal = isFinal;
        pose = drive.pose;
    }

    @Override
    public void initialize() {
        toEnd.set(x - pose.getX(), y - pose.getY());
        remaining = toEnd.getNorm();
        initialHeading = toEnd.getAngle().getRadians();
    }

    @Override
    public void execute() {
        toEnd.set(x - pose.getX(), y - pose.getY());
        remaining = toEnd.getNorm();
        double alpha = 2*toEnd.getAngle().getRadians() - initialHeading;
        double vel = isFinal ? Math.min(remaining * KVelocity, v) : v;
        drive.setSpeeds(new ChassisSpeeds(vel*Math.cos(alpha),vel*Math.sin(alpha),omega));
    } 

    @Override
    public boolean isFinished() {
        return remaining < (isFinal ? FINAL_ERROR : NON_FINAL_ERROR);
    }

    @Override
    public void end(boolean interrupted) {
        drive.setSpeeds(new ChassisSpeeds(0,0,0));
    }    

}
