import java.util.HashMap;

public class Bath extends Service{
    private boolean scentedShampoo;
    private boolean lowShedShampoo;
    private boolean antiTickFleaShampoo;
    private boolean earCleaning;
    private boolean analGlandCleaning;
    private boolean hairRemover_Flushing;
    private boolean breathFreshener;
    private boolean teethCleanse;

    // Constructor
    Bath() {
        // no-args
    }

    Bath(boolean scentedShampoo,
         boolean lowShedShampoo,
         boolean antiTickFleaShampoo,
         boolean earCleaning,
         boolean analGlandCleaning,
         boolean hairRemover_Flushing,
         boolean breathFreshener,
         boolean teethCleanse,
         String desc,
         double price,
         HashMap<String, Double> addOnPrice) {
        super(desc);
        this.scentedShampoo = scentedShampoo;
        this.lowShedShampoo = lowShedShampoo;
        this.antiTickFleaShampoo = antiTickFleaShampoo;
        this.earCleaning = earCleaning;
        this.analGlandCleaning = analGlandCleaning;
        this.hairRemover_Flushing = hairRemover_Flushing;
        this.breathFreshener = breathFreshener;
        this.teethCleanse = teethCleanse;
    }

    // Getter & Setter
    public boolean isScentedShampoo() {
        return scentedShampoo;
    }

    public void setScentedShampoo(boolean scentedShampoo) {
        this.scentedShampoo = scentedShampoo;
    }

    public boolean isLowShedShampoo() {
        return lowShedShampoo;
    }

    public void setLowShedShampoo(boolean lowShedShampoo) {
        this.lowShedShampoo = lowShedShampoo;
    }

    public boolean isAntiTickFleaShampoo() {
        return antiTickFleaShampoo;
    }

    public void setAntiTickFleaShampoo(boolean antiTickFleaShampoo) {
        this.antiTickFleaShampoo = antiTickFleaShampoo;
    }

    public boolean isEarCleaning() {
        return earCleaning;
    }

    public void setEarCleaning(boolean earCleaning) {
        this.earCleaning = earCleaning;
    }

    public boolean isAnalGlandCleaning() {
        return analGlandCleaning;
    }

    public void setAnalGlandCleaning(boolean analGlandCleaning) {
        this.analGlandCleaning = analGlandCleaning;
    }

    public boolean isHairRemover_Flushing() {
        return hairRemover_Flushing;
    }

    public void setHairRemover_Flushing(boolean hairRemover_Flushing) {
        this.hairRemover_Flushing = hairRemover_Flushing;
    }

    public boolean isBreathFreshener() {
        return breathFreshener;
    }

    public void setBreathFreshener(boolean breathFreshener) {
        this.breathFreshener = breathFreshener;
    }

    public boolean isTeethCleanse() {
        return teethCleanse;
    }

    public void setTeethCleanse(boolean teethCleanse) {
        this.teethCleanse = teethCleanse;
    }

    public double getPrice() {
        return Bath.price;
    }

    public void setPrice(double price) {
        Bath.price = price;
    }

    public HashMap<String, Double> getAddOnPrice() {
        return Bath.addOnPrice;
    }

    public void setAddOnPrice(HashMap<String, Double> addOnPrice) {
        Bath.addOnPrice = addOnPrice;
    }

    // Methods
}
