package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.util.sendable.SendableBuilder;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import com.ctre.phoenix6.hardware.TalonFX;

public class MoveSubsystem extends SubsystemBase {
    TalonFX motor;
 public MoveSubsystem(){
    super();
    motor = new TalonFX(Constants.MyFirstSubsystemConstants.MOTOR_IDX, Constants.MyFirstSubsystemConstants.MOTOR_CAN);
 }
 public void setPower(double power) {
    motor.set(power);
 }
 public void Stop(){
    setPower(0.0);
 }

 public double GetVelocity(){
    return motor.getVelocity().getValueAsDouble();
 }
 public double GetPosition(){
    return motor.getPosition().getValueAsDouble();
 }
 @Override
 public void initSendable(SendableBuilder builder){
    builder.addDoubleProperty("motor x pos(m)", this::GetPosition, null);
    builder.addDoubleProperty("motor x v(m/s)", this::GetVelocity, null);
 }
 public void periodic() {
    //   SmartDashboard.putNumber("motor x pos(m)", Double.parseDouble(motorx.getPosition().toString()));
    //   SmartDashboard.putNumber("motor y pos(m)", Double.parseDouble(motory.getPosition().toString()));
    //   SmartDashboard.putNumber("motor x v(m/s)", Double.parseDouble(motorx.getVelocity().toString()));
    //   SmartDashboard.putNumber("motor y v(m/s)", Double.parseDouble(motory.getVelocity().toString()));
    SmartDashboard.putData("MoveSubsystem",this);
    }

}
