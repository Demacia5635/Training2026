public class BaseSensor {
    protected boolean calibrated;
    protected double value;

    public BaseSensor(boolean calibrated, double value){
        this.calibrated = calibrated;
        this.value = value;
    }
    public void calibrate(){
        this.calibrated = true;
    }
    public void clear(){
        this.value = 0;
    }
    public double readValue(){
        return this.value;
    }
}
