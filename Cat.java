public class Cat extends Pet {

    private boolean neutered;
    private boolean pregnant;
    private static int totalCatAge;
    private static int totalCatCount;
    private static int currentCatCount;

    // Constructor
    Cat() {
        // no-args
    }

    Cat(boolean neutered, boolean pregnant, int age, char gender, String ID, String color, Level aggressive, Size size) {
        super(age, gender, ID, color, aggressive, size);
        this.neutered = neutered;
        this.pregnant = pregnant;
        totalCatCount++;
        currentCatCount++;
        totalCatAge+=age;
    }

    // Getter & Setter
    public boolean isNeutered() {
        return neutered;
    }

    public void setNeutered(boolean neutered) {
        this.neutered = neutered;
    }

    public boolean isPregnant() {
        return pregnant;
    }

    public void setPregnant(boolean pregnant) {
        this.pregnant = pregnant;
    }

    public static int getTotalCatAge() {
        return totalCatAge;
    }

    public static void setTotalCatAge(int totalCatAge) {
        Cat.totalCatAge = totalCatAge;
    }

    public static int getTotalCatCount() {
        return totalCatCount;
    }

    public static void setTotalCatCount(int totalCatCount) {
        Cat.totalCatCount = totalCatCount;
    }

    public static int getCurrentCatCount() {
        return currentCatCount;
    }

    public static void setCurrentCatCount(int currentCatCount) {
        Cat.currentCatCount = currentCatCount;
    }

    // Methods

}
