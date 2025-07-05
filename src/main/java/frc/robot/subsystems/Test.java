    package frc.robot.subsystems;

import java.nio.channels.Pipe;

import org.opencv.core.Mat;
import org.opencv.imgproc.Imgproc;

import com.ctre.phoenix6.swerve.SwerveModule;
import com.ctre.phoenix6.swerve.jni.SwerveJNI.ModuleState;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.math.estimator.PoseEstimator;
import edu.wpi.first.math.estimator.SwerveDrivePoseEstimator;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.math.kinematics.SwerveDriveKinematics;
import edu.wpi.first.math.kinematics.SwerveModuleState;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.vision.VisionPipeline;
import edu.wpi.first.vision.VisionThread;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.smartdashboard.Field2d;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Test {
    
        public static void main1(String[] args) {
            
            ModuleState[] moduleStates;
            ChassisSpeeds speeds = new ChassisSpeeds(1, 0.5, Math.toRadians(30));
            Compressor c = new Compressor(PneumaticsModuleType.CTREPCM);
            DoubleSolenoid ds = new DoubleSolenoid(PneumaticsModuleType.CTREPCM,4,5);


/* 
            ChassisSpeeds.fromRobotRelativeSpeeds(speeds, getRobotAngle())
            ChassisSpeeds.fromFieldRelativeSpeeds(speeds, getRobotAngle())
            SwerveDriveKinematics kinematics = new SwerveDriveKinematics(new Translation2d[] { 
                    new Translation2d(0.35, 0.3),
                    new Translation2d(0.35, -0.3),
                    new Translation2d(-0.35, 0.3),
                    new Translation2d(-0.35, -0.3)});
            kinematics.toSwerveModuleStates(speeds)
            kinematics.toChassisSpeeds(moduleStates)
            Translation2d point1 = new Translation2d(1,3);
            Translation2d point2 = new Translation2d(2,-1);
            point1.getAngle(), 
            point1.getNorm(), 
            point1.getX(), 
            point1.getY()
            point1.unaryMinus()
            point1.minus(point2)
            point1.plus(point2)
            point1.getDistance(point2)
            point1.div(2)
            point1.times(3)
            point1.rotateBy(Rotation2d.fromDegrees(35))
            point1.rotateAround(point2, Rotation2d.fromRadians(1))
            point1.unaryMinus()

            Rotation2d rot1 = Rotation2d.fromDegrees(35)
            Rotation2d rot2 = new Rotation2d(Math.PI/3)
            Rotation2d rot3 = new Rotation2d(3,2)
            rot1.getDegrees()
            rot1.getRadians()
            rot1.getRotations()
            rot1.div(2)
            rot1.times(3)
            rot1.getSin()
            rot1.getCos()
            rot1.getTan()
            rot1.plus(rot2)
            rot1.minus(rot3)
            rot1.rotateBy(rot1)
            rot1.unaryMinus()

            Pose2d pose = new Pose2d(point2, rot3)
            pose.getX()
            pose.getY()
            pose.getRotation()
            pose.getTranslation()
            pose.minus(pose)
            pose.plus(pose)

            MathUtil.inputModulus(rot1.getDegrees(),-180,180)
            SwerveModuleState.optimize(null, rot3)
            SwerveDriveKinematics.desaturateWheelSpeeds(null, null);

            SwerveDrivePoseEstimator poseEstimator = new SwerveDrivePoseEstimator(kinematics, gyroAngle,modulesPosition,initialPosition);
            poseEstimator.update(gyroAngle, modulesPositions);
            poseEstimator.getEstimatedPosition()
            poseEstimator.resetPose(pose);
            poseEstimator.addVisionMeasurement(pose, time);

            Field2d field2d = new Field2d();
            SmartDashboard.putData("Robot Position", field2d);
            field2d.setRobotPose(pose);
            field2d.getObject("traj").setTrajectory(trajectory);
            
            
            */
        }

        public static void intFunction(int intParameter) {
            intParameter = intParameter * 2;
        }
        public static class IntClass {
            public int intOfObject;
            public IntClass(int initialValue) {
                intOfObject = initialValue;
            }
        }
        public static void intClassFunction(IntClass intClassParameter) {
            intClassParameter.intOfObject = intClassParameter.intOfObject * 2;
        }

        public static double getAverage(double[] arrayOfDoubls) {
            if(arrayOfDoubls.length == 0) {
                return 0;
            }
            double sum = 0;
            for(double value : arrayOfDoubls) {
                sum += value;
            }
            return sum / arrayOfDoubls.length;
        }


        public static void main(String[] args) {

            double[] arrayOfDoubls = new double[1000000];
            for(int i = 0; i < arrayOfDoubls.length; i++) {
                arrayOfDoubls[i] = 1000 * Math.random() * i;
            }
            double average = getAverage(arrayOfDoubls);
            System.out.println("average = " + average);

            int test = 3;
            IntClass intInObject = new IntClass(3);
            System.out.println("test = " + test);
            System.out.println("intInObect = " + intInObject.intOfObject);
            intFunction(test);
            intClassFunction(intInObject);
            System.out.println("test = " + test);
            System.out.println("intInObect = " + intInObject.intOfObject);
        }

    }