package streamAPI;

public class IterateString {
	public static void main(String[] args) {
		String s = "What a wonderful day it is !";

		iterateString(s);
	}

	static void iterateString(String s) {
		printLine();
		
		s.chars() // instream
				.forEach(System.out::print);
		
		printLine();
		
		s.chars().
			mapToObj(ch -> Character.valueOf((char)ch))
			.forEach(System.out::print);
	}
	
	
	
	static void printLine() {
		System.out.println();
		System.out.println("-----------------------------------------------------------------------------");
		System.out.println();
	}
}
