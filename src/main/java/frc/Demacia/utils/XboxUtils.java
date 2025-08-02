package frc.Demacia.utils;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;

public class XboxUtils {

        public static enum JoystickSide { LeftX, LeftY, RightX, RightY;
            double value(XboxController controller) {
                switch (this) {
                    case LeftX:
                        return controller.getLeftX();
                    case LeftY:
                        return -controller.getLeftY();
                    case RightX:
                        return controller.getRightX();
                    case RightY:
                        return -controller.getRightY();
                    default:
                        return 0;
                }

            }
            double value(CommandXboxController controller) {
                return value(controller.getHID());
            };
        }


    public static double getNormalized(double value, double deadband, boolean power) {
        return  value < -deadband ? (power ? -value*value : value):
                value > deadband ? (power ? value*value : value) :
                0;
    }
    
    public static double getNormalized(double value, double deadband) {
        return getNormalized(value, deadband, true);
    }
    public static double getNormalized(double value) {
        return getNormalized(value, 0.1, true);
    }

    public static double getJSvalue(XboxController controller, JoystickSide side, double deadband, boolean usePower) {
        return getNormalized(side.value(controller), deadband, usePower);
    }
    public static double getJSvalue(XboxController controller, JoystickSide side, double deadband) {
        return getJSvalue(controller, side, deadband, true);
    }
    public static double getJSvalue(XboxController controller, JoystickSide side) {
        return getJSvalue(controller, side, 0.1, true);
    }

    public static double getJSvalue(CommandXboxController controller, JoystickSide side, double deadband, boolean usePower) {
        return getJSvalue(controller.getHID(),side,deadband,usePower);
    }
    public static double getJSvalue(CommandXboxController controller, JoystickSide side, double deadband) {
        return getJSvalue(controller.getHID(), side, deadband, true);
    }
    public static double getJSvalue(CommandXboxController controller, JoystickSide side) {
        return getJSvalue(controller.getHID(), side, 0.1, true);
    }


}
