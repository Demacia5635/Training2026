package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.MyFirstSubsystem;
import edu.wpi.first.math.MathUtil;
import edu.wpi.first.util.sendable.SendableBuilder;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Constants;

public class MyFirstSubsystemCommand extends Command {
    private final MyFirstSubsystem subsystem;
    double sumOfEror;
    double lastError;
 
    /** Activate motor for a duration*/
    public MyFirstSubsystemCommand (MyFirstSubsystem  subsystem) {
      this.subsystem = subsystem;
      addRequirements(subsystem);
    }
    @Override
    public void initialize() {
    }
    @Override
    public void execute() {
      subsystem.turnToAngle(SmartDashboard.getNumber("degree",45.0));
    }
    @Override
    public void end(boolean interrupted) {
      subsystem.stop();
    }
    @Override
    public boolean isFinished() {
      return (SmartDashboard.getNumber("degree",45.0)-subsystem.GetPositiony())<2&&(SmartDashboard.getNumber("degree",45.0)-subsystem.GetPositiony()>-2);
    }
    

  }
  