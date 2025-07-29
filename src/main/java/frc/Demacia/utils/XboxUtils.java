package frc.Demacia.utils;

import edu.wpi.first.wpilibj.XboxController;

public class XboxUtils {

    public static double getJSvalue(XboxController controller, boolean useLeft, boolean useY, double deadband, boolean usePower) {
        double value = 0;
        if(useLeft) {
            if(useY) {
                value = -controller.getLeftY();
            } else {
                value = controller.getLeftX();
            }
        } else {
            if(useY) {
                value = -controller.getRightY();
            } else {
                value = controller.getRightX();
            }
        }
        return getNormalized(value, deadband, usePower);
    }

    public static double getNormalized(double value, double deadband, boolean power) {
        if(value < -deadband) {
            if(power) {
                return -value*value;
            } else {
                return value;
            }
        } else if(value > deadband) {
            if(power) {
                return value*value;
            } else {
                return value;
            }
        }
        return 0;
    }
    public static double getNormalized(double value, double deadband) {
        return getNormalized(value, deadband, true);
    }
    public static double getNormalized(double value) {
        return getNormalized(value, 0.1, true);
    }
    public static double getJSvalue(XboxController controller, boolean useLeft, double deadband, boolean usePower) {
        return getJSvalue(controller, useLeft, true, deadband, usePower);
    }
    public static double getJSvalue(XboxController controller, boolean useLeft, double deadband) {
        return getJSvalue(controller, useLeft, true, deadband, true);
    }
    public static double getJSvalue(XboxController controller, boolean useLeft) {
        return getJSvalue(controller, useLeft, true, 0.1, true);
    }
    public static double getJSXvalue(XboxController controller, boolean useLeft) {
        return getJSvalue(controller, useLeft, false, 0.1, true);
    }
    public static double getJSYvalue(XboxController controller, boolean useLeft) {
        return getJSvalue(controller, useLeft, true, 0.1, true);
    }

}
