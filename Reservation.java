import java.time.LocalDate;
import java.time.LocalDateTime;


/**
 * The reservation class allows the customers to make a reservation for a certain pet service. Once the customers
 * request to add a reservation in this class, a reservation record with an auto-generated ID will be created to
 * store the reservation details. The customers are allowed to select either one of the four time slots and also
 * the employee to serve when making the reservation.
 * When the customers complete the reservation, a table with the reservation details will be shown to the
 * customers.
 *
 * @author Lee Khoon Hong
 */
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
    private boolean paymentStatus = false;

    // Constructor
    Reservation() {
        // no-args
        this.reserveMadeDateTime = LocalDateTime.now();
        currentReserveCount++;
        totalReserveCount++;
        this.reserveID = generateID(currentReserveCount);
    }

    /**
     * Creates a {@code Reservation} class object when called
     *
     * @param reserveDateTime Reservation timestamp
     * @param services Service details such as add-ons
     * @param pet Pet that was reserved for
     * @param remarks Remarks that to be passed on to the responsible employee
     * @param reserveSession Session that was reserved
     * @param employeeSelected The employee that was selected by customer
     */
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

    Reservation(LocalDateTime reserveDateTime,
                Service services,
                Pet pet,
                String remarks,
                int reserveSession,
                Employee employeeSelected,
                LocalDateTime reserveMadeDateTime) {
        this.reserveDateTime = reserveDateTime;
        this.services = services;
        this.pet = pet;
        this.remarks = remarks;
        this.reserveMadeDateTime = reserveMadeDateTime;
        this.reserveSession = reserveSession;
        this.employeeSelected = employeeSelected;
        currentReserveCount++;
        totalReserveCount++;
        this.reserveID = generateID(currentReserveCount);
        this.paymentStatus = true;
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

    public boolean isPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(boolean paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    // Methods

    /**
     * Overrides the {@code displayRow()} method in {@code Displayable} interface.
     *
     * @return Formatted {@code Reservation} attribute in row
     */
    public String displayRow() {
        return String.format("""
                        |%8s | %-8s|%s |%-8s|  %-6s  |      %-2d       |   %s   | %-16s |""",
                getReserveID(),
                getServices().getClass().getSimpleName(),
                Main.datetimeToString(getReserveDateTime()),
                getPet().getID(),
                getPet().getClass().getSimpleName(),
                getReserveSession(),
                Main.datetimeToString(getReserveMadeDateTime()),
                getEmployeeSelected().fullName());
    }

    /**
     * Overrides the {@code toString()} method in {@code Object}.
     *
     * @return formatted {@code Reservation} attributes
     */
    @Override
    public String toString() {
        return String.format("""
        	\n\n        HERE IS YOUR RESERVATION DETAILS!
        	  +------------------------------------------+
        	  |                                          |
        	  |                  %s                  |
        	  |                  %-8s                |
        	  |             %s             |
        	  |                                          |
        	  |------------------------------------------|
        	  |                                          |
        	  |  Pet ID   : %-8s                     |
        	  |  Pet      : %-6s                       |
        	  |  Remarks  : %-29s|
        	  |                                          |
        	  |  Reserved Session  : %-2d                  |
        	  |  R.Made TimeStamp  : %s    |
        	  |  Employee Selected : %-16s    |
        	  |                                          |
        	  +------------------------------------------+""",
                getReserveID(),
                getServices().getClass().getSimpleName(),
                Main.datetimeToString(getReserveDateTime()),
                getPet().getID(),
                getPet().getClass().getSimpleName(),
                getRemarks(),
                getReserveSession(),
                Main.datetimeToString(getReserveMadeDateTime()),
                getEmployeeSelected().getFirstName() + " " + getEmployeeSelected().getLastName());
    }



    /**
     * Convert customer selection choice into session hour
     *
     * @param session Session choice selected by customer
     * @return Integer value of session time
     */
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

    /**
     * Overrides the {@code generateID()} method in {@code Identifiable} interface.
     *
     * @param count The current {@code Reservation} object total count
     * @return A formatted ID with reservation in abbreviation at the front, current {@code Reservation} count at the back
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
        return "R" + additionalZero + idNum;
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
        if (o instanceof Reservation reservation) {
            return reservation.equals(this);
        }
        return false;
    }
     */
}
