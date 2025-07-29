package frc.Demacia.utils;

import edu.wpi.first.wpilibj.XboxController;

public class XboxUtils {

    public static double getJSvalue(XboxController controller, boolean useLeft, boolean useY, double deadband, boolean usePower) {
        double raw = 0;
        if(useLeft) {
            if(useY) {
                raw = -controller.getLeftY();
            } else {
                raw = controller.getLeftX();
            }
        } else {
            if(useY) {
                raw = -controller.getRightY();
            } else {
                raw = controller.getRightX();
            }
        }
        if(raw < -deadband) {
            if(usePower) {
                return -raw*raw;
            } else {
                return raw;
            }
        } else if(raw > deadband) {
            if(usePower) {
                return raw*raw;
            } else {
                return raw;
            }
        }
        return 0;
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

}
