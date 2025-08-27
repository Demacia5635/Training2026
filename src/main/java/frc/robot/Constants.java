package frc.robot;

import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.util.Units;
import edu.wpi.first.math.kinematics.SwerveDriveKinematics;

public final class Constants {
  public static final class CAN {
    public static final int FL_DRIVE_ID = 1;
    public static final int FL_STEER_ID = 2;
<<<<<<< HEAD
    public static final int FR_DRIVE_ID = 4;
    public static final int FR_STEER_ID = 5;
    public static final int BL_DRIVE_ID = 7;
    public static final int BL_STEER_ID = 8;
    public static final int BR_DRIVE_ID = 10;
    public static final int BR_STEER_ID = 11;
    public static final int PIGEON_ID = 14;
    public static final int FL_CANcoder_ID = 3; 
    public static final int FR_CANcoder_ID = 6;
    public static final int BL_CANcoder_ID = 9;
    public static final int BR_CANcoder_ID = 12;
    public static final double FL_CANcoder_Ofset =0.3945;
    public static final double FR_CANcoder_Ofset =0.487;
    public static final double BL_CANcoder_Ofset =-0.0622;
    public static final double BR_CANcoder_Ofset =0.4;
=======
    public static final int FR_DRIVE_ID = 3;
    public static final int FR_STEER_ID = 4;
    public static final int BL_DRIVE_ID = 5;
    public static final int BL_STEER_ID = 6;
    public static final int BR_DRIVE_ID = 7;
    public static final int BR_STEER_ID = 8;
    public static final int PIGEON_ID = 9;
>>>>>>> d628868 (LastHomeWork)
  }

  public static final class Swerve {
    public static final double TRACKWIDTH = Units.inchesToMeters(21.0);
    public static final double WHEELBASE = Units.inchesToMeters(21.0);

    public static final Translation2d FRONT_LEFT_LOCATION = new Translation2d(+WHEELBASE/2.0, +TRACKWIDTH/2.0);
    public static final Translation2d FRONT_RIGHT_LOCATION = new Translation2d(+WHEELBASE/2.0, -TRACKWIDTH/2.0);
    public static final Translation2d BACK_LEFT_LOCATION = new Translation2d(-WHEELBASE/2.0, +TRACKWIDTH/2.0);
    public static final Translation2d BACK_RIGHT_LOCATION = new Translation2d(-WHEELBASE/2.0, -TRACKWIDTH/2.0);

    public static final SwerveDriveKinematics KINEMATICS = new SwerveDriveKinematics(
        FRONT_LEFT_LOCATION, FRONT_RIGHT_LOCATION, BACK_LEFT_LOCATION, BACK_RIGHT_LOCATION);

    public static final double WHEEL_DIAMETER_M = Units.inchesToMeters(4.0);
    public static final double WHEEL_CIRCUMFERENCE_M = Math.PI * WHEEL_DIAMETER_M;
    public static final double DRIVE_GEAR_RATIO = 6.75;
    public static final double STEER_GEAR_RATIO = 12.8;
    public static final double MAX_SPEED_MPS = 4.5;
    public static final double MAX_ANGULAR_SPEED_RAD_PER_S = 2 * Math.PI;

    public static final double DRIVE_KP = 0.1;
    public static final double DRIVE_KI = 0.0;
    public static final double DRIVE_KD = 0.0;

    public static final double STEER_KP = 40.0;
    public static final double STEER_KI = 0.0;
    public static final double STEER_KD = 0.5;

    public static final int DRIVE_SUPPLY_LIMIT_A = 40;
    public static final int STEER_SUPPLY_LIMIT_A = 30;

    public static final double DEADBAND = 0.08;
  }

  public static final class OI {
    public static final int XBOX_PORT = 0;
  }
}
