package org.usfirst.frc.team5026.robot.subsystems;

import org.usfirst.frc.team5026.robot.Robot;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.command.PIDSubsystem;

public class PIDDrive extends PIDSubsystem {
	
	public static final double targetSpeed = 0.2; 
	
	public static final double posFactor = (1-targetSpeed);
	public static final double negFactor = (1+targetSpeed);
	
	private Encoder leftEnc = Robot.hardware.leftEncoder;
	private Encoder rightEnc = Robot.hardware.rightEncoder;
	
	private Drive drive;

	public PIDDrive(String name, double p, double i, double d, double f, Drive _drive) {
		super(name, p, i, d, f);
		
		drive = _drive;
	}

	@Override
	protected double returnPIDInput() {
		System.out.println(leftEnc.getDistance() + rightEnc.getDistance());
		return (leftEnc.getDistance() + rightEnc.getDistance())/1000;
	}

	@Override
	protected void usePIDOutput(double output) {
		//System.out.println(speedFromPID(output));
		//System.out.println(speedFromPID(-output));
		
		//drive.setLeftRightMotors(speedFromPID(output), speedFromPID(-output));
		drive.setLeftRightMotors(targetSpeed, targetSpeed);
	}
	
	private double speedFromPID(double output){
		if (output < 0){
			return output*negFactor+targetSpeed;
		} else {
			return output*posFactor+targetSpeed;
		}
	}

	@Override
	protected void initDefaultCommand() {

	}

}
