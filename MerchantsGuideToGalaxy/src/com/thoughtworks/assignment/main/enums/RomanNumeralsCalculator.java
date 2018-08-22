package com.thoughtworks.assignment.main.enums;

import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

/**
 * The Enum RomanNumeralsCalculator.
 * 
 * Singleton
 * 
 * This class has the responsibility to perform all manipulations with roman numerals. It also has an inner class for validations.
 */
public enum RomanNumeralsCalculator {

	/** The instance. */
	INSTANCE;
	
	/**
	 * Inner Class
	 * 
	 * The Class RomanNumeralValidator.
	 * 
	 * This class deals with all roman numeral related validations. Specifically there are two:
	 * 1. Consecutive rule
	 * 2. Subtraction rule
	 */
	static class RomanNumeralValidator {
		
		/** The roman numeral to be validated. */
		private String romanNumeral;
		
		/** The is valid roman numeral. */
		private boolean isValidRomanNumeral = true;
		
		/**
		 * Instantiates a new roman numeral validator.
		 *
		 * @param romanNumeral the roman numeral
		 */
		public RomanNumeralValidator(String romanNumeral) {
			this.romanNumeral = romanNumeral;
		}
		
		/**
		 * Validate consecutive rule. I,X,C,M can occur thrice in succession. V,L,D cannot repeat.
		 *
		 * @return the roman numeral validator
		 */
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
				if (index != -1 && ((index+2) <= romanNumeral.length()-1)) {
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
				if (index != -1 && ((index+1) <= romanNumeral.length()-1)) {
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
		
		/**
		 * Validate subtraction rule. Lesser values roman numerals should be followed by larger valued roman numerals.
		 *
		 * @return the roman numeral validator
		 */
		RomanNumeralValidator validateSubtractionRule() {
			if (this.isValidRomanNumeral) {
				if (this.romanNumeral.length() == 1) {
					this.isValidRomanNumeral = true;
					return this;
				}
				
				for (int i = 0; i < this.romanNumeral.length() - 1;) {
					char romanSymbol = this.romanNumeral.charAt(i);
					char nextRomanSymbol = this.romanNumeral.charAt(i + 1);

					// Increment index by 2 for valid cases and 1 for invalid cases.
					
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
	
	/** The default mapping. */
	private Map<String, Integer> defaultMapping = new LinkedHashMap<String, Integer>();

	/**
	 * Instantiates a new roman numerals calculator. Depends on order of roman numerals provided by {@link RomanNumerals enum}}
	 */
	private RomanNumeralsCalculator() {
		if (defaultMapping.isEmpty()) {
			for (RomanNumerals each : RomanNumerals.getRomanNumerals()) {
				defaultMapping.put(each.toString(), each.getArabicNumeral());
			} 
		}
	}

	/**
	 * Convert roman to arabic.
	 *
	 * 1. Verify validity of roman numeral.
	 * 2. Identify if it is a subtraction case or not.
	 * 3. Compute by using values from defaultMapping map.
	 *
	 * @param romanNumeral the roman numeral
	 * @return the long - arabic number value of roman numeral
	 */
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
		} 
		
		return -1;
	}

	/**
	 * Checks if is valid roman numeral.
	 *
	 * @param romanNumeral the roman numeral
	 * @return true, if is valid roman numeral
	 */
	private boolean isValidRomanNumeral(String romanNumeral) {
		return new RomanNumeralsCalculator.RomanNumeralValidator(romanNumeral)
		.validateConsecutiveRule()
		.validateSubtractionRule()
		.isValidRomanNumeral;
	}
}
