package org.usfirst.frc.team5026.robot.commands;

import org.usfirst.frc.team5026.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriveForwardsCarveRight extends Command {

	private Timer timer = new Timer();
	private double seconds;
	private double negative;
	
    public DriveForwardsCarveRight(double seconds) {
    	requires(Robot.drive);
    	this.seconds = Math.abs(seconds);
    	this.negative = Math.abs(seconds) / seconds;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.drive.stopDriveMotors();
    	timer.reset();
    	timer.start();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.drive.setLeftRightMotors(0.40 * negative, 0.5 * negative);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return timer.hasPeriodPassed(seconds);
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.drive.stopDriveMotors();
    	timer.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
