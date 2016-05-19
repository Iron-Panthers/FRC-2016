package org.usfirst.frc.team5026.robot.subsystems;

import org.usfirst.frc.team5026.lib.ShooterMotorGroup;
import org.usfirst.frc.team5026.robot.Robot;
import org.usfirst.frc.team5026.robot.commands.ShooterPistonsLower;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.CANTalon.TalonControlMode;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Defines the shooter PID and pistons to angle shooter position
 */

public class Shooter extends Subsystem {
    
	
	public ShooterMotorGroup lowerShooterGroup;
	public ShooterMotorGroup upperShooterGroup;
	
	public int[] upperLookup;
	public int[] lowerLookup;
	public NetworkTable table;
	
	public static int upperRPM; // Max: 6500
	public static int lowerRPM; // Max: 8100
	public double distance = 30; // In feet
	public boolean upperEncoder = false;
	public boolean lowerEncoder = false;
	
	public boolean running = false;
	public double[] def = new double[0];
	public double[] areas;
	private double lowerMotorOutput;
	private double upperMotorOutput;
	
	private String sb = "";
	
	public Shooter(int[] lookupListUpper, int[] lookupListLower) {
		lowerShooterGroup = Robot.hardware.lowerShooterGroup;
		upperShooterGroup = Robot.hardware.upperShooterGroup;
		
		upperLookup = lookupListUpper;
		lowerLookup = lookupListLower;
		
		Robot.hardware.shooterLight.set(0);
		
		try {
			double z = upperShooterGroup.motor1.getClosedLoopError();
			upperEncoder = true;
		} catch (InstantiationError e) {
			upperEncoder = false; // No upper motor encoder
		}
		
		try {
			double x = lowerShooterGroup.motor1.getClosedLoopError();
			lowerEncoder = true;
		} catch (InstantiationError e) {
			lowerEncoder = false;
		}
	}
	
	// Has to be repeatedly called
	public void update() {
		SmartDashboard.putBoolean("UPPER ENCODER", upperEncoder);
		SmartDashboard.putBoolean("LOWER ENCODER", lowerEncoder);
		if(running) {
			PID();
		}
		else {
			stop();
		}
	}
	
	// For testing purposes
	public void shootBall() {
		lowerShooterGroup.set(lowerRPM);
		upperShooterGroup.set(upperRPM);
		System.out.println("shootBall() called");
	}
	
	public void stop() {
		lowerShooterGroup.motor1.set(0);
		upperShooterGroup.motor1.set(0);
	}
	
	public void distance(double distanceToTargetFeet) {
		distance = distanceToTargetFeet;
		if(distance > upperLookup.length) {
			distance = upperLookup.length;
		}
		else if(distance <= 0) {
			distance = 0;
		}
		upperRPM = upperLookup[(int) Math.round(distance) - 1];
		lowerRPM = lowerLookup[(int) Math.round(distance) -1];
		PID();	
	}
	
	public void rpms(int rpmUpper, int rpmLower) {
		upperRPM = rpmUpper;
		lowerRPM = rpmLower;
		PID();
	}
	
	private void PID() {
		upperShooterGroup.motor1.enable();
		lowerShooterGroup.motor1.enable();
		if(upperEncoder && lowerEncoder) {
			upperPid();
			lowerPid();
		}
		else if(upperEncoder && !lowerEncoder) {
			upperPid();
		}
		else if(lowerEncoder && !upperEncoder) {
			lowerPid();
		}
		else {
			// No encoders found
			System.out.println("NO ENCODERS DETECTED!");
		}
	}
	
	private void upperPid() {
		upperMotorOutput = upperShooterGroup.motor1.getOutputVoltage() / upperShooterGroup.motor1.getBusVoltage();
		upperShooterGroup.motor1.set(upperRPM);
		prints(true);
	}
	
	private void lowerPid() {
		// FAILSAFE
		/*
		if (lowerShooterGroup.motor1.getSpeed() == 0) {
			lowerMotorOutput = lowerShooterGroup.motor1.getOutputCurrent() / lowerShooterGroup.motor1.getBusVoltage();
			lowerShooterGroup.motor1.changeControlMode(TalonControlMode.PercentVbus);
			lowerShooterGroup.motor1.set(0.75);
		} else {
			lowerMotorOutput = lowerShooterGroup.motor1.getOutputVoltage() / lowerShooterGroup.motor1.getBusVoltage();
			lowerShooterGroup.motor1.changeControlMode(TalonControlMode.Speed);
			lowerShooterGroup.motor1.set(lowerRPM);
			prints(false);
		}
		*/
		lowerMotorOutput = lowerShooterGroup.motor1.getOutputVoltage() / lowerShooterGroup.motor1.getBusVoltage();
		lowerShooterGroup.motor1.changeControlMode(TalonControlMode.Speed);
		lowerShooterGroup.motor1.set(lowerRPM);
		prints(false);
		
	}
	public void slowStop() {
		upperShooterGroup.motor1.disable();
		lowerShooterGroup.motor1.disable();
		SmartDashboard.putString("Stable shooter?", "NO");
		Robot.hardware.shooterLight.set(0);
	}
	
	public void prints(boolean upper) {
		if (upper) {
    		upperMotorOutput = upperShooterGroup.motor1.getOutputVoltage() / upperShooterGroup.motor1.getBusVoltage();
    		sb = "";
    		sb += "UpperMotorOut ";
        	sb += upperMotorOutput;
        	sb += " UpperSpeed ";
        	sb += upperShooterGroup.motor1.getSpeed();// / 1440 * 600; //Ticks to RPM
        	sb += " UpperClosedLoopError ";
    		sb += upperShooterGroup.motor1.getClosedLoopError();
    		sb += " UpperTargetSpeed ";
    		sb += upperRPM;
    		System.out.println(sb);
    	} else {
    		lowerMotorOutput = lowerShooterGroup.motor1.getOutputVoltage() / lowerShooterGroup.motor1.getBusVoltage();
    		sb = "";
    		sb += "LowerMotorOut ";
        	sb += lowerMotorOutput;
        	sb += " LowerSpeed ";
        	sb += lowerShooterGroup.motor1.getSpeed();// / 1440 * 600; //Ticks to RPM
        	sb += " LowerClosedLoopError ";
    		sb += lowerShooterGroup.motor1.getClosedLoopError();
    		sb += " LowerTargetSpeed ";
    		sb += lowerRPM;
    		System.out.println(sb);
    	}
	}
	
	

    public void initDefaultCommand() {
    	
    }
}

