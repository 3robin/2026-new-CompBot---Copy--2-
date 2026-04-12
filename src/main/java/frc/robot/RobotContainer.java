// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.pathplanner.lib.auto.AutoBuilder;
import com.pathplanner.lib.auto.NamedCommands;
import edu.wpi.first.math.controller.ProfiledPIDController;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.trajectory.TrapezoidProfile.Constraints;
import edu.wpi.first.math.util.Units;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Filesystem;
import edu.wpi.first.wpilibj.RobotBase;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.Constants.OperatorConstants;
import frc.robot.subsystems.swervedrive.SwerveSubsystem;
import frc.robot.commands.Climb;
import frc.robot.commands.HorizontalReverseTransfer;
import frc.robot.commands.IntakeLower;
import frc.robot.commands.IntakeSpin;
import frc.robot.commands.IntakeRaise;
import frc.robot.commands.IntakeReverseSpin;
import frc.robot.commands.Shoot;
import frc.robot.commands.VerticalReverseTransfer;
import frc.robot.commands.HorizontalTransfer;
import frc.robot.commands.VerticalTransfer;
import frc.robot.subsystems.flywheelShooterSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.HorizontalTransferSubsystem;
import frc.robot.subsystems.VerticalTransferSubsystem;
import frc.robot.subsystems.ClimberSubsystem;

import static frc.robot.Constants.ClimberConstants.voltage;


import java.io.File;
import swervelib.SwerveInputStream;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a "declarative" paradigm, very
 * little robot logic should actually be handled in the {@link Robot} periodic methods (other than the scheduler calls).
 * Instead, the structure of the robot (including subsystems, commands, and trigger mappings) should be declared here.
 */
public class RobotContainer
{

  // Replace with CommandPS4Controller or CommandJoystick if needed
  final CommandXboxController driverXbox = new CommandXboxController(0);
  final CommandXboxController operatorController = new CommandXboxController(1);

  // The robot's subsystems and commands are defined here...
  private final SwerveSubsystem       drivebase  = new SwerveSubsystem(new File(Filesystem.getDeployDirectory(),
                                                                                "swerve/modules"));
  private final flywheelShooterSubsystem flywheelSubsystem = new flywheelShooterSubsystem();
  private final IntakeSubsystem intakeSubsystem = new IntakeSubsystem();
  private final HorizontalTransferSubsystem HTSubsystem = new HorizontalTransferSubsystem();
  private final VerticalTransferSubsystem VTSubsystem = new VerticalTransferSubsystem();
  //private final ClimberSubsystem climberSubsystem = new ClimberSubsystem();


  // Establish a Sendable Chooser that will be able to be sent to the SmartDashboard, allowing selection of desired auto
  private final SendableChooser<Command> autoChooser;

  /**
   * Converts driver input into a field-relative ChassisSpeeds that is controlled by angular velocity.
   */
  SwerveInputStream driveAngularVelocity = SwerveInputStream.of(drivebase.getSwerveDrive(),
                                                                () -> driverXbox.getLeftY() * -1,
                                                                () -> driverXbox.getLeftX() * -1)
                                                            .withControllerRotationAxis(driverXbox::getRightX)
                                                            .deadband(OperatorConstants.DEADBAND)
                                                            .scaleTranslation(0.8)
                                                            .allianceRelativeControl(false);

  /**
   * Clone's the angular velocity input stream and converts it to a fieldRelative input stream.
   */
  SwerveInputStream driveDirectAngle = driveAngularVelocity.copy().withControllerHeadingAxis(driverXbox::getRightX,
                                                                                             driverXbox::getRightY)
                                                           .headingWhile(true);

  /**
   * Clone's the angular velocity input stream and converts it to a robotRelative input stream.
   */
  SwerveInputStream driveRobotOriented = driveAngularVelocity.copy().robotRelative(true)
                                                             .allianceRelativeControl(false);

  SwerveInputStream driveAngularVelocityKeyboard = SwerveInputStream.of(drivebase.getSwerveDrive(),
                                                                        () -> -driverXbox.getLeftY(),
                                                                        () -> -driverXbox.getLeftX())
                                                                    .withControllerRotationAxis(() -> driverXbox.getRawAxis(
                                                                        2))
                                                                    .deadband(OperatorConstants.DEADBAND)
                                                                    .scaleTranslation(0.8)
                                                                    .allianceRelativeControl(false);
  // Derive the heading axis with math!
  SwerveInputStream driveDirectAngleKeyboard     = driveAngularVelocityKeyboard.copy()
                                                                               .withControllerHeadingAxis(() ->
                                                                                                              Math.sin(
                                                                                                                  driverXbox.getRawAxis(
                                                                                                                      2) *
                                                                                                                  Math.PI) *
                                                                                                              (Math.PI *
                                                                                                               2),
                                                                                                          () ->
                                                                                                              Math.cos(
                                                                                                                  driverXbox.getRawAxis(
                                                                                                                      2) *
                                                                                                                  Math.PI) *
                                                                                                              (Math.PI *
                                                                                                               2))
                                                                               .headingWhile(true)
                                                                               .translationHeadingOffset(true)
                                                                               .translationHeadingOffset(Rotation2d.fromDegrees(
                                                                                   0));

