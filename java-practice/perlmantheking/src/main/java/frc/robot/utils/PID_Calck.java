// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.utils;

/** Add your docs here. */
public class PID_Calck {
    private double error;
    private double sumError;
    private double smartFriend;
    private double KP,KI,KD;
    private double setPoint;
    private double lastError;

    public PID_Calck(double KP,double KI,double KD){
        this.KP = KP;
        this.KI = KI;
        this.KD = KD;
    }
    public double caculate(){
        return error*KP+KI*sumError+(lastError-error)*KD;
    }
}
