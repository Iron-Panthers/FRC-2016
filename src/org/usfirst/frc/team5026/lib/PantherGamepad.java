package org.usfirst.frc.team5026.lib;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;


public class PantherGamepad extends Joystick {
	// Gamepad axis ports
	private static final int AXIS_LEFT_X = 0;
	private static final int AXIS_LEFT_Y = 1;
	private static final int AXIS_TRIGGER_LEFT = 2;
	private static final int AXIS_TRIGGER_RIGHT = 3;
	private static final int AXIS_RIGHT_X = 4;
	private static final int AXIS_RIGHT_Y = 5;
	private static final int AXIS_DPAD = 6;

	// Gamepad buttons
	private static final int BUTTON_A = 2;
	private static final int BUTTON_B = 3;
	private static final int BUTTON_X = 1;
	private static final int BUTTON_Y = 4;
	private static final int BUTTON_BUMBPER_LEFT = 5;
	private static final int BUTTON_BUMPER_RIGHT = 6;
	private static final int BUTTON_BACK = 7;
	private static final int BUTTON_START = 8;
	private static final int BUTTON_LEFT_STICK = 9;
	private static final int BUTTON_RIGHT_STICK = 10;
	
	public static final double LEFT_TRIG_THRESHOLD = 0.9;
	public static final double RIGHT_TRIG_THRESHOLD = 0.9;
	
	private AxisButton leftTrigButton;
	private AxisButton rightTrigButton;
	
	public int power;
	public double m_deadzoneX;
	public double m_deadzoneY;
	double motorDeadZone;
	double scalingX = 0.5; // slope of joystick curve
	double scalingY = 1; // slope of joystick curve
	
	public PantherGamepad(int port, double deadzoneX, double deadzoneY, double driveMotorsDeadZone, double driveJoystickXScaling, double driveJoystickYScaling) {
		super(port);
		m_deadzoneX = deadzoneX;
		m_deadzoneY = deadzoneY;
		scalingX = driveJoystickXScaling;
		scalingY = driveJoystickYScaling;
		motorDeadZone = driveMotorsDeadZone;
		power = 1;
		
		leftTrigButton = new AxisButton(this, AXIS_TRIGGER_LEFT, LEFT_TRIG_THRESHOLD);
		rightTrigButton = new AxisButton(this, AXIS_TRIGGER_RIGHT, RIGHT_TRIG_THRESHOLD);
	}
	
	public double getLeftY(){
		return getRawAxis(AXIS_LEFT_Y);
	}
	
	public double getRightY(){
		return getRawAxis(AXIS_RIGHT_Y);
	}

	public double getLeftYAxis() {
		double value;
		double negative = (Math.abs(getLeftY()) / getLeftY()); 
		double yAxis = getLeftY() - negative * m_deadzoneY;
		
		if(Math.abs(getLeftY()) > m_deadzoneY) {
			if (yAxis < 0) {
				value = -Math.pow(Math.abs(yAxis), power);
			}
			else {
				value = Math.pow(Math.abs(yAxis), power);
			}
			value = scalingY * value + negative * motorDeadZone;
		}
		else {
			value = 0;
		}
		
		return value;
	}
	
	public double getRightYAxis() {
		double value;
		double negative = (Math.abs(getRightY()) / getRightY());
		double yAxis = getRightY() - negative * m_deadzoneY;
		
		if(Math.abs(getRightY()) > m_deadzoneY) {
			if(yAxis < 0) {
				value = -Math.pow(Math.abs(yAxis), power);
			}
			else {
				value = Math.pow(Math.abs(yAxis), power);
			}
			value = scalingY * value + negative * motorDeadZone;
		}
		else {
			value = 0;
		}
		return value;
	}
	
	/**
	 * Returns an object of Button A.
	 */
	public JoystickButton getButtonA() {
	    return new JoystickButton(this, BUTTON_A);
	}

	/**
	* Returns an object of Button B.
	*/
	public JoystickButton getButtonB() {
	    return new JoystickButton(this, BUTTON_B);
	}

	/**
	 *  Returns an object of Button X.
	 */
	public JoystickButton getButtonX() {
	    return new JoystickButton(this, BUTTON_X);
	}

	/**
	 *  Returns an object of Button Y.
	 */
	public JoystickButton getButtonY() {
	    return new JoystickButton(this, BUTTON_Y);
	}

	/**
	 *  Return the DPad axis positions.
	 */
	public double getDPadX() {
	    return getRawAxis(AXIS_DPAD);
	}

	/**
	 * DPad Left and Right only
	 * WPILIB cannot access the vertical axis of the Logitech Game Controller Dpad
	 */

	public boolean getDPadLeft() {
	  double x = getDPadX();
	    return (x < -0.5);
	}

	public boolean getDPadRight() {
	  double x = getDPadX();
	    return (x > 0.5);
	}

	/**
	 * Gets the state of the Start button
	 * @  return the state of the Start button
	 */
	public JoystickButton getStartButton(){
	    return new JoystickButton(this, BUTTON_START);
	}

	public JoystickButton getBackButton() {
	    return new JoystickButton(this, BUTTON_BACK);
	}

	/**
	 * Gets the state of the left shoulder
	 * @  return the state of the left shoulder
	 */
	public JoystickButton getLeftShoulder() {
	    return new JoystickButton(this, BUTTON_BUMBPER_LEFT);
	}

	/**
	 * Gets the state of the right shoulder
	 * @  return the state of the right shoulder
	 */
	public JoystickButton getRightShoulder() {
	    return new JoystickButton(this, BUTTON_BUMPER_RIGHT);
	}

	public JoystickButton getLeftStickClick() {
	    return new JoystickButton(this, BUTTON_LEFT_STICK);
	}

	public JoystickButton getRightStickClick() {
	    return new JoystickButton(this, BUTTON_RIGHT_STICK);
	}

	public AxisButton getLeftTrig(){
		return leftTrigButton;
	}
	
	public AxisButton getRightTrig(){
		return rightTrigButton;
	}
}
