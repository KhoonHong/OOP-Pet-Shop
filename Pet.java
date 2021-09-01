

/**
 * Pet class is a superclass for the rabbit class, cat class, dog class and bird class. All of the four classes
 * inherited data and methods from it. The data field being inherited are the age, gender, color, aggressive, size,
 * ID and total number of the particular pet kinds registered in the shop. The ID for the pets will be generated
 * automatically in the subclass as they are of different kinds. The total amount of pet registered will only be
 * calculated among the subclass.
 *
 * @author Tan Shi Jing
 */
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
    /**
     * {@code Pet} is an abstract (super) class, thus can't be used to create objects
     *
     * @param age The age of pet
     * @param gender The gender of pet
     * @param color The color of pet
     * @param aggressive The aggressiveness of pet stored in {@code Level} enum
     * @param size The size of pet stored in {@code Size} enum
     */
    protected Pet(int age, char gender, String color, Level aggressive, Size size) {
        this.age = age;
        this.gender = gender;
        this.color = color;
        this.aggressive = aggressive;
        this.size = size;
        totalPetCount++;
        currentPetCount++;
    }

    protected Pet() {
        // no-args
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

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
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

    /**
     * Prints the {@code toString} method of the subclasses
     */
    public void displayPet() {
        System.out.println(this);
    }

    /**
     * Overrides the {@code equals()} method in {@code Object}.
     *
     * @param o Object to be compared
     * @return True if equals, else return false
     */
    /*
    @Override
    public boolean equals(Object o) {
        if (o instanceof Pet pet) {
            return pet.equals(this);
        }
        return false;
    }

     */
}
