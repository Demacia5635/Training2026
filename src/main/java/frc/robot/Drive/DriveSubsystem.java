package frc.robot.Drive;


import static edu.wpi.first.units.Units.Radians;

import com.ctre.phoenix6.StatusSignal;
import com.ctre.phoenix6.hardware.Pigeon2;

import edu.wpi.first.math.estimator.SwerveDrivePoseEstimator;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.math.kinematics.SwerveDriveKinematics;
import edu.wpi.first.math.kinematics.SwerveModulePosition;
import edu.wpi.first.math.kinematics.SwerveModuleState;
import edu.wpi.first.units.measure.Angle;
import edu.wpi.first.util.sendable.SendableBuilder;
import edu.wpi.first.wpilibj.smartdashboard.Field2d;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.Demacia.Geometry.Pose2d;
import frc.Demacia.Geometry.Rotation2d;
import frc.Demacia.Geometry.Translation2d;
import frc.Demacia.utils.DriverUtils;
import frc.Demacia.utils.Utilities;
import frc.Demacia.utils.Motors.MotorCommands;
import frc.Demacia.utils.Motors.MotorInterface;
import frc.Demacia.utils.Log.SwerveLogEntry;
import frc.Demacia.utils.DriverUtils.JoystickSide;

public class DriveSubsystem extends SubsystemBase {

    SwerveModule[] modules;
    SwerveDrivePoseEstimator poseEstimator;
    Field2d robotField;
    SwerveDriveKinematics kinematics;
    UdiKinematics1 udiKinematics1;
    Pigeon2 gyro;
    MotorInterface[] steerMotors;
    MotorInterface[] driveMotors;
    SwerveModulePosition[] modulePositions;
    SwerveModuleState[] moduleStates;
    String moduleNames[];
    StatusSignal<Angle> gyroSignal;
    Pose2d pose;
    ChassisSpeeds currentChassisSpeeds = new ChassisSpeeds();
    CommandXboxController controller;
    ChassisSpeeds targetChassisSpeeds = new ChassisSpeeds();
    Rotation2d gyroRotation = new Rotation2d();

    public DriveSubsystem(CommandXboxController controller) {
        super();
        this.controller = controller;
        modules = new SwerveModule[Constants.CONFIGS.length];
        Translation2d[] modulePositionOnRobot = new Translation2d[modules.length];
        modulePositions = new SwerveModulePosition[modules.length];
        moduleStates = new SwerveModuleState[modules.length];
        steerMotors = new MotorInterface[modules.length];
        driveMotors = new MotorInterface[modules.length];
        moduleNames = new String[modules.length];
        for(int i = 0; i < modules.length; i++) {
            modules[i] = new SwerveModule(Constants.CONFIGS[i]);
            modulePositionOnRobot[i] = modules[i].config.positionRelativeToRobotCenter;
            moduleStates[i] = modules[i].state;
            modulePositions[i] = modules[i].position;
            steerMotors[i] = modules[i].steerMotor();
            driveMotors[i] = modules[i].driveMotor();
            moduleNames[i] = Constants.CONFIGS[i].name;
        }
        kinematics = new SwerveDriveKinematics(modulePositionOnRobot);
        udiKinematics1 = new UdiKinematics1(modulePositionOnRobot);
        gyro = new Pigeon2(Constants.GYRO_ID, Constants.GYRO_CANBUS);
        gyroSignal = gyro.getYaw();
        poseEstimator = new SwerveDrivePoseEstimator(kinematics, getGyroRotation(), modulePositions,new Pose2d());
        pose = new Pose2d();
        pose.set(poseEstimator.getEstimatedPosition());
        robotField = new Field2d();
        SmartDashboard.putData("Drive", this);
        SmartDashboard.putData("Robot Position", robotField);
        SmartDashboard.putData("Set Drive Brake", new InstantCommand(()-> {for(SwerveModule m : modules) m.setBrake();}).ignoringDisable(true));
        SmartDashboard.putData("Set Drive Coast", new InstantCommand(()-> {for(SwerveModule m : modules) m.setCoast();}).ignoringDisable(true));
        SmartDashboard.putData("Reset Heading", new InstantCommand(this::setFieldHeading).ignoringDisable(true));
        controller.start().onTrue(new InstantCommand(this::setFieldHeading).ignoringDisable(true));
        setDefaultCommand(new RunCommand(this::drive, this));
        showBaseCommands();
        SwerveLogEntry.add(moduleNames, moduleStates, modulePositions, pose, currentChassisSpeeds, targetChassisSpeeds);
    }

