package org.usfirst.frc.team5026.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 *
 */
public class RoutineAutoAlign extends CommandGroup {
    
    public RoutineAutoAlign() {
    	double speed = 0.27; //0.18
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
        
        addSequential(new VisionMoveToOptimal());
        addSequential(new WaitCommand(delay));
        addSequential(new VisionMoveToOptimal());
        addSequential(new WaitCommand(delay));
        addSequential(new VisionMoveToOptimal());
        
        addSequential(new VisionAngleCalculation());
        addSequential(new VisionAngleRotation(speed));
        addSequential(new WaitCommand(delay));
        addSequential(new VisionAngleCalculation());
        addSequential(new VisionAngleRotation(speed));
        addSequential(new WaitCommand(delay));
        addSequential(new VisionAngleCalculation());
        addSequential(new VisionAngleRotation(speed));
        
        
        
    	//addSequential(new VisionAngleCoarseRotation()); //Without Gyro
        
        //addSequential(new VisionAngleCalculation());
        //addSequential(new VisionAngleRotation(speed));
    }
}