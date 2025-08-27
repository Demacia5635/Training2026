// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.util.sendable.SendableBuilder;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import frc.Demacia.utils.Motors.TalonMotor;
import frc.robot.subsystems.MyFirstSubsystem;
import frc.robot.subsystems.TestUtils;

/* You should consider using the more terse Command factories API instead https://docs.wpilib.org/en/stable/docs/software/commandbased/organizing-command-based.html#defining-commands */
public class MoveInSameVelocityWithFF extends Command {
  /** Creates a new MoveInSameVelocityWithFF. */
  private TestUtils sub4;
  public MoveInSameVelocityWithFF(TestUtils subsystem1) {
    super();
    this.sub4=subsystem1;
    addRequirements(subsystem1);

      // Use addRequirements() here to declare subsystem dependencies.
  }
  public MoveInSameVelocityWithFF(MyFirstSubsystem subsystem1) {
    //TODO Auto-generated constructor stub
}
// Called when the command is initially scheduled.
  @Override
  public void initialize() {
    
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    sub4.ffWithpid(10);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    sub4.setPow(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
