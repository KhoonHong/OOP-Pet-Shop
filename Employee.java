import java.time.LocalDateTime;

public class Employee extends Person {

    private double salary;
    private LocalDateTime startWorkDate;
    private Schedule workSchedule;
    private static int totalEmpCount;
    private static int currentEmployeeCount;

    // Constructor
    Employee() {
        // no-args
        this.startWorkDate = LocalDateTime.now();
        totalEmpCount++;
        currentEmployeeCount++;
    }

    Employee(double salary, Schedule workSchedule, String firstName, String lastName, int age, String tel, char gender, String id, LocalDateTime dob, Address address, String email) {
        super(firstName, lastName, age, tel, gender, id, dob, address, email);
        this.salary = salary;
        this.workSchedule = workSchedule;
        this.startWorkDate = LocalDateTime.now();
        totalEmpCount++;
        currentEmployeeCount++;
    }

    // Getter & Setter
    public static int getCurrentEmployeeCount() {
        return currentEmployeeCount;
    }

    public static void setCurrentEmployeeCount(int currentEmployeeCount) {
        Employee.currentEmployeeCount = currentEmployeeCount;
    }

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

    public Schedule getWorkSchedule() {
        return workSchedule;
    }

    public void setWorkSchedule(Schedule workSchedule) {
        this.workSchedule = workSchedule;
    }

    public static int getTotalEmpCount() {
        return totalEmpCount;
    }

    public static void setTotalEmpCount(int totalEmpCount) {
        Employee.totalEmpCount = totalEmpCount;
    }

    // Methods

}
