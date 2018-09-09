package com.sahaj.assignments.cleanstrike.main.vos;

import com.sahaj.assignments.cleanstrike.main.moves.Move;
import com.sahaj.assignments.cleanstrike.main.singletons.GameDashboard;

public class Event  {

	private Player player;
	private Move move;
	
	private Event(Player player, Move move) {
		this.player = player;
		this.move = move;
	}
	
	public static Event create(Player player, Move move) {
		return new Event(player, move);
	}

	public Player getPlayer() {
		return player;
	}

	public Move getMove() {
		return move;
	}

	public void happens() {
		this.player.addPoints(this.move.executeMove());
		GameDashboard.INSTANCE.addEvent(this);
	}
}
