import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;

public class Customer extends Person {

    private LocalDateTime regDate;
    private ArrayList<Pet> pets;
    private ArrayList<Reservation> reservation;
    private ArrayList<Reservation> reservationHistory;
    private ArrayList<Billing> bill;
    private ArrayList<Billing> billHistory;
    private ArrayList<Card> cards;
    private static int currentCustCount;
    private static int totalCustCount;

    // Constructor
    Customer() {
        // no-args
        this.regDate = LocalDateTime.now();
        currentCustCount++;
        totalCustCount++;
    }

    Customer(ArrayList<Pet> pets,
                    ArrayList<Reservation> reservation,
                    ArrayList<Reservation> reservationHistory,
                    ArrayList<Billing> bill,
                    ArrayList<Billing> billHistory,
                    ArrayList<Card> cards,
                    String firstName, String lastName, int age, String tel, char gender, String id, LocalDateTime dob, Address address, String email) {
        super(firstName, lastName, age, tel, gender, id, dob, address, email);
        this.pets = pets;
        this.reservation = reservation;
        this.reservationHistory = reservationHistory;
        this.bill = bill;
        this.billHistory = billHistory;
        this.cards = cards;
        this.regDate = LocalDateTime.now();
        currentCustCount++;
        totalCustCount++;
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

    public ArrayList<Reservation> getReservationHistory() {
        return reservationHistory;
    }

    public void setReservationHistory(ArrayList<Reservation> reservationHistory) {
        this.reservationHistory = reservationHistory;
    }

    public ArrayList<Billing> getBill() {
        return bill;
    }

    public void setBill(ArrayList<Billing> bill) {
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

}
