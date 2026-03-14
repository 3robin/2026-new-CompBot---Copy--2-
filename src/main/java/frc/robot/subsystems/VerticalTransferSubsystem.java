// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import static frc.robot.Constants.VerticalTransferConstants.*;

import com.revrobotics.spark.SparkBase.PersistMode;
import com.revrobotics.spark.SparkBase.ResetMode;
import com.revrobotics.spark.SparkFlex;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.config.SparkFlexConfig;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class VerticalTransferSubsystem extends SubsystemBase {
  private final SparkFlex Vertical_Transfer_1Motor;
  private final SparkFlex Vertical_Transfer_2Motor;


  /** Creates a new CANBallSubsystem. 
     * @return */
    @SuppressWarnings("removal")
    public VerticalTransferSubsystem() {
    // create brushed motors for each of the motors on the launcher mechanism
    Vertical_Transfer_1Motor = new SparkFlex(VERTICAL_TRANSFER_1_ID, MotorType.kBrushless);
    Vertical_Transfer_2Motor = new SparkFlex(VERTICAL_TRANSFER_2_ID, MotorType.kBrushless);

    // create the configuration for the launcher roller, set a current limit, set
    // the motor to inverted so that positive values are used for both intaking and
    // launching, and apply the config to the controller
    SparkFlexConfig VerticalTransfer1Config = new SparkFlexConfig();
    VerticalTransfer1Config.inverted(false);
    VerticalTransfer1Config.smartCurrentLimit(VERTICAL_TRANSFER_1_CURRENT_LIMIT);
    Vertical_Transfer_1Motor.configure(VerticalTransfer1Config, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);

    SparkFlexConfig VerticalTransfer2Config = new SparkFlexConfig();
    VerticalTransfer2Config.inverted(true);
    VerticalTransfer2Config.smartCurrentLimit(VERTICAL_TRANSFER_2_CURRENT_LIMIT);
    Vertical_Transfer_2Motor.configure(VerticalTransfer2Config, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);

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
