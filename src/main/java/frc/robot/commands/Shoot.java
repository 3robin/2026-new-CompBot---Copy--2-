// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.flywheelShooterSubsystem;
import static frc.robot.Constants.ShooterConstants.*;

/* You should consider using the more terse Command factories API instead https://docs.wpilib.org/en/stable/docs/software/commandbased/organizing-command-based.html#defining-commands */
public class Shoot extends Command {
  /** Creates a new Intake. */

  flywheelShooterSubsystem shooterSubsystem;

  public Shoot(flywheelShooterSubsystem flywheelSystem) {
    addRequirements(flywheelSystem);
    this.shooterSubsystem = flywheelSystem;
  }

  public Shoot(Command run) {
    //TODO Auto-generated constructor stub
}

// Called when the command is initially scheduled. Set the rollers to the
 // appropriate values for intaking
  @Override
  public void initialize() {
    shooterSubsystem
        .setflywheelShooterVelocity( SHOOTER_FLYWHEEL_VELOCITY);
    // fuelSubsystem.setFeederRoller(SmartDashboard.getNumber("Launching feeder roller value", LAUNCHING_FEEDER_VOLTAGE));
  }

  // Called every time the scheduler runs while the command is scheduled. This
  // command doesn't require updating any values while running
  @Override
  public void execute() {
  }

  // Called once the command ends or is interrupted. Stop the rollers
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
