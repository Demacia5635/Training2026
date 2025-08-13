package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.subsystems.MyFirstSubsystem;

public class PrintStatusCommand extends InstantCommand {
  private MyFirstSubsystem sub;

  public PrintStatusCommand(MyFirstSubsystem sub) {
    this.sub = sub;
  }

  @Override
  public void initialize() {
    System.out.println("=== Motor Status ===");
    System.out.println("Angle: " + sub.spinToAngle());
    System.out.println("Position: " + sub.moveToMeter());
    System.out.println("Velocity Steer: " + sub.getSteerVelocity());
    System.out.println("Velocity Drive: " + sub.getDriveVelocity());
  }
}


