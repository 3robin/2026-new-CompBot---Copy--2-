// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.RelativeEncoder;
import com.revrobotics.spark.ClosedLoopSlot;
import com.revrobotics.spark.FeedbackSensor;
import com.revrobotics.spark.SparkBase.ControlType;
import com.revrobotics.spark.SparkBase.PersistMode;
import com.revrobotics.spark.SparkBase.ResetMode;
import com.revrobotics.spark.SparkClosedLoopController;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;
import com.revrobotics.spark.config.SparkFlexConfig;
import com.revrobotics.spark.SparkFlex;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import static frc.robot.Constants.IntakeConstants.*;

public class IntakeSubsystem extends SubsystemBase {
  private final SparkFlex intake_lower_leaderMotor;
  private final SparkFlex intake_lower_followerMotor;
  private final SparkFlex intake_spinMotor;
  private final RelativeEncoder intakeLowerEncoder;
  private final SparkClosedLoopController intakeLowerClosedLoopController;
  private final RelativeEncoder intakeSpinEncoder;
  private final SparkClosedLoopController intakeSpinClosedLoopController;
  private final double intakeZeroPosition;

  /** Creates a new CANBallSubsystem. **/

    public IntakeSubsystem() {
    // create brushed motors for each of the motors on the launcher mechanism
    intake_lower_leaderMotor = new SparkFlex(INTAKE_LOWER_LEADER_ID, MotorType.kBrushless);
    intake_lower_followerMotor = new SparkFlex(INTAKE_LOWER_FOLLOWER_ID, MotorType.kBrushless);

    intakeLowerClosedLoopController = intake_lower_leaderMotor.getClosedLoopController();
    intakeLowerEncoder = intake_lower_leaderMotor.getEncoder();

    // create the configuration for the launcher roller, set a current limit, set
    // the motor to inverted so that positive values are used for both intaking and
    // launching, and apply the config to the controller

    // SparkFlexConfig intakeLowerConfig = new SparkFlexConfig();
    // intakeLowerConfig.inverted(false);
    // intakeLowerConfig.smartCurrentLimit(INTAKE_LOWER_CURRENT_LIMIT);
    // intake_lower_leaderMotor.configure(intakeLowerConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);

    // SparkFlexConfig intakeLowerFollowerConfig = new SparkFlexConfig();
    
    // intakeLowerFollowerConfig.smartCurrentLimit(INTAKE_LOWER_CURRENT_LIMIT);
    // intakeLowerFollowerConfig.follow(INTAKE_LOWER_LEADER_ID, false); // true = invert relative to leader
    // intake_lower_followerMotor.configure(intakeLowerFollowerConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);

    // SparkFlexConfig intakeLowerConfig = new SparkFlexConfig();
    // intakeLowerConfig.inverted(true);
    // intakeLowerConfig.smartCurrentLimit(INTAKE_LOWER_CURRENT_LIMIT);
    // intake_lower_leaderMotor.configure(intakeLowerConfig, SparkFlex.ResetMode.kResetSafeParameters, SparkFlex.PersistMode.kPersistParameters);

    SparkFlexConfig intakeLowerConfig = new SparkFlexConfig();
    intakeLowerConfig.inverted(false);
    intakeLowerConfig.smartCurrentLimit(INTAKE_LOWER_CURRENT_LIMIT);

    intakeLowerConfig.encoder.positionConversionFactor(1.0);
    intakeLowerConfig.encoder.velocityConversionFactor(1.0);
    intakeLowerConfig.closedLoop
      .feedbackSensor(FeedbackSensor.kPrimaryEncoder)
      // Set PID values for velocity control in slot 0
      .p(INTAKE_LOWER_MOTOR_P, ClosedLoopSlot.kSlot0) // proportional
      .i(INTAKE_LOWER_MOTOR_I, ClosedLoopSlot.kSlot0) // integral
      .d(INTAKE_LOWER_MOTOR_D, ClosedLoopSlot.kSlot0) // derivitave
      .outputRange(-1, 1, ClosedLoopSlot.kSlot0);
    // for testnig limit switch input
    intakeLowerConfig.softLimit
      .forwardSoftLimit(INTAKE_LOWER_MOTOR_FWD_LIMIT)
      .forwardSoftLimitEnabled(false)
      .reverseSoftLimit(INTAKE_LOWER_MOTOR_REV_LIMIT)
      .reverseSoftLimitEnabled(false);

    intake_lower_leaderMotor.configure(intakeLowerConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
    
    intakeZeroPosition = intake_lower_leaderMotor.getEncoder().getPosition();

    SparkFlexConfig intakeLowerFollowerConfig = new SparkFlexConfig();
    intakeLowerFollowerConfig.smartCurrentLimit(INTAKE_LOWER_CURRENT_LIMIT);
    intakeLowerFollowerConfig.follow(INTAKE_LOWER_LEADER_ID,true);
    intake_lower_followerMotor.configure(intakeLowerFollowerConfig, SparkFlex.ResetMode.kResetSafeParameters, SparkFlex.PersistMode.kPersistParameters);

    // SparkFlexConfig intakeRaiseConfig = new SparkFlexConfig();
    // intakeRaiseConfig.inverted(true);
    // intakeRaiseConfig.smartCurrentLimit(INTAKE_LOWER_CURRENT_LIMIT);
    // intake_lower_leaderMotor.configure(intakeRaiseConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);

    // SparkFlexConfig intakeRaiseFollowerConfig = new SparkFlexConfig();
    // intakeRaiseFollowerConfig.smartCurrentLimit(INTAKE_LOWER_CURRENT_LIMIT);
    // intakeRaiseFollowerConfig.follow(INTAKE_LOWER_LEADER_ID, false);
    // intake_lower_followerMotor.configure(intakeRaiseFollowerConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);

    // put default values for various fuel operations onto the dashboard
    // all commands using this subsystem pull values from the dashbaord to allow
    // you to tune the values easily, and then replace the values in Constants.java
    // with your new values. For more information, see the Software Guide.
    SmartDashboard.putNumber("intake lower value", INTAKE_LOWER_VOLTAGE);


    // create brushed motors for each of the motors on the launcher mechanism
    intake_spinMotor = new SparkFlex(INTAKE_SPIN_ID, MotorType.kBrushless);

    intakeSpinClosedLoopController = intake_spinMotor.getClosedLoopController();
    intakeSpinEncoder = intake_spinMotor.getEncoder();

    SparkFlexConfig intakeSpinConfig = new SparkFlexConfig();
    intakeSpinConfig.inverted(false);
    intakeSpinConfig.smartCurrentLimit(INTAKE_LOWER_CURRENT_LIMIT);

    intakeSpinConfig.encoder.positionConversionFactor(1.0);
    intakeSpinConfig.encoder.velocityConversionFactor(1.0);
    intakeSpinConfig.closedLoop
      .feedbackSensor(FeedbackSensor.kPrimaryEncoder)
      // Set PID values for velocity control in slot 0
      .p(INTAKE_SPIN_MOTOR_P, ClosedLoopSlot.kSlot0) // proportional
      .i(INTAKE_SPIN_MOTOR_I, ClosedLoopSlot.kSlot0) // integral
      .d(INTAKE_SPIN_MOTOR_D, ClosedLoopSlot.kSlot0) // derivitave
      .outputRange(-1, 1, ClosedLoopSlot.kSlot0);
    // for testnig limit switch input
    intakeSpinConfig.softLimit
      .forwardSoftLimit(INTAKE_SPIN_MOTOR_FWD_LIMIT)
      .forwardSoftLimitEnabled(false)
      .reverseSoftLimit(INTAKE_SPIN_MOTOR_REV_LIMIT)
      .reverseSoftLimitEnabled(false);

    intake_spinMotor.configure(intakeSpinConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);

    // create the configuration for the launcher roller, set a current limit, set
    // the motor to inverted so that positive values are used for both intaking and
    // launching, and apply the config to the controller
    // SparkFlexConfig intakeSpinConfig = new SparkFlexConfig();
    // intakeSpinConfig.inverted(false);
    // intakeSpinConfig.smartCurrentLimit(INTAKE_LOWER_CURRENT_LIMIT);
    // intake_spinMotor.configure(intakeSpinConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);

    // put default values for various fuel operations onto the dashboard
    // all commands using this subsystem pull values from the dashbaord to allow
    // you to tune the values easily, and then replace the values in Constants.java
    // with your new values. For more information, see the Software Guide.
    SmartDashboard.putNumber("intake spin value", INTAKE_SPIN_VOLTAGE);
  }

  // A method to set the voltage of the intake roller
  public void setIntakeLower(double voltage) {
    intake_lower_leaderMotor.setVoltage(INTAKE_LOWER_VOLTAGE);
  }

  // A method to set the voltage of the intake roller
  public void setIntakeRaise(double voltage) {
    intake_lower_leaderMotor.setVoltage(INTAKE_LOWER_VOLTAGE_INV);
  }

  // A method to set the voltage of the intake roller
  public void setIntakeSpin(double voltage) {
    intake_spinMotor.setVoltage(voltage);
  }

  public void setintakeLowerVelocity(double velocity) {
    intakeLowerClosedLoopController.setSetpoint(velocity, ControlType.kVelocity, ClosedLoopSlot.kSlot0);
  }

  // position pid
  public void setintakeLowerPosition(double position) {
    intakeLowerClosedLoopController.setSetpoint(position + intakeZeroPosition, ControlType.kPosition, ClosedLoopSlot.kSlot0);
  }

  public double getintakeLowerVelocity() {
    return intakeLowerEncoder.getVelocity();
  }

  public void setintakeRaiseVelocity(double velocity) {
    intakeLowerClosedLoopController.setSetpoint(velocity, ControlType.kVelocity, ClosedLoopSlot.kSlot0);
  }

  // position pid
  public void setintakeRaisePosition(double position) {
    intakeLowerClosedLoopController.setSetpoint(intakeZeroPosition, ControlType.kPosition, ClosedLoopSlot.kSlot0);
  }

  public double getintakeRaiseVelocity() {
    return intakeLowerEncoder.getVelocity();
  }

  public void setintakeSpinVelocity(double velocity) {
    intakeSpinClosedLoopController.setSetpoint(velocity, ControlType.kVelocity, ClosedLoopSlot.kSlot0);
  }

  public double getintakeSpinVelocity() {
    return intakeSpinEncoder.getVelocity();
  }

  // A method to stop the rollers
  public void stop() {
    intake_spinMotor.set(0);
    intake_lower_leaderMotor.set(0);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
