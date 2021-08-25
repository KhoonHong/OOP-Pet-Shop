import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * The billing class handles the customer billing functionalities
 *
 *
 * @author Chan Jia Wei
 */

public class Billing implements Displayable, Identifiable{
    private double totalAmount;
    private double grandTotal;
    private static int currentTransactionCount;
    private static int totalTransactionCount;
    private String transactionID;
    private ArrayList<Reservation> billDetails = new ArrayList<>();
    private String paymentMethod;
    private Promotion promoApplied;
    private String promoOrigin;
    private LocalDateTime paymentDate;

    // Constructor
    Billing() {
        // no-args
    }

    Billing(Reservation bill) {
        currentTransactionCount++;
        totalTransactionCount++;
        this.transactionID = generateID(currentTransactionCount); //generate ID
        // add bill into array
        this.billDetails.add(bill);
    }

    Billing(Reservation bill, double totalAmount, double grandTotal, String paymentMethod, Promotion promoApplied, String promoOrigin, LocalDateTime paymentDate) {
        currentTransactionCount++;
        totalTransactionCount++;
        this.transactionID = generateID(currentTransactionCount); //generate ID
        // add bill into array
        this.billDetails.add(bill);
        this.paymentMethod = paymentMethod;
        this.promoApplied = promoApplied;
        this.promoOrigin = promoOrigin;
        this.paymentDate = paymentDate;
        this.grandTotal = grandTotal;
        this.totalAmount = totalAmount;
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

    public ArrayList<Reservation> getBillDetails() {
        return billDetails;
    }

    public void setBillDetails(ArrayList<Reservation> billDetails) {
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

    public void addBill(Reservation reservation) {
        this.getBillDetails().add(reservation);
    }

    public LocalDateTime getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(LocalDateTime paymentDate) {
        this.paymentDate = paymentDate;
    }

    public double getGrandTotal() {
        return grandTotal;
    }

    public void setGrandTotal(double grandTotal) {
        this.grandTotal = grandTotal;
    }

    // Methods
    @Override
    public String displayRow(){
        return String.format("|%10s |%10s | %6s |%8s |%10s |%11s |%s |Count : %d |",
                Main.convertCurrency(getTotalAmount()),
                Main.convertCurrency(getGrandTotal()),
                getTransactionID(),
                getPaymentMethod(),
                displayPromoOrigin(),
                Main.datetimeToString(getPaymentDate()),
                displayPromo(getPromoApplied()),
                getBillDetails().size());
    }

    // to generate ID
    @Override
    public String generateID(int count){
        String additionalZero = "";
        String idNum = String.valueOf(count);
        if(idNum.length() == 1){
            additionalZero = "0000";
        }
        else if (idNum.length() == 2){
            additionalZero = "000";
        }
        else if (idNum.length() == 3){
            additionalZero = "00";
        }
        else if (idNum.length() == 4){
            additionalZero = "0";
        }
        return "B" + additionalZero + idNum;
    }

    /**
     * To handle null output scenario,
     * as customers can opt to either apply promotion code or not during checkout.
     * The promotion class variable will be null if no promo code is applied.
     * Therefore, this method will check the status of the promotion class variable in billing class.
     * if the promotion passed in is null, the method will return "No Promo" String back when called
     * else the method will return the {@code toString()} method of the Promotion class.
     *
     * @param promo pass in promotion class object to process output for display function
     * @return "No Promo" String output or {@code toString()} of Promotion class
     *
     */
    public static String displayPromo(Promotion promo) {
        if (promo == null) {
            return "No Promo";
        }
        else {
            return promo.toString();
        }
    }

    /**
     * This method is
     *
     * @return "No Promo" String output or {@code getPromoOrigin()}
     */
    public String displayPromoOrigin() {
        if (getPromoOrigin() == null) {
            return "No Promo";
        }
        else {
            return getPromoOrigin();
        }
    }

    @Override
    public String toString() {
        return String.format("""
                Total Amount   : %s
                Grand Total    : %s
                Transaction ID : %s
                Payment Method : %s
                Promo Origin   : %s
                Payment Date   : %s
                Bill Details
                ------------------
                %s
                ------------------
                Promo Applied
                ------------------
                %s
                ------------------
                """, Main.convertCurrency(getTotalAmount()),
                Main.convertCurrency(getGrandTotal()),
                getTransactionID(),
                getPaymentMethod(),
                getPromoOrigin(),
                getPaymentDate(),
                getBillDetails().toString(),
                displayPromo(getPromoApplied()));
    }
}
