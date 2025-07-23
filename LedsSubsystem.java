public class LedsSubsystem {
    private double red;
    private double green;
    private double blue;


    public void setColor(double red, double green, double blue) {
        this.red = red;
        this.green = green;
        this.blue = blue;
    }

    public void removeColor(){
        this.red = 0.0;
        this.green = 0.0;
        this.blue = 0.0;
    }

    public void returnColor() {
        this.red = red;
        this.green = green;
        this.blue = blue;
    }


}
