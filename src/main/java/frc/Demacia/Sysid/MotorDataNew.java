package frc.Demacia.Sysid;
import java.util.ArrayList;
import java.util.Vector;

import edu.wpi.first.util.datalog.DataLogRecord;

public class MotorDataNew extends MotorData {

    protected static Vector<LogDataEntry> motors = new Vector<>();

    LogDataEntry entry;
    double maxVelocity = 0;

    public MotorDataNew(LogDataEntry entry) {
        super(null);
        this.entry = entry;
        createData();
    }
    private void createData() {
        entry.resetIterator();
        double maxVolt = 0;
        DataLogRecord record = entry.next();
        while(record != null) {
            try {
                float[] d = record.getFloatArray();
                data.add(new MotorTimeData(d[2], d[1],d[3],d[0],record.getTimestamp()));
                if(Math.abs(d[2]) > maxVelocity) {
                    maxVelocity = Math.abs(d[2]);
                }
                if(Math.abs(d[0]) > maxVolt) {
                    maxVolt = Math.abs(d[0]);
                }
            } catch (Exception e) {
                System.err.println("Error getting double array for " + entry.name  +  " after " + data.size() + "  : " + e);
            }
            record = entry.next();
        }
        updateAcceleration();
        System.out.println("max volt = " + maxVolt);
    }

    private void updateAcceleration() {
        MotorTimeData prev = null;
        for(MotorTimeData m : data) {
            m.rawAcceleration = m.acceleration;
            if(prev != null) {
                double deltaTime = (m.time - prev.time)/1000.0;
                double acc = (m.velocity - prev.velocity) / deltaTime;
                m.acceleration = (m.acceleration * deltaTime + acc * 0.02) / (deltaTime + 0.02);
                m.prev = prev;
            }
            prev = m;
        }
    }

    public double maxVelocity() {
        return maxVelocity;
    }
    public ArrayList<MotorTimeData> data() {
        return data;
    }

 
}