  /**
   * The container for the robot. Contains subsystems, OI devices, and commands.
   */
  public RobotContainer()
  {
    // Configure the trigger bindings
    configureBindings();
    DriverStation.silenceJoystickConnectionWarning(true);
    
    //Create the NamedCommands that will be used in PathPlanner
    NamedCommands.registerCommand("test", Commands.print("I EXIST"));

    NamedCommands.registerCommand("shoot", (new Shoot(flywheelSubsystem)));

    // NamedCommands.registerCommand("horizontal_transfer", (new HorizontalTransfer(HTSubsystem)));

    NamedCommands.registerCommand("vertical_transfer", (new VerticalTransfer(VTSubsystem)));
  
    NamedCommands.registerCommand("intake_roller", (new IntakeSpin(intakeSubsystem)));

    NamedCommands.registerCommand("intake_arm_down", (new IntakeLower(intakeSubsystem)));

    NamedCommands.registerCommand("intake_arm_up", (new IntakeRaise(intakeSubsystem)));

    NamedCommands.registerCommand("reset_gyro", (Commands.runOnce(drivebase::zeroGyro)));

          // driverXbox.b().and(driverXbox.a()).onTrue((Commands.runOnce(drivebase::zeroGyro)));



    //Have the autoChooser pull in all PathPlanner autos as options
    autoChooser = AutoBuilder.buildAutoChooser();

    //Set the default auto (do nothing) 
    autoChooser.setDefaultOption("Do Nothing", Commands.none());

    //Add a simple auto option to have the robot drive forward for 1 second then stop
    autoChooser.addOption("Drive Forward", drivebase.driveForward().withTimeout(1));
    
    //Put the autoChooser on the SmartDashboard
    SmartDashboard.putData("Auto Chooser", autoChooser);

  }

