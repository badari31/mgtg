package com.sahaj.assignments.cleanstrike.main.moves;

import com.sahaj.assignments.cleanstrike.main.game.Coins;
import com.sahaj.assignments.cleanstrike.main.vos.MoveTypes;

/**
 * 
 * @author bburli
 *
 * Class RedStrike encapsulates the move where red coin is pocketed.
 *
 */

public class RedStrike extends Move {

	protected int pointsGainedByThisMove = 3;
	
	public RedStrike() {
		moveType = MoveTypes.RED_STRIKE;
	}
	
	@Override
	public int makeMove() {
		Coins.INSTANCE.pocketRedCoin();
		return this.pointsGainedByThisMove;
	}

}
