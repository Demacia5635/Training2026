// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.util.sendable.SendableBuilder;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.Demacia.utils.Motors.TalonMotor;
import frc.robot.Drive.Constants;
import static frc.robot.Constants.*;

public class TestUtils extends SubsystemBase {

  TalonMotor talonMotor;
  int num = 0;

  /** Creates a new TestUtils. */
  public TestUtils() {
    super();
    talonMotor = new TalonMotor(Constants.BASE_DRIVE_CONFIG);
    SmartDashboard.putData(this);
    
  }

  public double getDriveMotorVelocity(){
    return talonMotor.getCurrentVelocity();
  }

  public void ffWithpid(double velocity){
    talonMotor.setVelocity(velocity);
  }
  public void setPow(double pow){
    talonMotor.set(pow);
  }
  public void initSendable(SendableBuilder builder) {
        super.initSendable(builder);
        builder.addDoubleProperty("speed", this::getDriveMotorVelocity, null);
 }

  @Override
  public void periodic() {
    //SmartDashboard.putData(this);
    // This method will be called once per scheduler run
    SmartDashboard.putNumber("TamirTheKing", ++num);
  }
}
