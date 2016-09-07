package org.usfirst.frc.team5026.robot.subsystems;

import org.usfirst.frc.team5026.robot.Robot;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Defines the banner sensor and motor for stage two
 */

public class StageTwo extends Subsystem {
	
	private DigitalInput bannerSensor;
	private Talon stageTwoMotor;
	
	public StageTwo() {
		bannerSensor = Robot.hardware.stageTwoBannerSensor;
		stageTwoMotor = Robot.hardware.stageTwoMotor;
	}
	
	public void intakeBall() {
		stageTwoMotor.set(-0.6);
	}
	public void intakeShooter() {
		stageTwoMotor.set(-1);
	}
	
	public void outtakeBall() {
		stageTwoMotor.set(0.8);
	}
	
	public void stopMotors() {
		stageTwoMotor.set(0.0);
	}
	
	public boolean hasBall() {
		return !bannerSensor.get();
	}
	
    public void initDefaultCommand() {
    }
}

