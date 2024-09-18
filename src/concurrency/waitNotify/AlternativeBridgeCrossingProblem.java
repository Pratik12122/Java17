package concurrency.waitNotify;


class Bridge1 {
    private int vehiclesOnBridge = 0;
    private String currentDirection = "";
    private int waitingNorth = 0;
    private int waitingSouth = 0;

    public synchronized void enterBridge(String direction) throws InterruptedException {
        if (direction.equals("north")) {
            waitingNorth++;
        } else {
            waitingSouth++;
        }

        while (vehiclesOnBridge > 0 && !currentDirection.equals(direction) || 
               (currentDirection.equals("north") && direction.equals("south") && vehiclesOnBridge > 0) || 
               (currentDirection.equals("south") && direction.equals("north") && vehiclesOnBridge > 0)) {
            wait(); // Wait until the bridge is clear or the direction matches
        }

        if (direction.equals("north")) {
            waitingNorth--;
        } else {
            waitingSouth--;
        }

        currentDirection = direction;
        vehiclesOnBridge++;
        System.out.println("Vehicle entering bridge, direction: " + direction + ", vehicles on bridge: " + vehiclesOnBridge);
    }

    public synchronized void exitBridge() {
        vehiclesOnBridge--;
        System.out.println("Vehicle exiting bridge, vehicles remaining on bridge: " + vehiclesOnBridge);

        if (vehiclesOnBridge == 0) {
            // Alternate direction only when there are vehicles waiting in the opposite direction
            if (currentDirection.equals("north") && waitingSouth > 0) {
                currentDirection = "south";
            } else if (currentDirection.equals("south") && waitingNorth > 0) {
                currentDirection = "north";
            }
            notifyAll(); // Notify all waiting vehicles to check whose turn it is
        }
    }
}

class Vehicle1 extends Thread {
    private final Bridge1 bridge;
    private final String direction;

    public Vehicle1(Bridge1 bridge, String direction) {
        this.bridge = bridge;
        this.direction = direction;
    }

    @Override
    public void run() {
        try {
            bridge.enterBridge(direction);
            Thread.sleep(3000); // Simulate time taken to cross the bridge
            bridge.exitBridge();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}

public class AlternativeBridgeCrossingProblem {
    public static void main(String[] args) {
        Bridge1 bridge = new Bridge1();

        // Create vehicles with different directions
        Vehicle1 v1 = new Vehicle1(bridge, "north");
        Vehicle1 v2 = new Vehicle1(bridge, "north");
        Vehicle1 v3 = new Vehicle1(bridge, "south");
        Vehicle1 v4 = new Vehicle1(bridge, "south");
        Vehicle1 v5 = new Vehicle1(bridge, "north");
        Vehicle1 v6 = new Vehicle1(bridge, "south");

        // Start vehicles
        v1.start();
        v2.start();
        v3.start();
        v4.start();
        v5.start();
        v6.start();
    }
}

