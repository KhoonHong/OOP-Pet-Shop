import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Objects;


/**
 * The customer class is made up of a combination of data and methods inherited from the person class together with
 * the data and methods specifically for customers.
 * Other than the data inherited, data like registration date, pet, reservation, bill made, and cards are stored in
 * this class on different ID generated by the system. These data will be initialized in the customer class and
 * stored automatically since their first entry into the system. The display of the customers' information will
 * take place when the users request or in the necessary situation.
 * The customers class also stores the total number of customers in the pet shop, providing more details
 * to the owner.
 *
 * @author Chan Jia Wei
 */
public class Customer extends Person implements Displayable, Identifiable {

    private LocalDate regDate;
    private ArrayList<Pet> pets = new ArrayList<>();
    private ArrayList<Reservation> reservation = new ArrayList<>();
    private Billing bill;
    private ArrayList<Billing> billHistory = new ArrayList<>();
    private ArrayList<Card> cards = new ArrayList<>();
    private static int currentCustCount;
    private static int totalCustCount;

    // Constructor
    public Customer() {
        // no-args
        this.regDate = LocalDate.now();
        currentCustCount++;
        totalCustCount++;
        this.id = generateID(currentCustCount);;
    }

    /**
     * Creates a {@code Customer} class object when called
     *
     * @param firstName Customer first name
     * @param lastName Customer last name
     * @param tel Customer phone number
     * @param gender Customer gender
     * @param dob Customer date of birth
     * @param address Customer housing address
     * @param email Customer email address
     * @param username Customer username
     * @param password Customer password
     */
    public Customer(String firstName, String lastName, String tel, char gender, LocalDate dob, Address address, String email, String username, String password) {
        super(firstName, lastName, tel, gender, dob, address, email, username, password);
        this.regDate = LocalDate.now();
        this.age = (LocalDate.now().getYear() - dob.getYear());
        currentCustCount++;
        totalCustCount++;
        this.id = generateID(currentCustCount);;
    }

    public Customer(String firstName, String lastName, String tel, char gender, LocalDate dob, Address address, String email, String username, String password, LocalDate regDate) {
        super(firstName, lastName, tel, gender, dob, address, email, username, password);
        this.regDate = regDate;
        this.age = (LocalDate.now().getYear() - dob.getYear());
        currentCustCount++;
        totalCustCount++;
        this.id = generateID(currentCustCount);;
    }

    // Getter & Setter
    public LocalDate getRegDate() {
        return regDate;
    }

    public void setRegDate(LocalDate regDate) {
        this.regDate = regDate;
    }

    public ArrayList<Pet> getPets() {
        return pets;
    }

    public void setPets(ArrayList<Pet> pets) {
        this.pets = pets;
    }

    public ArrayList<Reservation> getReservation() {
        return reservation;
    }

    public void setReservation(ArrayList<Reservation> reservation) {
        this.reservation = reservation;
    }

    public Billing getBill() {
        return bill;
    }

    public void setBill(Billing bill) {
        this.bill = bill;
    }

    public ArrayList<Billing> getBillHistory() {
        return billHistory;
    }

    public void setBillHistory(ArrayList<Billing> billHistory) {
        this.billHistory = billHistory;
    }

    public ArrayList<Card> getCards() {
        return cards;
    }

    public void setCards(ArrayList<Card> cards) {
        this.cards = cards;
    }

    public static int getCurrentCustCount() {
        return currentCustCount;
    }

    public static void setCurrentCustCount(int currentCustCount) {
        Customer.currentCustCount = currentCustCount;
    }

    public static int getTotalCustCount() {
        return totalCustCount;
    }

    public static void setTotalCustCount(int totalCustCount) {
        Customer.totalCustCount = totalCustCount;
    }

    // Methods

    /**
     * Add a {@code Pet} object into {@code pets} ArrayList
     *
     * @param pet {@code Pet} object created by customer
     */
    public void addPet(Pet pet) {
        this.getPets().add(pet);
    }

