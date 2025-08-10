package frc.robot.utils.Log;

import frc.robot.utils.SparkMotor;
import frc.robot.utils.TalonMotor;

public class MotorLogEntry {

    public static void add(TalonMotor motor) {
        new LogEntry(
            motor.name(), 
            new LogSupplier[] {
                new LogSupplier(motor.getMotorVoltage(), "Volts",null),
                new LogSupplier(motor.getPosition(), "Position",null),
                new LogSupplier(motor.getVelocity(), "Velocity",null),
                new LogSupplier(motor.getAcceleration(), "Acceleration",null),
                new LogSupplier(motor.getStatorCurrent(), "Current",null),
                new LogSupplier(motor.getClosedLoopError(), "Error",null),
                new LogSupplier(motor.getClosedLoopReference(), "SetPoint",null)
                            },
            2, 
            "Motor", 
            "Talon", 
            "GearRatio:" + motor.gearRatio());

    }
    public static void add(SparkMotor motor) {
        new LogEntry(
            motor.name(), 
            new LogSupplier[] {
                new LogSupplier(motor::getCurrentVoltage, "Volts",null),
                new LogSupplier(motor::getCurrentPosition, "Position",null),
                new LogSupplier(motor::getCurrentVelocity, "Velocity",null),
                new LogSupplier(()->0, "Acceleration",null),
                new LogSupplier(motor::getOutputCurrent, "Current",null),
                new LogSupplier(motor::getCloseLoopError, "Error",null),
                new LogSupplier(motor::getSetPoint, "SetPoint",null)
                            },
            2, 
            "Motor", 
            "Spark", 
            "GearRatio:" + motor.gearRatio());
    }
}
