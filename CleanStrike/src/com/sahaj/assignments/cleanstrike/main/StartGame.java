package com.sahaj.assignments.cleanstrike.main;

import com.sahaj.assignments.cleanstrike.main.moves.Defunct;
import com.sahaj.assignments.cleanstrike.main.moves.Move;
import com.sahaj.assignments.cleanstrike.main.moves.MultiStrike;
import com.sahaj.assignments.cleanstrike.main.singletons.GameDashboard;
import com.sahaj.assignments.cleanstrike.main.vos.Event;
import com.sahaj.assignments.cleanstrike.main.vos.Player;

public class StartGame {

	public static void main(String[] args) {
		Player playerA = new Player("A");
		Player playerB = new Player("B");
		
		Move multiStrikeTwoCoins = MultiStrike.of(2);
		Move defunctStrike = new Defunct();
		
		Event.create(playerA, multiStrikeTwoCoins).happens();
		Event.create(playerB, multiStrikeTwoCoins).happens();
		
		GameDashboard.INSTANCE.display();
		
		Event.create(playerA, multiStrikeTwoCoins).happens();
		Event.create(playerB, multiStrikeTwoCoins).happens();
		
		GameDashboard.INSTANCE.display();
		
		Event.create(playerA, defunctStrike).happens();
		Event.create(playerB, multiStrikeTwoCoins).happens();
	}

}
