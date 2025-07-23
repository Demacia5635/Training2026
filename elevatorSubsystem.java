public class elevatorSubsystem {
    private double currentHeight;
    private double force;
    private double velocity;
    private double accleration;
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
    public void isInLowHeight(){

    }
    public void currentHeightAfter002sec(){
        this.currentHeight = currentHeight + velocity*0.02;
        this.velocity=velocity + 0.02*accleration;
    }
    
}
