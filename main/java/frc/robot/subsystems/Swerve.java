package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.kinematics.SwerveDriveKinematics;
import edu.wpi.first.math.kinematics.SwerveModuleState;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import edu.wpi.first.wpilibj.Encoder;

public class Swerve extends SubsystemBase {
  
    private static final double MAX_SPEED = 4.0; 
    
    
    Translation2d FRONT_LEFT = new Translation2d(+0.381, +0.381);
    Translation2d FRONT_RIGHT = new Translation2d(+0.381, -0.381);  
    Translation2d BACK_LEFT = new Translation2d(-0.381, +0.381);
    Translation2d BACK_RIGHT = new Translation2d(-0.381, -0.381);

  
    public SwerveDriveKinematics kinematics = new SwerveDriveKinematics(
        FRONT_LEFT,
        FRONT_RIGHT,
        BACK_LEFT,
        BACK_RIGHT
    );

    private SwerveModule[] modules;

    public Swerve() {
         modules = new SwerveModule[4];
         modules[0] = new SwerveModule(new TalonFX(1), new TalonFX(2), new Encoder(0, 1));
         modules[1] = new SwerveModule(new TalonFX(1), new TalonFX(2), new Encoder(0, 1));
         modules[2] = new SwerveModule(new TalonFX(1), new TalonFX(2), new Encoder(0, 1));
         modules[3] = new SwerveModule(new TalonFX(1), new TalonFX(2), new Encoder(0, 1));
    }


    public void driveWithController() {
        double leftX = MathUtil.applyDeadband(controller.getLeftX(), DEADBAND);
        double leftY = -MathUtil.applyDeadband(controller.getLeftY(), DEADBAND); 
        double rightX = MathUtil.applyDeadband(controller.getRightX(), DEADBAND);        
       double vx = leftY * MAX_SPEED;
        double vy = leftX * MAX_SPEED; 
        double omega = rightX * Math.PI; 
        
        drive(vx, vy, omega);
    }
    
    public void drive(double vx, double vy, double omega) {
        ChassisSpeeds chassisSpeeds = new ChassisSpeeds(vx, vy, omega);
        SwerveModuleState[] states = kinematics.toSwerveModuleStates(chassisSpeeds);

        for (int i = 0; i < modules.length; i++) {
            modules[i].setDesiredState(states[i]);
        }
    }
    
    @Override
    public void periodic() {
        driveWithController();
    }

    public class SwerveModule {
        private final TalonFX driveMotor;
        private final TalonFX steerMotor;
        private final Encoder steerEncoder;

        public SwerveModule(TalonFX drive, TalonFX steer, Encoder encoder) {
            driveMotor = drive;
            steerMotor = steer;
            steerEncoder = encoder;
        }

        public void setDesiredState(SwerveModuleState state) {
            double drivePower = state.speedMetersPerSecond / MAX_SPEED;
            driveMotor.set(ControlMode.PercentOutput, drivePower);
            double angleError = state.angle.getDegrees() - getCurrentAngle();
            double kP = 0.01; 
            steerMotor.set(ControlMode.PercentOutput, angleError * kP);
        }

        public double getCurrentAngle() {
            return steerEncoder.getDistance();
        }
    }
}