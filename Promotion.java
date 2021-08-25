import java.time.LocalDateTime;

/**
 *
 *
 * @author Ong Jia Hui
 */

public class Promotion implements Identifiable {
    private String promoID;
    private String promoCode;
    private LocalDateTime promoStartDate;
    private LocalDateTime promoEndDate;
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

    Promotion(String promoCode, LocalDateTime promoStartDate, LocalDateTime promoEndDate, double promoRate, String description) {
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

    public LocalDateTime getPromoStartDate() {
        return promoStartDate;
    }

    public void setPromoStartDate(LocalDateTime promoStartDate) {
        this.promoStartDate = promoStartDate;
    }

    public LocalDateTime getPromoEndDate() {
        return promoEndDate;
    }

    public void setPromoEndDate(LocalDateTime promoEndDate) {
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

    @Override
    public String toString() {
        return String.format("|%-10s|%-20s|%-20s| %-6.2f|%-60s|\n", promoID,
                Main.dateToString(promoStartDate),
                Main.dateToString(promoEndDate) ,
                promoRate,
                description);
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
        return "PR" + additionalZero + idNum;
    }
}
