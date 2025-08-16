// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.robot.commands.Drive;
import frc.robot.commands.GoToAngle;
import frc.robot.commands.KsAkvCalc;
import frc.robot.commands.VelocityLoopcontrol;
import frc.robot.subsystems.MyFirstSubsystem;
import frc.robot.subsystems.PizzaMotor;
import frc.robot.subsystems.DemaciaMotorExample;
import edu.wpi.first.wpilibj2.command.Command;

public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  private final MyFirstSubsystem subsystem = new MyFirstSubsystem();
  private final PizzaMotor PizzaMotor = new PizzaMotor();
 //private final Command Velocity1 = new VelocityLoopcontrol(subsystem, 1, 0.001, -0.1, 1.3, 0.0, 0.0);
 //private final Command Velocity2= new VelocityLoopcontrol(subsystem, 2, 0.001, -0.1, 1.3, 0.0, 0.0);
 //private final Command Velocity_1 = new VelocityLoopcontrol(subsystem, -1, 0.001, -0.1, 1.3, 0.0, 0.0);
 //private final Command Velocity_2 = new VelocityLoopcontrol(subsystem, -2, 0.001, -0.1, 1.3, 0.0, 0.0);
  private final Command drive1 = new Drive(subsystem, 100, 0.01, 0.0, 0.0);
  private final Command Pizza = new KsAkvCalc(subsystem, 5);
  private final Command Pizza2 = new VelocityLoopcontrol(PizzaMotor, 2.0);
  
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
  }

  public Command getAutonomousCommand() {
    // An example command will be run in autonomous
    
    return Pizza2;

    

  }
}