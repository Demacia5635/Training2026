package frc.Demacia.utils;

import com.ctre.phoenix6.StatusSignal;

public class StatusSignalData<T> {
    StatusSignal<T> signal;
    double lastValue;



    public StatusSignalData(StatusSignal<T> signal) {
        this.signal = signal;
        signal.refresh();
        lastValue = signal.getValueAsDouble();
    }

    public double get() {
        signal.refresh();
        if(signal.getStatus().isOK()) {
            lastValue = signal.getValueAsDouble();
        }
        return lastValue;
    }

    public String getString() {
        signal.refresh();
        return signal.getValue().toString();

    }

    public StatusSignal<T> signal() {
        return signal;
    }

}
