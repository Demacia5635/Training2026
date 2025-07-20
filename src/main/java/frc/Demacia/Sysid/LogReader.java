package frc.Demacia.Sysid;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Vector;

import edu.wpi.first.util.datalog.DataLogReader;
import edu.wpi.first.util.datalog.DataLogRecord;
import edu.wpi.first.util.datalog.DataLogRecord.StartRecordData;



/**
 * Class to read the log
 * Create the hirerchy of entries
 * Crerate the list of motors
 */
public class LogReader {

    private DataLogReader reader;   // WPILIB log reader
    private String file;            // file name
    DataEntry[] entries = new DataEntry[1000]; // array of entries - assume that no more than 1000
    entryHirerchy top = new entryHirerchy("TOP"); // the TOP hirerchy

    public static final String[] MotorFields = {"Position", "Velocity", "Voltage", "Acceleration"}; // motor fields

    /**
     * Create the Log Reader
     * Can throw IO exception
     * @param file
     * @throws IOException
     */
    public LogReader(String file) throws IOException {
        this.file = file;
        Arrays.fill(entries, null); // set all entries to null
        try {
            reader = new DataLogReader(this.file);
            reader.forEach(this::process); // process all records
            addHirerchy(); // build the hirerchy
        } catch (IOException e) {
            System.err.println(" Can not open Log File " + file + " error:" + e.getMessage());
            throw e;
        }
    }

    /**
     * process the record from the file
     * @param record
     */
    private void process(DataLogRecord record) {
        if(record.isStart()) { // definition of a new data field
            var data = record.getStartData();
            new DataEntry(data); // add the data to the entries
        } else if(!record.isControl() && !record.isSetMetadata()) { // ignore end/meta record
            entries[record.getEntry()].add(record);
        }
    }
    /**
     * print the hirerchy
     */
    public void print() {
        top.print("");
    }

    /**
     * build the hirerchy
     */
    private void addHirerchy() {
        for(DataEntry d : entries) {
            addHirerchy(d);
        }
    }

    /**
     * create the list of motors 
     * @return Vector - for use by JList
     */
    public Vector<entryHirerchy> motors() {
        Vector<entryHirerchy> motors = new Vector<>();
        top.addMotors(motors);
        return motors;       
    }

    /**
     * Add the hirerchy of a specific entry
     * @param d
     */
    private void addHirerchy(DataEntry d) {
        if(d != null) {
            String[] names = d.entryStart.name.split("/");
            addHirerchy(top, names, 0, d); // add all the name hirerchy
        }
    }

    /**
     * 
     * @param currentHirerchy - add the childs to this level
     * @param names - all the names
     * @param index - the level
     * @param data - The base data entry
     */
    private void addHirerchy(entryHirerchy currentHirerchy, String[] names, int index, DataEntry data) {
        if(index == names.length) { // have all the hirerchy - add the data to this level
            currentHirerchy.data = data;
            data.name = names[index-1];
            return;
        }
        String name = names[index]; // the name of the next level
        for(entryHirerchy child : currentHirerchy.child) { // check if already have this name
            if(child.name.equals(name)) { // found - add the remaining to this entry
                addHirerchy(child, names, index+1, data);
                return;
            }
        }
        // new item - create and add
        entryHirerchy eh = new entryHirerchy(name); 
        currentHirerchy.child.add(eh);
        addHirerchy(eh, names, index+1, data);
    }


    /**
     * Class for hirerchy items
     */
    public class entryHirerchy {
        entryHirerchy parent = null;
        ArrayList<entryHirerchy> child = new ArrayList<>();
        DataEntry data = null; // for leafs - the actual data
        String name;
        MotorData motorData = null; // motor data if it is a motor
        entryHirerchy(String name) {
            this.name = name;
        }

        /**
         * 
         * @param offset - for hirerchy level print
         */
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

        /**
         * 
         * @return a String of all childs
         */
        String childs() {
            StringBuilder sb = new StringBuilder();
            for(entryHirerchy eh : child) {
                sb.append("'");
                sb.append(eh.name);
                sb.append("' ");
            }
            return sb.toString();
        }

        /**
         * Check if have child with the specific name and valid data
         * @param name
         * @return
         */
        boolean hasChildWithData(String name) {
            for(entryHirerchy eh : child) {
                if(eh.name.equals(name) && eh.data != null && eh.data.data.size() > 50) {
                    return true;
                }
            }
            return false;
        }

        /**
         * check if this entry is a motor
         * done vy checking that have all the subfileds as defined in MotorFields
         * @return
         */
        boolean isMotor() {
            // is motor if it has Position, Velocity, Acceleration and Voltage childs with data
            for(String fld : MotorFields) {
                if(!hasChildWithData(fld)) {
                    return false;
                }
            }
            return true;
        }
        /**
        *  create a Vector of motors
        * Vector for JList
        * @param motors
        */
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

        /**
         * get the child with the name
         * @param name
         * @return
         */
        entryHirerchy child(String name) {
            for(entryHirerchy eh : child) {
                if(eh.name.equals(name)) {
                    return eh;
                }
            }
            return null;

        }

        /**
         * add the motor data
         */
        void addMotorData() {
            if(!isMotor()) {
                return;
            }
            motorData = new MotorData(this);
        }

        /**
         * 
         * @return motor data
         */
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

    /**
     * Class to store records of an entry
     */
    public class DataEntry {
        StartRecordData entryStart; // the start record - name, type, meta
        ArrayList<DataLogRecord> data = new ArrayList<>(); // array of data records - time/data
        String name = null;
        public Iterator<DataLogRecord> iterator; // an iterator to iterate over the data

        /**
         * Constructor 
         * @param start record
         */
        DataEntry(StartRecordData start) {
            entryStart = start;
            entries[start.entry] = this;
        }

        /**
         * add a data record
         * @param record
         */
        void add(DataLogRecord record) {
            data.add(record);
        }

        /**
         * reset the iterator
         */
        void resetIterator() {
            iterator = data.iterator();
        }
        /**
         * get the next data from the iterator
         * @return
         */
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

}
