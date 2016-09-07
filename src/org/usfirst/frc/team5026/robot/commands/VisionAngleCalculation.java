package org.usfirst.frc.team5026.robot.commands;

import org.usfirst.frc.team5026.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class VisionAngleCalculation extends Command {

    public VisionAngleCalculation() {
    	requires(Robot.rotate);
    }

    protected void initialize() {
    	System.out.println("GYRO CALIBRATION START");
    	Robot.rotate.align();
    	System.out.println("GYRO CALIBRATED");
    }

    protected void execute() {
    	Robot.rotate.offsetFromContours();
    	SmartDashboard.putNumber("offsetAngle", Robot.rotate.offsetAngle);
    	SmartDashboard.putNumber("Current Angle", Robot.rotate.getGyro());
    }

    protected boolean isFinished() {
        return Robot.rotate.offsetAngle != 0 || Robot.oi.boardButton4.get();
    }     

    protected void end() {
    	System.out.println("VisionCalcFin");
    }

    protected void interrupted() { // add interrupted for buttons
    }
}

