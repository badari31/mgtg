package com.sahaj.assignments.cleanstrike.main.moves;

import com.sahaj.assignments.cleanstrike.main.vos.MoveTypes;

/**
 * 
 * @author bburli
 *
 * Class StrikerStrike encapsulates the move striker strike. A striker strike move costs players 1 point and 
 * no coins are gained by it.
 */

public class StrikerStrike extends Move {

	protected int coinsGainedByThisMove = -1;
	
	@Override
	public int makeMove() {
		return this.coinsGainedByThisMove;
	}
	
	public StrikerStrike() {
		moveType = MoveTypes.STRIKER_STRIKE;
	}
	
	@Override
	public String toString() {
		return MoveTypes.STRIKER_STRIKE.toString();
	}

}
