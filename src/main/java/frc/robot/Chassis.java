package frc.robot;
import static edu.wpi.first.units.Units.Radian;

import com.ctre.phoenix6.hardware.Pigeon2;

import edu.wpi.first.math.estimator.PoseEstimator;
import edu.wpi.first.math.estimator.SwerveDrivePoseEstimator;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.math.kinematics.Kinematics;
import edu.wpi.first.math.kinematics.SwerveDriveKinematics;
import edu.wpi.first.math.kinematics.SwerveModulePosition;
import edu.wpi.first.math.kinematics.SwerveModuleState;
import edu.wpi.first.wpilibj.smartdashboard.Field2d;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.subsystems.SwerveModule;

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
        moudles[0].SetMotor(Constants.MyFirstSubsystemConstants.steer_Motor_ID_Front_left,Constants.MyFirstSubsystemConstants.Drive_MOTOR_ID_Front_left);
        moudles[1].SetMotor(Constants.MyFirstSubsystemConstants.steer_Motor_ID_Front_right,Constants.MyFirstSubsystemConstants.Drive_Motor_ID_Front_right);
        moudles[2].SetMotor(Constants.MyFirstSubsystemConstants.steer_Motor_ID_back_left,Constants.MyFirstSubsystemConstants.Drive_Motor_ID_back_left);
        moudles[2].SetMotor(Constants.MyFirstSubsystemConstants.steer_Motor_ID_back_right,Constants.MyFirstSubsystemConstants.Drive_Motor_ID_back_right);
        poseEstimator = new SwerveDrivePoseEstimator(kinematicsFix, getAngle(), null, new Pose2d());
        field = new Field2d();
        SmartDashboard.putData("reset gyro", new InstantCommand(this::resetGyro).ignoringDisable(true));
        SmartDashboard.putData("field", field);
            
    }
    public void resetPose(Pose2d pose2d){
        poseEstimator.resetPose(pose2d);
    }


    public void resetGyro() {
        resetPose(new Pose2d(getPose().getTranslation(), Rotation2d.kZero));
    }

    public Rotation2d getAngle() {
        return Rotation2d.fromRadians(gyro.getYaw().getValue().in(Radian));
    }
    public double getHeading() {
        return poseEstimator.getEstimatedPosition().getRotation().getDegrees();
    }

    public Pose2d getPose(){
        return poseEstimator.getEstimatedPosition();
    }
    public void setVelocities(ChassisSpeeds wantedSpeeds, ChassisSpeeds speeds){
        ChassisSpeeds robotSpeed = speeds.fromFieldRelativeSpeeds(wantedSpeeds, gyro.getRotation2d());
        SwerveModuleState[] states = kinematicsFix.toSwerveModuleStates(robotSpeed);
        for(int i = 0; i < moudles.length; i++) {
            moudles[i].SetState(states[i]);
       }
       

    }

    public SwerveModulePosition[] getModulePositions(){
        SwerveModulePosition[] swerveModulesPos = new SwerveModulePosition[4];
        for(int i = 0; i < 4; i++){
            swerveModulesPos[i] = new SwerveModulePosition(moudles[i].GetVelocityDrive(),Rotation2d.fromDegrees(moudles[i].GetPositionOfSteer() ));
        }
        return swerveModulesPos;
    }

    public void periodic() {
        poseEstimator.update(getAngle(), getModulePositions());
        field.setRobotPose(poseEstimator.getEstimatedPosition());

    }



    

}
