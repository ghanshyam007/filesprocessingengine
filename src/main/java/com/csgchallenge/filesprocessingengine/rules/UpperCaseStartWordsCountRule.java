package com.csgchallenge.filesprocessingengine.rules;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * This is Business Rule class to Output the number of words that start with upper case letter in each file
 *  
 * @author Ghanshyam Baheti
 */
public class UpperCaseStartWordsCountRule implements BusinessRule {
	private final String ruleName = "RULE_UPPERCASE_START_WORDS_COUNT";

	@Override
	public String getRuleName() {
		return this.ruleName;
	}
	
	@Override
	public String applyRule(Map<String, Integer> words) {

		List<String> keysWithUppercaseFirstLetter = words.keySet().stream()
				.filter(key -> Character.isUpperCase(key.charAt(0))).collect(Collectors.toList());

		List<Integer> values = words.entrySet().stream()
				.filter(entry -> keysWithUppercaseFirstLetter.contains(entry.getKey())).map(Map.Entry::getValue)
				.collect(Collectors.toList());

		int sum = values.stream().mapToInt(Integer::intValue).sum();
		
		String outputMessage = String.format("%-40s%-30s", ruleName, ":: No of words starting with a capital letter: " + sum);
		return outputMessage;
	}
}
