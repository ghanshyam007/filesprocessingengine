package com.csgchallenge.filesprocessingengine.processor;

import java.util.Map;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.stereotype.Component;

import com.csgchallenge.filesprocessingengine.rules.BusinessRulesEngine;

/**
 * This is class to process indexed file information from previous processor and trigger business rules processing steps using BusinessRulesEngine.
 * 
 * @author Ghanshyam Baheti
 */
@Component
public class BuisnessRulesProcessor implements Processor {

	@Override
	public void process(Exchange exchange) throws Exception {
		// Get the File name from exchange
		String fileName = exchange.getIn().getHeader(Exchange.FILE_NAME, String.class);

		// Get the indexed words map from the previous processor
		Map<String, Integer> wordIndex = exchange.getIn().getBody(Map.class);

		// Count the total number of unique words (keys in the map)
		int totalWordCount = wordIndex.size();

		// Optionally, you can also count the total occurrences of words (sum the
		// values)
		int totalOccurrences = wordIndex.values().stream().mapToInt(Integer::intValue).sum();

		System.out.println("\n----------------------------------------------------------------------------------------------------------------------------");
        System.out.println("\t\t\t\t\t\t\t\t File Name: " + fileName+"\t\t\t\t\t\t\t\t");
        System.out.println("----------------------------------------------------------------------------------------------------------------------------");
		String result = String.format("\nTotal unique words: %d, Total word occurrences: %d", totalWordCount,
				totalOccurrences);
		BusinessRulesEngine businessRulesEngine = new BusinessRulesEngine();
		businessRulesEngine.applyRules(wordIndex);

		// Set the result as the body of the exchange
		exchange.getIn().setBody(result);

	}

}
