

/**
 * The dog class store the basic information, inherited from the pet class such as age, gender, color etc. ,
 * and some specific information related to dogs such as the neuter status, pregnancy status etc.
 * In the dog class, users will have to key in the neuter, pregnancy status of the pet other than the basic data.
 * When a user creates a new pet dog record, the information keyed in will be stored in the dog class and divided
 * among the other existing records through a unique ID, which is generated every time a new record is keyed in.
 * All the dog information within an ID will be displayed in a table when requested. For example, displaying the
 * pet information after the user finishes keying in the details for a quick checking.
 * Dogs are eligible for all the services provided in this pet shop.
 *
 * @author Tan Shi Jing
 */
public class Dog extends Pet implements Identifiable {

    private boolean neutered;
    private boolean pregnant;
    private static int totalDogAge;
    private static int totalDogCount;
    private static int currentDogCount;

    // Constructor

    /**
     * Creates a {@code Dog} class object when called
     *
     * @param neutered Store dog neutered boolean status
     * @param age The age of dog
     * @param gender The gender of dog
     * @param color The color of dog
     * @param aggressive The aggressiveness of dog stored with {@code Level} enum
     * @param size The size of dog stored with {@code Size} enum
     */
    Dog(boolean neutered, int age, char gender, String color, Level aggressive, Size size) {
        super(age, gender, color, aggressive, size);
        this.neutered = neutered;
        if (gender == 'f'  && !neutered) {
            this.pregnant = Main.promptYesNo("Is your doggo pregnant? (Y/N) > ");
        }
        totalDogAge+=age;
        totalDogCount++;
        currentDogCount++;
        this.ID = generateID(currentDogCount);
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
    public static void displayServices() {
        System.out.println("\n\n\tServices available");
        System.out.println("------------------------");
        System.out.printf("1. Groom (%s)\n", Main.convertCurrency(80));
        System.out.printf("2. Bath (%s)\n", Main.convertCurrency(40));
        System.out.printf("3. Massage (%s)\n", Main.convertCurrency(50));
        System.out.printf("4. Shelter (%s per night)\n", Main.convertCurrency(120));
    }

    /**
     * Overrides the {@code toString()} method in {@code Object}.
     *
     * @return formatted {@code Dog} attributes
     */
    @Override
    public String toString() {
        return String.format("""
                        									
                        \t\t  HERE IS YOUR PET INFORMATION !
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
     * @param count The current {@code Dog} object total count
     * @return A formatted ID with dog in abbreviation at the front, current {@code Dog} count at the back
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
        return "DG" + additionalZero + idNum;
    }

    /**
     * Overrides the {@code equals()} method in {@code Object} and {@code Pet}.
     *
     * @param o Object to be compared
     * @return True if equals, else return false
     */
    @Override
    public boolean equals(Object o) {
        if (o instanceof Dog dog) {
            return dog.equals(this);
        }
        return false;
    }
}
