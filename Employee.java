import java.time.LocalDateTime;

public class Employee extends Person implements Displayable, Identifiable {

    private double salary;
    private LocalDateTime startWorkDate;
    private Schedule workSchedule = new Schedule();
    private static int totalEmpCount;
    private static int currentEmployeeCount;

    // Constructor
    Employee() {
        // no-args
        this.startWorkDate = LocalDateTime.now();
        totalEmpCount++;
        currentEmployeeCount++;
        this.age = (LocalDateTime.now().getYear() - dob.getYear());
        this.id = generateID(currentEmployeeCount);
    }

    Employee(String firstName, String lastName, String tel, char gender, LocalDateTime dob, Address address, String email, String username, String password, double salary) {
        super(firstName, lastName, tel, gender, dob, address, email, username, password);
        this.salary = salary;
        this.age = (LocalDateTime.now().getYear() - dob.getYear());
        this.startWorkDate = LocalDateTime.now();
        totalEmpCount++;
        currentEmployeeCount++;
        this.id = generateID(currentEmployeeCount);
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
    @Override
    public String displayRow() {
        return String.format("""
        	| %6s |%s %-5s | %3s   |    %c   |  %11s   | %s |%-6s |%-20s | %-15s|   %s  |%-30s     |""",
                getId(),
                getFirstName(),
                getLastName(),
                getAge(),
                getGender(),
                getTel(),
                Main.dateToString(getDob()),
                Main.convertCurrency(getSalary()),
                getEmail(),
                getUsername(),
                Main.dateToString(getStartWorkDate()),
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
        		|   Salary   > %-10s                                                 |
        		|   Username > %-20s                                        |
        		|   Email    > %-30s                              |
        		|   Address  > %-20s     |
        		|                                                                          |
        		+--------------------------------------------------------------------------+""",
                getId(),
                getFirstName(),
                getAge(),
                getLastName(),
                getGender(),
                getTel(),
                Main.dateToString(getDob()),
                Main.dateToString(getStartWorkDate()),
                Main.convertCurrency(getSalary()),
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
        return "E" + additionalZero + idNum;
    }


}
