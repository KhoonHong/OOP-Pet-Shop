import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;

public class Shelter extends Service {
    private boolean vegetarian;
    private boolean halalFood;
    private boolean aircond;
    private Size foodPortion;
    private LocalDateTime checkInDate;
    private LocalDateTime checkOutDate;
    private int totalNumOfDays;
    private boolean playRoom;
    private boolean oneOnOneCare;

    // Constructor
    Shelter() {
        // no-args
    }

    Shelter(boolean vegetarian,
                   boolean halalFood,
                   boolean aircond,
                   Size foodPortion,
            LocalDateTime checkInDate,
            LocalDateTime checkOutDate,
                   int totalNumOfDays,
                   boolean playRoom,
                   boolean oneOnOneCare,
                   String desc,
                   double price,
                   HashMap<String, Double> addOnPrice) {
        super(desc);
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

    // Getter & Setter
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

    public double getPrice() {
        return Shelter.price;
    }

    public void setPrice(double price) {
        Shelter.price = price;
    }

    public HashMap<String, Double> getAddOnPrice() {
        return Shelter.addOnPrice;
    }

    public void setAddOnPrice(HashMap<String, Double> addOnPrice) {
        Shelter.addOnPrice = addOnPrice;
    }

    // Methods

}
