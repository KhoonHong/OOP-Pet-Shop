import java.time.LocalDateTime;
import java.util.Date;

public class Reservation {
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
    }

    Reservation(String reserveID,
                LocalDateTime reserveDateTime,
                Service services,
                Pet pet,
                String remarks,
                LocalDateTime reserveMadeDateTime,
                int reserveSession,
                Employee employeeSelected,
                LocalDateTime editMadeDateTime) {
        this.reserveID = reserveID;
        this.reserveDateTime = reserveDateTime;
        this.services = services;
        this.pet = pet;
        this.remarks = remarks;
        this.reserveMadeDateTime = reserveMadeDateTime;
        this.reserveSession = reserveSession;
        this.employeeSelected = employeeSelected;
        this.editMadeDateTime = editMadeDateTime;
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

}
