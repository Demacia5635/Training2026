package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.MoveSubsystem;
import frc.robot.subsystems.MyFirstSubsystem;
import edu.wpi.first.math.MathUtil;
import edu.wpi.first.units.measure.Velocity;
import edu.wpi.first.util.sendable.SendableBuilder;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Constants;

public class SpeedPID extends Command {
    private final MoveSubsystem subsystem;
    private final double sumEror;
    private final double lastError;

    public SpeedPID(MoveSubsystem subsystem){
        this.subsystem = subsystem;
        addRequirements(subsystem);
    }

    @Override
    public void initialize() {
    }
    public void setTarget(double tergatSpeed){

    }
    public void execute() {
        subsystem.setPower(calcaulateVelocty(lastError, sumEror));
    }
    @Override
    public void end(boolean interrupted) {
      subsystem.stop();
    }
    @Override
    public boolean isFinished() {
      return (SmartDashboard.getNumber("velocty",20.0)-subsystem.GetVelocity())<0.1&&(SmartDashboard.getNumber("velocty",20.0)-subsystem.GetVelocity())<-0.1
    }






}
