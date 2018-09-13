package com.sahaj.assignments.cleanstrike.main.vos;

import com.sahaj.assignments.cleanstrike.main.game.GameDashboard;
import com.sahaj.assignments.cleanstrike.main.moves.Move;

/**
 * 
 * @author bburli
 *
 * Class Event encapsulated the occuring of an event in the game which is always a combination of player and move and denotes that a
 * particular player has made a specific move.
 * 
 * @see {@link Player}, {@link Move}
 *
 */

public class Event  {

	// The player involved in the event.
	
	private Player player;
	
	// The move executed by the player.
	
	private Move move;
	
	private Event(Player player, Move move) {
		this.player = player;
		this.move = move;
	}
	
	/**
	 * Static factory method to create an event. Parameters player and move are passed to the event.
	 * 
	 * 
	 * @param player who made a move and is thus part of this event.
	 * @param move which was made and is part of the event
	 * @return Event instance involving player and move.
	 */
	
	public static Event create(Player player, Move move) {
		return new Event(player, move);
	}

	public Player getPlayer() {
		return player;
	}

	public Move getMove() {
		return move;
	}

	/**
	 * Method that executes the event. Only creating an event won't do anything. 
	 */
	
	public void happens() {
		this.player.addPoints(this.move.executeMove());
		GameDashboard.INSTANCE.addEvent(this);
	}
}
