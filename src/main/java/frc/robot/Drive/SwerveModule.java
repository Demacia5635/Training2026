package frc.robot.Drive;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.math.kinematics.SwerveModulePosition;
import edu.wpi.first.math.kinematics.SwerveModuleState;
import edu.wpi.first.util.sendable.Sendable;
import edu.wpi.first.util.sendable.SendableBuilder;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Subsystem;
import frc.Demacia.utils.Motors.MotorCommands;
import frc.Demacia.utils.Motors.MotorInterface;
import frc.Demacia.utils.Motors.TalonMotor;
import frc.Demacia.utils.Sensors.Cancoder;

public class SwerveModule implements Sendable {
    private MotorInterface steer;
    private MotorInterface drive;
    private Cancoder absEncoder;
    protected Constants.ModuleConfig config;
    protected SwerveModuleState state = new SwerveModuleState();
    protected SwerveModulePosition position = new SwerveModulePosition();
    private double lastSteerPosition = 0;
    private double steerTargetDiff = 0;
    private double driveTarget = 0;


    SwerveModule(Constants.ModuleConfig config) {
        this.config = config;
        steer = new TalonMotor(config.steerConfig);
        drive = new TalonMotor(config.driveConfig);
        absEncoder = new Cancoder(config.cancoderConfig);
        setSteerOffset();
        refreshPosition();
        refreshState();
        SmartDashboard.putData(config.name, this);
    }

    public void setSteerOffset() {
        steer.setEncoderPosition(getAbsEncoder()-config.cancoderOffset);
    }

    public double getAbsEncoder() {
        return absEncoder.getCurrentAbsPosition();
    }

    public void refreshState() {
        state.angle.setDegrees(steer.getCurrentPosition());
        state.speedMetersPerSecond = drive.getCurrentVelocity();
    }

    public void refreshPosition() {
        double steerPosition = steer.getCurrentPosition();
        position.angle.setDegrees((lastSteerPosition + steerPosition)/2);
        lastSteerPosition = steerPosition;
        position.distanceMeters = drive.getCurrentPosition() + steerPosition * Constants.STEER_TO_DISTANCE_RATIO;
    }

    private void optimaizeTarget() {
        if(steerTargetDiff > 90) {
            steerTargetDiff -= 180;
            driveTarget = -driveTarget;
        } else if(steerTargetDiff < -90) {
            steerTargetDiff += 180;
            driveTarget = -driveTarget;
        }
    }

    public void setState(SwerveModuleState state) {
        double currentPosition = steer.getCurrentPosition();
        steerTargetDiff = MathUtil.inputModulus(state.angle.getDegrees() - currentPosition,-180,180);
        driveTarget = state.speedMetersPerSecond;
        optimaizeTarget();
        steerTargetDiff = (MathUtil.clamp(steerTargetDiff * Constants.STATE_STEER_ADDITION, steerTargetDiff-Constants.MAX_SET_STATE_STEER_ADDITION, steerTargetDiff+Constants.MAX_SET_STATE_STEER_ADDITION));
        optimaizeTarget();
        steer.setMotion(currentPosition + steerTargetDiff);
        drive.setVelocity(driveTarget);
    }

    public void setSteerPower(double power) {
        steer.setDuty(power);
    }
    public void setDrivePower(double power) {
        drive.setDuty(power);
    }
    public void setSteerAngle(double angle) {
        steer.setMotion(angle);
    }
    public void setDriveVelocity(double velocity) {
        drive.setVelocity(velocity);
    }

    public void showConfigPID() {
        steer.showConfigPIDFSlotCommand(0);
        drive.showConfigPIDFSlotCommand(0);
        steer.showConfigMotionVelocitiesCommand();
        drive.showConfigMotionVelocitiesCommand();
    }

    public void showBaseCommands(Subsystem subsystem) {
        MotorCommands.showRandomPowerCommand(config.name + " Steer Random Power",-0.6, 0.6, 0.2, subsystem, steer);
        MotorCommands.showRandomPowerCommand(config.name + " Drive Random Power",-1, 1, 0.2, subsystem, drive);
        MotorCommands.showSlowPowerCommand(config.name + " Steer Slow Power",0.07, 0.01, 1, subsystem, steer);
        MotorCommands.showSlowPowerCommand(config.name + " Drive Slow Power",0.01, 0.01, 1, subsystem, drive);
        MotorCommands.showMotionCommand(config.name + " Steer Angle",subsystem, steer);
        MotorCommands.showVelocityCommand(config.name + " Drive Velocity",subsystem, drive);
    }

    protected MotorInterface steerMotor() {
        return steer;
    }
    protected MotorInterface driveMotor() {
        return drive;
    }

    public void setBrake() {
        steer.setNeutralMode(true);
        drive.setNeutralMode(true);
    }
    public void setCoast() {
        steer.setNeutralMode(false);
        drive.setNeutralMode(false);
    }

    @Override
    public void initSendable(SendableBuilder builder) {
        builder.addDoubleProperty("AbsEncoder",this::getAbsEncoder, null);
    }
}