    /**
     * Add a {@code Reservation} object into {@code reservation} ArrayList
     *
     * @param reservation {@code Reservation} object created by customer
     */
    public void addReservation(Reservation reservation) {
        this.getReservation().add(reservation);
    }

    /**
     * Add a {@code Card} object into {@code cards} ArrayList
     *
     * @param card {@code Card} object created by customer
     */
    public void addCard(Card card) {
        this.getCards().add(card);
    }

    /**
     * Remove a {@code Pet} object from {@code pets} ArrayList
     *
     * @param pet {@code Pet} object created by customer
     */
    public void removePet(Pet pet) {
        this.getPets().remove(pet);
        Pet.setTotalPetCount(Pet.getTotalPetCount()-1); // reduce by one
    }

    /**
     * Remove a {@code Reservation} object from {@code reservation} ArrayList
     *
     * @param reservation {@code Reservation} object created by customer
     */
    public void removeReservation(Reservation reservation) {
        this.getReservation().remove(reservation);
    }

    /**
     * Remove a {@code Card} object from {@code cards} ArrayList
     *
     * @param card {@code Card} object created by customer
     */
    public void removeCard(Card card) {
        this.getCards().remove(card);
    }

    /**
     * Overrides the {@code equals()} method in {@code Object} and {@code Person}.
     *
     * @param o Object to be compared
     * @return True if equals, else return false
     */
    @Override
    public boolean equals(Object o) {
        if (o instanceof Customer customer) {
            return super.equals(customer) &&
                    Objects.equals(customer.getRegDate(), this.getRegDate())&&
                    Objects.equals(customer.getPets(), this.getPets()) &&
                    Objects.equals(customer.getReservation(), this.getReservation()) &&
                    Objects.equals(customer.getBillHistory(), this.getBillHistory()) &&
                    Objects.equals(customer.getCards(),this.getCards());
        }
        return false;
    }

    /**
     * Overrides the {@code displayRow()} method in {@code Displayable} interface.
     *
     * @return Formatted {@code Customer} attribute in row
     */

    @Override
    public String displayRow() {
        return String.format("""
        	|  %6s |%-17s| %3s   |   %c    |  %11s   | %s |%-30s| %-15s|   %s  |%-60s|""",
                getID(),
                fullName(),
                getAge(),
                getGender(),
                getTel(),
                Main.dateToString(getDob()),
                getEmail(),
                getUsername(),
                Main.dateToString(getRegDate()),
                getAddress().limitAddress());
    }

    /**
     * Overrides the {@code toString()} method in {@code Object}.
     *
     * @return formatted {@code Customer} attributes
     */
    @Override
    public String toString() {
        return String.format("""
        	       ID : %s
        	      +--------------------------------------------------------------------------+
        	      |                                    |                                     |
        	      |   First Name > %-20s|   Age        > %-4s                 |
        	      |   Last Name  > %-20s|   Gender     > %c                    |
        	      |	                                   |   Phone No.  > %-12s         |
        	      |                                    |   Birth Date > %-10s           |
        	      |--------------------------------------------------------------------------|
        	      |                                                                          |
        	      |                                        Register Date > %-10s        |
        	      |                                                                          |
        	      |   Username > %-60s|
        	      |   Email    > %-60s|
        	      |   Address  > %-60s|
        	      |                                                                          |
        	      +--------------------------------------------------------------------------+
        	   	""",
                getID(),
                getFirstName(),
                getAge(),
                getLastName(),
                getGender(),
                getTel(),
                Main.dateToString(getDob()),
                Main.dateToString(getRegDate()),
                getUsername(),
                getEmail(),
                getAddress().limitAddress());
    }

    /**
     * Overrides the {@code generateID()} method in {@code Identifiable} interface.
     *
     * @param count The current {@code Customer} object total count
     * @return A formatted ID with customer in abbreviation at the front, current {@code Customer} count at the back
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
        return "C" + additionalZero + idNum;
    }
}
