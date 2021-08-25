import java.util.HashMap;

abstract class Service {
    private static int totalCustomer;
    protected static double price;
    protected HashMap<String, Double> addOnPrice = new HashMap<>();

    // Constructor
    protected Service() {
        // no-args
        totalCustomer++;
    }

    protected Service(HashMap<String, Double> addOnPrice) {
        this.addOnPrice = addOnPrice;
        totalCustomer++;
    }

    // Getter & Setter
    public static int getTotalCustomer() {
        return totalCustomer;
    }

    public static void setTotalCustomer(int totalCustomer) {
        Service.totalCustomer = totalCustomer;
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
    public abstract void calculateAddOnPrice();

    public double sumAddOnPrice() {
        double sum = 0;
        for (Double price : getAddOnPrice().values()) {
            sum += price;
        }
        return sum;
    }
}
