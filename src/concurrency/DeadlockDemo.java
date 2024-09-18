package concurrency;

public class DeadlockDemo {

    private final Object resource1 = new Object();
    private final Object resource2 = new Object();

    // Thread A
    public void methodA() {
        synchronized (resource1) {
            System.out.println("Thread A: Locked Resource 1");

            try { Thread.sleep(100); } catch (InterruptedException e) {}

            synchronized (resource2) {
                System.out.println("Thread A: Locked Resource 2");
            }
        }
    }

    // Thread B
    public void methodB() {
        synchronized (resource2) {
            System.out.println("Thread B: Locked Resource 2");

            //try { Thread.sleep(100); } catch (InterruptedException e) {}

            synchronized (resource1) {
                System.out.println("Thread B: Locked Resource 1");
            }
        }
    }

    public static void main(String[] args) {
        DeadlockDemo deadlock = new DeadlockDemo();

        new Thread(deadlock::methodA, "Thread A").start();
        new Thread(deadlock::methodB, "Thread B").start();
    }
}
