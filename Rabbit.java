public class Rabbit extends Pet {

    private boolean neutered;
    private boolean pregnant;
    private static int totalRabbitAge;
    private static int totalRabbitCount;
    private static int currentRabbitCount;

    // Constructor
    Rabbit() {
        // no-args
    }

    Rabbit(boolean neutered, boolean pregnant, int age, char gender, String ID, String color, Level aggressive, Size size) {
        super(age, gender, ID, color, aggressive, size);
        this.neutered = neutered;
        this.pregnant = pregnant;
        totalRabbitAge+=age;
        totalRabbitCount++;
        currentRabbitCount++;
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

    public static int getTotalRabbitAge() {
        return totalRabbitAge;
    }

    public static void setTotalRabbitAge(int totalRabbitAge) {
        Rabbit.totalRabbitAge = totalRabbitAge;
    }

    public static int getTotalRabbitCount() {
        return totalRabbitCount;
    }

    public static void setTotalRabbitCount(int totalRabbitCount) {
        Rabbit.totalRabbitCount = totalRabbitCount;
    }

    public static int getCurrentRabbitCount() {
        return currentRabbitCount;
    }

    public static void setCurrentRabbitCount(int currentRabbitCount) {
        Rabbit.currentRabbitCount = currentRabbitCount;
    }
    // Methods

}
