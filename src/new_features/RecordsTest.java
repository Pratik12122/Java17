package new_features;

public class RecordsTest {
	public static void main(String[] args) {
		Point p1 = new Point(10, 4);

		System.out.println(p1);
		System.out.println("distance b/w two points " + p1.distance());

		Thread t1 = new Thread(p1);
		t1.start();

		Point p2 = new Point(10, -4);
	}
}

record Point(int x, int y) implements Runnable {
	public Point {
		if (x < 0 || y < 0) {
			throw new IllegalArgumentException("Coordinates must be non-negative");
		}
	}

	public int distance() {
		return x - y;
	}

	@Override
	public void run() {
		System.out.println("Record as runnaable");
	}

};
