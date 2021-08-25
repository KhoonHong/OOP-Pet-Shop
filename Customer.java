import java.time.LocalDateTime;
import java.util.ArrayList;

public class Customer extends Person implements Displayable, Identifiable {

    private LocalDateTime regDate;
    private ArrayList<Pet> pets = new ArrayList<>();
    private ArrayList<Reservation> reservation = new ArrayList<>();
    private Billing bill;
    private ArrayList<Billing> billHistory = new ArrayList<>();
    private ArrayList<Card> cards = new ArrayList<>();
    private static int currentCustCount;
    private static int totalCustCount;

    // Constructor
    Customer() {
        // no-args
        this.regDate = LocalDateTime.now();
        this.age = (LocalDateTime.now().getYear() - dob.getYear());
        currentCustCount++;
        totalCustCount++;
        this.id = generateID(currentCustCount);;
    }

    Customer(String firstName, String lastName, String tel, char gender, LocalDateTime dob, Address address, String email, String username, String password) {
        super(firstName, lastName, tel, gender, dob, address, email, username, password);
        this.regDate = LocalDateTime.now();
        this.age = (LocalDateTime.now().getYear() - dob.getYear());
        currentCustCount++;
        totalCustCount++;
        this.id = generateID(currentCustCount);;
    }

    // Getter & Setter
    public LocalDateTime getRegDate() {
        return regDate;
    }

    public void setRegDate(LocalDateTime regDate) {
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
    public void addPet(Pet pet) {
        this.getPets().add(pet);
    }

    public void addReservation(Reservation reservation) {
        this.getReservation().add(reservation);
    }

    public void addCard(Card card) {
        this.getCards().add(card);
    }

    public void removePet(Pet pet) {
        this.getPets().remove(pet);
    }

    public void removeReservation(Reservation reservation) {
        this.getReservation().remove(reservation);
    }

    public void removeCard(Card card) {
        this.getCards().remove(card);
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Customer) {
            return ((Customer) o).getUsername().equals(this.username) && (((Customer) o).getPassword().equals(this.password));
        }
        return false;
    }

    @Override
    public String displayRow() {
        return String.format("""
        	|  %6s   |%s %-5s| %3s   |     %c    |  %11s   | %s |%-20s | %-15s|   %s  |%-30s     |""",
                getId(),
                getFirstName(),
                getLastName(),
                getAge(),
                getGender(),
                getTel(),
                Main.dateToString(getDob()),
                getEmail(),
                getUsername(),
                Main.dateToString(getRegDate()),
                getAddress().displayRow());
    }


    @Override
    public String toString() {
        return String.format("""
        		ID : %s
        		+--------------------------------------------------------------------------+
        		|                                    |                                     |
        		|   First Name > %-20s|   Age        > %-4s                 |
        		|	Last Name  > %-20s|   Gender     > %c                    |
        		|	                                 |   Phone No.  > %-12s         |
        		|                                    |   Birth Date > %-8s           |
        		|--------------------------------------------------------------------------|
        		|                                                                          |
        		|                                        Register Date > %-10s        |
        		|                                                                          |
        		|   Username > %-20s                                        |
        		|   Email    > %-30s                              |
        		|   Address  > %-20s            |
        		|                                                                          |
        		+--------------------------------------------------------------------------+""",
                getId(),
                getFirstName(),
                getAge(),
                getLastName(),
                getGender(),
                getTel(),
                Main.dateToString(getDob()),
                Main.dateToString(getRegDate()),
                getUsername(),
                getEmail(),
                getAddress().displayRow());
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
        return "C" + additionalZero + idNum;
    }
}
