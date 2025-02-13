// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;


import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.extendy;
import frc.robot.subsystems.flippy;

/* You should consider using the more terse Command factories API instead https://docs.wpilib.org/en/stable/docs/software/commandbased/organizing-command-based.html#defining-commands */
public class flipBack extends Command {

  /** Creates a new flipBack. */
  public flipBack(flippy f_Flippy) {
    addRequirements(f_Flippy);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if (extendy.extendyPosition.getDistance() < 10){
      flippy.leftPivot.setVoltage(flippy.wristPID.calculate(flippy.wristAngle, 45) + flippy.wristFeedForward.calculate(flippy.wristAngle, 0));
      flippy.rightPivot.setVoltage(flippy.wristPID.calculate(flippy.wristAngle, 45) + flippy.wristFeedForward.calculate(flippy.wristAngle, 0));
    }
    else{
      flippy.leftPivot.setVoltage(flippy.wristFeedForward.calculate(flippy.wristAngle, 0));
      flippy.rightPivot.setVoltage(flippy.wristFeedForward.calculate(flippy.wristAngle, 0));
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    flippy.leftPivot.setVoltage(flippy.wristFeedForward.calculate(flippy.wristAngle, 0));
    flippy.rightPivot.setVoltage(flippy.wristFeedForward.calculate(flippy.wristAngle, 0));
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    if (Math.abs(flippy.wristPID.calculate(flippy.wristEncoder.get(), 45)) <.005){
      return true;
    }
    else{
      return false;
    }
  }
}
