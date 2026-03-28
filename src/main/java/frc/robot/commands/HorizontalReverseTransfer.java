// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.HorizontalTransferSubsystem;
import static frc.robot.Constants.HorizontalTransferConstants.*;

/* You should consider using the more terse Command factories API instead https://docs.wpilib.org/en/stable/docs/software/commandbased/organizing-command-based.html#defining-commands */
public class HorizontalReverseTransfer extends Command {
    /** Creates a new Horizontal Reverse Transfer command. */

    HorizontalTransferSubsystem HTSubsystem;

    public HorizontalReverseTransfer(HorizontalTransferSubsystem HorizontalSystem) {
        addRequirements(HorizontalSystem);
        this.HTSubsystem = HorizontalSystem;
    }

    // @Override
    // public void initialize() {
    //     HTSubsystem.setHorizontalTransfer(HORIZONTAL_REV_TRANSFER_VOLTAGE);
    // }

    // @Override
    // public void initialize() {
    //   HTSubsystem
    //       .setHorizontalTransferVelocity(HORIZONTAL_TRANSFER_REV_VELOCITY);
    // }

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
