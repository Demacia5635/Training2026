// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.robot.Drive.Path;
import frc.robot.subsystems.DemaciaMotorExample;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.wpilibj2.command.Command;

public class RobotContainer {

  public static Robot robot;
  public static int N_CYCLE = 0;
  public static double CYCLE_TIME = 0.02;

  public DemaciaMotorExample demaciaMotorExample = new DemaciaMotorExample();
  public Path path = new Path(new Pose2d(0,6,Rotation2d.kZero),new Pose2d(0,6, Rotation2d.kZero), 
        new Translation2d[]{new Translation2d(4,6), new Translation2d(6,3), new Translation2d(2,3)},
        1, 2, 90, 90, 6);

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
