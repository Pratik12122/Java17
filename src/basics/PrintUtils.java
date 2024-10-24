package basics;

public class PrintUtils {
	static void printLine() {
		System.out.println();
		System.out.println("-----------------------------------------------------------------------------");
		System.out.println();
	}
	
	
	static void printLine(String s) {
		printLine();
		System.out.println(s);
	}
}


