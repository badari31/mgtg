package com.thoughtworks.assignment.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;

import com.thoughtworks.assignment.main.ConversationMapper;
import com.thoughtworks.assignment.main.MerchantGuide;
import com.thoughtworks.assignment.main.RomanNumeralsCalculator;

class TestMerchantsGuide {

	@Test
	void testRomanNumeralConversion() {
		Map<String, Integer> romanNumerals = new HashMap<String, Integer>();
		romanNumerals.put("MCMIXIX", 1918);
		romanNumerals.put("IIII", -1);
		romanNumerals.put("CCC", 300);
		romanNumerals.put("MMM", 3000);
		romanNumerals.put("DDD", -1);
		romanNumerals.put("VVV", -1);
		romanNumerals.put("L", 50);
		romanNumerals.put("X", 10);
		romanNumerals.put("MDX", 1510);
		romanNumerals.put("IIIVIII", 9);

		for (String eachTestCase: romanNumerals.keySet()) {
			long actual = RomanNumeralsCalculator.INSTANCE.convertRomanToArabic(eachTestCase);
			long expected = romanNumerals.get(eachTestCase);
			assertEquals(expected, actual, "Assert Failed!");
		}
		
	}
	
	@Test
	void testQuery1() {
		MerchantGuide mGuide = initializeMerchantGuide();
		String answer = mGuide.processQuery("how much is pish tegj glob glob ?");
		String expected = "pish tegj glob glob is 42.0";
		
		assertEquals(expected, answer);
	}
	
	@Test
	void testQuery2() {
		MerchantGuide mGuide = initializeMerchantGuide();
		String answer = mGuide.processQuery("how much wood could a woodchuck chuck if a woodchuck could chuck wood ?");
		String expected = "I have no idea what you're talking about.\n";
		
		assertEquals(expected, answer);
	}
	
	@Test
	void testQuery3() {
		MerchantGuide mGuide = initializeMerchantGuide();
		String answer = mGuide.processQuery("how many Credits is glob prok Silver ?");
		String expected = "glob prok Silver is 68.0 Credits";
		
		assertEquals(expected, answer);
	}
	
	@Test
	void testQuery4() {
		MerchantGuide mGuide = initializeMerchantGuide();
		String answer = mGuide.processQuery("how many Credits is glob prok Gold ?");
		String expected = "glob prok Gold is 57800.0 Credits";
		
		assertEquals(expected, answer);
	}
	
	@Test
	void testQuery5() {
		MerchantGuide mGuide = initializeMerchantGuide();
		String answer = mGuide.processQuery("how many Credits is glob prok Iron ?");
		String expected = "glob prok Iron is 782.0 Credits";
		
		assertEquals(expected, answer);
	}
	
	private MerchantGuide initializeMerchantGuide() {
		MerchantGuide mGuide = new MerchantGuide();
		ConversationMapper conversationMapper = new ConversationMapper();
		conversationMapper.setConversations(getConversations());
		conversationMapper.processConversations();
		mGuide.setConversationMapper(conversationMapper);
		
		return mGuide;
	}

	private List<String> getConversations() {
		List<String> conversations = new ArrayList<String>();
		conversations.add("glob is I");
		conversations.add("prok is V");
		conversations.add("pish is X");
		conversations.add("tegj is L");
		conversations.add("glob glob Silver is 34 Credits");
		conversations.add("glob prok Gold is 57800 Credits");
		conversations.add("pish pish Iron is 3910 Credits");
		
		return conversations;
	}

}
