package org.usfirst.frc.team5026.robot.subsystems;

import org.usfirst.frc.team5026.robot.Robot;

import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class Shifter extends Subsystem {
    
	boolean isHigh;
	
	public Shifter(boolean highShift) {
		setShift(highShift);
	}
	
    public void initDefaultCommand() {}
    
    public void setShift(boolean highShift) {
    	if(highShift) {
    		Robot.hardware.shifterSolenoid.set(Value.kForward);
    		isHigh = true;
    		SmartDashboard.putString("Gear", "Low");
    	}
    	
    	else {
    		Robot.hardware.shifterSolenoid.set(Value.kReverse);
    		isHigh = false;
    		SmartDashboard.putString("Gear", "High");
    	}
    }
    
    public void toggleShift() {
    	setShift(!isHigh);
    }
}

