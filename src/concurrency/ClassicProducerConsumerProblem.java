package concurrency;

import java.time.Duration;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

public class ClassicProducerConsumerProblem {
	public static void main(String[] args) throws InterruptedException {
		var queue = new LinkedList<Integer>();
		var producerThread = new Classic_ProducerThread(queue);
		var consumerThread = new Classic_ConsumertThread(queue);

		consumerThread.start();
		producerThread.start();

		consumerThread.join();
		producerThread.join();

	}
}

class Classic_ProducerThread extends Thread {
	private Queue<Integer> queue;

	public Classic_ProducerThread(Queue<Integer> queue) {
		super();
		this.queue = queue;
	}

	@Override
	public void run() {
		try {
			startProducing();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private void startProducing() throws InterruptedException {
		synchronized (queue) {
			while (true) {
				while (!queue.isEmpty()) {
					queue.wait();
				}
				int value = new Random().nextInt();
				queue.add(value);
				System.out.println("Produced : " + value);
				queue.notify();
				ThreadUtils.sleep(Duration.ofSeconds(3));
			}
		}

	}

}

class Classic_ConsumertThread extends Thread {
	private Queue<Integer> queue;

	public Classic_ConsumertThread(Queue<Integer> queue) {
		super();
		this.queue = queue;
	}

	@Override
	public void run() {
		try {
			startConsuming();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void startConsuming() throws InterruptedException {
		synchronized (queue) {
			while (true) {
				while (queue.isEmpty()) {
					queue.wait();
				}

				int value = queue.poll();
				System.out.println("Consumed " + value);
				queue.notify();
				ThreadUtils.sleep(Duration.ofSeconds(3));
			}
		}
	}
}
