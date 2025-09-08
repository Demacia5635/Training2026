// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.
package frc.robot;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.commands.Drive;
import frc.robot.subsystems.Chassis;

public class RobotContainer {
  public static final int N_CYCLE = 0;
  public RobotContainer(Robot robot) {}
  private final Chassis chassis = new Chassis();
  private final XboxController driverController = new XboxController(Constants.OperatorConstants.kDriverControllerPort);
  private final Command driveCommand = new Drive(chassis, driverController, true);

  public RobotContainer() {
    chassis.setDefaultCommand(driveCommand);
    configureBindings();
  }
  public void periodic() { 
    
    
  }

  private void configureBindings() {
    
    new Trigger(() -> driverController.getAButton())
        .onTrue(new InstantCommand(() -> chassis.zeroHeadingToField(), chassis));
  }

  public Command getAutonomousCommand() {
    return null;
  }
}



  
