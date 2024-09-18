package concurrency.waitNotify;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class ProducerConsumer {
	public static void main(String[] args) {
		Buffer buffer = new Buffer();

		Producer p = new Producer(buffer);
		Consumer c = new Consumer(buffer);

		c.start();
		p.start();

	}
}

class Producer extends Thread {
	final Buffer buffer;
	int value = 0;

	public Producer(Buffer buffer) {
		super();
		this.buffer = buffer;
	}

	@Override
	public void run() {
		while (true) {
			try {
				buffer.produce(value++);
				Thread.sleep(1000);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}

class Consumer extends Thread {
	final Buffer buffer;

	public Consumer(Buffer buffer) {
		super();
		this.buffer = buffer;
	}

	@Override
	public void run() {
		while (true) {
			try {
				buffer.consume();
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}

class Buffer {
	private final Queue<Integer> queue = new LinkedList<Integer>();
	private final int capacity = 5;

	public synchronized void produce(int value) throws InterruptedException {
		if (queue.size() == capacity) {
			notify();
			wait();
		}
		queue.offer(value);
		System.out.println("Produced: " + value);
	}

	public synchronized List<Integer> consume() throws InterruptedException {
		while (queue.isEmpty() || queue.size() < capacity) {
			wait();
		}

		var res = new ArrayList<Integer>();

		while (!queue.isEmpty()) {
			int value = queue.poll();
			System.out.println("Consumed: " + value);
			res.add(value);
		}

		notify();
		return res;

	}
}