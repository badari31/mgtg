package com.sahaj.assignments.cleanstrike.main.vos;

/**
 * 
 * @author bburli
 *
 *	Enum type enumerating all possible valid moves in the application. The property <code>moveName</code> contains string value indicating the friendly name for the move type.
 */

public enum MoveTypes {

	STRIKE ("Single strike"),
	MULTI_STRIKE ("Multiple strike"),
	RED_STRIKE ("Red Strike"),
	STRIKER_STRIKE ("Striker strike"),
	DEFUNCT ("Defunct strike"),
	NO_MOVE("No move");
	
	private String moveName;
	
	private MoveTypes(String moveName) {
		this.moveName = moveName;
	}
	
	@Override
	public String toString() {
		return this.moveName;
	}
	
}
