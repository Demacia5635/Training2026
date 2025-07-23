public class LedsSubsystem {
    private double red;
    private double green;
    private double blue;

    private double red1;
    private double green1;
    private double blue1;


    public LedsSubsystem(double red, double green, double blue) {
        this.red = red;
        this.green = green;
        this.blue = blue;

        this.red1 = red;
        this.green1 = green;
        this.blue1 = blue;
    }

    public void removeColor(){
        this.red = 0.0;
        this.green = 0.0;
        this.blue = 0.0;
    }

    public void returnColor() {
        this.red = this.red1;
        this.green = this.green1;
        this.blue = this.blue1;
    }


}
