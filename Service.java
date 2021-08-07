import java.util.HashMap;

abstract class Service {
    private static int totalCustomer;
    protected String desc;
    protected static double price;
    protected static HashMap<String, Double> addOnPrice;

    // Constructor
    protected Service() {
        // no-args
        totalCustomer++;
    }

    protected Service(String desc) {
        this.desc = desc;
        totalCustomer++;
    }

    // Getter & Setter
    public static int getTotalCustomer() {
        return totalCustomer;
    }

    public static void setTotalCustomer(int totalCustomer) {
        Service.totalCustomer = totalCustomer;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        Service.price = price;
    }

    public HashMap<String, Double> getAddOnPrice() {
        return addOnPrice;
    }

    public void setAddOnPrice(HashMap<String, Double> addOnPrice) {
        Service.addOnPrice = addOnPrice;
    }

    // Methods

}
