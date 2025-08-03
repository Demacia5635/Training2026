package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Modle;

public class Drive extends Command {

    
    private double power;
    private double distance;
    private double startPosition;
    private Modle modle;

    public Drive(double power, double distance , Modle modle) {
        this.modle = modle; // Initialize the Modle subsystem
        this.power = power;
        this.distance = distance;
    }
    @Override
    public void initialize() {
        // TODO Auto-generated method stub
        super.initialize();
    }
    
    @Override
  public void execute() {
    if (distance< modle.getdrivePosition()) {
      modle.setdrivePower(power);
      
     } // Logic to steer the robot towards the target angle
     else{
      modle.setdrivePower(-power);
     }  
  }

    @Override
  public void end(boolean interrupted) { modle.setdrivePower(0);}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return Math.abs(distance - modle.getdrivePosition()) < 0.1; // Command is finished when within 1 degree of target angle
   
    
  }
}




