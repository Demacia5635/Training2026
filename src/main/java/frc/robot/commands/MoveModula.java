package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants.ChassisConstants;
import frc.robot.subsystems.Modula;

public class MoveModula extends Command {

  private Modula modula;

  public MoveModula() {
    this.modula = new Modula(ChassisConstants.BACK_RIGHT);
    addRequirements(modula);
  }

  @Override
  public void initialize() {}

  @Override
  public void execute() {
    modula.setDriveVelocity(30);
  }

  @Override
  public void end(boolean interrupted) {}

  @Override
  public boolean isFinished() {
    return false;
  }
}
