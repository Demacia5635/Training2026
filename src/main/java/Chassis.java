import com.ctre.phoenix6.hardware.Pigeon2;

import edu.wpi.first.math.estimator.PoseEstimator;
import edu.wpi.first.math.estimator.SwerveDrivePoseEstimator;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.math.kinematics.Kinematics;
import edu.wpi.first.math.kinematics.SwerveDriveKinematics;
import edu.wpi.first.math.kinematics.SwerveModuleState;
import edu.wpi.first.wpilibj.smartdashboard.Field2d;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.subsystems.SwerveModule;
import frc.robot.Constants;

public class Chassis {
    SwerveModule[] moudles;
    Pigeon2 gyro;
    SwerveDrivePoseEstimator poseEstimator;
    Field2d field;
    SwerveDriveKinematics kinematicsFix;
    public Chassis(){
        moudles=new SwerveModule[]{
            new SwerveModule("FrontLeft"),
            new SwerveModule("FrontRight"),
            new SwerveModule("BackLeft"),
            new SwerveModule("BackRight"),

        };
        gyro = new Pigeon2(Constants.MyFirstSubsystemConstants.Gyro_Id,Constants.MyFirstSubsystemConstants.MOTOR_CAN);
            kinematicsFix = new SwerveDriveKinematics(
            new Translation2d[] {
                new Translation2d(0.35, 0.3),
                 new Translation2d(0.35, -0.3),
                new Translation2d(-0.35, 0.3),
                new Translation2d(-0.35, -0.3)
            }
        );
        poseEstimator = new SwerveDrivePoseEstimator(kinematicsFix, null, null, new Pose2d());
        field = new Field2d();
        SmartDashboard.putData("reset gyro", new InstantCommand(() -> setYaw(Rotation2d.kZero)).ignoringDisable(true));
        SmartDashboard.putData("reset gyro 180", new InstantCommand(() -> setYaw(Rotation2d.kPi)).ignoringDisable(true));
        SmartDashboard.putData("field", field);
        SmartDashboard.putData("Chassis/set coast", new InstantCommand(() -> setNeutralMode(false)).ignoringDisable(true));
        SmartDashboard.putData("Chassis/set brake", new InstantCommand(() -> setNeutralMode(true)).ignoringDisable(true));
            
    }
    public void resetPose(Pose2d pose2d){
        poseEstimator.resetPose(pose2d);
    }
    public Pose2d getPose(){
        return poseEstimator.getEstimatedPosition();
    }
    public void setVelocitiesWithAccel(ChassisSpeeds wantedSpeeds, ChassisSpeeds speeds , Translation2d target){
        ChassisSpeeds robotSpeed = speeds.fromFieldRelativeSpeeds(wantedSpeeds, gyro.getRotation2d());
        Translation2d robotToTarget = target.minus(getPose().getTranslation());
        SwerveModuleState[] states = kinematicsFix.toSwerveModuleStates(robotSpeed);
        for(int i = 0; i < moudles.length; i++) {
            moudles[i].SetState(states[i]);
       }
       

    }
    public void periodic() {
        double gyroAngle = gyro.getAngle();
        poseEstimator.update(gyroAngle, getModulePositions());
        field.setRobotPose(poseEstimator.getEstimatedPosition());

    }



    

}
