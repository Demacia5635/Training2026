package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.MyFirstSubsystem;

public class MyFirstSubsystemCommand extends Command {
    private final MyFirstSubsystem subsystem;
    private final double power2;
    private final double power;
    private final double duration;
    private double startTime;

    /** Activate motor for a duration*/
    public MyFirstSubsystemCommand (MyFirstSubsystem  subsystem, double power,double power2, double duration) {
      this.subsystem = subsystem;
      this.power = power;
      this.power2 = power2;
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
      subsystem.setPower(power, power2);
    }
    @Override
    public void end(boolean interrupted) {
      subsystem.stop();
      System.out.println("Command ended at: " + Timer.getFPGATimestamp() + " seconds");
    }
    @Override
    public boolean isFinished() {
      return duration > 0 && Timer.getFPGATimestamp() > duration + startTime;
    }

  }
  