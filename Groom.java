import java.util.HashMap;


/**
 * The groom class is a class for grooming services.
 * The groom class stores data and methods related to the basic information of the groom service (inherits from the
 * service class) and the add-on requirement specifically for groom service. Users are allowed to customize the
 * grooming services precisely based on their demands. A list of total 7 add-on together with the price will be
 * printed out accordingly for the users reference. The add-on selected by the users will show a 'O' , which by
 * default, they are all in 'X'.
 * The class also consists of basic information related to the grooming service such as price and description that
 * is retrievable and modifiable when necessary. The total price of all the services required will then be calculated
 * accordingly after the selection.
 *
 * @author Ong Jia Hui
 */
public class Groom extends Service{
    private boolean fancyCut = false;
    private boolean sanitaryTrim = false;
    private boolean nailClip = false;
    private boolean nailGrind = false;
    private boolean scissoringFeet = false;
    private boolean padShaving = false;
    private boolean coatStyling = false;
    private static double price = 80;
    private static final String desc = """
					  +----------------------------------------------------------------+
					  |                                                                |
					  |                      *** G R O O M ***                         |
					  |                                                                |
					  |        If you are finding a solution to fix the untidy         |
					  |           appearance of your pet, just come to us.             |
					  |                                                                |
					  |      We provide grooming services to pets with the basic       |
					  |     hair cutting. We do design fancy cut and coat styling      |
					  |    for pets, making them outstanding. Sensitive tasks like     |
					  |     dealing with their feet, pads or nails, are able to be     |
					  |       tackled professionally by our well-trained workers.      |
					  |                                                                |
					  |      Try our grooming package at a minimum price of RM80,      |
					  |                and we won't disappoint you.                    |
					  |                                                                |
					  +----------------------------------------------------------------+
						""";

    // Constructor
    Groom() {
        // no-args
    }

    /**
     * Creates a {@code Groom} class object when called
     *
     * @param fancyCut True if customer wants fancy cut
     * @param sanitaryTrim True if customer wants sanitary trim
     * @param nailClip True if customer want nail clip
     * @param nailGrind True if customer want nail grind
     * @param scissoringFeet True if customer want scissoring feet
     * @param padShaving True if customer want pad shaving
     * @param coatStyling True if customer want coat styling
     * @param addOnPrice A hashmap of add-ons selected with name as key and price as value
     */
    Groom(boolean fancyCut,
          boolean sanitaryTrim,
          boolean nailClip,
          boolean nailGrind,
          boolean scissoringFeet,
          boolean padShaving,
          boolean coatStyling,
          HashMap<String, Double> addOnPrice) {
        super(addOnPrice);
        this.fancyCut = fancyCut;
        this.sanitaryTrim = sanitaryTrim;
        this.nailClip = nailClip;
        this.nailGrind = nailGrind;
        this.scissoringFeet = scissoringFeet;
        this.padShaving = padShaving;
        this.coatStyling = coatStyling;
    }

    // Getter & Setter
    public static String getDesc() {
        return Groom.desc;
    }

    public boolean isFancyCut() {
        return fancyCut;
    }

    public void setFancyCut(boolean fancyCut) {
        this.fancyCut = fancyCut;
    }

    public boolean isSanitaryTrim() {
        return sanitaryTrim;
    }

    public void setSanitaryTrim(boolean sanitaryTrim) {
        this.sanitaryTrim = sanitaryTrim;
    }

    public boolean isNailClip() {
        return nailClip;
    }

    public void setNailClip(boolean nailClip) {
        this.nailClip = nailClip;
    }

    public boolean isNailGrind() {
        return nailGrind;
    }

    public void setNailGrind(boolean nailGrind) {
        this.nailGrind = nailGrind;
    }

    public boolean isScissoringFeet() {
        return scissoringFeet;
    }

    public void setScissoringFeet(boolean scissoringFeet) {
        this.scissoringFeet = scissoringFeet;
    }

    public boolean isPadShaving() {
        return padShaving;
    }

    public void setPadShaving(boolean padShaving) {
        this.padShaving = padShaving;
    }

    public boolean isCoatStyling() {
        return coatStyling;
    }

    public void setCoatStyling(boolean coatStyling) {
        this.coatStyling = coatStyling;
    }

    public static double getPrice() {
        return Groom.price;
    }

    public static void setPrice(double price) {
        Groom.price = price;
    }


    // Methods

    /**
     These methods below when called will toggle the boolean "buttons" within the {@code Groom} class attributes
     */
    public void toggleFancyCut() {
        fancyCut = !fancyCut;
    }

    public void toggleSanitaryTrim() {
        sanitaryTrim = !sanitaryTrim;
    }

    public void toggleNailCLip() {
        nailClip = !nailClip;
    }

    public void toggleNailGrind() {
        nailGrind = !nailGrind;
    }

    public void toggleScissoringFeet() {
        scissoringFeet = !scissoringFeet;
    }

    public void togglePadShaving() {
        padShaving = !padShaving;
    }

    public void toggleCoatStyling() {
        coatStyling = !coatStyling;
    }

    /**
     * Overrides the {@code calculateAddOnPrice()} method in {@code Service}
     *
     * This method will add the add-on name and prices of {@code Groom} service to the addOnPrice hashmap
     */
    public void calculateAddOnPrice() {
        addOnPrice.clear(); // clear add on price hashmap
        if (fancyCut) {
            addOnPrice.put("Fancy Cut", 10.00);
        }
        if (sanitaryTrim) {
            addOnPrice.put("Sanitary Trim", 15.00);
        }
        if (nailClip) {
            addOnPrice.put("Nail Clip", 10.00);
        }
        if (nailGrind) {
            addOnPrice.put("Nail Grind", 15.00);
        }
        if (scissoringFeet) {
            addOnPrice.put("Scissoring Feet", 10.00);
        }
        if (padShaving) {
            addOnPrice.put("Pad Shaving", 15.00);
        }
        if (coatStyling) {
            addOnPrice.put("Coat Styling", 10.00);
        }
    }

    /**
     * Overrides the {@code toString()} method in {@code Object}.
     *
     * @return formatted {@code Groom} attributes
     */
    @Override
    public String toString() {
        return String.format("""
                  Basic Price      (RM80.00)
                  Fancy Cut        (RM10.00) : %c
                  Sanitary Trim    (RM15.00) : %c
                  Nail Clip        (RM10.00) : %c
                  Nail Grind       (RM15.00) : %c
                  Scissoring Feet  (RM10.00) : %c
                  Pad Shaving      (RM15.00) : %c
                  Coat Styling     (RM10.00) : %c
                """, Main.booleanToSymbol(isFancyCut()),
                Main.booleanToSymbol(isSanitaryTrim()),
                Main.booleanToSymbol(isNailClip()),
                Main.booleanToSymbol(isNailGrind()),
                Main.booleanToSymbol(isScissoringFeet()),
                Main.booleanToSymbol(isPadShaving()),
                Main.booleanToSymbol(isCoatStyling()));
    }

    /**
     * Overrides the {@code equals()} method in {@code Object} and {@code Service}.
     *
     * @param o Object to be compared
     * @return True if equals, else return false
     */
    @Override
    public boolean equals(Object o) {
        if (o instanceof Groom groom) {
            return groom.equals(this);
        }
        return false;
    }
}
