package org.usfirst.frc.team5026.robot.subsystems;

import org.usfirst.frc.team5026.robot.Robot;

import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class IntakeMotors extends Subsystem {
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

	private Talon rollerMotor;
	
	public IntakeMotors() {
		rollerMotor = Robot.hardware.intakeMotor;
	}
	
	public void intakeBall() {
		rollerMotor.set(-1);
	}
	
	public void outtakeBall() {
		rollerMotor.set(0.9);
	}
	
	public void stopIntake() {
		rollerMotor.set(0.0);
	}
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

