package com.thoughtworks.assignment.main;

import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

public class RomanNumeralsCalculator {

	private static Map<String, Integer> defaultMapping = new LinkedHashMap<String, Integer>();
	private static final int MAX_ALLOWED_CONSECUTIVE_OCCURENCE = 3;

	private static void initialize() {
		for (RomanNumerals each: RomanNumerals.getRomanNumerals()) {
			defaultMapping.put(each.toString(), each.getArabicNumeral());
		}
	}

	public static long convertRomanToArabic(String romanNumeral) {
		if (defaultMapping.isEmpty())
			initialize();
		
		if (isValidRomanNumeral(romanNumeral)) {
			long sum = 0;
			for (int i = 0; i < romanNumeral.length();) {
				String valueAtI = String.valueOf(romanNumeral.charAt(i));
				String valueAtIPlusOne = null;
				if (i != romanNumeral.length()-1) {
					valueAtIPlusOne = String.valueOf(romanNumeral.charAt(i + 1));
				}

				int value = 0;

				if (valueAtIPlusOne != null && (defaultMapping.get(valueAtI) < defaultMapping.get(valueAtIPlusOne))) {
					// Subtraction case
					value = defaultMapping.get(valueAtIPlusOne) - defaultMapping.get(valueAtI);
					i += 2;
				} else {
					// Normal case
					value = defaultMapping.get(valueAtI);
					i++;
				}

				sum += value;
			}
			
			return sum;
		} else {
			System.out.println("Invalid roman numeral. Fails to satisfy one of the rules.");
		}
		
		return 0;
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

	private static boolean isValidRomanNumeral(String romanNumeral) {
		boolean validRomanNumeral = true;

		validRomanNumeral &= GeneralInputValidator.INSTANCE.isValidString(romanNumeral);
		validRomanNumeral &= isConsecutiveRuleSatisfied(romanNumeral);
		validRomanNumeral &= isSubtractionRuleSatisfied(romanNumeral);

		return validRomanNumeral;
	}

	private static boolean isSubtractionRuleSatisfied(String romanNumeral) {
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

	private static boolean isConsecutiveRuleSatisfied(String romanNumeral) {
		Set<String> applicableSymbols = new HashSet<String>();
		applicableSymbols.add("I");
		applicableSymbols.add("X");
		applicableSymbols.add("C");
		applicableSymbols.add("M");

		if (romanNumeral.length() <= MAX_ALLOWED_CONSECUTIVE_OCCURENCE)
			return true;

		boolean isConsecutive = true;

		for (String eachApplicableSymbol : applicableSymbols) {
			int index = romanNumeral.indexOf(eachApplicableSymbol);
			if (index != -1) {
				String subString = romanNumeral.substring(index, index + 2);
				isConsecutive = subString.chars().distinct().count() == 1;
				if (isConsecutive)
					return true;
			}
		}

		return false;
	}

}
