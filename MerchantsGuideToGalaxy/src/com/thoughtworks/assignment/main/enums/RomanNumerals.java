package com.thoughtworks.assignment.main.enums;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * The Enum RomanNumerals.
 * 
 * There are many operations on Roman numerals in this problem. Thus, they need to have a representation of their own.
 * It is apt to have an enum with its integer value.
 */
public enum RomanNumerals implements Comparator<RomanNumerals> {

	 I(1), 
	 V(5), 
	 X(10),
	 L(50),
	 C(100),
	 D(500),
	 M(1000);
	
	/** The arabic numeral value. */
	private int arabicNumeralValue;
	
	/**
	 * Instantiates a new roman numerals.
	 *
	 * @param arabicNumeralValue the arabic numeral value
	 */
	private RomanNumerals(int arabicNumeralValue) {
		this.arabicNumeralValue = arabicNumeralValue;
	}
	
	/**
	 * Gets the arabic numeral.
	 *
	 * @return the arabic numeral
	 */
	int getArabicNumeral() {
		return this.arabicNumeralValue;
	}
	
	/**
	 * Gets sorted roman numerals according to their integer values. This sorting is important for subtraction rule of roman numerals.
	 *
	 * @return the sorted roman numerals List.
	 */
	public static List<RomanNumerals> getRomanNumerals() {
		List<RomanNumerals> romanNumerals = new ArrayList<RomanNumerals>();
		
		for (RomanNumerals eachNumeral : values()) {
			romanNumerals.add(eachNumeral);
		}
		
		Collections.sort(romanNumerals);
		return romanNumerals;
	}
	
	/* (non-Javadoc)
	 * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
	 */
	@Override
	public int compare(RomanNumerals o1, RomanNumerals o2) {
		return Integer.compare(o1.arabicNumeralValue, o2.arabicNumeralValue);
	}
}
