package basics;

public class Test {

	public static void main(String[] args) {
		String s1 = new String("abc");
		String s2 = new String("abc");

		System.out.println(s1 == s2);
		
		s1 = s1.intern();
		s2 = s2.intern();
		
		s1.transform(s -> new StringBuilder(s).reverse().toString().toUpperCase());
	}

	static void op(boolean a, boolean b) {
		boolean c = a != b;
		boolean d = a ^ b;
		boolean e = c == d;
		System.out.println(e);
	}
}
