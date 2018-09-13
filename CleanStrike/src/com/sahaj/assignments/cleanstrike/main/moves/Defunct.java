package com.sahaj.assignments.cleanstrike.main.moves;

import com.sahaj.assignments.cleanstrike.main.game.Coins;
import com.sahaj.assignments.cleanstrike.main.vos.MoveTypes;

/**
 * 
 * @author bburli
 * @see {@link Move}, {@link MoveTypes}
 * 
 * Class Defunct move. A move is a defunct move if the coin is tossed out the board. In such a case, the player loses two points or gains -2 points.  
 * 
 */

public class Defunct extends Move {
	
	protected int pointsGainedByThisMove = -2;
	
	public Defunct() {
		this.moveType = MoveTypes.DEFUNCT;
	}
	
	@Override
	public int makeMove() {
		Coins.INSTANCE.loseBlackCoins(1);
		return this.pointsGainedByThisMove;
	}

}
