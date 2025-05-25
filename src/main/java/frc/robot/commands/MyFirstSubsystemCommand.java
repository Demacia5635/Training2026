package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.MyFirstSubsystem;

public class MyFirstSubsystemCommand extends Command {
    private final MyFirstSubsystem subsystem;
    private final double power;
    private final double duration;
    private double startTime;

    /**  מפעיל את המנוע בכח הנדרש למשך הזמן המבוקש   */
    public MyFirstSubsystemCommand (MyFirstSubsystem  subsystem, double power, double duration) {
      this.subsystem = subsystem;
      this.power = power;
      this.duration = duration;
      addRequirements(subsystem);
    }
    @Override
    public void initialize() {
      startTime = System.currentTimeMillis() / 1000.0;
    }
    @Override
    public void execute() {
      subsystem.setPower(power);
    }
    @Override
    public void end(boolean interrupted) {
      subsystem.stop();
    }
    @Override
    public boolean isFinished() {
      return duration == 0 || (System.currentTimeMillis() / 1000.0 - startTime) >= duration;
    }
  }
  