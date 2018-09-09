package com.sahaj.assignments.cleanstrike.main.vos;

import com.sahaj.assignments.cleanstrike.main.singletons.GameDashboard;

public class Player {

	private int points;
	private String name;
	private int fouls;
	private int miss;
	
	public void foul() {
		this.fouls++;
		if (this.fouls >= GameDashboard.TOTAL_PERMITTED_FOULS)
			deductPoints(1);
	}
	
	public void miss() {
		this.miss++;
		if (this.miss >= GameDashboard.TOTAL_PERMITTED_MISSES)
			deductPoints(1);
	}
	
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

	public void addPoints(int pointsToBeAdded) {
		this.points += pointsToBeAdded;
	}
	
	private void deductPoints(int pointsToBeDeducted) {
		this.points -= pointsToBeDeducted;
	}

	public int getFouls() {
		return fouls;
	}

	public int getMiss() {
		return miss;
	}
}
