// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.spark.SparkBase.PersistMode;
import com.revrobotics.spark.SparkBase.ResetMode;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.config.SparkFlexConfig;
import com.revrobotics.spark.SparkFlex;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import static frc.robot.Constants.IntakeConstants.*;

public class IntakeSubsystem extends SubsystemBase {
  private final SparkFlex intake_lower_leaderMotor;
  private final SparkFlex intake_lower_followerMotor;
  private final SparkFlex intake_spinMotor;

  /** Creates a new CANBallSubsystem. **/

    public IntakeSubsystem() {
    // create brushed motors for each of the motors on the launcher mechanism
    intake_lower_leaderMotor = new SparkFlex(INTAKE_LOWER_LEADER_ID, MotorType.kBrushless);
    intake_lower_followerMotor = new SparkFlex(INTAKE_LOWER_FOLLOWER_ID, MotorType.kBrushless);

    // create the configuration for the launcher roller, set a current limit, set
    // the motor to inverted so that positive values are used for both intaking and
    // launching, and apply the config to the controller
    SparkFlexConfig intakeLowerConfig = new SparkFlexConfig();
    intakeLowerConfig.inverted(false);
    intakeLowerConfig.smartCurrentLimit(INTAKE_LOWER_CURRENT_LIMIT);
    intake_lower_leaderMotor.configure(intakeLowerConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);

    SparkFlexConfig intakeUptakeConfig = new SparkFlexConfig();
    intakeUptakeConfig.inverted(true);
    intakeUptakeConfig.smartCurrentLimit(INTAKE_LOWER_CURRENT_LIMIT);
    intake_lower_leaderMotor.configure(intakeUptakeConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);

    SparkFlexConfig intakeLowerFollowerConfig = new SparkFlexConfig();
    intakeLowerFollowerConfig.inverted(true);
    intakeLowerFollowerConfig.smartCurrentLimit(INTAKE_LOWER_CURRENT_LIMIT);
    intakeLowerFollowerConfig.follow(INTAKE_LOWER_LEADER_ID);
    intake_lower_followerMotor.configure(intakeLowerFollowerConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);

    // put default values for various fuel operations onto the dashboard
    // all commands using this subsystem pull values from the dashbaord to allow
    // you to tune the values easily, and then replace the values in Constants.java
    // with your new values. For more information, see the Software Guide.
    SmartDashboard.putNumber("intake lower value", INTAKE_LOWER_VOLTAGE);


    // create brushed motors for each of the motors on the launcher mechanism
    intake_spinMotor = new SparkFlex(INTAKE_SPIN_ID, MotorType.kBrushless);

    // create the configuration for the launcher roller, set a current limit, set
    // the motor to inverted so that positive values are used for both intaking and
    // launching, and apply the config to the controller
    SparkFlexConfig intakeSpinConfig = new SparkFlexConfig();
    intakeSpinConfig.inverted(false);
    intakeSpinConfig.smartCurrentLimit(INTAKE_LOWER_CURRENT_LIMIT);
    intake_spinMotor.configure(intakeSpinConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);

    // put default values for various fuel operations onto the dashboard
    // all commands using this subsystem pull values from the dashbaord to allow
    // you to tune the values easily, and then replace the values in Constants.java
    // with your new values. For more information, see the Software Guide.
    SmartDashboard.putNumber("intake spin value", INTAKE_SPIN_VOLTAGE);
  }

  // A method to set the voltage of the intake roller
  public void setIntakeLower(double voltage) {
    intake_lower_leaderMotor.setVoltage(voltage);
  }

  // A method to set the voltage of the intake roller
  public void setIntakeSpin(double voltage) {
    intake_spinMotor.setVoltage(voltage);
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
