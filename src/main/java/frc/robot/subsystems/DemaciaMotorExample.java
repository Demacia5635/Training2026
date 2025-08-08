package frc.robot.subsystems;


import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.Demacia.utils.Motors.BaseMotorConfig.Canbus;
import frc.Demacia.utils.Motors.MotorCommands;
import frc.Demacia.utils.Motors.MotorInterface;
import frc.Demacia.utils.Motors.TalonConfig;
import frc.Demacia.utils.Motors.TalonMotor;

/*
 * This class is an example of demacia lib
 * 
 * The configuration shoulc be in a Constants file
 * Change the motor id/canbus to match a real motor on the Robot
 * Change the motor configuration to match the motor on the robot
 * 
 * Deploy to Robot
 * In Elastic:
 *  open a new TAB
 *  add the Motor Information,
 *  add the Position as Graph
 *  check direction and degrees are correct - if needed update the configuration
 *  add the slow, random and motion command
 *  for the motion command add the target position value
 *      change the position value to slider, make it longer, set the min/max values and allow updating while draggin
 *      lock the display - for draging the slider
 *  add the PID and MotionVelocities display - change the update buttom type to toggle button
 *  
 *  run the Slow power command - mark the voltage when the position starts to move - this should be KS for position (radians/degrees) motor
 *  run the Random power command - let it run for 20-30 seconds
 *  use the log data manage on the PC to transfer the log to the project logs dir
 *  run the Sysid - from Vscode select the Sysid.java file, locate the main function and select RUN
 *  select the downloaded file, select the motor and run the calculate
 *  note the gains - also check that the avg error is low, and the max error uis reasnable
 *      records with high error are listed at the message panel
 *      also note the maximum velocity reached
 * 
 *   enter the values in the PID form and update. Note the max velocity and set the Motion Velocities as needed
 * 
 *   run the Motion command
 *      use the slider to move the motor angle
 *      look at the graph for normal vehaviour
 *      look at the ClosedLoopError value - it should be small
 *      change parameters to get a good profile
 *      update the configuration of the motor
 * 
 *  re-deploy and check the motion
 *   
 */

public class DemaciaMotorExample extends SubsystemBase {
    // Define the motor 
    MotorInterface motor;

    
    // Constructor
    public DemaciaMotorExample() {
        super();
        motor = new TalonMotor(Example.MOTOR_CONFIG);
        // commands
        MotorCommands.showSlowPowerCommand("Slow Power", 0.04, 0.01, 1, this, motor);
        MotorCommands.showRandomPowerCommand("Random Power", -0.5, 0.5, 0.3, this, motor);
        MotorCommands.showMotionCommand("Goto Position", this, motor);
        // pid and motion configration
        motor.showConfigMotionVelocitiesCommand();
        motor.showConfigPIDFSlotCommand(0);
    }

    public static class Example { // Should be in Constants file
        public static final TalonConfig MOTOR_CONFIG = new TalonConfig(10, Canbus.Rio, "Talon Example")
          .withBrake(true)
          .withCurrent(20) // Maximum current in ASpmpers
          .withInvert(true)
          .withDegreesMotor(12.8) // the motor gear ratio - all data is in degrees
          .withMotionParam(720, 1440, 2900) // set the motion parametrs
          .withPID(0.04, 0, 0, 0.1, 0.004,0.0008, 0) // the PID gains
          .withRampTime(0.3) // time from 0 to full power
          .withVolts(7);
  }

}   