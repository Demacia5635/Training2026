package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.ModuleSubsystem;

import java.lang.module.ModuleDescriptor.Builder;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.util.sendable.SendableBuilder;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Constants;

public class MyFirstSubsystemCommand extends Command {
    private final ModuleSubsystem subsystem;
    double targetDegree;
    double targetVeloctyDrive;
    double targetVeloctySteer;
 
    /** Activate motor for a duration*/
    public MyFirstSubsystemCommand (ModuleSubsystem  subsystem) {
      this.subsystem = subsystem;
      addRequirements(subsystem);
      this.targetDegree = 45;
      this.targetVeloctyDrive = 20;
      this.targetVeloctySteer = 10;
      SmartDashboard.putData(this);
    }
    @Override
    public void initialize() {


    }
    @Override
    public void execute() {
      subsystem.SetSteerAngle(targetDegree);
      subsystem.setVeloctyDrive(targetVeloctyDrive);
    }
    @Override
    public void end(boolean interrupted) {
      subsystem.Stop();
    }
    @Override
    public boolean isFinished() {
      double VelocityEror = targetVeloctyDrive-subsystem.GetVelocityDrive();
      double DegreeEror = targetDegree-subsystem.GetPositionOfSteer();
      return (VelocityEror>-0.1&&VelocityEror<0.1&&DegreeEror>-1&&DegreeEror<-1);
    }
    

  }
  