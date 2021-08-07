import java.util.HashMap;

public class Massage extends Service{
    private boolean afterMassageWash;
    private boolean premiumCalmingOil;
    private boolean multiMasseur;

    // Constructor
    Massage() {
        // no-args
    }

    Massage(boolean afterMassageWash, boolean premiumCalmingOil, boolean multiMasseur, String desc) {
        super(desc);
        this.afterMassageWash = afterMassageWash;
        this.premiumCalmingOil = premiumCalmingOil;
        this.multiMasseur = multiMasseur;
    }

    // Getter and Setter
    public boolean isAfterMassageWash() {
        return afterMassageWash;
    }

    public void setAfterMassageWash(boolean afterMassageWash) {
        this.afterMassageWash = afterMassageWash;
    }

    public boolean isPremiumCalmingOil() {
        return premiumCalmingOil;
    }

    public void setPremiumCalmingOil(boolean premiumCalmingOil) {
        this.premiumCalmingOil = premiumCalmingOil;
    }

    public boolean isMultiMasseur() {
        return multiMasseur;
    }

    public void setMultiMasseur(boolean multiMasseur) {
        this.multiMasseur = multiMasseur;
    }

    public double getPrice() {
        return Massage.price;
    }

    public void setPrice(double price) {
        Massage.price = price;
    }

    public HashMap<String, Double> getAddOnPrice() {
        return Massage.addOnPrice;
    }

    public void setAddOnPrice(HashMap<String, Double> addOnPrice) {
        Massage.addOnPrice = addOnPrice;
    }

    // Methods

}
