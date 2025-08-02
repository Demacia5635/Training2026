package frc.Demacia.utils;

import com.revrobotics.spark.ClosedLoopSlot;
import com.revrobotics.spark.SparkLowLevel;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.config.SparkBaseConfig;
import com.revrobotics.spark.config.SparkMaxConfig;

import edu.wpi.first.util.sendable.Sendable;
import edu.wpi.first.util.sendable.SendableBuilder;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.Alert.AlertType;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.Demacia.utils.Log.LogManager;
import frc.Demacia.utils.Log.MotorLogEntry;
import frc.robot.RobotContainer;

public class SparkMotor extends SparkMax implements Sendable, Motor {

  private SparkConfig config;
  private String name;
  private SparkMaxConfig cfg;
  private ClosedLoopSlot slot = ClosedLoopSlot.kSlot0;
  private ControlType controlType = ControlType.kDutyCycle;

  private String lastControlMode;
  private double lastVelocity;
  private double lastAcceleration;
  private double setPoint = 0;
  private int lastCycleNum = 0;
  private double lastTime = 0;

  public SparkMotor(SparkConfig config) {
    super(config.id, SparkLowLevel.MotorType.kBrushless);
    this.config = config;
    name = config.name;
    configMotor();
    addLog();
    LogManager.log(name + " motor initialized");
  }

  private void configMotor() {
    cfg = new SparkMaxConfig();
    cfg.smartCurrentLimit((int) config.maxCurrent);
    cfg.openLoopRampRate(config.rampUpTime);
    cfg.closedLoopRampRate(config.rampUpTime);
    cfg.inverted(config.inverted);
    cfg.idleMode(config.brake ? SparkBaseConfig.IdleMode.kBrake : SparkBaseConfig.IdleMode.kCoast);
    cfg.voltageCompensation(config.maxVolt);
    cfg.closedLoop.pidf(config.pid.kp, config.pid.ki, config.pid.kd, config.pid.kv, ClosedLoopSlot.kSlot0);
    if (config.pid1 != null) {
      cfg.closedLoop.pidf(config.pid1.kp, config.pid1.ki, config.pid1.kd, config.pid1.kv, ClosedLoopSlot.kSlot1);
    }
    if (config.pid2 != null) {
      cfg.closedLoop.pidf(config.pid2.kp, config.pid2.ki, config.pid2.kd, config.pid2.kv, ClosedLoopSlot.kSlot2);
    }
    cfg.encoder.positionConversionFactor(config.motorRatio);
    cfg.encoder.velocityConversionFactor(config.motorRatio / 60);
    if (config.maxVelocity != 0) {
      cfg.closedLoop.maxMotion.maxVelocity(config.maxVelocity).maxAcceleration(config.maxAcceleration);
    }
    getEncoder();
    this.configure(cfg, ResetMode.kNoResetSafeParameters, PersistMode.kPersistParameters);
  }

  private void addLog() {
    MotorLogEntry.add(this);

  }

  /**
   * change the slot of the pid and feed forward.
   * will not work if the slot is null
   * 
   * @param slot the wanted slot between 0 and 2
   */
  public void changeSlot(int slot) {
    if (slot < 0 || slot > 2) {
      LogManager.log("slot is not between 0 and 2", AlertType.kError);
      return;
    }
    if (slot == 0 && config.pid == null) {
      LogManager.log("slot is null, add config for slot 0", AlertType.kError);
      return;
    }
    if (slot == 1 && config.pid1 == null) {
      LogManager.log("slot is null, add config for slot 1", AlertType.kError);
      return;
    }
    if (slot == 2 && config.pid2 == null) {
      LogManager.log("slot is null, add config for slot 2", AlertType.kError);
      return;
    }
    this.slot = slot == 0 ? ClosedLoopSlot.kSlot0 : slot == 1 ? ClosedLoopSlot.kSlot1 : ClosedLoopSlot.kSlot2;
  }

  /*
   * set motor to brake or coast
   */
  public void setNeutralMode(boolean isBrake) {
    cfg.idleMode(config.brake ? SparkBaseConfig.IdleMode.kBrake : SparkBaseConfig.IdleMode.kCoast);
    configure(cfg, ResetMode.kNoResetSafeParameters, PersistMode.kNoPersistParameters);
  }

  /**
   * set power from 1 to -1 (v/12) no PID/FF
   * 
   * @param power the wanted power between -1 to 1
   */
  public void setDuty(double power) {
    super.set(power);
    controlType = ControlType.kDutyCycle;

  }

  public void setVoltage(double voltage) {
    super.setVoltage(voltage);
    controlType = ControlType.kVoltage;
  }

  /**
   * set volocity to motor with PID and FF
   * 
   * @param velocity    the wanted velocity in meter per second or radians per
   *                    seconds depending on the config
   * @param feedForward wanted feed forward to add to the ks kv ka and kg,
   *                    defaults to 0
   */
  public void setVelocity(double velocity, double feedForward) {
    super.closedLoopController.setReference(velocity, ControlType.kMAXMotionVelocityControl, slot, feedForward);
    controlType = ControlType.kMAXMotionVelocityControl;
    setPoint = velocity;
  }

  public void setVelocity(double velocity) {
    setVelocity(velocity, 0);
  }

  public void setPositionVoltage(double position, double feedForward) {
    super.closedLoopController.setReference(position, ControlType.kPosition, slot, feedForward);
    controlType = ControlType.kPosition;
    setPoint = position;
  }

