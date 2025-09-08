// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.robot.subsystems.DemaciaMotorExample;
import frc.robot.chassis;
import edu.wpi.first.wpilibj2.command.Command;

public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  private final Chassis chassis;
  private final DriveCommand autoCommand;


  
  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    chassis = new Chassis();
    autoCommand = new DriveCommand(chassis);
    chassis.setDefaultCommand(autoCommand);

  public static Robot robot;
  public static int N_CYCLE = 0;
  public static double CYCLE_TIME = 0.02;
  RobotContainer.robot = robot;
  RobotContainer.CYCLE_TIME = robot.getPeriod();
  configureBindings();
  }

  public DemaciaMotorExample demaciaMotorExample = new DemaciaMotorExample();

  
   private void configureBindings() {
  }

  public static boolean isEnabled() {
    return robot.isEnabled();
  }

  public void periodic() {
    N_CYCLE++;
  }

  public Command getAutonomousCommand() {
    // An example command will be run in autonomous
    
    return autoCommand;
  }
}
