package org.usfirst.frc.team5026.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class RoutineAlignRPMs extends CommandGroup {
    
    public  RoutineAlignRPMs() {
        addSequential(new VisionRPMChange());
    }
}