    private void showBaseCommands() {
        MotorCommands.showRandomPowerCommand("Steers Random Power", -0.6, 0.6, 0.3, this, steerMotors);
        MotorCommands.showRandomPowerCommand("Drives Random Power", -0.9, 0.9, 0.2, this, driveMotors);
        MotorCommands.showSlowPowerCommand("Steers Slow Power", 0.05, 0.01, 1, this, steerMotors);
        MotorCommands.showSlowPowerCommand("Drives Slow Power", 0.03, 0.01, 1, this, driveMotors);
        MotorCommands.showMotionCommand("Set Steer Angle",this, steerMotors);
        MotorCommands.showVelocityCommand("Set Drive Velocity",this, driveMotors);

    }

    private void drive() {
        targetChassisSpeeds.vxMetersPerSecond = DriverUtils.getJSvalue(controller, JoystickSide.RightY) * Constants.MAX_SPEED;
        targetChassisSpeeds.vyMetersPerSecond = -DriverUtils.getJSvalue(controller, JoystickSide.RightX) * Constants.MAX_SPEED;
        targetChassisSpeeds.omegaRadiansPerSecond = DriverUtils.getTriggerValue(controller) * Constants.MAX_OMEGA;
        setSpeeds(targetChassisSpeeds);
    }

    public void updateVisionPosition(Pose2d pose, double time) {
        poseEstimator.addVisionMeasurement(pose, time);
    }
    
    public void setFieldHeading() {
        resetPose(pose.getTranslation(), Rotation2d.kZero);
    }
    public Rotation2d getGyroRotation() {
        gyroSignal.refresh();
        gyroRotation.set(gyroSignal.getValue().in(Radians));
        return gyroRotation;
    }

    public double getGyroHeading() {
        return getGyroRotation().getDegrees();
    }

    public Rotation2d getHeadingRotation() {
        return pose.getRotation();
    }

    public double getHeading() {
        return getHeadingRotation().getDegrees();
    }

    public void resetPose(Translation2d translation2d, Rotation2d rotation2d) {
        poseEstimator.resetPose(new Pose2d(translation2d, rotation2d));
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
            double newX = currentX + Utilities.clamp(speeds.vxMetersPerSecond-currentX, Constants.MAX_X_VELOCITY_CHANGE);
            double ratio = Math.abs(newX / speeds.vxMetersPerSecond);
            speeds.vxMetersPerSecond *= ratio;
            speeds.vyMetersPerSecond *= ratio;
        } 
        if(Math.abs(speeds.vyMetersPerSecond)  > 0.1) {
            double currentY = currentChassisSpeeds.vyMetersPerSecond;
            double newY = currentY + Utilities.clamp(speeds.vyMetersPerSecond-currentY, Constants.MAX_Y_VELOCITY_CHANGE);
            double ratio = Math.abs(newY / speeds.vxMetersPerSecond);
            speeds.vxMetersPerSecond *= ratio;
            speeds.vyMetersPerSecond *= ratio;
        }
    }

    public Command getTestCommand() {
        return new InstantCommand(()->resetPose(new Translation2d(0,6),new Rotation2d(0))).andThen(
            new DriveTo(4, 6, 1000, 2, 1, 90, this, false),
            new DriveTo(6, 3, 2, -1000, -1, 90, this, false),
            new DriveTo(2, 3, 2, 1000,  -1, 90, this, false),
            new DriveTo(0, 6, 0, 2, 1, 90, this, true));
    }

    @Override
    public void periodic() {
        super.periodic();
        for(SwerveModule m : modules) {
            m.refreshStateAndPosition();
        }
        ChassisSpeeds t = kinematics.toChassisSpeeds(moduleStates);
        currentChassisSpeeds.vxMetersPerSecond = t.vxMetersPerSecond;
        currentChassisSpeeds.vyMetersPerSecond = t.vyMetersPerSecond;
        currentChassisSpeeds.omegaRadiansPerSecond = t.omegaRadiansPerSecond;
        poseEstimator.update(getGyroRotation(), modulePositions);
        pose.set(poseEstimator.getEstimatedPosition());
        robotField.setRobotPose(pose);

    }

    @Override
    public void initSendable(SendableBuilder builder) {
        super.initSendable(builder);
        builder.addDoubleProperty("Heading", this::getHeading, null);
        builder.addDoubleProperty("Vx", ()->currentChassisSpeeds.vxMetersPerSecond, null);
        builder.addDoubleProperty("Vy", ()->currentChassisSpeeds.vyMetersPerSecond, null);
        builder.addDoubleProperty("Omega Rad Per Sec", ()->currentChassisSpeeds.omegaRadiansPerSecond, null);
    }

}
