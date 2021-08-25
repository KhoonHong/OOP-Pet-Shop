import java.time.LocalDateTime;

public class Owner extends Person implements Identifiable {
    private LocalDateTime startWorkDate;
    private static int totalOwnerCount;
    private static int currentOwnerCount;

    // Constructor
    Owner() {
        // no-args
        this.startWorkDate = LocalDateTime.now();
        this.age = (LocalDateTime.now().getYear() - dob.getYear());
        currentOwnerCount++;
        totalOwnerCount++;
        this.id = generateID(currentOwnerCount);
    }

    Owner(String firstName,
          String lastName,
          String tel,
          char gender,
          LocalDateTime dob,
          Address address,
          String email,
          String username,
          String password) {
        super(firstName, lastName, tel, gender, dob, address, email, username, password);
        this.startWorkDate = LocalDateTime.now();
        this.age = (LocalDateTime.now().getYear() - dob.getYear());
        currentOwnerCount++;
        totalOwnerCount++;
        this.id = generateID(currentOwnerCount);
    }

    // Getter & Setter
    public LocalDateTime getStartWorkDate() {
        return startWorkDate;
    }

    public void setStartWorkDate(LocalDateTime startWorkDate) {
        this.startWorkDate = startWorkDate;
    }

    public static int getTotalOwnerCount() {
        return totalOwnerCount;
    }

    public static void setTotalOwnerCount(int totalOwnerCount) {
        Owner.totalOwnerCount = totalOwnerCount;
    }

    public static int getCurrentOwnerCount() {
        return currentOwnerCount;
    }

    public static void setCurrentOwnerCount(int currentOwnerCount) {
        Owner.currentOwnerCount = currentOwnerCount;
    }
    // Methods

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
        		|   Address  > %-20s    |
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
                getUsername(),
                getEmail(),
                getAddress().displayRow());
    }

    public String generateID(int count){
        String additionalZero = "";
        String idNum = String.valueOf(count);
        if (idNum.length() == 1){
            additionalZero = "00";
        }
        else if (idNum.length() == 2){
            additionalZero = "0";
        }
        return "MGM" + additionalZero + idNum;
    }
}
