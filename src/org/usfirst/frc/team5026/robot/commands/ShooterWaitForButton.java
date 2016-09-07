package org.usfirst.frc.team5026.robot.commands;

import org.usfirst.frc.team5026.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;

/**
 * Waits for the button in the constructor to be pressed
 */
public class ShooterWaitForButton extends Command {

	private int buttonForContinue = 1;
	private boolean finished = false;
	
    public ShooterWaitForButton(int buttonForContinue) {
        finished = false;
        this.buttonForContinue = buttonForContinue;
    }

    protected void initialize() {
    }

    protected void execute() {
    	if (Robot.oi.rightTrigButton.get()) {
    		finished = true;
    	} else {
    		finished = false;
    	}
    	
    	// Failsafe
    	if (Robot.oi.leftTrigButton.get()) {
    		Scheduler.getInstance().removeAll();
    		System.out.println("INTERRUPTED BUTTON");
    		finished = true;
    	}
    }

    protected boolean isFinished() {
        return finished;
    }

    protected void end() {
    	Robot.hardware.shooterLight.set(0);
    }

    protected void interrupted() { // Need to test
    	System.out.println("INTERRUPTED BUTTON WAIT");
    	Scheduler.getInstance().removeAll();
    	finished = true;
    	end();
    }
}
