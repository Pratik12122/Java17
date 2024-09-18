package concurrency;

import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.CountDownLatch;

public class CountDownLatchExample {
	public static void main(String[] args) throws InterruptedException {
		var countDownLatch = new CountDownLatch(3);

		System.out.println("Starting the Operations....");

		var dataCollectorThread = new DataCollector(countDownLatch);
		var taxCalculatorThread = new CalculateTax(countDownLatch);
		var printReport = new PrintTaxReport(countDownLatch);

		dataCollectorThread.start();
		taxCalculatorThread.start();
		printReport.start();

		countDownLatch.await();

		System.out.println("All Task Completed... closing the Operations....");

	}
}

class DataCollector extends Thread {

	private CountDownLatch countDownLatch;

	public DataCollector(CountDownLatch countDownLatch) {
		super();
		this.countDownLatch = countDownLatch;
	}

	@Override
	public void run() {
		System.out.println("Collecting Data...." + Instant.now());
		ThreadUtils.sleep(Duration.ofSeconds(5));
		System.out.println("Data Collected..." + Instant.now());
		countDownLatch.countDown();
	}

}

class CalculateTax extends Thread {

	private CountDownLatch countDownLatch;

	public CalculateTax(CountDownLatch countDownLatch) {
		super();
		this.countDownLatch = countDownLatch;
	}

	@Override
	public void run() {
		System.out.println("Calcualting Tax..." + Instant.now());
		ThreadUtils.sleep(Duration.ofSeconds(10));
		System.out.println("Tax Calculated..." + Instant.now());
		countDownLatch.countDown();
	}
}

class PrintTaxReport extends Thread {

	private CountDownLatch countDownLatch;

	public PrintTaxReport(CountDownLatch countDownLatch) {
		super();
		this.countDownLatch = countDownLatch;
	}

	@Override
	public void run() {
		System.out.println("Printing Tax Report... started @" + Instant.now());
		ThreadUtils.sleep(Duration.ofSeconds(15));
		System.out.println("Printed Tax Report...@" + Instant.now());
		countDownLatch.countDown();
	}
}
