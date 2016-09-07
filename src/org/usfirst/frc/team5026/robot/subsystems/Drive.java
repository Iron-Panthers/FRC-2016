package org.usfirst.frc.team5026.robot.subsystems;

import org.usfirst.frc.team5026.lib.PantherGamepad;
import org.usfirst.frc.team5026.robot.Hardware;
import org.usfirst.frc.team5026.robot.Robot;
import org.usfirst.frc.team5026.robot.commands.TankDriveWithGamepad;

import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Defines the drive base of the robot
 */

public class Drive extends Subsystem {
	
	private RobotDrive drive;
	private PantherGamepad joystick;
	private PantherGamepad turnJoystick; // TESTING PURPOSES
	private Hardware hardware;
	
	public Drive() {
		joystick = Robot.oi.getDriveJoystick();
		hardware = Robot.hardware;
		drive = new RobotDrive(hardware.leftDrive, hardware.rightDrive); // hardware.leftDrive, hardware.rightDrive new Talon(1), new Talon(0));
	}
	
	/**
	 * For driving with joystick
	 * @param joystick: Joystick to be passed in
	 */
	public void joystickControl(PantherGamepad joystick) {
		drive.arcadeDrive(joystick);
	}
	
	public void useArcadeDrive(double xAxis, double yAxis) {
		drive.arcadeDrive(xAxis, yAxis);
	}
    
	public void setLeftRightMotors(double leftMotors, double rightMotors) {
		drive.setLeftRightMotorOutputs(leftMotors, rightMotors);	
	}
	
	public void stopDriveMotors() {
		setLeftRightMotors(0, 0);
	}
	
	public void turnThrottleControl(double turn, double throttle)
	{
		double leftOutput;
		double rightOutput;
		
		if(turn > 0)
		{
			leftOutput = 100 * throttle;
			rightOutput = (((1 - Math.abs(turn)) * 200) - 100) * throttle;
		}
		else {
			rightOutput = 100 * throttle;
			leftOutput = (((1 - Math.abs(turn)) * 200) - 100) * throttle;
		}
		setLeftRightMotors(leftOutput, rightOutput);
		
	}

    public void initDefaultCommand() {
    	setDefaultCommand(new TankDriveWithGamepad(joystick));
    	//setDefaultCommand(new TurnThrottleJoystickControl(turnJoystick, joystick));
    }
}

