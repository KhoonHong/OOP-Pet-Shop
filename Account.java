import java.util.ArrayList;

public class Account {
    private String username;
    private String password;
    private static Person currentUser;
    private static ArrayList<Customer> customerList;
    private static ArrayList<Employee> employeeList;
    private static ArrayList<Manager> managerList;


    // Getter & Setter
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


    public Person getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(Person currentUser) {
        Account.currentUser = currentUser;
    }

    public ArrayList<Customer> getCustomerList() {
        return customerList;
    }

    public void setCustomerList(ArrayList<Customer> customerList) {
        Account.customerList = customerList;
    }

    public ArrayList<Employee> getEmployeeList() {
        return employeeList;
    }

    public void setEmployeeList(ArrayList<Employee> employeeList) {
        Account.employeeList = employeeList;
    }

    public ArrayList<Manager> getManagerList() {
        return managerList;
    }

    public void setManagerList(ArrayList<Manager> managerList) {
        Account.managerList = managerList;
    }

    // Methods
    public static void login() {

    }

    public static void signup() {

    }
}
