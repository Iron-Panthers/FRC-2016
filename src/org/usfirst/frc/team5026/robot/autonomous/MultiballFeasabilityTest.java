package org.usfirst.frc.team5026.robot.autonomous;

import org.usfirst.frc.team5026.robot.Robot;
import org.usfirst.frc.team5026.robot.commands.AutoMultiballLowBarTest;
import org.usfirst.frc.team5026.robot.commands.DriveForwardsForTime;
import org.usfirst.frc.team5026.robot.commands.DriveRotateThetaWithGyro;
import org.usfirst.frc.team5026.robot.commands.IntakeArmLower;
import org.usfirst.frc.team5026.robot.commands.RoutineIntakeBall;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class MultiballFeasabilityTest extends CommandGroup {
    
    public  MultiballFeasabilityTest() {
        double offsetReturnAngle = 0;
    	addParallel(new AutoMultiballLowBarTest()); // Resets and calibrates Gyro, also tells us how much we are off by when we return
        addParallel(new RoutineIntakeBall()); // Grabs dat ball (Shouldn't be one here though)
        addParallel(new IntakeArmLower()); // Drops arm too, does all this in parallel ot save time
        addSequential(new DriveForwardsForTime(-0.5)); // Now we drive up to the line and grab it
        addSequential(new DriveForwardsForTime(3)); // Go over bar
        offsetReturnAngle = Robot.rotate.getGyro();
        addSequential(new DriveRotateThetaWithGyro(180 + offsetReturnAngle,0.4)); // Do a u turn (at 0.4 speed)
        addParallel(new AutoMultiballLowBarTest()); // Resets and calibrates gyro cause we used gyro for our turn
        addSequential(new DriveForwardsForTime(3)); // Back over the low bar
        offsetReturnAngle = Robot.rotate.getGyro();
        addSequential(new DriveRotateThetaWithGyro(offsetReturnAngle, 0.4));
        addParallel(new RoutineIntakeBall()); // Grabs the ball (hopefully) near that area
    }
}
