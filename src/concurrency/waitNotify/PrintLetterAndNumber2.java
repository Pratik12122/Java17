package concurrency.waitNotify;

import java.util.Iterator;

public class PrintLetterAndNumber2 {

	public static void main(String[] args) {
		Printer printer = new Printer();
		LetterPrinter1 lp = new LetterPrinter1(printer);
		NumberPrinter1 np = new NumberPrinter1(printer);

		lp.start();
		np.start();
	}
}

class LetterPrinter1 extends Thread {
	private Printer printer;

	public LetterPrinter1(Printer printer) {
		super();
		this.printer = printer;
	}

	@Override
	public void run() {
		try {
			for (char ch = 'a'; ch <= 'z'; ch++) {
				printer.printLetter(ch);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

}

class NumberPrinter1 extends Thread {
	private Printer printer;

	public NumberPrinter1(Printer printer) {
		super();
		this.printer = printer;
	}

	@Override
	public void run() {
		try {
			for (int i = 1; i <= 26; i++) {
				printer.printNumber(i);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

}

class Printer {

	boolean isLetterTurn = true;

	public synchronized void printLetter(char ch) throws InterruptedException {
		if (!isLetterTurn) {
			wait();
		}

		System.out.print(ch);
		isLetterTurn = false;
		notify();
	}

	public synchronized void printNumber(int number) throws InterruptedException {
		if (isLetterTurn) {
			wait();
		}
		System.out.print(number);
		isLetterTurn = true;
		notify();
	}
}
