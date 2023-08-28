package io.character.streams;

import java.io.File;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;

public class PathsExamples {
	public static void main(String[] args) {
		// waysToCreatePathObjects();
		//InteroperabilityFileAndPaths();
		
	}

	private static void InteroperabilityFileAndPaths() {
		Path tempPath = Path.of("c:", "temp");
		File tempFile = tempPath.toFile();
		System.out.println("tempFile " + tempFile);
		System.out.println("tempPath " + tempFile.toPath());
	}

	public static void waysToCreatePathObjects() {

		// using Path.Of <interface> method
		Path temp = Path.of("c:", "temp");
		Path temp1 = Path.of("c:\\temp");
		System.out.println(temp);

		// Using Paths Class
		Path temp3 = Paths.get("c:", "temp");
		System.out.println(temp3);

		// using fileSystem
		FileSystem dfs = FileSystems.getDefault();
		Path temp4 = dfs.getPath("c:", "temp");
		System.out.println(temp4);
	}
}
