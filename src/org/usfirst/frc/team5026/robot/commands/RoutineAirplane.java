package org.usfirst.frc.team5026.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 *
 */
public class RoutineAirplane extends CommandGroup {
    
    public  RoutineAirplane() {
        addSequential(new ShooterShootRPM(-14000, -14000));
        addSequential(new WaitCommand(7));
        addSequential(new StageTwoQueueToShooter());
        addSequential(new ShooterSlowStop());
    }
}
