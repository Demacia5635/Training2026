package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants;
import frc.robot.subsystems.SteerMotor;
import edu.wpi.first.math.controller.PIDController;
public class SteerWithPID extends Command {

  private final SteerMotor subsystem;
  // PID constants
  private double power;
  private PIDController pidController = new PIDController(Constants.ModuleConstants.STEER_KP, Constants.ModuleConstants.STEER_KI, Constants.ModuleConstants.STEER_KD);
  
  
  public SteerWithPID(SteerMotor subsystem) {
    this.subsystem = subsystem;
    addRequirements(subsystem);
  }
  
  
  @Override
  public void initialize() {}
  
    
  @Override
  public void execute() {
    // PID control logic 
    power = pidController.calculate(subsystem.getPosition(), subsystem.getTargetAngle());

    subsystem.setPower(power);
  }


  @Override
  public void end(boolean interrupted) {
    subsystem.stop();
  }


  @Override
  public boolean isFinished() {
    return false;
  }
}
