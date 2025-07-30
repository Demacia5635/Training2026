package frc.robot.commands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.MoveSubsystem;
import frc.robot.subsystems.MyFirstSubsystem;
import frc.robot.Constants;

public class CommandToMove extends Command {
    private final MoveSubsystem subsystem;
    private double meter;
    private final double power;

    /** Activate motor for a duration*/
    public CommandToMove (MoveSubsystem  subsystemMove, double power,double meter) {
      this.subsystem = subsystemMove;
      this.power = power;
      this.meter=meter;
      addRequirements(subsystemMove);
    }
    public double ToDegrees(double spins){
        return spins*Constants.MyFirstSubsystemConstants.steerration*360;
    
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
      subsystem.Stop();
    }
    @Override
    public boolean isFinished() {
      return (Constants.MyFirstSubsystemConstants.ToMeter(SmartDashboard.getNumber("motor x pos", 0.0)))>=meter;
    }

  }

