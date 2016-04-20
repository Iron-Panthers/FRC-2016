package org.usfirst.frc.team5026.robot;

import org.usfirst.frc.team5026.lib.AxisButton;
import org.usfirst.frc.team5026.lib.PantherGamepad;
import org.usfirst.frc.team5026.robot.commands.AllFailSafe;
import org.usfirst.frc.team5026.robot.commands.DriveRotateThetaWithGyro;
import org.usfirst.frc.team5026.robot.commands.DriveTurnDegrees;
import org.usfirst.frc.team5026.robot.commands.IntakeArmLower;
import org.usfirst.frc.team5026.robot.commands.IntakeRollerSpinOut;
import org.usfirst.frc.team5026.robot.commands.RoutineAutoAlign;
import org.usfirst.frc.team5026.robot.commands.RoutineIntakeBall;
import org.usfirst.frc.team5026.robot.commands.RoutineShootWithJoystick;
import org.usfirst.frc.team5026.robot.commands.ShooterPistonsRaise;
import org.usfirst.frc.team5026.robot.commands.StageTwoOuttake;

import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */

public class OI {
	
	private PantherGamepad driveJoystick;
	
	public JoystickButton xButton;
	public JoystickButton yButton;
	public JoystickButton bButton;
	public JoystickButton aButton;
	public JoystickButton leftBumper;
	public JoystickButton rightBumper;
	public JoystickButton backButton;
	public JoystickButton startButton;
	public AxisButton leftTrigButton;
	public AxisButton rightTrigButton;
	
	public OI() {
		SmartDashboard.putString("INITS", "NONE");
		driveJoystick = new PantherGamepad(RobotMap.DRIVE_JOYSTICK, Constants.DRIVE_JOYSTICK_X_DEADZONE, Constants.DRIVE_JOYSTICK_Y_DEADZONE, 
				Constants.DRIVE_MOTORS_DEAD_ZONE, Constants.DRIVE_JOYSTICK_X_SCALING, Constants.DRIVE_JOYSTICK_Y_SCALING);
	}
	
	public PantherGamepad getDriveJoystick() {
		return driveJoystick;
	}
	
	public void initButtons() {
		initGamepadButtons();
	}
	
	//initialize gamepad buttons
	private void initGamepadButtons(){
		xButton = driveJoystick.getButtonX();
		yButton = driveJoystick.getButtonY();
		bButton = driveJoystick.getButtonB();
		aButton = driveJoystick.getButtonA();
		leftBumper = driveJoystick.getLeftShoulder();
		rightBumper = driveJoystick.getRightShoulder();
		backButton = driveJoystick.getBackButton();
		startButton = driveJoystick.getStartButton();
		
		leftTrigButton = driveJoystick.getLeftTrig();
		rightTrigButton = driveJoystick.getRightTrig();
	}
	
	public void mapButtonsToCommands() {
		// Button Board
		boardButton1.whenPressed(new RoutineShootWithJoystick());
		//boardButton2.whenPressed(new RoutineBatterShot());
		//boardButton2.whenPressed(new DriveTurnDegrees(90));
		boardButton2.whenPressed(new DriveRotateThetaWithGyro(5));
		boardButton3.whenPressed(new RoutineAutoAlign());
		boardButton4.whenPressed(new AllFailSafe()); //Add FailSafe
		boardButton5.whenPressed(new RoutineIntakeBall());
		boardButton6.whileHeld(new IntakeRollerSpinOut());
		boardButton7.whileHeld(new StageTwoOuttake());
		boardButton8.whenPressed(new DriveTurnDegrees(-5));
		boardButton9.whenPressed(new DriveTurnDegrees(5));
		boardSwitch10.whileHeld(new IntakeArmLower());
		boardSwitch11.whileHeld(new ShooterPistonsRaise());
		//boardSwitch12.whenPressed(new AIRPLANE());
		//boardSwitch13.whenPressed(new AIRPLANE());
		// Driver Joysticks
		// ADD BUTTONS FOR SHOOTER RPMS
	}
}

