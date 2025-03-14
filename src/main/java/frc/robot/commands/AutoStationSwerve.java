// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants;
import frc.robot.subsystems.Swerve;
import frc.robot.subsystems.limelight;

/* You should consider using the more terse Command factories API instead https://docs.wpilib.org/en/stable/docs/software/commandbased/organizing-command-based.html#defining-commands */
public class AutoStationSwerve extends Command {
  private Swerve s_Swerve;
  private limelight l_Limelight;
  /** Creates a new AutoStationSwerve. */
  public AutoStationSwerve(Swerve s_Swerve, limelight l_Limelight) {
    this.s_Swerve = s_Swerve;
    this.l_Limelight  = l_Limelight;
    addRequirements(s_Swerve);
    
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if ((l_Limelight.ID1 == 1.0 || l_Limelight.ID1 == 2.0 || l_Limelight.ID1 == 12.0 || l_Limelight.ID1 == 13.0) && (Math.abs(l_Limelight.x2) > 1   || l_Limelight.stationd > .5)){
          s_Swerve.drive(
            new Translation2d(l_Limelight.stationd,0)
            .times(Constants.Swerve.maxSpeed * .025), 
            -l_Limelight.x2  *.1, 
            false, 
            true);}
      else {
          s_Swerve.drive(
              new Translation2d(0,0),
              0, 
              false, 
              true);}
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    s_Swerve.drive(
            new Translation2d(0,0),
            0, 
            false, 
            true);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    if (Math.abs(l_Limelight.x2) > 1 || Math.abs(l_Limelight.stationd) > .5    || s_Swerve.mSwerveMods[0].getState().speedMetersPerSecond != 0){
      return false;
    
    }
    
    else{
      return true;
    }
  }
  }


