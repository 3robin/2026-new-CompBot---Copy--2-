// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import static frc.robot.Constants.VerticalTransferConstants.*;

import com.revrobotics.RelativeEncoder;
import com.revrobotics.spark.ClosedLoopSlot;
import com.revrobotics.spark.FeedbackSensor;
import com.revrobotics.spark.SparkBase.ControlType;
import com.revrobotics.spark.SparkBase.PersistMode;
import com.revrobotics.spark.SparkBase.ResetMode;
import com.revrobotics.spark.SparkClosedLoopController;
import com.revrobotics.spark.SparkFlex;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;
import com.revrobotics.spark.config.SparkFlexConfig;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class VerticalTransferSubsystem extends SubsystemBase {
  private final SparkFlex Vertical_Transfer_1Motor;
  private final SparkFlex Vertical_Transfer_2Motor;
  private final RelativeEncoder VerticalTransfer1Encoder;
  private final RelativeEncoder VerticalTransfer2Encoder;
  private final SparkClosedLoopController VerticalTransfer1ClosedLoopController;
  private final SparkClosedLoopController VerticalTransfer2ClosedLoopController;


  /** Creates a new CANBallSubsystem. 
     * @return */
    @SuppressWarnings("removal")
    public VerticalTransferSubsystem() {
    // create brushed motors for each of the motors on the launcher mechanism
    Vertical_Transfer_1Motor = new SparkFlex(VERTICAL_TRANSFER_1_ID, MotorType.kBrushless);
    Vertical_Transfer_2Motor = new SparkFlex(VERTICAL_TRANSFER_2_ID, MotorType.kBrushless);

    VerticalTransfer1ClosedLoopController = Vertical_Transfer_1Motor.getClosedLoopController();
    VerticalTransfer1Encoder = Vertical_Transfer_1Motor.getEncoder();
    VerticalTransfer2ClosedLoopController = Vertical_Transfer_2Motor.getClosedLoopController();
    VerticalTransfer2Encoder = Vertical_Transfer_2Motor.getEncoder();

    SparkFlexConfig VerticalTransfer1Config = new SparkFlexConfig();
    VerticalTransfer1Config.inverted(false);
    VerticalTransfer1Config.smartCurrentLimit(VERTICAL_TRANSFER_1_CURRENT_LIMIT);

    VerticalTransfer1Config.encoder.positionConversionFactor(1.0);
    VerticalTransfer1Config.encoder.velocityConversionFactor(1.0);
    VerticalTransfer1Config.closedLoop
      .feedbackSensor(FeedbackSensor.kPrimaryEncoder)
      // Set PID values for velocity control in slot 0
      .p(VERTICAL_TRANSFER_1_MOTOR_P, ClosedLoopSlot.kSlot0) // proportional
      .i(VERTICAL_TRANSFER_1_MOTOR_I, ClosedLoopSlot.kSlot0) // integral
      .d(VERTICAL_TRANSFER_1_MOTOR_D, ClosedLoopSlot.kSlot0) // derivitave
      .outputRange(-1, 1, ClosedLoopSlot.kSlot0);
    // for testnig limit switch input
    VerticalTransfer1Config.softLimit
      .forwardSoftLimit(VERTICAL_TRANSFER_1_MOTOR_FWD_LIMIT)
      .forwardSoftLimitEnabled(false)
      .reverseSoftLimit(VERTICAL_TRANSFER_1_MOTOR_REV_LIMIT)
      .reverseSoftLimitEnabled(false);

    Vertical_Transfer_1Motor.configure(VerticalTransfer1Config, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);

    SparkFlexConfig VerticalTransfer2Config = new SparkFlexConfig();
    VerticalTransfer2Config.inverted(true);
    VerticalTransfer2Config.smartCurrentLimit(VERTICAL_TRANSFER_2_CURRENT_LIMIT);

    VerticalTransfer2Config.encoder.positionConversionFactor(1.0);
    VerticalTransfer2Config.encoder.velocityConversionFactor(1.0);
    VerticalTransfer2Config.closedLoop
      .feedbackSensor(FeedbackSensor.kPrimaryEncoder)
      // Set PID values for velocity control in slot 0
      .p(VERTICAL_TRANSFER_2_MOTOR_P, ClosedLoopSlot.kSlot0) // proportional
      .i(VERTICAL_TRANSFER_2_MOTOR_I, ClosedLoopSlot.kSlot0) // integral
      .d(VERTICAL_TRANSFER_2_MOTOR_D, ClosedLoopSlot.kSlot0) // derivitave
      .outputRange(-1, 1, ClosedLoopSlot.kSlot0);
    // for testnig limit switch input
    VerticalTransfer2Config.softLimit
      .forwardSoftLimit(VERTICAL_TRANSFER_2_MOTOR_FWD_LIMIT)
      .forwardSoftLimitEnabled(false)
      .reverseSoftLimit(VERTICAL_TRANSFER_2_MOTOR_REV_LIMIT)
      .reverseSoftLimitEnabled(false);

    Vertical_Transfer_2Motor.configure(VerticalTransfer2Config, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);

    // create the configuration for the launcher roller, set a current limit, set
    // the motor to inverted so that positive values are used for both intaking and
    // launching, and apply the config to the controller
    // SparkFlexConfig VerticalTransfer1Config = new SparkFlexConfig();
    // VerticalTransfer1Config.inverted(false);
    // VerticalTransfer1Config.smartCurrentLimit(VERTICAL_TRANSFER_1_CURRENT_LIMIT);
    // Vertical_Transfer_1Motor.configure(VerticalTransfer1Config, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);

    // SparkFlexConfig VerticalTransfer2Config = new SparkFlexConfig();
    // VerticalTransfer2Config.inverted(true);
    // VerticalTransfer2Config.smartCurrentLimit(VERTICAL_TRANSFER_2_CURRENT_LIMIT);
    // Vertical_Transfer_2Motor.configure(VerticalTransfer2Config, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);

    // put default values for various fuel operations onto the dashboard
    // all commands using this subsystem pull values from the dashbaord to allow
    // you to tune the values easily, and then replace the values in Constants.java
    // with your new values. For more information, see the Software Guide.
    SmartDashboard.putNumber("vertical transfer 1 value", VERTICAL_TRANSFER_1_VOLTAGE);
    SmartDashboard.putNumber("vertical transfer 2 value", VERTICAL_TRANSFER_2_VOLTAGE);

  }
  // A method to set the voltage of the intake roller
  public void setVerticalTransfer(double voltage, double d) {
    Vertical_Transfer_1Motor.setVoltage(voltage);
    Vertical_Transfer_2Motor.setVoltage(voltage);
  }

  // A method to set the voltage of the intake roller
  //public void setfeederRoller(double voltage) {
    //feederRoller.setVoltage(voltage);
  //}

  public void setVerticalTransfer1Velocity(double velocity) {
    VerticalTransfer1ClosedLoopController.setSetpoint(velocity, ControlType.kVelocity, ClosedLoopSlot.kSlot0);
  }

  public void setVerticalTransfer2Velocity(double velocity) {
    VerticalTransfer2ClosedLoopController.setSetpoint(velocity, ControlType.kVelocity, ClosedLoopSlot.kSlot0);
  }

  public double getVerticalTransfer1Velocity() {
    return VerticalTransfer1Encoder.getVelocity();
  }

  public double getVerticalTransfer2Velocity() {
    return VerticalTransfer2Encoder.getVelocity();
  }

  // A method to stop the rollers 
  public void stop() {
    //feederRoller.set(0);
    Vertical_Transfer_1Motor.set(0);
    Vertical_Transfer_2Motor.set(0);

  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

}
