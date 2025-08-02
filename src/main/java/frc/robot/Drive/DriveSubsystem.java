package frc.robot.Drive;

import static edu.wpi.first.units.Units.Degrees;

import com.ctre.phoenix6.StatusSignal;
import com.ctre.phoenix6.hardware.Pigeon2;

import edu.wpi.first.math.estimator.SwerveDrivePoseEstimator;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.math.kinematics.SwerveDriveKinematics;
import edu.wpi.first.math.kinematics.SwerveModulePosition;
import edu.wpi.first.math.kinematics.SwerveModuleState;
import edu.wpi.first.units.measure.Angle;
import edu.wpi.first.util.sendable.SendableBuilder;
import edu.wpi.first.wpilibj.smartdashboard.Field2d;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.Demacia.utils.XboxUtils;
import frc.Demacia.utils.XboxUtils.JoystickSide;

public class DriveSubsystem extends SubsystemBase {

    SwerveModule[] modules;
    SwerveDrivePoseEstimator poseEstimator;
    Field2d robotField;
    SwerveDriveKinematics kinematics;
    Pigeon2 gyro = new Pigeon2(Constants.GYRO_ID);
    SwerveModulePosition[] modulePositions;
    SwerveModuleState[] moduleState;
    StatusSignal<Angle> gyroSignal;
    Pose2d pose;
    ChassisSpeeds currentChassisSpeeds;
    CommandXboxController controller;
    ChassisSpeeds targetChassisSpeeds = new ChassisSpeeds();

    public DriveSubsystem(CommandXboxController controller) {
        super();
        this.controller = controller;
        modules = new SwerveModule[Constants.CONFIGS.length];
        Translation2d[] modulePositionOnRobot = new Translation2d[modules.length];
        modulePositions = new SwerveModulePosition[modules.length];
        moduleState = new SwerveModuleState[modules.length];
        for(int i = 0; i < modules.length; i++) {
            modules[i] = new SwerveModule(Constants.CONFIGS[i]);
            modulePositionOnRobot[i] = modules[i].config.positionRelativeToRobotCenter;
            moduleState[i] = modules[i].state;
            modulePositions[i] = modules[i].position;
        }
        kinematics = new SwerveDriveKinematics(modulePositionOnRobot);
        gyro = new Pigeon2(Constants.GYRO_ID, Constants.CANBUS);
        gyroSignal = gyro.getYaw();
        poseEstimator = new SwerveDrivePoseEstimator(kinematics, getGyroRotation(), modulePositions,new Pose2d());
        pose = poseEstimator.getEstimatedPosition();
        robotField = new Field2d();
        SmartDashboard.putData("Drive", this);
        SmartDashboard.putData("Robot Position", robotField);
        setDefaultCommand(new RunCommand(this::drive, this));
        controller.start().onTrue(new InstantCommand(this::setFieldHeading, this).ignoringDisable(true));
    }

    private void drive() {
        targetChassisSpeeds.vxMetersPerSecond = XboxUtils.getJSvalue(controller, JoystickSide.RightY) * Constants.MAX_SPEED;
        targetChassisSpeeds.vyMetersPerSecond = -XboxUtils.getJSvalue(controller, JoystickSide.RightX) * Constants.MAX_SPEED;
        targetChassisSpeeds.omegaRadiansPerSecond = XboxUtils.getNormalized(controller.getLeftTriggerAxis() - controller.getRightTriggerAxis()) * Constants.MAX_OMEGA;
        setSpeeds(targetChassisSpeeds);
    }

    public void setFieldHeading() {
        resetPose(pose.getTranslation(), Rotation2d.kZero);
    }

    public double getGyroHeading() {
        gyroSignal.refresh();
        return gyroSignal.getValue().in(Degrees);
    }

    public void resetPose(Translation2d translation2d, Rotation2d rotation2d) {
        poseEstimator.resetPose(new Pose2d(translation2d, rotation2d));
    }

    public double getHeading() {
        return pose.getRotation().getDegrees();
    }
    public Rotation2d getHeadingRotation() {
        return pose.getRotation();
    }

    public Rotation2d getGyroRotation() {
        gyroSignal.refresh();
        return new Rotation2d(gyroSignal.getValue());
    }

    public void setSpeeds(ChassisSpeeds speeds) {
        ChassisSpeeds robotRelativSpeeds = ChassisSpeeds.fromFieldRelativeSpeeds(speeds, getHeadingRotation());
        limitSpeeds(robotRelativSpeeds);
        SwerveModuleState[] states = kinematics.toSwerveModuleStates(robotRelativSpeeds);
        SwerveDriveKinematics.desaturateWheelSpeeds(states, Constants.MAX_SPEED);
        for(int i = 0; i < modules.length; i++) {
            modules[i].setState(states[i]);
        }
    }

    private void limitSpeeds(ChassisSpeeds speeds) {
        // limit robot relative speeds to account for MAX accelration
        if(Math.abs(speeds.vxMetersPerSecond) > 0.1) {
            double currentX = currentChassisSpeeds.vxMetersPerSecond;
            double newX = Math.min(Math.max(speeds.vxMetersPerSecond, currentX-Constants.MAX_X_VELOCITY_CHANGE),currentX+Constants.MAX_X_VELOCITY_CHANGE);
            double ratio = Math.abs(newX / speeds.vxMetersPerSecond);
            speeds.vxMetersPerSecond *= ratio;
            speeds.vyMetersPerSecond *= ratio;
        } 
        if(Math.abs(speeds.vyMetersPerSecond)  > 0.1) {
            double currentY = currentChassisSpeeds.vyMetersPerSecond;
            double newY = Math.min(Math.max(speeds.vyMetersPerSecond, currentY-Constants.MAX_Y_VELOCITY_CHANGE),currentY+Constants.MAX_Y_VELOCITY_CHANGE);
            double ratio = Math.abs(newY / speeds.vxMetersPerSecond);
            speeds.vxMetersPerSecond *= ratio;
            speeds.vyMetersPerSecond *= ratio;
        }
    }

    @Override
    public void periodic() {
        super.periodic();
        for(SwerveModule m : modules) {
            m.refreshPosition();
            m.refreshState();
        }
        currentChassisSpeeds = kinematics.toChassisSpeeds(moduleState);
        poseEstimator.update(getGyroRotation(), modulePositions);
        pose = poseEstimator.getEstimatedPosition();
        robotField.setRobotPose(pose);

    }

    @Override
    public void initSendable(SendableBuilder builder) {
        super.initSendable(builder);
        builder.addDoubleProperty("Heading", this::getHeading, null);
    }

}
