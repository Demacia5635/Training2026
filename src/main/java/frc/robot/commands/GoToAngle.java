package frc.robot.commands;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.math.controller.SimpleMotorFeedforward;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.PizzaMotor;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.Subsystem;
import edu.wpi.first.wpilibj2.command.InstantCommand;


public class GoToAngle extends Command {
    PizzaMotor subsystem;
    private double targetAngle;


    public GoToAngle(PizzaMotor subsystem) {
        double targetAngle = SmartDashboard.getNumber("steering Target ", subsystem.steerMotor.getCurrentPosition());
        this.subsystem = subsystem;
         SmartDashboard.putNumber("steering Error", 0);
    SmartDashboard.putNumber("steering angle", subsystem.steerMotor.getCurrentPosition());
      

  //       PIDController Spid = new PIDController(kp,ki,kd);
  //       SmartDashboard.putData("My steering PID", Spid);

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
    subsystem.steerMotor.setPositionVoltage(targetAngle);
    double currnt = subsystem.steerMotor.getCurrentPosition();
    double error = targetAngle - currnt;
    SmartDashboard.putNumber("steering Error", error);
    SmartDashboard.putNumber("steering angle", currnt);
    targetAngle = SmartDashboard.getNumber("steering Target ", targetAngle);
    //SmartDashboard.putNumber("steering velocity", subsystem.steerMotor.getVelocity().getValueAsDouble());
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    subsystem.steerMotor.setVoltage(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    boolean isFinished = Math.abs(subsystem.steerMotor.getCurrentPosition() - targetAngle) < 1;
    return isFinished;
  }

}
  //   public void steer(double targetAngle,  double kp, double ki, double kd){
  //       targetAngle = SmartDashboard.getNumber( "steering Target", targetAngle);
  //       double power;
  //       double currnt = getSPosition();
  //       double sumError = 0;
  //       PIDController Spid = new PIDController(kp,ki,kd);
  //       SmartDashboard.putData("My steering PID", Spid);
  //       double error = targetAngle - currnt;
  //       while (Math.abs(error) > 3){
  //           currnt = getSPosition();
  //           double lastError = error;
  //           error = targetAngle - currnt;
  //           sumError += error;
  //           double p = kp * error;
  //           double i = ki * sumError;
  //           double d = kd * (lastError - error);
  //           power = MathUtil.clamp(p+i+d,-0.5, 0.5);
  //           SmartDashboard.putNumber("steering Error", error);
  //           SmartDashboard.putNumber("steering angle", currnt);
  //           SmartDashboard.putNumber("steering Target ", targetAngle);
  //           setSPower(power);
  //           SmartDashboard.putNumber("steering Power", power);
  //           SmartDashboard.putNumber("steering velocity", steerMotor.getVelocity().getValueAsDouble());
  //       }
  //       power = 0;
  //       setSPower(power);
  //   }
  