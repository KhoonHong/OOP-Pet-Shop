import java.time.LocalDate;


/**
 * Promotion class is defined for the usage of promotion. It stores the information of the promotion and will apply
 * the relevant discount rate when used by the customer before payment. When a promotion code is used, the system
 * will always verify the promotion code is still before the expiry date. All the promotion records that are being
 * generated in this class will be assigned an ID to differentiate every single of them.
 *
 * @author Lee Khoon Hong
 */
public class Promotion implements Identifiable {
    private String promoID;
    private String promoCode;
    private LocalDate promoStartDate;
    private LocalDate promoEndDate;
    private double promoRate;
    private String description;
    private static int currentPromoCount;
    private static int totalPromoCount;

    // Constructor
    Promotion() {
        // no-args
        currentPromoCount++;
        totalPromoCount++;
        this.promoID = generateID(currentPromoCount); //generateID
    }

    /**
     * Creates a {@code Promotion} class object when called
     *
     * @param promoCode The promotion code
     * @param promoStartDate The promotion starting date
     * @param promoEndDate The promotion ending date
     * @param promoRate The promotion rate
     * @param description The promotion description
     */
    Promotion(String promoCode, LocalDate promoStartDate, LocalDate promoEndDate, double promoRate, String description) {
        currentPromoCount++;
        totalPromoCount++;
        this.promoCode = promoCode;
        this.promoStartDate = promoStartDate;
        this.promoEndDate = promoEndDate;
        this.promoRate = promoRate;
        this.description = description;
        this.promoID = generateID(currentPromoCount); //generateID
    }

    // Getter & Setter
    public String getPromoID() {
        return promoID;
    }

    public void setPromoID(String promoID) {
        this.promoID = promoID;
    }

    public String getPromoCode() {
        return promoCode;
    }

    public void setPromoCode(String promoCode) {
        this.promoCode = promoCode;
    }

    public LocalDate getPromoStartDate() {
        return promoStartDate;
    }

    public void setPromoStartDate(LocalDate promoStartDate) {
        this.promoStartDate = promoStartDate;
    }

    public LocalDate getPromoEndDate() {
        return promoEndDate;
    }

    public void setPromoEndDate(LocalDate promoEndDate) {
        this.promoEndDate = promoEndDate;
    }

    public double getPromoRate() {
        return promoRate;
    }

    public void setPromoRate(double promoRate) {
        this.promoRate = promoRate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public static int getCurrentPromoCount() {
        return currentPromoCount;
    }

    public static void setCurrentPromoCount(int currentPromoCount) {
        Promotion.currentPromoCount = currentPromoCount;
    }

    public static int getTotalPromoCount() {
        return totalPromoCount;
    }

    public static void setCurrentTransactionCount(int totalPromoCount) {
        Promotion.totalPromoCount = totalPromoCount;
    }

    // Methods

    /**
     * Overrides the {@code equals()} method in {@code Object}.
     *
     * @param o Object to be compared
     * @return True if equals, else return false
     */
    @Override
    public boolean equals(Object o) {
        if (o instanceof Promotion promo) {
            return promo.getDescription().equals(this.description) ||
                    (promo.getPromoStartDate().equals(this.promoStartDate) &&
                            promo.getPromoEndDate().equals(this.promoEndDate) &&
                            promo.getPromoRate() == this.promoRate);
        }
        return false;
    }

    /**
     * Overrides the {@code toString()} method in {@code Object}.
     *
     * @return formatted {@code Promotion} attributes
     */
    @Override
    public String toString() {
        return String.format("  |%-10s|%-20s|%-20s| %-6.2f|%-60s|\n", promoID,
                Main.dateToString(promoStartDate),
                Main.dateToString(promoEndDate) ,
                promoRate,
                description);
    }

    /**
     * Overrides the {@code generateID()} method in {@code Identifiable} interface.
     *
     * @param count The current {@code Bird} object total count
     * @return A formatted ID with promotion in abbreviation at the front, current {@code Bird} count at the back
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
        return "PR" + additionalZero + idNum;
    }
}
