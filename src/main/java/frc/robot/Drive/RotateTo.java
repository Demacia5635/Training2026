package frc.robot.Drive;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Utilities;

public class RotateTo extends Command {
    double x;
    double y;
    double v;
    double turnRate;
    double omega;
    DriveSubsystem drive;
    Pose2d pose;
    ChassisSpeeds current;
    

    Translation2d toEnd = new Translation2d(0,0);
    Rotation2d rot = new Rotation2d();
    double remaining = 0;

    public static final double FINAL_ERROR = 0.1;

    public RotateTo(double x, double y, double v, double omega, double turnRate, DriveSubsystem drive) {
        this.x = x;
        this.y = y;
        this.v = v;
        this.omega = omega;
        this.drive = drive;
        this.turnRate = Math.toRadians(turnRate) * 0.02;
        pose = drive.pose;
        current = drive.currentChassisSpeeds;
    }


    @Override
    public void execute() {
        toEnd.set(x - pose.getX(), y - pose.getY());
        rot.set(current.vxMetersPerSecond, current.vyMetersPerSecond);
        double heading = rot.getRadians();
        double remaining = MathUtil.angleModulus(toEnd.getAngle().getRadians() - heading);
        double alpha = heading + Utilities.clamp(remaining, -turnRate, turnRate);
        drive.setSpeeds(new ChassisSpeeds(v*Math.cos(alpha),v*Math.sin(alpha) , omega));
    } 

    @Override
    public boolean isFinished() {
        return Math.abs(remaining) < Math.max(FINAL_ERROR, 2*turnRate);
    }

    @Override
    public void end(boolean interrupted) {
        drive.setSpeeds(new ChassisSpeeds(0,0,0));
    }    

}
