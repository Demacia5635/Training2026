// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.controller.SimpleMotorFeedforward;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.MyFirstSubsystem;

/* You should consider using the more terse Command factories API instead https://docs.wpilib.org/en/stable/docs/software/commandbased/organizing-command-based.html#defining-commands */
public class VelocityLoopControl extends Command {
  private final double kp;
  private final double ks;
  private final double kv;
  private final double ka;
  private final double kg;
  private final double targetV;
  private final MyFirstSubsystem subsystem;
  
  final SimpleMotorFeedforward feedforward;
  final PIDController pidController;
  
  /** Creates a new VelocityLoopcontrol. */
  public VelocityLoopControl(MyFirstSubsystem subsystem, double targetV,double kp, double ks, double kv, double ka, double kg) {
    this.kp = kp;
    this.ks = ks;
    this.kv = kv;
    this.ka = ka;
    this.kg = kg;
    this.targetV = targetV;
  
    this.feedforward =  new SimpleMotorFeedforward(ks,kv,ka);
    this.pidController = new PIDController(kp, 0.0, 0.0);
    this.subsystem = subsystem;
    addRequirements(subsystem);
    SmartDashboard.putNumber("target velocity", targetV);
    
    

    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    double currentVelocity = subsystem.DgetSpeedinMpSpizza();
        double feedforwardOutput = feedforward.calculateWithVelocities(currentVelocity, targetV);
        double pidOutput = pidController.calculate(currentVelocity, targetV);
        double totalOutput = feedforwardOutput + pidOutput;        
        subsystem.setVoltage(totalOutput);
        SmartDashboard.putNumber("target velocity", targetV);
    

  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
