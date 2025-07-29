package frc.robot.Drive;

import static edu.wpi.first.units.Units.Radians;

import com.ctre.phoenix6.StatusSignal;
import com.ctre.phoenix6.hardware.CANcoder;
import edu.wpi.first.math.MathUtil;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.kinematics.SwerveModulePosition;
import edu.wpi.first.math.kinematics.SwerveModuleState;
import edu.wpi.first.units.measure.Angle;
import frc.robot.utils.TalonMotor;

public class SwerveModule {
    TalonMotor steer;
    TalonMotor drive;
    CANcoder absEncoder;
    Constants.ModuleConfig config;
    StatusSignal<Angle> absEncoderSignal;
    SwerveModuleState state = new SwerveModuleState();
    SwerveModulePosition position = new SwerveModulePosition();

    private static final double STEER_TO_DISTANCE_RATIO = 0.1;

    SwerveModule(Constants.ModuleConfig config) {
        this.config = config;
        steer = new TalonMotor(config.steerConfig);
        drive = new TalonMotor(config.driveConfig);
        absEncoder = new CANcoder(config.cancoderId);
        absEncoderSignal = absEncoder.getAbsolutePosition();
        setSteerOffset();
    }

    public void setSteerOffset() {
        steer.setPosition(getAbsEncoder()-config.cancoderOffset);
    }

    public double getAbsEncoder() {
        return absEncoderSignal.refresh().getValue().in(Radians);
    }

    public SwerveModuleState getState() {
        state.angle = new Rotation2d(steer.getCurrentPosition());
        state.speedMetersPerSecond = drive.getCurrentVelocity();
        return state;
    }

    public SwerveModulePosition getPosition() {
        position.angle = new Rotation2d(steer.getCurrentPosition());
        position.distanceMeters = drive.getCurrentPosition() + steer.getCurrentPosition() * STEER_TO_DISTANCE_RATIO;
        return position;
    }

    public void setState(SwerveModuleState state) {
        double targetAngle = state.angle.getRadians();
        double targetVelocity = state.speedMetersPerSecond;
        double currentAngle = steer.getCurrentPosition();
        double diff = MathUtil.angleModulus(targetAngle-currentAngle);
        if(diff < -Math.PI/2) {
            diff += Math.PI;
            targetVelocity = -targetVelocity;
        } else if(diff < Math.PI) {
            diff -= Math.PI;
            targetVelocity = -targetVelocity;            
        }
        targetAngle = currentAngle + diff;
        steer.setPositionVoltage(targetAngle);
        drive.setVelocity(targetVelocity);
    }

}
