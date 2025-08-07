package frc.Demacia.utils;

import java.util.Arrays;

import com.ctre.phoenix6.StatusSignal;

public class StatusSignalData<T> {

    @SuppressWarnings("rawtypes")
    private static StatusSignal[] signals = null;


    StatusSignal<T> signal;
    double lastValue;
    double multiplier = 1;

    public StatusSignalData(StatusSignal<T> signal) {
        this.signal = signal;
        signal.refresh();
        lastValue = signal.getValueAsDouble();
        add();
    }
    public StatusSignalData(StatusSignal<T> signal, double multiplier) {
        this(signal);
        setMultiplier(multiplier);
    }

    public void setMultiplier(double multiplier) {
        this.multiplier = multiplier;
    }

    public double get() {
        signal.refresh();
        if(signal.getStatus().isOK()) {
            lastValue = signal.getValueAsDouble();
        }
        return lastValue * multiplier;
    }

    public T getValue() {
        return signal.getValue();
    }

    public String getString() {
        signal.refresh();
        return signal.getValue().toString();
    }

    public StatusSignal<T> signal() {
        return signal;
    }

    private void add() {
        if(signals == null) {
            signals = new StatusSignal[1];
            signals[0] = signal;
        } else {
            signals = Arrays.copyOf(signals, signals.length + 1);
            signals[signals.length-1] = signal;
        }
    }

    public void refreshAll() {
        StatusSignal.refreshAll(signals);
    }

}
