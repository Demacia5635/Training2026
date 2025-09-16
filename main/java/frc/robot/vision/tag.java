// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package tag;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.smartdashboard.Field2d;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.utils.LogManager;
import frc.robot.vision.Camera;
import frc.robot.vision.Camera.CameraType;
import static frc.robot.vision.utils.VisionConstants.*;
import java.util.function.Supplier;
//pich = ty
// yaw = yx
public class tag extends SubsystemBase {
    private Translation2d robotToTag;
    private Translation2d cameraToTag;
    private double alpha;
  
    private double wantedPip = 0;

  // NetworkTables communication for each camera
  private NetworkTable Table;

  // Vision processing variables
  
  /** Creates a new chasisis. */
    public Tag(Supplier<Rotation2d> getRobotAngle, Supplier<ChassisSpeeds> speeds, Camera camera) {
      this.getRobotAngle = getRobotAngle;
      this.speeds = speeds;
  
      this.camera = camera;
      Table = NetworkTableInstance.getDefault().getTable(camera.getTableName());
  
      field = new Field2d();
      latency = 0;
      is3D = Table.getEntry("pipeline").getInteger(0) == 1; 
    }
  

  @Override
  public void periodic() {
    cropEntry = Table.getEntry("crop");
    pipeEntry = Table.getEntry("pipeline");
    camToTagPitch = Table.getEntry("ty").getDouble(0.0);
    camToTagYaw = (-Table.getEntry("tx").getDouble(0.0)) + camera.getYaw();
    id = getTagId();

    latency = Table.getEntry("tl").getDouble(0.0) + Table.getEntry("cl").getDouble(0.0);

    if (Table.getEntry("tv").getDouble(0.0) != 0) {
      crop();
      // Only process tag IDs
      if (id > 0 && id < TAG_HEIGHT.length) {
        pose = new Pose2d(getOriginToRobot(), getRobotAngle.get());
        field.setRobotPose(pose);
        confidence = getConfidence();
        wantedPip = GetDistFromCamera() > 1 ? 0 : 0;
      }
    } else {
      cropStop();
      wantedPip = 0;
      pose = null;
    }

    if(wantedPip != Table.getEntry("getpipe").getDouble(0.0)){
    
  }
  }
 
 
public void set3D(boolean is3D){
  pipeEntry.setDouble(is3D ? 1 : 0);
  this.is3D = is3D;
}

public int getTagId(){
  return (int)Table.getEntry("tid").getDouble(0.0);
}

public double GetDistFromCamera() {
  if (camera.getCameraType() == CameraType.REEF) {
    alpha = camToTagPitch + camera.getPitch();
    dist = (Math.abs(height - camera.getHeight())) * (Math.tan(Math.toRadians(alpha)));
    dist = dist/Math.cos(Math.toRadians(camToTagYaw));
    //LogManager.log(camera.getName() + ":" + dist);
    return Math.abs(dist);
  }
  alpha = camToTagPitch + camera.getPitch();
  dist = (Math.abs(height - camera.getHeight())) / (Math.tan(Math.toRadians(alpha)));
  dist = dist/Math.cos(Math.toRadians(camToTagYaw));
  return Math.abs(dist);
}
public Translation2d getRobotToTagRR() {
  cameraToTag = new Translation2d(GetDistFromCamera(),
        Rotation2d.fromDegrees(camToTagYaw));
  robotToTag = new Translation2d(camera.getRobotToCamPosition().getX(), camera.getRobotToCamPosition().getY())
    .plus(cameraToTag);
  return robotToTag;
}
public Translation2d getCameraToTag() {
  return new Translation2d(GetDistFromCamera(),
      Rotation2d.fromDegrees(camToTagYaw));
}
public Translation2d getOriginToRobot() {

  origintoTag = O_TO_TAG[(int) this.id == -1 ? 0 : (int) this.id];

  height = TAG_HEIGHT[(int) this.id];
  if (origintoTag != null) {
    robotToTagRR = getRobotToTagRR();

      robotToTagFC = robotToTagRR.rotateBy(getRobotAngle.get());
      originToRobot = origintoTag.plus(robotToTagFC.rotateBy(Rotation2d.kPi));

      return originToRobot;
    }
    return new Translation2d();

  }
  private void crop() {
    double YawCrop = getYawCrop();
    double PitchCrop = getPitchCrop();
    double[] crop = { YawCrop - getCropOfset(), YawCrop + getCropOfset(), PitchCrop - getCropOfset(), PitchCrop + getCropOfset() };
    cropEntry.setDoubleArray(crop);
    }

    private double getCropOfset() {
      double crop = GetDistFromCamera() * CROP_CONSTAT;
      return MathUtil.clamp(crop, MIN_CROP, MAX_CROP);
    }

    private double getYawCrop(){
      double TagYaw = ((-camToTagYaw) + camera.getYaw()) / 31.25;
      return TagYaw + speeds.get().vyMetersPerSecond*PREDICT_Y + speeds.get().omegaRadiansPerSecond*PREDICT_OMEGA;
    }

    private double getPitchCrop(){
      double TagPitch = camToTagPitch / 24.45;
      return TagPitch + speeds.get().vxMetersPerSecond*PREDICT_X;
    }

  private void cropStop() {
    double[] crop = { -1, 1, -1, 1 };
    cropEntry.setDoubleArray(crop);
  }

  public Pose2d getPose() {
    return this.pose;
  }

  public double getTimestamp() {
    return latency;
  }

  public Rotation2d getRobotAngle() {
    Table.getEntry("pipeline").setNumber(1);
    try {
      Yaw3d = Table.getEntry("camerapose_targetspace").getDoubleArray(new double[] { 0, 0, 0, 0, 0, 0 })[4];
      tagID = Table.getEntry("tid").getDouble(0.0);
      Table.getEntry("pipeline").setNumber(0);
      yaw3dRotation2d = Rotation2d.fromDegrees(Yaw3d).rotateBy(Rotation2d.fromDegrees(camera.getYaw()))
          .rotateBy(TAG_ANGLE[(int) tagID]).rotateBy(Rotation2d.fromDegrees(180));
      return yaw3dRotation2d;

    } catch (Exception E) {
      getRobotAngle();
    }

    return null;
  }

  public double getPoseEstemationConfidence() {
    return this.confidence;
  }

  private double getConfidence() {
    double currentDist = GetDistFromCamera();
    if (currentDist > (is3D ? 20 : WORST_RELIABLE_DISTANCE)) {
      return 0.0;
    }
    if (currentDist <= BEST_RELIABLE_DISTANCE) {
      return 1.0;
    }
    double normalizedDist = (currentDist - BEST_RELIABLE_DISTANCE)
    / ((is3D ? 20 : WORST_RELIABLE_DISTANCE) - BEST_RELIABLE_DISTANCE);
    return Math.pow(1 - normalizedDist, 3);
  }
  public boolean isSeeTag(int id, double distance) {
    return Table.getEntry("tid").getDouble(0.0) == id && getRobotToTagRR().getNorm() <= distance;
  }
  public boolean isSeeTag(){
    return Table.getEntry("tid").getDouble(0.0) > 0;
  }

  public double getAngle() {
    return Yaw3d = Table.getEntry("botpose").getDoubleArray(new double[] { 0, 0, 0, 0, 0, 0 })[5];
  }

}