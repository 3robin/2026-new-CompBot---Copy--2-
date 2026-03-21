// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.spark.ClosedLoopSlot;
import com.revrobotics.spark.FeedbackSensor;
import com.revrobotics.spark.SparkBase.PersistMode;
import com.revrobotics.spark.SparkBase.ResetMode;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;
import com.revrobotics.spark.config.SparkFlexConfig;
import com.revrobotics.spark.SparkFlex;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import static frc.robot.Constants.ShooterConstants.*;

public class flywheelShooterSubsystem extends SubsystemBase {
  private final SparkFlex flywheel_leaderMotor;
  private final SparkFlex flywheel_followerMotor;

  /** Creates a new CANBallSubsystem. 
     * @return */
    @SuppressWarnings("removal")
    public flywheelShooterSubsystem() {
    // create brushed motors for each of the motors on the launcher mechanism
    flywheel_leaderMotor = new SparkFlex(FLYWHEEL_LEADER_ID, MotorType.kBrushless);
    flywheel_followerMotor = new SparkFlex(FLYWHEEL_FOLLOWER_ID, MotorType.kBrushless);

    // create the configuration for the launcher roller, set a current limit, set
    // the motor to inverted so that positive values are used for both intaking and
    // launching, and apply the config to the controller
    SparkFlexConfig flywheelConfig = new SparkFlexConfig();
    flywheelConfig.inverted(false);
    flywheelConfig.idleMode(IdleMode.kCoast);
    flywheelConfig.smartCurrentLimit(FLYWHEEL_SHOOTER_CURRENT_LIMIT);
    flywheel_leaderMotor.configure(flywheelConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);

    flywheelConfig.encoder.positionConversionFactor(1.0);
    flywheelConfig.encoder.velocityConversionFactor(1.0);
    flywheelConfig.closedLoop
      .feedbackSensor(FeedbackSensor.kPrimaryEncoder)
      // Set PID values for velocity control in slot 0
      .p(FLYWHEEL_MOTOR_P, ClosedLoopSlot.kSlot0) // proportional
      .i(FLYWHEEL_MOTOR_I, ClosedLoopSlot.kSlot0) // integral
      .d(FLYWHEEL_MOTOR_D, ClosedLoopSlot.kSlot0) // derivitave
      .outputRange(-1, 1 ClosedLoopSlot.kSlot0);
    // for testnig limit switch input
    flywheelConfig.softLimit
      .forwardSoftLimit(FLYWHEEL_MOTOR_FWD_LIMIT)
      .forwardSoftLimit(false)
      .reverseSoftLimit(FLYWHEEL_MOTOR_REV_LIMIT)
      .reverseSoftLimit(false);



    SparkFlexConfig flywheelfollowerConfig = new SparkFlexConfig();
    flywheelfollowerConfig.inverted(false);
    flywheelfollowerConfig.idleMode(IdleMode.kCoast);
    flywheelfollowerConfig.smartCurrentLimit(FLYWHEEL_SHOOTER_CURRENT_LIMIT);
    flywheelfollowerConfig.follow(FLYWHEEL_LEADER_ID);
    flywheel_followerMotor.configure(flywheelfollowerConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);

    // put default values for various fuel operations onto the dashboard
    // all commands using this subsystem pull values from the dashbaord to allow
    // you to tune the values easily, and then replace the values in Constants.java
    // with your new values. For more information, see the Software Guide.
    SmartDashboard.putNumber("flywheel Shooter value", SHOOTER_FLYWHEEL_VOLTAGE);
  }
  // A method to set the voltage of the intake roller
  public void setflywheelShooter(double voltage) {
    flywheel_leaderMotor.setVoltage(voltage);
  }

  // A method to set the voltage of the intake roller
  //public void setfeederRoller(double voltage) {
    //feederRoller.setVoltage(voltage);
  //}

  // A method to stop the rollers
  public void stop() {
    //feederRoller.set(0);
    flywheel_leaderMotor.set(0);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
