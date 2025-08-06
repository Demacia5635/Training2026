package frc.Demacia.utils.Log;

import edu.wpi.first.units.measure.Current;
import frc.Demacia.utils.StatusSignalData;
import frc.Demacia.utils.Motors.SparkMotor;
import frc.Demacia.utils.Motors.TalonMotor;

public class MotorLogEntry {

    public static void add(TalonMotor motor) {
        new LogEntry(
            motor.name(), 
            new LogSupplier[] {
                new LogSupplier(motor.getVoltageSignal(), "Volts",null),
                new LogSupplier(motor.getPositionSignal(), "Position",null),
                new LogSupplier(motor.getVelocitySignal(), "Velocity",null),
                new LogSupplier(motor.getAccelerationSignal(), "Acceleration",null),
                new LogSupplier(new StatusSignalData<Current>(motor.getStatorCurrent()), "Current",null),
                new LogSupplier(motor.getClosedLoopErrorSignal(), "Error",null),
                new LogSupplier(motor.getClosedLoopSPSignal(), "SetPoint",null)
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
                new LogSupplier(motor::getCurrentAcceleration, "Acceleration",null),
                new LogSupplier(motor::getOutputCurrent, "Current",null),
                new LogSupplier(motor::getCurrentClosedLoopError, "Error",null),
                new LogSupplier(motor::getCurrentClosedLoopSP, "SetPoint",null)
                            },
            2, 
            "Motor", 
            "Spark", 
            "GearRatio:" + motor.gearRatio());
    }
}
