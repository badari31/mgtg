package com.thoughtworks.assignment.main;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

public class RomanNumerals {

	private static Map<String, Integer> defaultMapping = new LinkedHashMap<String, Integer>();
	private static final int MAX_ALLOWED_CONSECUTIVE_OCCURENCE = 3;

	private static void initialize() {
		defaultMapping.put("I", 1);
		defaultMapping.put("V", 5);
		defaultMapping.put("X", 10);
		defaultMapping.put("L", 50);
		defaultMapping.put("C", 100);
		defaultMapping.put("D", 500);
		defaultMapping.put("M", 1000);
	}
	
	public static int getRomanNumeralValue(String key) {
		if (defaultMapping.isEmpty())
			initialize();
		
		if (defaultMapping.containsKey(key)) {
			return defaultMapping.get(key);
		} else {
			return -1;
		}
	}

	private boolean isValidRomanNumeral(String romanNumeral) {
		boolean validRomanNumeral = true;

		validRomanNumeral &= GeneralInputValidator.INSTANCE.isValidString(romanNumeral);
		validRomanNumeral &= isConsecutiveRuleSatisfied(romanNumeral);
		validRomanNumeral &= isSubtractionRuleSatisfied(romanNumeral);

		return validRomanNumeral;
	}

	private boolean isSubtractionRuleSatisfied(String romanNumeral) {
		if (romanNumeral.length() == 1)
			return true;
		
		for (int i = 0 ; i < romanNumeral.length()-1 ; i++) {
			char romanSymbol = romanNumeral.charAt(i);
			char nextRomanSymbol = romanNumeral.charAt(i+1);
			
			if (romanSymbol == 'I' && (nextRomanSymbol == 'I' || nextRomanSymbol == 'V' || nextRomanSymbol == 'X')) {
				return true;
			} else if (romanSymbol == 'X' && (nextRomanSymbol == 'X' || nextRomanSymbol == 'L' || nextRomanSymbol == 'C')) {
				return true;
			} else if (romanSymbol == 'C' && (nextRomanSymbol == 'C' || nextRomanSymbol == 'D' || nextRomanSymbol == 'M')) {
				return true;
			} else if (romanSymbol == 'V' && (nextRomanSymbol == 'X' || nextRomanSymbol == 'C' || nextRomanSymbol == 'L' 
					|| nextRomanSymbol == 'D' || nextRomanSymbol == 'M')) {
				return false;
			} else if (romanSymbol == 'L' && (nextRomanSymbol == 'C' || nextRomanSymbol == 'D' || nextRomanSymbol == 'M')) {
				return false;
			} else if (romanSymbol == 'D' && nextRomanSymbol == 'M') {
				return false;
			}
			
		}
		
		return true;
	}

	private boolean isConsecutiveRuleSatisfied(String romanNumeral) {
		Set<String> applicableSymbols = Collections.<String>emptySet();
		applicableSymbols.add("I");
		applicableSymbols.add("X");
		applicableSymbols.add("C");
		applicableSymbols.add("M");

		if (romanNumeral.length() <= MAX_ALLOWED_CONSECUTIVE_OCCURENCE)
			return true;

		boolean isConsecutive = true;

		for (String eachApplicableSymbol : applicableSymbols) {
			int index = romanNumeral.indexOf(eachApplicableSymbol);
			String subString = romanNumeral.substring(index, index + 2);
			isConsecutive = subString.chars().distinct().count() == 1;

			if (isConsecutive)
				return true;
		}

		return false;
	}

}
