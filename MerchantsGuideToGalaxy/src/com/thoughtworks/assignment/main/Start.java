package com.thoughtworks.assignment.main;

import java.util.InputMismatchException;
import java.util.Scanner;

import com.thoughtworks.assignment.main.merchantguide.MerchantGuide;
import com.thoughtworks.assignment.main.util.GeneralInputValidator;

/**
 * The Class Start.
 *
 * @author bburli
 * Entry point for program. This is a console input starting point.
 */

public class Start {
	
	/** The Constant EXIT - to exit the program. **/
	private static final String EXIT = "exit";
	
	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Hello! Welcome to your guide to Intergalactic trade!\n");

		MerchantGuide mGuide = new MerchantGuide();
		
		System.out.println("Please enter the total number of conversation you have :\n");
		
		int numOfConversations = 0;
		
		try {
			numOfConversations = scanner.nextInt();
			scanner.nextLine();
			System.out.println("Please enter your conversations below one by one.\n");
			mGuide.readConversations(numOfConversations, scanner);
		} catch (InputMismatchException e) {
			System.out.println("Please enter a valid integer.\n");
		} 
		
		System.out.println("Great! Now, please enter your queries to be translated one by one. Type 'exit' to end query translation.\n");

		do {
			String query = scanner.nextLine();
			if (GeneralInputValidator.INSTANCE.isValidString(query)) {
				if (query.toLowerCase().equalsIgnoreCase(EXIT)) {
					break;
				}
			}
			
			String answer = mGuide.processQuery(query);
			if (answer != null && !answer.isEmpty())
				System.out.println("Query translation: "+answer+"\n");
			else
				System.out.println("Query translation: Invalid query :( ");
		} while(true);

		scanner.close();
	}	
}
