package frc.robot;

public class Utilities {

    public static double clamp(double value, double min, double max) {
        return value < min ? min : value > max ? max : value;
    }
    
}
