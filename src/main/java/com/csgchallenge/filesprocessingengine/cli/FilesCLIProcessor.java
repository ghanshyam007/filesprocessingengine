package com.csgchallenge.filesprocessingengine.cli;

import java.util.Scanner;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.csgchallenge.filesprocessingengine.ShutdownApplication;
import com.csgchallenge.filesprocessingengine.core.FileProcessingService;
import com.csgchallenge.filesprocessingengine.utility.FileDirectoryOperationUtility;

import io.micrometer.common.util.StringUtils;

/**
 * This is class to allow user to perform CLI operations providing various files for analysis.
 * 
 * @author Ghanshyam Baheti
 */
@Component
public class FilesCLIProcessor implements CommandLineRunner {

	private final FileProcessingService fileProcessingService;
	private final ShutdownApplication shutdownApplication;
	Scanner scanner = new Scanner(System.in);

	public FilesCLIProcessor(FileProcessingService fileProcessingService, ShutdownApplication shutdownApplication) {
		this.fileProcessingService = fileProcessingService;
		this.shutdownApplication = shutdownApplication;
	}

	@Override
	public void run(String... args) throws Exception {
		if (args.length > 0) {
			for (int i = 0; i < args.length; i++) {
				performFileProcessing(args[i]);
			}
		} 
		showMenu();
	}

	/**
	 * This is method showing Command Line Operation Menu for User.
	 */
	private void showMenu() {
		int option = 1;
		do {
			System.out.println("\n\nChoose an option: ");
			System.out.println("1. Perform an File Processing Operation. ");
			System.out.println("2. Exit. ");
			System.out.print("Your option: ");
			try {
				option = scanner.nextInt();
				scanner.nextLine(); // Consume the newline character
				switch (option) {
				case 1:
					performFileProcessing(null);
					break;
				case 2:
					shutdownApplication.shutdown();
					break;
				default:
					System.out.println("Invalid option. Please try again.");
				}
			} catch (Exception e) {
				System.out.println("Invalid information. Please try again."+e.getMessage());
				continue;
			}

		} while (option != 2);
	}

	private void performFileProcessing(String sourceDirectoryPath) {
		try {
			if (StringUtils.isBlank(sourceDirectoryPath)) {
				System.out.print("Enter Source Directory : ");
				sourceDirectoryPath = scanner.nextLine();
			}
			System.out.print("Enter Empty Directory for Processing "+sourceDirectoryPath +" Source File : ");
			String camelProcessingDirectoryPath = scanner.nextLine();
			if (FileDirectoryOperationUtility.createCamelProcessingDirectory(sourceDirectoryPath, camelProcessingDirectoryPath)) {
				try {
					fileProcessingService.startProcessing(camelProcessingDirectoryPath);
					Thread.sleep(10000);
				} catch (InterruptedException e) {
					System.out.println("Operation InterruptedException. Please try again."+e.getMessage());
				} catch (Exception e) {
					System.out.println("Operation failed. Please try again."+e.getMessage());
				}
			} else {
				System.out.println("Invalid information. Please try again.");
			}
		} catch (Exception e) {
			System.out.println("Invalid information. Please try again."+e.getMessage());
			System.exit(0);
		}
	}
}
