public class Bird extends Pet implements Identifiable {

    private static int totalBirdAge;
    private static int totalBirdCount;
    private static int currentBirdCount;

    // Constructor
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
        		+---------------------------------------+""", getId(),
                getClass().getSimpleName(),
                getAge(),
                getColor(),
                getGender(),
                Main.displaySize(getSize()),
                Main.displayLevel(getAggressive()));
    }

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
}
