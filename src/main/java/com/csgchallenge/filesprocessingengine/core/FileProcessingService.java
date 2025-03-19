package com.csgchallenge.filesprocessingengine.core;

import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Service;

import com.csgchallenge.filesprocessingengine.processor.BuisnessRulesProcessor;
import com.csgchallenge.filesprocessingengine.processor.FileIndexProcessor;

/**
 * This is core service that does step by step processing on file and calls
 * processors to perform various operations - ReadFile, File Indexing, Business
 * Rules Processing, etc.
 * 
 * @author Ghanshyam Baheti
 */
@Service
public class FileProcessingService {

	private final CamelContext camelContext;

	private FileIndexProcessor fileIndexProcessor;

	private BuisnessRulesProcessor buisnessRulesProcessor;

	public FileProcessingService(CamelContext camelContext, FileIndexProcessor fileIndexProcessor,
			BuisnessRulesProcessor buisnessRulesProcessor) {
		this.camelContext = camelContext;
		this.fileIndexProcessor = fileIndexProcessor;
		this.buisnessRulesProcessor = buisnessRulesProcessor;
	}

	public void startProcessing(String processingDirectory) {
		// Start Camel routing and file processing
		try {
			camelContext.addRoutes(new RouteBuilder() {
				@Override
				public void configure() throws Exception {
					from("file:" + processingDirectory + "?delete=false").routeId("file-processing-route")
							// .log("Processing file: ${header.CamelFileName}")

							// First Processor: Index the file
							.process(fileIndexProcessor)

							// Second Processor: Trigger Business Rules
							.process(buisnessRulesProcessor);

					// .log("Word counts: ${body}");
					// .to("file:"+destinationDirectory);
				}
			});
			camelContext.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
