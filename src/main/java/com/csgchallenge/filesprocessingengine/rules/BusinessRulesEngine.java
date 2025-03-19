package com.csgchallenge.filesprocessingengine.rules;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import io.micrometer.common.util.StringUtils;

/**
 * This is Rules Engine to Add New Rules and trigger them on indexed file information.
 * 
 * This class has to be used for adding new rules in Future OR adopting other Rules Technology like Drools.
 * 
 * @author Ghanshyam Baheti
 */
@Component
public class BusinessRulesEngine {

	// It holds all rules to be applied
	private Map<String, BusinessRule> rules = null;
	
	// It holds all response message to be shown to user
	private List<String> outputMessages = null;

	/**
	 * This is Method configuring predefined rules and if any new rule has to be added later on other method addExternalBusinessRules should be used.
	 */
	public BusinessRulesEngine() {
		this.rules = new HashMap<>();
		UpperCaseStartWordsCountRule upperCaseStartWordsCountRule = new UpperCaseStartWordsCountRule();
		rules.put(upperCaseStartWordsCountRule.getRuleName(), upperCaseStartWordsCountRule);
		MinLengthWordsListRule minLengthWordsListRule = new MinLengthWordsListRule();
		rules.put(minLengthWordsListRule.getRuleName(), minLengthWordsListRule);
		this.outputMessages = new ArrayList<>();
	}
	
	/**
	 * This is Method to be used by other systems in Organization to add new Rules if not to be included in this system.
	 */
	public boolean addExternalBusinessRules(BusinessRule rule) {
		String ruleName = rule.getRuleName();
		if(rule != null && StringUtils.isNotBlank(ruleName) && !rules.keySet().contains(ruleName)) {
			this.rules.put(rule.getRuleName(), rule);
			return true;
		}else {
			System.err.println("Invalid Rule : Rule cannot be added ");
		}
		return false;
	}

	/**
	 * This is Method to be used to apply all the rules on indexed data step by step.
	 */
	public void applyRules(Map<String, Integer> words) {
		// Apply simple rules logic, We could use Drools/AI rules in future to file content
		for (String rule : rules.keySet()) {
			outputMessages.add(rules.get(rule).applyRule(words));
		}
		outputMessages.stream().forEach(s-> System.out.println(s));
	}
}
