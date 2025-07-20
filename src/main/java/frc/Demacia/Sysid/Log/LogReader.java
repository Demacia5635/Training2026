package frc.Demacia.Sysid.Log;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Vector;

import frc.Demacia.Sysid.Log.DataLogRecord.StartRecordData;


public class LogReader {

    private DataLogReader reader;
    private String file;
    DataEntry[] entries = new DataEntry[1000];
    entryHirerchy top = new entryHirerchy("TOP");

    public static final String[] MotorFields = {"Position", "Velocity", "Voltage", "Acceleration"};

    public LogReader(String file) throws IOException {
        this.file = file;
        Arrays.fill(entries, null);
        try {
            reader = new DataLogReader(this.file);
        } catch (IOException e) {
            System.err.println(" Can not open Log File " + file + " error:" + e.getMessage());
            throw e;
        }
        reader.forEach(this::process);
        addHirerchy();
    }

    private void process(DataLogRecord record) {
        if(record.isStart()) {
            var data = record.getStartData();
            new DataEntry(data);

        } else if(!record.isControl() && !record.isSetMetadata()) {
            entries[record.getEntry()].add(record);
        }
    }
    public void print() {
        top.print("");
    }

    public void print1() {
        for(DataEntry d : entries) {
            if(d != null) {
                System.out.println(" entry " + d.entryStart.entry + " " + d.entryStart.name + " have " + d.data.size() + " elements of type " + d.entryStart.type);
            }
        }
    }

    private void addHirerchy() {
        for(DataEntry d : entries) {
            addHirerchy(d);
        }
    }

    public Vector<entryHirerchy> motors() {
        Vector<entryHirerchy> motors = new Vector<>();
        top.addMotors(motors);
        return motors;       
    }

    private void addHirerchy(DataEntry d) {
        if(d != null) {
            String[] names = d.entryStart.name.split("/");
            addHirerchy(top, names, 0, d);
        }
    }

    private void addHirerchy(entryHirerchy e, String[] names, int index, DataEntry data) {
        if(index == names.length) {
            e.data = data;
            data.name = names[index-1];
            return;
        }
        String name = names[index];
        for(entryHirerchy child : e.child) {
            if(child.name.equals(name)) {
                addHirerchy(child, names, index+1, data);
                return;
            }
        }
        entryHirerchy eh = new entryHirerchy(name);
        e.child.add(eh);
        addHirerchy(eh, names, index+1, data);
    }

    public class entryHirerchy {
        entryHirerchy parent = null;
        ArrayList<entryHirerchy> child = new ArrayList<>();
        DataEntry data = null;
        String name;
        MotorData motorData = null;
        entryHirerchy(String name) {
            this.name = name;
        }

        void print(String offset) {
            if(data != null) {
                System.out.println(offset + data.toString());
            } else {
                System.out.println(offset + name);
            }
            for(entryHirerchy eh : child) {
                eh.print(offset + "    ");
            }
        }

        String childs() {
            StringBuilder sb = new StringBuilder();
            for(entryHirerchy eh : child) {
                sb.append("'");
                sb.append(eh.name);
                sb.append("' ");
            }
            return sb.toString();
        }

        boolean hasChildWithData(String name) {
            for(entryHirerchy eh : child) {
                if(eh.name.equals(name) && eh.data != null && eh.data.data.size() > 50) {
                    return true;
                }
            }
            return false;
        }

        boolean isMotor() {
            // is motor if it has Position, Velocity, Acceleration and Voltage childs with data
            for(String fld : MotorFields) {
                if(!hasChildWithData(fld)) {
                    return false;
                }
            }
            return true;
        }

        void addMotors(Vector<entryHirerchy> motors) {
            if(isMotor()) {
                motors.add(this);
                addMotorData();
            } else {
                for(entryHirerchy eh : child) {
                    eh.addMotors(motors);
                }
            }
        }

        entryHirerchy child(String name) {
            for(entryHirerchy eh : child) {
                if(eh.name.equals(name)) {
                    return eh;
                }
            }
            return null;

        }

        void addMotorData() {
            if(!isMotor()) {
                return;
            }
            motorData = new MotorData(this);
        }
        public MotorData geMotorData() {
            return motorData;
        }

        @Override
        public String toString() {
            if(motorData != null) {
                return name + "(" + motorData.data.size() + ") max Velocity " + motorData.maxVelocity;
            }
            return name;
        }
    }

    public class DataEntry {
        StartRecordData entryStart;
        ArrayList<DataLogRecord> data = new ArrayList<>();
        String name = null;
        public Iterator<DataLogRecord> iterator;
        DataEntry(StartRecordData start) {
            entryStart = start;
            entries[start.entry] = this;
        }

        void add(DataLogRecord record) {
            data.add(record);
        }

        void resetIterator() {
            iterator = data.iterator();
        }
        DataLogRecord next() {
            if(iterator.hasNext()) 
                return iterator.next();
            return null;
        }



        @Override
        public String toString() {
            return name + " have " + data.size() + " " + entryStart.type;
        }
    }


    public static void main(String[] args) {
        String file = "D:\\Projects\\wpilibLogAnalyzer\\FRC_20250113_153218.2.wpilog";
        try {
            LogReader log = new LogReader(file);
            Vector<entryHirerchy> motors = log.motors();
            for(entryHirerchy m : motors) {
                System.out.println(m.name);
                m.addMotorData();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
