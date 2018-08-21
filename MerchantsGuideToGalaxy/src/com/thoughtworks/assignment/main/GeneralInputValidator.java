package com.thoughtworks.assignment.main;

public enum GeneralInputValidator {

	INSTANCE;
	
	public boolean isValidInteger(int numOfConversations) {
		return (numOfConversations > 0) ? true : false;
	}
	
	public boolean isValidString(String string) {
		return (string != null && !string.isEmpty());
	}	
}
