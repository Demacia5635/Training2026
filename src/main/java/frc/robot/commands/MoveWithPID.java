// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import com.ctre.phoenix6.hardware.TalonFX;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.controller.SimpleMotorFeedforward;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.util.sendable.SendableBuilder;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.MyFirstSubsystem;

/* You should consider using the more terse Command factories API instead https://docs.wpilib.org/en/stable/docs/software/commandbased/organizing-command-based.html#defining-commands */
public class  MoveWithPID extends Command {
  /** Creates a new SpinToAngle. */
 
  // private double startingAngle;
  private MyFirstSubsystem sub;
  private double wantedAngle;
  private double power;
  private PIDController velocityController = new PIDController(0.0001, 0.0, 0.00001);
  private TalonFX motor2Talon;
  private PIDController driveFF;
  private PIDController drivePID;
        
        public MoveWithPID(MyFirstSubsystem sub) {
          this.sub = sub;
          this.power = 0.1;
          SmartDashboard.putData("Subsystem 1", this);
          addRequirements(sub);
      }
        
        @Override
        public boolean isFinished() {
          return Math.abs(sub.spinToAngle() - wantedAngle) < 5; 
        }
        public void setAngle(double angle) {
          this.wantedAngle = angle;
      }
        @Override
          public void initSendable(SendableBuilder builder){
            builder.addDoubleProperty("Angle", this::getAngle, this::setAngle);
      
          }
          public double getAngle() {
            return this.wantedAngle;
        }
        // Called every time the scheduler runs while the command is scheduled.
        @Override
        public void execute() {
          if (wantedAngle > 0) {
            double currentAngle = sub.spinToAngle(); // conversion
            double Power = velocityController.calculate(currentAngle, wantedAngle);
            sub.setPower2(Power);
        } else {
            sub.setPower(power);
        }
        }
        
        // Called once the command ends or is interrupted.
        @Override
        public void end(boolean interrupted) {
          power = 0;
        }
        public void setDriveVelocity(double velocityMetersPerSecond) {
          double currentVelocity = getDriveVelocity(); // במטר לשנייה
          double pidOutput = drivePID.calculate(currentVelocity, velocityMetersPerSecond);
          double ffOutput = driveFF.calculate(velocityMetersPerSecond);
          double totalVoltage = pidOutput + ffOutput;
          motor2Talon.setVoltage(totalVoltage); // הדרייב
}
        private double getDriveVelocity() {
        
          throw new UnsupportedOperationException("Unimplemented method 'getDriveVelocity'");
}

}
