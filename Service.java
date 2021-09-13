import java.util.HashMap;
import java.util.Objects;


/**
 * Service class is a superclass for the groom class, bath class, massage class and shelter class.
 * All of the four classes inherited data and methods from it.
 * The data field being inherited are the total number of customers, price and a hashmap of add-ons prices.
 * The price will be initialized with the basic services charges (without any add-on) in the subclass and later on being sum up with additional charges.
 * The additional charges are to be retrieved from the hashmap accordingly.
 *
 * @author Ong Jia Hui
 */
abstract class Service {
    private static int totalCustomer;
    protected static double price;
    protected HashMap<String, Double> addOnPrice = new HashMap<>();

    // Constructor
    protected Service() {
        // no-args
        totalCustomer++;
    }

    /**
     * {@code Service} is an abstract (super) class, thus can't be used to create objects.
     *
     * @param addOnPrice A hashmap of add-ons selected with name as key and price as value
     */
    protected Service(HashMap<String, Double> addOnPrice) {
        this.addOnPrice = addOnPrice;
        totalCustomer++;
    }

    // Getter & Setter
    public static int getTotalCustomer() {
        return totalCustomer;
    }

    public static double getPrice() {
        return price;
    }

    public static void setPrice(double price) {
        Service.price = price;
    }

    public HashMap<String, Double> getAddOnPrice() {
        return addOnPrice;
    }

    public void setAddOnPrice(HashMap<String, Double> addOnPrice) {
        this.addOnPrice = addOnPrice;
    }

    // Methods

    /**
     * This abstract method will be overridden by subclass methods.
     */
    public abstract void calculateAddOnPrice();

    /**
     * Calculates the total add-on price by iterating through {@code addOnPrice} hashmap.
     *
     * @return The total sum of add-on price of a service
     */
    public double sumAddOnPrice() {
        double sum = 0;
        for (Double price : getAddOnPrice().values()) {
            sum += price;
        }
        return sum;
    }

    /**
     * Overrides the {@code equals()} method in {@code Object}.
     *
     * @param o Object to be compared
     * @return True if equals, else return false
     */
    @Override
    public boolean equals(Object o) {
        if (o instanceof Service service) {
            return Objects.equals(service.getAddOnPrice(), this.getAddOnPrice());
        }
        return false;
    }
}
