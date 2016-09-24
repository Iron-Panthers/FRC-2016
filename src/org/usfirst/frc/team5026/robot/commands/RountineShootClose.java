package org.usfirst.frc.team5026.robot.commands;

import org.usfirst.frc.team5026.robot.Robot;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class RountineShootClose extends CommandGroup {
    
    public  RountineShootClose() {
    	addSequential(new StageTwoPulseBack());
    	addSequential(new ShooterWaitForStabilize(Robot.rpmUpperShooterAuto, Robot.rpmLowerShooterAuto));
        addSequential(new ShooterWaitForButton(1));
        addSequential(new StageTwoQueueToShooter());
        addSequential(new ShooterSlowStop());
    }
}
