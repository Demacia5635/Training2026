package frc.Demacia.utils.Motors;

import java.util.Random;
import edu.wpi.first.math.MathUtil;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.Subsystem;

public class RandomPowerGenerator {

    enum Strategy {Stright, Step, Random};

    double maxPower;
    double minPower;
    double maxRiseTime;

    Strategy strategy;
    double maxChange;
    double lastPower;
    double nextTarget;
    double stepEndTime;
    double lastTime;
    Random random;

    public RandomPowerGenerator(double min, double max, double ramp) {
        maxPower = max;
        minPower = min;
        maxRiseTime = ramp;
        random = new Random(System.currentTimeMillis());
        maxChange = 0.02/ramp;
        strategy = nextStrategy();
        lastPower = 0;
        nextTarget = maxPower;
        lastTime = time();
        stepEndTime = lastTime;
    }

    private Strategy nextStrategy() {
        double r = random.nextDouble();
        return r > 0.66 ? Strategy.Random : r > 0.33 ? Strategy.Step : Strategy.Stright;
    }

    double time() {
        return System.currentTimeMillis()/1000.0;
    }

    private void nextTarget() {
        nextTarget = maxPower == nextTarget ? minPower : maxPower;
        strategy = nextStrategy();
    }

    private double nextChange(double direction) {
        return (random.nextDouble()*1.5 - 0.5) * maxChange * direction;
    }

    public double next() {
        double direction = Math.signum(nextTarget - lastPower);
        if(direction == 0) {
            nextTarget();
            return lastPower;
        }

        double time = time();
        switch (strategy) {
            case Random:
                lastPower = MathUtil.clamp(lastPower + nextChange(direction) , minPower, maxPower);
                break;
            case Step:
                if(time > stepEndTime) {
                    stepEndTime = time + random.nextDouble()*0.5;
                    lastPower += random.nextDouble()*maxChange*direction;
                }
                break;
            case Stright:
                lastPower += random.nextDouble()*maxChange*direction;
                break;
            default:
                break;
        }
        lastPower = MathUtil.clamp(lastPower, minPower, maxPower);
        lastTime = time;
        return lastPower;
    }

    public static Command getRandomPowerCommand(MotorInterface motor, RandomPowerGenerator generator, Subsystem subsystem) {
        return new RunCommand(()->motor.setDuty(generator.next()), subsystem);
    }

    public static void main(String[] args) {
        RandomPowerGenerator r = new RandomPowerGenerator(-0.5,0.6,0.3);
        for(int i = 0; i < 100; i++) {
            System.out.printf("%3d: %3.2f s=%s %.3f %.3f %.3f\n", i, r.next(), r.strategy, r.lastTime, r.stepEndTime, r.maxChange);
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
 
}
