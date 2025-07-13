package frc.robot.utils.Log;


import java.util.ArrayList;

import edu.wpi.first.networktables.BooleanArrayPublisher;
import edu.wpi.first.networktables.BooleanArrayTopic;
import edu.wpi.first.networktables.BooleanPublisher;
import edu.wpi.first.networktables.BooleanTopic;
import edu.wpi.first.networktables.DoubleTopic;
import edu.wpi.first.networktables.FloatArrayPublisher;
import edu.wpi.first.networktables.FloatArrayTopic;
import edu.wpi.first.networktables.FloatPublisher;
import edu.wpi.first.networktables.Publisher;
import edu.wpi.first.util.datalog.BooleanArrayLogEntry;
import edu.wpi.first.util.datalog.BooleanLogEntry;
import edu.wpi.first.util.datalog.DataLogEntry;
import edu.wpi.first.util.datalog.DoubleArrayLogEntry;
import edu.wpi.first.util.datalog.DoubleLogEntry;
import edu.wpi.first.util.datalog.FloatArrayLogEntry;
import edu.wpi.first.util.datalog.FloatLogEntry;

/*
   * class for a single data entry
   */
  public class LogEntry {
    /**
     *
     */
    private final LogManager logManager;

    DataLogEntry entry; // wpilib log entry
    LogSupplier[] suppliers;
    String name;
    String meta;
    Publisher ntPublisher = null; 
    float[] floatData;
    boolean[] booleanData;
    boolean isDouble = true;
    boolean isArray = false;
    boolean updateNetworkTable;

    /*
     * the log levels are this:
     * 1 -> log if it is not in a compition
     * 2 -> only log
     * 3 -> log and add to network tables if not in a compition
     * 4 -> log and add to network tables
     */
    public int logLevel;

    private static ArrayList<LogEntry> logEntries = new ArrayList<>();

    public static void periodic() {
        for(LogEntry entry : logEntries) {
            entry.log();
        }
    }

    /*
     * Constructor with the suppliers and boolean if add to network table
     */
    LogEntry(String name, LogSupplier[] suplliers, int logLevel, String type, String subType, String meta) {

      this.logManager = LogManager.logManager;
      this.name = name;
      this.logLevel = logLevel;
      this.suppliers = suplliers;
      this.meta = "Type:" + type + ";Subtype:" + subType + ";" + meta;
      updateNetworkTable = (logLevel == 4 || logLevel == 3);
      isDouble = suplliers[0].isFloat();
      isArray = suplliers.length > 1;
      if(!isArray) {
        if(isDouble) { // Single Double
            entry = new DoubleLogEntry(logManager.log, name, this.meta);
            floatData = new float[] {suplliers[0].getFloat()};
            if(updateNetworkTable) {
                DoubleTopic dt = this.logManager.table.getDoubleTopic(name);
                ntPublisher = dt.publish();
            }
        } else { // Single Boolean
            entry = new BooleanLogEntry(logManager.log, name, this.meta);
            booleanData = new boolean[] {suplliers[0].getBoolean()};
            if(updateNetworkTable) {
                BooleanTopic bt = this.logManager.table.getBooleanTopic(name);
                ntPublisher = bt.publish();
            }
        }
      } else if(isDouble) { // double array
        entry = new DoubleArrayLogEntry(logManager.log, name, this.meta);
        floatData = new float[suplliers.length];
        for(int i = 0; i < suplliers.length; i++) {
            floatData[i] = suplliers[i].getFloat();
        }
        if(updateNetworkTable) {
            FloatArrayTopic dt = this.logManager.table.getFloatArrayTopic(name);
            ntPublisher = dt.publish();
        }
      } else { // Boolean array
        entry = new BooleanArrayLogEntry(logManager.log, name, this.meta);
        isDouble = false;
        booleanData = new boolean[suplliers.length];
        for(int i = 0; i < suplliers.length; i++) {
            booleanData[i] = suplliers[i].getBoolean();
        }
        if(updateNetworkTable) {
            BooleanArrayTopic dt = this.logManager.table.getBooleanArrayTopic(name);
            ntPublisher = dt.publish();
        }
      }
      logEntries.add(this);
    }

    /*
     * perform a periodic log
     * get the data from the getters and call the actual log
     */
    void log() {
        boolean changed = false;
        for(int i = 0; i < suppliers.length; i++) {
            if(isDouble) {
                floatData[i] = suppliers[i].getFloat();
            } else {
                booleanData[i] = suppliers[i].getBoolean();
            }
            changed = changed || suppliers[i].changed();
        }
        if(changed) {
            if(isDouble) {
                if(isArray) {
                    ((FloatArrayLogEntry)entry).append(floatData);
                    if(updateNetworkTable) {
                        ((FloatArrayPublisher)ntPublisher).accept(floatData);
                    }
                } else {
                    ((FloatLogEntry)entry).append(floatData[0]);
                    if(updateNetworkTable) {
                        ((FloatPublisher)ntPublisher).accept(floatData[0]);
                    }
                }
            } else if(isArray) {
                ((BooleanArrayLogEntry)entry).append(booleanData);
                if(updateNetworkTable) {
                    ((BooleanArrayPublisher)ntPublisher).accept(booleanData);
                }
            } else {
                ((BooleanLogEntry)entry).append(booleanData[0]);
                if(updateNetworkTable) {
                    ((BooleanPublisher)ntPublisher).accept(booleanData[0]);
                }
            }
        }
    }
    
    public void removeInComp() {
      if (logLevel == 3) {
        updateNetworkTable = false;
      }
    }
  }