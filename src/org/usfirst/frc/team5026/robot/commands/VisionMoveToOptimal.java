package org.usfirst.frc.team5026.robot.commands;

import org.usfirst.frc.team5026.robot.Constants;
import org.usfirst.frc.team5026.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class VisionMoveToOptimal extends Command {

	double secondsTillDone = 0;
	Timer timer = new Timer();
	
    public VisionMoveToOptimal() {
        requires(Robot.rotate);
        requires(Robot.drive);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.drive.setLeftRightMotors(0, 0);
    	Robot.rotate.offsetDistance();
    	secondsTillDone = Math.abs(Robot.rotate.deltaY / Constants.Y_PIXELS_PER_SECOND);
    	timer.reset();
    	timer.start();
    	System.out.println("Going Backwards: " + Robot.rotate.close + " for " + secondsTillDone);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if (!Robot.rotate.close) {
    		Robot.drive.setLeftRightMotors(0.5, 0.5);
    	} else {
    		Robot.drive.setLeftRightMotors(-0.5, -0.5);
    	}
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return timer.hasPeriodPassed(secondsTillDone);
    }

    // Called once after isFinished returns true
    protected void end() {
    	timer.stop();
    	Robot.drive.setLeftRightMotors(0, 0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
