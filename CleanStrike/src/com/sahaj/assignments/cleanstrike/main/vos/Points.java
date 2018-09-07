package com.sahaj.assignments.cleanstrike.main.vos;

public enum Points {

	GAIN_SINGLE_POINT(1),
	GAIN_TWO_POINTS(2),
	GAIN_THREE_POINTS(3),
	
	LOSE_SINGLE_POINT(-1),
	LOSE_TWO_POINTS(-2);
	
	private int points;
	
	private Points(int points) {
		this.points = points;
	}
	
	public int value() {
		return this.points;
	}
	
	
}
