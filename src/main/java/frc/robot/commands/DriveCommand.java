package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.subsystems.SwerveModule;

import java.lang.module.ModuleDescriptor.Builder;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.util.sendable.SendableBuilder;
import frc.robot.Constants;
import frc.robot.Chassis;

public class DriveCommand extends Command {
  private Chassis chassis;
  private CommandXboxController xboxController;
  private boolean IsRed;
  private ChassisSpeeds wantSpeeds;
  private ChassisSpeeds speed;
  private double direction;
  private double wantedSpeedsX;
  private double wantedSpeedsY;
  private double wantedRot;

  double joyX;
  double joyY;
    public DriveCommand (Chassis chassis) {
      SmartDashboard.putData(this);
      chassis = new Chassis();
      xboxController = new CommandXboxController(Constants.MyFirstSubsystemConstants.DriveID);
      IsRed=true;
      wantedSpeedsX = 15.0;
      wantedSpeedsY = 15.0;
      wantedRot = 30.0;
    }
    @Override
    public void initialize() {
      wantSpeeds = new ChassisSpeeds(wantedSpeedsX,wantedSpeedsY,wantedRot);
      direction = IsRed ? 1:-1;
      // = xboxController.getLeftY() * direction;
     // = xboxController.getLeftX() * direction;   

    }
    @Override
    public void execute() {
      joyX = xboxController.getLeftY() * direction;
      joyY = xboxController.getLeftX() * direction;        
      double rot = xboxController.getLeftTriggerAxis() - xboxController.getRightTriggerAxis();
      double velX = Math.pow(joyX, 2) * 
      Constants.MAX_DRIVE_VELOCITY_X * Math.signum(joyX);
      double velY = Math.pow(joyY, 2) *
      Constants.MAX_DRIVE_VELOCITY_Y * Math.signum(joyY);
      double velRot = Math.pow(rot, 2) *
      Constants.MAX_ROTATIONAL_VELOCITY*Math.signum(rot);
      speed = new ChassisSpeeds(velX, velY,velRot);
      chassis.setVelocities(wantSpeeds,speed);

    }
    @Override
    public void end(boolean interrupted) {

    }
    @Override
    public boolean isFinished() {
      return false;
    }
    

  }
  