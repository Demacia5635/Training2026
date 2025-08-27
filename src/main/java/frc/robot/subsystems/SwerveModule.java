package frc.robot.subsystems;

import com.ctre.phoenix6.hardware.CANcoder;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.NeutralModeValue;
import com.ctre.phoenix6.configs.TalonFXConfiguration;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class SwerveModule{
    private final TalonFX driveMotor;
    private final TalonFX steerMotor;
    private final CANcoder absCaNcoder;

    public SwerveModule(int driveMotorID, int steerMotorID, int CANcoderID, double CANcoderOfset) {
        driveMotor = new TalonFX(driveMotorID);
        steerMotor = new TalonFX(steerMotorID);
        absCaNcoder = new CANcoder(CANcoderID);

        

        TalonFXConfiguration config = new TalonFXConfiguration();
        config.MotorOutput.NeutralMode = NeutralModeValue.Brake;

        driveMotor.getConfigurator().apply(config);
        steerMotor.getConfigurator().apply(config);

        resetToAbsolute(CANcoderOfset);

    }
    public double getabsolotangle(){
        return absCaNcoder.getAbsolutePosition().getValueAsDouble();
    }
    public void setDriveSpeed(double speed) {
        driveMotor.set(speed);
    }

    public void setSteerAngle(double angle) {
        steerMotor.set(angle / 360.0);
    }

    public void resetToAbsolute(double ofset) {
        steerMotor.setPosition(getabsolotangle()-ofset);
        driveMotor.setPosition(0);

    }

    public double getDrivePosition() {
        return driveMotor.getPosition().getValueAsDouble();
    }

    public double getSteerPosition() {
        return steerMotor.getPosition().getValueAsDouble();
    }

    public void logToDashboard(String moduleName) {
        SmartDashboard.putNumber(moduleName + " Drive Position", getDrivePosition());
        SmartDashboard.putNumber(moduleName + " Steer Position", getSteerPosition());
    }
}
