import java.time.LocalDateTime;

abstract class Person {
    protected String firstName;
    protected String lastName;
    protected int age;
    protected String tel;
    protected char gender;
    protected String id;
    protected LocalDateTime dob;
    protected Address address;
    protected String email;
    protected String username;
    protected String password;

    // Constructor
    protected Person() {
        // no-args
    }

    protected Person(String firstName, String lastName, String tel, char gender, LocalDateTime dob, Address address, String email, String username, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = (LocalDateTime.now().getYear() - dob.getYear());
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LocalDateTime getDob() {
        return dob;
    }

    public void setDob(LocalDateTime dob) {
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

    public static String censorPassword(String password) {
        return "" + String.valueOf('*').repeat(password.length());
    }

    public String fullName() {
        return firstName + " " + lastName;
    }

}
