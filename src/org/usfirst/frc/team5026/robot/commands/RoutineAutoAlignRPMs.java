package org.usfirst.frc.team5026.robot.commands;

import org.usfirst.frc.team5026.robot.Robot;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 *
 */
public class RoutineAutoAlignRPMs extends CommandGroup {
    
    public  RoutineAutoAlignRPMs() {
    	double speed = 0.35; //0.24
    	addSequential(new VisionAngleCalculation());
    	//Robot.rotate.offsetAngle = 30;
    	double delay = 0.55;
        addSequential(new VisionAngleRotation(speed));
        addSequential(new WaitCommand(delay));
        addSequential(new VisionAngleCalculation());
        addSequential(new VisionAngleRotation(speed));
        addSequential(new WaitCommand(delay));
        addSequential(new VisionAngleCalculation());
        addSequential(new VisionAngleRotation(speed));
        addSequential(new VisionRPMChange());
        addSequential(new StageTwoPulseBack());
        // rpmUpper and lower are messed up here (somehow) perhaps, not set?
        // ISSUE DISCOVERED: THIS IS ALL IN A CONSTRUCTOR, SO AT THE TIME, THESE VALUES ARE DIFFERENT THAN THE REALTIME VALUES
        addSequential(new ShooterShootRPM());
        addSequential(new ShooterWaitForStabilize());
        addSequential(new StageTwoQueueToShooter());
        addSequential(new ShooterSlowStop());
    }
}
