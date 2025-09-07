package frc.robot;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;

public class utils {
private final CommandXboxController controller = new CommandXboxController(Constants.DriverConstants.DriverID);
 public void controllerpizza() {

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
        double leftjoystickY = controller.getLeftY();
         double leftjoystickX = controller.getLeftX();
        if (Math.abs(controller.getLeftY())> 0.13 && Math.abs(controller.getLeftX()) >0.13) {
            double anglepizza = Math.atan(leftjoystickX/leftjoystickY); 
            return anglepizza;
        }
       return pastAngle;
    }
}