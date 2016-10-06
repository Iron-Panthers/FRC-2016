package org.usfirst.frc.team5026.robot.commands;

import org.usfirst.frc.team5026.robot.Robot;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * Shoots with a joystick button
 */
public class RoutineShootWithJoystick extends CommandGroup {
    
    public RoutineShootWithJoystick() {
        //addSequential(new RoutineShooterSpinupStabilize());
    	addSequential(new StageTwoPulseBack());
    	addSequential(new ShooterWaitForStabilize());
        addSequential(new ShooterWaitForButton(1));
        addSequential(new StageTwoQueueToShooter());
        addSequential(new ShooterSlowStop());
    }
}