package frc.Demacia.utils;

import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;

public class VectorUtils {

    public static Translation2d intersct(Translation2d p1, Rotation2d v1Angle, Translation2d p2, Rotation2d v2Angle) {
        Translation2d R = new Translation2d(1, v1Angle);
        Translation2d S = new Translation2d(1, v2Angle);
        Translation2d p1ToP2 = p2.minus(p1);
        S.set(S.getX(), -S.getY());
        double t = R.dot(S);
        if(Math.abs(t) > 1e-10) {
            t = p1ToP2.dot(S) / t; 
            return p1.plus(R.timesSelf(t));
        }
        return null;
    }

}
