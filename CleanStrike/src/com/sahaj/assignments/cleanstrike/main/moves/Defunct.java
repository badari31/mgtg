package com.sahaj.assignments.cleanstrike.main.moves;

import com.sahaj.assignments.cleanstrike.main.singletons.Coins;
import com.sahaj.assignments.cleanstrike.main.vos.MoveTypes;
import com.sahaj.assignments.cleanstrike.main.vos.Points;

public class Defunct extends Move {
	
	public Defunct() {
		this.moveType = MoveTypes.DEFUNCT;
	}
	
	@Override
	public int executeMove() {
		Coins.INSTANCE.loseBlackCoins(1);
		return Points.LOSE_TWO_POINTS.value();
	}

}