package com.sahaj.assignments.cleanstrike.main.singletons;

import java.util.LinkedList;

import com.sahaj.assignments.cleanstrike.main.vos.Event;

public enum GameDashboard {

	INSTANCE;

	private LinkedList<Event> events = new LinkedList<Event>();

	public void addEvent(Event event) {
		this.events.add(event);
	}

	public void display() {
		System.out.println("======================== DASHBOARD START =================================");
		System.out.println("Total black coins = " + Coins.INSTANCE.getCurrentNoOfBlackCoins()
				+ " and red coin is still present = " + Coins.INSTANCE.isRedCoinPresent());

		System.out.println("Events so far ...");

		for (Event eachEvent : events) {
			System.out.println("Player " + eachEvent.getPlayer().getName() + " has made a move " + eachEvent.getMove()
					+ " and his/her current points are " + eachEvent.getPlayer().getPoints());
		}
		
		System.out.println("======================== DASHBOARD END =================================");
	}
}
