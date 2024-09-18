package concurrency;

import java.time.Duration;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.LinkedBlockingQueue;

public class BlockingQueueExample {

	public static void main(String[] args) {
		var queue = new LinkedBlockingQueue<Integer>();
		var producerRunnable = new BQ_Producer(queue);
		var consumerRunnable = new BQ_Consumer(queue);

		var producerThread = new Thread(producerRunnable);
		var consumerThread = new Thread(consumerRunnable);

		producerThread.start();
		consumerThread.start();

	}

}

class BQ_Producer implements Runnable {

	private BlockingQueue<Integer> queue;

	public BQ_Producer(BlockingQueue<Integer> queue) {
		super();
		this.queue = queue;
	}

	@Override
	public void run() {
		while (true) {
			for (int i = 0; i < 10; i++) {
				queue.add(i);
				System.out.println("Producer Added " + i);
				// ThreadUtils.sleep(Duration.ofSeconds(3));
			}
			queue.add(-1); // special value
			break;
		}
	}

}

class BQ_Consumer implements Runnable {

	private BlockingQueue<Integer> queue;

	public BQ_Consumer(BlockingQueue<Integer> queue) {
		super();
		this.queue = queue;
	}

	@Override
	public void run() {
		while (true) {
			int value;
			try {
				value = queue.take();
				System.out.println("Consumed value : " + value);
				ThreadUtils.sleep(Duration.ofSeconds(3));

				if (value == -1) {
					break;
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}

	}

}
