// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.math.geometry.Translation3d;
import edu.wpi.first.math.util.Units;
import swervelib.math.Matter;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean constants. This
 * class should not be used for any other purpose. All constants should be declared globally (i.e. public static). Do
 * not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants
{

  public static final double ROBOT_MASS = (148 - 20.3) * 0.453592; // 32lbs * kg per pound
  public static final Matter CHASSIS    = new Matter(new Translation3d(0, 0, Units.inchesToMeters(8)), ROBOT_MASS);
  public static final double LOOP_TIME  = 0.13; //s, 20ms + 110ms sprk max velocity lag
  public static final double MAX_SPEED  = Units.feetToMeters(14.5);
  // Maximum speed of the robot in meters per second, used to limit acceleration.

//  public static final class AutonConstants
//  {
//
//    public static final PIDConstants TRANSLATION_PID = new PIDConstants(0.7, 0, 0);
//    public static final PIDConstants ANGLE_PID       = new PIDConstants(0.4, 0, 0.01);
//  }

  public static final class DrivebaseConstants
  {

    // Hold time on motor brakes when disabled
    public static final double WHEEL_LOCK_TIME = 10; // seconds
  }

  public static class OperatorConstants
  {

    // Joystick Deadband
    public static final double DEADBAND        = 0.1;
    public static final double LEFT_Y_DEADBAND = 0.1;
    public static final double RIGHT_X_DEADBAND = 0.1;
    public static final double TURN_CONSTANT    = 6;
  }

    public static final class ShooterConstants {
    // Motor controller IDs for Shooter Mechanism motors
    public static final int FLYWHEEL_LEADER_ID = 20;
    public static final int FLYWHEEL_FOLLOWER_ID = 21;

    // Current limit and nominal voltage for fuel mechanism motors.
    public static final int FLYWHEEL_SHOOTER_CURRENT_LIMIT = 40;

    // Voltage values for various fuel operations. These values may need to be tuned
    // based on exact robot construction.
    // See the Software Guide for tuning information
    public static final double SHOOTER_FLYWHEEL_VOLTAGE = 4;
    public static final double voltage = 1;
  }

  public static final class IntakeConstants {
    // Motor controller IDs for Shooter Mechanism motors
    public static final int INTAKE_SPIN_ID = 25;

    // Motor controller IDs for Shooter Mechanism motors
    public static final int INTAKE_LOWER_LEADER_ID = 26;
    public static final int INTAKE_LOWER_FOLLOWER_ID = 27;

    // Current limit and nominal voltage for fuel mechanism motors.
    public static final int INTAKE_LOWER_CURRENT_LIMIT = 40;
    public static final int INTAKE_LOWER_FOLLOWER_CURRENT_LIMIT = 40;

    public static final int INTAKE_SPIN_CURRENT_LIMIT = 40;

    // Voltage values for various fuel operations. These values may need to be tuned
    // based on exact robot construction.
    // See the Software Guide for tuning information
    public static final double INTAKE_LOWER_VOLTAGE = 5;
    public static final double INTAKE_LOWER_VOLTAGE_INV = -9;
    public static final double INTAKE_SPIN_VOLTAGE = 5;
    public static final double voltage = 1;
  }

  public static final class HorizontalTransferConstants {
    // Motor controller IDs for Shooter Mechanism motors
    public static final int HORIZONTAL_TRANSFER_ID = 30;

    // Current limit and nominal voltage for fuel mechanism motors.
    public static final int HORIZONTAL_TRANSFER_CURRENT_LIMIT = 40;

    // Voltage values for various fuel operations. These values may need to be tuned
    // based on exact robot construction.
    // See the Software Guide for tuning information
    public static final double HORIZONTAL_TRANSFER_VOLTAGE = 4;
    public static final double voltage = 1;
  }

  public static final class VerticalTransferConstants {
    // Motor controller IDs for Shooter Mechanism motors
    public static final int VERTICAL_TRANSFER_1_ID = 28;
    public static final int VERTICAL_TRANSFER_2_ID = 29;

    // Current limit and nominal voltage for fuel mechanism motors.
    public static final int VERTICAL_TRANSFER_1_CURRENT_LIMIT = 40;
    public static final int VERTICAL_TRANSFER_2_CURRENT_LIMIT = 40;

    // Voltage values for various fuel operations. These values may need to be tuned
    // based on exact robot construction.
    // See the Software Guide for tuning information
    public static final double VERTICAL_TRANSFER_1_VOLTAGE = 3;
    public static final double VERTICAL_TRANSFER_2_VOLTAGE = 3;
    public static final double voltage = 1;
  }

  public static final class ClimberConstants {
    // Motor controller IDs for Shooter Mechanism motors
    public static final int CLIMBER_LEADER_ID = 28;
    public static final int CLIMBER_FOLLOWER_ID = 29;

    // Current limit and nominal voltage for fuel mechanism motors.
    public static final int CLIMBER_CURRENT_LIMIT = 40;

    // Voltage values for various fuel operations. These values may need to be tuned
    // based on exact robot construction.
    // See the Software Guide for tuning information
    public static final double CLIMBER_VOLTAGE = 1;
    public static final double voltage = 1;
  }

}
