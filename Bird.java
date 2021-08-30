

/**
 * The bird class store the basic information, which is owned by all pets that is inherited from the pets class such
 * as age, gender, color etc. , and certain specific information such as the total birds ages etc.
 * All the bird records created will be assigned an ID, generated by the system, and each of them represented a
 * particular bird. In this bird class, all the information within a record will be displayed in a table when there
 * is a necessity for reference or checking. For example, displaying the pet information after the user finishes
 * keying in the details for a quick checking. Users are limited to the shelter service option for pet birds.
 *
 * @author Tan Shi Jing
 */
public class Bird extends Pet implements Identifiable {

    private static int totalBirdAge;
    private static int totalBirdCount;
    private static int currentBirdCount;

    // Constructor
    /**
     * Creates a {@code Bird} class object when called
     *
     * @param age The age of bird
     * @param gender The gender of bird
     * @param color The color of bird
     * @param aggressive The aggressiveness of bird stored with {@code Level} enum
     * @param size The size of bird stored with {@code Size} enum
     */
    Bird(int age, char gender, String color, Level aggressive, Size size) {
        super(age, gender, color, aggressive, size);
        totalBirdCount++;
        currentBirdCount++;
        totalBirdAge+=age;
        this.ID = generateID(currentBirdCount);;
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
    public static void displayServices() {
        System.out.println("\n\n\tServices available");
        System.out.println("------------------------");
        System.out.printf("1. Shelter (%s per night)\n", Main.convertCurrency(120));
    }

    /**
     * Overrides the {@code toString()} method in {@code Object}.
     *
     * @return formatted {@code Bird} attributes
     */
    @Override
    public String toString() {
        return String.format("""
        	\n\t\t  HERE IS YOUR PET INFORMATION !
        		+---------------------------------------+
        		| Pet ID > %-6s                      |
        		| Type   > %-6s                       |
        		|---------------------------------------|
        		| Age    > %-3d      Color  > %-8s   |
        		| Gender > %c                            |
        		|                                       |
        		|---------------------------------------|
        		|            Characteristic             |
        		|---------------------------------------|
        		|                                       |
        		| Size  --> %-15s            |
        		| Aggressiveness --> %-10s         |
        		|                                       |
        		+---------------------------------------+""", getID(),
                getClass().getSimpleName(),
                getAge(),
                getColor(),
                getGender(),
                Main.displaySize(getSize()),
                Main.displayLevel(getAggressive()));
    }

    /**
     * Overrides the {@code generateID()} method in {@code Identifiable} interface.
     *
     * @param count The current {@code Bird} object total count
     * @return A formatted ID with bird in abbreviation at the front, current {@code Bird} count at the back
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
        return "BD" + additionalZero + idNum;
    }

    /**
     * Overrides the {@code equals()} method in {@code Object} and {@code Pet}.
     *
     * @param o Object to be compared
     * @return True if equals, else return false
     */
    @Override
    public boolean equals(Object o) {
        if (o instanceof Bird bird) {
            return bird.equals(this);
        }
        return false;
    }
}
