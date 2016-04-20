package org.usfirst.frc.team5026.lib;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.buttons.Button;

public class AxisButton extends Button {
	private GenericHID stick;
	private int axisPort;
	private double thresh;
	
	public AxisButton(GenericHID stick, int axisPort, double threshold){
		this.stick = stick;
		this.axisPort = axisPort;
		this.thresh = threshold;
	}
	
	private double getAxis(){
		return stick.getRawAxis(axisPort);
	}
	
	@Override
	public boolean get() {
		return getAxis() > thresh;
	}

}
