public class Cat extends Pet implements Identifiable {

    private boolean neutered;
    private boolean pregnant;
    private static int totalCatAge;
    private static int totalCatCount;
    private static int currentCatCount;

    // Constructor

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
        System.out.println("\n\n\tServices available");
        System.out.println("------------------------");
        System.out.printf("1. Groom (%s)\n", Main.convertCurrency(80));
        System.out.printf("2. Bath (%s)\n", Main.convertCurrency(40));
        System.out.printf("3. Massage (%s)\n", Main.convertCurrency(50));
        System.out.printf("4. Shelter (%s per night)\n", Main.convertCurrency(120));
    }

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
        		| Size     --> %-15s          |
        		| Neutered --> %c                        |
        		| Pregnant --> %c                        |
        		| Aggressiveness --> %-10s         |
        		|                                       |
        		+---------------------------------------+""", getId(),
                getClass().getSimpleName(),
                getAge(),
                getColor(),
                getGender(),
                Main.displaySize(getSize()),
                Main.booleanToSymbol(isNeutered()),
                Main.booleanToSymbol(isPregnant()),
                Main.displayLevel(getAggressive()));
    }

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
}
