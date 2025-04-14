public class Checkout {
    public static void main(String[] args) {
        final int SIMULATION_TIME = 7200; // 2 hours expressed in seconds
        final int NUM_CUSTOMERS = 200; // may be unused in final program, just used for testing

        for (int i = 0; i < NUM_CUSTOMERS; i++) {
            Customer customer = new Customer();
            System.out.println(customer);
        }
    }
}