import java.util.concurrent.ThreadLocalRandom;

public class Customer {
    // Constant static variables
    private static final int NUM_ITEMS_LOWER_BOUND = 20;
    private static final int NUM_ITEMS_UPPER_BOUND = 30;

    private static final int TIME_PER_ITEM_LOWER_BOUND = 2;
    private static final int TIME_PER_ITEM_UPPER_BOUND = 4;

    private static final int TIME_TO_PAY_LOWER_BOUND = 5;
    private static final int TIME_TO_PAY_UPPER_BOUND = 30;

    public static final int MIN_ARRIVAL_TIME = 1; // Customers arrive every 1-3o seconds
    public static final int MAX_ARRIVAL_TIME = 30;

    // Member variables
    private final int numItems;
    private final int timePerItem;
    private final int timeToPay;
    private int arrivalTime;

    // Constructors
    /**
     * Default constructor for creating a customer with random values
     */
    public Customer() {
        ThreadLocalRandom rnd = ThreadLocalRandom.current();
        this.numItems = rnd.nextInt(NUM_ITEMS_LOWER_BOUND, NUM_ITEMS_UPPER_BOUND);
        this.timePerItem = rnd.nextInt(TIME_PER_ITEM_LOWER_BOUND, TIME_PER_ITEM_UPPER_BOUND);
        this.timeToPay = rnd.nextInt(TIME_TO_PAY_LOWER_BOUND, TIME_TO_PAY_UPPER_BOUND);
        this.arrivalTime = 0;
    }

    /**
     * Generate a random number between lowerBound and upperBound (inclusive)
     * @param lowerBound the lower bound
     * @param upperBound the upper bound
     * @return a random number between lowerBound and upperBound
     */


//    /**
//     * Constructor for creating a customer with specific values
//     * @param numItems the number of items the customer has
//     * @param timePerItem the time it takes to scan each item
//     * @param timeToPay the time it takes to pay
//     */
//    public Customer(int numItems, int timePerItem, int timeToPay) {
//        this.numItems = numItems;
//        this.timePerItem = timePerItem;
//        this.timeToPay = timeToPay;
//        this.arrivalTime = 0;
//    }

    // Accessors

    /**
     * Get the number of items the customer has
     * @return the number of items
     */
    public int getNumItems() {
        return numItems;
    }

    /**
     * Get the time it takes to scan each item
     * @return the time per item
     */
    public int getTimePerItem() {
        return timePerItem;
    }

    /**
     * Get the time it takes to pay
     * @return the time to pay
     */
    public int getTimeToPay() {
        return timeToPay;
    }

    /**
     * Set the arrival time of the customer
     * @param arrivalTime the arrival time
     */
    public void setArrivalTime(int arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    /**
     * Get the arrival time of the customer
     * @return the arrival time
     */
    public int getArrivalTime() {
        return this.arrivalTime;
    }

    // Debug

    /**
     * String representation of the customer
     * @return a string describing the customer
     */
    @Override
    public String toString() {
        return "Customer: " + numItems + " item(s), " + timePerItem + " seconds/item, " + timeToPay + " seconds to pay";
    }
}
