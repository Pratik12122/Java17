package concurrency;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

class Counter {

	ReentrantLock lock = new ReentrantLock();
	ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
	Lock reviewsWriteLock = readWriteLock.writeLock();
	Lock reviewsReadLock = readWriteLock.readLock();
	
	

	private int count = 0;

	public void increment() {
		try {
			lock.lock();
			count++;
		} finally {
			lock.unlock();
		}

	}

	public int getCount() {
		try {
			lock.lock();
			return count;
		} finally {
			lock.unlock();
		}

	}

	public static void main(String[] args) throws InterruptedException {
		Counter counter = new Counter();

		Thread t1 = new Thread(() -> {
			for (int i = 0; i < 1000; i++) {
				counter.increment();
			}
		});

		Thread t2 = new Thread(() -> {
			for (int i = 0; i < 1000; i++) {
				counter.increment();
			}
		});

		t1.start();
		t2.start();
		t1.join();
		t2.join();

		System.out.println("Final count: " + counter.getCount());
	}
}
