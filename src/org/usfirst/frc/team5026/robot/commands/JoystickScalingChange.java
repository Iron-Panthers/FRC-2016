package org.usfirst.frc.team5026.robot.commands;

import org.usfirst.frc.team5026.robot.Constants;
import org.usfirst.frc.team5026.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class JoystickScalingChange extends Command {

    public JoystickScalingChange() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.oi.getDriveJoystick().scalingX = 0.5;
    	Robot.oi.getDriveJoystick().scalingY = 0.7;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return !Robot.oi.boardSwitch11.get();
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.oi.getDriveJoystick().scalingX = Constants.DRIVE_JOYSTICK_X_SCALING;
    	Robot.oi.getDriveJoystick().scalingY = Constants.DRIVE_JOYSTICK_Y_SCALING;
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
