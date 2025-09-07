package frc.robot.subsystems;

import com.ctre.phoenix6.hardware.TalonFX;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.controller.SimpleMotorFeedforward;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.kinematics.SwerveModuleState;
import edu.wpi.first.util.sendable.SendableBuilder;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import com.ctre.phoenix6.hardware.TalonFX;

public class SwerveModule extends SubsystemBase {
    TalonFX DriveMotor;
    TalonFX SteerMotor;
    String name;
    private PIDController SteerController;
    private PIDController DriveController;
    private SimpleMotorFeedforward FF;
    Encoder encoder;
    public SwerveModule(String name){
        super();
        this.name=name;
        DriveMotor = new TalonFX(Constants.MyFirstSubsystemConstants.Drive_MOTOR_ID,Constants.MyFirstSubsystemConstants.MOTOR_CAN);
        SteerMotor = new TalonFX(Constants.MyFirstSubsystemConstants.steer_Motor_ID, Constants.MyFirstSubsystemConstants.MOTOR_CAN);
        SteerController = new PIDController(Constants.MyFirstSubsystemConstants.Kp,Constants.MyFirstSubsystemConstants.Ki, Constants.MyFirstSubsystemConstants.Kd);
        DriveController = new PIDController(Constants.MyFirstSubsystemConstants.kp2,Constants.MyFirstSubsystemConstants.ki2, Constants.MyFirstSubsystemConstants.kd2);
        FF = new SimpleMotorFeedforward(Constants.MyFirstSubsystemConstants.kS, Constants.MyFirstSubsystemConstants.kV);
        encoder = new Encoder(0, 1);

        SmartDashboard.putData("SwerveModule", this);
    }
    public void SetPositionSteer(double Angle){
        SteerMotor.setPosition(Angle);
    }

    public void setPowerDriveMotor(double power){
        DriveMotor.set(power);
        DriveController.setTolerance(0.1);
        DriveController.setIntegratorRange(-0.1, 0.1);
        SteerController.setTolerance(1.0);
        SteerController.setIntegratorRange(-1, 1);
    }
    public void setPowerSteerMotor(double power){
        SteerMotor.set(power);
        SteerController.setTolerance(1.0);
        SteerController.setIntegratorRange(-1, 1);
    }
    public double GetPositionOfSteer(){
        return ((SteerMotor.getPosition().getValueAsDouble()/Constants.MyFirstSubsystemConstants.Deegree_ratio)*360)%360;
    }
    public void Stop() {
        setPowerDriveMotor(0.0);
        setPowerSteerMotor(0.0);
    }
    public double GetVelocityDrive() {
        return ((DriveMotor.getVelocity().getValueAsDouble())/Constants.MyFirstSubsystemConstants.Drive_ratio)*Constants.MyFirstSubsystemConstants.Drive_Radios_In_Meter*2*Math.PI;
    }
    public double getPositionOfDrive(){
        return ((DriveMotor.getPosition().getValueAsDouble())/Constants.MyFirstSubsystemConstants.Drive_ratio)*Constants.MyFirstSubsystemConstants.Drive_Radios_In_Meter*2*Math.PI;
    }
    public double GetVelocitySteer(){
        return ((SteerMotor.getVelocity().getValueAsDouble())/Constants.MyFirstSubsystemConstants.Deegree_ratio)*360;
    }
    public void setVeloctyDrive(double TargetSpeed){
        double currentSpeed = GetVelocityDrive();
        double power = DriveController.calculate(currentSpeed,TargetSpeed)+FF.calculateWithVelocities(currentSpeed,TargetSpeed);
        setPowerDriveMotor(power);
    }
    public void SetSteerAngle(double targetAngle) {
        double currentAngle = GetPositionOfSteer();
        double turnSpeed = SteerController.calculate(currentAngle, targetAngle);
        setPowerSteerMotor(turnSpeed);

        
    }
    public void SetState(SwerveModuleState state){
        double wantedAngle = state.angle.getDegrees();
        double velocty = state.speedMetersPerSecond;
        setVeloctyDrive(velocty);
        SetSteerAngle(wantedAngle);
    }

        @Override
    public void initSendable(SendableBuilder builder) {
        builder.addDoubleProperty("veloctyDrive(m/sec)", this::GetVelocityDrive, null);
        builder.addDoubleProperty("veloctySteer(degrre/sec)", this::GetVelocitySteer, null);
        builder.addDoubleProperty("degree", this::GetPositionOfSteer, null);
        builder.addDoubleProperty("position m", this::getPositionOfDrive, null);

    }


}
