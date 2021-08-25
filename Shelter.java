import java.time.LocalDateTime;
import java.util.HashMap;

public class Shelter extends Service {
    private boolean vegetarian = false;
    private boolean halalFood = false;
    private boolean aircond = false;
    private Size foodPortion = Size.XSMALL;
    private LocalDateTime checkInDate;
    private LocalDateTime checkOutDate;
    private int totalNumOfDays = 1;
    private boolean playRoom = false;
    private boolean oneOnOneCare = false;
    private static double perNightPrice = 120;
    private double totalPerNightPrice;
    private static final String desc = """
					+----------------------------------------------------------------+
					|                                                                |
					|                        S H E L T E R                           |
					|                                                                |
					|          One-on-one care of your pet is not impossible         |
					|      when you are not around. We provide shelter for pets      |
					|       which have "parents" temporarily gone far for work.      |
					|       Such luxurious demands as air-conditioning, special      |
					|    requirements on meals or a period to spend in a playroom    |
					|                     can be fulfilled here.                     |
					|                                                                |
					|      Grab the service at RM120 per night with no concern       |
					|                to leave the little cutie alone.                |
					|                                                                |
					+----------------------------------------------------------------+
												""";

    // Constructor
    Shelter(boolean vegetarian,
                   boolean halalFood,
                   boolean aircond,
                   Size foodPortion,
            LocalDateTime checkInDate,
            LocalDateTime checkOutDate,
                   int totalNumOfDays,
                   boolean playRoom,
                   boolean oneOnOneCare) {
        this.vegetarian = vegetarian;
        this.halalFood = halalFood;
        this.aircond = aircond;
        this.foodPortion = foodPortion;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.totalNumOfDays = totalNumOfDays;
        this.playRoom = playRoom;
        this.oneOnOneCare = oneOnOneCare;
    }

    public Shelter() {

    }

    // Getter & Setter
    public static String getDesc() {
        return Shelter.desc;
    }

    public double getTotalPerNightPrice() {
        return totalPerNightPrice;
    }

    public void setTotalPerNightPrice(double totalPerNightPrice) {
        this.totalPerNightPrice = totalPerNightPrice;
    }

    public static double getPerNightPrice() {
        return perNightPrice;
    }

    public static void setPerNightPrice(double perNightPrice) {
        Shelter.perNightPrice = perNightPrice;
    }

    public boolean isVegetarian() {
        return vegetarian;
    }

    public void setVegetarian(boolean vegetarian) {
        this.vegetarian = vegetarian;
    }

    public boolean isHalalFood() {
        return halalFood;
    }

    public void setHalalFood(boolean halalFood) {
        this.halalFood = halalFood;
    }

    public boolean isAircond() {
        return aircond;
    }

    public void setAircond(boolean aircond) {
        this.aircond = aircond;
    }

    public Size getFoodPortion() {
        return foodPortion;
    }

    public void setFoodPortion(Size foodPortion) {
        this.foodPortion = foodPortion;
    }

    public LocalDateTime getCheckInDate() {
        return checkInDate;
    }

    public void setCheckInDate(LocalDateTime checkInDate) {
        this.checkInDate = checkInDate;
    }

    public LocalDateTime getCheckOutDate() {
        return checkOutDate;
    }

    public void setCheckOutDate(LocalDateTime checkOutDate) {
        this.checkOutDate = checkOutDate;
    }

    public int getTotalNumOfDays() {
        return totalNumOfDays;
    }

    public void setTotalNumOfDays(int totalNumOfDays) {
        this.totalNumOfDays = totalNumOfDays;
    }

    public boolean isPlayRoom() {
        return playRoom;
    }

    public void setPlayRoom(boolean playRoom) {
        this.playRoom = playRoom;
    }

    public boolean isOneOnOneCare() {
        return oneOnOneCare;
    }

    public void setOneOnOneCare(boolean oneOnOneCare) {
        this.oneOnOneCare = oneOnOneCare;
    }

    public static double getPrice() {
        return Shelter.price;
    }

    public static void setPrice(double price) {
        Shelter.price = price;
    }

    // Methods
    public void toggleVegetarian() {
        vegetarian = !vegetarian;
    }

    public void toggleHalalFood() {
        halalFood = !halalFood;
    }

    public void toggleAircond() {
        aircond = !aircond;
    }

    public void togglePlayRoom() {
        playRoom = !playRoom;
    }

    public void toggleOneOnOneCare() {
        oneOnOneCare = !oneOnOneCare;
    }

    @Override
    public void calculateAddOnPrice() {
        addOnPrice.clear(); // clear add on price hashmap
        if (vegetarian) {
            addOnPrice.put("Vegetarian", 0.00);
        }
        if (halalFood) {
            addOnPrice.put("Halal Food", 0.00);
        }
        if (aircond) {
            addOnPrice.put("Air Conditioning", 50.00);
        }
        if (playRoom) {
            addOnPrice.put("Play Room", 10.00);
        }
        if (oneOnOneCare) {
            addOnPrice.put("One on One Care", 100.00);
        }

        // calculate day of stay
        totalPerNightPrice = perNightPrice * totalNumOfDays;
        addOnPrice.put("Day of Stay ( " + Main.convertCurrency(120) + " Per night) -> " + totalNumOfDays + " days", totalPerNightPrice);

        switch (foodPortion) {
            case XSMALL -> addOnPrice.put("Food Portion (XS)", 15.00);
            case SMALL -> addOnPrice.put("Food Portion (S)", 20.00);
            case MEDIUM -> addOnPrice.put("Food Portion (M)", 25.00);
            case LARGE -> addOnPrice.put("Food Portion (L))", 30.00);
            case XLARGE -> addOnPrice.put("Food Portion (XL))", 35.00);
        }
    }

    @Override
    public String toString() {
        return String.format("""
                Check-In Date     : %s
                Check-Out Date    : %s
                Day of Stay       : %d
                Total Basic Price : %s
                Food Portion      : %s
                Vegetarian         (RM  0.00) : %c
                Halal Food         (RM  0.00) : %c
                Air Conditioning   (RM 50.00) : %c
                Play Room          (RM 10.00) : %c
                One on One Care    (RM100.00) : %c
                """, Main.dateToString(getCheckInDate()),
                Main.dateToString(getCheckOutDate()),
                getTotalNumOfDays(),
                Main.convertCurrency(getTotalPerNightPrice()),
                Main.displaySize(getFoodPortion()),
                Main.booleanToSymbol(isVegetarian()),
                Main.booleanToSymbol(isHalalFood()),
                Main.booleanToSymbol(isAircond()),
                Main.booleanToSymbol(isPlayRoom()),
                Main.booleanToSymbol(isOneOnOneCare()));
    }
}
