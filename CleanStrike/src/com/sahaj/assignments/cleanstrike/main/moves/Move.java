package com.sahaj.assignments.cleanstrike.main.moves;

import com.sahaj.assignments.cleanstrike.main.vos.MoveTypes;

public abstract class Move {

	protected MoveTypes moveType;
	
	public abstract int executeMove();
	
	@Override
	public String toString() {
		return MoveTypes.NO_MOVE.toString();
	}
	
}
