package frc.robot.subsystems;

<<<<<<< HEAD
import com.ctre.phoenix6.hardware.CANcoder;
=======
>>>>>>> d628868 (LastHomeWork)
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.NeutralModeValue;
import com.ctre.phoenix6.configs.TalonFXConfiguration;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

<<<<<<< HEAD
public class SwerveModule{
    private final TalonFX driveMotor;
    private final TalonFX steerMotor;
    private final CANcoder absCaNcoder;

    public SwerveModule(int driveMotorID, int steerMotorID, int CANcoderID, double CANcoderOfset) {
        driveMotor = new TalonFX(driveMotorID);
        steerMotor = new TalonFX(steerMotorID);
        absCaNcoder = new CANcoder(CANcoderID);

        
=======
public class SwerveModule {
    private final TalonFX driveMotor;
    private final TalonFX steerMotor;

    public SwerveModule(int driveMotorID, int steerMotorID) {
        driveMotor = new TalonFX(driveMotorID);
        steerMotor = new TalonFX(steerMotorID);
>>>>>>> d628868 (LastHomeWork)

        TalonFXConfiguration config = new TalonFXConfiguration();
        config.MotorOutput.NeutralMode = NeutralModeValue.Brake;

        driveMotor.getConfigurator().apply(config);
        steerMotor.getConfigurator().apply(config);

<<<<<<< HEAD
        resetToAbsolute(CANcoderOfset);

    }
    public double getabsolotangle(){
        return absCaNcoder.getAbsolutePosition().getValueAsDouble();
    }
=======
        resetToAbsolute();
    }

>>>>>>> d628868 (LastHomeWork)
    public void setDriveSpeed(double speed) {
        driveMotor.set(speed);
    }

    public void setSteerAngle(double angle) {
        steerMotor.set(angle / 360.0);
    }

<<<<<<< HEAD
    public void resetToAbsolute(double ofset) {
        steerMotor.setPosition(getabsolotangle()-ofset);
        driveMotor.setPosition(0);

=======
    public void resetToAbsolute() {
        steerMotor.setPosition(0);
        driveMotor.setPosition(0);
>>>>>>> d628868 (LastHomeWork)
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
