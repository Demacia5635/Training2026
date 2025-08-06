//from  line 2 to 6 import librisries for TalonFX and SubsystemBase
package frc.robot.subsystems;

import org.ejml.equation.IntegerSequence.For;

import com.ctre.phoenix6.hardware.TalonFX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

import frc.robot.Constants;

public class Subsystem extends SubsystemBase {

    // This is a subsystem class for controlling a TalonFX motor
    private TalonFX motor;
    public Subsystem() {
        // Initialize the TalonFX motor with a device ID of 1
        motor = new TalonFX(Constants.DRIVE_MOTOR_ID, Constants.CAN);

    }
    public void setPower(double power){
        motor.set(power);
    }
}
