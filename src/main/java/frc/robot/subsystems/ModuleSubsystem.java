package frc.robot.subsystems;

import com.ctre.phoenix6.hardware.CANcoder;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.controller.SimpleMotorFeedforward;
import edu.wpi.first.util.sendable.SendableBuilder;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.utils.SparkConfig;
import frc.robot.utils.SparkMotor;
import static frc.robot.Constants.ModuleConstants.*;

public class ModuleSubsystem extends SubsystemBase {
    SparkMotor steerMotor;
    SparkMotor driveMotor;
    CANcoder absEncoder;

    SimpleMotorFeedforward steerFF = new SimpleMotorFeedforward(STEER_KS, STEER_KV, 0);
    SimpleMotorFeedforward driveFF = new SimpleMotorFeedforward(DRIVE_KS, DRIVE_KV, 0);
    PIDController steerPID = new PIDController(STEER_KP,STEER_KI, STEER_KD);
    PIDController drivePID = new PIDController(DRIVE_KP, DRIVE_KI, DRIVE_KD);

    public ModuleSubsystem() {
        super();
        steerMotor = new SparkMotor(new SparkConfig(STEER_ID,"SteerMotor")
                .withBrake(true)
                .withCurrent(MAX_STEER_AMPS)
                .withInvert(STEER_INVERTED)
                .withRampTime(STEER_RAMP)
                .withVolts(MAX_STEER_VOLTS, 0));
        driveMotor = new SparkMotor(new SparkConfig(DRIVE_ID,"DriveMotor")
                .withBrake(true)
                .withInvert(DRIVE_INVERTED)
                .withRampTime(DRIVE_RAMP));
        absEncoder = new CANcoder(CANBCODER_ID);

        calibrateSteer();
        SmartDashboard.putData("Module", this);
    }

    public double getSteerPower() {
        return steerMotor.getAppliedOutput();
    }
    public double getSteerAngle() {
        return steerMotor.getEncoder().getPosition() * 360 / STEER_GERA_RATIO;
    }
    public double getSteerVelocity() {
        return steerMotor.getEncoder().getVelocity() * 360 / STEER_GERA_RATIO / 60;
    }
    public void setSteerPower(double power) {
        steerMotor.set(power);
    }
    public double getDrivePower() {
        return driveMotor.getAppliedOutput();
    }
    public double getDriveAngle() {
        return driveMotor.getEncoder().getPosition() / DRIVE_GERA_RATIO * WHEEL_CIRCUMFERENCE;
    }
    public double getDriveVelocity() {
        return driveMotor.getEncoder().getVelocity() / DRIVE_GERA_RATIO / 60 * WHEEL_CIRCUMFERENCE;
    }
    public void setDrivePower(double power) {
        driveMotor.set(power);
    }

    public double getAbsAngle() {
        return absEncoder.getAbsolutePosition().getValueAsDouble() * 360;
    }

    private void calibrateSteer() {
        double angle = getAbsAngle() - ABS_ENCODER_OFFSET;
        steerMotor.getEncoder().setPosition(angle / 360 * STEER_GERA_RATIO);
    }

    public void setDriveVelocity(double velocity) {
        double ff = driveFF.calculate(velocity);
        double pid = drivePID.calculate(getDriveVelocity(), velocity);
        setDrivePower(pid + ff);;
    }
    public void setSteerVelocity(double velocity) {
        double ff = steerFF.calculate(velocity);
        double pid = steerPID.calculate(getSteerVelocity(), velocity);
        setSteerPower(pid + ff);;
    }

    public void setSteerAngle(double angle) {
        double velocity = (angle - getSteerAngle()) * STEER_VELOCITY_P;
        setSteerVelocity(velocity);
    }


    @Override
    public void initSendable(SendableBuilder builder) {
        super.initSendable(builder);
        builder.addDoubleProperty("Steer Power", this::getSteerPower, null);
        builder.addDoubleProperty("Steer Angle", this::getSteerAngle, null);
        builder.addDoubleProperty("Steer Velocity", this::getSteerVelocity, null);
        builder.addDoubleProperty("Drive Power", this::getDrivePower, null);
        builder.addDoubleProperty("Drive Position", this::getDriveAngle, null);
        builder.addDoubleProperty("Drive Velocity", this::getDriveVelocity, null);
        builder.addDoubleProperty("Abs Angle", this::getAbsAngle, null);

        SmartDashboard.putNumber("Set Steer Power", 0);
        SmartDashboard.putNumber("Set Drive Power", 0);
        SmartDashboard.putData("Set Steer", new RunCommand(()->setSteerPower(SmartDashboard.getNumber("Set Steer Power", 0)), this));
        SmartDashboard.putData("Set Drive", new RunCommand(()->setDrivePower(SmartDashboard.getNumber("Set Drive Power", 0)), this));
        SmartDashboard.putNumber("Set Steer Angle", 0);
        SmartDashboard.putNumber("Set Drive Velocity", 0);
        SmartDashboard.putData("Set Angle", new RunCommand(()->setSteerAngle(SmartDashboard.getNumber("Set Steer Angle", 0)), this));
        SmartDashboard.putData("Set Velocity", new RunCommand(()->setDriveVelocity(SmartDashboard.getNumber("Set Drive Velocity", 0)), this));
    }



}
