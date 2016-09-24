package org.usfirst.frc.team5026.robot.autonomous;

import org.usfirst.frc.team5026.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 * When the robot is lazy and doesn't move during autonomous
 */

public class DoNothingAutonomous extends Command {

    public DoNothingAutonomous() {
    	requires(Robot.drive);
    	requires(Robot.stageTwo);
    	requires(Robot.shooter);
    	requires(Robot.intakeMotors);

    }

    protected void initialize() {
    }

    protected void execute() {
    }

    protected boolean isFinished() {
        return true;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}
