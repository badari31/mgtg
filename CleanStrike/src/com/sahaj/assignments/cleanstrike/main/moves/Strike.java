package com.sahaj.assignments.cleanstrike.main.moves;

import com.sahaj.assignments.cleanstrike.main.singletons.Coins;
import com.sahaj.assignments.cleanstrike.main.vos.MoveTypes;
import com.sahaj.assignments.cleanstrike.main.vos.Points;

public class Strike extends Move {

	@Override
	public int executeMove() {
		Coins.INSTANCE.pocketBlackCoins(1);
		return Points.GAIN_SINGLE_POINT.value();
	}
	
	@Override
	public String toString() {
		return MoveTypes.STRIKE.toString();
	}

}
