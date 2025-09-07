package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.SwerveModule;

import java.lang.module.ModuleDescriptor.Builder;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.util.sendable.SendableBuilder;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Constants;
import frc.robot.Chassis;

public class DriveCommand extends Command {
  private Chassis chassis;
    /** Activate motor for a duration*/
    public DriveCommand (SwerveModule  subsystem) {
      SmartDashboard.putData(this);
    }
    @Override
    public void initialize() {


    }
    @Override
    public void execute() {
    }
    @Override
    public void end(boolean interrupted) {
    }
    @Override
    public boolean isFinished() {
    }
    

  }
  