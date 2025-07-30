package frc.robot;

import com.ctre.phoenix6.hardware.TalonFX;

import edu.wpi.first.util.sendable.Sendable;
import edu.wpi.first.util.sendable.SendableBuilder;

public class Send implements Sendable {

    TalonFX motor = new TalonFX(10);

    @Override
    public void initSendable(SendableBuilder builder) {
        builder.addDoubleProperty("pos", this::getposition, null);
    }

    public double getposition(){
        return 5.0;
    }
}
