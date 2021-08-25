abstract class Pet {
    protected int age;
    protected char gender;
    protected String ID;
    protected String color;
    protected Level aggressive;
    protected Size size;
    private static int totalPetCount;
    private static int currentPetCount;

    // Constructor
    protected Pet(int age, char gender, String color, Level aggressive, Size size) {
        this.age = age;
        this.gender = gender;
        this.color = color;
        this.aggressive = aggressive;
        this.size = size;
        totalPetCount++;
        currentPetCount++;
    }

    // Getter & Setter
    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public char getGender() {
        return gender;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }

    public String getId() {
        return ID;
    }

    public void setId(String ID) {
        this.ID = ID;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Level getAggressive() {
        return aggressive;
    }

    public void setAggressive(Level aggressive) {
        this.aggressive = aggressive;
    }

    public Size getSize() {
        return size;
    }

    public void setSize(Size size) {
        this.size = size;
    }

    public static int getTotalPetCount() {
        return totalPetCount;
    }

    public static void setTotalPetCount(int totalPetCount) {
        Pet.totalPetCount = totalPetCount;
    }

    public static int getCurrentPetCount() {
        return currentPetCount;
    }

    public static void setCurrentPetCount(int currentPetCount) {
        Pet.currentPetCount = currentPetCount;
    }

    // Methods
    public void displayPet() {
        System.out.println(this);
    }
}
