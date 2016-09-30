package org.usfirst.frc.team5026.robot.commands;

import org.usfirst.frc.team5026.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class AutoMultiballLowBarTest extends Command {

    public AutoMultiballLowBarTest() {
        requires(Robot.rotate);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.rotate.align();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	SmartDashboard.putNumber("Auto Delta Theta", Robot.rotate.offsetAngle);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	SmartDashboard.putNumber("Auto Delta Theta", Robot.rotate.offsetAngle);
    	System.out.println("Auto Delta Theta "+ Robot.rotate.offsetAngle);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
