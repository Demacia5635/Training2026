package frc.robot;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;

public class controllerpizza {
private final CommandXboxController controller = new CommandXboxController(Constants.DriverConstants.DriverID);
 public controllerpizza() {

 }

public double driveparameter() {
    double rightjoystickY = controller.getRightY();
    if(rightjoystickY>0.5)  
    {
        return 1;
    }
    else if(rightjoystickY<-0.5)
    {
        return -1;
    }
    else
    {
        return 0;
    }
}
    public double pizzasteerparmeter(double pastAngle) {
        if (Math.abs(controller.getLeftY())> 0.7 && Math.abs(controller.getLeftX()) >0.7) {
            double leftjoystickY = controller.getLeftY();
            double leftjoystickX = controller.getLeftX();
            double anglepizza = Math.atan(leftjoystickX/leftjoystickY); 
            return anglepizza;
        }
       return pastAngle;
    }
}