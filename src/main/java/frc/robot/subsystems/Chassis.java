package frc.robot.subsystems;

import com.ctre.phoenix6.hardware.Pigeon2;
import edu.wpi.first.math.estimator.SwerveDrivePoseEstimator;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.math.kinematics.SwerveDriveKinematics;
import edu.wpi.first.math.kinematics.SwerveModulePosition;
import edu.wpi.first.math.kinematics.SwerveModuleState;
import edu.wpi.first.wpilibj.smartdashboard.Field2d;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Chassis extends SubsystemBase {
    // מודולי הסרב
    private final SwerveModule fl = new SwerveModule(Constants.CAN.FL_DRIVE_ID, Constants.CAN.FL_STEER_ID,Constants.CAN.FL_CANcoder_ID,Constants.CAN.FL_CANcoder_Ofset);
    private final SwerveModule fr = new SwerveModule(Constants.CAN.FR_DRIVE_ID, Constants.CAN.FR_STEER_ID,Constants.CAN.FR_CANcoder_ID,Constants.CAN.FR_CANcoder_Ofset);
    private final SwerveModule bl = new SwerveModule(Constants.CAN.BL_DRIVE_ID, Constants.CAN.BL_STEER_ID,Constants.CAN.BL_CANcoder_ID,Constants.CAN.BL_CANcoder_Ofset);
    private final SwerveModule br = new SwerveModule(Constants.CAN.BR_DRIVE_ID, Constants.CAN.BR_STEER_ID,Constants.CAN.BR_CANcoder_ID,Constants.CAN.BR_CANcoder_Ofset);

    private final SwerveDriveKinematics kinematics = Constants.Swerve.KINEMATICS;
    private final Pigeon2 gyro = new Pigeon2(Constants.CAN.PIGEON_ID);
    private final Field2d field = new Field2d();
    private final SwerveDrivePoseEstimator poseEstimator;

    public Chassis() {
        gyro.reset();
        poseEstimator = new SwerveDrivePoseEstimator(kinematics, getHeading(), getModulePositions(), new Pose2d());
        SmartDashboard.putData("Field", field);
    }

    /** מחזיר את כיוון הרובוט (yaw) ביחס למגרש */
    public Rotation2d getHeading() {
        return Rotation2d.fromDegrees(gyro.getYaw().getValueAsDouble());
    }

    /** מאפס את כיוון הראש ל־0° */
    public void zeroHeadingToField() {
        gyro.setYaw(0.0);
    }

    /** מחזיר מערך של מצבי מודולי הסרב (Position) */
    public SwerveModulePosition[] getModulePositions() {
        return new SwerveModulePosition[] {
            new SwerveModulePosition(fl.getDrivePosition(), new Rotation2d(fl.getSteerPosition())),
            new SwerveModulePosition(fr.getDrivePosition(), new Rotation2d(fr.getSteerPosition())),
            new SwerveModulePosition(bl.getDrivePosition(), new Rotation2d(bl.getSteerPosition())),
            new SwerveModulePosition(br.getDrivePosition(), new Rotation2d(br.getSteerPosition()))
        };
    }

    /** מפעיל את הרובוט עם מהירויות נתונות, עם או בלי field-relative */
    public void drive(ChassisSpeeds speeds, boolean fieldRelative) {
        ChassisSpeeds applied = fieldRelative
            ? ChassisSpeeds.fromFieldRelativeSpeeds(speeds, getHeading())
            : speeds;

        SwerveModuleState[] states = kinematics.toSwerveModuleStates(applied);
        SwerveDriveKinematics.desaturateWheelSpeeds(states, Constants.Swerve.MAX_SPEED_MPS);

        fl.setDriveSpeed(states[0].speedMetersPerSecond);
        fl.setSteerAngle(states[0].angle.getDegrees());

        fr.setDriveSpeed(states[1].speedMetersPerSecond);
        fr.setSteerAngle(states[1].angle.getDegrees());

        bl.setDriveSpeed(states[2].speedMetersPerSecond);
        bl.setSteerAngle(states[2].angle.getDegrees());

        br.setDriveSpeed(states[3].speedMetersPerSecond);
        br.setSteerAngle(states[3].angle.getDegrees());
    }

    /** עוצר את כל המנועים */
    public void stop() {
        fl.setDriveSpeed(0);
        fr.setDriveSpeed(0);
        bl.setDriveSpeed(0);
        br.setDriveSpeed(0);
    }

    /** מאפס את כל מודולי הסרב למצב "קדימה" */
    public void faceForwardAll() {
        fl.setSteerAngle(0);
        fr.setSteerAngle(0);
        bl.setSteerAngle(0);
        br.setSteerAngle(0);
    }

    /** מחזיר את הפוזה הנוכחית */
    public Pose2d getPose() {
        return poseEstimator.getEstimatedPosition();
    }

    /** מאפס את הפוזה */
    public void resetPose(Pose2d pose) {
        poseEstimator.resetPosition(getHeading(), getModulePositions(), pose);
    }

    /** קריאה מחזורית */
    @Override
    public void periodic() {
        // עדכון PoseEstimator
        poseEstimator.update(getHeading(), getModulePositions());

        // עדכון Field2d
        Pose2d pose = getPose();
        field.setRobotPose(pose);

        // עדכון ל‑SmartDashboard
        SmartDashboard.putNumber("Pose/X", pose.getX());
        SmartDashboard.putNumber("Pose/Y", pose.getY());
        SmartDashboard.putNumber("Pose/HeadingDeg", pose.getRotation().getDegrees());

        // ניתן להוסיף גם log של מודולים
        fl.logToDashboard("FL");
        fr.logToDashboard("FR");
        bl.logToDashboard("BL");
        br.logToDashboard("BR");
    }
}