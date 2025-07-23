import.Math.random
public class elevatorCommand {
    elevatorSubsystem elevator;
    private LedsSubsystem led;
    public elevatorCommand(elevatorSubsystem elevator){
        this.elevator=elevator;
        this.led=led;
    }
    public void Up(){
        int count = 0;
        double time = 0;
        elevator.setForce(1);
        while(elevator.getCurrentHeight()<=0.6){
            elevator.currentHeightAfter002sec();
            count++;
            time= time+0.02;
            System.out.println("machzorim: "+count);
            System.out.println("time: "+ time);
            System.out.println("hight: "+ elevator.getCurrentHeight());
            if (count%3 == 0){
                led.setColor(Math.random()*255,Math.random()*255,Math.random()*255)
            }
        }
    }
    public void Down(){
        int count = 0;
        double time = 0;
        elevator.setForce(-1);
        while(elevator.getCurrentHeight()>=0){
            elevator.currentHeightAfter002sec();
            count++;
            time= time+0.02;
            System.out.println("machzorim: "+count);
            System.out.println("time: "+ time);
            System.out.println("hight: "+ elevator.getCurrentHeight());

        }
    }
    public void Wait() throws InterruptedException{
        Thread.sleep(100);
        Up();
        Thread.sleep(2000);
        Down();
    }
}