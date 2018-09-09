package com.sahaj.assignments.cleanstrike.main.moves;

import com.sahaj.assignments.cleanstrike.main.singletons.Coins;
import com.sahaj.assignments.cleanstrike.main.vos.MoveTypes;
import com.sahaj.assignments.cleanstrike.main.vos.Points;

public class MultiStrike extends Move {

	private int noOfCoinsPocketed;
	
	private MultiStrike(int noOfCointsPocketed) {
		this.noOfCoinsPocketed = noOfCointsPocketed;
		moveType = MoveTypes.MULTI_STRIKE;
	}
	
	public static MultiStrike of(int noOfCoinsPocketed) {
		return new MultiStrike(noOfCoinsPocketed);
	}
	
	@Override
	public int executeMove() {
		Coins.INSTANCE.pocketBlackCoins(this.noOfCoinsPocketed);
		return Points.GAIN_TWO_POINTS.value();
	}
}
