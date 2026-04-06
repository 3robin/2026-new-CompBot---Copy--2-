// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.VerticalTransferSubsystem;
import static frc.robot.Constants.VerticalTransferConstants.*;

/* You should consider using the more terse Command factories API instead https://docs.wpilib.org/en/stable/docs/software/commandbased/organizing-command-based.html#defining-commands */
public class VerticalReverseTransfer extends Command {
  /** Creates a new Intake. */

  VerticalTransferSubsystem VTSubsystem;

  public VerticalReverseTransfer(VerticalTransferSubsystem VerticalSystem) {
    addRequirements(VerticalSystem);
    this.VTSubsystem = VerticalSystem;
  }

  public VerticalReverseTransfer(Command run) {
    //TODO Auto-generated constructor stub
}

// Called when the command is initially scheduled. Set the rollers to the
 // appropriate values for intaking
  // @Override
  // public void initialize() {
  //   VTSubsystem
  //       .setVerticalTransfer((
  //           SmartDashboard.getNumber("vertical transfer 1 value", VERTICAL_TRANSFER_1_VOLTAGE)),
  //               SmartDashboard.getNumber("vertical transfer 2 value", VERTICAL_TRANSFER_2_VOLTAGE));
  // }

  @Override
  public void initialize() {
    VTSubsystem
      .setVerticalTransfer1Velocity(VERTICAL_TRANSFER_REV_1_VELOCITY);
    VTSubsystem
      .setVerticalTransfer2Velocity(VERTICAL_TRANSFER_REV_2_VELOCITY);
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
