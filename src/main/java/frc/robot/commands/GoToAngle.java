package frc.robot.commands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.MyFirstSubsystem;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.Subsystem;
import edu.wpi.first.wpilibj2.command.InstantCommand;


public class GoToAngle extends Command {
    MyFirstSubsystem subsystem;
    private double targetAngle;

    public GoToAngle(MyFirstSubsystem subsystem,  double targetAngle) {
        this.targetAngle = targetAngle;
        this.subsystem = subsystem;
        addRequirements(subsystem);
    //     SmartDashboard.putNumber("Target Angle",90);
    //     SmartDashboard.putNumber("drive Error", 0);
    //     SmartDashboard.putNumber("drive angle", subsystem.getDPosition());
    //     SmartDashboard.putNumber("drive Target ", subsystem.getDPosition());
    //     SmartDashboard.putNumber("drive Power", 0);
    //     SmartDashboard.putNumber("drive velocity", 0);
    //     SmartDashboard.putNumber("steering Error", 0);
    // SmartDashboard.putNumber("steering angle", subsystem.getSPosition());
    // SmartDashboard.putNumber("steering Target ", subsystem.getSPosition());
    // SmartDashboard.putNumber("steering Power", 0);
    // SmartDashboard.putNumber("steering velocity", 0);
    }
    // Called when the command is initially scheduled.
  @Override
  public void initialize() {

  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute () {
    double currnt = subsystem.getSPosition();
    double error = targetAngle - currnt;
    double power = 0.05*Math.signum(error);
    SmartDashboard.putNumber("steering Error", error);
    SmartDashboard.putNumber("steering angle", currnt);
    SmartDashboard.putNumber("steering Target ", targetAngle);
    subsystem.setSPower(power);
    SmartDashboard.putNumber("steering Power", power);
    SmartDashboard.putNumber("steering velocity", subsystem.steerMotor.getVelocity().getValueAsDouble());
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    subsystem.stop();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    boolean isFinished = Math.abs(subsystem.getSPosition() - targetAngle) < 10;
    return isFinished;
  }

}