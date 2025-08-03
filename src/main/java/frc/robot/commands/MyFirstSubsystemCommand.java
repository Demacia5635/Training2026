package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants;
import frc.robot.subsystems.MyFirstSubsystem;

public class MyFirstSubsystemCommand extends Command {
    private final MyFirstSubsystem subsystem;
    private  double power = Constants.MyFirstSubsystemConstants.SET_POWER;
    private  double duration = 5.0;
    private double startTime;

    /** Activate motor for a duration*/
    public MyFirstSubsystemCommand (MyFirstSubsystem  subsystem, double power, double duration) {
      this.subsystem = subsystem;
      this.power = power;
      this.duration = duration;
      addRequirements(subsystem);
    }
    @Override
    public void initialize() {
      startTime = Timer.getFPGATimestamp();
      System.out.println("Command strted at: " + startTime + " seconds for " + duration + " seconds with power: " + power);
    }
    @Override
    public void execute() {
      subsystem.drivesetPower(power);
    }
    @Override
    public void end(boolean interrupted) {
      subsystem.stopAll();
      System.out.println("Command ended at: " + Timer.getFPGATimestamp() + " seconds");
    }
    @Override
    public boolean isFinished() {
      return duration > 0 && Timer.getFPGATimestamp() > duration + startTime;
    }

  }
  