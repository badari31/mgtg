package com.thoughtworks.assignment.main;

import java.io.File;
import java.util.InputMismatchException;
import java.util.Scanner;

import com.thoughtworks.assignment.main.merchantguide.MerchantGuide;
import com.thoughtworks.assignment.main.merchantguide.MerchantGuideInputData;
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
		MerchantGuide mGuide = new MerchantGuide();
		
		if (args != null && args.length == 1) {
			 // Run program using input file.
			File input = new File(args[0].trim());
			if (input.exists()) {
				MerchantGuideInputData inputData = new MerchantGuideInputData();
				inputData.readDataFromFile(input);
				mGuide.setConversations(inputData.getConversations());
				mGuide.processConversations();
				
				if (inputData.getQueries() != null && !inputData.getQueries().isEmpty()) {
					inputData.getQueries().stream().forEach(query -> System.out.println(mGuide.processQuery(query)));
				}
				
				return;
			}
		} else if (args.length > 1) {
			System.out.println("Usage: Only one argument to be specified as full path of file containing input.");
			return;
		}
		
		Scanner scanner = new Scanner(System.in);
		System.out.println("Hello! Welcome to your guide to Intergalactic trade!\n");
		
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
