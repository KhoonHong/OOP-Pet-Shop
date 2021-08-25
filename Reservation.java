import java.time.LocalDateTime;
import java.util.ArrayList;

public class Reservation implements Displayable, Identifiable {
    private String reserveID;
    private LocalDateTime reserveDateTime;
    private Service services;
    private Pet pet;
    private String remarks;
    private LocalDateTime reserveMadeDateTime;
    private int reserveSession;
    private Employee employeeSelected;
    private LocalDateTime editMadeDateTime;
    private static int currentReserveCount;
    private static int totalReserveCount;

    // Constructor
    Reservation() {
        // no-args
        this.reserveMadeDateTime = LocalDateTime.now();
        currentReserveCount++;
        totalReserveCount++;
        this.reserveID = generateID(currentReserveCount);
    }

    Reservation(LocalDateTime reserveDateTime,
                Service services,
                Pet pet,
                String remarks,
                int reserveSession,
                Employee employeeSelected) {
        this.reserveDateTime = reserveDateTime;
        this.services = services;
        this.pet = pet;
        this.remarks = remarks;
        this.reserveMadeDateTime = LocalDateTime.now();
        this.reserveSession = reserveSession;
        this.employeeSelected = employeeSelected;
        currentReserveCount++;
        totalReserveCount++;
        this.reserveID = generateID(currentReserveCount);
    }

    // Getter & Setter
    public String getReserveID() {
        return reserveID;
    }

    public void setReserveID(String reserveID) {
        this.reserveID = reserveID;
    }

    public LocalDateTime getReserveDateTime() {
        return reserveDateTime;
    }

    public void setReserveDateTime(LocalDateTime reserveDateTime) {
        this.reserveDateTime = reserveDateTime;
    }

    public Service getServices() {
        return services;
    }

    public void setServices(Service services) {
        this.services = services;
    }

    public Pet getPet() {
        return pet;
    }

    public void setPet(Pet pet) {
        this.pet = pet;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public LocalDateTime getReserveMadeDateTime() {
        return reserveMadeDateTime;
    }

    public void setReserveMadeDateTime(LocalDateTime reserveMadeDateTime) {
        this.reserveMadeDateTime = reserveMadeDateTime;
    }

    public int getReserveSession() {
        return reserveSession;
    }

    public void setReserveSession(int reserveSession) {
        this.reserveSession = reserveSession;
    }

    public Employee getEmployeeSelected() {
        return employeeSelected;
    }

    public void setEmployeeSelected(Employee employeeSelected) {
        this.employeeSelected = employeeSelected;
    }

    public LocalDateTime getEditMadeDateTime() {
        return editMadeDateTime;
    }

    public void setEditMadeDateTime(LocalDateTime editMadeDateTime) {
        this.editMadeDateTime = editMadeDateTime;
    }

    public static int getCurrentReserveCount() {
        return currentReserveCount;
    }

    public static void setCurrentReserveCount(int currentReserveCount) {
        Reservation.currentReserveCount = currentReserveCount;
    }

    public static int getTotalReserveCount() {
        return totalReserveCount;
    }

    public static void setTotalReserveCount(int totalReserveCount) {
        Reservation.totalReserveCount = totalReserveCount;
    }
    // Methods
    public String displayRow() {
        return String.format("""
                        | %8s | %-8s | %s | %-8s   |  %-6s   |  %-2d   | %s |%-10s |""",
                getReserveID(),
                getServices().getClass().getSimpleName(),
                Main.datetimeToString(getReserveDateTime()),
                getPet().getId(),
                getPet().getClass().getSimpleName(),
                getReserveSession(),
                Main.datetimeToString(getReserveMadeDateTime()),
                getEmployeeSelected().fullName());
    }

    @Override
    public String toString() {
        return String.format("""
        	+------------------------------------------+
        	|                                          |
        	|                  %s                  |
        	|                  %-8s                |
        	|             %s             |
        	|                                          |
        	|------------------------------------------|
        	|  Pet ID   : %-8s                     |
        	|  Pet      : %-6s                       |
        	|  Remarks  : %-20s         |
        	|  Reserved Session  : %-2d                  |
        	|  R.Made TimeStamp  : %s    |
        	|  Employee Selected : %-10s        |
        	+------------------------------------------+""",
                getReserveID(),
                getServices().getClass().getSimpleName(),
                Main.datetimeToString(getReserveDateTime()),
                getPet().getId(),
                getPet().getClass().getSimpleName(),
                getRemarks(),
                getReserveSession(),
                Main.datetimeToString(getReserveMadeDateTime()),
                getEmployeeSelected().getFirstName() + " " + getEmployeeSelected().getLastName());
    }

    public static void displayReservation(Person currentUser, ArrayList<Customer> customerList) {
        // check if current user is an owner
        if (currentUser instanceof Owner) {
            Main.displayCustReservation(Main.inputCustomer(customerList));
        }
        // check if current user is a customer
        else if (currentUser instanceof Customer) {
            Main.displayCustReservation(((Customer) currentUser));
        }
    }

    public static int sessionToTime(int session) {
        switch (session) {
            case 1 -> {
                // 9am - 11am
                return 9;
            }
            case 2 -> {
                // 11am - 1pm
                return 11;
            }
            case 3 -> {
                // 2pm - 4pm
                return 14;
            }
            case 4 -> {
                // 4pm - 6pm
                return 16;
            }
        }
        return 0;
    }

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
        return "R" + additionalZero + idNum;
    }
}