  public void setPositionVoltage(double position) {
    setPositionVoltage(position, 0);
  }

  public void setVelocityWithFeedForward(double velocity) {
    setVelocity(velocity, velocityFeedForward(velocity));
  }

  public void setMotionWithFeedForward(double velocity) {
    setVelocity(velocity, positionFeedForward(velocity));
  }

  private double velocityFeedForward(double velocity) {
    return velocity * velocity * Math.signum(velocity) * config.kv2;
  }

  private double positionFeedForward(double positin) {
    return Math.cos(positin * config.posToRad) * config.kSin;
  }

  @Override
  public String getCurrentControlMode() {
    return lastControlMode;
  }

  @Override
  public double getCurrentClosedLoopError() {
    return controlType == ControlType.kMAXMotionPositionControl ? setPoint - getCurrentPosition()
        : controlType == ControlType.kMAXMotionVelocityControl ? setPoint - getCurrentVelocity() : 0;
  }

  @Override
  public double getCurrentClosedLoopSP() {
    return setPoint;
  }

  /**
   * creates a widget in elastic of the pid and ff for hot reload
   * 
   * @param slot the slot of the close loop perams (from 0 to 2)
   */
  public void configPidFf(int slot) {

    Command configPidFf = new InstantCommand(() -> {
      switch (slot) {

        case 1:
          cfg.closedLoop.pid(config.pid1.kp, config.pid1.ki, config.pid1.kd, ClosedLoopSlot.kSlot1);
          break;
        case 2:
          cfg.closedLoop.pid(config.pid1.kp, config.pid1.ki, config.pid1.kd, ClosedLoopSlot.kSlot2);
          break;
        case 0:
        default:
          cfg.closedLoop.pid(config.pid.kp, config.pid.ki, config.pid.kd, ClosedLoopSlot.kSlot0);
          break;
      }
      super.configure(cfg, ResetMode.kNoResetSafeParameters, PersistMode.kNoPersistParameters);
    }).ignoringDisable(true);

    SmartDashboard.putData(name + "/PID+FF config", new Sendable() {
      @Override
      public void initSendable(SendableBuilder builder) {
        builder.setSmartDashboardType("PID+FF Config");

        switch (slot) {
          case 1:
            builder.addDoubleProperty("KP", () -> config.pid1.kp, (double newValue) -> config.pid1.kp = newValue);
            builder.addDoubleProperty("KI", () -> config.pid1.ki, (double newValue) -> config.pid1.ki = newValue);
            builder.addDoubleProperty("KD", () -> config.pid1.kd, (double newValue) -> config.pid1.kd = newValue);
            break;

          case 2:
            builder.addDoubleProperty("KP", () -> config.pid2.kp, (double newValue) -> config.pid2.kp = newValue);
            builder.addDoubleProperty("KI", () -> config.pid2.ki, (double newValue) -> config.pid2.ki = newValue);
            builder.addDoubleProperty("KD", () -> config.pid2.kd, (double newValue) -> config.pid2.kd = newValue);
            break;

          case 0:
          default:
            builder.addDoubleProperty("KP", () -> config.pid.kp, (double newValue) -> config.pid.kp = newValue);
            builder.addDoubleProperty("KI", () -> config.pid.ki, (double newValue) -> config.pid.ki = newValue);
            builder.addDoubleProperty("KD", () -> config.pid.kd, (double newValue) -> config.pid.kd = newValue);
        }

        builder.addBooleanProperty("Update", () -> configPidFf.isScheduled(),
            value -> {
              if (value) {
                if (!configPidFf.isScheduled()) {
                  configPidFf.schedule();
                }
              } else {
                if (configPidFf.isScheduled()) {
                  configPidFf.cancel();
                }
              }
            });
      }
    });
  }

  public double getCurrentPosition() {
    return encoder.getPosition();
  }

  public double getCurrentVelocity() {
    double velocity = encoder.getVelocity();
    if(lastCycleNum != RobotContainer.N_CYCLE) {
      lastCycleNum = RobotContainer.N_CYCLE;
      double time = Timer.getFPGATimestamp();
      lastAcceleration = (velocity - lastVelocity) / (time - lastTime);
      lastTime = time;
      lastVelocity = velocity;
    }
    return velocity;
  }

  public double getCurrentAcceleration() {
    return lastAcceleration;
  }

  public double getCurrentVoltage() {
    return getAppliedOutput() * 12;
  }

  /**
   * override the sendable of the talonFX to our costum widget in elastic
   * <br>
   * </br>
   * to activate put in the code:
   * 
   * <pre>
   * SmartDashboard.putData("talonMotor name", talonMotor);
   * </pre>
   */
  @Override
  public void initSendable(SendableBuilder builder) {
    builder.setSmartDashboardType("SparkMotor");
    builder.addStringProperty("ControlMode", this::getCurrentControlMode, null);
    builder.addDoubleProperty("Position", encoder::getPosition, null);
    builder.addDoubleProperty("Velocity", encoder::getVelocity, null);
    builder.addDoubleProperty("Voltage", super::getAppliedOutput, null);
  }

  public double gearRatio() {
    return config.motorRatio;
  }

  public String name() {
    return name;
  }

  @Override
  public void setMotion(double position, double feedForward) {
    super.closedLoopController.setReference(position, ControlType.kMAXMotionPositionControl, slot, feedForward);
    controlType = ControlType.kMAXMotionPositionControl;
    setPoint = position;
  }

  @Override
  public void setMotion(double position) {
    setMotion(position, 0);
  }


}
