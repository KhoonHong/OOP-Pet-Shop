import java.util.HashMap;

public class Massage extends Service{
    private boolean afterMassageWash = false;
    private boolean premiumCalmingOil = false;
    private boolean multiMasseur = false;
    private static double price = 50;
    private static final String desc = """
					+----------------------------------------------------------------+
					|                                                                |
					|                        M A S S A G E                           |
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
    Massage() {
        // no-args
    }

    Massage(boolean afterMassageWash,
            boolean premiumCalmingOil,
            boolean multiMasseur,
            HashMap<String, Double> addOnPrice) {
        super(addOnPrice);
        this.afterMassageWash = afterMassageWash;
        this.premiumCalmingOil = premiumCalmingOil;
        this.multiMasseur = multiMasseur;
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
    public void toggleAfterMassageWash() {
        afterMassageWash = !afterMassageWash;
    }

    public void togglePremiumCalmingOil() {
        premiumCalmingOil = !premiumCalmingOil;
    }

    public void toggleMultiMasseur() {
        multiMasseur = !multiMasseur;
    }

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
}
