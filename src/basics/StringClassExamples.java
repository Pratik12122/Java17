package basics;

import java.util.stream.Collectors;

public class StringClassExamples {

	public static void main(String[] args) {
		strip();
	}

	// java-11
	private static void strip() {
		// \u0020 is not considered whitespace by trim()
		Character c = '\u2000';
		String s = c + "abc" + c;
		System.out.println(s.trim().equals(s.strip()));

	}

	// java 12
	private static void transform() {
		String alphabet = "abcdefghijklmnopqrstuvwxyz";

		// Find all letters that have an even
		// int representation and join the results
		// back into a string.
		String letters = alphabet.transform(s -> s.chars().filter(n -> n % 2 == 0)
				.mapToObj(n -> String.valueOf((char) n)).collect(Collectors.joining()));

		System.out.println(letters);
	}

	// Java 15
	static void textBlock() {
		String sql1 = """
				SELECT *
				FROM Programmers
				WHERE Language = 'Java';
				""";
		System.out.println(sql1);
	}
}
