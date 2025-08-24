package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.util.sendable.SendableBuilder;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import com.ctre.phoenix6.hardware.TalonFX;

public class MoveSubsystem extends SubsystemBase {
    TalonFX motor;
    private PIDController turnController;
    double veloctyTraget;
    public MoveSubsystem() {
        super();
        motor = new TalonFX(Constants.MyFirstSubsystemConstants.Drive_MOTOR_ID,
                Constants.MyFirstSubsystemConstants.MOTOR_CAN);
        turnController = new PIDController(Constants.MyFirstSubsystemConstants.kp2,Constants.MyFirstSubsystemConstants.ki2, Constants.MyFirstSubsystemConstants.kd2);
        veloctyTraget = 20.0;
        SmartDashboard.putData("MoveSubsystem", this);
    }

    public void setPower(double power) {
        motor.set(power);
        turnController.setTolerance(0.1);
        turnController.setIntegratorRange(-10, 10);

    }
    public void Stop() {
        setPower(0.0);
    }

    public double GetVelocity() {
        return motor.getVelocity().getValueAsDouble();
    }
    public double GetVelocityTarget(){
        return veloctyTraget;
    }
    public double calculateSpeed(double TargetSpeed){
        double currentSpeed = GetVelocity();
        return turnController.calculate(currentSpeed,TargetSpeed);
    }

    @Override
    public void initSendable(SendableBuilder builder) {
        builder.addDoubleProperty("velocty", this::GetVelocity, null);
        builder.addDoubleProperty("veloctyTarget", this::GetVelocityTarget, null);
    }

    public void periodic() {
        // SmartDashboard.putNumber("motor x pos(m)",
        // Double.parseDouble(motorx.getPosition().toString()));
        // SmartDashboard.putNumber("motor y pos(m)",
        // Double.parseDouble(motory.getPosition().toString()));
        // SmartDashboard.putNumber("motor x v(m/s)",
        // Double.parseDouble(motorx.getVelocity().toString()));
        // SmartDashboard.putNumber("motor y v(m/s)",
        // Double.parseDouble(motory.getVelocity().toString()));
     
    }
}
