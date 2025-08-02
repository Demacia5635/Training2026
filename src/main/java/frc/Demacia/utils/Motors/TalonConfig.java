package frc.Demacia.utils.Motors;

import com.ctre.phoenix6.CANBus;

/** 
 * Class to hold all Talon FX/SRX configuration
 * Applicable to Phoenix 6
 *  */
public class TalonConfig extends BaseMotorConfig<TalonConfig> {
    public CANBus canbus;           // Canbus 





    /** 
     * Constructor
     * @param id - canbus ID
     * @param canbus - Name of canbus
     * @param name - name of motor for logging
     */
    public TalonConfig(int id, CANBus canbus, String name) {
        super(id, name);
        this.canbus = canbus;
        motorType = MotorControllerType.TalonFX;
    }

    public TalonConfig(int id, String name, TalonConfig config) {
        this(id, config.canbus, name);
        copyBaseFields(config);
    }
   
}