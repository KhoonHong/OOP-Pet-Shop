

/**
 * Rabbit class store the basic information, inherited from the pet class such as age, gender, color etc., and
 * some specific data related to the rabbit such as the neuter status, pregnancy status etc.
 * In the rabbit class, users will have to key in more details than the basic one, which is the pregnancy status.
 * When a user creates a new pet rabbit record, the information keyed in will be stored in the rabbit class and
 * partitioned with the others through a new ID. All the rabbit information within an ID will be displayed in a
 * table when there is a necessity. For example, displaying the pet information after the user finishes keying in
 * the details for a quick checking.
 * Rabbit are eligible for all services provided except massage in this pet shop.
 *
 * @author Tan Shi Jing
 */
public class Rabbit extends Pet implements Identifiable {

    private boolean neutered;
    private boolean pregnant = false;
    private static int totalRabbitAge;
    private static int totalRabbitCount;
    private static int currentRabbitCount;

    // Constructor
    /**
     * Creates a {@code Rabbit} class object when called
     *
     * @param neutered Store rabbit neutered boolean status
     * @param age The age of rabbit
     * @param gender The gender of rabbit
     * @param color The color of rabbit
     * @param aggressive The aggressiveness of rabbit stored with {@code Level} enum
     * @param size The size of rabbit stored with {@code Size} enum
     */
    Rabbit(boolean neutered, int age, char gender, String color, Level aggressive, Size size) {
        super(age, gender, color, aggressive, size);
        this.neutered = neutered;
        if (gender == 'f' && !neutered) {
            this.pregnant = Main.promptYesNo("Is your rabbit pregnant? (Y/N) > ");
        }
        totalRabbitAge+=age;
        totalRabbitCount++;
        currentRabbitCount++;
        this.ID = generateID(currentRabbitCount);
    }

    Rabbit(boolean neutered, int age, char gender, String color, Level aggressive, Size size, boolean pregnant) {
        super(age, gender, color, aggressive, size);
        totalRabbitAge+=age;
        totalRabbitCount++;
        currentRabbitCount++;
        this.neutered = neutered;
        this.ID = generateID(currentRabbitCount);
        this.pregnant = pregnant;
        // no-args
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
    public static void displayServices() {
        System.out.println("\n\n\t  Services available");
        System.out.println("  ------------------------");
        System.out.printf("  1. Groom   (%s)\n", Main.convertCurrency(80));
        System.out.printf("  2. Bath    (%s)\n", Main.convertCurrency(40));
        System.out.printf("  3. Shelter (%s per night)\n", Main.convertCurrency(120));
    }

    /**
     * Overrides the {@code toString()} method in {@code Object}.
     *
     * @return formatted {@code Rabbit} attributes
     */
    @Override
    public String toString() {
        return String.format("""
        	\n        HERE IS YOUR PET INFORMATION !
        	  +---------------------------------------+
        	  | Pet ID > %-6s                      |
        	  | Type   > %-6s                       |
        	  |---------------------------------------|
        	  | Age    > %-3d        Color  > %-8s |
        	  | Gender > %c                            |
        	  |                                       |
        	  |---------------------------------------|
        	  |            Characteristic             |
        	  |---------------------------------------|
        	  | Size     --> %-15s          |
        	  | Neutered --> %c                        |
        	  | Pregnant --> %c                        |
        	  | Aggressiveness --> %-10s         |
        	  |                                       |
        	  +---------------------------------------+""", getID(),
                getClass().getSimpleName(),
                getAge(),
                getColor(),
                getGender(),
                Main.displaySize(getSize()),
                Main.booleanToSymbol(isNeutered()),
                Main.booleanToSymbol(isPregnant()),
                Main.displayLevel(getAggressive()));
    }

    /**
     * Overrides the {@code generateID()} method in {@code Identifiable} interface.
     *
     * @param count The current {@code Rabbit} object total count
     * @return A formatted ID with rabbit in abbreviation at the front, current {@code Rabbit} count at the back
     */
    @Override
    public String generateID(int count){
        String additionalZero = "";
        String idNum = String.valueOf(count);
        if(idNum.length() == 1){
            additionalZero = "0000";
        }
        else if (idNum.length() == 2){
            additionalZero = "000";
        }
        else if (idNum.length() == 3){
            additionalZero = "00";
        }
        else if (idNum.length() == 4){
            additionalZero = "0";
        }
        return "RT" + additionalZero + idNum;
    }

    /**
     * Overrides the {@code equals()} method in {@code Object} and {@code Pet}.
     *
     * @param o Object to be compared
     * @return True if equals, else return false
     */
    @Override
    public boolean equals(Object o) {
        if (o instanceof Rabbit rabbit) {
            return super.equals(rabbit) &&
                    rabbit.isNeutered() == this.isNeutered() &&
                    rabbit.isPregnant() == this.isPregnant();
        }
        return false;
    }
}
