package com.csgchallenge.filesprocessingengine;

import org.apache.camel.CamelContext;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

/**
 * This is class to shutdown application in case of issues or User command.
 * 
 * @author Ghanshyam Baheti
 */
@Component
public class ShutdownApplication {

    private final CamelContext camelContext;
    private final ConfigurableApplicationContext applicationContext;

    public ShutdownApplication(CamelContext camelContext, ConfigurableApplicationContext applicationContext) {
        this.camelContext = camelContext;
        this.applicationContext = applicationContext;
    }

    public void shutdown() {
        try {
            // Shutdown Camel context
            if (camelContext != null) {
                camelContext.stop();
                System.out.println("Camel Context stopped successfully.");
            }

            // Shutdown Spring Boot application
            if (applicationContext != null) {
                SpringApplication.exit(applicationContext);
                System.out.println("Spring Boot application stopped successfully.");
            }
        } catch (Exception e) {
            System.err.println("Error during shutdown: " + e.getMessage());
        }
    }
}
