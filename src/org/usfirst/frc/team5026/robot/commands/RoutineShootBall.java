package org.usfirst.frc.team5026.robot.commands;

import org.usfirst.frc.team5026.robot.Constants;
import org.usfirst.frc.team5026.robot.Robot;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class RoutineShootBall extends CommandGroup {
    
    public  RoutineShootBall() {
    	addSequential(new ShooterWaitForStabilize(Constants.UPPER_SHOOTER_RPM_9, Constants.LOWER_SHOOTER_RPM_6));
    	addSequential(new StageTwoQueueToShooter());
    	addSequential(new ShooterSlowStop());
    }
}
