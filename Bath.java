import java.util.HashMap;
import java.util.Objects;


/**
 * The bath class is a class for bath services.
 * The bath class stores data and methods related to the basic information of the bath service (inherits from the
 * service class) and the add-on requirement specifically for bath service. Users are allowed to customize the bath
 * services precisely based on their demands. A list of total 8 add-on together with the price will be printed out
 * accordingly for the users reference. The add-on selected by the users will show a 'O' , which by default, they
 * are all in 'X'.
 * The class also consists of basic information related to the bath service such as price and description that is
 * retrievable and modifiable when necessary.The total price of all the services required will then be calculated
 * accordingly after the selection.
 *
 * @author Ong Jia Hui
 */
public class Bath extends Service{
    private boolean scentedShampoo = false;
    private boolean lowShedShampoo = false;
    private boolean antiTickFleaShampoo = false;
    private boolean earCleaning = false;
    private boolean analGlandCleaning = false;
    private boolean hairRemover_Flushing = false;
    private boolean breathFreshener = false;
    private boolean teethCleanse = false;
    private static double price = 40;
    private static final String desc = """
					  +----------------------------------------------------------------+
					  |                                                                |
					  |                       *** B A T H ***                          |
					  |                                                                |
					  |         Having a big fuss with pets' sanitary issues?          |
					  |            Fading up to bath your own little pet?              |
					  |            Send them here, and we will solve it.               |
					  |                                                                |
					  |     We provide choices of shampoo to be selected based on      |
					  |     different circumstances or requirements. Troublesome       |
					  |     partial cleaning such as the ear, teeth and anal gland     |
					  |     can be handed over to us with no worries. Book a bath      |
					  |      service for your pet if you wish to get rid of the        |
					  |       pet's cleaning issue at a price lowest to RM40.          |
					  |                                                                |
					  +----------------------------------------------------------------+
						""";

    // Constructor
    public Bath() {
        // no-args

    }

    /**
     * Creates a {@code Bath} class object when called
     *
     * @param scentedShampoo True if customer want scented shampoo
     * @param lowShedShampoo True if customer want low shed shampoo
     * @param antiTickFleaShampoo True if customer want anti tick & flea shampoo
     * @param earCleaning True if customer want ear cleaning
     * @param analGlandCleaning True if customer want anal gland cleaning
     * @param hairRemover_Flushing True if customer want hair remover & flushing
     * @param breathFreshener True if customer want breath freshener
     * @param teethCleanse True if customer want teeth cleanse
     * @param addOnPrice A hashmap of add-ons selected with name as key and price as value
     */
    public Bath(boolean scentedShampoo,
         boolean lowShedShampoo,
         boolean antiTickFleaShampoo,
         boolean earCleaning,
         boolean analGlandCleaning,
         boolean hairRemover_Flushing,
         boolean breathFreshener,
         boolean teethCleanse,
         HashMap<String, Double> addOnPrice) {
        super(addOnPrice);
        this.scentedShampoo = scentedShampoo;
        this.lowShedShampoo = lowShedShampoo;
        this.antiTickFleaShampoo = antiTickFleaShampoo;
        this.earCleaning = earCleaning;
        this.analGlandCleaning = analGlandCleaning;
        this.hairRemover_Flushing = hairRemover_Flushing;
        this.breathFreshener = breathFreshener;
        this.teethCleanse = teethCleanse;
        calculateAddOnPrice();
    }

    // Getter & Setter
    public static String getDesc() {
        return Bath.desc;
    }

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

    public static double getPrice() {
        return Bath.price;
    }

    public static void setPrice(double price) {
        Bath.price = price;
    }

    // Methods

    /**
     These methods below when called will toggle the boolean "buttons" within the {@code Bath} class attributes
     */
    public void toggleScentedShampoo() {
        scentedShampoo = !scentedShampoo;
    }

    public void toggleLowShedShampoo() {
        lowShedShampoo = !lowShedShampoo;
    }

    public void toggleAntiTickFleaShampoo() {
        antiTickFleaShampoo = !antiTickFleaShampoo;
    }

