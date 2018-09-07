package com.sahaj.assignments.cleanstrike.main.moves;

import com.sahaj.assignments.cleanstrike.main.vos.MoveTypes;
import com.sahaj.assignments.cleanstrike.main.vos.Points;

public class StrikerStrike extends Move {

	@Override
	public int executeMove() {
		return Points.LOSE_SINGLE_POINT.value();
	}
	
	@Override
	public String toString() {
		return MoveTypes.STRIKER_STRIKE.toString();
	}

}
