
package org.usfirst.frc.team5026.robot;

import org.usfirst.frc.team5026.lib.ShooterMotorGroup;
import org.usfirst.frc.team5026.robot.autonomous.CrossLowBarAutonomous;
import org.usfirst.frc.team5026.robot.autonomous.DoNothingAutoGroup;
import org.usfirst.frc.team5026.robot.autonomous.LowBarShootAutoAlignAutonomous;
import org.usfirst.frc.team5026.robot.autonomous.SpyBotAutonomous;
import org.usfirst.frc.team5026.robot.subsystems.Drive;
import org.usfirst.frc.team5026.robot.subsystems.IntakeArm;
import org.usfirst.frc.team5026.robot.subsystems.IntakeMotors;
import org.usfirst.frc.team5026.robot.subsystems.PIDDrive;
import org.usfirst.frc.team5026.robot.subsystems.RotationAlign;
import org.usfirst.frc.team5026.robot.subsystems.Shifter;
import org.usfirst.frc.team5026.robot.subsystems.Shooter;
import org.usfirst.frc.team5026.robot.subsystems.ShooterPistons;
import org.usfirst.frc.team5026.robot.subsystems.StageTwo;

import edu.wpi.first.wpilibj.CANTalon.FeedbackDevice;
import edu.wpi.first.wpilibj.CANTalon.TalonControlMode;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {

	public static OI oi;
	public static Hardware hardware;
	public static Drive drive;
	public static IntakeArm intakeArm;
	public static IntakeMotors intakeMotors;
	public static StageTwo stageTwo;
	public static Shooter shooter;
	public static ShooterPistons shooterPistons;
	public static RotationAlign rotate;
	public static Shifter shifter;
	
	public static PIDDrive pidDrive;

    CommandGroup autonomousCommand;
    SendableChooser autonomousChooser;

	public int[] lookupU = {
			0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
			0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
			0, 0, 0, 0, 0, 0, 0, 0, 0, 0
	};
	public int[] lookupL = {
			0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
			0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
			0, 0, 0, 0, 0, 0, 0, 0, 0, 0
	};
	
	public static PIDController motorPID1;
	public static PIDController motorPID2;
	
	public int shooterIsNegative = -1;
	public static int rpmUpperBatter = -3200;
	public static int rpmLowerBatter = -4700;

	public static int rpmUpperShooter = -4800; //5600
	public static int rpmLowerShooter = -3200; //4000
	
	public static int rpmUpperShooterAuto = -5600;
	public static int rpmLowerShooterAuto = -4000;
	NetworkTable table;
	
	double[] defaultValue = new double[0];
	public double[] centerX;
	public double[] centerY;
	public double[] areas;
	public double[] widths;
	public double[] heights;
	public double contourCenterX;
	public double contourCenterY;
	public double contourX;
	public double contourY;
	public int indexOfMaxArea = 0;
	// dist.
	public double distance;
	public double xOffset;
	public double yOffset;
	String sr = "";
	
	double maxArea = 0;
	public static boolean canDrive = true;

	int arrayNum = 0;
	//distance = 91 inches: 4000, 4000; distance = 76 inches: 4000, 4000;
	// SPY BOX: 123 INCHES 4600 UPPER, 3200 LOWER
	// SAFE SHOT: 143 INCHES 4800 UPPER, 3200 LOWER
	// SAFE SHOT: 126 INCHES 4800 UPPER, 3200 LOWER
	
	private void setupTwoGroup(ShooterMotorGroup group, boolean reverse, boolean upper){
    	group.motor1.configNominalOutputVoltage(+0.0f, -0.0f);
    	group.motor1.configPeakOutputVoltage(+12.0f, -12.0f);
    	group.motor1.setProfile(Constants.SHOOTER_PROFILE);
    	if (upper) {
    		group.motor1.setF(Constants.UPPER_SHOOTER_F);
        	group.motor1.setP(Constants.UPPER_SHOOTER_P);
        	group.motor1.setI(Constants.UPPER_SHOOTER_I);
        	group.motor1.setD(Constants.UPPER_SHOOTER_D);
    	} else {
    		group.motor1.setF(Constants.LOWER_SHOOTER_F);
        	group.motor1.setP(Constants.LOWER_SHOOTER_P);
        	group.motor1.setI(Constants.LOWER_SHOOTER_I);
        	group.motor1.setD(Constants.LOWER_SHOOTER_I);
    	}
    	// Sec. 17.2.3 (Software Reference Manual)
    	group.motor1.reverseSensor(reverse);
    	group.motor1.setFeedbackDevice(FeedbackDevice.QuadEncoder);
    	group.motor1.configEncoderCodesPerRev(1024); // 1/4 * 4096 (CAUSE 4096 IS THE NUMBER OF TICKS PER REV. MEASURED. F U MANUAL (check position changes (big number in selftest))
    	group.motor1.setPosition(0);
    	group.motor1.setForwardSoftLimit(+15.0);
    	group.motor1.setReverseSoftLimit(-15.0);
		group.motor1.changeControlMode(TalonControlMode.Speed);
    	
    	// Sec. 12.1.3 (Software Reference Manual)
    	//group.motorController1.setPID(akbar.p, akbar.i, akbar.d, akbar.f, akbar.izone, akbar.ramprate, akbar.profile);
    	
    	group.motor2.configNominalOutputVoltage(+0.0f, 0.0f);
    	group.motor2.configPeakOutputVoltage(+12.0f, 12.0f);
    	group.motor2.setProfile(Constants.SHOOTER_PROFILE);
    	group.motor2.setForwardSoftLimit(+15.0);
    	group.motor2.setReverseSoftLimit(15.0);
    	//group.motorController2.changeControlMode(TalonControlMode.PercentVbus);
    	group.motor2.changeControlMode(TalonControlMode.Follower);
    	group.motor2.reverseOutput(false);
    	group.motor2.set(group.motor1.getDeviceID());
    	
    	group.motor1.disable();
    }
    
    public void robotInit() {
		oi = new OI();
		hardware = new Hardware();
		drive = new Drive();
		intakeArm = new IntakeArm();
		intakeMotors = new IntakeMotors();
		stageTwo = new StageTwo();
		shooter = new Shooter(lookupU, lookupL);
		shifter = new Shifter(false);
		shooterPistons = new ShooterPistons();
		setupTwoGroup(hardware.lowerShooterGroup, true, false);
		setupTwoGroup(hardware.upperShooterGroup, true, true);
		rotate = new RotationAlign();
		oi.mapButtonsToCommands();
		NetworkTable.setServerMode();
		
		//pidDrive = new PIDDrive("LAME", 0.5,0,0,0.1, drive);
		//pidDrive.enable();
		
        autonomousChooser = new SendableChooser();
        autonomousChooser.addDefault("ESSENTIALLY MULTIBALL (AUTO ALIGN + LOW BAR)", new LowBarShootAutoAlignAutonomous());
        autonomousChooser.addObject("Do Nothing", new DoNothingAutoGroup());
        autonomousChooser.addObject("Cross Low Bar", new CrossLowBarAutonomous());
        autonomousChooser.addObject("Spy Box Shot", new SpyBotAutonomous());
        autonomousChooser.addObject("DO NOTHING 2", new DoNothingAutoGroup());
        SmartDashboard.putData("Autonomous Selector", autonomousChooser);
        
    	for (int i = 0; i < lookupU.length; i++) {
    		if (lookupU[i] == 0) {
    			lookupU[i] = (Constants.SHOOTER_MAX_RPM * shooterIsNegative * (i + 1)) / lookupU.length;
    		}
    	}
    	for (int j = 0; j < lookupL.length; j++) {
    		if (lookupL[j] == 0) {
    			lookupL[j] = (Constants.SHOOTER_MAX_RPM * shooterIsNegative * (j + 1)) / lookupL.length;
    		}
    	}
    	
    	// this makes the shooter lookup tables have values that increase by a certain ratio,
    	// where the 30th value of the array is max speed (in rpms) {14000}
    	// and the 0th value is 0 rpm
    }
	
	/**
     * This function is called once each time the robot enters Disabled mode.
     * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
     */
    public void disabledInit(){
    	shooter.stop();
    }
	
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
		shooter.stop();
	}

	/**
	 * This autonomous (along with the chooser code above) shows how to select between different autonomous modes
	 * using the dashboard. The sendable chooser code works with the Java SmartDashboard. If you prefer the LabVIEW
	 * Dashboard, remove all of the chooser code and uncomment the getString code to get the auto name from the text box
	 * below the Gyro
	 *
	 * You can add additional auto modes by adding additional commands to the chooser code above (like the commented example)
	 * or additional comparisons to the switch structure below with additional strings & commands.
	 */
    public void autonomousInit() {
    	autonomousCommand = (CommandGroup) autonomousChooser.getSelected();
    	System.out.println(autonomousChooser.getSelected());
    	System.out.println(autonomousCommand.getName());
		if (autonomousCommand != null) autonomousCommand.start();
    }

    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
        System.out.println(autonomousCommand.isRunning());
        if (autonomousCommand.timeSinceInitialized() <= 2) {
	        if (!autonomousCommand.isRunning()) {
	        	autonomousCommand.start();
	        	System.out.println("RESTARTING");
	        }
        }
    }

    public void teleopInit() {
    	hardware.shooterLight.set(0);
        if (autonomousCommand != null) autonomousCommand.cancel();
    }

    public void teleopPeriodic() {
        Scheduler.getInstance().run();
    }
    	
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
        LiveWindow.run();
    }
}
