package frc.robot.commands;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Motor;

public class PidTest extends Command{
    
    PIDController pid;
    Motor motor;
    double angle;
    public PidTest(Motor motor) {
  // Constructor logic if needed
    pid = new PIDController(0.1, 0.01, 0.1); // Example PID values
    pid.setTolerance(1);
    pid.enableContinuousInput(-180, 180);

    this.motor = motor;
    }  
 
 
 @Override
 public void initialize() {
    pid.reset();
    pid.setSetpoint(angle);
 }

 @Override
 public void execute() {
    double turnSpeed = pid.calculate(motor.getPosition());
    motor.setPow(turnSpeed);

 }

 @Override
 public void end(boolean interrupted) {
    motor.setPow(0);
 }

 @Override
 public boolean isFinished() {
    return Math.abs(motor.getPosition() - angle) < 1.0;
 }

}
