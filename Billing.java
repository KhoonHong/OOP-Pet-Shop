import java.util.HashMap;

public class Billing {
    private double totalAmount;
    private static int currentTransactionCount;
    private static int totalTransactionCount;
    private String transactionID;
    private HashMap<String, Double> billDetails;
    private String paymentMethod;
    private Promotion promoApplied;
    private String promoOrigin;

    // Constructor
    Billing() {
        // no-args
    }

    Billing(double totalAmount, String transactionID, HashMap<String, Double> billDetails, String paymentMethod, Promotion promoApplied, String promoOrigin) {
        this.totalAmount = totalAmount;
        this.transactionID = transactionID;
        this.billDetails = billDetails;
        this.paymentMethod = paymentMethod;
        this.promoApplied = promoApplied;
        this.promoOrigin = promoOrigin;
    }

    // Getter & Setter
    public static int getTotalTransactionCount() {
        return totalTransactionCount;
    }

    public static void setTotalTransactionCount(int totalTransactionCount) {
        Billing.totalTransactionCount = totalTransactionCount;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public static int getCurrentTransactionCount() {
        return currentTransactionCount;
    }

    public static void setCurrentTransactionCount(int currentTransactionCount) {
        Billing.currentTransactionCount = currentTransactionCount;
    }

    public String getTransactionID() {
        return transactionID;
    }

    public void setTransactionID(String transactionID) {
        this.transactionID = transactionID;
    }

    public HashMap<String, Double> getBillDetails() {
        return billDetails;
    }

    public void setBillDetails(HashMap<String, Double> billDetails) {
        this.billDetails = billDetails;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public Promotion getPromoApplied() {
        return promoApplied;
    }

    public void setPromoApplied(Promotion promoApplied) {
        this.promoApplied = promoApplied;
    }

    public String getPromoOrigin() {
        return promoOrigin;
    }

    public void setPromoOrigin(String promoOrigin) {
        this.promoOrigin = promoOrigin;
    }

    // Methods

}
