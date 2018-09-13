package com.sahaj.assignments.cleanstrike.main.moves;

import com.sahaj.assignments.cleanstrike.main.game.Coins;
import com.sahaj.assignments.cleanstrike.main.vos.MoveTypes;

/**
 * 
 * @author bburli
 * @see {@link Defunct}, {@link StrikerStrike}, {@link Strike}, {@link RedStrike}, {@link MultiStrike}
 * 
 * The parent class of all kinds of moves. Contains protected abstract method {@code makeMove} implemented by its children.
 * Each implementing sub-class should override the attributes <code>moveType</code> and <code>coinsGainedByThisMove</code>
 * Each implementing sub-class should implement the abstract method <code>makeMove</code> 
 */

public abstract class Move {

	
	// Default move type NO_MOVE
	protected MoveTypes moveType = MoveTypes.NO_MOVE;
	
	// Default coins gained. Is zero in case of no move.
	protected int pointsGainedByThisMove = 0;
	
	/*
	 * This method executes or performs the move. The pre-condition is first checked i.e. if a move can be made or not by the 
	 * <code>isTheMoveAllowed()</code> method. If a move is not allowed, zero is returned indicating that a player has not
	 * gained any points. In all other cases, the number of points gained by players are returned as a result of <code>makeMove</code> method.
	 * 
	 * @return number of points gained by this move or zero if the move is not allowed.
	 */
	
	public int executeMove() {
		if (isTheMoveAllowed()) {
			return makeMove();
		} else {
			return 0;
		}
	}
	
	/**
	 * Abstract method that has to be implemented by child classes. This method should contain the operation of actually executing the move.
	 * 
	 * @return the number of points resulting from this move.
	 */
	
	protected abstract int makeMove();

	/**
	 * Check if a move is allowed or not. In case of a RED_STRIKE, check if the red coin is present or not to make the move. In other cases, check if the 
	 * points that will be pocketed by the move are present.
	 * 
	 * @return true, if the move is allowed or possible; false otherwise.
	 * @see {@link RedStrike} 
	 */
	
	private boolean isTheMoveAllowed() {
		boolean isTheMoveAllowed = true;
		
		if (this.moveType.equals(MoveTypes.RED_STRIKE) && !Coins.INSTANCE.isRedCoinPresent()) {
			isTheMoveAllowed = false;
		} else {
			isTheMoveAllowed = ((Coins.INSTANCE.getCurrentNoOfBlackCoins() - this.pointsGainedByThisMove) >= 0);	
		}
		
		if (!isTheMoveAllowed)
			System.out.println("Invalid move! Not allowed.");
		
		return isTheMoveAllowed;
	}
	
	/**
	 * 
	 * Protected method that can be called by any of the child class to get the number of coins gained by a particular move.
	 * 
	 * @return number of coins gained by this move.
	 */
	
	protected int getCoinsGainedByThisMove() {
		return this.pointsGainedByThisMove;
	}

	/**
	 * 
	 * Checks if the move made is a foul or not. A move is said to be a foul move, if a player loses at least one point from that move.
	 * 
	 * @return true, if the move made is a foul move; false otherwise.
	 */
	
	public boolean isFoul() {
		return (this.moveType.equals(MoveTypes.DEFUNCT) || this.moveType.equals(MoveTypes.STRIKER_STRIKE));
	}
	
	/**
	 * 
	 * Checks if the move is a miss or not. A move is said to be missed, if the player does not gain any point in that move. Since fouls 
	 * make players lose points, they are also misses. 
	 * 
	 * @return true, if the move is a miss; false otherwise.
	 */
	
	public boolean isMiss() {
		return (isFoul() || this.moveType.equals(MoveTypes.NO_MOVE));
	}
	
	@Override
	public String toString() {
		return this.moveType.toString();
	}
	
}
