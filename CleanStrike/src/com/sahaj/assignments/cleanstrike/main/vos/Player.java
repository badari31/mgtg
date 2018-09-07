package com.sahaj.assignments.cleanstrike.main.vos;

public class Player {

	private int points;
	private String name;
	
	public Player(String name) {
		this.name = name;
	}

	public int getPoints() {
		return points;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public void setPoints(int points) {
		this.points += points;
	}
}
