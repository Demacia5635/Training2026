// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.
package frc.robot.commands;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants;
import frc.robot.subsystems.Chassis;

public class Drive extends Command {
  private final Chassis chassis;
  private final XboxController controller;
  private final boolean fieldRelative;

  public Drive(Chassis chassis, XboxController controller, boolean fieldRelative) {
    this.chassis = chassis;
    this.controller = controller;
    this.fieldRelative = fieldRelative;
    addRequirements(chassis);
  }

  private static double deadband(double v) {
    return MathUtil.applyDeadband(v, Constants.Swerve.DEADBAND);
  }

  @Override
  public void execute() {
    double fwd = -deadband(controller.getLeftY());
    double str = +deadband(controller.getLeftX());
    double rot = +deadband(controller.getRightX());

    double vx = fwd * Constants.Swerve.MAX_SPEED_MPS;
    double vy = str * Constants.Swerve.MAX_SPEED_MPS;
    double omega = rot * Constants.Swerve.MAX_ANGULAR_SPEED_RAD_PER_S;

    chassis.drive(new ChassisSpeeds(vx, vy, omega), fieldRelative);
  }

  @Override
  public void end(boolean interrupted) {
    chassis.stop();

  }
  @Override
  public boolean isFinished() {
    return false;
  }
}
