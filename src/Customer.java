import java.util.Random;

public class Customer {
    // Constant static variables
    private static final int NUM_ITEMS_LOWER_BOUND = 1;
    private static final int NUM_ITEMS_UPPER_BOUND = 25;

    private static final int TIME_PER_ITEM_LOWER_BOUND = 1;
    private static final int TIME_PER_ITEM_UPPER_BOUND = 6;

    private static final int TIME_TO_PAY_LOWER_BOUND = 2;
    private static final int TIME_TO_PAY_UPPER_BOUND = 45;

    // Member variables
    private final int numItems;
    private final int timePerItem;
    private final int timeToPay;

    // Constructors
    public Customer() {
        Random random = new Random();

        numItems = random.nextInt(NUM_ITEMS_UPPER_BOUND - NUM_ITEMS_LOWER_BOUND + 1) + NUM_ITEMS_LOWER_BOUND;
        timePerItem = random.nextInt(TIME_PER_ITEM_UPPER_BOUND - TIME_PER_ITEM_LOWER_BOUND + 1) + TIME_PER_ITEM_LOWER_BOUND;
        timeToPay = random.nextInt(TIME_TO_PAY_UPPER_BOUND - TIME_PER_ITEM_LOWER_BOUND + 1) + TIME_TO_PAY_LOWER_BOUND;
    }

    public Customer(int numItems, int timePerItem, int timeToPay) {
        this.numItems = numItems;
        this.timePerItem = timePerItem;
        this.timeToPay = timeToPay;
    }

    // Accessors
    public int getNumItems() {
        return numItems;
    }

    public int getTimePerItem() {
        return timePerItem;
    }

    public int getTimeToPay() {
        return timeToPay;
    }

    // Debug
    @Override
    public String toString() {
        return "Customer: " + numItems + " item(s), " + timePerItem + " seconds/item, " + timeToPay + " seconds to pay";
    }
}
