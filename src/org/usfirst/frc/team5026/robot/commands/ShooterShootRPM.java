package org.usfirst.frc.team5026.robot.commands;

import org.usfirst.frc.team5026.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Starts the shooter PID
 */

public class ShooterShootRPM extends Command {
	
	private int upperRPM;
	private int lowerRPM;
	
    public ShooterShootRPM(int upperRPM, int lowerRPM) {
    	requires(Robot.shooter);
    	this.upperRPM = upperRPM;
    	this.lowerRPM = lowerRPM;
    }
    public ShooterShootRPM() {
    	requires(Robot.shooter);
    	this.upperRPM = 0;
    	this.lowerRPM = 0;
    }

    protected void initialize() {
    	if (upperRPM == 0 && lowerRPM == 0) {
    		upperRPM = Robot.rpmUpperShooter;
    		lowerRPM = Robot.rpmLowerShooter;
    	}
    }

    protected void execute() {
    	Robot.shooter.rpms(upperRPM, lowerRPM);
    }

    protected boolean isFinished() {
        return true;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}
