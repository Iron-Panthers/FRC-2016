package org.usfirst.frc.team5026.robot.commands;

import org.usfirst.frc.team5026.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Pulses stage two motors back so that shooter wheels do not touch the ball
 * Please take a second to admire line 27 in all of its beauty
 */
public class StageTwoPulseBack extends Command {
	
	private double seconds = 3; // Change for new stage 2

    public StageTwoPulseBack() {
    	requires(Robot.stageTwo);
    }

    protected void initialize() {
    }

    protected void execute() {
    	Robot.stageTwo.pulseBack();
    	System.out.println("Pulse Back!");
    }

    protected boolean isFinished() {
        return !Robot.stageTwo.hasBall();
    }

    protected void end() {
    	Robot.stageTwo.stopMotors();
    }

    protected void interrupted() {
    	end();
    }
}
