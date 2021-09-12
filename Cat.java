

/**
 * The cat class store the basic information, inherited from the pet class such as age, gender, color etc. ,
 * and certain specific data related to the cat such as the neuter status, pregnancy status etc.
 * In the cat class, users will have to key in more details than the basic one, which is the pregnancy status. When
 * a user creates a new pet cat record, the information keyed in will be stored in the cat class and partitioned
 * with the others through a new ID. All the cat information within an ID will be displayed in a table when there
 * is a necessity. For example, displaying the pet information after the user finishes keying in the details for a
 * quick checking.
 * Cats are eligible for all services provided in this pet shop.
 *
 * @author Tan Shi Jing
 */
public class Cat extends Pet implements Identifiable {

    private boolean neutered;
    private boolean pregnant = false;
    private static int totalCatAge;
    private static int totalCatCount;
    private static int currentCatCount;

    // Constructor
    /**
     * Creates a {@code Cat} class object when called
     *
     * @param neutered Store cat neutered boolean status
     * @param age The age of cat
     * @param gender The gender of cat
     * @param color The color of cat
     * @param aggressive The aggressiveness of cat stored with {@code Level} enum
     * @param size The size of cat stored with {@code Size} enum
     */
    Cat(boolean neutered, int age, char gender, String color, Level aggressive, Size size) {
        super(age, gender, color, aggressive, size);
        this.neutered = neutered;
        if (gender == 'f' && !neutered) {
            this.pregnant = Main.promptYesNo("Is your cat pregnant? (Y/N) > ");
        }
        totalCatCount++;
        currentCatCount++;
        totalCatAge+=age;
        this.ID = generateID(currentCatCount);
    }

    Cat() {
        // no-args
    }

    Cat(boolean neutered, int age, char gender, String color, Level aggressive, Size size, boolean pregnant) {
        super(age, gender, color, aggressive, size);
        this.neutered = neutered;
        this.pregnant = pregnant;
        totalCatAge += age;
        totalCatCount++;
        currentCatCount++;
        this.ID = generateID(currentCatCount);
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
    public static void displayServices() {
        System.out.println("\n\n\t  Services available");
        System.out.println("  ------------------------");
        System.out.printf("  1. Groom   (%s)\n", Main.convertCurrency(80));
        System.out.printf("  2. Bath    (%s)\n", Main.convertCurrency(40));
        System.out.printf("  3. Massage (%s)\n", Main.convertCurrency(50));
        System.out.printf("  4. Shelter (%s per night)\n", Main.convertCurrency(120));
    }

    /**
     * Overrides the {@code toString()} method in {@code Object}.
     *
     * @return formatted {@code Cat} attributes
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
     * @param count The current {@code Cat} object total count
     * @return A formatted ID with cat in abbreviation at the front, current {@code Cat} count at the back
     */
    @Override
    public String generateID(int count) {
        String additionalZero = "";
        String idNum = String.valueOf(count);
        if (idNum.length() == 1) {
            additionalZero = "0000";
        } else if (idNum.length() == 2) {
            additionalZero = "000";
        } else if (idNum.length() == 3) {
            additionalZero = "00";
        } else if (idNum.length() == 4) {
            additionalZero = "0";
        }
        return "CT" + additionalZero + idNum;
    }

    /**
     * Overrides the {@code equals()} method in {@code Object} and {@code Pet}.
     *
     * @param o Object to be compared
     * @return True if equals, else return false
     */
    @Override
    public boolean equals(Object o) {
        if (o instanceof Cat cat) {
            return super.equals(cat) &&
                    cat.isNeutered() == this.isNeutered() &&
                    cat.isPregnant() == this.isPregnant();
        }
        return false;
    }
}
