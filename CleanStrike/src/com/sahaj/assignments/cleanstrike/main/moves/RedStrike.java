package com.sahaj.assignments.cleanstrike.main.moves;

import com.sahaj.assignments.cleanstrike.main.singletons.Coins;
import com.sahaj.assignments.cleanstrike.main.vos.MoveTypes;
import com.sahaj.assignments.cleanstrike.main.vos.Points;

public class RedStrike extends Move {

	public RedStrike() {
		moveType = MoveTypes.RED_STRIKE;
	}
	
	@Override
	public int executeMove() {
		Coins.INSTANCE.pocketRedCoin();
		return Points.GAIN_THREE_POINTS.value();
	}

}
