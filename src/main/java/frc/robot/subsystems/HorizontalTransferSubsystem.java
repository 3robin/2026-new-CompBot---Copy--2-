// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import static frc.robot.Constants.HorizontalTransferConstants.*;

import com.revrobotics.RelativeEncoder;
import com.revrobotics.spark.ClosedLoopSlot;
import com.revrobotics.spark.FeedbackSensor;
import com.revrobotics.spark.SparkBase.ControlType;
import com.revrobotics.spark.SparkBase.PersistMode;
import com.revrobotics.spark.SparkBase.ResetMode;
import com.revrobotics.spark.SparkClosedLoopController;
import com.revrobotics.spark.SparkFlex;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.config.SparkFlexConfig;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class HorizontalTransferSubsystem extends SubsystemBase {
  private final SparkFlex Horizontal_TransferMotor;
  private final RelativeEncoder HorizontalTransferEncoder;
  private final SparkClosedLoopController HorizontalTransferClosedLoopController;

  /** Creates a new CANBallSubsystem. 
     * @return */
    @SuppressWarnings("removal")
    public HorizontalTransferSubsystem() {
    // create brushed motors for each of the motors on the launcher mechanism
    Horizontal_TransferMotor = new SparkFlex(HORIZONTAL_TRANSFER_ID, MotorType.kBrushless);

    HorizontalTransferClosedLoopController = Horizontal_TransferMotor.getClosedLoopController();
    HorizontalTransferEncoder = Horizontal_TransferMotor.getEncoder();

    SparkFlexConfig HorizontalTransferConfig = new SparkFlexConfig();
    HorizontalTransferConfig.inverted(true);
    HorizontalTransferConfig.smartCurrentLimit(HORIZONTAL_TRANSFER_CURRENT_LIMIT);

    HorizontalTransferConfig.encoder.positionConversionFactor(1.0);
    HorizontalTransferConfig.encoder.velocityConversionFactor(1.0);
    HorizontalTransferConfig.closedLoop
      .feedbackSensor(FeedbackSensor.kPrimaryEncoder)
      // Set PID values for velocity control in slot 0
      .p(HORIZONTAL_TRANSFER_MOTOR_P, ClosedLoopSlot.kSlot0) // proportional
      .i(HORIZONTAL_TRANSFER_MOTOR_I, ClosedLoopSlot.kSlot0) // integral
      .d(HORIZONTAL_TRANSFER_MOTOR_D, ClosedLoopSlot.kSlot0) // derivitave
      .outputRange(-1, 1, ClosedLoopSlot.kSlot0);
    // for testnig limit switch input
    HorizontalTransferConfig.softLimit
      .forwardSoftLimit(HORIZONTAL_TRANSFER_MOTOR_FWD_LIMIT)
      .forwardSoftLimitEnabled(false)
      .reverseSoftLimit(HORIZONTAL_TRANSFER_MOTOR_REV_LIMIT)
      .reverseSoftLimitEnabled(false);

    Horizontal_TransferMotor.configure(HorizontalTransferConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);

    // create the configuration for the launcher roller, set a current limit, set
    // the motor to inverted so that positive values are used for both intaking and
    // launching, and apply the config to the controller
    // SparkFlexConfig HorizontalTransferConfig = new SparkFlexConfig();
    // HorizontalTransferConfig.inverted(true);
    // HorizontalTransferConfig.smartCurrentLimit(HORIZONTAL_TRANSFER_CURRENT_LIMIT);
    // Horizontal_TransferMotor.configure(HorizontalTransferConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);

    // put default values for various fuel operations onto the dashboard
    // all commands using this subsystem pull values from the dashbaord to allow
    // you to tune the values easily, and then replace the values in Constants.java
    // with your new values. For more information, see the Software Guide.
    SmartDashboard.putNumber("horizontal transfer value", HORIZONTAL_TRANSFER_VOLTAGE);
  }
  // A method to set the voltage of the intake roller
  public void setHorizontalTransfer(double voltage) {
    Horizontal_TransferMotor.setVoltage(voltage);
  }

  // A method to set the voltage of the intake roller
  //public void setfeederRoller(double voltage) {
    //feederRoller.setVoltage(voltage);
  //}

  public void setHorizontalTransferlVelocity(double velocity) {
    HorizontalTransferClosedLoopController.setSetpoint(velocity, ControlType.kVelocity, ClosedLoopSlot.kSlot0);
  }

  public double getintakeLowerVelocity() {
    return HorizontalTransferEncoder.getVelocity();
  }

  // A method to stop the rollers
  public void stop() {
    //feederRoller.set(0);
    Horizontal_TransferMotor.set(0);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
