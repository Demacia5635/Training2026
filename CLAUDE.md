# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Project Overview

This is a FIRST Robotics Competition (FRC) Java robot codebase using WPILib framework, built with Gradle and targeting Java 17. The project includes both robot code and system identification (SysID) utilities for motor characterization.

## Common Commands

### Build and Deploy
```bash
# Build the project
./gradlew build

# Deploy to robot (RoboRIO)
./gradlew deploy

# Run tests
./gradlew test

# Simulate robot code
./gradlew simulateJava
```

### Development
```bash
# Clean build artifacts
./gradlew clean

# Build without tests
./gradlew assemble
```

## Architecture

### Core Structure
- **frc.robot** - Main robot code following WPILib Command-Based framework
  - `Robot.java` - Main robot class extending TimedRobot
  - `RobotContainer.java` - Handles button bindings and autonomous command selection
  - `Constants.java` - Robot-wide constants organized in nested classes
  - **commands/** - Command implementations
  - **subsystems/** - Subsystem implementations (motors, sensors, etc.)

### Motor Configuration System
The codebase uses a fluent configuration pattern for motors:
- `SparkConfig` - Configuration builder for REV Spark motors
- `TalonConfig` - Configuration builder for CTRE Talon motors  
- `SparkMotor` and `TalonMotor` - Motor wrapper classes using the configs

Motor configurations support:
- Voltage/current limits
- PID parameters (multiple slots)
- Motor-to-mechanism ratios for different units (meters, radians, degrees)
- Brake/coast modes, inversion, ramp rates
- Velocity and acceleration constraints

### System Identification Package
- **frc.Demacia.Sysid** - Complete SysID toolkit for motor characterization
  - `LogReader.java` - Parses WPILib data logs to extract motor data
  - `MatrixUtil.java` - Matrix operations for control calculations
  - `CalculateFeedbackGains.java` - Computes PID/feedforward gains
  - `Discretization.java` - Continuous to discrete system conversion
  - `RiccatiSolver.java` - LQR controller calculations

### Module System
- `ModuleSubsystem` - Swerve drive module with steer/drive motors
- Uses Phoenix 6 for Talon motors and REV robotics for Spark motors
- Includes simulation support with DCMotorSim for both steer and drive

## Key Patterns

### Configuration Pattern
Motors use builder pattern for configuration:
```java
new SparkConfig(id, "name")
    .withVolts(maxV, minV)
    .withCurrent(maxAmps)
    .withPID(kp, ki, kd, kf)
    .withRadiansMotor(gearRatio)
```

### Constants Organization
Constants are organized in nested static classes by subsystem/functionality in `Constants.java`.

### Simulation Support
The codebase includes comprehensive simulation support with motor simulation for testing without hardware.

## Dependencies
- WPILib 2025.3.2 (FRC framework)
- Phoenix 6 (CTRE motor controllers)
- REV Robotics libraries (Spark motor controllers)
- JUnit 5 for testing