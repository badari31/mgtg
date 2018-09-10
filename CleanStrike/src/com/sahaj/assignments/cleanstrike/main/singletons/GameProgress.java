package com.sahaj.assignments.cleanstrike.main.singletons;

import com.sahaj.assignments.cleanstrike.main.vos.Event;
import com.sahaj.assignments.cleanstrike.main.vos.Player;

public class GameProgress {

	private static GameStatus gameStatus = GameStatus.INPROGRESS;
	private static Player winner = null;
	
	public static void reset() {
		gameStatus = GameStatus.INPROGRESS;
		winner = null;
	}
	
	public static Player getWinner() {
		return winner;
	}
	
	public static String getGameStatus() {
		return gameStatus.toString();
	}
	
	private enum GameStatus {
		INPROGRESS, WON, DRAWN;
	}
	
	public static void evaluate(Event event) {
		// Foul - player loses at least one point
		if (event.getMove().isFoul()) {
			event.getPlayer().foul();
		} else {
			event.getPlayer().resetFoulCounter();
		}

		// 3 Successive misses

		if (event.getMove().isMiss()) {
			event.getPlayer().miss();
		} else {
			event.getPlayer().resetMissCounter();
		}

		// Won - at least 5 points and 3 points more than opponent
		// if not 3 points, at least 5 points

		Player[] players = GameDashboard.INSTANCE.getPlayersInGame();

		if (players != null && players.length > 1) {
			evaluateWin(players);
			
			if (!gameStatus.equals(GameStatus.INPROGRESS)) {
				// Game has ended in some way.
				
				if (gameStatus.equals(GameStatus.DRAWN)) {
					System.out.println("Game drawn!");	
				} else {
					System.out.println(
							winner.getName() + " has won the game! With " + winner.getPoints() + " points");
				}
			}
		}	
	}
	
	private static void evaluateWin(Player[] players) {
		final Player firstPlayer = players[0];
		final Player secondPlayer = players[1];

		int pointsDiff = (firstPlayer.getPoints() - secondPlayer.getPoints());

		if (firstPlayer.getPoints() >= 5 && secondPlayer.getPoints() < 5) {
			if (Math.abs(pointsDiff) >= 3 && pointsDiff > 0) {
				winner = firstPlayer;
				gameStatus = GameStatus.WON;
			}
		} else if (secondPlayer.getPoints() >= 5 && firstPlayer.getPoints() < 5) {
			if (Math.abs(pointsDiff) >= 3 && pointsDiff < 0) {
				winner = secondPlayer;
				gameStatus = GameStatus.WON;
			}
		} 
		
		if (Coins.INSTANCE.getCurrentNoOfBlackCoins() == 0 && gameStatus.equals(GameStatus.INPROGRESS)) {
			// Draw! - Neither has 5 or more points and no one is leading by 3 points.
			winner = null;
			gameStatus = GameStatus.DRAWN;
		} 
	}

}
