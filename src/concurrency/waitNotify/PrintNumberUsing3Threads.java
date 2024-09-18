package concurrency.waitNotify;

import java.time.Duration;

import concurrency.ThreadUtils;

public class PrintNumberUsing3Threads {
	public static void main(String[] args) {
		var printer1809 = new Printer1809();
		var t1 = new T1(printer1809);
		var t2 = new T2(printer1809);
		var t3 = new T3(printer1809);

		t1.start();
		t2.start();
		t3.start();
	}
}

class T1 extends Thread {
	private int id = 1;

	private Printer1809 printer;

	public T1(Printer1809 printer) {
		super();
		this.printer = printer;
	}

	@Override
	public void run() {
		for (int i = 1; i <= 100; i = i + 3) {
			try {
				printer.print(id, i);
				printer.setNextId(2);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}

class T2 extends Thread {

	private int id = 2;

	private Printer1809 printer;

	public T2(Printer1809 printer) {
		this.printer = printer;
	}

	@Override
	public void run() {
		for (int i = 2; i <= 100; i = i + 3) {
			try {
				printer.print(id, i);
				printer.setNextId(3);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}

class T3 extends Thread {

	private int id = 3;

	private Printer1809 printer;

	public T3(Printer1809 printer) {
		this.printer = printer;
	}

	@Override
	public void run() {
		for (int i = 3; i <= 100; i = i + 3) {
			try {
				printer.print(id, i);
				printer.setNextId(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}

class Printer1809 {

	private volatile int id = 1;

	public synchronized void print(int currentId, int number) throws InterruptedException {
		while (id != currentId) {
			wait();
		}
		System.out.println(number);
		ThreadUtils.sleep(Duration.ofSeconds(1));
	}

	public synchronized void setNextId(int currentId) {
		this.id = currentId;
		notifyAll();
	}
}