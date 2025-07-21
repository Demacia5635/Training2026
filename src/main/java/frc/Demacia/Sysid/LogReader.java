package frc.Demacia.Sysid;
import java.io.IOException;
import java.util.Arrays;
import java.util.Vector;

import edu.wpi.first.util.datalog.DataLogReader;
import edu.wpi.first.util.datalog.DataLogRecord;



/**
 * Class to read the log
 * Create the hirerchy of entries
 * Crerate the list of motors
 */
public class LogReader {

    LogDataEntry[] entries = new LogDataEntry[1000]; // array of entries - assume that no more than 1000
    LogEentryHirerchy top = new LogEentryHirerchy("TOP", 1); // the TOP hirerchy
    boolean debug = false;

    public static final String[] MotorFields = {"Position", "Velocity", "Voltage"}; // motor fields

    /**
     * Create the Log Reader
     * Can throw IO exception
     * @param file
     * @throws IOException
     */
    public LogReader(String file) throws IOException {
        this(file, false);
    }
    public LogReader(String file, boolean debug) throws IOException {
        this.debug = debug;
        Arrays.fill(entries, null); // set all entries to null
        try {
            DataLogReader reader = new DataLogReader(file);
            reader.forEach(this::process); // process all records
            addHirerchy(); // build the hirerchy
            top.addMotorsData();
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
            var startRecord = record.getStartData();
            entries[startRecord.entry] =  new LogDataEntry(startRecord); // add the data to the entries
            if(debug) {
                System.out.println(" added " + startRecord.entry + " " + startRecord.name + " type=" + startRecord.type + " meta=" + startRecord.metadata);
            }
        } else if(!record.isControl() && !record.isSetMetadata()) { // ignore end/meta record
            entries[record.getEntry()].add(record);
        }
    }
    /**
     * print the hirerchy
     */
    public void print() {
        top.print();
    }

    /**
     * build the hirerchy
     */
    private void addHirerchy() {
        for(LogDataEntry d : entries) {
            addHirerchy(d);
        }
    }
    
    /**
     * create the list of motors 
     * @return Vector - for use by JList
     */
    public Vector<LogEentryHirerchy> motors() {
        Vector<LogEentryHirerchy> motors = new Vector<>();
        top.addMotors(motors);
        return motors;       
    }

    /**
     * Add the hirerchy of a specific entry
     * @param logEntry
     */
    private void addHirerchy(LogDataEntry logEntry) {
        if(logEntry != null) {
            String[] names = logEntry.startRecord.name.split("/");
            addHirerchy(top, names, 0, logEntry); // add all the name hirerchy
        }
    }

    /**
     * 
     * @param currentHirerchy - add the childs to this level
     * @param names - all the names
     * @param index - the level
     * @param data - The base data entry
     */
    private void addHirerchy(LogEentryHirerchy currentHirerchy, String[] names, int index, LogDataEntry data) {
        if(index == names.length) { // have all the hirerchy - add the data to this level
            currentHirerchy.entryData = data;
            data.name = names[index-1];
            return;
        }
        String name = names[index]; // the name of the next level
        for(LogEentryHirerchy child : currentHirerchy.childs) { // check if already have this name
            if(child.name.equals(name)) { // found - add the remaining to this entry
                addHirerchy(child, names, index+1, data);
                return;
            }
        }
        // new item - create and add
        LogEentryHirerchy newHirerchy = new LogEentryHirerchy(name, currentHirerchy.level + 1); 
        currentHirerchy.childs.add(newHirerchy);
        addHirerchy(newHirerchy, names, index+1, data);
    }

    public static void main(String[] args) {
        String fileName = "D:\\Projects\\2026Training\\Demacia\\Training2026\\logs\\FRC_20250721_102912.wpilog";
        try {
            LogReader log = new LogReader(fileName, true);
            log.print();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

}
