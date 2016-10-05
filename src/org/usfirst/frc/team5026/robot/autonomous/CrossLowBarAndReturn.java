package org.usfirst.frc.team5026.robot.autonomous;

import org.usfirst.frc.team5026.robot.commands.DriveForwardsForTime;
import org.usfirst.frc.team5026.robot.commands.IntakeArmLower;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class CrossLowBarAndReturn extends CommandGroup {
    
    public  CrossLowBarAndReturn() {
    	addSequential(new IntakeArmLower());
        addSequential(new DriveForwardsForTime(3));
        addSequential(new DriveForwardsForTime(-3));
    }
}
