public class elevatorCommand {
    elevatorSubsystem elevator;
    public elevatorCommand(elevatorSubsystem elevator){
        this.elevator=elevator;
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
        }

    }
    public void Wait() throws InterruptedException{
        Thread.sleep(100);
        Up();
    }
}
