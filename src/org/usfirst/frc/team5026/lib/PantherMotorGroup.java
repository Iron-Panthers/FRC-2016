package org.usfirst.frc.team5026.lib;


import org.usfirst.frc.team5026.robot.Robot;

import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.SpeedController;

/**
 * Infinitely expandable motor group 'cause why not?
 */

public class PantherMotorGroup implements SpeedController {

	private SpeedController[] motorGroup;
	private boolean[] isReversed;
	private PowerDistributionPanel pdp = Robot.hardware.powerDistributionPanel;
	private int[] pdpSlot;
	
	public PantherMotorGroup(SpeedController[] motors) {
		motorGroup = motors;
	}

	public PantherMotorGroup(boolean isGroupInverted, SpeedController[] motors, int[] pdpPort) {
		setInverted(isGroupInverted);
		motorGroup = motors;
		pdpSlot = pdpPort;
	}

	public PantherMotorGroup(boolean[] isMotorInverted, SpeedController[] motors, int[] pdpPort) {
		isReversed = isMotorInverted;
		motorGroup = motors;
		pdpSlot = pdpPort;
	}

	@Override
	public void pidWrite(double output) { // Need to fix ternary operator
			for(int i = 0; i < motorGroup.length; i++) {
			if(isReversed[i]) {
				motorGroup[i].pidWrite(-output);
			}
			else if(!isReversed[i]) {
				motorGroup[i].pidWrite(output);
			}
		}
	}

	@Override
	public double get() {
		return motorGroup[0].get();
	}

	public double get(int index) {
		return motorGroup[index].get();
	}

	@Override
	public void set(double speed, byte syncGroup) {
		for(int i = 0; i < motorGroup.length; i++) {
			if(isReversed[i]) {
				motorGroup[i].set(-speed, syncGroup);
			}
			else if(!isReversed[i]) {
				motorGroup[i].set(speed, syncGroup);
			}
		}
	}

	@Override
	public void set(double speed) {
		for(int i = 0; i < motorGroup.length; i++) {
			if(isReversed[i]) {
				motorGroup[i].set(-speed);
			}
			else if(!isReversed[i]) {
				motorGroup[i].set(speed);
			}
		}
	}

	@Override
	public void setInverted(boolean isInverted) {
		for(int i = 0; i < isReversed.length; i++) {
			isReversed[i] = isInverted;
		}
	}

	public void setInverted(boolean isInverted, int index) {
		isReversed[index] = isInverted;
	}

	@Override
	public boolean getInverted() {
		return isReversed[0];
	}

	public boolean getInverted(int index) {
		return isReversed[index];
	}

	@Override
	public void disable() {
		for(SpeedController speedcontroller : motorGroup) {
			speedcontroller.disable();
		}
	}

	@Override
	public void stopMotor() {
		for(SpeedController speedcontroller : motorGroup) {
			speedcontroller.stopMotor();
		}
	}
	
	public double getCurrent(int pdpSlot) {
		return pdp.getCurrent(0);
	}
	
	public double getTotalCurrent() {
		return pdp.getTotalCurrent();
	}
}