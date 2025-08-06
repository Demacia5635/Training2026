package frc.robot.Drive;

import static edu.wpi.first.units.Units.Degrees;

import com.ctre.phoenix6.StatusSignal;
import com.ctre.phoenix6.hardware.CANcoder;
import edu.wpi.first.math.MathUtil;
import edu.wpi.first.math.kinematics.SwerveModulePosition;
import edu.wpi.first.math.kinematics.SwerveModuleState;
import edu.wpi.first.units.measure.Angle;
import edu.wpi.first.util.sendable.Sendable;
import edu.wpi.first.util.sendable.SendableBuilder;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.Demacia.utils.Motors.MotorInterface;
import frc.Demacia.utils.Motors.TalonMotor;

public class SwerveModule implements Sendable {
    private MotorInterface steer;
    private MotorInterface drive;
    private CANcoder absEncoder;
    protected Constants.ModuleConfig config;
    private StatusSignal<Angle> absEncoderSignal;
    protected SwerveModuleState state = new SwerveModuleState();
    protected SwerveModulePosition position = new SwerveModulePosition();
    private double lastSteerPosition = 0;

    

    SwerveModule(Constants.ModuleConfig config) {
        this.config = config;
        steer = new TalonMotor(config.steerConfig);
        drive = new TalonMotor(config.driveConfig);
        absEncoder = new CANcoder(config.cancoderId);
        absEncoderSignal = absEncoder.getAbsolutePosition();
        setSteerOffset();
        refreshPosition();
        refreshState();
        SmartDashboard.putData(config.name, this);
    }

    public void setSteerOffset() {
        steer.setEncoderPosition(getAbsEncoder()-config.cancoderOffset);
    }

    public double getAbsEncoder() {
        return absEncoderSignal.refresh().getValue().in(Degrees);
    }

    public SwerveModuleState refreshState() {
        state.angle.set(Math.toRadians(steer.getCurrentPosition()));
        state.speedMetersPerSecond = drive.getCurrentVelocity();
        return state;
    }

    public SwerveModulePosition refreshPosition() {
        double steerPosition = steer.getCurrentPosition();
        position.angle.set(Math.toRadians(lastSteerPosition + steerPosition)/2);
        lastSteerPosition = steerPosition;
        position.distanceMeters = drive.getCurrentPosition() + steerPosition * Constants.STEER_TO_DISTANCE_RATIO;
        return position;
    }

    public void setState(SwerveModuleState state) {
        double targetAngle = state.angle.getDegrees();
        double targetVelocity = state.speedMetersPerSecond;
        double currentAngle = steer.getCurrentPosition();
        double diff = MathUtil.inputModulus(targetAngle-currentAngle, -180, 180);
        if(diff < -90) {
            diff += 180;
            targetVelocity = -targetVelocity;
        } else if(diff > 90) {
            diff -= 180;
            targetVelocity = -targetVelocity;            
        }
        if(diff > Constants.MAX_SET_STATE_STEER_ADDITION) {
            targetAngle = currentAngle + diff + Constants.MAX_SET_STATE_STEER_ADDITION;
        } else if(diff > -Constants.MAX_SET_STATE_STEER_ADDITION) {
            targetAngle = currentAngle + diff*2;
        } else {
            targetAngle = currentAngle + diff - Constants.MAX_SET_STATE_STEER_ADDITION;
        }
        steer.setMotion(targetAngle);
        drive.setVelocity(targetVelocity);
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
