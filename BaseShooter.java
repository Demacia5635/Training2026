import java.security.PublicKey;

public class BaseShooter {
    protected boolean ready;
    protected double velocity;
    protected double angle;

    public BaseShooter(boolean ready, double velocity, double angle){
        this.angle = angle;
        this.ready = ready;
        this.velocity = velocity;
    }
    public boolean isReady(){
        return this.ready;
    }
    public void stop(){
        this.velocity = 0.0;
    }
    public void setVelocityAndAngle(double otherVelocity, double otherAngle){
        this.angle = otherAngle;
        this.velocity = otherVelocity;
    }
    Public void shoot(){
        System.out.println("shoot");
    }

}
