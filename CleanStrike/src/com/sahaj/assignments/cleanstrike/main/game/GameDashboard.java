package com.sahaj.assignments.cleanstrike.main.game;

import java.util.LinkedList;
import java.util.stream.Collectors;

import com.sahaj.assignments.cleanstrike.main.vos.Event;
import com.sahaj.assignments.cleanstrike.main.vos.Player;

/**
 * 
 * @author bburli
 * @see {@link Event}
 * 
 * Singleton class to encapsulate all aspect of Game overall status. In summary, this class contains all that is required to know about the game
 * and its current state.
 *
 */

public enum GameDashboard {

	// Single instace of dashboard.
	
	INSTANCE;

	// Total permitted fouls in the game are 3. If any player hits the limit, special punishment ensues.
	
	public final static int TOTAL_PERMITTED_FOULS = 3;
	
	// Total permitted misses in the game are 3. If any player hits the limit, special punishment ensues.
	
	public final static int TOTAL_PERMITTED_MISSES = 3;

	// A linked list maintaining the sequence of events as they occur in the game.
	
	private LinkedList<Event> events = new LinkedList<Event>();

	/**
	 * Method to add or register the event in dashboard. This method first adds the event to the list and then delegates the game progress evaluation logic to 
	 * {@link GameProgressEvaluator}
	 * 
	 * @param event containing player and the move involved.
	 */
	
	public void addEvent(Event event) {
		this.events.add(event);
		GameProgressEvaluator.evaluate(event);
	}

	/**
	 * 
	 * Get players involved in the game so far based on the events that have happened so far.
	 * 
	 * @return Array of players involved in the game so far.
	 */
	
	public Player[] getPlayersInGame() {
		return events.stream()
				.map(e -> e.getPlayer())
				.collect(Collectors.toSet())
				.stream().toArray(Player[]::new);
	}
	
	/**
	 * Method to display the dashboard statistics.
	 */

	public void display() {
		System.out.println("======================== DASHBOARD START =================================");
		System.out.println("Total black coins = " + Coins.INSTANCE.getCurrentNoOfBlackCoins()
				+ " and red coin is still present = " + Coins.INSTANCE.isRedCoinPresent());

		Player[] players = getPlayersInGame();
		StringBuilder messageAboutPlayerPoints = new StringBuilder();
		if (players != null && players.length > 0) {
			for (Player eachPlayer : players) {
				messageAboutPlayerPoints
						.append("Player " + eachPlayer.getName() + " has " + eachPlayer.getPoints() + " points.\n");
			}
		}

		System.out.println(messageAboutPlayerPoints);

		System.out.println("======================== DASHBOARD END =================================");
	}

	/**
	 * Method to reset the dashboard for a new game.
	 */
	
	public void reset() {
		Coins.INSTANCE.reset();
		this.events.clear();
		Player[] players = getPlayersInGame();
		if (players != null && players.length > 1) {
			players[0].reset();
			players[1].reset();
		}
		GameProgressEvaluator.reset();
	}
}
