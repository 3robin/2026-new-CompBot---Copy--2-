// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.IntakeSubsystem;
import static frc.robot.Constants.IntakeConstants.*;

/* You should consider using the more terse Command factories API instead https://docs.wpilib.org/en/stable/docs/software/commandbased/organizing-command-based.html#defining-commands */
public class IntakeReverseSpin extends Command {

    IntakeSubsystem intakeSubsystem;

    /** Creates a new Intake Reverse Spin command. */
    public IntakeReverseSpin(IntakeSubsystem intakeSystem) {
        addRequirements(intakeSystem);
        this.intakeSubsystem = intakeSystem;
    }

    // @Override
    // public void initialize() {
    //     intakeSubsystem.setIntakeSpin(INTAKE_REV_SPIN_VOLTAGE);  
    // }

    @Override
    public void initialize() {
        intakeSubsystem
        .setintakeSpinVelocity(INTAKE_SPIN_REV_VELOCITY);
  }
  
    @Override
    public void execute() {
    }

    @Override
    public void end(boolean interrupted) {
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
