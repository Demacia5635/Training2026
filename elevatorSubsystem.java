public class elevatorSubsystem {
    private double currentHeight;
    private double force;
    private double velocity;
    private double accleration;
    private LedsSubsystem led;
    public elevatorSubsystem(){
        this.velocity=0;
        this.accleration=0;
        this.force=0;
    }
    public void setForce(int x){
        this.force=x;
        if(force==1){
            this.accleration=0.1;
        }
        else{
            this.accleration=-0.1;
        }
    }
    public void setCurrentHeight(double currentHeight){
        this.currentHeight=currentHeight;
    }
    public double getCurrentHeight(){
        return currentHeight;
    }
    public boolean isInMaxHeight(double currentHeight){
        return currentHeight==0.6;
    }
    public boolean isInLowHeight(double currentHeight){
        return currentHeight==0;
    }
    public void currentHeightAfter002sec(){
        this.currentHeight = currentHeight + velocity*0.02;
        this.velocity=velocity + 0.02*accleration;
    }
    public void setColorLedWhileMoving(){
        if(isInLowHeight(currentHeight)){
            led.setColor(256,256,256);
        }
        else if(isInMaxHeight(currentHeight)){
            led.setColor(256,0,0);
        }
        else if(this.accleration==0.1){
            led.setColor(256,0,0);
        }
        else{
           led.setColor(0,256,0);
        }
    }
}
