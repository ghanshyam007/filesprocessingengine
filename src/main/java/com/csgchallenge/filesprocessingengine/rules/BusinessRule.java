package com.csgchallenge.filesprocessingengine.rules;

import java.util.Map;

import org.springframework.stereotype.Component;

/**
 * This is Interface each Business Rule Implemtation class should implement.
 * 
 * @author Ghanshyam Baheti
 */
@Component
public interface BusinessRule {
	
	/**
	 * This Method gives Business Rule name.
	 * @param 
	 * @return String
	 */
	String getRuleName();
	
	/**
	 * This Method applies rule on given indexed information and provides response message.
	 * @param words
	 * @return String
	 */
	String applyRule(Map<String, Integer> words);
}