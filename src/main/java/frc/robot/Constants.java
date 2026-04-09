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

    // Current limit
    public static final int FLYWHEEL_SHOOTER_CURRENT_LIMIT = 60;

    // Voltage values for various fuel operations. These values may need to be tuned
    // based on exact robot construction.
    // See the Software Guide for tuning information
    public static final double SHOOTER_FLYWHEEL_VOLTAGE = 5.8; //Sets speed
    public static final double voltage = 1;

    public static final double SHOOTER_FLYWHEEL_VELOCITY = 3500; //3500

    // setting velocity constants
    public static final double FLYWHEEL_MOTOR_P = 0.002;
    public static final double FLYWHEEL_MOTOR_I = 0;
    public static final double FLYWHEEL_MOTOR_D = 0;

    public static final double FLYWHEEL_MOTOR_FWD_LIMIT = 0;
    public static final double FLYWHEEL_MOTOR_REV_LIMIT = 0;
  }

  public static final class IntakeConstants {
    // Motor controller IDs for Shooter Mechanism motors
    public static final int INTAKE_SPIN_ID = 25;

    // Motor controller IDs for Shooter Mechanism motors
    public static final int INTAKE_LOWER_LEADER_ID = 26;
    public static final int INTAKE_LOWER_FOLLOWER_ID = 27;

    // Current limit and nominal voltage for fuel mechanism motors.
    public static final int INTAKE_LOWER_CURRENT_LIMIT = 60;
    public static final int INTAKE_LOWER_FOLLOWER_CURRENT_LIMIT = 60;

    public static final int INTAKE_SPIN_CURRENT_LIMIT = 60;

    // Voltage values for various fuel operations. These values may need to be tuned
    // based on exact robot construction.
    // See the Software Guide for tuning information
    public static final double INTAKE_LOWER_VOLTAGE = 2;
    public static final double INTAKE_LOWER_VOLTAGE_INV = -4;
    public static final double INTAKE_SPIN_VOLTAGE = 6;
    public static final double INTAKE_REV_SPIN_VOLTAGE = -3; // -3 is guess, to be tuned
    public static final double voltage = 1;

    public static final double INTAKE_LOWER_VELOCITY = 1200;
    public static final double INTAKE_LOWER_VELOCITY_INV = -2400;
    public static final double INTAKE_SPIN_VELOCITY = 8000;
    public static final double INTAKE_SPIN_REV_VELOCITY = -1800;

    public static final double INTAKE_LOWER_POSITION = 3.89;
    public static final double INTAKE_LOWER_POSITION_INV = 0;

    // setting velocity constants
    public static final double INTAKE_LOWER_MOTOR_P = 0.1;
    public static final double INTAKE_LOWER_MOTOR_I = 0;
    public static final double INTAKE_LOWER_MOTOR_D = 0;

    public static final double INTAKE_LOWER_MOTOR_FWD_LIMIT = 0;
    public static final double INTAKE_LOWER_MOTOR_REV_LIMIT = 0;

    // setting velocity constants
    public static final double INTAKE_SPIN_MOTOR_P = 0.0001;
    public static final double INTAKE_SPIN_MOTOR_I = 0;
    public static final double INTAKE_SPIN_MOTOR_D = 0;

    public static final double INTAKE_SPIN_MOTOR_FWD_LIMIT = 0;
    public static final double INTAKE_SPIN_MOTOR_REV_LIMIT = 0;
  }

  public static final class HorizontalTransferConstants {
    // Motor controller IDs for Shooter Mechanism motors
    public static final int HORIZONTAL_TRANSFER_ID = 30;

    // Current limit and nominal voltage for fuel mechanism motors.
    public static final int HORIZONTAL_TRANSFER_CURRENT_LIMIT = 60;

    // Voltage values for various fuel operations. These values may need to be tuned
    // based on exact robot construction.
    // See the Software Guide for tuning information
    public static final double HORIZONTAL_TRANSFER_VOLTAGE = 6;
    public static final double HORIZONTAL_REV_TRANSFER_VOLTAGE = -6; // -6 is guess, to be tuned
    public static final double voltage = 1;

    public static final double HORIZONTAL_TRANSFER_VELOCITY = 3600;
    public static final double HORIZONTAL_TRANSFER_REV_VELOCITY = -3600;

    // setting velocity constants
    public static final double HORIZONTAL_TRANSFER_MOTOR_P = 0.001;
    public static final double HORIZONTAL_TRANSFER_MOTOR_I = 0;
    public static final double HORIZONTAL_TRANSFER_MOTOR_D = 0;

    public static final double HORIZONTAL_TRANSFER_MOTOR_FWD_LIMIT = 0;
    public static final double HORIZONTAL_TRANSFER_MOTOR_REV_LIMIT = 0;
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

    public static final double VERTICAL_TRANSFER_1_VELOCITY = 1800;
    public static final double VERTICAL_TRANSFER_2_VELOCITY = 1800;

    public static final double VERTICAL_TRANSFER_REV_1_VELOCITY = -1800;
    public static final double VERTICAL_TRANSFER_REV_2_VELOCITY = -1800;

    // setting velocity constants
    public static final double VERTICAL_TRANSFER_1_MOTOR_P = 0.0001;
    public static final double VERTICAL_TRANSFER_1_MOTOR_I = 0;
    public static final double VERTICAL_TRANSFER_1_MOTOR_D = 0;

    public static final double VERTICAL_TRANSFER_1_MOTOR_FWD_LIMIT = 0;
    public static final double VERTICAL_TRANSFER_1_MOTOR_REV_LIMIT = 0;

    // setting velocity constants
    public static final double VERTICAL_TRANSFER_2_MOTOR_P = 0.0001;
    public static final double VERTICAL_TRANSFER_2_MOTOR_I = 0;
    public static final double VERTICAL_TRANSFER_2_MOTOR_D = 0;

    public static final double VERTICAL_TRANSFER_2_MOTOR_FWD_LIMIT = 0;
    public static final double VERTICAL_TRANSFER_2_MOTOR_REV_LIMIT = 0;
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

    public static final double CLIMBER_VELOCITY = 600;

    // setting velocity constants
    public static final double CLIMBER_MOTOR_P = 0.001;
    public static final double CLIMBER_MOTOR_I = 0;
    public static final double CLIMBER_MOTOR_D = 0;

    public static final double CLIMBER_MOTOR_FWD_LIMIT = 0;
    public static final double CLIMBER_MOTOR_REV_LIMIT = 0;
  }

}
