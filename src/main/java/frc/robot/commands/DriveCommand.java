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
  private double lastSpeedsX;
  private double lastSpeedsY;
  private double lastwantedRot;

  double joyX;
  double joyY;
    public DriveCommand (Chassis chassis) {
      this.chassis = chassis;
      xboxController = new CommandXboxController(Constants.MyFirstSubsystemConstants.DriveID);
      IsRed=true;
      addRequirements(chassis);
      SmartDashboard.putData(this);
    }
    @Override
    public void initialize() {
      direction = IsRed ? 1:-1;
      chassis.resetGyro();
      // = xboxController.getLeftY() * direction;
     // = xboxController.getLeftX() * direction;   

    }
    @Override
    public void execute() {
      joyX = xboxController.getLeftX() * direction;
      joyY = xboxController.getLeftY() * direction;        
      double rot = xboxController.getLeftTriggerAxis() - xboxController.getRightTriggerAxis();
      double velX = Math.pow(joyX, 2) * 3.6;
      double velY = Math.pow(joyY, 2) * 3.6;
      double velRot = Math.pow(rot, 2) * 40;
      wantSpeeds = new ChassisSpeeds(velX, velY,velRot);
      chassis.setVelocities(wantSpeeds,speed);
      lastSpeedsX = velY;
      lastSpeedsY = velY;
      lastwantedRot = rot;
      speed = new ChassisSpeeds(lastSpeedsX,lastSpeedsY,lastwantedRot);


    }
    @Override
    public void end(boolean interrupted) {

    }
    @Override
    public boolean isFinished() {
      return false;
    }
    

  }
  