package frc.robot.subsystems;


import com.ctre.phoenix6.sim.TalonFXSimState;

import edu.wpi.first.math.system.plant.DCMotor;
import edu.wpi.first.math.system.plant.LinearSystemId;
import edu.wpi.first.wpilibj.RobotController;
import edu.wpi.first.wpilibj.simulation.DCMotorSim;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.utils.SparkMotor;
import frc.robot.utils.TalonMotor;

public class MotorExampleSubsytem extends SubsystemBase {
    // Define the motor 
    TalonMotor talonMotor;
    SparkMotor sparkMotor;

    
    // Constructor
    public MotorExampleSubsytem() {
        super();
        talonMotor = new TalonMotor(Constants.Example.TALON_CONFIG);
        sparkMotor = new SparkMotor(Constants.Example.SPARKMOTOR_CONFIG);
    }

    // Method to set the motor speed
    public void setTalonPower(double power) {
        talonMotor.setDuty(power);
    }
    // Method to stop the motor
    public void stopTalon() {
        setTalonPower(0);
    }
    public void setSparkPower(double power) {
        sparkMotor.setDuty(power);
    }
    // Method to stop the motor
    public void stopSpark() {
        setSparkPower(0);
    }


    public double getTalonPosition() {
        return talonMotor.getCurrentPosition();
    }
    public double getTalonVelocity() {
        return talonMotor.getCurrentVelocity();
    }
    public double getTalonVolts() {
        return talonMotor.getCurrentVoltage();
    }

    public double getSparkVelocity() {
        return sparkMotor.getCurrentVelocity();
    }
    public double getSparkPosition() {
        return sparkMotor.getCurrentPosition();
    }
    public double getSparkVolts() {
        return sparkMotor.getCurrentVoltage();
    }


}   