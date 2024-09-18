package concurrency;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class ExcuterServicesDemo {
	public static void main(String[] args) {
		var executor = Executors.newFixedThreadPool(2);
	}
}
