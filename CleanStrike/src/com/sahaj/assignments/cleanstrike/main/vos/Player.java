package com.sahaj.assignments.cleanstrike.main.vos;

import com.sahaj.assignments.cleanstrike.main.game.GameDashboard;

/**
 * 
 * @author bburli
 *
 * Class Player that encapsulates the player of the game.
 *
 */

public class Player {

	// Each player has their points and thiese are initialized to zero for each player on start.
	
	private int points;
	
	// Name of player to recognize properly.
	private String name;
	
	// The count of number of fouls done by the player.
	private int fouls;
	
	// The count of number of missed done by the player.
	private int miss;
	
	/**
	 * Method executing the function of a foul.
	 * 
	 */
	
	public void foul() {
		this.fouls++;
		if (this.fouls >= GameDashboard.TOTAL_PERMITTED_FOULS)
			deductPoints(1);
	}
	
	/**
	 * Method executing the function of a miss.
	 * 
	 */
	
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

	/**
	 * 
	 * Method to add points to this players quota.
	 * 
	 * @param pointsToBeAdded to this player
	 */
	
	public void addPoints(int pointsToBeAdded) {
		this.points += pointsToBeAdded;
	}
	
	/**
	 * Method to deduct the points from this player.
	 * 
	 * @param pointsToBeDeducted from this player.
	 */
	
	private void deductPoints(int pointsToBeDeducted) {
		this.points -= pointsToBeDeducted;
	}

	public int getFouls() {
		return fouls;
	}

	public int getMiss() {
		return miss;
	}

	public void reset() {
		this.points = 0;
		this.fouls = 0;
		this.miss = 0;
	}

	/**
	 * Method to reset foul counter as consecutive fouls cost player more points.
	 */
	
	public void resetFoulCounter() {
		this.fouls = 0;		
	}

	/**
	 * Method to reset the miss counter as consecutive misses cost player more points.
	 */
	
	public void resetMissCounter() {
		this.miss = 0;
	}
}
