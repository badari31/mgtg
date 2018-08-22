package com.thoughtworks.assignment.main.merchantguide;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.thoughtworks.assignment.main.enums.RomanNumeralsCalculator;
import com.thoughtworks.assignment.main.util.GeneralInputValidator;

/**
 * The Class MerchantGuide.
 * 
 * This is the main class. This class manages conversations through {@link ConversationMapper}.
 * The central functionality of this class is around query processing.
 */
public class MerchantGuide {
	
	/** The conversations. */
	private List<String> conversations;
	
	/** The conversation mapper. */
	private ConversationMapper conversationMapper = new ConversationMapper();
	
	/**
	 * @return
	 */
	public List<String> getConversations() {
		return conversations;
	}

	/**
	 * @param conversations
	 */
	public void setConversations(List<String> conversations) {
		this.conversations = conversations;
		this.conversationMapper.setConversations(conversations);
	}
	
	/**
	 * Sets the conversation mapper.
	 *
	 * @param conversationMapper the new conversation mapper. Mostly for Unit testing.
	 */
	public void setConversationMapper(ConversationMapper conversationMapper) {
		this.conversationMapper = conversationMapper;
	}

	/**
	 * Process query.
	 *
	 * @param query the query provided by merchant.
	 * @return the string - answer to the query.
	 */
	
	/*
	 * WARNING: This method assumes a structure to query. Structure is as below:
	 *     1. Words that need translation start after 'is' and end at '?'.
	 *     2. Every query has exactly one instance of tokens stated in point 1 i.e. there is only one 'is' and one '?'.
	 */
	public String processQuery(String query) {
		
		// If the questions is about credits, include credits in answer.
		boolean includeCreditsInAnswer = query.toLowerCase().contains("Credits".toLowerCase());
		
		StringBuilder answer = new StringBuilder();
		if (GeneralInputValidator.INSTANCE.isValidString(query)) {
			
			// index+3 because we have to ignore i,s and space in string 'is ' in query.
			String translatablePart = query.substring(query.indexOf("is")+3, query.indexOf("?"));
			String[] translatablePartComponents = translatablePart.split(" ");
			
			if (translatablePartComponents != null && translatablePartComponents.length > 0) {
				double sum = 0;
				StringBuilder romanNumeralBuilder = new StringBuilder();
				for (String eachComponent : translatablePartComponents) {
					if (conversationMapper.isSimpleComponent(eachComponent)) {
						romanNumeralBuilder.append(conversationMapper.getSimpleComponentValue(eachComponent));
					} else if (conversationMapper.isComplexComponent(eachComponent)) {
						// ASSUMPTION: Roman numerals are done. Multiply with existing roman numeral value for complex type.
						sum = RomanNumeralsCalculator
								.INSTANCE
								.convertRomanToArabic(
										romanNumeralBuilder.toString()
										) 
								* 
								conversationMapper
								.getComplexComponentValue(eachComponent);
					} else {
						sum = -1;
						break;
					}
				}
				
				if (sum == 0 && romanNumeralBuilder.length() > 0) {
					// This is the case where only roman numerals are present; no special symbols like Silver, Gold etc.
					sum = RomanNumeralsCalculator.INSTANCE.convertRomanToArabic(romanNumeralBuilder.toString());
				} 
				
				if (sum > 0) {
					
					for (String eachComponent : translatablePartComponents) {
						answer.append(eachComponent);
						answer.append(" ");
					}
					
					answer.append("is ");
					answer.append(sum);
					
					if (includeCreditsInAnswer)
						answer.append(" Credits");
				} else {
					return noIdea();
				}
			}
		} else {
			System.out.println("Invalid query. Must be non-empty string.\n");
		}
		
		return answer.toString();
	}

	

	private String noIdea() {
		return "I have no idea what you're talking about.";
	}

	/**
	 * Read conversations.
	 *
	 * @param numOfConversations the num of conversations
	 * @param scanner the scanner
	 */
	public void readConversations(int numOfConversations, Scanner scanner) {
		if (GeneralInputValidator.INSTANCE.isValidInteger(numOfConversations)) {
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
		processConversations();
	}
	
	/**
	 * 
	 */
	public void processConversations() {
		this.conversationMapper.processConversations();
	}
}
