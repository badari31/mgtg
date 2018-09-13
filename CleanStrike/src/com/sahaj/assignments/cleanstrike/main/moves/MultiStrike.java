package com.sahaj.assignments.cleanstrike.main.moves;

import com.sahaj.assignments.cleanstrike.main.game.Coins;
import com.sahaj.assignments.cleanstrike.main.vos.MoveTypes;

/**
 * 
 * @author bburli
 *
 * Class encapsulating multiple strike. A multiple strike is a strike of more than 1 coins. As such, a multistrike
 * can pocket as many coins as possible. 
 *
 */

public class MultiStrike extends Move {

	protected int pointsGainedByThisMove;
	
	private MultiStrike(int noOfCointsPocketed) {
		this.pointsGainedByThisMove = noOfCointsPocketed;		
		moveType = MoveTypes.MULTI_STRIKE;
	}
	
	/**
	 * Static factory method that take a parameter of number of coins involved in the MultiStrike and creates
	 * and instance of the move.
	 * 
	 * @param noOfCoinsPocketed in this MultiStrike move.
	 * @return instance of MultiStrike with the number of coins involved or gained by strike.
	 */
	
	public static MultiStrike of(int noOfCoinsPocketed) {
		return new MultiStrike(noOfCoinsPocketed);
	}
	
	@Override
	public int makeMove() {
		Coins.INSTANCE.pocketBlackCoins(this.pointsGainedByThisMove);
		return this.pointsGainedByThisMove;
	}
}
