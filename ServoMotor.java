public class ServoMotor {
    private static final double MOTOR_SPEED = 30.0; // degrees/sec 
    private static final double MAX_VELOCITY = 90.0;
    private static final double MIN_VELOCITY = -90.0;
    private static final double STOP_VELOCITY = 0.0;
    private static final double MAX_ANGLE = 180.0;
    private static final double MIN_ANGLE = 0;
    private static final double START_ANGLE = 90.0;
    private static final double PERIOD_INTERVAL = 0.1; // seconds
    private static final double MOTOR_ANGLE_PER_INTERVAL = MOTOR_SPEED * PERIOD_INTERVAL;

    private double currentAngle = START_ANGLE;
    private double targetAngle = START_ANGLE;
    private String motorName;
    private double velocity = STOP_VELOCITY;

    public ServoMotor(String motorName) {
        this.motorName = motorName;
    }

    public double getAngle() {
        return currentAngle;
    }

    public void moveToAngle(double targetAngle) {
        if (targetAngle < MIN_ANGLE || targetAngle > MAX_ANGLE) {
            System.out.println("got invalid target angle: " + targetAngle);
            return;
        }
        this.targetAngle = targetAngle;

        setVelocityByAngle();
    }

    public boolean isAtAngle() {
        return currentAngle == targetAngle;
    }

    public void periodicUpdate () {
        if (velocity == MAX_VELOCITY) {
            currentAngle = Math.min(MAX_ANGLE, currentAngle + MOTOR_ANGLE_PER_INTERVAL);
        } else if (velocity == MIN_VELOCITY) {
            currentAngle = Math.max(MIN_ANGLE, currentAngle - MOTOR_ANGLE_PER_INTERVAL);
        }

        setVelocityByAngle();
    }

    private void setVelocityByAngle() {
        if (targetAngle > currentAngle) {
            velocity = MAX_VELOCITY;
        } else if (targetAngle < currentAngle) {
            velocity = MIN_VELOCITY;
        } else {
            // current angle already equals the target angle
            velocity = STOP_VELOCITY;
        }
    }
}


