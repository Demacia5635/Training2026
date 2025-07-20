package frc.Demacia.Sysid;

import java.util.ArrayList;

import org.ejml.simple.SimpleMatrix;

import frc.Demacia.Sysid.MotorData.MotorTimeData;

public class SysidCalculate {

    public static enum VelocityRange { SLOW, MID, HIGH};
    public static enum KTypes { KS, KV, KA, KGElevator, KGArm, KV2, KSqrt};

    double[] vRange;
    ArrayList<ArrayList<MotorTimeData>> data = new ArrayList<>();
    double[] maxError = {0,0,0};
    double[] avgError = {0,0,0};
    double[][] k = new double[KTypes.values().length][VelocityRange.values().length];
    
    MotorData motor;
    boolean kgElevator;
    boolean kgArm;
    boolean kv2;
    boolean ksqrt;

    public SysidCalculate(MotorData motor,boolean kgElevator, boolean kgArm, boolean kv2, boolean ksqrt ) {
        this.motor = motor;
        this.kgArm = kgArm;
        this.kgElevator = kgElevator;
        this.ksqrt = ksqrt;
        this.kv2 = kv2;
        calculate();
    }

    private boolean valid(double value, double min) {
        return value > min || value < -min;
    }

    // return the array to use - -1 if filterred
    private int range(MotorTimeData data) {
        double v = Math.abs(data.velocity);
        int i = v < vRange[0] ? 0 : v < vRange[1] ? 1 : 2;
        
        if(valid(v, 0.1) && valid(data.voltage, 0.2)) {
            if(data.prev != null) {
                if(valid(data.prev.velocity, 0.1) && valid(data.prev.voltage, 0.2)) {
                    return i;
                } else {
                    return -1;
                }
            } else {
                return i;
            }
        } else {
            return -1;
        }
    }

    private void calculate() {
        double maxV = motor.maxVelocity();
        vRange = new double[] { maxV*0.3, maxV*0.7, maxV};
        for(int i = 0; i < 3; i++) {
            data.add(new ArrayList<>());
        }
        // fill the array of data - filterring data based on minimum voltage and velocity
        for(MotorTimeData d : motor.data()) {
            int range = range(d);
            if(range >= 0) {
                data.get(range).add(d);
            }
        }
        int col = 3;
        if(kgElevator)
            col++;
        if(kgArm)
            col++;
        if(kv2)
            col++;
        if(ksqrt)
            col++;
        for(int i = 0; i < 3; i++) { // for each range
            if(data.get(i).size() > 50) { // only if have enough data
                // fill the data and volts matrix
                SimpleMatrix mat = new SimpleMatrix(data.get(i).size(), col);
                SimpleMatrix volt = new SimpleMatrix(data.get(i).size(), 1);
                for(int row  = 0; row < data.get(i).size(); row++) {
                    MotorTimeData d = data.get(i).get(row);
                    int c = 0;
                    mat.set(row, c++, Math.signum(d.velocity)); // ks
                    mat.set(row, c++, d.velocity); // kv
                    mat.set(row, c++, d.acceleration); // kv
                    if(kgElevator) {
                        mat.set(row, c++, 1); // kg
                    }
                    if(kgArm) {
                        mat.set(row, c++, Math.cos(d.position)); // kg - arm
                    }
                    if(kv2) {
                        mat.set(row, c++, Math.signum(d.velocity)*d.velocity*d.velocity); // kv2
                    }
                    if(ksqrt) {
                        mat.set(row, c++, Math.signum(d.velocity)*Math.sqrt(Math.abs(d.velocity))); // ksqrt
                    }
                    volt.set(row, 0, d.voltage);
                }
                // solve
                SimpleMatrix res = mat.solve(volt);
                // calculate error
                SimpleMatrix predict = mat.mult(res);
                SimpleMatrix error = volt.minus(predict);
                double maxErr = 0;
                double sumError = 0;
                for(int e = 0; e < error.getNumRows(); e++) {
                    MotorTimeData md = data.get(i).get(e);
                    double val = Math.abs(error.get(e, 0) / md.voltage);
                    if(val > maxErr)
                        maxErr = val;
                    sumError += val;
                }
                double avgErr = sumError / data.get(i).size();
                // update result
                int c = 0;
                k[KTypes.KS.ordinal()][i] = res.get(c++,0);
                k[KTypes.KV.ordinal()][i] = res.get(c++,0);
                k[KTypes.KA.ordinal()][i] = res.get(c++,0);
                k[KTypes.KGElevator.ordinal()][i] = kgElevator ? res.get(c++,0) : 0;
                k[KTypes.KGArm.ordinal()][i] = kgArm ? res.get(c++,0) : 0;
                k[KTypes.KV2.ordinal()][i] = kv2 ? res.get(c++,0) : 0;
                k[KTypes.KSqrt.ordinal()][i] = ksqrt ? res.get(c++,0) : 0;
                maxError[i] = maxErr;
                avgError[i] = avgErr;
                // print the worst cases
                for(int e = 0; e < error.getNumRows(); e++) {
                    MotorTimeData md = data.get(i).get(e);
                    double val = Math.abs(error.get(e, 0) / md.voltage);
                    if(val > avgErr * 5) {
                        Sysid.msg(String.format("error %4.2f%% for %s", val, md.toString()));
                    }
                }
            }
        }
    }

    public double getRange(VelocityRange range) {
        return vRange[range.ordinal()];
    }
    public int getCount(VelocityRange range) {
        return data.get(range.ordinal()).size();
    }
    public double getMaxError(VelocityRange range) {
        return maxError[range.ordinal()];
    }
    public double getAverageError(VelocityRange range) {
        return avgError[range.ordinal()];
    }
    public double getK(KTypes type, VelocityRange range) {
        return k[type.ordinal()][range.ordinal()];
    }
}
