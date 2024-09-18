package concurrency.waitNotify;

class Bridge {
	private int vehiclesOnBridge = 0;
	private String currentDirection = "";

	public synchronized void enterBridge(String direction) throws InterruptedException {
		while (vehiclesOnBridge > 0 && !currentDirection.equals(direction)) {
			wait(); // Wait until the bridge is clear or the direction matches
		}
		currentDirection = direction;
		vehiclesOnBridge++;
		System.out.println(
				"Vehicle entering bridge, direction: " + direction + ", vehicles on bridge: " + vehiclesOnBridge);
	}

	public synchronized void exitBridge() {
		vehiclesOnBridge--;
		System.out.println("Vehicle exiting bridge, vehicles remaining on bridge: " + vehiclesOnBridge);
		if (vehiclesOnBridge == 0) {
			currentDirection = ""; // Reset direction when bridge is empty
			notifyAll(); // Notify all waiting vehicles
		}
	}
}

class Vehicle extends Thread {
	private final Bridge bridge;
	private final String direction;

	public Vehicle(Bridge bridge, String direction) {
		this.bridge = bridge;
		this.direction = direction;
	}

	@Override
	public void run() {
		try {
			bridge.enterBridge(direction);
			Thread.sleep(1000); // Simulate time taken to cross the bridge
			bridge.exitBridge();
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
	}
}

public class CrossingBridgeProblem {
	public static void main(String[] args) {
		Bridge bridge = new Bridge();

		// Create vehicles with different directions
		Vehicle v1 = new Vehicle(bridge, "north");
		Vehicle v2 = new Vehicle(bridge, "south");
		Vehicle v3 = new Vehicle(bridge, "north");
		Vehicle v4 = new Vehicle(bridge, "south");
		Vehicle v5 = new Vehicle(bridge, "north");
		Vehicle v6 = new Vehicle(bridge, "south");
		

		// Start vehicles
		v1.start();
		v2.start();
		v3.start();
		v4.start();
		v5.start();
		v6.start();
	}
}
