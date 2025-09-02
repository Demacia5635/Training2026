// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.robot.commands.drivecommand;
import frc.robot.subsystems.Chassis;
import frc.robot.subsystems.DemaciaMotorExample;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;

public class RobotContainer {

  public static Robot robot;
  public static int N_CYCLE = 0;
  public static double CYCLE_TIME = 0.02;
  public static XboxController controller = new XboxController(0);
  public static Chassis chassis = new Chassis();
  public static drivecommand drivecommand = new drivecommand(chassis, controller);

  public DemaciaMotorExample demaciaMotorExample = new DemaciaMotorExample();

  public RobotContainer(Robot robot) {
    RobotContainer.robot = robot;
    RobotContainer.CYCLE_TIME = robot.getPeriod();
    configureBindings();
  }
   private void configureBindings() {
  }

  public static boolean isEnabled() {
    return robot.isEnabled();
  }

  public void periodic() {
    N_CYCLE++;
  }

  public Command getAutonomousCommand() {
    return null;
  }
}
