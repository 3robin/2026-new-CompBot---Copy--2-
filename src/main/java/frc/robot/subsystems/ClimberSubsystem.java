// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.RelativeEncoder;
import com.revrobotics.spark.SparkBase.PersistMode;
import com.revrobotics.spark.SparkBase.ResetMode;
import com.revrobotics.spark.SparkClosedLoopController;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.config.SparkFlexConfig;
import com.revrobotics.spark.SparkFlex;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import static frc.robot.Constants.ClimberConstants.*;

public class ClimberSubsystem extends SubsystemBase {
  private final SparkFlex climber_leaderMotor;
  private final SparkFlex climber_followerMotor;
  private final RelativeEncoder ClimberEncoder;
  private final SparkClosedLoopController ClimberClosedLoopController;

  /** Creates a new CANBallSubsystem. 
     * @return */
    @SuppressWarnings("removal")
    public ClimberSubsystem() {
    // create brushed motors for each of the motors on the launcher mechanism
    climber_leaderMotor = new SparkFlex(CLIMBER_LEADER_ID, MotorType.kBrushless);
    climber_followerMotor = new SparkFlex(CLIMBER_FOLLOWER_ID, MotorType.kBrushless);

    ClimberClosedLoopController = climber_leaderMotor.getClosedLoopController();
    ClimberEncoder = climber_leaderMotor.getEncoder();

    // create the configuration for the launcher roller, set a current limit, set
    // the motor to inverted so that positive values are used for both intaking and
    // launching, and apply the config to the controller
    SparkFlexConfig climberConfig = new SparkFlexConfig();
    climberConfig.inverted(false);
    climberConfig.smartCurrentLimit(CLIMBER_CURRENT_LIMIT);
    climber_leaderMotor.configure(climberConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);

    SparkFlexConfig climberfollowerConfig = new SparkFlexConfig();
    climberfollowerConfig.inverted(false);
    climberfollowerConfig.smartCurrentLimit(CLIMBER_CURRENT_LIMIT);
    climberfollowerConfig.follow(CLIMBER_LEADER_ID);
    climber_followerMotor.configure(climberfollowerConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);

    // put default values for various fuel operations onto the dashboard
    // all commands using this subsystem pull values from the dashbaord to allow
    // you to tune the values easily, and then replace the values in Constants.java
    // with your new values. For more information, see the Software Guide.
    SmartDashboard.putNumber("climber value", CLIMBER_VOLTAGE);
  }
  // A method to set the voltage of the intake roller
  public void setClimber(double voltage) {
    climber_leaderMotor.setVoltage(voltage);
  }

  // A method to set the voltage of the intake roller
  //public void setfeederRoller(double voltage) {
    //feederRoller.setVoltage(voltage);
  //}

  // A method to stop the rollers
  public void stop() {
    //feederRoller.set(0);
    climber_leaderMotor.set(0);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
