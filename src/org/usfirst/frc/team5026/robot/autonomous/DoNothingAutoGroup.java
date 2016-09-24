package org.usfirst.frc.team5026.robot.autonomous;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class DoNothingAutoGroup extends CommandGroup {
    
    public  DoNothingAutoGroup() {
        addSequential(new DoNothingAutonomous());
    }
}
