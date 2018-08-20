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
	private Map<String, Integer> conversationComponentMap = new HashMap<String, Integer>();

	public int getValueFor(String componentName) {
		if (this.conversationComponentMap.containsKey(componentName)) {
			return this.conversationComponentMap.get(componentName);
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
		String key = null;
		int value = 0;

		if (isSimpleConversation) {
			key = wordsInConversation[0].trim();
			value = RomanNumerals.getRomanNumeralValue(wordsInConversation[2].trim());
			
			if (value == -1) {
				System.out.println("Invalid conversation.\n");
			}

			this.conversationComponentMap.put(key, value);
		} else {
			Integer value1 = this.conversationComponentMap.get(wordsInConversation[0].trim());
			Integer value2 = this.conversationComponentMap.get(wordsInConversation[1].trim());
			
			if (value1 == null || value2 == null) {
				System.out.println("Invalid conversation.\n");
			}
			
			long sum = value1 + value2;
			long totalPerInput = Integer.parseInt(wordsInConversation[4].trim());
			key = wordsInConversation[2].trim();
			value = (int) (totalPerInput / sum);
			this.conversationComponentMap.put(key, value);
		}
	}

	public void setConversations(List<String> conversations) {
		this.conversations = conversations;	
	}
}

