import java.util.ArrayList;
import java.util.Random;

public class Checkout {

    // Constants for simulation
    public static final int SIMULATION_TIME = 7200; // 2 hours = 7200 seconds
    public static final int CUSTOMER_MIN_ARRIVAL = 15; // Customers arrive every 15–30 seconds
    public static final int CUSTOMER_MAX_ARRIVAL = 30;

    public static final int NUM_STATIONS = 5; // Number of checkout stations (cashiers)

    static Random rand = new Random(); // Random number generator

    public static void main(String[] args) {
        System.out.println("Starting Checkout Simulation...");

        // Run all three models one after another
        System.out.println("\nModel 1: One Line for All Stations");
        simulateOneLineModel();

        System.out.println("\nModel 2: Fewest Line Model");
        simulateFewestLineModel();

        System.out.println("\nModel 3: Random Line Model");
        simulateRandomLineModel();
    }

    // Model 1: One Big Line for all stations
    public static void simulateOneLineModel() {
        ArrayList<Station> stations = new ArrayList<>();
        for (int i = 0; i < NUM_STATIONS; i++) {
            stations.add(new Station()); // Create stations (cashiers)
        }

        Queue<CustomerWrapper> line = new Queue<>(); // One big line

        int currentTime = 0; // Clock starts at 0
        int nextCustomerTime = getNextArrivalTime(); // Time when next customer will arrive
        int customersServed = 0;
        int maxQueueLength = 0;
        int totalWaitTime = 0;

        while (currentTime < SIMULATION_TIME) {
            // New customer arrives
            if (currentTime == nextCustomerTime) {
                Customer newCustomer = new Customer();
                line.enqueue(new CustomerWrapper(newCustomer, currentTime));
                nextCustomerTime = currentTime + getNextArrivalTime(); // Schedule next customer
            }

            // Assign customers to free stations
            for (Station s : stations) {
                if (s.isFree() && !line.isEmpty()) {
                    CustomerWrapper cw = line.dequeue();
                    totalWaitTime += (currentTime - cw.arrivalTime);
                    s.assignCustomer(cw.customer);
                    customersServed++;
                }
            }

            // Tick stations to update their timers
            for (Station s : stations) {
                s.tick();
            }

            // Track the maximum size of the line
            maxQueueLength = Math.max(maxQueueLength, line.size());

            currentTime++; // Move clock forward by 1 second
        }

        // Print the results after 2 hours
        printResults(customersServed, maxQueueLength, totalWaitTime);
    }

    // Model 2: Fewest Line Model (choose the shortest line)
    public static void simulateFewestLineModel() {
        ArrayList<Station> stations = new ArrayList<>();
        ArrayList<Queue<CustomerWrapper>> lines = new ArrayList<>();

        // Create stations and separate lines for each station
        for (int i = 0; i < NUM_STATIONS; i++) {
            stations.add(new Station());
            lines.add(new Queue<>());
        }

        int currentTime = 0;
        int nextCustomerTime = getNextArrivalTime();
        int customersServed = 0;
        int maxQueueLength = 0;
        int totalWaitTime = 0;

        while (currentTime < SIMULATION_TIME) {
            // New customer arrives
            if (currentTime == nextCustomerTime) {
                Customer newCustomer = new Customer();
                // Find the line with the fewest people
                int bestLine = 0;
                for (int i = 1; i < lines.size(); i++) {
                    if (lines.get(i).size() < lines.get(bestLine).size()) {
                        bestLine = i;
                    }
                }
                lines.get(bestLine).enqueue(new CustomerWrapper(newCustomer, currentTime));
                nextCustomerTime = currentTime + getNextArrivalTime();
            }

            // Assign customers to their own station
            for (int i = 0; i < NUM_STATIONS; i++) {
                if (stations.get(i).isFree() && !lines.get(i).isEmpty()) {
                    CustomerWrapper cw = lines.get(i).dequeue();
                    totalWaitTime += (currentTime - cw.arrivalTime);
                    stations.get(i).assignCustomer(cw.customer);
                    customersServed++;
                }
            }

            // Tick all stations
            for (Station s : stations) {
                s.tick();
            }

            // Track the maximum line length across all lines
            for (Queue<CustomerWrapper> q : lines) {
                maxQueueLength = Math.max(maxQueueLength, q.size());
            }

            currentTime++;
        }

        printResults(customersServed, maxQueueLength, totalWaitTime);
    }

    // Model 3: Random Line Model (choose a random line)
    public static void simulateRandomLineModel() {
        ArrayList<Station> stations = new ArrayList<>();
        ArrayList<Queue<CustomerWrapper>> lines = new ArrayList<>();

        // Create stations and separate lines
        for (int i = 0; i < NUM_STATIONS; i++) {
            stations.add(new Station());
            lines.add(new Queue<>());
        }

        int currentTime = 0;
        int nextCustomerTime = getNextArrivalTime();
        int customersServed = 0;
        int maxQueueLength = 0;
        int totalWaitTime = 0;

        while (currentTime < SIMULATION_TIME) {
            // New customer arrives
            if (currentTime == nextCustomerTime) {
                Customer newCustomer = new Customer();
                int randomLine = rand.nextInt(NUM_STATIONS); // Pick random line
                lines.get(randomLine).enqueue(new CustomerWrapper(newCustomer, currentTime));
                nextCustomerTime = currentTime + getNextArrivalTime();
            }

            // Assign customers to stations
            for (int i = 0; i < NUM_STATIONS; i++) {
                if (stations.get(i).isFree() && !lines.get(i).isEmpty()) {
                    CustomerWrapper cw = lines.get(i).dequeue();
                    totalWaitTime += (currentTime - cw.arrivalTime);
                    stations.get(i).assignCustomer(cw.customer);
                    customersServed++;
                }
            }

            // Tick all stations
            for (Station s : stations) {
                s.tick();
            }

            // Track maximum line length
            for (Queue<CustomerWrapper> q : lines) {
                maxQueueLength = Math.max(maxQueueLength, q.size());
            }

            currentTime++;
        }

        printResults(customersServed, maxQueueLength, totalWaitTime);
    }

    // Helper Methods

    // How long until the next customer arrives
    public static int getNextArrivalTime() {
        return rand.nextInt(CUSTOMER_MAX_ARRIVAL - CUSTOMER_MIN_ARRIVAL + 1) + CUSTOMER_MIN_ARRIVAL;
    }

    // Print the statistics after the simulation ends
    public static void printResults(int customersServed, int maxQueueLength, int totalWaitTime) {
        System.out.println("Customers served: " + customersServed);
        System.out.println("Maximum queue length: " + maxQueueLength);
        if (customersServed > 0) {
            System.out.printf("Average waiting time: %.2f seconds\n", (double) totalWaitTime / customersServed);
        } else {
            System.out.println("Average waiting time: 0 seconds");
        }
    }
}
