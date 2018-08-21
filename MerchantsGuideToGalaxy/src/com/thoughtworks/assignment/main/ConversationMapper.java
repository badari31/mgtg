package com.thoughtworks.assignment.main;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author bburli
 *
 */
public class ConversationMapper {
	private List<String> conversations;
	private Map<String, String> simpleComponentMap = new HashMap<String, String>();
	private Map<String, Integer> complexComponentMap = new HashMap<String, Integer>();

	public boolean isSimpleComponent(String componentName) {
		return this.simpleComponentMap.containsKey(componentName);
	}
	
	public boolean isComplexComponent(String componentName) {
		return this.complexComponentMap.containsKey(componentName);
	}
	
	public String getSimpleComponentValue(String componentName) {
		if (this.simpleComponentMap.containsKey(componentName)) {
			return this.simpleComponentMap.get(componentName);
		} else {
			System.out.println("Invalid component in Query!");
			return null;
		}
	}
	
	public int getComplexComponentValue(String componentName) {
		if (this.complexComponentMap.containsKey(componentName)) {
			return this.complexComponentMap.get(componentName);
		} else {
			System.out.println("Invalid component in Query!");
			return -1;
		}
	}

	public void processConversations() {
		if (this.conversations != null && !this.conversations.isEmpty()) {
			for (String eachConversation : this.conversations) {
				String[] wordsInConversation = eachConversation.split(" ");
				if (wordsInConversation.length == 3) {
					processConversation(wordsInConversation, true);
				} else {
					processConversation(wordsInConversation, false);
				}
			}
		}
	}

	/*
	 * WARNING: This method assumes a rigid conversation structure. Ordering and content of conversation is assumed to be fixed.
	 */
	
	private void processConversation(String[] wordsInConversation, boolean isSimpleConversation) {
		if (isSimpleConversation) {
			String key = null;
			String value = null;
			
			key = wordsInConversation[0].trim();
			value = wordsInConversation[2].trim();
			this.simpleComponentMap.put(key, value);
		} else {
			String key = null;
			Integer value = null;
			
			String value1 = this.simpleComponentMap.get(wordsInConversation[0].trim());
			String value2 = this.simpleComponentMap.get(wordsInConversation[1].trim());
			
			if (!GeneralInputValidator.INSTANCE.isValidString(value1) || !GeneralInputValidator.INSTANCE.isValidString(value2)) {
				System.out.println("Invalid conversation.\n");
			}
			
			long sum = RomanNumerals.getArabicNumeralFor(value1) + RomanNumerals.getArabicNumeralFor(value2);
			long totalPerInput = Integer.parseInt(wordsInConversation[4].trim());
			key = wordsInConversation[2].trim();
			value = (int) (totalPerInput / sum);
			this.complexComponentMap.put(key, value);
		}
	}

	public void setConversations(List<String> conversations) {
		this.conversations = conversations;	
	}
}

