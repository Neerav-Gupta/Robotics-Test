// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.robot.Constants.OperatorConstants;
import frc.robot.commands.ArcadeDrive;
import frc.robot.commands.AutoCommand;
import frc.robot.commands.LowerElevator;
import frc.robot.commands.RaiseElevator;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Elevator;
import frc.robot.subsystems.GyroSubsystem;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.Trigger;

/**
 * This class is where the bulk of the robot should be declared. Since
 * Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in
 * the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of
 * the robot (including
 * subsystems, commands, and trigger mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  private final GyroSubsystem gyroSubsystem = new GyroSubsystem();
  private final DriveTrain driveTrain = new DriveTrain();
  private final Elevator elevator = new Elevator();

  // Replace with CommandPS4Controller or CommandJoystick if needed
  private final Joystick xboxController = new Joystick(OperatorConstants.joystickPort);
  private final JoystickButton raiseElevatorButton = new JoystickButton(xboxController, OperatorConstants.raiseElevatorPort);
  private final JoystickButton lowerElevatorButton = new JoystickButton(xboxController, OperatorConstants.lowerElevatorPort);

  /**
   * The container for the robot. Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    // Configure the trigger bindings
    configureBindings();
  }

  /**
   * Use this method to define your trigger->command mappings. Triggers can be
   * created via the
   * {@link Trigger#Trigger(java.util.function.BooleanSupplier)} constructor with
   * an arbitrary
   * predicate, or via the named factories in {@link
   * edu.wpi.first.wpilibj2.command.button.CommandGenericHID}'s subclasses for
   * {@link
   * CommandXboxController
   * Xbox}/{@link edu.wpi.first.wpilibj2.command.button.CommandPS4Controller
   * PS4} controllers or
   * {@link edu.wpi.first.wpilibj2.command.button.CommandJoystick Flight
   * joysticks}.
   */
  private void configureBindings() {

    driveTrain.setDefaultCommand(
        new ArcadeDrive(driveTrain, () -> xboxController.getRawAxis(OperatorConstants.verticalLeftJoystickAxis),
            () -> xboxController.getRawAxis(OperatorConstants.horizontalRightJoystickAxis)));

    raiseElevatorButton.whileTrue(new RaiseElevator(elevator));
    lowerElevatorButton.whileTrue(new LowerElevator(elevator));

  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An example command will be run in autonomous
    return new AutoCommand(driveTrain, gyroSubsystem);
  }
}
