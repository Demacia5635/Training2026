public class swerveMuduke {
    private final TalonFX driveMotor;
    private final TalonFX steerMotor;
    private final Encoder steerEncoder;

    public SwerveModule(TalonFX drive, TalonFX steer, Encoder encoder) {
        driveMotor = drive;
        steerMotor = steer;
        steerEncoder = encoder;
    }

    public void setDesiredState(SwerveModuleState state) {
        double drivePower = state.speedMetersPerSecond / MAX_SPEED;
        driveMotor.set(ControlMode.PercentOutput, drivePower);
        double angleError = state.angle.getDegrees() - getCurrentAngle();
        double kP = 0.01; 
        steerMotor.set(ControlMode.PercentOutput, angleError * kP);
    }

    public double getCurrentAngle() {
        return steerEncoder.getDistance();
    }
}

