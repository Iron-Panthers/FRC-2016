package org.usfirst.frc.team5026.robot.autonomous;

import org.usfirst.frc.team5026.robot.commands.DriveForwardsForTime;
import org.usfirst.frc.team5026.robot.commands.DriveRotateThetaWithGyro;
import org.usfirst.frc.team5026.robot.commands.IntakeArmLower;
import org.usfirst.frc.team5026.robot.commands.RoutineAutoAlignNoDistance;
import org.usfirst.frc.team5026.robot.commands.RoutineIntakeBall;
import org.usfirst.frc.team5026.robot.commands.ShooterShootRPM;
import org.usfirst.frc.team5026.robot.commands.ShooterSlowStop;
import org.usfirst.frc.team5026.robot.commands.ShooterWaitForStabilize;
import org.usfirst.frc.team5026.robot.commands.StageTwoPulseBack;
import org.usfirst.frc.team5026.robot.commands.StageTwoQueueToShooter;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class MultiballAutonomous extends CommandGroup {
    
    public  MultiballAutonomous() {
        /*
         * So the ball should be placed on the front end of the robot (by the shooter) so 
         * that as we get to the low bar, the ball is pushed off (hopefully) onto the ground 
         * in a reasonable location which we can then measure and retrieve for the second 
         * part of 2 ball
         * NOTE: ALL NUMBERS ARE BOGUS
         * TODO: 
         * Find a way to know the angle offset and when rotating 180 degrees, also compensate for that
         * Add a mechanism for auto align to change the right rpms when dealing with distance
         * In other words, change RPMs based off of y contour value
         */
    	double timeToMidLine = 0.2; // Time to midline from starting position
    	double timeFromLineToShotPosition = 3.1; // Time to drive at half speed to get to shot spot from midline
    	double timeFromShotToSecondBall = 2.8; // Time to drive at half speed to get to the magic spot from low bar after shot
    	double timeToBallFromMagicSpot = 0.2; // Time to drive at half speed to get the ball from the spot we come out from the low bar
    	double angleAfterLowBar = 25; // Angle to turn after coming out of the low bar (before auto align, turn this many degrees) right is positive
    	double speedOfRotation = 0.4; // Speed to do so
    	int rpmU = 5000; // Rpm Upper, look into having it get changed by AutoAlign
    	int rpmL = 4300; // Rpm Lower, look into having it get changed by AutoAlign
    	
    	// Collection
        addParallel(new IntakeArmLower());
        addParallel(new RoutineIntakeBall());
        addParallel(new DriveForwardsForTime(-1 * timeToMidLine));
        // Drive to spot + shoot
        addSequential(new DriveForwardsForTime(timeFromLineToShotPosition));
        addSequential(new StageTwoPulseBack());
        addParallel(new ShooterShootRPM(rpmU, rpmL));
        addSequential(new DriveRotateThetaWithGyro(angleAfterLowBar, speedOfRotation));
        addParallel(new RoutineAutoAlignNoDistance()); // This may be modified to change RPMs as needed, look into this
        addSequential(new ShooterWaitForStabilize(rpmU, rpmL));
        addSequential(new StageTwoQueueToShooter());
        addSequential(new ShooterSlowStop());
        // Ball 1 complete
        // Collection
        addSequential(new DriveRotateThetaWithGyro(180 - angleAfterLowBar, speedOfRotation));
        addSequential(new DriveForwardsForTime(timeFromShotToSecondBall));
        addSequential(new DriveRotateThetaWithGyro(180));
        addParallel(new DriveForwardsForTime(-1 * timeToBallFromMagicSpot));      
        addSequential(new RoutineIntakeBall());
        // Drive to spot + shoot
        addSequential(new DriveForwardsForTime(timeFromShotToSecondBall + timeToBallFromMagicSpot));
        addSequential(new StageTwoPulseBack());
        addParallel(new ShooterShootRPM(rpmU, rpmL));
        addSequential(new DriveRotateThetaWithGyro(angleAfterLowBar, speedOfRotation));
        addParallel(new RoutineAutoAlignNoDistance());
        addSequential(new ShooterWaitForStabilize(rpmU, rpmL));
        addSequential(new StageTwoQueueToShooter());
        addSequential(new ShooterSlowStop());
        // Ball 2 complete
    }
}
