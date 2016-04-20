package org.usfirst.frc.team5026.robot.commands;

import org.usfirst.frc.team5026.lib.PantherGamepad;
import org.usfirst.frc.team5026.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class TankDriveWithGamepad extends Command {

	private PantherGamepad gamepad;

    public TankDriveWithGamepad(PantherGamepad gamepad) {
    	requires(Robot.drive);
    	this.gamepad = gamepad;
    }

    protected void initialize() {
    	Robot.drive.stopDriveMotors();
    }

    protected void execute() {
    	Robot.drive.setLeftRightMotors(gamepad.getLeftYAxis(), gamepad.getRightYAxis());
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    	Robot.drive.stopDriveMotors();
    }

    protected void interrupted() {
    	end();
    }
}
