package org.usfirst.frc.team5026.robot.commands;

import org.usfirst.frc.team5026.robot.Constants;
import org.usfirst.frc.team5026.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class VisionAngleRotation extends Command {

	double speed;
	
    public VisionAngleRotation(double speed) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.rotate);
    	requires(Robot.drive);
    	this.speed = speed;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.drive.stopDriveMotors();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	System.out.println("Turning until Gyro Angle = " + Robot.rotate.offsetAngle);
    	SmartDashboard.putNumber("offsetAngle", Robot.rotate.offsetAngle);
    	SmartDashboard.putNumber("Current Angle", Robot.rotate.getGyro());
    	if (Robot.rotate.offsetAngle - Robot.rotate.getGyro() < 0) {
    		// LEFT
    		Robot.drive.setLeftRightMotors(speed, -speed);
    	} else {
    		// RIGHT
    		Robot.drive.setLeftRightMotors(-speed, speed);
    	}
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return (Math.abs(Robot.rotate.offsetAngle - Robot.rotate.getGyro()) < Constants.ANGLE_THRESHOLD) || Robot.oi.leftTrigButton.get();
    }

    // Called once after isFinished returns true
    protected void end() {
    	if (Robot.oi.leftTrigButton.get()) {
    		System.out.println("CANCELLED!");
    	}
    	else {
    		System.out.println("COMPLETE! " + (Robot.rotate.offsetAngle - Robot.rotate.getGyro()) + " < " + Constants.ANGLE_THRESHOLD);
    	}
    	Robot.drive.stopDriveMotors();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() { // add interrupted for button presses
    	end();
    }
}

