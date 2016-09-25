package org.usfirst.frc.team5026.robot.commands;

import org.usfirst.frc.team5026.robot.Robot;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class RoutineShootClose extends CommandGroup {
    
    public  RoutineShootClose() {
    	addSequential(new StageTwoPulseBack());
    	addSequential(new ShooterWaitForStabilize(Robot.rpmUpperShooterAuto, Robot.rpmLowerShooterAuto));
        addSequential(new ShooterWaitForButton(8));
        addSequential(new StageTwoQueueToShooter());
        addSequential(new ShooterSlowStop());
    }
}
