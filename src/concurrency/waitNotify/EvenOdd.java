package concurrency.waitNotify;

public class EvenOdd {
	public static void main(String[] args) throws InterruptedException {
		var envenOddPrinter = new EvenOddPrinter();
		Thread evenThread = new Thread(() -> {
			try {
				envenOddPrinter.printeven();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		});

		Thread oddThread = new Thread(() -> {
			try {
				envenOddPrinter.printOdd();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		});

		evenThread.setName("even ");
		oddThread.setName("odd ");
		
		evenThread.setDaemon(true);
		oddThread.setDaemon(true);
		
		
		evenThread.start();
		oddThread.start();
		
	}
}

class EvenOddPrinter {

	volatile int counter = 1;

	public synchronized void printOdd() throws InterruptedException {
		while (true) {
			if (counter % 2 == 0) {
				wait();
			} else {
				System.out.println("thread name : " + Thread.currentThread().getName() + counter++);
				//Thread.sleep(Duration.ofSeconds(2));
				notify();
			}
		}
	}

	public synchronized void printeven() throws InterruptedException {
		while (true) {
			if (counter % 2 != 0) {
				wait();
			} else {
				System.out.println("thread name : " + Thread.currentThread().getName() + counter++);
				//Thread.sleep(Duration.ofSeconds(2));
				notify();
			}
		}
	}
}
