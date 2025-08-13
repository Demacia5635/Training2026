// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.robot.commands.MoveWithPID;
import frc.robot.commands.MyFirstSubsystemCommand;
import frc.robot.subsystems.MyFirstSubsystem;
import edu.wpi.first.wpilibj2.command.Command;

public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  private final MyFirstSubsystem subsystem = new MyFirstSubsystem();
  private final Command autoCommand = new MyFirstSubsystemCommand(subsystem, 0.1, 0.1,  11.0);
  private final MoveWithPID cmd = new MoveWithPID(subsystem);
  

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
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
    
    return cmd;
  }
  
}
