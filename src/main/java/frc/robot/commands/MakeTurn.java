// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.GyroSubsystem;

public class MakeTurn extends CommandBase {
  /** Creates a new MakeTurn. */

  DriveTrain driveTrain;
  double kP = 0.05;
  double angleToTurn;
  double currentHeading;
  double movement;
  GyroSubsystem gyroSubsystem;

  public MakeTurn(DriveTrain drive, GyroSubsystem gyroGetter, double angleOfTurn) {
    driveTrain = drive;
    gyroSubsystem = gyroGetter;
    currentHeading = gyroSubsystem.getAngle();
    angleToTurn = (angleOfTurn + currentHeading)%360;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(driveTrain, gyroSubsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    while (movement > 0.1 || movement < -0.1) {
      movement = angleToTurn - gyroSubsystem.getAngle();
      driveTrain.kDrive.tankDrive(kP*movement, -kP*movement);
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    driveTrain.stopMotors();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
