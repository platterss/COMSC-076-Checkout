public class Station {

    private boolean busy; // true if helping a customer, false if free
    private int timeRemaining; // how much time left helping current customer

    public Station() {
        busy = false;        // At the start, station is free
        timeRemaining = 0;
    }

    // Assign a customer to this station (start checkout)
    public void assignCustomer(Customer customer) {
        // Calculate how long the checkout will take:
        // (items * seconds per item) + time to pay
        timeRemaining = (customer.getNumItems() * customer.getTimePerItem()) + customer.getTimeToPay();
        busy = true; // Now the station is busy
    }

    // Tick: move the clock forward one second
    public void tick() {
        if (busy) {
            timeRemaining--; // Reduce the time left
            if (timeRemaining <= 0) {
                busy = false; // Done helping the customer
            }
        }
    }

    // Is the station free right now?
    public boolean isFree() {
        return !busy;
    }
}
