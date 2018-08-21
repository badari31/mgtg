package com.thoughtworks.assignment.main;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MerchantGuide {

	private List<String> conversations;
	private ConversationMapper conversationMapper = new ConversationMapper();
	
	
	/*
	 * WARNING: This method assumes a structure to query. Structure is as below:
	 *     1. Words that need translation start after 'is' and end at '?'.
	 *     2. Every query has exactly one instance of tokens stated in point 1.
	 */
	public String processQuery(String query) {
		StringBuilder answer = new StringBuilder();
		if (GeneralInputValidator.INSTANCE.isValidString(query)) {
			String translatablePart = query.substring(query.indexOf("is")+3, query.indexOf("?"));
			String[] translatablePartComponents = translatablePart.split(" ");
			if (translatablePartComponents != null && translatablePartComponents.length > 0) {
				long sum = 0;
				StringBuilder romanNumeralBuilder = new StringBuilder();
				for (String eachComponent : translatablePartComponents) {
					if (conversationMapper.isSimpleComponent(eachComponent)) {
						romanNumeralBuilder.append(conversationMapper.getSimpleComponentValue(eachComponent));
					} else if (conversationMapper.isComplexComponent(eachComponent)) {
						// ASSUMPTION: Roman numerals are done. Multiple with existing roman numeral value for complex type.
						sum = RomanNumeralsCalculator.convertRomanToArabic(romanNumeralBuilder.toString()) * 
								conversationMapper.getComplexComponentValue(eachComponent);
					} else {
						answer.append("I have no idea what you're talking about.\n");
						return answer.toString();
					}
				}
				
				if (sum == 0 && romanNumeralBuilder.length() > 0) {
					// Only roman numerals; no special symbols like Silver, Gold etc.
					sum = RomanNumeralsCalculator.convertRomanToArabic(romanNumeralBuilder.toString());
				}
				
				if (sum > 0) {
					
					for (String eachComponent : translatablePartComponents) {
						answer.append(eachComponent);
						answer.append(" ");
					}
					
					answer.append("is ");
					answer.append(sum);
				}
			}
		} else {
			System.out.println("Invalid query. Must be non-empty string.\n");
		}
		
		return answer.toString();
	}

	

	public void readConversations(int numOfConversations, Scanner scanner) {
		if (GeneralInputValidator.INSTANCE.isValidNumOfConversations(numOfConversations)) {
			this.conversations = new ArrayList<String>(numOfConversations);

			for (int i = 0; i < numOfConversations;) {
				String conversation = scanner.nextLine();
				if (GeneralInputValidator.INSTANCE.isValidString(conversation)) {
					this.conversations.add(conversation);
					i++;
					System.out.println("Conversation added!\n");
				} else {
					System.out.println("Invalid conversation. Must be non-empty string.\n");
				}
			}
			
			System.out.println(this.conversations.size()+" conversations read successfully.\n"); 
		} else {
			System.out.println("Invalid entry for numOfConversations. Must be a positive integer.\n");
		}
		
		conversationMapper.setConversations(this.conversations);
		conversationMapper.processConversations();
	}
}
