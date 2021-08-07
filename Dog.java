public class Dog extends Pet {

    private boolean neutered;
    private boolean pregnant;
    private static int totalDogAge;
    private static int totalDogCount;
    private static int currentDogCount;

    // Constructor
    Dog() {
        // no-args
    }

    Dog(boolean neutered, boolean pregnant, int age, char gender, String ID, String color, Level aggressive, Size size) {
        super(age, gender, ID, color, aggressive, size);
        this.neutered = neutered;
        this.pregnant = pregnant;
        totalDogAge+=age;
        totalDogCount++;
        currentDogCount++;
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

    public static int getTotalDogAge() {
        return totalDogAge;
    }

    public static void setTotalDogAge(int totalDogAge) {
        Dog.totalDogAge = totalDogAge;
    }

    public static int getTotalDogCount() {
        return totalDogCount;
    }

    public static void setTotalDogCount(int totalDogCount) {
        Dog.totalDogCount = totalDogCount;
    }

    public static int getCurrentDogCount() {
        return currentDogCount;
    }

    public static void setCurrentDogCount(int currentDogCount) {
        Dog.currentDogCount = currentDogCount;
    }

    // Methods
}
