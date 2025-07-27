
      /*  public class lesson_3
        {
            public static double caculationMotorPower(double voltage,double current)
            {
                return voltage*current;
            }
            public static double limitSpeed(double requestSpeed,double maxSpeed)
            {
                if(requestSpeed>maxSpeed)
                {
                    return maxSpeed;
                }
                else if (requestSpeed<-maxSpeed)
                {
                    return -maxSpeed;
                }
                return requestSpeed;
            }
            public static double caculationTravelTime(double distance, double speed)
            {
                if(speed==0)
                {
                    return Double.POSITIVE_INFINITY;
                }
                return Math.abs(distance/speed);
            }
            public static void main(String[] args) 
            {
                System.out.println(caculationMotorPower(7.5,12));
                
                System.out.println(limitSpeed(2,1.5));
                System.out.println(limitSpeed(1.8,1.5));
                System.out.println(limitSpeed(1.6,1.5));
                System.out.println(limitSpeed(1.4,1.5));
                System.out.println(limitSpeed(1.2,1.5));
                System.out.println(limitSpeed(1,1.5));
                System.out.println(limitSpeed(0.8,1.5));
                System.out.println(limitSpeed(0.6,1.5));
                System.out.println(limitSpeed(0.4,1.5));
                System.out.println(limitSpeed(0.2,1.5));
                System.out.println(limitSpeed(0,1.5));
                System.out.println(limitSpeed(-0.2,1.5));
                System.out.println(limitSpeed(-0.4,1.5));
                System.out.println(limitSpeed(-0.6,1.5));
                System.out.println(limitSpeed(-0.8,1.5));
                System.out.println(limitSpeed(-1,1.5));
                System.out.println(limitSpeed(-1.2,1.5));
                System.out.println(limitSpeed(-1.4,1.5));
                System.out.println(limitSpeed(-1.6,1.5));
                System.out.println(limitSpeed(-1.8,1.5));
                System.out.println(limitSpeed(-2,1.5));
                caculationTravelTime(4.6, 6.1);
                caculationTravelTime(56.35, 0);

                
            }
        }
       */
      //q 2

import java.io.Reader;

public class Wheel
      {
        private double diameter;
        private double velocity;
        private double position;
        private String name;

        public static double Constructor (string name,double diameter)
        {
            this.name=name;
            this.diameter=diameter;
        }
        public static double SetVelocity ( double rps)
        {
            this.rps=rps;
        }
        public static double GetLinearVelocity()
        {
            return rps;
        }
        public static double addRotation(double rotations)
        {

        }
        public static double GetDistance()
        {
            return smt;
        }
        public static double reset(){
            double velocity=0;
            double position=0;
            
        }
        public static double getVelocityInRPS()
        {

        }
        public static name(){
            return name;
        }
        

      }
      

