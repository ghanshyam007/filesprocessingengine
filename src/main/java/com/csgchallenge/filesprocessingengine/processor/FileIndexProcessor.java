package com.csgchallenge.filesprocessingengine.processor;

import java.util.HashMap;
import java.util.Map;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.stereotype.Component;

/**
 * This is class to process file to create indexed data before passing information to next processor
 * 
 * @author Ghanshyam Baheti
 */
@Component
public class FileIndexProcessor implements Processor {

	@Override
	public void process(Exchange exchange) throws Exception {
            
			// Get file content as a string
            String content = exchange.getIn().getBody(String.class);
            
            // Index the words in a map (word -> count)
            Map<String, Integer> wordIndex = new HashMap<>();
            
            // Split content into words (basic split by spaces, you can customize this as needed)
            String[] words = content.split("\\W+"); // Split by non-word characters
            
            for (String word : words) {
                if (word.length() > 0) {
                    wordIndex.put(word, wordIndex.getOrDefault(word, 0) + 1);
                }
            }
            
            // Set indexed words map as the body of the exchange
            exchange.getIn().setBody(wordIndex);
        }
    }
