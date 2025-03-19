package com.csgchallenge.filesprocessingengine.rules;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * This is Business Rule class to List all the words that are longer than 5 characters long in each file 
 *  
 * @author Ghanshyam Baheti
 */
public class MinLengthWordsListRule implements BusinessRule {

	// This is constant but should be configurable in next iteration if business rule allows configurable word length
	private int WORLD_LENGTH = 5;
	
	private final String ruleName = "RULE_MIN_LENGTH_WORDS";

	@Override
	public String getRuleName() {
		return this.ruleName;
	}
	
	@Override
	public String applyRule(Map<String, Integer> words) {

		List<String> keysWithExpectedWordLength = words.keySet().stream().filter(key -> key.length() > WORLD_LENGTH)
				.collect(Collectors.toList());

		String outputMessage = String.format("%-40s%-30s", ruleName, ":: No of words with length more than 5 are : " + keysWithExpectedWordLength.toString());
		return outputMessage;
	}
	
}
