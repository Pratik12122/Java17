package concurrency.waitNotify;

public class PrintLetterAndNumbers {

	volatile boolean isNumberPrinting = false;

	public static void main(String[] args) {
		PrintLetterAndNumbers object = new PrintLetterAndNumbers();

		LetterPrinter lp = new LetterPrinter(object);
		NumberPrinter np = new NumberPrinter(object);

		lp.start();
		np.start();

	}
}

class LetterPrinter extends Thread {
	char letter = 'a';
	PrintLetterAndNumbers object;

	public LetterPrinter(PrintLetterAndNumbers object) {
		this.object = object;
	}

	@Override
	public void run() {
		while (letter <= 'z') {
			try {
				synchronized (object) {
					if (!object.isNumberPrinting) {
						System.out.println(letter++);
						object.isNumberPrinting = true;
					}
				}
			} catch (Exception e) {
			}
		}
		System.out.println("LP thread Ended");
	}

}

class NumberPrinter extends Thread {
	int number = 0;
	PrintLetterAndNumbers object;

	public NumberPrinter(PrintLetterAndNumbers object) {
		this.object = object;
	}

	@Override
	public void run() {
		while (number < 26) {
			try {
				synchronized (object) {
					if (object.isNumberPrinting) {
						System.out.println(number++);
						object.isNumberPrinting = false;
					}
				}
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		System.out.println("NP thread Ended");
	}

}
