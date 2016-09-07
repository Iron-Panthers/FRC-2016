package org.usfirst.frc.team5026.lib;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.buttons.Button;

public class AxisButton extends Button {
	private GenericHID stick;
	private int axisPort;
	private double thresh;
	private boolean moreThan = true;
	
	public AxisButton(GenericHID stick, int axisPort, double threshold){
		this.stick = stick;
		this.axisPort = axisPort;
		this.thresh = threshold;
	}
	
	public AxisButton(GenericHID stick, int axisPort, double threshold, boolean moreThan){
		this.stick = stick;
		this.axisPort = axisPort;
		this.thresh = threshold;
		this.moreThan = moreThan;
	}
	
	private double getAxis(){
		return stick.getRawAxis(axisPort);
	}
	
	@Override
	public boolean get() {
		if (moreThan){
			return getAxis() > thresh;
		} else {
			return getAxis() < thresh;
		}
	}

}
