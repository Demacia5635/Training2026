package frc.Demacia.utils.Motors;

public interface MotorInterface {
    void changeSlot(int slot);
    void setNeutralMode(boolean isBrake);
    void setDuty(double power);
    void setVoltage(double voltage);
    void setVelocity(double velocity, double feedForward);
    void setVelocity(double velocity);
    void setMotion(double position, double feedForward);
    void setMotion(double position);
    void setPositionVoltage(double position, double feedForward);
    void setPositionVoltage(double position);
    void setVelocityWithFeedForward(double velocity);
    void setMotionWithFeedForward(double velocity);
    String getCurrentControlMode();
    double getCurrentClosedLoopSP();
    double getCurrentClosedLoopError();
    double getCurrentPosition();
    double getCurrentVelocity();
    double getCurrentAcceleration();
    double getCurrentVoltage();
}
