package com.sahaj.assignments.cleanstrike.main.singletons;

import java.util.LinkedList;
import java.util.stream.Collectors;

import com.sahaj.assignments.cleanstrike.main.vos.Event;
import com.sahaj.assignments.cleanstrike.main.vos.Player;

public enum GameDashboard {

	INSTANCE;

	public final static int TOTAL_PERMITTED_FOULS = 3;
	public final static int TOTAL_PERMITTED_MISSES = 3;

	private LinkedList<Event> events = new LinkedList<Event>();

	public void addEvent(Event event) {
		this.events.add(event);
		GameProgress.evaluate(event);
	}

	public Player[] getPlayersInGame() {
		return events.stream()
				.map(e -> e.getPlayer())
				.collect(Collectors.toSet())
				.stream().toArray(Player[]::new);
	}

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

	public void reset() {
		Coins.INSTANCE.reset();
		this.events.clear();
		Player[] players = getPlayersInGame();
		if (players != null && players.length > 1) {
			players[0].reset();
			players[1].reset();
		}
		GameProgress.reset();
	}
}
