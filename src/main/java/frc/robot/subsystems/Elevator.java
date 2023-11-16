// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import frc.robot.Constants.ElevatorConstants;

public class Elevator extends SubsystemBase {

  private final CANSparkMax elevatorMotor1 = new CANSparkMax(ElevatorConstants.firstElevatorPort,
      MotorType.kBrushless);
  private final CANSparkMax elevatorMotor2 = new CANSparkMax(ElevatorConstants.secondElevatorPort,
      MotorType.kBrushless);

  /** Creates a new Elevator. */
  public Elevator() {
    elevatorMotor1.restoreFactoryDefaults();
    elevatorMotor2.restoreFactoryDefaults();

    elevatorMotor2.follow(elevatorMotor1);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void stopMotors() {
    elevatorMotor1.stopMotor();
    elevatorMotor2.stopMotor();
  }

  public void raiseElevator() {
    elevatorMotor1.set(1);
  }

  public void lowerElevator() {
    elevatorMotor1.set(-1);
  }

}
