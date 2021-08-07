public class Bird extends Pet {

    private static int totalBirdAge;
    private static int totalBirdCount;
    private static int currentBirdCount;

    // Constructor
    Bird() {
        // no-args
    }

    Bird(int age, char gender, String ID, String color, Level aggressive, Size size) {
        super(age, gender, ID, color, aggressive, size);
        totalBirdCount++;
        currentBirdCount++;
        totalBirdAge+=age;
    }

    // Getter and Setter
    public static int getTotalBirdAge() {
        return totalBirdAge;
    }

    public static void setTotalBirdAge(int totalBirdAge) {
        Bird.totalBirdAge = totalBirdAge;
    }

    public static int getTotalBirdCount() {
        return totalBirdCount;
    }

    public static void setTotalBirdCount(int totalBirdCount) {
        Bird.totalBirdCount = totalBirdCount;
    }

    public static int getCurrentBirdCount() {
        return currentBirdCount;
    }

    public static void setCurrentBirdCount(int currentBirdCount) {
        Bird.currentBirdCount = currentBirdCount;
    }

    // Methods

}
