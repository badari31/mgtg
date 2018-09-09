package com.sahaj.assignments.cleanstrike.main.moves;

import com.sahaj.assignments.cleanstrike.main.vos.MoveTypes;

public abstract class Move {

	protected MoveTypes moveType = MoveTypes.NO_MOVE;
	
	public abstract int executeMove();
	
	public boolean isFoul() {
		return (this.moveType.equals(MoveTypes.DEFUNCT) || this.moveType.equals(MoveTypes.STRIKER_STRIKE));
	}
	
	public boolean isMiss() {
		return (isFoul() || this.moveType.equals(MoveTypes.NO_MOVE));
	}
	
	@Override
	public String toString() {
		return this.moveType.toString();
	}
	
}
