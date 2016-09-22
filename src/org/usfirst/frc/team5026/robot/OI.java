package org.usfirst.frc.team5026.robot;

import org.usfirst.frc.team5026.lib.AxisButton;
import org.usfirst.frc.team5026.lib.PantherGamepad;
import org.usfirst.frc.team5026.lib.PantherJoystick;
import org.usfirst.frc.team5026.robot.commands.AllFailSafe;
import org.usfirst.frc.team5026.robot.commands.DriveStraightWithGyro;
import org.usfirst.frc.team5026.robot.commands.DriveTurnDegrees;
import org.usfirst.frc.team5026.robot.commands.GearToggle;
import org.usfirst.frc.team5026.robot.commands.IntakeArmLower;
import org.usfirst.frc.team5026.robot.commands.IntakeArmToggle;
import org.usfirst.frc.team5026.robot.commands.IntakeRollerSpinOut;
import org.usfirst.frc.team5026.robot.commands.RoutineAirplane;
import org.usfirst.frc.team5026.robot.commands.RoutineAutoAlign;
import org.usfirst.frc.team5026.robot.commands.RoutineAutoAlignNoDistance;
import org.usfirst.frc.team5026.robot.commands.RoutineIntakeBall;
import org.usfirst.frc.team5026.robot.commands.RoutineShootWithJoystick;
import org.usfirst.frc.team5026.robot.commands.ShooterPistonsRaise;
import org.usfirst.frc.team5026.robot.commands.StageTwoOuttake;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */

public class OI {
	
	public PantherGamepad driveGamepad;
	
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
	
	public PantherJoystick driveJoystick;
	
	public Button driveButton6;
	public Button driveButton7;
	public Button driveButton10;
	public Button driveButton11;
	
	private PantherJoystick turnJoystick; // FOR TESTING PURPOSES
	
	private Joystick buttonBoard;
	
	public Button boardButton1;
	public Button boardButton2;
	public Button boardButton3;
	public Button boardButton4;
	public Button boardButton5;
	public Button boardButton6;
	public Button boardButton7;
	public Button boardButton8;
	public Button boardButton9;
	public Button boardSwitch10;
	public Button boardSwitch11;
	public Button boardSwitch12;
	public Button boardSwitch13;
	
	public OI() {
		SmartDashboard.putString("INITS", "NONE");
		//driveGamepad = new PantherGamepad(RobotMap.DRIVE_JOYSTICK, Constants.DRIVE_JOYSTICK_X_DEADZONE, Constants.DRIVE_JOYSTICK_Y_DEADZONE, 
		//		Constants.DRIVE_MOTORS_DEAD_ZONE, Constants.DRIVE_JOYSTICK_X_SCALING, Constants.DRIVE_JOYSTICK_Y_SCALING);
		driveJoystick = new PantherJoystick(RobotMap.DRIVE_JOYSTICK, Constants.DRIVE_JOYSTICK_X_DEADZONE, Constants.DRIVE_JOYSTICK_Y_DEADZONE, 
						Constants.DRIVE_MOTORS_DEAD_ZONE, Constants.DRIVE_JOYSTICK_X_SCALING, Constants.DRIVE_JOYSTICK_Y_SCALING);
		buttonBoard = new Joystick(RobotMap.BUTTON_BOARD);
		turnJoystick = new PantherJoystick(3, Constants.DRIVE_JOYSTICK_X_DEADZONE, Constants.DRIVE_JOYSTICK_Y_DEADZONE, 
 -				Constants.DRIVE_MOTORS_DEAD_ZONE, Constants.DRIVE_JOYSTICK_X_SCALING, Constants.DRIVE_JOYSTICK_Y_SCALING);
		initButtons();
	}
	
	public PantherJoystick getDriveJoystick() {
		return driveJoystick;
	}
	/*
	public PantherGamepad getDriveJoystick() {
		return driveGamepad;
	} */
	
	// For testing purposes
	public PantherJoystick getTurnJoystick() {
		return turnJoystick;
	}
	public Joystick getButtonBoard() {
		return buttonBoard;
	}
	
	public void initButtons() {
		initDriveJoystick();
		initButtonBoard();
		//initGamepadButtons();
	}
	
	// Construct and add commands to buttons
	private void initDriveJoystick() {
		driveButton6 = new JoystickButton(driveJoystick, RobotMap.DRIVE_BUTTON_6);
		driveButton7 = new JoystickButton(driveJoystick, RobotMap.DRIVE_BUTTON_7);
		driveButton10 = new JoystickButton(driveJoystick, RobotMap.DRIVE_BUTTON_10);
		driveButton11 = new JoystickButton(driveJoystick, RobotMap.DRIVE_BUTTON_11);
	}
	
 	private void initButtonBoard() {
		boardButton1 = new JoystickButton(buttonBoard, RobotMap.BOARD_BUTTON_1);
		boardButton2 = new JoystickButton(buttonBoard, RobotMap.BOARD_BUTTON_2);
		boardButton3 = new JoystickButton(buttonBoard, RobotMap.BOARD_BUTTON_3);
		boardButton4 = new JoystickButton(buttonBoard, RobotMap.BOARD_BUTTON_4);
		boardButton5 = new JoystickButton(buttonBoard, RobotMap.BOARD_BUTTON_5);
		boardButton6 = new JoystickButton(buttonBoard, RobotMap.BOARD_BUTTON_6);
		boardButton7 = new JoystickButton(buttonBoard, RobotMap.BOARD_BUTTON_7);
		boardButton8 = new JoystickButton(buttonBoard, RobotMap.BOARD_BUTTON_8);
		boardButton9 = new JoystickButton(buttonBoard, RobotMap.BOARD_BUTTON_9);
		boardSwitch10 = new JoystickButton(buttonBoard, RobotMap.BOARD_SWITCH_10);
		boardSwitch11 = new JoystickButton(buttonBoard, RobotMap.BOARD_SWITCH_11);
		boardSwitch12 = new JoystickButton(buttonBoard, RobotMap.BOARD_SWITCH_12);
		boardSwitch13 = new JoystickButton(buttonBoard, RobotMap.BOARD_SWITCH_13);
		SmartDashboard.putString("INITS", "Board Done");
 	}
	
	//initialize gamepad buttons
	/*
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
	*/

	public void mapButtonsToCommands() { 
		// Button Board
		boardButton1.whenPressed(new RoutineShootWithJoystick());
		boardButton2.whenPressed(new RoutineAutoAlignNoDistance());
		//boardButton2.whenPressed(new DriveRotateThetaWithGyro(5));
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
	}
	public void gamepadMapButtonsToCommands() {
		rightTrigButton.whenPressed(new RoutineShootWithJoystick()); //right trigger
		//boardButton2.whenPressed(new RoutineBatterShot());
		//boardButton2.whenPressed(new DriveTurnDegrees(90));
		//boardButton2.whenPressed(new DriveRotateThetaWithGyro(5));
		rightBumper.whenPressed(new RoutineAutoAlign()); // right bumper
		leftBumper.whenPressed(new AllFailSafe()); //Add FailSafe left bumper
		leftTrigButton.whenPressed(new RoutineIntakeBall()); // left trigger
		aButton.whileHeld(new IntakeRollerSpinOut()); // a
		xButton.whileHeld(new StageTwoOuttake()); // x
		startButton.whenPressed(new RoutineAirplane());
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

