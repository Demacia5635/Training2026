// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.math.estimator.SwerveDrivePoseEstimator;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.utils;
import frc.robot.subsystems.SwervePizza;

/* You should consider using the more terse Command factories API instead https://docs.wpilib.org/en/stable/docs/software/commandbased/organizing-command-based.html#defining-commands */
public class DriveController extends Command {
  /** Creates a new DriveController. */
  private final SwervePizza subsystem;
  private double steerTarget;
  private final utils utils;
  
  public DriveController(SwervePizza subsystem) {
    // Use addRequirements() here to declare subsystem dependencies.
this.subsystem = subsystem;
this.steerTarget = 0;
this.utils = new utils();
addRequirements(subsystem);


   
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    double pastAngle = steerTarget;
    double velocitypizza = utils.driveparameter();
    subsystem.pizza.driveSetvelocity(velocitypizza);
    double steerTarget = utils.pizzasteerparmeter(pastAngle);
    subsystem.pizza.setPowerForPosition(steerTarget);
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