    public void toggleEarCleaning() {
        earCleaning = !earCleaning;
    }

    public void toggleAnalGlandCleaning() {
        analGlandCleaning = !analGlandCleaning;
    }

    public void toggleHairRemoval_Flushing() {
        hairRemover_Flushing = !hairRemover_Flushing;
    }

    public void toggleBreathFreshener() {
        breathFreshener = !breathFreshener;
    }

    public void toggleTeethCleanse() {
        teethCleanse = !teethCleanse;
    }

    /**
     * Overrides the {@code calculateAddOnPrice()} method in {@code Service}
     *
     * This method will add the add-on name and prices of {@code Bath} service to the {@code addOnPrice} hashmap
     */
    @Override
    public void calculateAddOnPrice() {
        addOnPrice.clear(); // clear add on price hashmap
        if (scentedShampoo) {
            addOnPrice.put("Scented Shampoo", 10.00);
        }
        if (lowShedShampoo) {
            addOnPrice.put("Low Shed Shampoo", 10.00);
        }
        if (antiTickFleaShampoo) {
            addOnPrice.put("Anti Tick Flea Shampoo", 10.00);
        }
        if (earCleaning) {
            addOnPrice.put("Ear Cleaning", 15.00);
        }
        if (analGlandCleaning) {
            addOnPrice.put("Anal Gland Cleaning", 15.00);
        }
        if (hairRemover_Flushing) {
            addOnPrice.put("Hair Remover & Flushing", 15.00);
        }
        if (breathFreshener) {
            addOnPrice.put("Breath Freshener", 15.00);
        }
        if (teethCleanse) {
            addOnPrice.put("Teeth Cleanse", 15.00);
        }
    }

    /**
     * Overrides the {@code toString()} method in {@code Object}.
     *
     * @return formatted {@code Bath} attributes
     */
    @Override
    public String toString() {
        return String.format("""
                  Basic Price               (RM40.00)
                  Scented Shampoo           (RM10.00) : %c
                  Low Shed Shampoo          (RM10.00) : %c
                  Anti Tick & Flea Shampoo  (RM10.00) : %c
                  Ear Cleaning              (RM15.00) : %c
                  Anal Gland Cleaning       (RM15.00) : %c
                  Hair Removal & Flushing   (RM15.00) : %c
                  Breath Freshener          (RM15.00) : %c
                  Teeth Cleanse             (RM15.00) : %c
                """, Main.booleanToSymbol(isScentedShampoo()),
                Main.booleanToSymbol(isLowShedShampoo()),
                Main.booleanToSymbol(isAntiTickFleaShampoo()),
                Main.booleanToSymbol(isEarCleaning()),
                Main.booleanToSymbol(isAnalGlandCleaning()),
                Main.booleanToSymbol(isHairRemover_Flushing()),
                Main.booleanToSymbol(isBreathFreshener()),
                Main.booleanToSymbol(isTeethCleanse()));
    }

    /**
     * Overrides the {@code equals()} method in {@code Object} and {@code Service}.
     *
     * @param o Object to be compared
     * @return True if equals, else return false
     */
    @Override
    public boolean equals(Object o) {
        if (o instanceof Bath bath) {
            return super.equals(bath) &&
                    bath.isScentedShampoo() == this.isScentedShampoo() &&
                    bath.isLowShedShampoo() == this.isLowShedShampoo() &&
                    bath.isAntiTickFleaShampoo() == this.isAntiTickFleaShampoo() &&
                    bath.isEarCleaning() == this.isEarCleaning() &&
                    bath.isAnalGlandCleaning() == this.isAnalGlandCleaning() &&
                    bath.isHairRemover_Flushing() == this.isHairRemover_Flushing() &&
                    bath.isBreathFreshener() == this.isBreathFreshener() &&
                    bath.isTeethCleanse() == this.isTeethCleanse() &&
                    Objects.equals(bath.getAddOnPrice(), this.getAddOnPrice());
        }
        return false;
    }

}
