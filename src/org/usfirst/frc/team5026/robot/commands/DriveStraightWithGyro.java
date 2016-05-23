package org.usfirst.frc.team5026.robot.commands;

import org.usfirst.frc.team5026.robot.Constants;
import org.usfirst.frc.team5026.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriveStraightWithGyro extends Command {

	//TODO ADD THIS WHOLE THING
	double distanceInches;
	double seconds;
	double speed;
	
	Timer timer = new Timer();
	/*
	 * @param distanceInches: Distance to travel in inches
	 * @param seconds: Distance to travel in seconds
	 * @param speed: Voltage
	 */
    public DriveStraightWithGyro(double distanceInches, double seconds, double speed) {
        requires(Robot.drive);
        requires(Robot.rotate);
        this.distanceInches = distanceInches;
        this.seconds = seconds;
        this.speed = speed;
    }
    /*
     * @param seconds: Distance to travel in seconds
     * 0 Distance
     * 0.5 Speed
     */
    public DriveStraightWithGyro(double seconds) {
    	this(0, seconds, 0.5);
    }
    /*
     * @param distanceInches: Distance to travel in inches
     * @param speed: Voltage
     */
    public DriveStraightWithGyro(double distanceInches, double speed) {
    	this(distanceInches, 0, speed);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	if (seconds != 0) {
	    	timer = new Timer();
	    	timer.reset();
	    	timer.start();
    	}
    	Robot.rotate.align();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	//TODO STEPS:
    	/*
    	 * 1. Drive Forwards
    	 * 2. Adjust motor speed on side that the angle is changing
    	 * 3. If 3 deg. right, change the right side to slightly faster by some constant
    	 * 4. Drive Straight more
    	 * 5. Make sure to drive for the RIGHT amount of seconds
    	 */
    	// TRIED A NUMBER HERE: SHOULD BE GOING FULL SPEED TO COMPENSATE ON ONE SIDE IF OFFSET BY 45 DEGREES
    	double offset = Robot.rotate.getGyro() / Constants.ANGLE_PER_FULL_SPEED_COMPENSATION;
    	if (offset > 0) {
    		Robot.drive.setLeftRightMotors(speed, speed + offset);
    	} else {
    		Robot.drive.setLeftRightMotors(speed + offset, speed);
    	}
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	if (seconds != 0) {
    		return timer.hasPeriodPassed(seconds);
    	} else if (false) {
    		// Encoder Distances
    	}
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
