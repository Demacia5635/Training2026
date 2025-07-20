package frc.Demacia.Sysid;

import java.util.ArrayList;
import java.util.Vector;

/**
 * Class for hirerchy of log items
 * can have multiple childs
 * can have the actual data
 */
public class LogEentryHirerchy {
    ArrayList<LogEentryHirerchy> childs = new ArrayList<>();
    LogDataEntry entryData = null; // for leafs - the actual data
    String name;
    MotorData motorData = null; // motor data if it is a motor
    int level = 0;


    LogEentryHirerchy(String name, int level) {
        this.name = name;
        this.level = level;
    }

    /**
     * nice print - with offset based on level
     * 
     */
    void print() {
        String offset = "   ".repeat(level);
        if(entryData != null) { // leaf - have actual data with all definition
            System.out.println(offset + entryData.toString());
        } else {
            System.out.println(offset + name);
        }
        for(LogEentryHirerchy child : childs) {
            child.print();
        }
    }

    /**
     * 
     * @return a String of all childs
     */
    String childsString() {
        StringBuilder sb = new StringBuilder();
        for(LogEentryHirerchy child : childs) {
            sb.append("'");
            sb.append(child.name);
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
        for(LogEentryHirerchy child : childs) {
            if(child.name.equals(name) && child.entryData != null && child.entryData.records.size() > 50) {
                return true;
            }
        }
        return false;
    }

    /**
     * 
     * 
     * @return
     */
    boolean isMotor() {
        return motorData != null;
    }

    /**
    * add all child motors, or itself if is motor
    * Vector for JList
    * @param motors
    */
    void addMotorsData() {
        boolean isMotor = true;
        for(String fld : LogReader.MotorFields) {
            if(!hasChildWithData(fld)) {
                isMotor = false;
                break;
            }
        }
        if(isMotor) {
            motorData = new MotorData(this); 
        }
        for(LogEentryHirerchy child : childs) {
            child.addMotorsData();
        }
    }

    /**
    * add all child motors, or itself if is motor
    * Vector for JList
    * @param motors
    */
    void addMotors(Vector<LogEentryHirerchy> motors) {
        if(isMotor()) {
            motors.add(this);
        }
        for(LogEentryHirerchy child : childs) {
            child.addMotors(motors);
        }
    }

    /**
     * get the child with the name
     * @param name
     * @return
     */
    LogEentryHirerchy child(String name) {
        for(LogEentryHirerchy eh : childs) {
            if(eh.name.equals(name)) {
                return eh;
            }
        }
        return null;
    }

    /**
     * 
     * @return motor data
     */
    public MotorData getMotorData() {
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