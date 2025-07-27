import java.util.Scanner;

public class first_proj {
    public static void main(String[] args) {
         /* lesson homework 1
          System.out.println("hi");
        Scanner scanner = new Scanner(System.in);
        System.out.println("whats the robots weight?");
        double weight = scanner.nextDouble();
        System.out.println("whats the robots max speed?");
        double maxSpeed = scanner.nextDouble();
        System.out.println("whats the max acceleration?");
        double maxAccel = scanner.nextDouble();
        System.out.println("whats the robots name?");
        String name = scanner.next();
        double timeToMaxSpeed = maxSpeed / maxAccel;
        System.out.println("time to max speed is " + timeToMaxSpeed);
        double distanceTillMaxSpeed = 0.5 * maxAccel * timeToMaxSpeed * timeToMaxSpeed;
        double maxEnergy = 0.5 * weight * maxSpeed * maxSpeed;
        // v^2 = 2as => a = v^2/2s
        double accelForDistanceSeven= (maxSpeed * maxSpeed)/(2*0.07); 
        double timeUntillStop=(maxSpeed)/accelForDistanceSeven;
        System.out.println("the distance the robot is passng till max speed is: " + distanceTillMaxSpeed);
        System.out.println("the time it takes the robot to pass 4 meters is: " + timeToPassDistance(4.0, maxSpeed, maxAccel));
        System.out.println("the time it takes the robot to pass 12 meters is: " + timeToPassDistance(12.0, maxSpeed, maxAccel));
        System.out.println("the stopping accel of the robot for seven cm is: -" + accelForDistanceSeven);
         //t=(v2-v1)/a
         System.out.println(" time until the robot stops from max speed, passing 7cm : " +timeUntillStop );
        scanner.close();

 S
    
    static double timeToPassDistance(double distance, double maxSpeed, double accel) {
        double timeToMaxSpeed = maxSpeed / accel;
        double distanceTillMaxSpeed = 0.5 * accel * timeToMaxSpeed * timeToMaxSpeed;

        if (distance >= 2 * distanceTillMaxSpeed) {
            return 2 * timeToMaxSpeed + (distance - 2 * distanceTillMaxSpeed) / maxSpeed;
        } else {
            // t = sqrt(2s/a)
            double halfTime = Math.sqrt(distance / accel);
            return 2 * halfTime;
        }*/
        
    }
}
