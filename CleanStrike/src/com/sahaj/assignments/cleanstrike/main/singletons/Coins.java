package com.sahaj.assignments.cleanstrike.main.singletons;

public enum Coins {

	INSTANCE;
	
	private static int blackCoins = 9;
	private static int redCoin = 1;	

	public void pocketBlackCoins(int noOfCointsPocketed) {
		if (blackCoins > 0) {
			blackCoins -= noOfCointsPocketed;
			System.out.println(noOfCointsPocketed+ " black coins pocketed.");
		}
	}
	
	public void pocketRedCoin() {
		if (redCoin == 1) {
			redCoin--;
			System.out.println("Red coin pocketed.");
		} 
	}
	
	public int getCurrentNoOfBlackCoins() {
		return blackCoins;
	}
	
	public boolean isRedCoinPresent() {
		return (redCoin == 1) ? true : false;
	}

	public void loseBlackCoins(int noOfCointsLost) {
		blackCoins -= noOfCointsLost;
		System.out.println(noOfCointsLost+ " black coins lost.");
	}

	public void reset() {
		blackCoins = 9;
		redCoin = 1;
	}

}
