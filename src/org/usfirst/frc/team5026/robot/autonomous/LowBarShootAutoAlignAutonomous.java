package org.usfirst.frc.team5026.robot.autonomous;

import org.usfirst.frc.team5026.robot.Constants;
import org.usfirst.frc.team5026.robot.Robot;
import org.usfirst.frc.team5026.robot.commands.DriveForwardsCarveRight;
import org.usfirst.frc.team5026.robot.commands.DriveRotateThetaWithGyro;
import org.usfirst.frc.team5026.robot.commands.IntakeArmLower;
import org.usfirst.frc.team5026.robot.commands.IntakeArmRaise;
import org.usfirst.frc.team5026.robot.commands.IntakeRollerSpinIn;
import org.usfirst.frc.team5026.robot.commands.IntakeRollerStop;
import org.usfirst.frc.team5026.robot.commands.RoutineAutoAlignRPMs;
import org.usfirst.frc.team5026.robot.commands.ShooterShootRPM;
import org.usfirst.frc.team5026.robot.commands.ShooterSlowStop;
import org.usfirst.frc.team5026.robot.commands.ShooterWaitForStabilize;
import org.usfirst.frc.team5026.robot.commands.StageTwoIntake;
import org.usfirst.frc.team5026.robot.commands.StageTwoPulseBack;
import org.usfirst.frc.team5026.robot.commands.StageTwoQueueToShooter;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class LowBarShootAutoAlignAutonomous extends CommandGroup {
    
    public  LowBarShootAutoAlignAutonomous() {
        addSequential(new StageTwoIntake());
        addSequential(new IntakeRollerSpinIn());
        addSequential(new IntakeArmLower());
        addSequential(new DriveRotateThetaWithGyro(-15));
        addSequential(new DriveForwardsCarveRight(4));
        addSequential(new StageTwoIntake());
        addSequential(new StageTwoPulseBack());
        addSequential(new ShooterShootRPM(Constants.UPPER_SHOOTER_RPM_9, Constants.LOWER_SHOOTER_RPM_9)); // COMMENT ME IF I DO NOT WORK AS I SHOULD
        addSequential(new IntakeRollerStop());
        addSequential(new IntakeArmRaise());
        addSequential(new DriveRotateThetaWithGyro(15));
        //addSequential(new DriveFowardsForTime(1));
        addSequential(new RoutineAutoAlignRPMs());
        addSequential(new RoutineAutoAlignRPMs());
        //addSequential(new RoutineAutoAlign());
        addSequential(new ShooterWaitForStabilize(Robot.rpmUpperShooter, Robot.rpmLowerShooter));
        addSequential(new StageTwoQueueToShooter());
        addSequential(new ShooterSlowStop());
    }
}
