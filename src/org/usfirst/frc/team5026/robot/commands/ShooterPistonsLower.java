package org.usfirst.frc.team5026.robot.commands;

import org.usfirst.frc.team5026.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class ShooterPistonsLower extends Command {

    public ShooterPistonsLower() {
    	requires(Robot.shooterPistons);
    }

    protected void initialize() {
    }

    protected void execute() {
    	Robot.shooterPistons.lowerShooter();
    }

    protected boolean isFinished() {
        return true;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}
