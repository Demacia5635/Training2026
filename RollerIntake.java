import java.security.PrivateKey;

public class RollerIntake {
    private Motor inAndOutBallMotor;
    private Motor inAndOutManganonMotor;
    private BallSensor entryBallSensor;
    private BallSensor finalBallSensor;
    private LimitSensor foldedLimitSensor;
    private LimitSensor collecLimitSensor;

    public RollerIntake(Motor inAndOutBallMotor, Motor inAndOutManganonMotor, BallSensor entryBallSensor, BallSensor finalBallSensor, LimitSensor foldedLimitSensor, LimitSensor collecLimitSensor){
        this.collecLimitSensor = collecLimitSensor;
        this.entryBallSensor = entryBallSensor;
        this.finalBallSensor = finalBallSensor;
        this.foldedLimitSensor = foldedLimitSensor;
        this.inAndOutBallMotor = inAndOutBallMotor;
        this.inAndOutManganonMotor = inAndOutManganonMotor;
    }
    public void collector(int i){
        for(int j = 0; j < i; j++){
            inAndOutManganonMotor.setPower();
            while(!collecLimitSensor.atLimit()){
            }
            inAndOutManganonMotor.stop();
            inAndOutBallMotor.setPower();
            while(!entryBallSensor.haveBall()){
            }
            inAndOutBallMotor.setPower();
            while(!finalBallSensor.haveBall()){}
            inAndOutBallMotor.stop();
            inAndOutManganonMotor.setPower();
            while(!foldedLimitSensor.atLimit();){}
            inAndOutManganonMotor.stop();
        }
    }
}
