import java.time.LocalDate;
import java.util.ArrayList;


/**
 * This billing class stores the data that are related to the payment, transaction amount, promotion and date paid.
 * The payment method, amount to pay before and after tax, and the reservation details will be stored in this class
 * and differentiated by unique billing ID.
 * All the billing information will be displayed when the user proceeds to pay in a table form. An ID will be
 * created automatically in this class for every new billing records. Users are allowed to apply the promotion code
 * in the billing process to get a discount. The code entered will then be verified its existence. The discount
 * only worked when the promotion code is present.
 *
 * @author Chan Jia Wei
 * */
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
    private LocalDate paymentDate;

    // Constructor
    Billing() {
        // no-args
    }

    /**
     * Creates a {@code Billing} class object when called
     *
     * @param bill Customer reservation will be passed in for bill processing
     */
    Billing(Reservation bill) {
        currentTransactionCount++;
        totalTransactionCount++;
        this.transactionID = generateID(currentTransactionCount); //generate ID
        // add bill into array
        this.billDetails.add(bill);
    }

    /**
     * Creates a {@code Billing} class object when called
     *
     * @param bill Customer reservation will be passed in for bill processing
     * @param totalAmount The total price of reservation
     * @param grandTotal The grand total price of billing
     * @param paymentMethod Customer preferred payment method
     * @param promoApplied Promotion applied by the customer
     * @param promoOrigin The source which the customer obtained the promotion
     * @param paymentDate The checkout timestamp
     */
    Billing(Reservation bill, double totalAmount, double grandTotal, String paymentMethod, Promotion promoApplied, String promoOrigin, LocalDate paymentDate) {
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

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public static int getCurrentTransactionCount() {
        return currentTransactionCount;
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

    public LocalDate getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(LocalDate paymentDate) {
        this.paymentDate = paymentDate;
    }

    public double getGrandTotal() {
        return grandTotal;
    }

    public void setGrandTotal(double grandTotal) {
        this.grandTotal = grandTotal;
    }

    // Methods

    public void addBill(Reservation reservation) {
        this.getBillDetails().add(reservation);
    }

    /**
     * Overrides the {@code displayRow()} method in {@code Displayable} interface.
     *
     * @return Formatted {@code Billing} attribute in row
     */
    @Override
    public String displayRow(){
        return String.format("|%10s |%10s | %6s |%8s |%10s |%11s |%s |Count : %d |",
                Main.convertCurrency(getTotalAmount()),
                Main.convertCurrency(getGrandTotal()),
                getTransactionID(),
                getPaymentMethod(),
                displayPromoOrigin(),
                Main.dateToString(getPaymentDate()),
                displayPromo(getPromoApplied()),
                getBillDetails().size());
    }

    /**
     * Overrides the {@code generateID()} method in {@code Identifiable} interface.
     *
     * @param count The current billing object total count
     * @return A formatted ID with Billing in abbreviation at the front, current billing count at the back
     */
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
     *
     * @param promo pass in promotion class object to process output for display function
     * @return "No Promo" String output if null else {@code toString()} of {@code Promotion}
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
     * To handle null output scenario,
     * as customers can opt to either apply promotion code or not during checkout.
     *
     * @return "No Promo" String output or {@code getPromoOrigin()} of {@code Promotion}
     */
    public String displayPromoOrigin() {
        if (getPromoOrigin() == null) {
            return "No Promo";
        }
        else {
            return getPromoOrigin();
        }
    }

    /**
     * Overrides the {@code toString()} method in {@code Object}.
     *
     * @return formatted {@code Billing} attributes
     */
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

    /**
     * Overrides the {@code equals()} method in {@code Object}.
     *
     * @param o Object to be compared
     * @return True if equals, else return false
     */
    @Override
    public boolean equals(Object o) {
        if (o instanceof Billing billing) {
            return billing.equals(this);
        }
        return false;
    }

    /**
     * Calculate the total amount of each reservation in {@code billDetails} ArrayList and store it into an ArrayList.
     *
     * @return The total amount of each reservation in ArrayList
     */
    public ArrayList<Double> getEachSubtotal(){
        ArrayList<Double> amt = new ArrayList<>();
        for (Reservation billDetail : billDetails) {
            if (billDetail.getServices() instanceof Groom) {
                amt.add(Groom.getPrice() + billDetail.getServices().sumAddOnPrice());
            } else if (billDetail.getServices() instanceof Bath) {
                amt.add(Bath.getPrice() + billDetail.getServices().sumAddOnPrice());
            } else if (billDetail.getServices() instanceof Massage) {
                amt.add(Massage.getPrice() + billDetail.getServices().sumAddOnPrice());
            } else {
                amt.add(billDetail.getServices().sumAddOnPrice());
            }
        }
        return amt;
    }

    /**
     * Calculate the total amount of each reservation in {@code billDetails} ArrayList with addition.
     *
     * @return The total amount of all the reservations
     */
    public double calcTotalAmount(){
        ArrayList<Double> amt = new ArrayList<>();
        double totalAmt = 0;
        for (int i = 0; i < billDetails.size(); i++){
            if( billDetails.get(i).getServices() instanceof Groom){
                amt.add(Groom.getPrice() + billDetails.get(i).getServices().sumAddOnPrice());
            }
            else if( billDetails.get(i).getServices() instanceof Bath){
                amt.add(Bath.getPrice() + billDetails.get(i).getServices().sumAddOnPrice());
            }
            else if( billDetails.get(i).getServices() instanceof Massage){
                amt.add(Massage.getPrice() + billDetails.get(i).getServices().sumAddOnPrice());
            }
            else{
                amt.add(billDetails.get(i).getServices().sumAddOnPrice());
            }
            totalAmt += amt.get(i);
        }
        totalAmount = totalAmt;
        return totalAmt;
    }
}
