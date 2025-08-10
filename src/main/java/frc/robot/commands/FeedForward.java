// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.controller.SimpleMotorFeedforward;
import edu.wpi.first.util.sendable.SendableBuilder;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants;
import frc.robot.subsystems.Modle;

/* You should consider using the more terse Command factories API instead https://docs.wpilib.org/en/stable/docs/software/commandbased/organizing-command-based.html#defining-commands */
public class FeedForward extends Command {
   private SimpleMotorFeedforward feedForward;
   private double currentVelocity;
   private double targetVelocity;
   private Modle modle;
   private PIDController theSecondPid;
 

  /** Creates a new FeedForward. */
  public FeedForward(Modle modle) {
    this.modle = modle;
    feedForward = new SimpleMotorFeedforward(Constants.KS,Constants.KV );
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    double pidCaculate1 = theSecondPid.calculate(currentVelocity, targetVelocity);
    currentVelocity = modle.getMotorVelocity();
    double feedforwardOutput = feedForward.calculateWithVelocities(currentVelocity, targetVelocity);
    modle.setDrivePower(feedforwardOutput+pidCaculate1);

  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
  public void initSendable(SendableBuilder builder){
    builder.addDoubleProperty("wanted velocity",this::getTargetVelocity,this::setTargetVelocity);
  }
  public double getTargetVelocity( ){
    return targetVelocity;
  }
  public void setTargetVelocity(double targetVelocity){
    this.targetVelocity = targetVelocity;
  }
}