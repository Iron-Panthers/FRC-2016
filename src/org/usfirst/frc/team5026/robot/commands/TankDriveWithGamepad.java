package org.usfirst.frc.team5026.robot.commands;

import java.nio.ByteBuffer;

import org.usfirst.frc.team5026.lib.PantherGamepad;
import org.usfirst.frc.team5026.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.communication.FRCNetworkCommunicationsLibrary;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *byte value = (byte) m_joystickAxes[stick][axis];

    if (value < 0) {
      return value / 128.0;
    } else {
      return value / 127.0;
    }
    
    private short[][] m_joystickAxes = new short[kJoystickPorts][FRCNetworkCommunicationsLibrary.kMaxJoystickAxes];
    
    for (byte stick = 0; stick < kJoystickPorts; stick++) {
      m_joystickAxes[stick] = FRCNetworkCommunicationsLibrary.HALGetJoystickAxes(stick);
    }
 */
public class TankDriveWithGamepad extends Command {

	private PantherGamepad gamepad;

    public TankDriveWithGamepad(PantherGamepad gamepad) {
    	requires(Robot.drive);
    	this.gamepad = gamepad;
    }

    protected void initialize() {
    	Robot.drive.stopDriveMotors();
    }

    protected void execute() {
    	Robot.drive.setLeftRightMotors(gamepad.getLeftYAxis(), gamepad.getRightYAxis());
    	SmartDashboard.putNumber("ENC. LOWER VALUE", Robot.shooter.lowerShooterGroup.motor1.getSpeed());
    	SmartDashboard.putNumber("ENC. UPPER VALUE", Robot.shooter.upperShooterGroup.motor1.getSpeed());
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    	Robot.drive.stopDriveMotors();
    }

    protected void interrupted() {
    	end();
    }
}
