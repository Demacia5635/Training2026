package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;

public class Drive extends Command {

    
    private double power;
    private double distance;
    private double startPosition;

    public Drive(double power, double distance) {
        this.power = power;
        this.distance = distance;
    }
    @Override
    public void initialize() {
        // TODO Auto-generated method stub
        super.initialize();
    }
    

}
