package org.usfirst.frc.team5026.robot;

import org.usfirst.frc.team5026.lib.AxisButton;
import org.usfirst.frc.team5026.lib.PantherGamepad;
import org.usfirst.frc.team5026.robot.commands.AllFailSafe;
import org.usfirst.frc.team5026.robot.commands.DriveStraightWithGyro;
import org.usfirst.frc.team5026.robot.commands.GearToggle;
import org.usfirst.frc.team5026.robot.commands.IntakeArmToggle;
import org.usfirst.frc.team5026.robot.commands.IntakeRollerSpinOut;
import org.usfirst.frc.team5026.robot.commands.RoutineAutoAlign;
import org.usfirst.frc.team5026.robot.commands.RoutineIntakeBall;
import org.usfirst.frc.team5026.robot.commands.RoutineShootWithJoystick;
import org.usfirst.frc.team5026.robot.commands.StageTwoOuttake;

import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */

public class OI {
	
	public PantherGamepad driveJoystick;
	
	public JoystickButton xButton;
	public JoystickButton yButton;
	public JoystickButton bButton;
	public JoystickButton aButton;
	public JoystickButton leftBumper;
	public JoystickButton rightBumper;
	public JoystickButton backButton;
	public JoystickButton startButton;
	public JoystickButton leftTrigButton;
	public JoystickButton rightTrigButton;
	public AxisButton leftDPadButton;
	public AxisButton rightDPadButton;
	
	public OI() {
		SmartDashboard.putString("INITS", "NONE");
		driveJoystick = new PantherGamepad(RobotMap.DRIVE_JOYSTICK, Constants.DRIVE_JOYSTICK_X_DEADZONE, Constants.DRIVE_JOYSTICK_Y_DEADZONE, 
				Constants.DRIVE_MOTORS_DEAD_ZONE, Constants.DRIVE_JOYSTICK_X_SCALING, Constants.DRIVE_JOYSTICK_Y_SCALING);
		
		initButtons();
	}
	
	public PantherGamepad getDriveJoystick() {
		return driveJoystick;
	}
	
	public void initButtons() {
		initGamepadButtons();
	}
	
	//initialize gamepad buttons
	private void initGamepadButtons(){
		System.out.println("initing buttons");
		
		xButton = driveJoystick.getButtonX();
		System.out.println(driveJoystick);
		
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
		rightTrigButton.whenPressed(new RoutineShootWithJoystick()); //right trigger
		//boardButton2.whenPressed(new RoutineBatterShot());
		//boardButton2.whenPressed(new DriveTurnDegrees(90));
		//boardButton2.whenPressed(new DriveRotateThetaWithGyro(5));
		rightBumper.whenPressed(new RoutineAutoAlign()); // right bumper
		leftBumper.whenPressed(new AllFailSafe()); //Add FailSafe left bumper
		leftTrigButton.whenPressed(new RoutineIntakeBall()); // left trigger
		aButton.whileHeld(new IntakeRollerSpinOut()); // a
		xButton.whileHeld(new StageTwoOuttake()); // x
		backButton.whenPressed(new DriveStraightWithGyro(0.8)); //0.8 seconds
		//leftDPadButton.whenPressed(new DriveTurnDegrees(-5)); //left d pad
		//rightDPadButton.whenPressed(new DriveTurnDegrees(5)); //right d pad
		bButton.whenPressed(new IntakeArmToggle()); //b chang to toggle (whenPressed)
		yButton.whenPressed(new GearToggle());
		//boardSwitch11.whileHeld(new ShooterPistonsRaise());
		//boardSwitch12.whenPressed(new AIRPLANE()); start (pressed with back)
		//boardSwitch13.whenPressed(new AIRPLANE()); back (pressed with start)
		// Driver Joysticks
		// ADD BUTTONS FOR SHOOTER RPMS */
	}
}

