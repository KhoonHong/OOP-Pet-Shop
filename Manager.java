import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;

public class Manager extends Person {
    private double salary;
    private LocalDateTime startWorkDate;
    private static int totalManagerCount;
    private static int currentManagerCount;
    private static ArrayList<Promotion> promotions;

    // Constructor
    Manager() {
        // no-args
        this.startWorkDate = LocalDateTime.now();
        currentManagerCount++;
        totalManagerCount++;
    }

    Manager(double salary,
            String firstName,
            String lastName,
            int age,
            String tel,
            char gender,
            String id,
            LocalDateTime dob,
            Address address,
            String email) {
        super(firstName, lastName, age, tel, gender, id, dob, address, email);
        this.salary = salary;
        this.startWorkDate = LocalDateTime.now();
        currentManagerCount++;
        totalManagerCount++;
    }

    // Getter & Setter
    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public LocalDateTime getStartWorkDate() {
        return startWorkDate;
    }

    public void setStartWorkDate(LocalDateTime startWorkDate) {
        this.startWorkDate = startWorkDate;
    }

    public static int getTotalManagerCount() {
        return totalManagerCount;
    }

    public static void setTotalManagerCount(int totalManagerCount) {
        Manager.totalManagerCount = totalManagerCount;
    }

    public static ArrayList<Promotion> getPromotions() {
        return promotions;
    }

    public static void setPromotions(ArrayList<Promotion> promotions) {
        Manager.promotions = promotions;
    }

    public static int getCurrentManagerCount() {
        return currentManagerCount;
    }

    public static void setCurrentManagerCount(int currentManagerCount) {
        Manager.currentManagerCount = currentManagerCount;
    }
    // Methods
}
