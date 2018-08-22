/*
 * 
 */
package com.thoughtworks.assignment.main.merchantguide;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.thoughtworks.assignment.main.enums.RomanNumeralsCalculator;
import com.thoughtworks.assignment.main.util.GeneralInputValidator;

// TODO: Auto-generated Javadoc
/**
 * The Class ConversationMapper.
 * 
 * Purpose: This class's responsibility is to store, process or convert conversations
 * provided by merchant into roman numerals and integer forms. At the core, it distinguishes
 * conversations as two types: simple conversations (which are direct and provide direct mapping
 * of one symbol with one roman numeral) and complex conversations (which are indirect and include
 * credits as multiplicative factors along with symbols for roman numerals). This distinction is 
 * required since there is a difference in the way these symbols are used in calculations.
 *  
 * @author bburli
 */
/**
 * @author bburli
 *
 */
/**
 * @author bburli
 *
 */
/**
 * @author bburli
 *
 */
/**
 * @author bburli
 *
 */
public class ConversationMapper {
	
	/**  The conversations - master list storing all conversations *. */
	private List<String> conversations;
	
	/** The simple component map - stores one-to-one mapping of symbols roman numerals i.e. glob = I */
	private Map<String, String> simpleComponentMap = new HashMap<String, String>();
	
	/**  The complex component map - stores multiplicative credits sympbols like Silver, Gold, Iron. */
	private Map<String, Double> complexComponentMap = new HashMap<String, Double>();
	
	/**
	 * Check if the component read from query is a simple symbol.
	 * @param componentName - String supplied from query.
	 * @return boolean - true if the component name supplied is simple component otherwise false.
	 */
	public boolean isSimpleComponent(String componentName) {
		return this.simpleComponentMap.containsKey(componentName);
	}
	
	/**
	 * Check if the component read from query is a complex symbol.
	 * @param componentName - String supplied from query.
	 * @return boolean - true if the component name supplied is complex component otherwise false.
	 */
	public boolean isComplexComponent(String componentName) {
		return this.complexComponentMap.containsKey(componentName);
	}
	
	
	
	/**
	 * Gets the simple component value.
	 *
	 * @param componentName - String supplied from query.
	 * @return String - the value of simple component.
	 */
	public String getSimpleComponentValue(String componentName) {
		if (this.simpleComponentMap.containsKey(componentName)) {
			return this.simpleComponentMap.get(componentName);
		} else {
			System.out.println("Invalid component in Query!");
			return null;
		}
	}
	
	
	/**
	 * Gets the complex component value.
	 *
	 * @param componentName - String supplied from query.
	 * @return Double - the value of complex component;
	 */
	public Double getComplexComponentValue(String componentName) {
		if (this.complexComponentMap.containsKey(componentName)) {
			return this.complexComponentMap.get(componentName);
		} else {
			System.out.println("Invalid component in Query!");
			return -1.0;
		}
	}

	
	/**
	 * Process the read conversations as either simple or complex and prepare the corresponding mapping.
	 * 
	 * This method is invoked by merchant guide software after reading all conversations. This method,
	 * goes through each conversation and categorizes them as simple or complex. Simple conversations are
	 * direct mappings so will have only 3 words ('glob is I') and complex conversations have more than 3 words.
	 * Sentences having less than 3 words are not considered conversations and are not processed.
	 * 
	 * @param None
	 * @return None
	 */
	public void processConversations() {
		if (this.conversations != null && !this.conversations.isEmpty()) {
			for (String eachConversation : this.conversations) {
				String[] wordsInConversation = eachConversation.split(" ");
				if (wordsInConversation.length == 3) {
					processConversation(wordsInConversation, true);
				} else if (wordsInConversation.length > 3) {
					processConversation(wordsInConversation, false);
				} else {
					System.out.println("Too short for a conversation! Won't be considered. Conversation is: "+eachConversation);
				}
			}
		}
	}

	/**
	 * Process each conversation based on its type and prepare mapping to their values.
	 * 
	 * WARNING: This is a very important method. Any change in the method has to be tested thoroughly.
	 * 
	 * This method read each conversations, tokenizes them and maps the values of symbols.
	 * Simple Conversations are assumed to have below form:
	 * 
	 *  <key-roman> is <value>
	 *  
	 * Complex conversations are assumed to have below form:
	 * 
	 * 	<key-roman> <key-roman> <key-credits> is <value> credits
	 * 
	 * If not above, conversations need to have some structure to interpret values. This method serves the
	 * structure mentioned in above assumptions. It should be changed if the assumptions change.
	 * 
	 * @param wordsInConversation - Tokenized words in conversations. Complex conversations must have simple
	 * components that must have appeared before in the flow.
	 * @param isSimpleConversation - Boolean flag indicating parsing path.
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
			double value = 0.0;
			
			StringBuilder romanNumeral = new StringBuilder();
			
			String value1 = this.simpleComponentMap.get(wordsInConversation[0].trim());
			String value2 = this.simpleComponentMap.get(wordsInConversation[1].trim());
			
			
			if (!GeneralInputValidator.INSTANCE.isValidString(value1) 
					|| !GeneralInputValidator.INSTANCE.isValidString(value2)) {
				System.out.println("Invalid conversation.\n");
			}
			
			romanNumeral.append(value1).append(value2);
			
			long sum = RomanNumeralsCalculator.INSTANCE.convertRomanToArabic(romanNumeral.toString());
			long totalPerInput = Integer.parseInt(wordsInConversation[4].trim());
			key = wordsInConversation[2].trim();
			value = ((double) totalPerInput / (double) sum);
			this.complexComponentMap.put(key, value);
		}
	}

	/**
	 * Sets the conversations.
	 *
	 * @param conversations the new conversations
	 */
	public void setConversations(List<String> conversations) {
		this.conversations = conversations;	
	}
}

