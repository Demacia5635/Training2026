package frc.robot.subsystems;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.Demacia.utils.Motors.SparkMotor;
import frc.Demacia.utils.Motors.SparkConfig;
import frc.robot.Constants;

public class SparkMotorSubsystem extends SubsystemBase {

    private final SparkMotor motor;
    private final SparkMotorSubsystem sparkMotorSubsystem = new SparkMotorSubsystem();

    public SparkMotorSubsystem() {
        // יוצרים קונפיג בסיסי ממחלקת Constants
        SparkConfig config = Constants.BaseConfigs.BASE_SPARK_CONFIG.copy();
        config.id = Constants.MyFirstSubsystemConstants.MOTOR_ID;
        config.name = "MySparkMotor";
        config.isRadiansMotor = false;
        config.isDegreesMotor = true;
        config.motorRatio = 1.0; // שים יחס מתאים
        

        // יוצרים את המנוע עם הקונפיג
        motor = new SparkMotor(config);
    }

    // פונקציית שליטה חכמה - לדוגמה ל־Command
    public void setVelocitySmart(double velocity) {
        motor.setVelocity(velocity);
    }

    public void setAngleSmart(double angle) {
        motor.setAngle(angle);
    }

    public void stop() {
        motor.setDuty(0);
    }

    @Override
    public void periodic() {
        SmartDashboard.putNumber("SparkMotor/Velocity", motor.getCurrentVelocity());
        SmartDashboard.putNumber("SparkMotor/Setpoint", motor.getCurrentClosedLoopSP());
        SmartDashboard.putNumber("SparkMotor/Error", motor.getCurrentClosedLoopError());
        SmartDashboard.putNumber("SparkMotor/Voltage", motor.getCurrentVoltage());
        SmartDashboard.putNumber("SparkMotor/Current", motor.getCurrentCurrent());
        SmartDashboard.putString("SparkMotor/ControlMode", motor.getCurrentControlMode());

        // אפשרות: הגנת זרם
        if (motor.getCurrentCurrent() > 80) {
            System.out.println("⚠️ זרם גבוה! עוצר מנוע.");
            stop();
        }
    }

    // גישה למנוע – אם צריך לדוגמה ל־SysId
    public SparkMotor getMotor() {
        return motor;
    }

    public void setSmartVelocity(double desiredVelocity) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setSmartVelocity'");
    }
}
