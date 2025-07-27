public class BallSensor extends BaseSensor {
    private boolean ball=true;
    public BallSensor(boolean calibrated, double value){
        super(calibrated,value);
    }
    public boolean haveBall(){
        return ball;
    }
}
