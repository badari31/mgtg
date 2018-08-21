package com.thoughtworks.assignment.main;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public enum RomanNumerals implements Comparator<RomanNumerals>{

	I(1), V(5), X(10), L(50), C(100), D(500), M(1000);
	
	private int arabicNumeralValue;
	
	private RomanNumerals(int arabicNumeralValue) {
		this.arabicNumeralValue = arabicNumeralValue;
	}
	
	public static int getArabicNumeralFor(String romanChar) {
		for (RomanNumerals eachRomanNumeral : values()) {
			if (eachRomanNumeral.toString().equalsIgnoreCase(romanChar)) {
				return eachRomanNumeral.getArabicNumeral();
			}
		}
		
		return -1;
	}
	
	int getArabicNumeral() {
		return this.arabicNumeralValue;
	}
	
	public static List<RomanNumerals> getRomanNumerals() {
		List<RomanNumerals> romanNumerals = new ArrayList<RomanNumerals>();
		
		for (RomanNumerals eachNumeral : values()) {
			romanNumerals.add(eachNumeral);
		}
		
		Collections.sort(romanNumerals);
		return romanNumerals;
	}
	
	@Override
	public int compare(RomanNumerals o1, RomanNumerals o2) {
		return Integer.compare(o1.arabicNumeralValue, o2.arabicNumeralValue);
	}
}
