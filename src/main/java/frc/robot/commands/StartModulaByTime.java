package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Modula;

public class StartModulaByTime extends Command {
  private final Modula subsystem;
  private final double drivePower;
  private final double steerPower;
  private final double duration;
  private double startTime; 

  public StartModulaByTime (Modula subsystem, double drivePower, double steerPower, double duration) {
    this.subsystem = subsystem;
    this.drivePower = drivePower;
    this.steerPower = steerPower;
    this.duration = duration;
    addRequirements(subsystem);
  }

  @Override
  public void initialize() {
    startTime = Timer.getFPGATimestamp();
  }

  @Override
  public void execute() {
    subsystem.setPowerToDrive(drivePower);
    subsystem.setPowerToSteer(steerPower);
  }

  @Override
  public boolean isFinished() {
    return Timer.getFPGATimestamp() >= startTime + duration;
  }

  @Override
  public void end(boolean interrupted) {
    subsystem.stopToDrive();
    subsystem.stopToSteer();
  }
}
