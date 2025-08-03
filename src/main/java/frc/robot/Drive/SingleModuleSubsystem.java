package frc.robot.Drive;

import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.kinematics.SwerveModulePosition;
import edu.wpi.first.math.kinematics.SwerveModuleState;
import edu.wpi.first.util.sendable.SendableBuilder;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class SingleModuleSubsystem extends SubsystemBase {

    SwerveModule[] modules;
    SwerveModulePosition[] modulePositions;
    SwerveModuleState[] moduleState;

    /*
     * TODO
     * - add the system to RobotContainer - remove all others
     * - check elastic for steer/drive/abs data and check units and direction
     * - see all log table - make sure all OK
     * - activate steer and drive power - collect log
     * - run sysid -
     *      - transfer log to PC
     *      - set newLog in LogReader
     *      - check the syslog - list of motors, number of data, calculation
     * - display the PIDFF of steer in ekastic
     * - set data
     * - rotate steer using the set position - using position voltage
     * - update all gains in constants
     * - do the same for drive velocity
     * - check using steer motion instead of positionVoltage
     */

    public SingleModuleSubsystem() {
        super();
        modules = new SwerveModule[Constants.CONFIGS1.length];
        Translation2d[] modulePositionOnRobot = new Translation2d[modules.length];
        modulePositions = new SwerveModulePosition[modules.length];
        moduleState = new SwerveModuleState[modules.length];
        for(int i = 0; i < modules.length; i++) {
            modules[i] = new SwerveModule(Constants.CONFIGS1[i]);
            modulePositionOnRobot[i] = modules[i].config.positionRelativeToRobotCenter;
            moduleState[i] = modules[i].state;
            modulePositions[i] = modules[i].position;
            modules[i].configPID();
        }
        SmartDashboard.putData("SingleModule", this);
        SmartDashboard.putData("Steer Power", getSteerPowerCommand());
        SmartDashboard.putData("Drive Power", getDrivePowerCommand());
        SmartDashboard.putData("Steer Angle", getSteerTurnCommand());
        SmartDashboard.putData("Drive Velocity", getDriveVelocityCommand());

    }

    private Command getSteerPowerCommand() {
        SmartDashboard.putNumber("Steer Power:", 0);
        return new RunCommand(()-> { 
                double power = SmartDashboard.getNumber("Steer Power:", 0); 
                for(SwerveModule m : modules) {
                    m.setSteerPower(power);
                }
            },this);
    }
    private Command getDrivePowerCommand() {
        SmartDashboard.putNumber("Drive Power:", 0);
        return new RunCommand(()-> { 
                double power = SmartDashboard.getNumber("Drive Power:", 0); 
                for(SwerveModule m : modules) {
                    m.setDrivePower(power);
                }
            },this);
    }
    private Command getSteerTurnCommand() {
        SmartDashboard.putNumber("Steer Angle:", 0);
        return new RunCommand(()-> { 
                double angle = SmartDashboard.getNumber("Steer Angle:", 0); 
                for(SwerveModule m : modules) {
                    m.setSteerAngle(angle);
                }
            },this);
    }
    private Command getDriveVelocityCommand() {
        SmartDashboard.putNumber("Drive Velocity:", 0);
        return new RunCommand(()-> { 
                double velocity = SmartDashboard.getNumber("Drive Velocity:", 0); 
                for(SwerveModule m : modules) {
                    m.setDriveVelocity(velocity);
                }
            },this);
    }


    @Override
    public void periodic() {
        super.periodic();
        for(SwerveModule m : modules) {
            m.refreshPosition();
            m.refreshState();
        }
    }

    @Override
    public void initSendable(SendableBuilder builder) {
        super.initSendable(builder);
    }

}
