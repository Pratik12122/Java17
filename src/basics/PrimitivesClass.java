package basics;

import java.io.File;

public class PrimitivesClass {

	public static void main(StringClassExamples[] args) {
		int a = (int) 3.14;
		long l = 300/0;
		
		char ch = 'a';
		byte sh =  (byte) ch;
		
		Object obj = "s";
		StringClassExamples s = (StringClassExamples) obj;
		System.out.println(s);
		
		byte b = 102;
		byte c = 1;
		
		int aa = c + b + c;
	}

}
