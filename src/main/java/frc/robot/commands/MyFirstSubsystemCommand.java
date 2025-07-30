package frc.robot.commands;

import static edu.wpi.first.units.Units.Degrees;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.MyFirstSubsystem;
import frc.robot.Constants;

public class MyFirstSubsystemCommand extends Command {
    private final MyFirstSubsystem subsystem;
    private final double degree;
    private final double power;

    /** Activate motor for a duration*/
    public MyFirstSubsystemCommand (MyFirstSubsystem  subsystem, double power,double degree) {
      this.subsystem = subsystem;
      this.power = power;
      this.degree=degree;
      addRequirements(subsystem);
    }
    @Override
    public void initialize() {
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
      return (Constants.MyFirstSubsystemConstants.ToDegrees(SmartDashboard.getNumber("motor y pos(m)", degree)))>=degree;
    }

  }
  