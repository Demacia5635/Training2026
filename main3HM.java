public class main3HM {

    
    
    public static void main(String[] args) {
        ServoMotor motor1 = new ServoMotor("motor1");
        ServoMotor motor2 = new ServoMotor("motor2");
        GameController controller = new GameController();

        controller.setButton(2);
        for (int i = 0 ; i < 50 ; i++) {
            
            if (controller.isPressed(1)) {
                motor1.moveToAngle(0.0);
                motor2.moveToAngle(180.0);
            } else if (controller.isPressed(2)) {
                motor2.moveToAngle(44.0);
            } else if (controller.isPressed(3)) {
                motor1.moveToAngle(135.0);
            } else if (controller.isPressed(4)) {
                motor1.moveToAngle(90.0);
                motor2.moveToAngle(90.0);
            }
            motor1.periodicUpdate();
            motor2.periodicUpdate();
            System.out.println("Iteration number " + i);
            System.out.println("Motor1 current angle is:" + motor1.getAngle());
            System.out.println("Motor2 current angle is:" + motor2.getAngle());

            if (i == 25) {
                controller.releaseButton(2);
                controller.setButton(1);
            }

        }
    }
    
}
