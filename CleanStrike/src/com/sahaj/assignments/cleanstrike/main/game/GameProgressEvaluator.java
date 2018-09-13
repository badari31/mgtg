package com.sahaj.assignments.cleanstrike.main.game;

import com.sahaj.assignments.cleanstrike.main.vos.Event;
import com.sahaj.assignments.cleanstrike.main.vos.Player;

/**
 * 
 * @author bburli
 * @see {@link GameStatus}
 * 
 * Class to evaluate the progress of the game after every event.
 *
 */

public class GameProgressEvaluator {
	
	// Minimum points to be gained by any player to be declared win.
	
	private static final int MINIMUM_POINTS_TO_WIN = 5;
	
	// Minimum different between two players to declared one as won.
	
	private static final int MINIMUM_POINTS_DIFF_BETWEEN_PLAYERS = 3;

	// GameStatus initially is INPROGRESS.
	
	private static GameStatus gameStatus = GameStatus.INPROGRESS;
	
	// There is no winner to being with so winner is initialized to null.
	
	private static Player winner = null;
	
	/**
	 * Method to reset the progress of the game after game is over.
	 */
	
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
	
	/**
	 * 
	 * @author bburli
	 *
	 *
	 * Inner enum that enumerates various game states.
	 */
	
	private enum GameStatus {
		INPROGRESS, WON, DRAWN;
	}
	
	/**
	 * 
	 * Method to process each event and evaulate against game rules. This does three thigs at the moment:
	 * 1. Check if a foul is made.
	 * 2. Check if a miss is made.
	 * 3. Check if the game has ended in one way or another.
	 * 
	 * @param event denoting each event consisting of player and the move.
	 */
	
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
	
	/**
	 * 
	 * Method to check if the game has been won or not.
	 * 
	 * @param players
	 */
	
	private static void evaluateWin(Player[] players) {
		final Player firstPlayer = players[0];
		final Player secondPlayer = players[1];

		int pointsDiff = (firstPlayer.getPoints() - secondPlayer.getPoints());

		if (firstPlayer.getPoints() >= MINIMUM_POINTS_TO_WIN && secondPlayer.getPoints() < MINIMUM_POINTS_TO_WIN) {
			if (Math.abs(pointsDiff) >= MINIMUM_POINTS_DIFF_BETWEEN_PLAYERS && pointsDiff > 0) {
				winner = firstPlayer;
				gameStatus = GameStatus.WON;
			}
		} else if (secondPlayer.getPoints() >= MINIMUM_POINTS_TO_WIN && firstPlayer.getPoints() < MINIMUM_POINTS_TO_WIN) {
			if (Math.abs(pointsDiff) >= MINIMUM_POINTS_DIFF_BETWEEN_PLAYERS && pointsDiff < 0) {
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
