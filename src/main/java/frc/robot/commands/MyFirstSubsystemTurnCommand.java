package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.MyFirstSubsystem;

public class MyFirstSubsystemTurnCommand extends Command {
    private final MyFirstSubsystem subsystem;
    private final double angle;
    private double power = 0;
    private final double tolerance = 4.0;

    /**  מפעיל את המנוע בכח הנדרש למשך הזמן המבוקש   */
    public MyFirstSubsystemTurnCommand (MyFirstSubsystem  subsystem, double angle) {
      this.subsystem = subsystem;
      this.angle = MyFirstSubsystem.normalizeDegrees(angle);
    
      addRequirements(subsystem);
    }
    @Override
    public void initialize() {
      double currentAngle = subsystem.getNormalizePosition();
      double rotate = angle - currentAngle;
      rotate = rotate > 180 ? rotate - 360 : rotate < -180 ? rotate + 360 : rotate;
      if(Math.abs(rotate) > tolerance) {
        power = Math.signum(rotate) * 0.2;
      } else {
        power = 0;
      }
      subsystem.setPower(power);
    }
    @Override
    public void execute() {
    }
    @Override
    public void end(boolean interrupted) {
      subsystem.stop();
    }
    @Override
    public boolean isFinished() {
      double currentAngle = subsystem.getNormalizePosition();
      double rotate = angle - currentAngle;
      rotate = rotate > 180 ? rotate - 360 : rotate < -180 ? rotate + 360 : rotate;
      return Math.abs(rotate) < tolerance || (power > 0 && rotate < 0) || (power < 0 && rotate > 0);
    }
  }
  