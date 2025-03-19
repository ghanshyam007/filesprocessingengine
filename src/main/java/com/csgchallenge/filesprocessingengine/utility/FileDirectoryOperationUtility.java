package com.csgchallenge.filesprocessingengine.utility;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.StandardCopyOption;
import java.nio.file.attribute.BasicFileAttributes;

/**
 * This is utility class for doing all file/directory operations.
 * 
 * @author Ghanshyam Baheti
 */
public class FileDirectoryOperationUtility {

	public static boolean createCamelProcessingDirectory(String sourceDirectoryPath, String destinationDirectoryPath) {
		// Define the source and target directories
		Path sourceDir = Paths.get(sourceDirectoryPath);
		Path targetDir = Paths.get(destinationDirectoryPath);

		try {
			// Create the target directory if it doesn't exist
			if (!Files.exists(targetDir)) {
				Files.createDirectories(targetDir);
			}

			if (Files.isDirectory(sourceDir)) {
				// Walk the file tree and copy each file
				Files.walkFileTree(sourceDir, new SimpleFileVisitor<Path>() {
					@Override
					public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
						// Construct the target file path
						Path targetFile = targetDir.resolve(sourceDir.relativize(file));

						// Create parent directories for the target file if they don't exist
						Files.createDirectories(targetFile.getParent());

						// Copy the file
						Files.copy(file, targetFile, StandardCopyOption.REPLACE_EXISTING);

						//System.out.println("Copied: " + file + " to " + targetFile);

						return FileVisitResult.CONTINUE;
					}

					@Override
					public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
						System.err.println("Failed to copy file: " + file);
						return FileVisitResult.CONTINUE;
					}
				});
			} else {
				copyFileToDirectory(sourceDir, targetDir);
			}

			return true;

		} catch (IOException e) {
			System.err.println("Error copying files: " + e.getMessage());
		}
		return false;
	}

	private static void copyFileToDirectory(Path sourceFile, Path destinationDir) {
		try {
			// Resolve the destination file path by combining the destination directory and source file name
			Path destinationFile = destinationDir.resolve(sourceFile.getFileName());

			// Copy the file to the destination directory, replace the file if it exists
			Files.copy(sourceFile, destinationFile, StandardCopyOption.REPLACE_EXISTING);

			//System.out.println("File copied successfully from " + sourceFile + " to " + destinationFile);

		} catch (Exception e) {
			System.err.println("Error copying file: " + e.getMessage());
		}
	}
}
