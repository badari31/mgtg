package com.thoughtworks.assignment.main;

public enum Choices {

	TEXT(1), QUERY(2), EXIT(3);
	
	private int choice;
	
	private Choices(int choice) {
		this.choice = choice;
	}
	
	public static boolean isValidChoice(int enteredChoice) {
		for (Choices eachValidChoice : values()) {
			if (eachValidChoice.equals(enteredChoice)) {
				return true;
			}
		}
		
		return false;
	}
	
	public int getChoiceValue() {
		return this.choice;
	}
	
}
