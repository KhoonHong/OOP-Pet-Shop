import java.time.LocalDateTime;
import java.util.Date;

public class Promotion {
    private String promoID;
    private LocalDateTime promoStartDate;
    private LocalDateTime promoEndDate;
    private double promoRate;
    private String desc;

    // Constructor
    Promotion() {
        // no-args
    }

    Promotion(String promoID, LocalDateTime promoStartDate, LocalDateTime promoEndDate, double promoRate, String desc) {
        this.promoID = promoID;
        this.promoStartDate = promoStartDate;
        this.promoEndDate = promoEndDate;
        this.promoRate = promoRate;
        this.desc = desc;
    }

    // Getter & Setter
    public String getPromoID() {
        return promoID;
    }

    public void setPromoID(String promoID) {
        this.promoID = promoID;
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

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    // Methods

}
