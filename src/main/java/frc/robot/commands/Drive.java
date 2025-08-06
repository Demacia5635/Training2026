// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import javax.sound.sampled.TargetDataLine;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants.OperatorConstants;
import frc.robot.subsystems.MyFirstSubsystem;


/* You should consider using the more terse Command factories API instead https://docs.wpilib.org/en/stable/docs/software/commandbased/organizing-command-based.html#defining-commands */
public class Drive extends Command {
  /** Creates a new driveAndSteer. */
  MyFirstSubsystem subsystem;
  private double targetDistance;
  private double targetPosition;

  public Drive(MyFirstSubsystem subsystem, double targetDistance) {
    // Use addRequirements() here to declare subsystem dependencies.
        this.subsystem = subsystem;
        this.targetDistance = targetDistance;
        double wheelCircumference = Math.PI * OperatorConstants.wheelDiameter;
        double rotations = targetDistance / wheelCircumference; 
        double targetPosition = rotations*360+subsystem.getDPosition();
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {

    double dCurrnt = subsystem.getDPosition();
    double dError = targetPosition - dCurrnt;
    double dPower = 0.3*Math.signum(dError);
    SmartDashboard.putNumber("drive Error", dError);
    SmartDashboard.putNumber("drive angle", dCurrnt);
    SmartDashboard.putNumber("drive Target ", targetPosition);
    subsystem.setDPower(dPower);
    SmartDashboard.putNumber("drive Power", dPower);
    SmartDashboard.putNumber("drive velocity", subsystem.driveMotor.getVelocity().getValueAsDouble());
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    boolean isFinished = Math.abs(targetPosition - subsystem.getDPosition()) < 10;
    return isFinished;
  }
}
// public void drive(double distanceInCm,  double kp, double ki, double kd){
//   double power;
//   double sumError = 0;
//   double lastError = 0;
//   double wheelCircumference = Math.PI * OperatorConstants.wheelDiameter;
//   double rotations = distanceInCm / wheelCircumference; 
//   double targetPosition = rotations*360+getDPosition();
//   double currnt = getDPosition();
//   double error = targetPosition - currnt;;
//    while (Math.abs(error) > 3){
//    currnt = getDPosition();
//    error = targetPosition - currnt;
//    sumError += error;
//   double p = kp * error;
//   double i = ki * sumError;
//   double d = kd * (lastError - error);
//   power = MathUtil.clamp(p+i+d,-0.5, 0.5);
//   SmartDashboard.putNumber("drive Error", error);
//   SmartDashboard.putNumber("drive angle", currnt);
//   SmartDashboard.putNumber("drive Target ", targetPosition);
//   setDPower(power);
//   SmartDashboard.putNumber("drive Power", power);
//   SmartDashboard.putNumber("drive velocity", driveMotor.getVelocity().getValueAsDouble());

//    }
//       power = 0;
//       setDPower(power);
//   }
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
