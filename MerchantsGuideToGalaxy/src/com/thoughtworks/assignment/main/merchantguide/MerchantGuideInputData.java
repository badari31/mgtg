package com.thoughtworks.assignment.main.merchantguide;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MerchantGuideInputData {

	private List<String> conversations = new ArrayList<String>();
	private List<String> queries = new ArrayList<String>();
	
	/**
	 * @return
	 */
	public List<String> getConversations() {
		return conversations;
	}

	/**
	 * @return
	 */
	public List<String> getQueries() {
		return queries;
	}
	
	public void readDataFromFile(File input) {
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(input));
			String line = null;
			
			while ((line = br.readLine()) != null) {
				if (!line.isEmpty() && isQuery(line)) {
					this.queries.add(line);
				} else {
					this.conversations.add(line);
				}
			}
		} catch (Exception e) {
			System.out.println("Exception occured while reading from input file: "+e.getMessage());
			System.exit(1);
		} finally {
			try {
				br.close();
			} catch (IOException e) {
				System.out.println("Exception while close the input stream: "+e.getMessage());
				System.exit(1);
			}
		}
		
	}

	private boolean isQuery(String line) {
		// ASSUMPTION: Queries start with questions asking a quantitative output
		return (line.startsWith("how") || line.startsWith("what"));
	}

	
	
}
