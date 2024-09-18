package concurrency.waitNotify;

class PrintOrder {
	private int turn = 1; // 1 for lowercase, 2 for number, 3 for uppercase

	public synchronized void waitTurn(int targetTurn) throws InterruptedException {
		while (turn != targetTurn) {
			wait(); // Wait until it's the thread's turn
		}
	}

	public synchronized void signalNextTurn(int nextTurn) {
		turn = nextTurn;
		notifyAll(); // Notify all threads to check whose turn it is
	}
}

class LowercasePrinter extends Thread {
	private final PrintOrder printOrder;

	public LowercasePrinter(PrintOrder printOrder) {
		this.printOrder = printOrder;
	}

	@Override
	public void run() {
		for (char letter = 'a'; letter <= 'z'; letter++) {
			try {
				printOrder.waitTurn(1); // Wait for the turn to print lowercase
				System.out.print(letter);
				printOrder.signalNextTurn(2); // Signal the number thread's turn
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
			}
		}
	}
}

class NumberPrinter11 extends Thread {
	private final PrintOrder printOrder;

	public NumberPrinter11(PrintOrder printOrder) {
		this.printOrder = printOrder;
	}

	@Override
	public void run() {
		for (int number = 1; number <= 26; number++) {
			try {
				printOrder.waitTurn(2); // Wait for the turn to print number
				System.out.print(number);
				printOrder.signalNextTurn(3); // Signal the uppercase thread's turn
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
			}
		}
	}
}

class UppercasePrinter extends Thread {
	private final PrintOrder printOrder;

	public UppercasePrinter(PrintOrder printOrder) {
		this.printOrder = printOrder;
	}

	@Override
	public void run() {
		for (char letter = 'A'; letter <= 'Z'; letter++) {
			try {
				printOrder.waitTurn(3); // Wait for the turn to print uppercase
				System.out.print(letter);
				printOrder.signalNextTurn(1); // Signal the lowercase thread's turn
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
			}
		}
	}
}

public class Printa1A {
	public static void main(String[] args) {
		PrintOrder printOrder = new PrintOrder();

		LowercasePrinter lowercasePrinter = new LowercasePrinter(printOrder);
		NumberPrinter11 numberPrinter = new NumberPrinter11(printOrder);
		UppercasePrinter uppercasePrinter = new UppercasePrinter(printOrder);

		lowercasePrinter.start();
		numberPrinter.start();
		uppercasePrinter.start();
	}
}
