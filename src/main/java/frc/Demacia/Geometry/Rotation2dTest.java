// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.Demacia.Geometry;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;

public class Rotation2dTest {

  

  public static void main(String[] args) {
    Rotation2d r = new Rotation2d(1);
    Rotation2d r2 = new Rotation2d(2);
    Rotation2d r3 = new Rotation2d(3);
    System.out.println("r1=" + r + " r2=" + r2 + " r3=" + r3);
    r.plusSelf(r2);
    System.out.println("r1=" + r + " r2=" + r2 + " r3=" + r3);
    r.plusSelf(r3);
    System.out.println("r1=" + r + " r2=" + r2 + " r3=" + r3);
    System.out.println(r3.plus(r));
    System.out.println("r1=" + r + " r2=" + r2 + " r3=" + r3);
    Pose2d pose = new Pose2d(new Translation2d(1, 2), r);
    System.out.println("pose=" + pose);
    r.minusSelf(r2);
    System.out.println("pose=" + pose);
  }


}
