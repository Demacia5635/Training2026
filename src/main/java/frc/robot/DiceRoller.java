// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import java.util.Random;

import edu.wpi.first.util.sendable.Sendable;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.util.sendable.SendableBuilder;

/** Add your docs here. */
public class DiceRoller implements Sendable{
    private static final Random random = new Random();
    private int num=1;
    // פונקציה שמחזירה מספר אקראי בין 1 ל־6
    public DiceRoller(){
    }    
    public void reRoll() {
        num = 1 + (int)(Math.random() * 6);
    }

    public int getNumber() {
        return num;
    }

    @Override
    public void initSendable(SendableBuilder builder) {
        builder.setSmartDashboardType("rolingNumberButton");
        // מוסיפים ערך קריא בלבד (המספר המוגרל)
        builder.addDoubleProperty("theRandomNumber", this::getNumber, null);
        builder.addBooleanProperty("reRoll", 
            () -> false,     // הערך לקריאה (פה לא חשוב, תמיד false)
            pressed -> {      // מה קורה כשמשנים ל-true
                if (pressed) {
                    reRoll();
                }
            });
    }
}

