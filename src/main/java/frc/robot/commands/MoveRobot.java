package frc.robot.commands;

import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.ChassisModula;

public class MoveRobot extends Command {

  private XboxController controller;
  private ChassisSpeeds speeds;
  private ChassisModula chassis;
  
  public MoveRobot(ChassisModula chassis, XboxController controller) {
    this.controller = controller;
    this.chassis = chassis;
    addRequirements();
  }

  @Override
  public void initialize() {}

  @Override
  public void execute() {
    double joyX = controller.getLeftY();
    double joyY = controller.getLeftX();        
    double rot = controller.getRightX();

    double velX = Math.pow(joyX, 2) * Math.signum(joyX);
    double velY = Math.pow(joyY, 2) * Math.signum(joyY);
    double velRot = Math.pow(rot, 2) * Math.signum(rot);

    speeds = new ChassisSpeeds(velX, velY,velRot);

    chassis.setVelocities(speeds);
  }

  @Override
  public void end(boolean interrupted) {}

  @Override
  public boolean isFinished() {
    return false;
  }
}
