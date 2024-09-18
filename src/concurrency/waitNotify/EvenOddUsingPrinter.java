package concurrency.waitNotify;

class PrinterCoordinator {
	private volatile int turn = 1;

	public synchronized void waitTurn(int targetturn) throws InterruptedException {
		while (turn != targetturn) {
			wait();
		}
	}

	public synchronized void sinalNextturn(int nextturn) {
		turn = nextturn;
		notifyAll();
	}
}

class EvenNumberPrinter extends Thread {
	private PrinterCoordinator printerCoordinator;

	public EvenNumberPrinter(PrinterCoordinator printerCoordinator) {
		super();
		this.printerCoordinator = printerCoordinator;
	}

	@Override
	public void run() {
		for (int i = 1; i <= 100; i = i + 2) {
			try {
				printerCoordinator.waitTurn(1);
				System.out.println("thread " + this.getClass().getName() + " " + i);
				printerCoordinator.sinalNextturn(2);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}

class OddNumberPrinter extends Thread {
	private PrinterCoordinator printerCoordinator;

	public OddNumberPrinter(PrinterCoordinator printerCoordinator) {
		super();
		this.printerCoordinator = printerCoordinator;
	}

	@Override
	public void run() {
		for (int i = 2; i <= 100; i = i + 2) {
			try {
				printerCoordinator.waitTurn(2);
				System.out.println("thread " + this.getClass().getName() + " " + i);
				printerCoordinator.sinalNextturn(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}

public class EvenOddUsingPrinter {
	public static void main(String[] args) {
		var printCO = new PrinterCoordinator();
		var evenPrinter = new EvenNumberPrinter(printCO);
		var oddPrinter = new OddNumberPrinter(printCO);

		evenPrinter.start();
		oddPrinter.start();
		evenPrinter.start();
	}
}
