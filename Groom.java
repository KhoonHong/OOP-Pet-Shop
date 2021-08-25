import java.util.HashMap;

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
					|                          G R O O M                             |
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
}
