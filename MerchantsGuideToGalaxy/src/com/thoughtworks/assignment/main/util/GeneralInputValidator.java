package com.thoughtworks.assignment.main.util;

/**
 * The Enum GeneralInputValidator.
 * 
 * Singleton 
 * 
 * This class hold validation logic for generic input type like int and string.
 */
public enum GeneralInputValidator {

	/** The instance. */
	INSTANCE;
	
	/**
	 * Checks if is valid integer.
	 *
	 * @param numOfConversations the num of conversations
	 * @return true, if is valid integer
	 */
	public boolean isValidInteger(int numOfConversations) {
		return (numOfConversations > 0) ? true : false;
	}
	
	/**
	 * Checks if is valid string.
	 *
	 * @param string the string
	 * @return true, if is valid string
	 */
	public boolean isValidString(String string) {
		return (string != null && !string.isEmpty());
	}	
}
