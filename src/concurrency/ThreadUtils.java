package concurrency;

import java.time.Duration;

public class ThreadUtils {

	public static void sleep(Duration duration) {
		try {
			Thread.sleep(duration);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
