package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Modula;

public class StartMotor extends Command {
  private final Modula subsystem;
  private final String motor;
  private final double power;
  private final double duration;
  private double startTime; 

  public StartMotor (Modula subsystem, String motor, double power, double duration) {
    this.subsystem = subsystem;
    this.duration = duration;
    this.motor = motor;
    this.power = power;
    addRequirements(subsystem);
  }

  @Override
  public void initialize() {
    startTime = Timer.getFPGATimestamp();
  }

  @Override
  public void execute() {
    if (motor.equals("drive")) {
      subsystem.setPowerToDrive(power);
    } else {
      subsystem.setPowerToSteer(power);
    }
  }

  @Override
  public boolean isFinished() {
    return Timer.getFPGATimestamp() >= startTime + duration;
  }

  @Override
  public void end(boolean interrupted) {
    if (motor.equals("drive")) {
      subsystem.stopToDrive();
    } else {
      subsystem.stopToSteer();
    }
  }
}
