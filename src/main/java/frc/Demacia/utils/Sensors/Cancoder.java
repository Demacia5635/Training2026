package frc.Demacia.utils.Sensors;
 
import com.ctre.phoenix6.configs.CANcoderConfiguration;
import com.ctre.phoenix6.hardware.CANcoder;
import com.ctre.phoenix6.signals.SensorDirectionValue;

import edu.wpi.first.units.measure.Angle;
import edu.wpi.first.units.measure.AngularVelocity;
import frc.Demacia.utils.StatusSignalData;
import frc.Demacia.utils.Log.LogEntry;
import frc.Demacia.utils.Log.LogManager;
import frc.Demacia.utils.Log.LogSupplier;

public class Cancoder extends CANcoder {

    CancoderConfig config;
    String name;

    CANcoderConfiguration cfg = new CANcoderConfiguration();

    StatusSignalData<Angle> positionSignal;
    StatusSignalData<Angle> absPositionSignal;
    StatusSignalData<AngularVelocity> velocitySignal;
    

    public Cancoder(CancoderConfig config) {
        super(config.id, config.canbus.canbus);
        this.config = config;
		name = config.name;
		configCancoder();
        setStatusSignals();
        addLog();
		LogManager.log(name + " cancoder initialized");
    }
    
    private void configCancoder() {
		cfg.MagnetSensor.MagnetOffset = config.offset;
        cfg.MagnetSensor.SensorDirection = config.inverted ? SensorDirectionValue.Clockwise_Positive: SensorDirectionValue.CounterClockwise_Positive;
        getConfigurator().apply(cfg);
    }
    
    private void setStatusSignals() {
        positionSignal = new StatusSignalData<>(getPosition());
        absPositionSignal = new StatusSignalData<>(getAbsolutePosition());
        velocitySignal = new StatusSignalData<>(getVelocity());

    }

    private void addLog() {
        new LogEntry(
            name, 
            new LogSupplier[] {
                new LogSupplier(positionSignal.signal(), "Position",null),
                new LogSupplier(absPositionSignal.signal(), "AbsPosition",null),
                new LogSupplier(velocitySignal.signal(), "Velocity",null),
                            },
            2, 
            "Cancoder", 
            "", 
            ""
        );
    }

    /**
     * when the cancoder opens its start at the absolute position
     * @return the none absolute amaunt of rotations the motor did in Radians
     */
    public double getCurrentPosition() {
        return positionSignal.get() * 360;
    }
    /**
     * @return the absolute amaunt of rotations the motor did in Radians
     */
    public double getCurrentAbsPosition() {
        return absPositionSignal.get() * 360;
    }
    /** 
     * @return the amount of rotations the motor do per second in Radians
     */
    public double getCurrentVelocity(){
        return velocitySignal.get() * 360;
    }
}