  /**
   * Use this method to define your trigger->command mappings. Triggers can be created via the
   * {@link Trigger#Trigger(java.util.function.BooleanSupplier)} constructor with an arbitrary predicate, or via the
   * named factories in {@link edu.wpi.first.wpilibj2.command.button.CommandGenericHID}'s subclasses for
   * {@link CommandXboxController Xbox}/{@link edu.wpi.first.wpilibj2.command.button.CommandPS4Controller PS4}
   * controllers or {@link edu.wpi.first.wpilibj2.command.button.CommandJoystick Flight joysticks}.
   */
  private void configureBindings()
  {
    Command driveFieldOrientedDirectAngle      = drivebase.driveFieldOriented(driveDirectAngle);
    Command driveFieldOrientedAnglularVelocity = drivebase.driveFieldOriented(driveAngularVelocity);
    Command driveRobotOrientedAngularVelocity  = drivebase.driveFieldOriented(driveRobotOriented);
    Command driveSetpointGen = drivebase.driveWithSetpointGeneratorFieldRelative(
        driveDirectAngle);
    Command driveFieldOrientedDirectAngleKeyboard      = drivebase.driveFieldOriented(driveDirectAngleKeyboard);
    Command driveFieldOrientedAnglularVelocityKeyboard = drivebase.driveFieldOriented(driveAngularVelocityKeyboard);
    Command driveSetpointGenKeyboard = drivebase.driveWithSetpointGeneratorFieldRelative(
        driveDirectAngleKeyboard);

    if (RobotBase.isSimulation())
    {
      drivebase.setDefaultCommand(driveFieldOrientedDirectAngleKeyboard);
    } else
    {
      drivebase.setDefaultCommand(driveFieldOrientedAnglularVelocity);
    }

    if (Robot.isSimulation())
    {
      Pose2d target = new Pose2d(new Translation2d(1, 4),
                                 Rotation2d.fromDegrees(90));
      //drivebase.getSwerveDrive().field.getObject("targetPose").setPose(target);
      driveDirectAngleKeyboard.driveToPose(() -> target,
                                           new ProfiledPIDController(5,
                                                                     0,
                                                                     0,
                                                                     new Constraints(5, 2)),
                                           new ProfiledPIDController(5,
                                                                     0,
                                                                     0,
                                                                     new Constraints(Units.degreesToRadians(360),
                                                                                     Units.degreesToRadians(180))
                                           ));
      driverXbox.start().onTrue(Commands.runOnce(() -> drivebase.resetOdometry(new Pose2d(3, 3, new Rotation2d()))));
      driverXbox.button(1).whileTrue(drivebase.sysIdDriveMotorCommand());
      driverXbox.button(2).whileTrue(Commands.runEnd(() -> driveDirectAngleKeyboard.driveToPoseEnabled(true),
                                                     () -> driveDirectAngleKeyboard.driveToPoseEnabled(false)));

//      driverXbox.b().whileTrue(
//          drivebase.driveToPose(
//              new Pose2d(new Translation2d(4, 4), Rotation2d.fromDegrees(0)))
//                              );

    }
    if (DriverStation.isTest())
    {
      drivebase.setDefaultCommand(driveFieldOrientedAnglularVelocity); // Overrides drive command above!

      driverXbox.x().whileTrue(Commands.runOnce(drivebase::lock, drivebase).repeatedly());
      driverXbox.start().onTrue((Commands.runOnce(drivebase::zeroGyro)));
      driverXbox.back().whileTrue(drivebase.centerModulesCommand());
      driverXbox.leftBumper().onTrue(Commands.none());
      driverXbox.rightBumper().onTrue(Commands.none());
    } else
    {
      
      driverXbox.x().onTrue(Commands.runOnce(drivebase::addFakeVisionReading));
      driverXbox.start().whileTrue(Commands.none());
      driverXbox.back().whileTrue(Commands.none());
      driverXbox.leftBumper().whileTrue(Commands.runOnce(drivebase::lock, drivebase).repeatedly());
      driverXbox.rightBumper().onTrue(Commands.none());
      driverXbox.b().and(driverXbox.a()).onTrue((Commands.runOnce(drivebase::zeroGyro)));


      // operatorController.rightTrigger().whileTrue(Commands.parallel(
      //   new Shoot(flywheelSubsystem),
      //   Commands.sequence(command.wait(1.0), new VerticalTransfer(VTSubsystem)));

      operatorController.rightTrigger().whileTrue(Commands.parallel
      (new Shoot(flywheelSubsystem),
      Commands.sequence(Commands.waitSeconds(1.0),new VerticalTransfer(VTSubsystem))));

      // ^need to delay until shooter is up to speed

      // whole transfer system 



      operatorController.leftTrigger().whileTrue(new Shoot(flywheelSubsystem));
      flywheelSubsystem.setDefaultCommand(flywheelSubsystem.run(() -> flywheelSubsystem.stop()));



      operatorController.y().whileTrue(new VerticalTransfer(VTSubsystem));
      operatorController.b().whileTrue(new VerticalReverseTransfer(VTSubsystem));
      VTSubsystem.setDefaultCommand(VTSubsystem.run(() -> VTSubsystem.stop()));

      // operatorController.leftBumper().whileTrue(new NegIntakeLower(intakeSubsystem));
      // intakeSubsystem.setDefaultCommand(intakeSubsystem.run(() -> intakeSubsystem.stop()));

      operatorController.rightBumper().whileTrue(new IntakeRaise(intakeSubsystem));
      intakeSubsystem.setDefaultCommand(intakeSubsystem.run(() -> intakeSubsystem.stop()));

      operatorController.leftBumper().whileTrue(new IntakeLower(intakeSubsystem));
      intakeSubsystem.setDefaultCommand(intakeSubsystem.run(() -> intakeSubsystem.stop()));


      // operatorController.leftTrigger().whileTrue(
      //   new ParallelCommandGroup(
      //     new HorizontalTransfer(HTSubsystem),
      //     new VerticalTransfer(VTSubsystem)
      //   )
      // ); 

      operatorController.x().whileTrue(new IntakeSpin(intakeSubsystem));
      operatorController.a().whileTrue(new IntakeReverseSpin(intakeSubsystem));
      intakeSubsystem.setDefaultCommand(intakeSubsystem.run(() -> intakeSubsystem.stop()));

      // operatorController.y().whileTrue(new HorizontalTransfer(HTSubsystem));
      // operatorController.b().whileTrue(new HorizontalReverseTransfer(HTSubsystem));
      // HTSubsystem.setDefaultCommand(HTSubsystem.run(() -> HTSubsystem.stop()));

      // operatorController.b().whileTrue(new Climb(climberSubsystem));
      // climberSubsystem.setDefaultCommand(climberSubsystem.run(() -> climberSubsystem.stop()));
    }

  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand()
  {
    // Pass in the selected auto from the SmartDashboard as our desired autnomous commmand 
    return autoChooser.getSelected();
  }

  public void setMotorBrake(boolean brake)
  {
    drivebase.setMotorBrake(brake);
  }
}
