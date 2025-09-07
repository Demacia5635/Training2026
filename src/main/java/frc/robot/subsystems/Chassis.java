package frc.robot.subsystems;

import com.ctre.phoenix6.hardware.Pigeon2;
import edu.wpi.first.math.estimator.SwerveDrivePoseEstimator;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.math.kinematics.SwerveDriveKinematics;
import edu.wpi.first.math.kinematics.SwerveModulePosition;
import edu.wpi.first.math.kinematics.SwerveModuleState;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.Field2d;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Chassis extends SubsystemBase {

    private final SwerveModule fl = new SwerveModule(Constants.CAN.FL_DRIVE_ID, Constants.CAN.FL_STEER_ID,Constants.CAN.FL_CANcoder_ID,Constants.CAN.FL_CANcoder_Ofset);
    private final SwerveModule fr = new SwerveModule(Constants.CAN.FR_DRIVE_ID, Constants.CAN.FR_STEER_ID,Constants.CAN.FR_CANcoder_ID,Constants.CAN.FR_CANcoder_Ofset);
    private final SwerveModule bl = new SwerveModule(Constants.CAN.BL_DRIVE_ID, Constants.CAN.BL_STEER_ID,Constants.CAN.BL_CANcoder_ID,Constants.CAN.BL_CANcoder_Ofset);
    private final SwerveModule br = new SwerveModule(Constants.CAN.BR_DRIVE_ID, Constants.CAN.BR_STEER_ID,Constants.CAN.BR_CANcoder_ID,Constants.CAN.BR_CANcoder_Ofset);

    private final SwerveDriveKinematics kinematics = new SwerveDriveKinematics(Constants.Swerve.KINEMATICS);
    private final Pigeon2 gyro = new Pigeon2(Constants.CAN.PIGEON_ID);
    private final Field2d field = new Field2d();
    private final SwerveDrivePoseEstimator poseEstimator;
    private final XboxController xboxController = new XboxController(Constants.OperatorConstants.kDriverControllerPort);


    public Chassis() {
        gyro.reset();
        poseEstimator = new SwerveDrivePoseEstimator(kinematics, getHeading(), getModulePositions(), new Pose2d());
        SmartDashboard.putData("Field", field);
    }

   
    public Rotation2d getHeading() {
        return Rotation2d.fromDegrees(gyro.getYaw().getValueAsDouble());
    }

    
    public void zeroHeadingToField() {
        gyro.setYaw(0.0);
    }

    
    public SwerveModulePosition[] getModulePositions() {
        return new SwerveModulePosition[] {
            new SwerveModulePosition(fl.getDrivePosition(), new Rotation2d(fl.getSteerPosition())),
            new SwerveModulePosition(fr.getDrivePosition(), new Rotation2d(fr.getSteerPosition())),
            new SwerveModulePosition(bl.getDrivePosition(), new Rotation2d(bl.getSteerPosition())),
            new SwerveModulePosition(br.getDrivePosition(), new Rotation2d(br.getSteerPosition()))
        };
    }

  
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

    public void stop() {
        fl.setDriveSpeed(0);
        fr.setDriveSpeed(0);
        bl.setDriveSpeed(0);
        br.setDriveSpeed(0);
    }

    public void faceForwardAll() {
        fl.setSteerAngle(0);
        fr.setSteerAngle(0);
        bl.setSteerAngle(0);
        br.setSteerAngle(0);
    }

    public Pose2d getPose() {
        return poseEstimator.getEstimatedPosition();
    }

    public void resetPose(Pose2d pose) {
        poseEstimator.resetPosition(getHeading(), getModulePositions(), pose);
    }

    @Override
    public void periodic() {
        poseEstimator.update(getHeading(), getModulePositions());

        Pose2d pose = getPose();
        field.setRobotPose(pose);

        SmartDashboard.putNumber("Pose/X", pose.getX());
        SmartDashboard.putNumber("Pose/Y", pose.getY());
        SmartDashboard.putNumber("Pose/HeadingDeg", pose.getRotation().getDegrees());

        fl.logToDashboard("FL");
        fr.logToDashboard("FR");
        bl.logToDashboard("BL");
        br.logToDashboard("BR");
    }
}