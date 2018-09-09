package com.sahaj.assignments.cleanstrike.main.singletons;

import java.util.LinkedList;
import java.util.stream.Collectors;

import com.sahaj.assignments.cleanstrike.main.vos.Event;
import com.sahaj.assignments.cleanstrike.main.vos.Player;

public enum GameDashboard {

	INSTANCE;
	
	public final static int TOTAL_PERMITTED_FOULS = 3;
	public final static int TOTAL_PERMITTED_MISSES = 3;
	
	private static GameStatus gameStatus = GameStatus.INPROGRESS;
	
	public static void setGameStatus(GameStatus gameStatus) {
		GameDashboard.gameStatus = gameStatus;
	}

	enum GameStatus {
		INPROGRESS, WON, DRAWN;
	}
	
	private LinkedList<Event> events = new LinkedList<Event>();

	public void addEvent(Event event) {
		this.events.add(event);
		checkGameProgress(event);
	}

	private void checkGameProgress(Event event) {
		// Foul - player loses at least one point
		if (event.getMove().isFoul()) {
			event.getPlayer().foul();
		}
		
		
		// 3 Successive misses
		
		if (event.getMove().isMiss()) {
			event.getPlayer().miss();
		}
		
		// Won - at least 5 points and 3 points more than opponent
		// if not 3 points, at least 5 points
		
		Player[] players = getPlayers();
		
		if (players != null && players.length > 1) {
			Player winningPlayer = getWinningPlayer(players);
			if (winningPlayer == null && gameStatus.equals(GameStatus.DRAWN)) {
				System.out.println("Game drawn!");
			} else if (gameStatus.equals(GameStatus.WON)) {
				System.out.println(
						winningPlayer.getName() + " has won the game! With " + winningPlayer.getPoints() + " points");
			} 
		}
		
	}

	private Player getWinningPlayer(Player[] players) {
		final Player firstPlayer = players[0];
		final Player secondPlayer = players[1];
		
		int pointsDiff = (firstPlayer.getPoints() - secondPlayer.getPoints());
		
		if (Math.abs(pointsDiff) == 3) {
			setGameStatus(GameStatus.WON);
			return ((pointsDiff < 0) ? secondPlayer : firstPlayer);
		}
		
		if (firstPlayer.getPoints() >= 5 && secondPlayer.getPoints() < 5) {
			setGameStatus(GameStatus.WON);
			return firstPlayer;
		} else if (secondPlayer.getPoints() >= 5 && firstPlayer.getPoints() < 5) {
			setGameStatus(GameStatus.WON);
			return secondPlayer;
		} else if (Coins.INSTANCE.getCurrentNoOfBlackCoins() == 0){
			// Draw! - Neither has 5 or more points and no one is leading by 3 points.
			setGameStatus(GameStatus.DRAWN);
			return null;
		}
		
		
		setGameStatus(GameStatus.INPROGRESS);
		return null;
	}

	private Player[] getPlayers() {
		return events.stream().map(e -> e.getPlayer()).toArray(Player[]::new);
	}

	public void display() {
		System.out.println("======================== DASHBOARD START =================================");
		System.out.println("Total black coins = " + Coins.INSTANCE.getCurrentNoOfBlackCoins()
				+ " and red coin is still present = " + Coins.INSTANCE.isRedCoinPresent());

		//System.out.println("Events so far ...");
		
		System.out.println("======================== DASHBOARD END =================================");
	}
}
