package frc.Demacia.Sysid;
import java.util.ArrayList;

import edu.wpi.first.util.datalog.DataLogRecord;

public class MotorData {
    LogEentryHirerchy motor;
    ArrayList<MotorTimeData> data = new ArrayList<>();
    LogEentryHirerchy[] entries = new LogEentryHirerchy[LogReader.MotorFields.length + 1];
    double maxVelocity = 0;

    public MotorData(LogEentryHirerchy motor) {
        this.motor = motor;
        if(motor.isNewMotor) {
            createNewData();
        } else {
            for(int i = 0; i < LogReader.MotorFields.length; i++) {
                String s = LogReader.MotorFields[i];
                LogEentryHirerchy e = motor.child(s);
                entries[i] = e;
            }
            createData();        
        }
    }
    private void createNewData() {
        motor.entryData.resetIterator();
        DataLogRecord record = motor.entryData.next();
        while(record != null) {
            double[] d = record.getDoubleArray();
            data.add(new MotorTimeData(d[2], d[1],d[3],d[0],record.getTimestamp()));
            record = motor.entryData.next();
        }
        updateAcceleration();
    }


    private void createData() {
        DataLogRecord[] records = new DataLogRecord[LogReader.MotorFields.length+1];
        DataLogRecord[] precords = new DataLogRecord[LogReader.MotorFields.length+1];
        for(int i = 0; i < records.length; i++) {
            if(entries[i] != null) {
                entries[i].entryData.resetIterator();
                records[i] = entries[i].entryData.next();
                precords[i] = records[i];
            } else {
                records[i] = null;
                precords[i] = null;
            }
        }

        while(records[0] != null) {
            // move all to vel time
            long time = records[0].getTimestamp();
            for(int i = 1; i < records.length; i++) {
                while(records[i] != null && records[i].getTimestamp() < time + 15) {
                    precords[i] = records[i];
                    records[i] = entries[i].entryData.next();
                }
            }
            data.add(new MotorTimeData(precords[1].getDouble(), records[0].getDouble(), precords[3] != null? precords[3].getDouble():0, precords[2].getDouble(), time));  
            //System.out.println(" added - times = " + precords[1].getTimestamp() + " " + records[0].getTimestamp() + " " + precords[3].getTimestamp() + " " + precords[2].getTimestamp());
            records[0] = entries[0].entryData.next();
        }
        updateAcceleration();
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




    public class MotorTimeData {

        public double velocity;
        public double position;
        public double acceleration;
        public double rawAcceleration;
        public double voltage;
        public long time;
        public MotorTimeData prev = null;

        public MotorTimeData(double velocity, double position, double acceleration, double voltage, long time) {
            this.velocity = velocity;
            this.position = position;
            this.acceleration = acceleration;
            this.voltage = voltage;
            this.time = time;
            maxVelocity = Math.max(maxVelocity, Math.abs(velocity));
        }

        @Override
        public String toString() {
            if(prev == null) {
                return String.format("volt=%4.2f  vel=%5.2f  acc=%5.2f  pos=%6.2f", voltage, velocity, rawAcceleration, position);
            } else {
                return String.format("volt=%4.2f-%4.2f  vel=%5.2f-%5.2f  acc=%5.2f-%5.2f  pos=%6.2f-%6.2f  timeDiff=%d", 
                    prev.voltage, voltage, prev.velocity, velocity, prev.rawAcceleration, rawAcceleration, prev.position, position, (time - prev.time));
            }
        }
    }
}