// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.robot.commands.MoveDriveMotor1Meter;
import frc.robot.commands.MoveInSameVelocity;
import frc.robot.commands.MoveToAngle;
import frc.robot.commands.MyFirstSubsystemCommand;
import frc.robot.subsystems.MyFirstSubsystem;
import edu.wpi.first.wpilibj2.command.Command;

public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  private MyFirstSubsystem subsystem1;
  // private final Command autoCommand = new MyFirstSubsystemCommand(subsystem1, 0.4,0.3,  10.0);
  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  MoveToAngle SteerMotorcmd;
  MoveDriveMotor1Meter DriveMotorcmd;
  MoveInSameVelocity MovingInSameVelocity;
  public RobotContainer() {
    subsystem1 = new MyFirstSubsystem();
    DriveMotorcmd = new MoveDriveMotor1Meter(subsystem1);
    SteerMotorcmd = new MoveToAngle(subsystem1);
    MovingInSameVelocity = new MoveInSameVelocity(subsystem1);
    configureBindings();
  }

  /**
   * Use this method to define your trigger->command mappings. Triggers can be created via the
   * {@link Trigger#Trigger(java.util.function.BooleanSupplier)} constructor with an arbitrary
   * predicate, or via the named factories in {@link
   * edu.wpi.first.wpilibj2.command.button.CommandGenericHID}'s subclasses for {@link
   * CommandXboxController Xbox}/{@link edu.wpi.first.wpilibj2.command.button.CommandPS4Controller
   * PS4} controllers or {@link edu.wpi.first.wpilibj2.command.button.CommandJoystick Flight
   * joysticks}.
   */
  private void configureBindings() {
    //controller.a().onTrue(new MyFirstSubsystemCommand(subsystem, 0.5, 2.0));
  }

  public void periodic() {
    N_CYCLE++;
  }

  public Command getAutonomousCommand() {
    // An example command will be run in autonomous
    
    // return DriveMotorcmd.andThen(SteerMotorcmd);
    //return MovingInSameVelocity;
    return SteerMotorcmd;
  }
}
