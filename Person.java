import java.time.LocalDateTime;
import java.util.Date;

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

    // Constructor
    protected Person() {
        // no-args
    }

    protected Person(String firstName, String lastName, int age, String tel, char gender, String id, LocalDateTime dob, Address address, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.tel = tel;
        this.gender = gender;
        this.id = id;
        this.dob = dob;
        this.address = address;
        this.email = email;
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

    // Methods

}
