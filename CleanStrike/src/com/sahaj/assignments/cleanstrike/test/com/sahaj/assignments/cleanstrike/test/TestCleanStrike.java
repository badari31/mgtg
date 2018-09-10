package com.sahaj.assignments.cleanstrike.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.sahaj.assignments.cleanstrike.main.moves.Defunct;
import com.sahaj.assignments.cleanstrike.main.moves.Move;
import com.sahaj.assignments.cleanstrike.main.moves.MultiStrike;
import com.sahaj.assignments.cleanstrike.main.moves.RedStrike;
import com.sahaj.assignments.cleanstrike.main.moves.Strike;
import com.sahaj.assignments.cleanstrike.main.moves.StrikerStrike;
import com.sahaj.assignments.cleanstrike.main.singletons.Coins;
import com.sahaj.assignments.cleanstrike.main.singletons.GameDashboard;
import com.sahaj.assignments.cleanstrike.main.singletons.GameProgress;
import com.sahaj.assignments.cleanstrike.main.vos.Event;
import com.sahaj.assignments.cleanstrike.main.vos.Player;

public class TestCleanStrike {
	
	private Player playerA;
	private Player playerB;
	
	private Move redStrike;
	private Move strikerStrike;
	private Move strike;
	private Move defunct;
	private Move noMove;
	
	
	
	@Before
	public void beforeSteps() {
		this.playerA = new Player("A");
		this.playerB = new Player("B");
		
		this.redStrike = new RedStrike();
		this.strike = new Strike();
		this.strikerStrike = new StrikerStrike();
		this.noMove = new Move() {
			
			@Override
			public int executeMove() {
				return 0;
			}
		};
		
		this.defunct = new Defunct();
	}
	
	@After
	public void afterSteps() {
		GameDashboard.INSTANCE.reset();
	}

	
	@Test
	public void testScenarioOne() {
		 Event.create(this.playerA, this.strike).happens();
		 Event.create(this.playerB, this.strike).happens();
		 Event.create(this.playerA, this.strike).happens();
		 Event.create(this.playerB, this.strike).happens();
		 Event.create(this.playerA, this.strike).happens();
		 Event.create(this.playerB, this.redStrike).happens();
		 Event.create(this.playerA, this.strikerStrike).happens();
		 
		 assertEquals(2, this.playerA.getPoints());
		 assertEquals(5, this.playerB.getPoints());
		 assertEquals("WON", GameProgress.getGameStatus().toString());
		 assertEquals("B", GameProgress.getWinner().getName());
	}
	
	@Test
	public void testScenarioTwo() {
		 Event.create(this.playerA, MultiStrike.of(2)).happens();
		 Event.create(this.playerB, MultiStrike.of(3)).happens();
		 Event.create(this.playerA, this.strike).happens();
		 Event.create(this.playerB, this.strikerStrike).happens();
		 Event.create(this.playerA, this.defunct).happens();
		 Event.create(this.playerB, this.noMove).happens();
		 Event.create(this.playerA, this.redStrike).happens();
		 Event.create(this.playerB, MultiStrike.of(2)).happens();
		 
		 assertEquals(4, this.playerA.getPoints());
		 assertEquals(4, this.playerB.getPoints());
		 assertEquals("DRAWN", GameProgress.getGameStatus().toString());
		 assertNull(GameProgress.getWinner());
	}
	
	@Test
	public void testScenarioThree() {
		 Event.create(this.playerA, this.noMove).happens();
		 Event.create(this.playerB, this.strike).happens();
		 Event.create(this.playerA, this.noMove).happens();
		 Event.create(this.playerB, this.strike).happens();
		 Event.create(this.playerA, this.noMove).happens();
		 Event.create(this.playerB, this.noMove).happens();
		 Event.create(this.playerA, this.strike).happens();
		 Event.create(this.playerB, MultiStrike.of(2)).happens();
		 Event.create(this.playerA, this.redStrike).happens();
		 Event.create(this.playerB, this.strikerStrike).happens();
		 Event.create(this.playerA, MultiStrike.of(2)).happens();
		 Event.create(this.playerB, this.strike).happens();
		 Event.create(this.playerA, this.strike).happens();
		 Event.create(this.playerB, this.noMove).happens();
		 
		 Coins.INSTANCE.getCurrentNoOfBlackCoins();
		 
		 assertEquals(6, this.playerA.getPoints());
		 assertEquals(4, this.playerB.getPoints());
		 assertEquals("DRAWN", GameProgress.getGameStatus().toString());
		 assertNull(GameProgress.getWinner());
	}
}
