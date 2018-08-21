package com.thoughtworks.assignment.main;

import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

public enum RomanNumeralsCalculator {

	INSTANCE;
	
	static class RomanNumeralValidator {
		private String romanNumeral;
		private boolean isValidRomanNumeral = true;
		
		public RomanNumeralValidator(String romanNumeral) {
			this.romanNumeral = romanNumeral;
		}
		
		RomanNumeralValidator validateConsecutiveRule() {
			Set<String> threeRepetitionSymbols = new HashSet<String>();
			threeRepetitionSymbols.add("I");
			threeRepetitionSymbols.add("X");
			threeRepetitionSymbols.add("C");
			threeRepetitionSymbols.add("M");
			
			Set<String> noRepetitionSymbols = new HashSet<String>();
			noRepetitionSymbols.add("V");
			noRepetitionSymbols.add("L");
			noRepetitionSymbols.add("D");

			boolean isConsecutive = true;
			
			for (String eachSymbol : threeRepetitionSymbols) {
				int index = romanNumeral.indexOf(eachSymbol);
				if (index != -1 && ((index+2) < romanNumeral.length()-1)) {
					String subString = romanNumeral.substring(index, index + 4);
					isConsecutive = subString.chars().distinct().count() == 1;
					if (isConsecutive) {
						this.isValidRomanNumeral = false;
						return this;
					}
				} 
			}
			
			
			for (String eachSymbol : noRepetitionSymbols) {
				int index = romanNumeral.indexOf(eachSymbol);
				if (index != -1 && ((index+1) < romanNumeral.length()-1)) {
					String subString = romanNumeral.substring(index, index + 2);
					isConsecutive = subString.chars().distinct().count() == 1;
					if (isConsecutive) {
						this.isValidRomanNumeral = false;
						return this;
					}
				} 
			}

			return this;
		}
		
		RomanNumeralValidator validateSubtractionRule() {
			if (this.isValidRomanNumeral) {
				if (this.romanNumeral.length() == 1) {
					this.isValidRomanNumeral = true;
					return this;
				}
				
				for (int i = 0; i < this.romanNumeral.length() - 1;) {
					char romanSymbol = this.romanNumeral.charAt(i);
					char nextRomanSymbol = this.romanNumeral.charAt(i + 1);

					if (romanSymbol == 'I'
							&& (nextRomanSymbol == 'I' || nextRomanSymbol == 'V' || nextRomanSymbol == 'X')) {
						this.isValidRomanNumeral = true;
						i += 2;
					} else if (romanSymbol == 'X'
							&& (nextRomanSymbol == 'X' || nextRomanSymbol == 'L' || nextRomanSymbol == 'C')) {
						this.isValidRomanNumeral = true;
						i += 2;
					} else if (romanSymbol == 'C'
							&& (nextRomanSymbol == 'C' || nextRomanSymbol == 'D' || nextRomanSymbol == 'M')) {
						this.isValidRomanNumeral = true;
						i += 2;
					} else if (romanSymbol == 'V' && (nextRomanSymbol == 'X' || nextRomanSymbol == 'C'
							|| nextRomanSymbol == 'L' || nextRomanSymbol == 'D' || nextRomanSymbol == 'M')) {
						this.isValidRomanNumeral = false;
						i++;
					} else if (romanSymbol == 'L'
							&& (nextRomanSymbol == 'C' || nextRomanSymbol == 'D' || nextRomanSymbol == 'M')) {
						this.isValidRomanNumeral = false;
						i++;
					} else if (romanSymbol == 'D' && nextRomanSymbol == 'M') {
						this.isValidRomanNumeral = false;
						i++;
					}
					
					i++;

				} 
			}
			
			return this;
		}
	}
	
	private Map<String, Integer> defaultMapping = new LinkedHashMap<String, Integer>();

	private RomanNumeralsCalculator() {
		if (defaultMapping.isEmpty()) {
			for (RomanNumerals each : RomanNumerals.getRomanNumerals()) {
				defaultMapping.put(each.toString(), each.getArabicNumeral());
			} 
		}
	}

	public long convertRomanToArabic(String romanNumeral) {
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
		
		return -1;
	}

	private boolean isValidRomanNumeral(String romanNumeral) {
		return new RomanNumeralsCalculator.RomanNumeralValidator(romanNumeral)
		.validateConsecutiveRule()
		.validateSubtractionRule()
		.isValidRomanNumeral;
	}
}
