package com.sahaj.assignments.cleanstrike.main.moves;

import com.sahaj.assignments.cleanstrike.main.game.Coins;
import com.sahaj.assignments.cleanstrike.main.vos.MoveTypes;

/**
 * 
 * @author bburli
 *
 *
 * Class Strike encapsulated the move to gain one point by pocketing one coin.
 */

public class Strike extends Move {

	protected int pointsGainedByThisMove = 1;
	
	public Strike() {
		this.moveType = MoveTypes.STRIKE;
	}
	
	@Override
	public int makeMove() {
		Coins.INSTANCE.pocketBlackCoins(1);
		return this.pointsGainedByThisMove;
	}
}
