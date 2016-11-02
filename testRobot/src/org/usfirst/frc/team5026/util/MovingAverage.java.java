package org.usfirst.frc.team5026.util;

public class MovingAverage {
	public int[] movingAverageArray;

	public MovingAverage(int averageSize) {
		movingAverageArray = new int[averageSize];
	}

	public void inputJoystickValue(int value) {
		movingAverageArray[0] = value;
	}

	public int getAverage() {
		int sum = 0;

		for(int i = 0; i < movingAverageArray.length; i++) {
			sum += movingAverageArray[i];
		}
			return sum / movingAverageArray.length;
	}

	public void shiftValues() {
		for(int i = (movingAverageArray.length - 1); i > 0; i--) {
			movingAverageArray[i] = movingAverageArray[i - 1];
		}
	}

	public int movingAverageUpdate(int value) {
		inputJoystickValue(value);
		getAverage();
		shiftValues();
	}
}