package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.MyFirstSubsystem;

public class MySecondSubsystemCommand extends Command {
    private final MyFirstSubsystem subsystem;
    private final double powerx;
    private final double powery;
    private final double duration;
    private double startTime = 0.0;

    /** Activate motor for a duration*/
    public MySecondSubsystemCommand (MyFirstSubsystem  subsystem, double powerx, double duration, double powery) {
      this.subsystem = subsystem;
      this.powerx = powerx;
      this.powery= powery;
      this.duration = duration;
      addRequirements(subsystem);
    }
    @Override
    public void initialize() {
      startTime = Timer.getFPGATimestamp();
      System.out.println("Command strted at: " + startTime + " seconds for " + duration + " seconds with power for motorx: " + powery+" seconds with power for motory: ");
    }
    @Override
    public void execute() {
      subsystem.setPowerx(powerx);
      subsystem.setPowery(powery);

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
  
