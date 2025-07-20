package frc.Demacia.Sysid;

import java.util.ArrayList;

import org.ejml.simple.SimpleMatrix;

import frc.Demacia.Sysid.Log.MotorData;
import frc.Demacia.Sysid.Log.MotorData.MotorTimeData;

public class SysidCalculate {
    double[] vRange;
    double[][] result;
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

    public void calculate() {
        double maxV = motor.maxVelocity();
        vRange = new double[] { maxV*0.3, maxV*0.7, maxV};
        @SuppressWarnings("unchecked")
        ArrayList<MotorTimeData>[] data = new ArrayList[3];
        for(int i = 0; i < 3; i++) {
            data[i] = new ArrayList<>();
        }
        // fill the array of data - filterring data based on minimum voltage and velocity
        for(MotorTimeData d : motor.data()) {
            int range = range(d);
            if(range >= 0) {
                data[range].add(d);
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
        result = new double[3][10];
        for(int i = 0; i < 3; i++) { // for each range
            if(data[i].size() > 50) { // only if have enough data
                // fill the data and volts matrix
                SimpleMatrix mat = new SimpleMatrix(data[i].size(), col);
                SimpleMatrix volt = new SimpleMatrix(data[i].size(), 1);
                for(int row  = 0; row < data[i].size(); row++) {
                    MotorTimeData d = data[i].get(row);
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
                double maxError = 0;
                double sumError = 0;
                for(int e = 0; e < error.getNumRows(); e++) {
                    MotorTimeData md = data[i].get(e);
                    double val = Math.abs(error.get(e, 0) / md.voltage);
                    if(val > maxError)
                        maxError = val;
                    sumError += val;
                }
                double avgError = sumError / data[i].size();
                // update result
                int c = 0;
                result[i][0] = res.get(c++,0);
                result[i][1] = res.get(c++,0);
                result[i][2] = res.get(c++,0);
                result[i][3] = kgElevator ? res.get(c++,0) : 0;
                result[i][4] = kgArm ? res.get(c++,0) : 0;
                result[i][5] = kv2 ? res.get(c++,0) : 0;
                result[i][6] = ksqrt ? res.get(c++,0) : 0;
                result[i][7] = maxError;
                result[i][8] = avgError;
                result[i][9] = data[i].size();
                // print the worst cases
                for(int e = 0; e < error.getNumRows(); e++) {
                    MotorTimeData md = data[i].get(e);
                    double val = Math.abs(error.get(e, 0) / md.voltage);
                    if(val > avgError * 5) {
                        Sysid.msg(String.format("error %4.2f%% for %s", val, md.toString()));
                    }
                }

            } else {
                for(int k = 0; k < 9; k++) {
                    result[i][k] = 0;
                }
                result[i][9] = data[i].size();
            }
        }
    }

    public double[] getRes(int range) {
        return result[range];
    }
}
