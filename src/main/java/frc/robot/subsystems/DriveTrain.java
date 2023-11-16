// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import frc.robot.Constants.DriveTrainConstants;

public class DriveTrain extends SubsystemBase {

  /** Creates a new DriveTrain. */

  private final CANSparkMax frontLeftMotor = new CANSparkMax(DriveTrainConstants.frontLeftMotorPort,
      MotorType.kBrushless);
  private final CANSparkMax frontRightMotor = new CANSparkMax(DriveTrainConstants.frontRightMotorPort,
      MotorType.kBrushless);

  private final CANSparkMax backLeftMotor = new CANSparkMax(DriveTrainConstants.backLeftMotorPort,
      MotorType.kBrushless);
  private final CANSparkMax backRightMotor = new CANSparkMax(DriveTrainConstants.backRightMotorPort,
      MotorType.kBrushless);

  public DifferentialDrive kDrive;

  public DriveTrain() {
    frontLeftMotor.restoreFactoryDefaults();
    frontRightMotor.restoreFactoryDefaults();

    backLeftMotor.restoreFactoryDefaults();
    backRightMotor.restoreFactoryDefaults();

    backRightMotor.follow(frontRightMotor);
    backLeftMotor.follow(frontLeftMotor);

    frontRightMotor.setInverted(true);
    backRightMotor.setInverted(true);

    kDrive = new DifferentialDrive(frontLeftMotor, frontRightMotor);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void stopMotors() {
    frontLeftMotor.stopMotor();
    frontRightMotor.stopMotor();
    backLeftMotor.stopMotor();
    backRightMotor.stopMotor();
  }

}
