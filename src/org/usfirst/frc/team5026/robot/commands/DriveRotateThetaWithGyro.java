package org.usfirst.frc.team5026.robot.commands;

import org.usfirst.frc.team5026.robot.Constants;
import org.usfirst.frc.team5026.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class DriveRotateThetaWithGyro extends Command {

	private double degrees;
	private double speed = 0.3;
	
    public DriveRotateThetaWithGyro(double degrees) {
        requires(Robot.drive);
        requires(Robot.rotate);
        this.degrees = degrees;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.rotate.align();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	System.out.println("Turning until Gyro Angle = " + degrees);
    	SmartDashboard.putNumber("offsetAngle", Robot.rotate.offsetAngle);
    	SmartDashboard.putNumber("Current Angle", Robot.rotate.getGyro());
    	if (degrees - Robot.rotate.getGyro() < 0) {
    		Robot.drive.setLeftRightMotors(speed, -speed);
    	} else {
    		Robot.drive.setLeftRightMotors(-speed, speed);
    	}
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	return (Math.abs(degrees - Robot.rotate.getGyro()) < Constants.ANGLE_THRESHOLD);
    }

    // Called once after isFinished returns true
    protected void end() {
    	if (Robot.oi.leftBumper.get()) {
    		System.out.println("CANCELLED!");
    	}
    	else {
    		System.out.println("COMPLETE! " + (degrees - Robot.rotate.getGyro()) + " < " + Constants.ANGLE_THRESHOLD);
    	}
    	Robot.drive.stopDriveMotors();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
