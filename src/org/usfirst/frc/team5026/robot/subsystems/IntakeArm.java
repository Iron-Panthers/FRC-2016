package org.usfirst.frc.team5026.robot.subsystems;

import org.usfirst.frc.team5026.robot.Robot;
import org.usfirst.frc.team5026.robot.commands.IntakeArmLower;
import org.usfirst.frc.team5026.robot.commands.IntakeArmRaise;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Defines the intake arm pistons and roller
 */

public class IntakeArm extends Subsystem {
    
	private DoubleSolenoid intakeArmSolenoid;
	
	public IntakeArm() {
		intakeArmSolenoid = Robot.hardware.intakeSolenoid;
		
	}
	
	public void extendIntakeArm() {
		intakeArmSolenoid.set(Value.kForward);
	}
	
	public void retractIntakeArm() {
		intakeArmSolenoid.set(Value.kReverse);
	}
	
	
    public void initDefaultCommand() {
    }
}

