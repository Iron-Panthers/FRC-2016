package org.usfirst.frc.team5026.robot.commands;

import org.usfirst.frc.team5026.robot.Constants;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class RoutineShooterShootBall extends CommandGroup {
    
    public  RoutineShooterShootBall() {
    	addSequential(new StageTwoPulseBack());
        addSequential(new ShooterShootRPM(Constants.UPPER_SHOOTER_RPM_9, Constants.LOWER_SHOOTER_RPM_9));
    }
}
