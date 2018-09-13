package com.sahaj.assignments.cleanstrike.main.game;

import com.sahaj.assignments.cleanstrike.main.moves.RedStrike;

/**
 * 
 * @author bburli
 *
 * Singleton class that encapsulates all functionality that is related to coins. 
 *
 */

public enum Coins {

	// Single instance of class.
	
	INSTANCE;
	
	// There are total of 9 black coins and 1 red coin in the game.
	
	private static int blackCoins = 9;
	private static int redCoin = 1;	

	
	/**
	 * 
	 * Method to pocket black coins. This is called by all moves except {@link RedStrike} which deals with red coin.
	 * 
	 * @param noOfCointsPocketed by the move.
	 */
	
	public void pocketBlackCoins(int noOfCointsPocketed) {
		if (blackCoins > 0) {
			blackCoins -= noOfCointsPocketed;
			System.out.println(noOfCointsPocketed+ " black coins pocketed.");
		}
	}
	
	/**
	 * Method to pocket red coin. Only called by {@link RedStrike}
	 */
	
	public void pocketRedCoin() {
		if (redCoin == 1) {
			redCoin--;
			System.out.println("Red coin pocketed.");
		} 
	}
	
	public int getCurrentNoOfBlackCoins() {
		return blackCoins;
	}
	
	/**
	 * Method to check if red coin is present or not in order to make a {@link RedStrike} move.
	 * @return true, if the red coin is present or if it's count equals 1; false otherwise.
	 */
	
	public boolean isRedCoinPresent() {
		return (redCoin == 1) ? true : false;
	}

	/**
	 * 
	 * Method to be called by moves that lose black coins. Note that it does not encapsulate anything new 
	 * apart from what <code>pocketBlackCoins</code> already does. This method, however, helps distinguish 
	 * the client on what call is being made and is explicit in contract. Instead of calling <code> Coins.INSTANCE.pocketBlackCoins(-1)</code>, saying
	 * <code> Coins.INSTANCE.loseBlackCoins(1)</code> is clearer and neater. 
	 * 
	 * @param noOfCointsLost by the move.
	 */
	
	public void loseBlackCoins(int noOfCointsLost) {
		blackCoins -= noOfCointsLost;
		System.out.println(noOfCointsLost+ " black coins lost.");
	}

	/**
	 * Method to reset the coins status so that a new game can begin.
	 */
	
	public void reset() {
		blackCoins = 9;
		redCoin = 1;
	}

}
