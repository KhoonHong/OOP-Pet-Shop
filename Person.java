import java.time.LocalDate;


/**
 * Person class is a superclass for the employee class, owner class and customer class. All of the three classes
 * inherited data and methods from it. The data field being inherited are the name, age, phone number, gender,
 * date of birth, address, email, username and password. The address is created through and stored in the Address
 * class while both the username and the password work in a pair to validate the login process in the system.
 * Both the username and password have certain restrictions, which if not obeyed, will be denied sign up.
 *
 * @author Chan Jia Wei
 */
abstract class Person {
    protected String firstName;
    protected String lastName;
    protected int age;
    protected String tel;
    protected char gender;
    protected String id;
    protected LocalDate dob;
    protected Address address;
    protected String email;
    protected String username;
    protected String password;

    // Constructor
    protected Person() {
        // no-args
    }

    /**
     * {@code Person} is an abstract (super) class, thus can't be used to create objects.
     *
     * @param firstName Person first name
     * @param lastName Person last name
     * @param tel Person phone number
     * @param gender Person gender
     * @param dob Person date of birth
     * @param address Person housing address
     * @param email Person email address
     * @param username Person username
     * @param password Person password
     */
    protected Person(String firstName, String lastName, String tel, char gender, LocalDate dob, Address address, String email, String username, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = (LocalDate.now().getYear() - dob.getYear());
        this.tel = tel;
        this.gender = gender;
        this.dob = dob;
        this.address = address;
        this.email = email;
        this.username = username;
        this.password = password;
    }

    // Getter & Setter
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public char getGender() {
        return gender;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }

    public String getID() {
        return id;
    }

    public void setID(String id) {
        this.id = id;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    // Methods

    /**
     * Convert password into censored String of text for security purposes.
     *
     * @param password Visible password in String
     * @return Censored password
     */
    public static String censorPassword(String password) {
        return "" + String.valueOf('*').repeat(password.length());
    }

    /**
     * Combine {@code firstName} and {@code lastName}
     *
     * @return Full name of a person
     */
    public String fullName() {
        return firstName + " " + lastName;
    }

    /**
     * Overrides the {@code toString()} method in {@code Object}.
     *
     * @return formatted {@code Person} attributes
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
                getUsername(),
                getEmail(),
                getAddress().limitAddress());
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Person person) {
            return person.getFirstName().equals(this.getFirstName()) &&
                    person.getLastName().equals(this.getLastName()) &&
                    person.getAge() == this.getAge() &&
                    person.getTel().equals(this.getTel()) &&
                    person.getGender() == this.getGender() &&
                    person.getDob().equals(this.getDob()) &&
                    person.getAddress().equals(this.getAddress()) &&
                    person.getEmail().equals(this.getEmail()) &&
                    person.getUsername().equals(this.getUsername()) &&
                    person.getPassword().equals(this.getPassword());
        }
        return false;
    }
}
