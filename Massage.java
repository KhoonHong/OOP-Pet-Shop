import java.util.HashMap;


/**
 * The massage class is a class for massage services.
 * The massage class stores data and methods related to the basic information of the massage service (inherits from
 * the service class) and the add-on requirement specifically for massage service. Users are allowed to customize
 * the massage services precisely based on their demands. A list of total 3 add-on together with the price will be
 * printed out accordingly for the users reference. The add-on selected by the users will show a 'O' , which by
 * default, they are all in 'X'.
 * The class also consists of basic information related to the massage service such as price and description that
 * is retrievable and modifiable when necessary. The total price of all the services required will then be
 * calculated accordingly after the selection.
 *
 * @author Ong Jia Hui
 */
public class Massage extends Service{
    private boolean afterMassageWash = false;
    private boolean premiumCalmingOil = false;
    private boolean multiMasseur = false;
    private static double price = 50;
    private static final String desc = """
					  +----------------------------------------------------------------+
					  |                                                                |
					  |                    *** M A S S A G E ***                       |
					  |                                                                |
					  |          Massage, a technique of joints and muscles            |
					  |     rubbing for pain relieving, is not limited to humans.      |
					  |     We have explored a little knowledge and are willing to     |
					  |     provide massaging services to pets. Massaging oil with     |
					  |      a calming effect can be included in the service, and      |
					  |         an after-massage bath service will be provided         |
					  |                        if necessary.                           |
					  |                                                                |
					  |  Pay an affordable price of RM50, and your pet can enjoy it.   |
					  |                                                                |
					  +----------------------------------------------------------------+
						""";

    // Constructor
    public Massage() {
        // no-args
    }

    /**
     * Creates a {@code Massage} class object when called
     *
     * @param afterMassageWash True if customer want after massage wash
     * @param premiumCalmingOil True if customer want premium calming oil
     * @param multiMasseur True if customer want multiple masseur
     * @param addOnPrice A hashmap of add-ons selected with name as key and price as value
     */
    public Massage(boolean afterMassageWash,
            boolean premiumCalmingOil,
            boolean multiMasseur,
            HashMap<String, Double> addOnPrice) {
        super(addOnPrice);
        this.afterMassageWash = afterMassageWash;
        this.premiumCalmingOil = premiumCalmingOil;
        this.multiMasseur = multiMasseur;
        calculateAddOnPrice();
    }

    // Getter and Setter
    public static String getDesc() {
        return Massage.desc;
    }

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

    public static double getPrice() {
        return Massage.price;
    }

    public static void setPrice(double price) {
        Massage.price = price;
    }

    // Methods

    /**
     These methods below when called will toggle the boolean "buttons" within the {@code Massage} class attributes
     */
    public void toggleAfterMassageWash() {
        afterMassageWash = !afterMassageWash;
    }

    public void togglePremiumCalmingOil() {
        premiumCalmingOil = !premiumCalmingOil;
    }

    public void toggleMultiMasseur() {
        multiMasseur = !multiMasseur;
    }

    /**
     * Overrides the {@code calculateAddOnPrice()} method in {@code Service}
     *
     * This method will add the add-on name and prices of {@code Massage} service to the addOnPrice hashmap
     */
    @Override
    public void calculateAddOnPrice() {
        addOnPrice.clear(); // clear add on price hashmap
        if (afterMassageWash) {
            addOnPrice.put("After Massage Wash", 15.00);
        }
        if (premiumCalmingOil) {
            addOnPrice.put("Premium Calming Oil", 20.00);
        }
        if (multiMasseur) {
            addOnPrice.put("Multiple Masseur", 30.00);
        }
    }

    /**
     * Overrides the {@code toString()} method in {@code Object}.
     *
     * @return formatted {@code Massage} attributes
     */
    @Override
    public String toString() {
        return String.format("""
                  Basic Price          (RM50.00) : %s
                  After Massage Wash   (RM15.00) : %c
                  Premium Calming Oil  (RM20.00) : %c
                  Multi Masseur        (RM30.00) : %c
                """, Main.convertCurrency(getPrice()),
                Main.booleanToSymbol(isAfterMassageWash()),
                Main.booleanToSymbol(isPremiumCalmingOil()),
                Main.booleanToSymbol(isMultiMasseur()));
    }

    /**
     * Overrides the {@code equals()} method in {@code Object} and {@code Service}.
     *
     * @param o Object to be compared
     * @return True if equals, else return false
     */
    @Override
    public boolean equals(Object o) {
        if (o instanceof Massage massage) {
            return super.equals(massage) &&
                    massage.isAfterMassageWash() == this.isAfterMassageWash() &&
                    massage.isPremiumCalmingOil() == this.isPremiumCalmingOil() &&
                    massage.isMultiMasseur() == this.isMultiMasseur();
        }
        return false;
    }
}
