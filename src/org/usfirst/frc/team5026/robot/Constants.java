package org.usfirst.frc.team5026.robot;

/**
 * Contains all the numbers that allow for robot control.
 * Examples are PID constants and encoder information.
 */

public class Constants {
	
	// Joystick
	public static final double DRIVE_JOYSTICK_X_DEADZONE = 0.2;
	public static final double DRIVE_JOYSTICK_Y_DEADZONE = 0.2;
	public static final double DRIVE_JOYSTICK_X_SCALING = 0.6; //Need to fix at comp.
	public static final double DRIVE_JOYSTICK_Y_SCALING = 1; //Need to fix at comp.

	// Motors
	public static final double DRIVE_MOTORS_DEAD_ZONE = 0.35; // Value required to overcome static friction
	
	// Encoder
	public static final int WHEEL_RADIUS = 6; // Inches
	public static final double WHEEL_CIRCUMFERENCE = 37.70; // Inches
	public static final int ENCODER_CPR = 256; // Counts per revolution
	public static final double ENCODER_COUNTS_PER_INCH = 6.79;
	public static final int LEFT_DRIVE_ENCODER1 = 0;
	public static final int LEFT_DRIVE_ENCODER2 = 1;
	
	public static final int RIGHT_DRIVE_ENCODER1 = 2;
	public static final int RIGHT_DRIVE_ENCODER2 = 3;
	
	// Shooter PID (All values obtained through magic and RTFM)
	public static final double DRIVE_BASE_P = 0.5;
	public static final double DRIVE_BASE_I = 0;
	public static final double DRIVE_BASE_D = 0;
	public static final double DRIVE_BASE_F = 0.478;
	
	public static final double UPPER_SHOOTER_P = 0.448973;
	public static final double UPPER_SHOOTER_I = 0.000898;
	public static final double UPPER_SHOOTER_D = 56.121601;
	public static final double UPPER_SHOOTER_F = 0.02;
	
	public static final double LOWER_SHOOTER_P = 0.093536;
	public static final double LOWER_SHOOTER_I = 0.000187;
	public static final double LOWER_SHOOTER_D = 0;
	public static final double LOWER_SHOOTER_F = 0.0107;
	
	public static final double SHOOTER_RAMP_RATE = 36;
	public static final int SHOOTER_MAX_RPM = 14000;
	public static final double SHOOTER_TOLERANCE = 0.015;
	public static final int SHOOTER_PROFILE = 0;
	
	// Tested as of 10/4
	public static final int UPPER_SHOOTER_RPM_12 = -4000; //Tested V
	public static final int LOWER_SHOOTER_RPM_12 = -2900;
	public static final int UPPER_SHOOTER_RPM_9 = -4200;
	public static final int LOWER_SHOOTER_RPM_9 = -3000;
	public static final int UPPER_SHOOTER_RPM_6 = -5600;
	public static final int LOWER_SHOOTER_RPM_6 = -4000;
	
	// Vision values
	public static final int Y_THRESHOLD_LOW = -14; //Need to fix this at comp.
	public static final int Y_THRESHOLD_HIGH = 18; //Need to fix this at comp.
	
	// 171 px, 259 px
	// 148 px: 94 in to camera
	// 259 px: 149 in to camera
	// Maker Fair Shot
	public static final int X_NOMINAL_6_FT = 327; //Need to fix this at comp. FIX THESE
	public static final int Y_NOMINAL_6_FT = 60; //Need to fix this at comp. FIX THESE
	// Far Shot
	public static final int X_NOMINAL_12_FT = 333;
	public static final int Y_NOMINAL_12_FT = 239;
	// Medium Shot (Most likely)
	public static final int X_NOMINAL_9_FT = 323;
	public static final int Y_NOMINAL_9_FT = 143;
	
	public static final int Y_PIXELS_PER_SECOND = 110; //Need to fix this at comp. FIX THESE
	// 110
	public static final double NOMINAL_FEET = 8; //Need to fix this at comp.
	
	public static final int X_MAX_DIFFERENCE = 180; // At 30 degrees
	public static final double X_MAX_ANGLE = 30;
	public static final double ANGLE_THRESHOLD = 1.5; //Need to fix this at comp.
	// Rotate J-ankly
	public static final double SECONDS_PER_ANGLE = 0.015; //Need to fix this at comp.
	
	// Drive Straight (perfectly) Values
	public static final double ANGLE_PER_FULL_SPEED_COMPENSATION = 45*2;
}
