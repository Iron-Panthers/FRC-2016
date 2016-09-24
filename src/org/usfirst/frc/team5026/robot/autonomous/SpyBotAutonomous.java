package org.usfirst.frc.team5026.robot.autonomous;

import org.usfirst.frc.team5026.robot.commands.RoutineShootBall;
import org.usfirst.frc.team5026.robot.commands.ShooterPistonsLower;
import org.usfirst.frc.team5026.robot.commands.StageTwoIntake;
import org.usfirst.frc.team5026.robot.commands.StageTwoPulseBack;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class SpyBotAutonomous extends CommandGroup {
    
    public  SpyBotAutonomous() {
    	addSequential(new StageTwoIntake());
    	addSequential(new StageTwoPulseBack());
    	//addSequential(new ShooterPistonsLower());
        addSequential(new RoutineShootBall()); 
    }
}
