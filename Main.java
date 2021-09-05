import java.io.File;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.text.NumberFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.IOException;

public class Main {

    public static void main(String[] args) {

        Person currentUser = null;
        ArrayList<Customer> customerList = new ArrayList<>();
        ArrayList<Employee> employeeList = new ArrayList<>();
        ArrayList<Owner> ownerList = new ArrayList<>();
        ArrayList<Promotion> promotions = new ArrayList<>();

        try {
            // to generate data
            File custCredentials = new File("customer_credentials.txt");
            FileWriter writer = new FileWriter(custCredentials);
            File empCredentials = new File("employee_credentials.txt");
            FileWriter writer1 = new FileWriter(empCredentials);
        }
        catch (IOException e) {
            System.out.println("FIle handling error occurred...");
        }

        ownerList.add(new Owner("Khoon Hong",
                "Lee",
                "0107762938",
                'M',
                LocalDate.of(2001, 12, 15),
                new Address("15, Lorong Jamban 3", "Bayan Lepas", "11900", "North", "Penang", "Malaysia"),
                "leekhoonhong@hotmail.com",
                "o",
                "o"));

        employeeList.add(new Employee("Jia Wei",
                "Chan",
                "0123456789",
                'M',
                LocalDate.of(2001, 1, 12),
                new Address("1, Tingkat Kenari 5", "Bayan Baru", "11900", "South", "Penang", "Malaysia"),
                "chanjiawei@gmail.com",
                "e",
                "e",
                20000.00));

        customerList.add(new Customer("Shi Jing",
                "Tan",
                "0129876543",
                'M',
                LocalDate.of(2002, 3, 22),
                new Address("11, Lorong sepit 2", "Kulim", "12111", "East", "Kedah", "Malaysia"),
                "tanshijing@gmail.com",
                "c",
                "c"));


        // add a cat into first customer
        customerList.get(0).addPet(new Cat(true, 1, 'M', "Black", Level.MEDIUM, Size.MEDIUM, false));

        promotions.add(new Promotion("11Double11", LocalDate.of(2021, 11, 1), LocalDate.of(2021, 11, 11), 0.1111, "Special 11.11% discount for spending along 1.11 to 11.11"));
        promotions.add(new Promotion("PetDay0411", LocalDate.of(2021, 4, 11), LocalDate.of(2021, 4, 12), 0.15, "15% caring to your pet on International Pet Day"));
        promotions.add(new Promotion("10Of500", LocalDate.of(2021, 9, 18), LocalDate.of(2022, 1, 1), 0.1, "Get 10% discount when spend up to RM50 and above"));
        promotions.add(new Promotion("Merdeka831", LocalDate.of(2021, 8, 31), LocalDate.of(2021, 9, 16), 0.11, "89% affordable price for all customers during 831 to 916"));

        // generate randomized data
        Main2.writeCredentials("################################\n# Employee Account Credentials #\n################################", "employee_credentials.txt");
        Main2.writeCredentials("################################\n# Customer Account Credentials #\n################################", "customer_credentials.txt");
        Main2.writeCredentials("\n  Username  Password\n---------  ----------", "employee_credentials.txt");
        Main2.writeCredentials("\n  Username  Password\n---------  ----------", "customer_credentials.txt");
        Main2.generateEmployeeProfile(employeeList);
        Main2.generateCustomerProfile(customerList, employeeList, promotions);

        // start system
        mainMenu(currentUser, customerList, employeeList, ownerList, promotions);

    }



    // start of program------------------------------------------------------------------------------------------------------------------------------------------------------------------

    // Main menu of the whole program
    public static void mainMenu(Person currentUser, ArrayList<Customer> customerList, ArrayList<Employee> employeeList, ArrayList<Owner> ownerList, ArrayList<Promotion> promotions) {
        do {
            System.out.println("\n\n  +-----------------------------------------------------------------+");
            System.out.println("  | +-------------------------------------------------------------+ |");
            System.out.println("  | |                                                             | |");
            System.out.println("  | |       ***  WELCOME    TO    HAPPY    PET    SHOP  ***       | |");
            System.out.println("  | |                                                             | |");
            System.out.println("  | |                                                             | |");
            System.out.println("  | |                  HERE IS OUR MAIN MENU !!!                  | |");
            System.out.println("  | |                                                             | |");
            System.out.println("  | |     ===================================================     | |");
            System.out.println("  | |                                                             | |");
            System.out.println("  | |      Executive Portal          >   1. Owner Login           | |");
            System.out.println("  | |                                                             | |");
            System.out.println("  | |      Staff Portal              >   2. Employee Login        | |");
            System.out.println("  | |                                                             | |");
            System.out.println("  | |      For Existing Customer     >   3. Customer Login        | |");
            System.out.println("  | |                                                             | |");
            System.out.println("  | |      For Unregistered Customer >   4. Customer Sign Up      | |");
            System.out.println("  | |                                                             | |");
            System.out.println("  | |      Quit Program              >   5. Exit Program          | |");
            System.out.println("  | |                                                             | |");
            System.out.println("  | |     ===================================================     | |");
            System.out.println("  | |                                                             | |");
            System.out.println("  | |                                                             | |");
            System.out.println("  | +-------------------------------------------------------------+ |");
            System.out.println("  +-----------------------------------------------------------------+\n");
            /*System.out.println("\n\n\t\tMain Menu");
            System.out.println("-------------------------");
            System.out.println("1. Owner Login");
            System.out.println("2. Employee Login");
            System.out.println("3. Customer Login");
            System.out.println("4. Customer Sign up");
            System.out.println("5. Exit Program\n");*/

            switch (promptInt("  Please enter a selection > ")) {
                case 1 -> ownerLogin(currentUser, customerList, employeeList, ownerList, promotions);
                case 2 -> employeeLogin(currentUser, customerList, employeeList, ownerList, promotions);
                case 3 -> customerLogin(currentUser, customerList, employeeList, ownerList, promotions);
                case 4 -> customerSignup(customerList);
                case 5 -> {
                    char exitChoice = promptChar("\n\n  Are you sure you want to exit program? (Y/N) >");

                    if (exitChoice == 'y' || exitChoice == 'Y') {
                        System.exit(1); // exit program

                    } else if (exitChoice != 'n' && exitChoice != 'N') {
                        System.out.println("  Invalid exit choice entered...");
                    }
                }
                default -> System.out.println("  Invalid menu choice entered...");
            }
        }
        while (true);
    }

    // owner login page
    public static void ownerLogin(Person currentUser, ArrayList<Customer> customerList, ArrayList<Employee> employeeList, ArrayList<Owner> ownerList, ArrayList<Promotion> promotions) {
        boolean exitFlag;
        do {
            if (ownerList.size() == 0) {
                System.out.println("  No data is initialized...");
                return;
            }
            System.out.println("\n\n   _____________________________ ");
            System.out.println("  |                             |");
            System.out.println("  |         OWNER LOGIN         |");
            System.out.println("  |_____________________________|\n");
            String username = promptString("  Username > ");
            String password = promptString("  Password > ");

            boolean usernameCheck = false;
            // Check for username and password exist
            for (Owner obj : ownerList) {
                usernameCheck = obj.username.equals(username);
                // If correct username and password
                if (obj.username.equals(username) && obj.password.equals(password)) {
                    // Set login successful user as current user
                    currentUser = obj;
                    System.out.println("  Successful Login");
                    ownerMainMenu(currentUser, customerList, employeeList, ownerList, promotions);
                    return; // return to main menu
                }
            }
            // If username incorrect
            if (!usernameCheck) {
                System.out.println("  Invalid username and password...");
            }
            // If password incorrect
            else {
                System.out.println("  Invalid password...");
            }
            exitFlag = promptYesNo("\n\n  Do you want to try again? (Y/N) > ");
        }
        while (exitFlag);
    }

    // owner
    public static void ownerMainMenu(Person currentUser, ArrayList<Customer> customerList, ArrayList<Employee> employeeList, ArrayList<Owner> ownerList, ArrayList<Promotion> promotions) {
        do {
            System.out.println("\n\n   _______________________________________ ");
            System.out.println("  |                                       |");
            System.out.println("  |        *** Owner Main Menu ***        |");
            System.out.println("  |                                       |");
            System.out.println("  |         1. Employee                   |");
            System.out.println("  |         2. Promotion                  |");
            System.out.println("  |         3. Search                     |");
            System.out.println("  |         4. Report                     |");
            System.out.println("  |         5. Display                    |");
            System.out.println("  |         6. User Profile               |");
            System.out.println("  |         7. Log Out                    |");
            System.out.println("  |_______________________________________|\n");

            switch (promptInt("  Please enter a selection > ")) {
                case 1 -> employeeMenu(employeeList);
                case 2 -> promoMenu(promotions);
                case 3 -> searchMenu(customerList, employeeList);
                case 4 -> reportMenu(customerList);
                case 5 -> ownerDisplayMenu(employeeList, customerList);
                case 6 -> ownerProfileMenu(currentUser, ownerList, customerList, employeeList, promotions);
                case 7 -> {
                    if (promptYesNo("\n\n  Are you sure you want to logout? (Y/N) >")) {
                        logout(currentUser); // logout
                        return;
                    }
                }
                default -> System.out.println("  Invalid menu choice entered...");
            }
        }
        while (true);
    }

    public static void customerLogin(Person currentUser, ArrayList<Customer> customerList, ArrayList<Employee> employeeList, ArrayList<Owner> ownerList, ArrayList<Promotion> promotions) {
        boolean exitFlag = true;
        do {
            if (customerList.size() == 0) {
                System.out.println("  No data is initialized...");
                return;
            }
            System.out.println("\n\n   ______________________________ ");
            System.out.println("  |                              |");
            System.out.println("  |        CUSTOMER LOGIN        |");
            System.out.println("  |______________________________|\n");
            String username = promptString("  Username > ");
            String password = promptString("  Password > ");

            boolean usernameCheck = false;
            // Check for username and password exist
            for (Customer obj : customerList) {
                usernameCheck = obj.username.equals(username);
                // If correct username and password
                if (obj.username.equals(username) && obj.password.equals(password)) {
                    // Set login successful user as current user
                    currentUser = obj;
                    System.out.println("\n  Successful Login");
                    customerMainMenu(currentUser, customerList, employeeList, ownerList, promotions);
                    return; // return to main menu
                }
            }
            // If username incorrect
            if (!usernameCheck) {
                System.out.println("  Invalid username and password...");
            }
            // If password incorrect
            else {
                System.out.println("  Invalid password...");
            }
            exitFlag = promptYesNo("\n\n  Do you want to try again? (Y/N) > ");
        }
        while (exitFlag);
    }

    public static void customerSignup(ArrayList<Customer> customerList) {
        System.out.println("\n\n   ______________________________ ");
        System.out.println("  |                              |");
        System.out.println("  |       CUSTOMER SIGN UP       |");
        System.out.println("  |______________________________|\n");
        // Create customer obj and add into customer array
        customerList.add(new Customer(inputFirstname(),
                inputLastname(),
                inputTel(),
                inputGender(),
                inputDOB(),
                createAddress(),
                inputEmail(),
                inputCustomerUsername(customerList),
                inputPassword()));
        System.out.println("  Account Successfully Created");
    }

    public static void customerMainMenu(Person currentUser, ArrayList<Customer> customerList, ArrayList<Employee> employeeList, ArrayList<Owner> ownerList, ArrayList<Promotion> promotions) {
        do {
            System.out.println("\n\n   ________________________________________ ");
            System.out.println("  |                                        |");
            System.out.println("  |       *** Customer Main Menu ***       |");
            System.out.println("  |                                        |");
            System.out.println("  |        1. Reservation                  |");
            System.out.println("  |        2. Billing                      |");
            System.out.println("  |        3. Pet                          |");
            System.out.println("  |        4. Services Available           |");
            System.out.println("  |        5. User Profile                 |");
            System.out.println("  |        6. Log Out                      |");
            System.out.println("  |________________________________________|\n");

            switch (promptInt("  Please enter a selection > ")) {
                case 1 -> reservationMenu(currentUser, customerList, employeeList);
                case 2 -> billingMenu(currentUser, promotions, employeeList);
                case 3 -> petMenu(currentUser);
                case 4 -> availableServices();
                case 5 -> customerProfileMenu(currentUser, employeeList, customerList, ownerList, promotions);
                case 6 -> {
                    if (promptYesNo("\n\n  Are you sure you want to logout? (Y/N) >")) {
                        logout(currentUser); // logout
                        return;
                    }
                }
                default -> System.out.println("  Invalid menu choice entered...");
            }
        }
        while (true);
    }

    public static void promoMenu(ArrayList<Promotion> promotions) {
        do {
            System.out.println("\n\n   ______________________________________ ");
            System.out.println("  |                                      |");
            System.out.println("  |        *** Promotion Menu ***        |");
            System.out.println("  |                                      |");
            System.out.println("  |         1. Add Promotion             |");
            System.out.println("  |         2. Edit Promotion            |");
            System.out.println("  |         3. Remove Promotion          |");
            System.out.println("  |         4. Display Promotion         |");
            System.out.println("  |         5. Back                      |");
            System.out.println("  |______________________________________|\n");

            switch (promptInt("  Please enter a selection > ")) {
                case 1 -> createPromo(new Promotion(), promotions);
                case 2 -> editPromoMenu(promotions);
                case 3 -> {
                    if (checkPromotionRecord(promotions)) {
                        promotions.remove(inputPromotion(promotions));
                        System.out.println("  Promotion removed successfully");
                    } else {
                        System.out.println("  No promotion records found...");
                    }
                }
                case 4 -> displayPromo(promotions);
                case 5 -> {
                    return;
                }
                default -> System.out.println("  Invalid menu choice entered...");
            }
        }
        while (true);
    }

    public static void displayPromo(ArrayList<Promotion> promotions) {
        if (!checkPromotionRecord(promotions)) {
            System.out.println("  No promotion records found...");
            return;
        }
        System.out.println("\n  *** Promotion List ***");
        System.out.println("  +----------+--------------------+--------------------+-------+------------------------------------------------------------+");
        System.out.printf("  |%-10s|%-20s|%-20s|%-7s|%-60s|\n", " ID ", " Start Date ", " End Date ", " Rate", " Description ");
        System.out.println("  +----------+--------------------+--------------------+-------+------------------------------------------------------------+");
        for (Promotion promotion : promotions) {
            System.out.print(promotion);
        }
        System.out.println("  +----------+--------------------+--------------------+-------+------------------------------------------------------------+");
        pressAnyKeyToContinue();
    }

    public static void editPromoMenu(ArrayList<Promotion> promotions) {
        if (!checkPromotionRecord(promotions)) {
            System.out.println("  No promotion records found...");
            return;
        }
        Promotion promotion = inputPromotion(promotions);
        boolean exitFlag;
        do {
            exitFlag = false;
            System.out.println("\n\n   _______________________________________ ");
            System.out.println("  |                                       |");
            System.out.println("  |        *** Edit Promo Menu ***        |");
            System.out.println("  |                                       |");
            System.out.println("  |        1. Promotion Description       |");
            System.out.println("  |        2. Promotion Start Date        |");
            System.out.println("  |        3. Promotion End Date          |");
            System.out.println("  |        4. Promotion Rate              |");
            System.out.println("  |        5. Back                        |");
            System.out.println("  |_______________________________________|\n");

            switch (Main.promptInt("  Please enter a selection > ")) {
                case 1 -> promotion.setDescription(promptString("  Enter new promotion description > "));
                case 2 -> {
                    do {
                        inputPromoStartDate(promotion);
                        // if start date is
                        if (!(promotion.getPromoStartDate().isBefore(promotion.getPromoEndDate()))) {
                            System.out.println("  Date Invalid...");
                            continue;
                        }
                        break;
                    }
                    while (true);
                }
                case 3 -> inputPromoEndDate(promotion);
                case 4 -> promotion.setPromoRate(inputRate());
                case 5 -> {
                    return;
                }
                default -> {
                    System.out.println("  Invalid menu choice entered...");
                    exitFlag = true; // loop
                }
            }
        }
        while (exitFlag);
        System.out.println("  Changes made successfully");
    }

    public static void availableServices() {
        System.out.println("\n\t\t\t\t\t    Available Services\n");
        System.out.println(Groom.getDesc());
        System.out.println(Bath.getDesc());
        System.out.println(Massage.getDesc());
        System.out.println(Shelter.getDesc());
        pressAnyKeyToContinue();
    }

    public static void employeeLogin(Person currentUser, ArrayList<Customer> customerList, ArrayList<Employee> employeeList, ArrayList<Owner> ownerList, ArrayList<Promotion> promotions) {
        boolean exitFlag = true;
        do {
            if (employeeList.size() == 0) {
                System.out.println("  No data is initialized...");
                return;
            }
            System.out.println("\n\n   ______________________________ ");
            System.out.println("  |                              |");
            System.out.println("  |        EMPLOYEE LOGIN        |");
            System.out.println("  |______________________________|\n");
            String username = promptString("  Username > ");
            String password = promptString("  Password > ");

            boolean usernameCheck = false;
            // Check for username and password exist
            for (Employee obj : employeeList) {
                usernameCheck = obj.username.equals(username);
                // If correct username and password
                if (obj.username.equals(username) && obj.password.equals(password)) {
                    // Set login successful user as current user
                    currentUser = obj;
                    System.out.println("  Successful Login");
                    employeeMainMenu(currentUser, customerList, employeeList, ownerList, promotions);
                    return; // return to main menu
                }
            }
            // If username incorrect
            if (!usernameCheck) {
                System.out.println("  Invalid username and password...");
            }
            // If password incorrect
            else {
                System.out.println("  Invalid password...");
            }
            exitFlag = promptYesNo("\n\n  Do you want to try again? (Y/N) > ");
        }
        while (exitFlag);
    }

    public static void employeeSignup(ArrayList<Employee> employeeList) {
        System.out.println("\n\n\t  Employee Sign up");
        System.out.println("  -------------------------");
        // Create customer obj and add into customer array
        employeeList.add(new Employee(inputFirstname(),
                inputLastname(),
                inputTel(),
                inputGender(),
                inputDOB(),
                createAddress(),
                inputEmail(),
                inputEmployeeUsername(employeeList),
                inputPassword(),
                promptDouble("  Enter salary > RM ")));
        System.out.println("  Account Successfully Created");
        pressAnyKeyToContinue();
    }

    public static void employeeMainMenu(Person currentUser, ArrayList<Customer> customerList, ArrayList<Employee> employeeList, ArrayList<Owner> ownerList, ArrayList<Promotion> promotions) {
        do {
            System.out.println("\n\n   ________________________________________ ");
            System.out.println("  |                                        |");
            System.out.println("  |       *** Employee Main Menu ***       |");
            System.out.println("  |                                        |");
            System.out.println("  |        1. Schedule                     |");
            System.out.println("  |        2. Search Customer              |");
            System.out.println("  |        3. Search Reservation           |");
            System.out.println("  |        4. User Profile                 |");
            System.out.println("  |        5. Log Out                      |");
            System.out.println("  |________________________________________|\n");

            switch (promptInt("  Please enter a selection > ")) {
                case 1 -> displaySchedule(currentUser, customerList);
                case 2 -> searchCustomer(customerList);
                case 3 -> searchReservation(customerList);
                case 4 -> empProfileMenu(currentUser, employeeList, customerList, ownerList, promotions);
                case 5 -> {
                    if (promptYesNo("\n\n  Are you sure you want to logout? (Y/N) >")) {
                        logout(currentUser); // logout
                        return;
                    }
                }
                default -> System.out.println("  Invalid menu choice entered...");
            }
        }
        while (true);
    }

    public static void displaySchedule(Person currentUser, ArrayList<Customer> customerList) {
        // display current employee schedule
        ((Employee) currentUser).getWorkSchedule().displaySchedule();

        // check if there are no reservations
        if (((Employee) currentUser).getWorkSchedule().getRecordPosition().isEmpty()) {
            System.out.println("  There are no reservation records found...\n");
            pressAnyKeyToContinue();
            return;
        }

        // display current employee customer reservation
        System.out.println("""
                \n  +-------------------------------------------------------------------------------------------------------------------------------+
                  |  Cust.ID  | Resv.ID | Service | Resv. Timestamp | Pet ID | Pet Type | Resv. Session | Resv. Made TimeStamp |  Employee Name   |
                  +-----------+---------+---------+-----------------+--------+----------+---------------+----------------------+------------------+""");
        for (var reservation : ((Employee)currentUser).getWorkSchedule().getRecordPosition().entrySet()) {
            System.out.printf("  |  %6s   ", getCustID(reservation.getKey(), customerList));
            System.out.print(reservation.getKey().displayRow()+"\n");
            System.out.println("  +-------------------------------------------------------------------------------------------------------------------------------+");
        }
        pressAnyKeyToContinue();
    }

    public static String getCustID(Reservation reservation, ArrayList<Customer> customerList) {
        for (Customer obj : customerList) {
            for (Reservation obj1 : obj.getReservation()) {
                // if matches
                if (obj1.equals(reservation)) {
                    return obj.getID();
                }
            }
        }
        return "";
    }

    public static void reservationMenu(Person currentUser, ArrayList<Customer> customerList, ArrayList<Employee> employeeList) {
        do {
            System.out.println("\n\n   ________________________________________ ");
            System.out.println("  |                                        |");
            System.out.println("  |        *** Reservation Menu ***        |");
            System.out.println("  |                                        |");
            System.out.println("  |         1. Add Reservation             |");
            System.out.println("  |         2. Edit Reservation            |");
            System.out.println("  |         3. Cancel Reservation          |");
            System.out.println("  |         4. Display Reservation         |");
            System.out.println("  |         5. Back                        |");
            System.out.println("  |________________________________________|\n");

            switch (Main.promptInt("  Please enter a selection > ")) {
                case 1 -> createReservation(currentUser, employeeList);
                case 2 -> editReservation(currentUser, employeeList);
                case 3 -> cancelReservation(currentUser, employeeList);
                case 4 -> displayReservation(currentUser, customerList);
                case 5 -> {
                    return;
                }
                default -> System.out.println("  Invalid menu choice entered...");
            }
        }
        while (true);
    }

    public static void petMenu(Person currentUser) {
        do {
            System.out.println("\n\n   ________________________________________ ");
            System.out.println("  |                                        |");
            System.out.println("  |            *** Pet Menu ***            |");
            System.out.println("  |                                        |");
            System.out.println("  |             1. Add Pet                 |");
            System.out.println("  |             2. Edit Pet                |");
            System.out.println("  |             3. Remove Pet              |");
            System.out.println("  |             4. Display Pet             |");
            System.out.println("  |             5. Back                    |");
            System.out.println("  |________________________________________|\n");

            switch (Main.promptInt("  Please enter a selection > ")) {
                case 1 -> createPet(currentUser);
                case 2 -> editPet(currentUser);
                case 3 -> deletePet(currentUser);
                case 4 -> {
                    if (((Customer) currentUser).getPets().isEmpty()) {
                        System.out.println("  No pets found in the record...");
                    } else {
                        selectPet(currentUser).displayPet();
                    }
                    pressAnyKeyToContinue();
                }
                case 5 -> {
                    return;
                }
                default -> System.out.println("  Invalid menu choice entered...");
            }
        }
        while (true);
    }

    /**
     * To redirect owner or customer to their own respective reservation display method.
     *
     * @param currentUser Current session user account object
     * @param customerList An ArrayList of customers
     */
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

    public static Address createAddress() {
        Address address;
        System.out.println("\n\n\t\t  Address");
        System.out.println("  ------------------------");
        // Initialize Address obj with input
        address = new Address(convertCapitalize(Main.promptString("\n  Enter your street   > ")),
                inputZipcode(),
                convertCapitalize(Main.promptString("\n  Enter your city     > ")),
                inputRegion(),
                convertCapitalize(Main.promptString("\n  Enter your state    > ")),
                convertCapitalize(Main.promptString("\n  Enter your country  > ")));
        return address;
    }

    public static String inputZipcode() {
        String zipcode;
        boolean zipcodeFlag = true;
        do {
            zipcode = Main.promptString("\n  Enter your zipcode  > ");
            if (zipcode.length() == 5) {
                zipcodeFlag = false;
            } else {
                System.out.println("\n  Invalid zipcode...");
            }
        }
        while (zipcodeFlag);
        return zipcode;
    }

    public static String inputRegion() {
        // Region
        String region = null;
        boolean regionFlag;
        do {
            regionFlag = false;
            System.out.println("\n  1. North");
            System.out.println("  2. North East");
            System.out.println("  3. East");
            System.out.println("  4. South East");
            System.out.println("  5. South");
            System.out.println("  6. South West");
            System.out.println("  7. West");
            System.out.println("  8. North West");
            System.out.println("  9. Other states");

            switch (Main.promptInt("  Please select your region in state > ")) {
                case 1 -> region = "North";
                case 2 -> region = "North East";
                case 3 -> region = "East";
                case 4 -> region = "South East";
                case 5 -> region = "South";
                case 6 -> region = "South West";
                case 7 -> region = "West";
                case 8 -> region = "North West";
                case 9 -> region = "Other states";
                default -> {
                    System.out.println("  Invalid Selection...");
                    regionFlag = true; // continue loop
                }
            }
        }
        while (regionFlag);
        return region;
    }

    public static void deletePet(Person currentUser) {
        if (((Customer) currentUser).getPets().isEmpty()) {
            System.out.println("  There are no pets in the record");
            pressAnyKeyToContinue();
            return;
        }
        System.out.println("\n\n\t\t  Pet Removal Selection");
        Pet pet = selectPet(currentUser);
        if (!promptYesNo("  Are you sure you want to remove this pet? (Y/N) > ")) {
            pressAnyKeyToContinue();
            return;
        }
        if(pet instanceof Bird){
            Bird.setTotalBirdAge(Bird.getTotalBirdAge() - pet.getAge());
            Bird.setTotalBirdCount(Bird.getTotalBirdCount() - 1);
        }
        else if(pet instanceof Cat){
            Cat.setTotalCatAge(Cat.getTotalCatAge() - pet.getAge());
            Cat.setTotalCatCount(Cat.getTotalCatCount() - 1);
        }
        else if(pet instanceof Dog){
            Dog.setTotalDogAge(Dog.getTotalDogAge() - pet.getAge());
            Dog.setTotalDogCount(Dog.getTotalDogCount() - 1);
        }
        else {
            Rabbit.setTotalRabbitAge(Rabbit.getTotalRabbitAge() - pet.getAge());
            Rabbit.setTotalRabbitCount(Rabbit.getTotalRabbitCount() - 1);
        }
        // remove from total pet count
        ((Customer) currentUser).removePet(pet);
        Pet.setTotalPetCount(Pet.getTotalPetCount() - 1);
        ((Customer) currentUser).removePet(pet);

        try {
            // check if same pet as deleted pet
            ((Customer) currentUser).getReservation().removeIf(reservation -> reservation.getPet().equals(pet)); // remove from reservation
            ((Customer) currentUser).getBill().getBillDetails().removeIf(reservation -> reservation.getPet().equals(pet)); // remove from billing
            if (((Customer) currentUser).getBill().getBillDetails().isEmpty()) {
                ((Customer) currentUser).setBill(null); // reset bill to clean slate
            }
        }
        // catch if customer billing is null/empty
        catch (NullPointerException ignored) {
        }

        System.out.println("  Pet deleted successfully");
    }

    public static void createPet(Person currentUser) {
        boolean exitFlag = false;
        do {
            System.out.println("\n\n\t\t  Pet Type");
            System.out.println("  -------------------------");
            System.out.println("  1. Dog");
            System.out.println("  2. Cat");
            System.out.println("  3. Bird");
            System.out.println("  4. Rabbit");

            switch(promptInt("  Please enter a selection > ")) {
                case 1 -> ((Customer)currentUser).addPet(new Dog(
                        promptYesNo("\n  Is your dog neutered? (Y/N) > "),
                        inputPetAge("\n  Age of your dog? (years)    > "),
                        inputGender(),
                        promptString("\n  The color of your doggo?   > "),
                        promptLevel("  Aggressiveness of doggo"),
                        promptSize("  Size of doggo")));

                case 2 -> ((Customer)currentUser).addPet(new Cat(
                        promptYesNo("\n  Is your cat neutered? (Y/N) > "),
                        inputPetAge("\n  Age of your cat? (years)    > "),
                        inputGender(),
                        promptString("\n  The color of your cat?     > "),
                        promptLevel("  Aggressiveness of cat"),
                        promptSize("  Size of cat")));

                case 3 -> ((Customer)currentUser).addPet(new Bird(
                        inputPetAge("\n  Age of your bird? (years) > "),
                        inputGender(),
                        promptString("\n  The color of your bird?  > "),
                        promptLevel("  Aggressiveness of bird"),
                        promptSize("  Size of bird")));

                case 4 -> ((Customer)currentUser).addPet(new Rabbit(
                        promptYesNo("\n  Is your rabbit neutered? (Y/N) > "),
                        inputPetAge("\n  Age of your rabbit? (years)    > "),
                        inputGender(),
                        promptString("\n  The color of your rabbit?     > "),
                        promptLevel("  Aggressiveness of rabbit"),
                        promptSize("  Size of rabbit")));
                default -> {
                    System.out.println("  Invalid choice entered...");
                    exitFlag = true;
                }
            }
            System.out.println(((Customer)currentUser).getPets().get(((Customer)currentUser).getPets().size()-1));
            System.out.println("\n\n  Pet added successful");
            pressAnyKeyToContinue();
        }
        while (exitFlag);
    }

    public static void editPet(Person currentUser) {
        boolean exitFlag;
        if (((Customer) currentUser).getPets().isEmpty()) {
            System.out.println("  No pets found in the record...");
            pressAnyKeyToContinue();
            return;
        }
        Pet pet = selectPet(currentUser);
        do {
            exitFlag = false;
            System.out.print("\n\t\t   *** Edit Pet Menu ***");
            System.out.print("\n  +---------------------------------------+");
            System.out.printf("\n  | Pet ID > %-6s                      |",pet.getID());
            System.out.printf("\n  | Type   > %-6s                       |",pet.getClass().getSimpleName());
            System.out.print("\n  +---------------------------------------+");
            System.out.printf("\n  | Age    > %-3d      Color  > %-8s   |",pet.getAge(),pet.getColor());
            System.out.printf("\n  | Gender > %c                            |",pet.getGender());
            System.out.print("\n  |                                       |");
            System.out.print("\n  |---------------------------------------|");
            System.out.print("\n  |            Characteristic             |");
            System.out.print("\n  |---------------------------------------|");
            System.out.print("\n  |                                       |");
            System.out.printf("\n  |      Size      --> %-15s    |",displaySize(pet.getSize()));
            System.out.printf("\n  | Aggressiveness --> %-10s         |",displayLevel(pet.getAggressive()));
            System.out.print("\n  |                                       |");
            System.out.print("\n  +---------------------------------------+\n\n");
            System.out.println("  1. Age");
            System.out.println("  2. Gender");
            System.out.println("  3. Color");
            System.out.println("  4. Size");
            System.out.println("  5. Aggressiveness");
            System.out.println("  6. Back");

            switch (Main.promptInt("  Please enter a selection > ")) {
                case 1 -> pet.setAge(inputPetAge("\n  Age of your " + pet.getClass().getSimpleName() + "? (years) > "));
                case 2 -> pet.setGender(inputGender());
                case 3 -> pet.setColor(promptString("\n  The color of your " + pet.getClass().getSimpleName()+ "? > "));
                case 4 -> pet.setSize(promptSize("  Size of " + pet.getClass().getSimpleName()));
                case 5 -> pet.setAggressive(promptLevel("  Aggressive of " + pet.getClass().getSimpleName()));
                case 6 -> {
                    return;
                }
                default -> {
                    System.out.println("  Invalid menu choice entered...");
                    exitFlag = true;
                }
            }
            System.out.println("\n");
            pet.displayPet();
            System.out.println("  Changes made successfully");
            pressAnyKeyToContinue();
        }
        while (exitFlag);
    }

    public static Pet selectPet(Person currentUser) {
        System.out.println("\n\n\t\t  Pets in record");
        System.out.println("  ------------------------------");
        System.out.println("\t\tID\t\tType\tGender\tColor");//TanShiJing
        for (int i = 0; i < ((Customer)currentUser).getPets().size(); i++) {
            System.out.printf("  %d. %s\t%-6s\t   %c\t%s\n",i+1, ((Customer)currentUser).getPets().get(i).getID(),
                    ((Customer)currentUser).getPets().get(i).getClass().getSimpleName(),
                    ((Customer)currentUser).getPets().get(i).getGender(),
                    ((Customer)currentUser).getPets().get(i).getColor());
        }
        do {
            try {
                return ((Customer)currentUser).getPets().get(Main.promptInt("  Choose a pet > ")-1);
            } catch (IndexOutOfBoundsException e) {
                System.out.println("  Invalid choice selected...");
            }
        }
        while (true);
    }

    public static void billingCardMenu(Person currentUser) {
        do {
            System.out.println("\n\n   ________________________________________ ");
            System.out.println("  |                                        |");
            System.out.println("  |        *** Billing Card Menu ***       |");
            System.out.println("  |                                        |");
            System.out.println("  |          1. Add Card                   |");
            System.out.println("  |          2. Display Card               |");
            System.out.println("  |          3. Remove Card                |");
            System.out.println("  |          4. Edit Card                  |");
            System.out.println("  |          5. Back                       |");
            System.out.println("  |________________________________________|\n");

            switch (Main.promptInt("  Please enter a selection > ")) {
                case 1 -> addCard(currentUser);
                case 2 -> displayCard(currentUser);
                case 3 -> removeCard(currentUser);
                case 4 -> editCard(currentUser);
                case 5 -> {
                    return;
                }
                default -> System.out.println("  Invalid menu choice entered...");
            }
        }
        while (true);
    }

    public static void reportMenu(ArrayList<Customer> customerList) {
        do {
            System.out.println("\n\n   ________________________________________ ");
            System.out.println("  |                                        |");
            System.out.println("  |           *** Report Menu ***          |");
            System.out.println("  |                                        |");
            System.out.println("  |     1. Promotion Source Report         |");
            System.out.println("  |     2. Shelter Report                  |");
            System.out.println("  |     3. Payment Method Report           |");
            System.out.println("  |     4. Employee Contribution Report    |");
            System.out.println("  |     5. Popular Service Report          |");
            System.out.println("  |     6. Customer Spending Report        |");
            System.out.println("  |     7. Customer Demographic Report     |");
            System.out.println("  |     8. Pet Demographic Report          |");
            System.out.println("  |     9. Back                            |");
            System.out.println("  |________________________________________|\n");

            switch (promptInt("  Please enter a selection > ")) {
                case 1 -> Main3.sourcePromoReport(customerList);
                case 2 -> System.out.println("  2. Shelter Report");
                case 3 -> Main3.paymentMethodReport(customerList);
                case 4 -> System.out.println("  4. Employee Contribution Report");
                case 5 -> System.out.println("  5. Popular Service Report");
                case 6 -> Main3.mostSpendingCustomer(customerList);
                case 7 -> Main3.custDemoReport(customerList);
                case 8 -> Main3.petDemoReport(customerList);
                case 9 -> {
                    return;
                }
                default -> System.out.println("  Invalid menu choice entered...");
            }
        }
        while (true);
    }

    public static void searchMenu(ArrayList<Customer> customerList, ArrayList<Employee> employeeList) {
        do {
            System.out.println("\n\n   ________________________________________ ");
            System.out.println("  |                                        |");
            System.out.println("  |           *** Search Menu ***          |");
            System.out.println("  |                                        |");
            System.out.println("  |         1. Employee Records            |");
            System.out.println("  |         2. Customer Records            |");
            System.out.println("  |         3. Reservation Records         |");
            System.out.println("  |         4. Bill Records                |");
            System.out.println("  |         5. Bill History Records        |");
            System.out.println("  |         6. Back                        |");
            System.out.println("  |________________________________________|\n");

            switch (promptInt("  Please enter a selection > ")) {
                case 1 -> searchEmployee(employeeList);
                case 2 -> searchCustomer(customerList);
                case 3 -> searchReservation(customerList);
                case 4 -> searchTotalBilling(customerList);
                case 5 -> searchBillingHistory(customerList);
                case 6 -> {
                    return;
                }
                default -> System.out.println("  Invalid menu choice entered...");
            }
        }
        while (true);
    }

    public static void ownerDisplayMenu(ArrayList<Employee> employeeList, ArrayList<Customer> customerList) {
        boolean backFlag;
        do {
            backFlag = false;
            System.out.println("\n\n   ________________________________________ ");
            System.out.println("  |                                        |");
            System.out.println("  |          *** Display Menu ***          |");
            System.out.println("  |                                        |");
            System.out.println("  |     1. Display Employee Records        |");
            System.out.println("  |     2. Display Customer Records        |");
            System.out.println("  |     3. Display Reservation Records     |");
            System.out.println("  |     4. Display Bill Records            |");
            System.out.println("  |     5. Display Bill History Records    |");
            System.out.println("  |     6. Back                            |");
            System.out.println("  |________________________________________|\n");

            switch (promptInt("  Please enter a selection > ")) {
                case 1 -> {
                    if (!checkEmployeeRecord(employeeList)) {
                        System.out.println("  No employee records found...");
                        pressAnyKeyToContinue();
                        return;
                    }
                    else {
                        System.out.println("""
                  \n  +-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+
                    | Emp.ID |      Name       |  Age  | Gender | Contact Number | Birth Date |   Salary   |             Email            |    Username    | Register Date |                           Address                          |
                    +--------+-----------------+-------+--------+----------------+------------+------------+------------------------------+----------------+---------------+------------------------------------------------------------+""");
                        for (int index = 0; index < employeeList.size() ; index++) {
                            System.out.println("  "+employeeList.get(index).displayRow());
                            System.out.println("  +-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+");
                            if ((index+1) % 10 == 0 && (index+1)!=employeeList.size()) {
                                switch (promptInt("\n\n  Displaying results (" + (index-8) + "-" + (index+1) + ") out of " + employeeList.size() + " records\n  1. Continue displaying records\n  2. Back\n\n  Please enter a selection > ")) {
                                    case 1 -> {
                                        System.out.println("""                                                                       
                                                                  
                                                \n  +-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+
                                                  | Emp.ID |      Name       |  Age  | Gender | Contact Number | Birth Date |   Salary   |             Email            |    Username    | Register Date |                           Address                          |
                                                  +--------+-----------------+-------+--------+----------------+------------+------------+------------------------------+----------------+---------------+------------------------------------------------------------+""");
                                    }
                                    case 2 -> backFlag = true;
                                    default -> System.out.println("Invalid selection entered...");
                                }
                                if (backFlag) {
                                    break;
                                }
                            }
                        }
                        pressAnyKeyToContinue();
                    }
                }
                case 2 -> {
                    if (!checkCustomerRecord(customerList)) {
                        System.out.println("  No customer records found...");
                        pressAnyKeyToContinue();
                        return;
                    }
                    else {
                        System.out.println("""
				  \n  +-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+
				    | Cust.ID |      Name       |  Age  | Gender | Contact Number | Birth Date |             Email            |    Username    | Register Date |                           Address                          |
				    +---------+-----------------+-------+--------+----------------+------------+------------------------------+----------------+---------------+------------------------------------------------------------+""");
                        for (int index = 0; index < customerList.size(); index++) {
                            System.out.println("  " +customerList.get(index).displayRow());
                            System.out.println("  +-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+");
                            if ((index+1) % 10 == 0 && (index+1)!=customerList.size()) {
                                switch (promptInt("\n\n  Displaying results (" + (index-8) + "-" + (index+1) + ") out of " + customerList.size() + " records\n  1. Continue displaying records\n  2. Back\n\n  Please enter a selection > ")) {
                                    case 1 -> {
                                        System.out.println("""
                                                                                                 	  
                                                \n  +-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+
                                                  | Cust.ID |      Name       |  Age  | Gender | Contact Number | Birth Date |             Email            |    Username    | Register Date |                           Address                          |
                                                  +---------+-----------------+-------+--------+----------------+------------+------------------------------+----------------+---------------+------------------------------------------------------------+""");
                                    }
                                    case 2 -> backFlag = true;
                                    default -> System.out.println("Invalid selection entered...");
                                }
                                if (backFlag) {
                                    break;
                                }
                            }
                        }
                        pressAnyKeyToContinue();
                    }
                }
                case 3 -> {
                    ArrayList<Reservation> reservations = new ArrayList<>();
                    if (!checkReserveRecords(customerList)) {
                        System.out.println("  No customer reservation records found...");
                        pressAnyKeyToContinue();
                        return;
                    }
                    else {
                        for (Customer cust : customerList) {
                            reservations.addAll(cust.getReservation());
                        }
                        if (reservations.isEmpty()) {
                            System.out.println("  No customer reservation records found...");
                            pressAnyKeyToContinue();
                            return;
                        }

                        System.out.println("""
                  \n  +-------------------------------------------------------------------------------------------------------------------------------+
                    |  Cust.ID  | Resv.ID | Service | Resv. Timestamp | Pet ID | Pet Type | Resv. Session | Resv. Made TimeStamp |  Employee Name   |
                    +-----------+---------+---------+-----------------+--------+----------+---------------+----------------------+------------------+""");
                        boolean exitFlag;
                        for (int index  = 0; index < reservations.size(); index++) {
                            exitFlag = false;
                            for (Customer cust : customerList) {
                                for(Reservation resv : cust.getReservation()) {
                                    if (resv.equals(reservations.get(index))) {
                                        System.out.printf("  |  %6s   ", cust.getID());
                                        exitFlag = true;
                                        break;
                                    }
                                }
                                if (exitFlag) {
                                    break;
                                }
                            }
                            System.out.print(reservations.get(index).displayRow()+"\n");
                            System.out.println("  +-------------------------------------------------------------------------------------------------------------------------------+");
                            if ((index+1) % 10 == 0 && (index+1)!=reservations.size()) {
                                switch (promptInt("\n\n  Displaying results (" + (index-8) + "-" + (index+1) + ") out of " + reservations.size() + " records\n  1. Continue displaying records\n  2. Back\n\n  Please enter a selection > ")) {
                                    case 1 -> {
                                        System.out.println("""
                  \n  +-------------------------------------------------------------------------------------------------------------------------------+
                    |  Cust.ID  | Resv.ID | Service | Resv. Timestamp | Pet ID | Pet Type | Resv. Session | Resv. Made TimeStamp |  Employee Name   |
                    +-----------+---------+---------+-----------------+--------+----------+---------------+----------------------+------------------+""");
                                    }
                                    case 2 -> backFlag = true;
                                    default -> System.out.println("Invalid selection entered...");
                                }
                                if (backFlag) {
                                    break;
                                }
                            }
                        }
                        pressAnyKeyToContinue();
                    }
                }
                case 4 -> {
                    ArrayList<Customer> custs = new ArrayList<>();
                    if (!checkBillRecords(customerList)) {
                        System.out.println("  No customer billing records found...");
                        pressAnyKeyToContinue();
                        return;
                    }
                    else {
                        System.out.println("""
                                  \n  +----------------------------------------------+
                                    |  Cust.ID  |Trans. ID |Total Amount| Services |
                                    +-----------+----------+------------+----------+""");
                        // filtering
                        for (Customer customer : customerList) {
                            if (customer.getBill() == null) {
                                continue;
                            }
                            custs.add(customer);
                        }

                        if (custs.isEmpty()) {
                            System.out.println("  No customer billing records found...");
                            pressAnyKeyToContinue();
                            return;
                        }

                        for (int index = 0; index < custs.size(); index++) {
                            System.out.printf("  |  %6s   |  %6s  | %10s |    %2d    |\n", custs.get(index).getID(),
                                    custs.get(index).getBill().getTransactionID(),
                                    convertCurrency(custs.get(index).getBill().calcTotalAmount()),
                                    custs.get(index).getBill().getBillDetails().size());
                            System.out.println("  +----------------------------------------------+");
                            if ((index+1) % 10 == 0 && (index+1)!=custs.size()) {
                                switch (promptInt("\n\n  Displaying results (" + (index - 8) + "-" + (index + 1) + ") out of " + custs.size() + " records\n  1. Continue displaying records\n  2. Back\n\n  Please enter a selection > ")) {
                                    case 1 -> {
                                        System.out.println("""
                                  \n  +----------------------------------------------+
                                    |  Cust.ID  |Trans. ID |Total Amount| Services |
                                    +-----------+----------+------------+----------+""");
                                    }
                                    case 2 -> backFlag = true;
                                    default -> System.out.println("Invalid selection entered...");
                                }
                                if (backFlag) {
                                    break;
                                }
                            }
                        }
                        pressAnyKeyToContinue();
                    }
                }
                case 5 -> {
                    ArrayList<String> custID = new ArrayList<>();
                    ArrayList<Billing> billHistory = new ArrayList<>();
                    if (!checkBillHistoryRecords(customerList)) {
                        System.out.println("  No customer billing history records found...");
                        pressAnyKeyToContinue();
                        return;
                    }
                    else {
                        for (Customer customer : customerList) {
                            for (Billing billing : customer.getBillHistory()) {
                                custID.add(customer.getID());
                                billHistory.add(billing);
                            }
                        }

                        if (billHistory.isEmpty()) {
                            System.out.println("  No customer billing history records found...");
                            pressAnyKeyToContinue();
                            return;
                        }
                        System.out.println("""
                  \n  +-----------------------------------------------------------------------------------------------------------------------------+
                    |  Cust.ID  | Total Amount | Grand Total | Trans. ID | Pay Method | Promo Origin |  Payment Date   | Promo Applied | Services |
                    +-----------+--------------+-------------+-----------+------------+--------------+-----------------+---------------+----------+""");

                        for (int index = 0; index < billHistory.size(); index++) {
                            System.out.printf("  |  %6s   ", custID.get(index));
                            System.out.print(billHistory.get(index).displayRow()+"\n");
                            System.out.println("  +-----------------------------------------------------------------------------------------------------------------------------+");
                            if ((index+1) % 10 == 0 && (index+1)!=billHistory.size()) {
                                switch (promptInt("\n\n  Displaying results (" + (index - 8) + "-" + (index + 1) + ") out of " + billHistory.size() + " records\n  1. Continue displaying records\n  2. Back\n\n  Please enter a selection > ")) {
                                    case 1 -> {
                                        System.out.println("""
                  \n  +-----------------------------------------------------------------------------------------------------------------------------+
                    |  Cust.ID  | Total Amount | Grand Total | Trans. ID | Pay Method | Promo Origin |  Payment Date   | Promo Applied | Services |
                    +-----------+--------------+-------------+-----------+------------+--------------+-----------------+---------------+----------+""");;
                                    }
                                    case 2 -> backFlag = true;
                                    default -> System.out.println("Invalid selection entered...");
                                }
                                if (backFlag) {
                                    break;
                                }
                            }
                        }
                        pressAnyKeyToContinue();
                    }
                }
                case 6 -> {
                    return;
                }
                default -> System.out.println("  Invalid menu choice entered...");
            }
        }
        while (true);
    }

    public static void ownerProfileMenu(Person currentUser, ArrayList<Owner> ownerList, ArrayList<Customer> customerList, ArrayList<Employee> employeeList, ArrayList<Promotion> promotions) {
        do {
            System.out.println("\n\n   ________________________________________ ");
            System.out.println("  |                                        |");
            System.out.println("  |        *** User Profile Menu ***       |");
            System.out.println("  |                                        |");
            System.out.println("  |          1. Display Profile            |");
            System.out.println("  |          2. Edit Profile               |");
            System.out.println("  |          3. Delete Account             |");
            System.out.println("  |          4. Back                       |");
            System.out.println("  |________________________________________|\n");

            switch (Main.promptInt("  Please enter a selection > ")) {
                case 1 -> {
                    System.out.println("\n\t\t\t\t\t\t\t*** Profile Info ***");
                    System.out.println(currentUser);
                    // Wait user response to continue
                    pressAnyKeyToContinue();
                }
                case 2 -> editProfileMenu(currentUser, customerList, employeeList, ownerList);
                case 3 -> deleteOwnerAccount(currentUser, customerList, employeeList, ownerList, promotions);
                case 4 -> {
                    return;
                }
                default -> System.out.println("  Invalid menu choice entered...");
            }
        }
        while (true);
    }

    public static void empProfileMenu(Person currentUser, ArrayList<Employee> employeeList, ArrayList<Customer> customerList, ArrayList<Owner> ownerList, ArrayList<Promotion> promotions) {
        do {
            System.out.println("\n\n   ________________________________________ ");
            System.out.println("  |                                        |");
            System.out.println("  |        *** User Profile Menu ***       |");
            System.out.println("  |                                        |");
            System.out.println("  |          1. Display Profile            |");
            System.out.println("  |          2. Edit Profile               |");
            System.out.println("  |          3. Back                       |");
            System.out.println("  |________________________________________|\n");

            switch (Main.promptInt("  Please enter a selection > ")) {
                case 1 -> {
                    System.out.println("\n\t\t\t\t\t\t\t*** Profile Info ***");
                    System.out.println(currentUser);
                    // Wait user response to continue
                    pressAnyKeyToContinue();
                }
                case 2 -> editProfileMenu(currentUser, customerList, employeeList, ownerList);
                case 3 -> {
                    return;
                }
                default -> System.out.println("  Invalid menu choice entered...");
            }
        }
        while (true);
    }

    public static void customerProfileMenu(Person currentUser, ArrayList<Employee> employeeList, ArrayList<Customer> customerList, ArrayList<Owner> ownerList, ArrayList<Promotion> promotions) {
        do {
            System.out.println("\n\n   ________________________________________ ");
            System.out.println("  |                                        |");
            System.out.println("  |        *** User Profile Menu ***       |");
            System.out.println("  |                                        |");
            System.out.println("  |          1. Display Profile            |");
            System.out.println("  |          2. Edit Profile               |");
            System.out.println("  |          3. Billing Card               |");
            System.out.println("  |          4. Delete Account             |");
            System.out.println("  |          5. Back                       |");
            System.out.println("  |________________________________________|\n");

            switch (Main.promptInt("  Please enter a selection > ")) {
                case 1 -> {
                    System.out.println("\n\t\t\t\t\t\t\t*** Profile Info ***");
                    System.out.println(currentUser);
                    // Wait user response to continue
                    pressAnyKeyToContinue();
                }
                case 2 -> editProfileMenu(currentUser, customerList, employeeList, ownerList);
                case 3 -> billingCardMenu(currentUser);
                case 4 -> deleteCustomerAccount(currentUser, customerList, employeeList, ownerList, promotions);
                case 5 -> {
                    return;
                }
                default -> System.out.println("  Invalid menu choice entered...");
            }
        }
        while (true);
    }

    public static void deleteEmployeeAccount(Employee employee, ArrayList<Employee> employeeList) {
        if (promptYesNo("  Are you sure you want to permanently delete account? (Y/N) > ")) {
            employeeList.remove(employee);
            Employee.setTotalEmpCount(Employee.getTotalEmpCount() - 1); // decrement by one from total employee count
            System.out.println("  Account successfully deleted...");
        }
    }

    public static void deleteCustomerAccount(Person currentUser, ArrayList<Customer> customerList, ArrayList<Employee> employeeList, ArrayList<Owner> ownerList, ArrayList<Promotion> promotions) {
        if (promptYesNo("  Are you sure you want to permanently delete account? (Y/N) > ")) {
            customerList.remove((Customer) currentUser); // remove account obj from customer array
            Customer.setTotalCustCount(Customer.getTotalCustCount() - 1); // decrement by one from total customer count
            logout(currentUser); // turn current user to null
            mainMenu(currentUser, customerList, employeeList, ownerList, promotions); // return to main menu
        }
    }

    public static void deleteOwnerAccount(Person currentUser, ArrayList<Customer> customerList, ArrayList<Employee> employeeList, ArrayList<Owner> ownerList, ArrayList<Promotion> promotions) {
        if (promptYesNo("  Are you sure you want to permanently delete account? (Y/N) > ")) {
            ownerList.remove((Owner) currentUser); // remove account obj from owner array
            Owner.setTotalOwnerCount(Owner.getTotalOwnerCount() - 1); // decrement by one from total owner count
            logout(currentUser); // turn current user to null
            mainMenu(currentUser, customerList, employeeList, ownerList, promotions); // return to main menu
        }
    }

    public static Promotion inputPromotion(ArrayList<Promotion> promotions) {
        Promotion selected;
        // choose which employee to book with
        System.out.println("\n     Promotion List");
        System.out.println("  -------------------------");
        for (int i = 0; i < promotions.size() ; i++) {
            System.out.println("  "+(i+1)+ ". "+
                    promotions.get(i).getPromoID() +
                    " " +
                    promotions.get(i).getPromoCode()); // display promo
        }
        // prompt user to choose promotion
        do {
            try {
                selected = promotions.get(Main.promptInt("  Choose a promotion > ") - 1);
                return selected;
            } catch (IndexOutOfBoundsException e) {
                System.out.println("  Invalid choice selected...");
            }
        }
        while (true);
    }

    public static void editProfileMenu(Person currentUser, ArrayList<Customer> customerList, ArrayList<Employee> employeeList, ArrayList<Owner> ownerList) {
        boolean exitFlag;
        do {
            exitFlag = false;
            System.out.print("\n                             *** Edit Profile ***\n");
            System.out.print("  +--------------------------------------------------------------------------+\n");
            System.out.print("  |                                    |                                     |\n");
            System.out.printf("  |   First Name > %-20s|                                     |\n",currentUser.getFirstName());
            System.out.printf("  |   Last Name  > %-20s|   Gender     > %c                    |\n",currentUser.getLastName(),currentUser.getGender());
            System.out.printf("  |	                                   |   Phone No.  > %-12s         |\n",currentUser.getTel());
            System.out.printf("  |                                    |   Birth Date > %-8s           |\n",dateToString(currentUser.getDob()));
            System.out.print("  |--------------------------------------------------------------------------|\n");
            System.out.print("  |                                                                          |\n");
            System.out.printf("  |   Username > %-20s                                        |\n",currentUser.getUsername());
            System.out.printf("  |   Password > %-20s                                        |\n",Person.censorPassword(currentUser.getPassword()));
            System.out.printf("  |   Email    > %-30s                              |\n",currentUser.getEmail());
            System.out.printf("  |   Address  > %-60s|\n",currentUser.getAddress().limitAddress());
            System.out.print("  |                                                                          |\n");
            System.out.print("  +--------------------------------------------------------------------------+\n\n");
            System.out.println("  1. First Name");
            System.out.println("  2. Last Name");
            System.out.println("  3. Gender");
            System.out.println("  4. Phone No.");
            System.out.println("  5. Date of Birth");
            System.out.println("  6. Username");
            System.out.println("  7. Password");
            System.out.println("  8. E-mail");
            System.out.println("  9. Address");
            System.out.println("  10. Back");

            switch (Main.promptInt("  Please enter a selection > ")) {
                case 1 -> currentUser.setFirstName(inputFirstname());
                case 2 -> currentUser.setLastName(inputLastname());
                case 3 -> currentUser.setGender(inputGender());
                case 4 -> currentUser.setTel(inputTel());
                case 5 -> {
                    currentUser.setDob(inputDOB());
                    currentUser.setAge((int) ChronoUnit.YEARS.between(currentUser.getDob(), LocalDate.now()));
                }
                case 6 -> {
                    if (currentUser instanceof Customer) {
                        currentUser.setUsername(inputCustomerUsername(customerList));
                    }
                    else if (currentUser instanceof Employee) {
                        currentUser.setUsername(inputEmployeeUsername(employeeList));
                    }
                    else if (currentUser instanceof Owner) {
                        currentUser.setUsername(inputOwnerUsername(ownerList));
                    }
                }
                case 7 -> currentUser.setPassword(editPassword(currentUser));
                case 8 -> currentUser.setEmail(inputEmail());
                case 9 -> currentUser.setAddress(createAddress());
                case 10 -> {
                    return;
                }
                default -> {
                    System.out.println("  Invalid menu choice entered...");
                    exitFlag = true; // loop
                }
            }
        }
        while (exitFlag);
        System.out.println("\n\t\t\t\t\t\t    *** New Profile Info ***");
        System.out.println(currentUser);
        System.out.println("  Changes made successfully\n");
        pressAnyKeyToContinue();

    }

    public static String editPassword(Person currentUser) {
        while (!promptString("\n  Enter current password > ").equals(currentUser.getPassword())) {
            System.out.println("\n  Invalid password entered...");
        }
        return inputPassword();
    }

    /**
     * Below are the validations required by the owner side display menu,
     * to ensure that an appropriate message will be displayed to the user.
     */

    public static boolean checkReserveRecords(ArrayList<Customer> customerList) {
        if (!checkCustomerRecord(customerList)) {
            return false; // if customer record is empty
        }
        for (Customer customer : customerList) {
            // if there is reservation records then return true
            if (!(customer.getReservation().isEmpty())) {
                return true;
            }
        }
        return false;
    }

    public static boolean checkBillRecords(ArrayList<Customer> customerList) {
        if (!checkCustomerRecord(customerList)) {
            return false; // if customer record is empty
        }
        for (Customer customer : customerList) {
            // if there is billing record then return true
            if (customer.getBill() != null) {
                return true;
            }
        }
        return false;
    }

    public static boolean checkBillHistoryRecords(ArrayList<Customer> customerList) {
        if (!checkCustomerRecord(customerList)) {
            return false; // if customer record is empty
        }
        for (Customer customer : customerList) {
            // if there is billing history record then return true
            if (!(customer.getBillHistory().isEmpty())) {
                return true;
            }
        }
        return false;
    }

    //--------------------------------------------------------------------------------------------

    public static void employeeMenu(ArrayList<Employee> employeeList) {
        do {
            System.out.println("\n\n   ________________________________________ ");
            System.out.println("  |                                        |");
            System.out.println("  |          *** Employee Menu ***         |");
            System.out.println("  |                                        |");
            System.out.println("  |         1. Add Employee                |");
            System.out.println("  |         2. Edit Employee Salary        |");
            System.out.println("  |         3. Remove Employee             |");
            System.out.println("  |         4. Display Employee            |");
            System.out.println("  |         5. Back                        |");
            System.out.println("  |________________________________________|\n");

            switch (Main.promptInt("  Please enter a selection > ")) {
                case 1 -> employeeSignup(employeeList);
                case 2 -> {
                    if (checkEmployeeRecord(employeeList)) {
                        Employee emp = inputEmployee(employeeList);
                        emp.setSalary(promptDouble("\n  Enter new salary > RM "));
                        System.out.println("\n\t\t\t\t\t\t  *** New Profile Info ***");
                        System.out.println(emp.toString());
                        System.out.println("  Salary modification success\n");
                        pressAnyKeyToContinue();
                    } else {
                        System.out.println("  No employee records found...");
                    }
                }
                case 3 -> {
                    if (checkEmployeeRecord(employeeList)) {
                        deleteEmployeeAccount(inputEmployee(employeeList), employeeList);
                    } else {
                        System.out.println("  No employee records found...");
                    }
                }
                case 4 -> displayEmployee(employeeList);
                case 5 -> {
                    return;
                }
                default -> System.out.println("  Invalid menu choice entered...");
            }
        }
        while (true);
    }

    public static void billingMenu(Person currentUser, ArrayList<Promotion> promotions, ArrayList<Employee> employeeList) {
        do {
            System.out.println("\n\n   ________________________________________ ");
            System.out.println("  |                                        |");
            System.out.println("  |          *** Billing Menu ***          |");
            System.out.println("  |                                        |");
            System.out.println("  |        1. Check Out                    |");
            System.out.println("  |        2. Search Billing History       |");
            System.out.println("  |        3. Display Billing History      |");
            System.out.println("  |        4. Display Billing              |");
            System.out.println("  |        5. Back                         |");
            System.out.println("  |________________________________________|\n");

            switch (Main.promptInt("  Please enter a selection > ")) {
                case 1 -> checkOut(currentUser, promotions, employeeList);
                case 2 -> searchCurrentCustBillingHistory(currentUser);
                case 3 -> displayBillingHistory(currentUser);
                case 4 -> displayBilling(currentUser);
                case 5 -> {
                    return;
                }
                default -> System.out.println("  Invalid menu choice entered...");
            }
        }
        while (true);
    }

    public static void cancelReservation(Person currentUser, ArrayList<Employee> employeeList) {
        if (((Customer) currentUser).getReservation().isEmpty()) {
            System.out.println("  No reservation records found...");
            pressAnyKeyToContinue();
            return;
        }
        System.out.println("\n\n  Remove a reservation : ");
        Reservation reserve = promptReservation(currentUser);
        if (!promptYesNo("\n\n  Are you sure to remove this reservation?\n** There will be no refunds issued if paid **\n\n (Y/N) >")) {
            return;
        }
        // remove from reservation array
        ((Customer) currentUser).removeReservation(reserve);
        // remove bath, groom, massage from schedule
        if (!(reserve.getServices() instanceof Shelter)) {
            for (Employee obj : employeeList) {
                if (obj.getWorkSchedule().getReservation(reserve)) {
                    // remove from that employee schedule
                    obj.getWorkSchedule().removeFromSchedule(reserve);
                }
            }
        }
        System.out.println("\n\n  Reservation successfully removed");
        pressAnyKeyToContinue();
    }

    public static Service createService(Pet pet) {
        Service service = null;
        // different type of pet have specific services
        boolean petFlag;
        if (pet instanceof Dog) {
            Dog.displayServices();
            do {
                petFlag = false;
                switch (Main.promptInt("  Select a service type > ")) {
                    case 1 -> service = promptGroomService(new Groom());
                    case 2 -> service = promptBathService(new Bath());
                    case 3 -> service = promptMassageService(new Massage());
                    case 4 -> service = promptShelterService(new Shelter());
                    default -> {
                        System.out.println("  Invalid choice selected...");
                        petFlag = true;
                    }
                }
            }
            while (petFlag);
        } else if (pet instanceof Cat) {
            do {
                Cat.displayServices();
                petFlag = false;
                switch (Main.promptInt("  Select a service type > ")) {
                    case 1 -> service = promptGroomService(new Groom());
                    case 2 -> service = promptBathService(new Bath());
                    case 3 -> service = promptMassageService(new Massage());
                    case 4 -> service = promptShelterService(new Shelter());
                    default -> {
                        System.out.println("  Invalid choice selected...");
                        petFlag = true;
                    }
                }
            }
            while (petFlag);
        } else if (pet instanceof Bird) {
            do {
                Bird.displayServices();
                petFlag = false;
                if (Main.promptInt("  Select a service type > ") == 1) {
                    service = promptShelterService(new Shelter());
                } else {
                    System.out.println("  Invalid choice selected...");
                    petFlag = true;
                }
            }
            while (petFlag);
        } else if (pet instanceof Rabbit) {
            do {
                Rabbit.displayServices();
                petFlag = false;
                switch (Main.promptInt("  Select a service type > ")) {
                    case 1 -> service = promptGroomService(new Groom());
                    case 2 -> service = promptBathService(new Bath());
                    case 3 -> service = promptShelterService(new Shelter());
                    default -> {
                        System.out.println("  Invalid choice selected...");
                        petFlag = true;
                    }
                }
            }
            while (petFlag);
        }
        return service;
    }

    public static Reservation promptReservation(Person currentUser) {
        do {
            System.out.println("\n\t  Reservation Records");
            System.out.println("  -------------------------");
            for (int i = 0; i < ((Customer) currentUser).getReservation().size(); i++) {
                System.out.println("  " + (i + 1) + ". " + ((Customer) currentUser).getReservation().get(i).getReserveID() + " " +
                        ((Customer) currentUser).getReservation().get(i).getServices().getClass().getSimpleName() + " " +
                        ((Customer) currentUser).getReservation().get(i).getPet().getClass().getSimpleName());
            }
            try {
                return ((Customer) currentUser).getReservation().get(promptInt("  Please enter a selection > ") - 1);
            } catch (IndexOutOfBoundsException e) {
                System.out.println("  Invalid choice selected...");
            }
        }
        while (true);
    }

    public static ArrayList<Employee> checkEmployeeAvailable(int date, int session, ArrayList<Employee> employeeList) {
        ArrayList<Employee> output = new ArrayList<>();
        for (Employee employee : employeeList) {
            if (employee.getWorkSchedule().getSlotStatus(date - 1, session - 1) == Schedule.getAvailable()) {
                // if employee is free then add into array
                output.add(employee);
            }
        }
        return output;
    }

    public static String inputRemarks() {
        String remark;
        do {
            remark = promptString("\n  Enter remarks > ");
            if (remark.length() > 28) {
                System.out.println("\n  Remark entered is too long (limit to 28 characters)...");
                pressAnyKeyToContinue();
                continue;
            }
            return remark;
        }
        while (true);
    }

    public static void editReservation(Person currentUser, ArrayList<Employee> employeeList) {
        Reservation reservation;
        ArrayList<Reservation> reservations = new ArrayList<>(); // to store the unpaid reservations

        // filter through paid and unpaid reservations
        for (Reservation reserve :  ((Customer) currentUser).getReservation()) {
            if (!reserve.isPaymentStatus()) {
                reservations.add(reserve);
            }
        }

        if (reservations.isEmpty()) {
            System.out.println("  No reservation records found...");
            pressAnyKeyToContinue();
            return;
        }

        System.out.println("\n\n    Current Reservations");
        System.out.println("  -------------------------");
        for (int index = 0; index < reservations.size(); index++) {
            System.out.println("  " + (index + 1) + ". " + reservations.get(index).getReserveID() + " " +
                    reservations.get(index).getServices().getClass().getSimpleName() + " " +
                    reservations.get(index).getPet().getClass().getSimpleName());
        }
        do {
            try {
                reservation = reservations.get(promptInt("  Choose a reservation > ") - 1);
                break;
            } catch (IndexOutOfBoundsException e) {
                System.out.println("  Invalid choice entered...");
            }
        }
        while (true);

        if (!(reservation.getServices() instanceof Shelter)) {
            do {
                System.out.println("\n\n   ________________________________________ ");
                System.out.println("  |                                        |");
                System.out.println("  |      *** Edit Reservation Menu ***     |");
                System.out.println("  |                                        |");
                System.out.println("  |        1. Edit Remark                  |");
                System.out.println("  |        2. Edit Pet & Service           |");
                System.out.println("  |        3. Edit Reservation             |");
                System.out.println("  |           - Date                       |");
                System.out.println("  |           - Session                    |");
                System.out.println("  |           - Employee Responsible       |");
                System.out.println("  |        4. Back                         |");
                System.out.println("  |________________________________________|\n");

                switch (Main.promptInt("  Please enter a selection > ")) {
                    case 1 -> {
                        reservation.setRemarks(inputRemarks());
                        System.out.println("\n  Changes made successfully");
                        reservation.setEditMadeDateTime(LocalDateTime.now()); // set current datetime
                        pressAnyKeyToContinue();
                        return;
                    }
                    case 2 -> {
                        if (((Customer) currentUser).getPets().isEmpty()) {
                            System.out.println("  No pets found in the record...");
                            return;
                        }
                        reservation.setPet(selectPet(currentUser));
                        reservation.setServices(createService(reservation.getPet()));
                        System.out.println("\n  Changes made successfully");
                        reservation.setEditMadeDateTime(LocalDateTime.now()); // set current datetime
                        pressAnyKeyToContinue();
                        return;
                    }
                    case 3 -> {
                        int dateChoice, sessionBooked;
                        ArrayList<Employee> availableEmployee;
                        do {
                            System.out.println("\n\n\t  Reservation Dates");
                            System.out.println("  -----------------------------");
                            // display customer choice
                            for (int index = 1; index < 8; index++) {
                                System.out.println("  " + index + ". " + Schedule.futureDate(index) + " " + Schedule.getDayInWeek().get(index - 1));
                            }
                            // prompt user for date selection
                            do {
                                dateChoice = Main.promptInt("  Choose date to place booking > ");
                                // validate input
                                if (dateChoice > 0 && dateChoice < 8) {
                                    reservation.setReserveDateTime(Schedule.futureDateLocal(dateChoice));
                                    break;
                                }
                                System.out.println("  Invalid choice selected...");
                            }
                            while (true);
                            // display session choice
                            System.out.println("\n\n\t  +-----------------------+");
                            System.out.println("\t  |                       |");
                            System.out.println("\t  | - We are open daily - |");
                            System.out.println("\t  |                       |");
                            System.out.println("\t  | * Opening hours : 9am |");
                            System.out.println("\t  | * Closing hours : 6pm |");
                            System.out.println("\t  |                       |");
                            System.out.println("\t  +-----------------------+\n");
                            String[] sessions = {"Session 1 ->  9am - 11am",
                                    "Session 2 -> 11am -  1pm",
                                    "Session 3 ->  2pm -  4pm",
                                    "Session 4 ->  4pm -  6pm"};
                            for (int index = 0; index < 4; index++) {
                                System.out.println("  " + (index + 1) + ". " + sessions[index]);
                            }
                            // prompt user for session selection
                            do {
                                sessionBooked = promptInt("  Choose which session to place booking > ");
                            } while (sessionBooked <= 0 || sessionBooked >= 5);
                            availableEmployee = checkEmployeeAvailable(dateChoice, sessionBooked, employeeList);
                            // if no employees are available for that date and session
                            if (availableEmployee.isEmpty()) {
                                System.out.println("  Sorry, the session on the date chosen has been fully booked.\n\nPlease select another session and date.");
                                pressAnyKeyToContinue();
                                continue;
                            }
                            reservation.setReserveSession(sessionBooked);
                            reservation.setReserveDateTime(reservation.getReserveDateTime().withHour(Reservation.sessionToTime(sessionBooked)));
                            reservation.setReserveDateTime(reservation.getReserveDateTime().withMinute(0));

                            System.out.println("\n\n\t  Available Employees");
                            System.out.println("  ----------------------------");
                            // get employee input and set
                            reservation.setEmployeeSelected(inputEmployee(availableEmployee));
                            System.out.println(reservation);
                            System.out.println("\n  Changes made successfully");
                            pressAnyKeyToContinue();
                            reservation.setEditMadeDateTime(LocalDateTime.now()); // set current datetime

                            return;
                        }
                        while (true);
                    }
                    case 4 -> {
                        return;
                    }
                    default -> System.out.println("  Invalid menu choice entered...");
                }
            }
            while (true);
        } else {
            do {
                System.out.println("\n\n   ________________________________________ ");
                System.out.println("  |                                        |");
                System.out.println("  |      *** Edit Reservation Menu ***     |");
                System.out.println("  |                                        |");
                System.out.println("  |         1. Edit Remark                 |");
                System.out.println("  |         2. Edit Pet & Service          |");
                System.out.println("  |         3. Back                        |");
                System.out.println("  |________________________________________|\n");

                switch (Main.promptInt("  Please enter a selection > ")) {
                    case 1 -> {
                        reservation.setRemarks(inputRemarks());
                        System.out.println("\n  Changes made successfully");
                        reservation.setEditMadeDateTime(LocalDateTime.now()); // set current datetime
                        pressAnyKeyToContinue();
                        return;
                    }
                    case 2 -> {
                        if (((Customer) currentUser).getPets().isEmpty()) {
                            System.out.println("  No pets found in the record...");
                            return;
                        }
                        reservation.setPet(selectPet(currentUser));
                        reservation.setServices(createService(reservation.getPet()));
                        System.out.println("\n  Changes made successfully");
                        reservation.setEditMadeDateTime(LocalDateTime.now()); // set current datetime
                        pressAnyKeyToContinue();
                        return;
                    }
                    case 3 -> {
                        return;
                    }
                    default -> System.out.println("  Invalid menu choice entered...");
                }
            }
            while (true);
        }
    }

    public static void createReservation(Person currentUser, ArrayList<Employee> employeeList) {
        ArrayList<Employee> availableEmployee;
        Employee selected;
        LocalDateTime dateBooked;
        int sessionBooked;
        Pet pet;
        String remarks;
        Service service;
        Reservation reserve;
        int dateChoice;

        // check if there are no employees
        if (!checkEmployeeRecord(employeeList)) {
            System.out.println("  There are no employee currently available...");
            pressAnyKeyToContinue();
            return;
        }

        // check pet at least one
        if (((Customer) currentUser).getPets().size() == 0) {
            System.out.println("  You must have at least one pet...");
            return;
        }

        // display and choose pet
        pet = Main.selectPet(currentUser);

        // get service input
        service = createService(pet);

        if (!(service instanceof Shelter)) {
            do {
                System.out.println("\n\n\t  Reservation Dates");
                System.out.println("  -----------------------------");
                // display customer choice
                for (int index = 1; index < 8; index++) {
                    System.out.println("  " +index + ". " + Schedule.futureDate(index) + " " + Schedule.getDayInWeek().get(index - 1));
                }

                // prompt user for date selection
                do {
                    dateChoice = Main.promptInt("  Choose date to place booking > ");
                    // validate input
                    if (dateChoice > 0 && dateChoice < 8) {
                        dateBooked = Schedule.futureDateLocal(dateChoice);
                        break;
                    }
                    System.out.println("  Invalid choice selected...");
                }
                while (true);

                // display session choice
                System.out.println("\n\n\t  +-----------------------+");
                System.out.println("\t  |                       |");
                System.out.println("\t  | - We are open daily - |");
                System.out.println("\t  |                       |");
                System.out.println("\t  | * Opening hours : 9am |");
                System.out.println("\t  | * Closing hours : 6pm |");
                System.out.println("\t  |                       |");
                System.out.println("\t  +-----------------------+\n");
                String[] sessions = {"Session 1 ->  9am - 11am",//TanShiJing
                        "Session 2 -> 11am -  1pm",//TanShiJing
                        "Session 3 ->  2pm -  4pm",//TanShiJing
                        "Session 4 ->  4pm -  6pm"};//TanShiJing
                for (int index = 0; index < 4; index++) {
                    System.out.println("  "+(index + 1) + ". " + sessions[index]);
                }

                // prompt user for session selection
                do {
                    sessionBooked = promptInt("  Choose which session to place booking > ");
                } while (sessionBooked <= 0 || sessionBooked >= 5);

                availableEmployee = checkEmployeeAvailable(dateChoice, sessionBooked, employeeList);

                // if no employees are available for that date and session
                if (availableEmployee.isEmpty()) {
                    System.out.println("  Sorry, the session on the date chosen has been fully booked.\n\nPlease select another session and date.");
                    pressAnyKeyToContinue();
                    continue;
                }
                break;
            }
            while (true);

            // set session time into reservation date time
            dateBooked = dateBooked.withHour(Reservation.sessionToTime(sessionBooked));
            dateBooked = dateBooked.withMinute(0);

            // remarks
            remarks = inputRemarks();

            // choose employee
            System.out.println("\n\n\t  Available Employees");
            System.out.println("  ----------------------------");
            selected = inputEmployee(availableEmployee);

            // create reservation object
            reserve = new Reservation(dateBooked, service, pet, remarks, sessionBooked, selected);

            // add reservation to employee schedule
            selected.getWorkSchedule().addToSchedule(dateChoice - 1, sessionBooked - 1, reserve);

            // add reservation to customer reservation array record

        } else {
            // if service is shelter

            dateBooked = ((Shelter) service).getCheckInDate().atTime(0,0,0);

            // remarks
            remarks = inputRemarks();

            // create reservation object
            System.out.println("\n\n\t  Available Employees");
            System.out.println("  ----------------------------");
            reserve = new Reservation(dateBooked, service, pet, remarks, 0, inputEmployee(employeeList));

            // add reservation to customer reservation array record
        }
        ((Customer) currentUser).addReservation(reserve);
        // check if customer has current billing on hand
        if (((Customer) currentUser).getBill() == null) {
            ((Customer) currentUser).setBill(new Billing(reserve));// add reservation price and details to billing
        } else {
            // add to existing billing object
            ((Customer) currentUser).getBill().addBill(reserve);// add reservation price and details to billing
        }

        // display services detail
        System.out.println("\n\n  " + reserve + "\n\n" + service);

        System.out.println("  Reservation successfully created");
        pressAnyKeyToContinue();
    }

    public static void searchCustomer(ArrayList<Customer> customerList) {
        ArrayList<Customer> customers = new ArrayList<>();
        System.out.println("\n  Enter keyword to start searching : ");
        String firstname = promptString("  Enter first name > ").trim().toLowerCase();
        String lastname = promptString("  Enter last name  > ").trim().toLowerCase();

        // searching
        for (Customer customer : customerList) {
            // check first name and last name
            if (customer.getFirstName().toLowerCase().contains(firstname) &&
                    customer.getLastName().toLowerCase().contains(lastname)) {
                customers.add(customer); // add index to array
            }
        }

        // if no search results found
        if (customers.isEmpty()) {
            System.out.println("  No search results found...");
            pressAnyKeyToContinue();
            return;
        }

        // sorting
        boolean exitFlag;
        do {
            exitFlag = false;
            System.out.println("\n\n   ________________________ ");
            System.out.println("  |                        |");
            System.out.println("  |  *** Sorting Flow ***  |");
            System.out.println("  |                        |");
            System.out.println("  |     1. Ascending       |");
            System.out.println("  |     2. Descending      |");
            System.out.println("  |________________________|");


            switch (promptInt("\n  Enter a selection > ")) {
                case 1 -> customers.sort((o1, o2) -> o1.getFirstName().compareTo(o2.getFirstName()));
                case 2 -> customers.sort((o1, o2) -> o2.getFirstName().compareTo(o1.getFirstName()));
                default -> {
                    System.out.println("Invalid choice entered...");
                    exitFlag = true;
                }
            }
        }
        while (exitFlag);

        boolean backFlag = false;
        System.out.println("""
				\n  +-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+
				  | Cust.ID |      Name       |  Age  | Gender | Contact Number | Birth Date |             Email            |    Username    | Register Date |                           Address                          |
				  +---------+-----------------+-------+--------+----------------+------------+------------------------------+----------------+---------------+------------------------------------------------------------+""");
        for (int index = 0; index < customers.size(); index++) {
            System.out.println("  "+customers.get(index).displayRow());
            System.out.println("  +-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+");
            if ((index+1) % 10 == 0 && (index+1)!=customers.size()) {
                switch (promptInt("\n\nDisplaying results (" + (index - 8) + "-" + (index + 1) + ") out of " + customers.size() + " records\n1. Continue displaying records\n2. Back\n\nPlease enter a selection > ")) {
                    case 1 -> {
                        System.out.println("""
				\n  +-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+
				  | Cust.ID |      Name       |  Age  | Gender | Contact Number | Birth Date |             Email            |    Username    | Register Date |                           Address                          |
				  +---------+-----------------+-------+--------+----------------+------------+------------------------------+----------------+---------------+------------------------------------------------------------+""");
                    }
                    case 2 -> backFlag = true;
                    default -> System.out.println("Invalid selection entered...");
                }
                if (backFlag) {
                    break;
                }
            }
        }
        pressAnyKeyToContinue();
    }

    public static void searchBillingHistory(ArrayList<Customer> customerList) {
        do {
            System.out.println("\n   Search Billing History");
            System.out.println("  -----------------------------");
            System.out.println("  1. Search by Total Price");
            System.out.println("  2. Search by Payment Date");
            System.out.println("  3. Back");

            switch (promptInt("  Please enter a selection > ")) {
                case 1 -> searchTotalBillingHistory(customerList);
                case 2 -> searchDateBillingHistory(customerList);
                case 3 -> {
                    return;
                }
                default -> {
                    System.out.println("  Invalid choice entered...");
                }
            }
        } while (true);
    }

    public static void searchTotalBillingHistory(ArrayList<Customer> customerList) {
        double upper, lower;
        ArrayList<Billing> billHistories = new ArrayList<>();
        do {
            upper = promptDouble("\n\n  Enter upper total price > ");
            lower = promptDouble("  Enter lower total price > ");
            if (upper >= lower) {
                break;
            }
            System.out.println("\n  Upper should not be lower than lower...");
        }
        while (true);

        //searching
        // loop customer
        for (Customer customer : customerList) {
            // loop billing history
            for (int billingIndex = 0; billingIndex < customer.getBillHistory().size(); billingIndex++) {
                if (customer.getBillHistory().get(billingIndex).getGrandTotal() >= lower &&
                        customer.getBillHistory().get(billingIndex).getGrandTotal() <= upper) {
                    billHistories.add(customer.getBillHistory().get(billingIndex));
                }
            }
        }

        // check if there are no results found
        if (billHistories.isEmpty()) {
            System.out.println("  No search results found...");
            pressAnyKeyToContinue();
            return;
        }

        // sorting
        boolean exitFlag;
        do {
            exitFlag = false;
            System.out.println("\n\n   ________________________ ");
            System.out.println("  |                        |");
            System.out.println("  |  *** Sorting Flow ***  |");
            System.out.println("  |                        |");
            System.out.println("  |     1. Ascending       |");
            System.out.println("  |     2. Descending      |");
            System.out.println("  |________________________|");


            switch (promptInt("\n  Enter a selection > ")) {
                case 1 -> billHistories.sort((c1, c2) -> Double.compare(c1.getGrandTotal(), c2.getGrandTotal()));
                case 2 -> billHistories.sort((c1, c2) -> Double.compare(c2.getGrandTotal(), c1.getGrandTotal()));
                default -> {
                    System.out.println("Invalid choice entered...");
                    exitFlag = true;
                }
            }
        }
        while (exitFlag);

        // display results
        boolean breakLoop, backFlag = false;
        System.out.println("""
                \n  +----------------------------------------------------------------------------------------------------------------------------------------------+
                  |  Cust.ID  |   Cust. Name   | Total Amount | Grand Total | Trans. ID | Pay Method | Promo Origin |  Payment Date   | Promo Applied | Services |
                  +-----------+----------------+--------------+-------------+-----------+------------+--------------+-----------------+---------------+----------+""");
        for (int index = 0; index < billHistories.size(); index++) {
            breakLoop = false;
            for (Customer cust : customerList) {
                for (Billing bill : cust.getBillHistory()) {
                    if (bill.equals(billHistories.get(index))) {
                        System.out.printf("  |  %6s   |%-16s", cust.getID(),cust.fullName());
                        breakLoop = true;
                        break;
                    }
                }
                if (breakLoop) {
                    break;
                }
            }
            System.out.print(billHistories.get(index).displayRow()+"\n"); // billing class
            System.out.println("  +----------------------------------------------------------------------------------------------------------------------------------------------+");
            if ((index+1) % 10 == 0 && (index+1)!=billHistories.size()) {
                switch (promptInt("\n\n  Displaying results (" + (index - 8) + "-" + (index + 1) + ") out of " + billHistories.size() + " records\n  1. Continue displaying records\n  2. Back\n\n  Please enter a selection > ")) {
                    case 1 -> {
                        System.out.println("""
                \n  +----------------------------------------------------------------------------------------------------------------------------------------------+
                  |  Cust.ID  |   Cust. Name   | Total Amount | Grand Total | Trans. ID | Pay Method | Promo Origin |  Payment Date   | Promo Applied | Services |
                  +-----------+----------------+--------------+-------------+-----------+------------+--------------+-----------------+---------------+----------+""");
                    }
                    case 2 -> backFlag = true;
                    default -> System.out.println("Invalid selection entered...");
                }
                if (backFlag) {
                    break;
                }
            }
        }

        pressAnyKeyToContinue();
    }

    public static void searchDateBillingHistory(ArrayList<Customer> customerList) {
        LocalDate startDate = inputSearchStartDate();
        LocalDate endDate = inputSearchEndDate(startDate);

        // searching
        ArrayList<Billing> billHistories = new ArrayList<>();

        // loop customer
        for (Customer customer : customerList) {
            // loop billing history
            for (int billingIndex = 0; billingIndex < customer.getBillHistory().size(); billingIndex++) {
                if ((customer.getBillHistory().get(billingIndex).getPaymentDate().isAfter(startDate) &&
                        customer.getBillHistory().get(billingIndex).getPaymentDate().isBefore(endDate)) ||
                        customer.getBillHistory().get(billingIndex).getPaymentDate().isEqual(startDate) ||
                        customer.getBillHistory().get(billingIndex).getPaymentDate().isEqual(endDate)){
                    billHistories.add(customer.getBillHistory().get(billingIndex));
                }
            }
        }

        // check if there are no results found
        if (billHistories.isEmpty()) {
            System.out.println("  No search results found...");
            pressAnyKeyToContinue();
            return;
        }

        // sorting
        boolean exitFlag, backFlag = false;
        do {
            exitFlag = false;
            System.out.println("\n\n   ________________________ ");
            System.out.println("  |                        |");
            System.out.println("  |  *** Sorting Flow ***  |");
            System.out.println("  |                        |");
            System.out.println("  |     1. Ascending       |");
            System.out.println("  |     2. Descending      |");
            System.out.println("  |________________________|");


            switch (promptInt("\n  Enter a selection > ")) {
                case 1 -> billHistories.sort((o1, o2) -> o1.getPaymentDate().compareTo(o2.getPaymentDate()));
                case 2 -> billHistories.sort((o1, o2) -> o2.getPaymentDate().compareTo(o1.getPaymentDate()));
                default -> {
                    System.out.println("Invalid choice entered...");
                    exitFlag = true;
                }
            }
        }
        while (exitFlag);

        // display results
        boolean breakLoop;
        System.out.println("""
                \n  +----------------------------------------------------------------------------------------------------------------------------------------------+
                  |  Cust.ID  |   Cust. Name   | Total Amount | Grand Total | Trans. ID | Pay Method | Promo Origin |  Payment Date   | Promo Applied | Services |
                  +-----------+----------------+--------------+-------------+-----------+------------+--------------+-----------------+---------------+----------+""");
        for (int index = 0; index < billHistories.size(); index++) {
            breakLoop = false;
            for (Customer cust : customerList) {
                for (Billing bill : cust.getBillHistory()) {
                    if (bill.equals(billHistories.get(index))) {
                        System.out.printf("  |  %6s   |%-16s", cust.getID(),cust.fullName());
                        breakLoop = true;
                        break;
                    }
                }
                if (breakLoop) {
                    break;
                }
            }
            System.out.print(billHistories.get(index).displayRow() + "\n"); // billing class
            System.out.println("  +----------------------------------------------------------------------------------------------------------------------------------------------+");
            if ((index+1) % 10 == 0 && (index+1)!=billHistories.size()) {
                switch (promptInt("\n\n  Displaying results (" + (index - 8) + "-" + (index + 1) + ") out of " + billHistories.size() + " records\n  1. Continue displaying records\n  2. Back\n\n  Please enter a selection > ")) {
                    case 1 -> {
                        System.out.println("""
                \n  +----------------------------------------------------------------------------------------------------------------------------------------------+
                  |  Cust.ID  |   Cust. Name   | Total Amount | Grand Total | Trans. ID | Pay Method | Promo Origin |  Payment Date   | Promo Applied | Services |
                  +-----------+----------------+--------------+-------------+-----------+------------+--------------+-----------------+---------------+----------+""");
                    }
                    case 2 -> backFlag = true;
                    default -> System.out.println("Invalid selection entered...");
                }
                if (backFlag) {
                    break;
                }
            }
        }
        pressAnyKeyToContinue();
    }

    public static void searchTotalBilling(ArrayList<Customer> customerList) {
        ArrayList<Customer> customers = new ArrayList<>();
        double upper, lower;
        do {
            upper = promptDouble("\n\n  Enter upper total price > ");
            lower = promptDouble("  Enter lower total price > ");
            if (upper >= lower) {
                break;
            }
            System.out.println("\n  Upper should not be lower than lower...");
        }
        while (true);
        // searching
        for (Customer customer : customerList) {
            // skip empty bill
            if (customer.getBill() == null) {
                continue;
            }
            if (customer.getBill().calcTotalAmount() >= lower && customer.getBill().calcTotalAmount() <= upper) {
                customers.add(customer);
            }
            System.out.println(customer.getBill().getTotalAmount());
        }

        // check if there are no results found
        if (customers.isEmpty()) {
            System.out.println("  No search results found...");
            pressAnyKeyToContinue();
            return;
        }

        // sorting
        boolean exitFlag;
        do {
            exitFlag = false;
            System.out.println("\n\n   ________________________ ");
            System.out.println("  |                        |");
            System.out.println("  |  *** Sorting Flow ***  |");
            System.out.println("  |                        |");
            System.out.println("  |     1. Ascending       |");
            System.out.println("  |     2. Descending      |");
            System.out.println("  |________________________|");


            switch (promptInt("\n  Enter a selection > ")) {
                case 1 -> customers.sort((c1, c2) -> Double.compare(c1.getBill().calcTotalAmount(), c2.getBill().calcTotalAmount()));
                case 2 -> customers.sort((c1, c2) -> Double.compare(c2.getBill().calcTotalAmount(), c1.getBill().calcTotalAmount()));
                default -> {
                    System.out.println("Invalid choice entered...");
                    exitFlag = true;
                }
            }
        }
        while (exitFlag);

        boolean backFlag = false;
        System.out.println("""
                    \n  +----------------------------------------+----------+
                      |  Cust.ID  | Total Amount | Grand Total | Services |
                      +-----------+--------------+-------------+----------+""");
        for (int index = 0; index < customers.size(); index++) {
            System.out.printf("  |  %6s   |  %10s  | %10s  |Count : %d |\n",customers.get(index).getID(),convertCurrency(customers.get(index).getBill().getTotalAmount()),convertCurrency(customers.get(index).getBill().getGrandTotal()),customers.get(index).getBill().getBillDetails().size());//TanShiJing
            System.out.println("  +----------------------------------------+----------+");
            if ((index+1) % 10 == 0 && (index+1)!=customers.size()) {
                switch (promptInt("\n\n  Displaying results (" + (index - 8) + "-" + (index + 1) + ") out of " + customers.size() + " records\n  1. Continue displaying records\n  2. Back\n\n  Please enter a selection > ")) {
                    case 1 -> {
                        System.out.println("""
                    \n  +----------------------------------------+----------+
                      |  Cust.ID  | Total Amount | Grand Total | Services |
                      +-----------+--------------+-------------+----------+""");
                    }
                    case 2 -> backFlag = true;
                    default -> System.out.println("Invalid selection entered...");
                }
                if (backFlag) {
                    break;
                }
            }
        }
        pressAnyKeyToContinue();
    }


    public static void searchEmployee(ArrayList<Employee> employeeList) {
        do {
            System.out.println("\n      Search Employee");
            System.out.println("  -----------------------------");
            System.out.println("  1. Search by Salary");
            System.out.println("  2. Search by Employee Name");
            System.out.println("  3. Back");

            switch (promptInt("  Please enter a selection > ")) {
                case 1 -> searchSalaryEmployee(employeeList);
                case 2 -> searchNameEmployee(employeeList);
                case 3 -> {
                    return;
                }
                default -> {
                    System.out.println("  Invalid choice entered...");
                }
            }
        } while (true);
    }

    public static void searchSalaryEmployee(ArrayList<Employee> employeeList) {
        ArrayList<Employee> employees = new ArrayList<>();
        if (!checkEmployeeRecord(employeeList)) {
            System.out.println("  No employees found in record...");
            return;
        }
        double upper, lower;
        do {
            upper = promptDouble("\n\n  Enter upper total price > ");
            lower = promptDouble("  Enter lower total price > ");
            if (upper >= lower) {
                break;
            }
            System.out.println("\n  Upper should not be lower than lower...");
        }
        while (true);
        // searching
        for (Employee employee : employeeList) {
            if (employee.getSalary() >= lower && employee.getSalary() <= upper) {
                employees.add(employee);
            }
        }

        // if no search results found
        if (employees.isEmpty()) {
            System.out.println("  No search results found...");
            pressAnyKeyToContinue();
            return;
        }

        // sorting
        boolean exitFlag;
        do {
            exitFlag = false;
            System.out.println("\n\n   ________________________ ");
            System.out.println("  |                        |");
            System.out.println("  |  *** Sorting Flow ***  |");
            System.out.println("  |                        |");
            System.out.println("  |     1. Ascending       |");
            System.out.println("  |     2. Descending      |");
            System.out.println("  |________________________|");


            switch (promptInt("\n  Enter a selection > ")) {
                case 1 -> employees.sort((c1, c2) -> Double.compare(c1.getSalary(), c2.getSalary()));
                case 2 -> employees.sort((c1, c2) -> Double.compare(c2.getSalary(), c1.getSalary()));
                default -> {
                    System.out.println("Invalid choice entered...");
                    exitFlag = true;
                }
            }
        }
        while (exitFlag);

        boolean backFlag = false;

        System.out.println("""
                \n  +-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+
                  | Emp.ID |      Name       |  Age  | Gender | Contact Number | Birth Date |   Salary   |            Email             |    Username    | Register Date |                           Address                          |
                  +--------+-----------------+-------+--------+----------------+------------+------------+------------------------------+----------------+---------------+------------------------------------------------------------+""");
        for (int index = 0; index < employees.size(); index++) {
            System.out.println("  "+employees.get(index).displayRow());
            System.out.println("  +-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+");
            if ((index+1) % 10 == 0 && (index+1)!=employees.size()) {
                switch (promptInt("\n\n  Displaying results (" + (index - 8) + "-" + (index + 1) + ") out of " + employees.size() + " records\n  1. Continue displaying records\n  2. Back\n\n  Please enter a selection > ")) {
                    case 1 -> {
                        System.out.println("""
                \n  +-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+
                  | Emp.ID |      Name       |  Age  | Gender | Contact Number | Birth Date |   Salary   |            Email             |    Username    | Register Date |                           Address                          |
                  +--------+-----------------+-------+--------+----------------+------------+------------+------------------------------+----------------+---------------+------------------------------------------------------------+""");
                    }
                    case 2 -> backFlag = true;
                    default -> System.out.println("Invalid selection entered...");
                }
                if (backFlag) {
                    break;
                }
            }
        }
        pressAnyKeyToContinue();
    }

    public static void searchNameEmployee(ArrayList<Employee> employeeList) {
        ArrayList<Employee> employees = new ArrayList<>();

        if (!checkEmployeeRecord(employeeList)) {
            System.out.println("  No employees found in record...");
            return;
        }
        System.out.println("\n  Enter keyword to start searching : ");
        String firstname = promptString("  Enter first name > ").trim().toLowerCase();
        String lastname = promptString("  Enter last name  > ").trim().toLowerCase();//TanShiJing
        // searching
        for (Employee employee : employeeList) {
            // check first name and last name
            if ((employee.getFirstName().toLowerCase().contains(firstname) &&
                    employee.getLastName().toLowerCase().contains(lastname))) {
                employees.add(employee); // add employee to array
            }
        }

        // if no search results found
        if (employees.isEmpty()) {
            System.out.println("  No search results found...");
            pressAnyKeyToContinue();
            return;
        }

        // sorting
        boolean exitFlag;
        do {
            exitFlag = false;
            System.out.println("\n\n   ________________________ ");
            System.out.println("  |                        |");
            System.out.println("  |  *** Sorting Flow ***  |");
            System.out.println("  |                        |");
            System.out.println("  |     1. Ascending       |");
            System.out.println("  |     2. Descending      |");
            System.out.println("  |________________________|");


            switch (promptInt("\n  Enter a selection > ")) {
                case 1 -> employees.sort((o1, o2) -> o1.getFirstName().compareTo(o2.getFirstName()));
                case 2 -> employees.sort((o1, o2) -> o2.getFirstName().compareTo(o1.getFirstName()));
                default -> {
                    System.out.println("Invalid choice entered...");
                    exitFlag = true;
                }
            }
        }
        while (exitFlag);

        boolean backFlag = false;

        System.out.println("""
                \n  +-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+
                  | Emp.ID |      Name       |  Age  | Gender | Contact Number | Birth Date |   Salary   |            Email             |    Username    | Register Date |                           Address                          |
                  +--------+-----------------+-------+--------+----------------+------------+------------+------------------------------+----------------+---------------+------------------------------------------------------------+""");
        for (int index = 0; index < employees.size(); index++) {
            System.out.println("  "+employees.get(index).displayRow());
            System.out.println("  +-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+");
            if ((index+1) % 10 == 0 && (index+1)!=employees.size()) {
                switch (promptInt("\n\n  Displaying results (" + (index - 8) + "-" + (index + 1) + ") out of " + employees.size() + " records\n  1. Continue displaying records\n  2. Back\n\n  Please enter a selection > ")) {
                    case 1 -> {
                        System.out.println("""
                \n  +-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+
                  | Emp.ID |      Name       |  Age  | Gender | Contact Number | Birth Date |   Salary   |            Email             |    Username    | Register Date |                           Address                          |
                  +--------+-----------------+-------+--------+----------------+------------+------------+------------------------------+----------------+---------------+------------------------------------------------------------+""");
                    }
                    case 2 -> backFlag = true;
                    default -> System.out.println("Invalid selection entered...");
                }
                if (backFlag) {
                    break;
                }
            }
        }
        pressAnyKeyToContinue();
    }

    public static void searchReservation(ArrayList<Customer> customerList) {
        do {
            System.out.println("\n\n\t  Search Reservation");
            System.out.println("  -----------------------------");
            System.out.println("  1. Search by Date");
            System.out.println("  2. Search by Customer Name");
            System.out.println("  3. Back");

            switch (promptInt("  Please enter a selection > ")) {
                case 1 -> searchDateReservation(customerList);
                case 2 -> searchNameReservation(customerList);
                case 3 -> {
                    return;
                }
                default -> {
                    System.out.println("  Invalid choice entered...");
                }
            }
        } while (true);
    }

    public static void searchDateReservation(ArrayList<Customer> customerList) {
        if (!checkCustomerRecord(customerList)) {
            System.out.println("  No customers found in record...");
            return;
        }
        LocalDate startDate = inputSearchStartDate();

        LocalDate endDate = inputSearchEndDate(startDate);

        ArrayList<Reservation> reservations = new ArrayList<>();

        // searching
        for (Customer customer : customerList) {

            for (Reservation reservation : customer.getReservation()) {
                if ((reservation.getReserveDateTime().isAfter(startDate.atTime(0, 0, 0)) && reservation.getReserveDateTime().isBefore(endDate.atTime(0, 0, 0))) ||
                        reservation.getReserveDateTime().isEqual(startDate.atTime(0, 0, 0)) || reservation.getReserveDateTime().isEqual(endDate.atTime(0, 0, 0))) {
                    reservations.add(reservation); // add reservation to array
                }
            }
        }
        if (reservations.isEmpty()) {
            System.out.println("  No search results found...");
            pressAnyKeyToContinue();
            return;
        }

        // sorting
        boolean exitFlag;
        do {
            exitFlag = false;
            System.out.println("\n\n   ________________________ ");
            System.out.println("  |                        |");
            System.out.println("  |  *** Sorting Flow ***  |");
            System.out.println("  |                        |");
            System.out.println("  |     1. Ascending       |");
            System.out.println("  |     2. Descending      |");
            System.out.println("  |________________________|");


            switch (promptInt("\n  Enter a selection > ")) {
                case 1 -> reservations.sort((o1, o2) -> o1.getReserveDateTime().compareTo(o2.getReserveDateTime()));
                case 2 -> reservations.sort((o1, o2) -> o2.getReserveDateTime().compareTo(o1.getReserveDateTime()));
                default -> {
                    System.out.println("Invalid choice entered...");
                    exitFlag = true;
                }
            }
        }
        while (exitFlag);

        boolean backFlag = false;

        System.out.println("""
                \n  +-------------------------------------------------------------------------------------------------------------------------------+
                  |  Cust.ID  | Resv.ID | Service | Resv. Timestamp | Pet ID | Pet Type | Resv. Session | Resv. Made TimeStamp |  Employee Name   |
                  +-----------+---------+---------+-----------------+--------+----------+---------------+----------------------+------------------+""");
        for (int index = 0; index < reservations.size(); index++) {
            for (Customer cust : customerList) {
                if (cust.getReservation().contains(reservations.get(index))) {
                    System.out.printf("  |  %6s   ", cust.getID());
                    break;
                }
            }
            System.out.print(reservations.get(index).displayRow() + "\n");
            System.out.println("  +-------------------------------------------------------------------------------------------------------------------------------+");
            if ((index+1) % 10 == 0 && (index+1)!=reservations.size()) {
                switch (promptInt("\n\n  Displaying results (" + (index - 8) + "-" + (index + 1) + ") out of " + reservations.size() + " records\n  1. Continue displaying records\n  2. Back\n\n  Please enter a selection > ")) {
                    case 1 -> {
                        System.out.println("""
                \n  +-------------------------------------------------------------------------------------------------------------------------------+
                  |  Cust.ID  | Resv.ID | Service | Resv. Timestamp | Pet ID | Pet Type | Resv. Session | Resv. Made TimeStamp |  Employee Name   |
                  +-----------+---------+---------+-----------------+--------+----------+---------------+----------------------+------------------+""");
                    }
                    case 2 -> backFlag = true;
                    default -> System.out.println("Invalid selection entered...");
                }
                if (backFlag) {
                    break;
                }
            }
        }
        pressAnyKeyToContinue();
    }

    public static LocalDate inputSearchStartDate() {
        LocalDate startDate;
        do {
            try {
                System.out.println("\n\n  Enter start date to search > ");
                startDate = LocalDate.of(Main.promptInt("  Year  > "), Main.promptInt("  Month > "), Main.promptInt("  Day   > "));//TanShiJing
                if (startDate.isBefore(LocalDate.now().minusYears(21))) {
                    System.out.println("  Date entered must be after year 2000...");
                    continue;
                }
                return startDate;
            } catch (DateTimeException e) {
                System.out.println("  Invalid date entered...");
            }
        }
        while (true);
    }

    public static LocalDate inputSearchEndDate(LocalDate startDate) {
        LocalDate endDate;
        do {
            try {
                System.out.println("\n  Enter end date to search > ");
                endDate = LocalDate.of(Main.promptInt("  Year  > "), Main.promptInt("  Month > "), Main.promptInt("  Day   > "));//TanShiJing
                if (endDate.isBefore(startDate)) {
                    System.out.println("  End date should not earlier than start date...");
                    continue;
                }
                return endDate;
            } catch (DateTimeException e) {
                System.out.println("  Invalid date entered...");
            }
        }
        while (true);
    }

    public static void searchNameReservation(ArrayList<Customer> customerList) {
        if (!checkCustomerRecord(customerList)) {
            System.out.println("  No customers found in record...");
            return;
        }
        System.out.println("\n  Enter keyword to start searching : ");
        String firstname = promptString("  Enter first name > ").trim().toLowerCase();
        String lastname = promptString("  Enter last name  > ").trim().toLowerCase();//TanShiJing

        // searching
        ArrayList<Reservation> reservations = new ArrayList<>();
        for (Customer customer : customerList) {
            // check first name and last name and customer reservation not empty
            if ((customer.getFirstName().toLowerCase().contains(firstname) &&
                    customer.getLastName().toLowerCase().contains(lastname)) &&
                    !(customer.getReservation().isEmpty())) {
                reservations.addAll(customer.getReservation()); // add reservation to array
            }
        }
        if (reservations.isEmpty()) {
            System.out.println("  No search results found...");
            pressAnyKeyToContinue();
            return;
        }

        boolean backFlag = false;

        System.out.println("""
                \n  +------------------------------------------------------------------------------------------------------------------------------------------------+
                  |  Cust.ID  |   Cust. Name   | Resv.ID | Service | Resv. Timestamp | Pet ID | Pet Type | Resv. Session | Resv. Made TimeStamp |  Employee Name   |
                  +-----------+----------------+---------+---------+-----------------+--------+----------+---------------+----------------------+------------------+""");
        for (int index = 0; index < reservations.size(); index++) {
            for (Customer cust : customerList) {
                if (cust.getReservation().contains(reservations.get(index))) {
                    System.out.printf("  |  %6s   |%-16s", cust.getID(), cust.fullName());
                    break;
                }
            }
            System.out.print(reservations.get(index).displayRow() + "\n");
            System.out.println("  +------------------------------------------------------------------------------------------------------------------------------------------------+");
            if ((index+1) % 10 == 0 && (index+1)!=reservations.size()) {
                switch (promptInt("\n\n  Displaying results (" + (index - 8) + "-" + (index + 1) + ") out of " + reservations.size() + " records\n  1. Continue displaying records\n  2. Back\n\n  Please enter a selection > ")) {
                    case 1 -> {
                        System.out.println("""
                \n  +------------------------------------------------------------------------------------------------------------------------------------------------+
                  |  Cust.ID  |   Cust. Name   | Resv.ID | Service | Resv. Timestamp | Pet ID | Pet Type | Resv. Session | Resv. Made TimeStamp |  Employee Name   |
                  +-----------+----------------+---------+---------+-----------------+--------+----------+---------------+----------------------+------------------+""");
                    }
                    case 2 -> backFlag = true;
                    default -> System.out.println("Invalid selection entered...");
                }
                if (backFlag) {
                    break;
                }
            }
        }
    }

    public static void addCard(Person currentUser) {
        System.out.println("\n\n\t  Add Your Card");
        System.out.println("  -------------------------");
        //Create card obj and add into customer array
        ((Customer) currentUser).addCard(
                new Card(inputNameOnCard(),
                        inputCardNumber(currentUser),
                        inputCardCvv(),
                        inputCardExpireDate(),
                        inputCardType(),
                        inputIssuer(),
                        createAddress()));
        System.out.println(((Customer) currentUser).getCards().get(((Customer) currentUser).getCards().size() - 1));
        System.out.println("\n  Card Successfully Created...");
        pressAnyKeyToContinue();
    }

    public static void displayCard(Person currentUser){
        if(((Customer)currentUser).getCards().isEmpty()){
            System.out.println("  There are no cards in record...");
            pressAnyKeyToContinue();
            return;
        }
        Card card = selectCard(currentUser);
        System.out.println(card);
        pressAnyKeyToContinue();
    }

    public static void editCard(Person currentUser) {
        boolean exitFlag;
        if (((Customer) currentUser).getCards().isEmpty()) {
            System.out.println("  There are no cards in record...");
            pressAnyKeyToContinue();
            return;
        }
        Card card = selectCard(currentUser);
        // display card info
        System.out.println(card);
        do {
            exitFlag = false;
            System.out.println("  *** Edit Card Information ***\n");
            System.out.println("  1. Name On Card");
            System.out.println("  2. Card Number");
            System.out.println("  3. CVV Number");
            System.out.println("  4. Expired Date");
            System.out.println("  5. Type of Card");
            System.out.println("  6. Issuer");
            System.out.println("  7. Billing Address");
            System.out.println("  8. Back");

            switch (Main.promptInt("  Please enter a selection > ")) {
                case 1 -> card.setNameOnCard(inputNameOnCard());
                case 2 -> card.setNumber(inputCardNumber(currentUser));
                case 3 -> card.setCVV(inputCardCvv());
                case 4 -> card.setExpireDate(inputCardExpireDate());
                case 5 -> card.setType(inputCardType());
                case 6 -> card.setIssuer(inputIssuer());
                case 7 -> card.setBillingAddress(createAddress());
                case 8 -> {
                    return;
                }
                default -> {
                    System.out.println("  Invalid choice entered...");
                    exitFlag = true; //loop
                }
            }
        } while (exitFlag);
        System.out.println(card);
        System.out.println("\n  Changes made successfully");
        pressAnyKeyToContinue();
    }

    public static void removeCard(Person currentUser) {
        if (((Customer) currentUser).getCards().isEmpty()) {
            System.out.println("\n  There are no cards in record...");
            pressAnyKeyToContinue();
            return;
        }
        ((Customer) currentUser).getCards().remove(selectCard(currentUser));
        System.out.println("\n  Remove Successfully...");
        pressAnyKeyToContinue();
    }

    public static String convertCapitalize(String input) {
        char[] charArray = input.toCharArray();
        boolean foundSpace = true;
        for (int i = 0; i < charArray.length; i++) {
            // if the array element is a letter
            if (Character.isLetter(charArray[i])) {
                // check space is present before the letter
                if (foundSpace) {
                    // change the letter into uppercase
                    charArray[i] = Character.toUpperCase(charArray[i]);
                    foundSpace = false;
                }
            } else {
                // if the new character is not character
                foundSpace = true;
            }
        }
        return String.valueOf(charArray);
    }

    public static String inputCardNumber(Person currentUser) {
        String number;
        do {
            number = promptString("\n  Enter 16-digits credit card number > ");//TanShiJing
            if (number.length() == 16 && cardValidation(number)) {
                // check for unique
                if (checkCardUnique(currentUser, number)) {
                    return number;
                } else {
                    System.out.println("  Card number entered already exist in record...");
                    pressAnyKeyToContinue();
                    continue;
                }
            }
            System.out.println("  Invalid Card Number...");
        } while (true);
    }

    public static boolean checkCardUnique(Person currentUser, String cardNumber) {
        for (Card card : ((Customer) currentUser).getCards()) {
            // if same in record
            if (card.getNumber().equals(cardNumber)) {
                return false;
            }
        }
        return true;
    }

    public static String inputCardCvv() {
        String cvv;
        do {
            cvv = promptString("\n  Enter CVV Number > ");//TanShiJing
            if (cvvValidation(cvv)) {
                return cvv;
            }
            System.out.println("  Invalid CVV Number...");
        } while (true);
    }

    public static String inputCardType() {
        String type = null;
        boolean typeFlag; // wait user response to continue
        do {
            typeFlag = false;
            System.out.println("\n\n\t  Type of Card");//TanShiJing
            System.out.println("  -------------------------");
            System.out.println("  1. Debit Card");
            System.out.println("  2. Credit Card");
            switch (promptInt("  Please select type of card > ")) {
                case 1 -> type = "Debit";
                case 2 -> type = "Credit";
                default -> {
                    System.out.println("  Invalid Selection...");
                    typeFlag = true; // continue loop
                }
            }
        } while (typeFlag);
        return type;
    }

    public static LocalDate inputCardExpireDate() {
        LocalDate expireDate;
        do {
            System.out.println("\n\n    Expired Date of Card");
            System.out.println("  -------------------------");
            try {
                expireDate = LocalDate.of(promptInt("  Year  > "), promptInt("  Month > "), 1);//TanShiJing
                //if year entered is before now
                if (expireDate.isBefore(LocalDate.now()) || expireDate.isAfter(LocalDate.now().plusYears(10))) {
                    System.out.println("  Invalid expired date...");
                    continue;
                }
                break;
            } catch (DateTimeException e) {
                System.out.println("  Invalid expired date");
            }
        } while (true);
        return expireDate;
    }

    public static String inputNameOnCard() {
        String nameOnCard;
        boolean nameOnCardFlag = true;
        do {
            nameOnCard = convertCapitalize(promptString("\n  Enter Name On Card > "));//TanShiJing
            //name validation - set to false if correct
            if (nameValidation(nameOnCard)) {
                nameOnCardFlag = false;
            } else {
                System.out.println("  Invalid name on card...");
            }
        } while (nameOnCardFlag);
        return nameOnCard;
    }

    public static String inputIssuer() {
        String[] banks = {"Alliance Bank Malaysia Berhad", "Affin Bank Berhad", "AmBank (M) Berhad", "Bank Islam Malaysia Berhad", "Bank Simpanan Nasional Berhad", "Bank Rakyat", "CIMB Bank Berhad",
                "Citibank Berhad", "Hong Leong Bank Berhad", "HSBC Bank Malaysia Berhad", "Maybank Berhad", "OCBC Bank (Malaysia) Berhad", "Public Bank Berhad", "RHB Bank Berhad",
                "Standard Chartered Bank (Malaysia) Berhad", "United Overseas Bank (Malaysia) Berhad"};
        do {
            System.out.println("\n\n\t  Card Issuer");
            System.out.println("  -------------------------");
            for (int index = 0; index < banks.length; index++) {
                System.out.println("  "+(index + 1) + ". " + banks[index]);
            }
            int selection = promptInt("\n  Please select card issuer > ");
            if (selection > 0 && selection < 17) {
                return banks[selection - 1];
            }
            System.out.println("  Invalid Selection...");
        } while (true);
    }

    public static void createPromo(Promotion promo, ArrayList<Promotion> promotions) {
        boolean loopFlag;
        do {
            loopFlag = false;
            promo.setPromoCode(inputPromo(promotions));
            promo.setDescription(promptString("  Enter promotion description > "));
            //promo start date
            inputPromoStartDate(promo);
            //promo end date
            inputPromoEndDate(promo);
            promo.setPromoRate(inputRate());
            for (Promotion promotion : promotions) {
                // if match
                if (promotion.equals(promo)) {
                    if (!promptYesNo("\n\n  Confirm to add this promotion (detected repeated info entered) ? (Y/N) > ")) {
                        loopFlag = true;
                        break; // break out of for loop
                    }
                }
            }
        }
        while (loopFlag);
        promotions.add(promo); // add promo to record
        System.out.println("  Successfully created new promotion");
    }

    // check promo code uniqueness
    public static boolean checkPromoCode(String code, ArrayList<Promotion> promotions) {
        for (Promotion promo : promotions) {
            if (promo.getPromoCode().equals(code)) {
                return false;
            }
        }
        return true;
    }

    public static Bath promptBathService(Bath bath) {
        boolean exitFlag;
        do {
            exitFlag = true;
            System.out.println("\n\n\t\t  Bath Service Add-Ons");
            System.out.println("  -----------------------------------");
            System.out.println("  1. Scented Shampoo           (RM 10.00) > " + Main.booleanToSymbol(bath.isScentedShampoo()));
            System.out.println("  2. Low Shed Shampoo          (RM 10.00) > " + Main.booleanToSymbol(bath.isLowShedShampoo()));
            System.out.println("  3. Anti Tick & Flea Shampoo  (RM 10.00) > " + Main.booleanToSymbol(bath.isAntiTickFleaShampoo()));
            System.out.println("  4. Ear Cleaning              (RM 15.00) > " + Main.booleanToSymbol(bath.isEarCleaning()));
            System.out.println("  5. Anal Gland Cleaning       (RM 15.00) > " + Main.booleanToSymbol(bath.isAnalGlandCleaning()));
            System.out.println("  6. Hair Removal & Flushing   (RM 15.00) > " + Main.booleanToSymbol(bath.isHairRemover_Flushing()));
            System.out.println("  7. Breath Freshener          (RM 15.00) > " + Main.booleanToSymbol(bath.isBreathFreshener()));
            System.out.println("  8. Teeth Cleanse             (RM 15.00) > " + Main.booleanToSymbol(bath.isTeethCleanse()));
            System.out.println("  9. Continue");
            System.out.println("  ** Options are toggleable **");
            switch (Main.promptInt("\n  Choose any add-ons you would like to apply > ")) {
                case 1 -> {
                    bath.toggleScentedShampoo();
                    if (bath.isScentedShampoo()) {
                        bath.setLowShedShampoo(!bath.isScentedShampoo()); // set to opposite of scented shampoo
                        bath.setAntiTickFleaShampoo(!bath.isScentedShampoo());
                    }
                }
                case 2 -> {
                    bath.toggleLowShedShampoo();
                    if (bath.isLowShedShampoo()) {
                        bath.setScentedShampoo(!bath.isLowShedShampoo()); // set to opposite of low shed shampoo
                        bath.setAntiTickFleaShampoo(!bath.isLowShedShampoo());
                    }
                }
                case 3 -> {
                    bath.toggleAntiTickFleaShampoo();
                    if (bath.isAntiTickFleaShampoo()) {
                        bath.setScentedShampoo(!bath.isAntiTickFleaShampoo()); // set to opposite of anti tick flea shampoo
                        bath.setLowShedShampoo(!bath.isAntiTickFleaShampoo());
                    }
                }
                case 4 -> bath.toggleEarCleaning();
                case 5 -> bath.toggleAnalGlandCleaning();
                case 6 -> bath.toggleHairRemoval_Flushing();
                case 7 -> bath.toggleBreathFreshener();
                case 8 -> bath.toggleTeethCleanse();
                case 9 -> exitFlag = false;
                default -> System.out.println("  Invalid choice selected...");
            }
        }
        while (exitFlag);

        bath.calculateAddOnPrice(); // add add-on name & price to hashmap

        return bath;
    }

    public static Groom promptGroomService(Groom groom) {
        boolean exitFlag;
        do {
            exitFlag = true;
            System.out.println("\n\n\t\t  Groom Service Add-Ons");
            System.out.println("  -----------------------------------");
            System.out.println("  1. Fancy Cut        (RM10.00) > " + Main.booleanToSymbol(groom.isFancyCut()));
            System.out.println("  2. Sanitary Trim    (RM15.00) > " + Main.booleanToSymbol(groom.isSanitaryTrim()));
            System.out.println("  3. Nail Clip        (RM10.00) > " + Main.booleanToSymbol(groom.isNailClip()));
            System.out.println("  4. Nail Grind       (RM15.00) > " + Main.booleanToSymbol(groom.isNailGrind()));
            System.out.println("  5. Scissoring Feet  (RM10.00) > " + Main.booleanToSymbol(groom.isScissoringFeet()));
            System.out.println("  6. Pad Shaving      (RM15.00) > " + Main.booleanToSymbol(groom.isPadShaving()));
            System.out.println("  7. Coat Styling     (RM10.00) > " + Main.booleanToSymbol(groom.isCoatStyling()));
            System.out.println("  8. Continue");
            System.out.println("  ** Options are toggleable **");
            switch (Main.promptInt("\n  Choose any add-ons you would like to apply > ")) {
                case 1 -> groom.toggleFancyCut();
                case 2 -> groom.toggleSanitaryTrim();
                case 3 -> groom.toggleNailCLip();
                case 4 -> groom.toggleNailGrind();
                case 5 -> groom.toggleScissoringFeet();
                case 6 -> groom.togglePadShaving();
                case 7 -> groom.toggleCoatStyling();
                case 8 -> exitFlag = false;
                default -> System.out.println("  Invalid choice selected...");
            }
        }
        while (exitFlag);

        groom.calculateAddOnPrice(); // add add-on name & price to hashmap

        return groom;
    }

    public static Massage promptMassageService(Massage massage) {
        boolean exitFlag;
        do {
            exitFlag = true;
            System.out.println("\n\n\t   Massage Service Add-Ons");//TanShiJing
            System.out.println("  -----------------------------------");
            System.out.println("  1. After Massage Wash   (RM15.00) > " + Main.booleanToSymbol(massage.isAfterMassageWash()));
            System.out.println("  2. Premium Calming Oil  (RM20.00) > " + Main.booleanToSymbol(massage.isPremiumCalmingOil()));
            System.out.println("  3. Multiple Masseur     (RM30.00) > " + Main.booleanToSymbol(massage.isMultiMasseur()));
            System.out.println("  4. Continue");
            System.out.println("  ** Options are toggleable **");
            switch (Main.promptInt("\n  Choose any add-ons you would like to apply > ")) {
                case 1 -> massage.toggleAfterMassageWash();
                case 2 -> massage.togglePremiumCalmingOil();
                case 3 -> massage.toggleMultiMasseur();
                case 4 -> exitFlag = false;
                default -> System.out.println("  Invalid choice selected...");
            }
        }
        while (exitFlag);

        massage.calculateAddOnPrice(); // add add-on name & price to hashmap

        return massage;
    }

    public static Shelter promptShelterService(Shelter shelter) {
        // prompt check in date

        do {
            shelter.setCheckInDate(Main.inputStartDate("\n\n  Enter check-in date  : "));//TanShiJing
            shelter.setCheckOutDate(Main.inputEndDate("\n\n  Enter check-out date : ", shelter.getCheckInDate()));
            if (ChronoUnit.DAYS.between(shelter.getCheckInDate(), shelter.getCheckOutDate()) > 31) {
                System.out.println("  Maximum days of stay is 30 days only...");
                pressAnyKeyToContinue();
                continue;
            }
            else if (ChronoUnit.DAYS.between(shelter.getCheckInDate(), shelter.getCheckOutDate()) == 0) {
                System.out.println("\n  Invalid date entered... \nStart date and end date shouldn't be the same\n");
                pressAnyKeyToContinue();
                continue;
            }
            break;

        } while (true);

        // choose food portion (default is extra-small)
        shelter.setFoodPortion(Main.promptSize("Food Portion"));

        // calculate days of stay
        shelter.setTotalNumOfDays(Period.between(shelter.getCheckInDate(), shelter.getCheckOutDate()).getDays());

        boolean exitFlag;
        do {
            exitFlag = true;
            System.out.println("\n\n\t   Shelter Service Add-Ons");
            System.out.println("  -----------------------------------");
            System.out.println("  1. Vegetarian        (RM  0.00) > " + Main.booleanToSymbol(shelter.isVegetarian()));
            System.out.println("  2. Halal Food        (RM  0.00) > " + Main.booleanToSymbol(shelter.isHalalFood()));
            System.out.println("  3. Air Conditioning  (RM 50.00) > " + Main.booleanToSymbol(shelter.isAircond()));
            System.out.println("  4. Play Room         (RM 10.00) > " + Main.booleanToSymbol(shelter.isPlayRoom()));
            System.out.println("  5. One on One Care   (RM100.00) > " + Main.booleanToSymbol(shelter.isOneOnOneCare()));
            System.out.println("  6. Continue");
            System.out.println("  ** Options are toggleable **");
            switch (Main.promptInt("\n  Choose any add-ons you would like to apply > ")) {
                case 1 -> shelter.toggleVegetarian();
                case 2 -> shelter.toggleHalalFood();
                case 3 -> shelter.toggleAircond();
                case 4 -> shelter.togglePlayRoom();
                case 5 -> shelter.toggleOneOnOneCare();
                case 6 -> exitFlag = false;
                default -> System.out.println("  Invalid choice selected...");
            }
        }
        while (exitFlag);

        shelter.calculateAddOnPrice(); // add add-on name & price to hashmap

        return shelter;
    }

    public static String inputPromo(ArrayList<Promotion> promotions) {
        String promo;
        System.out.println("\n  +--------------------------------------------------------------------+");
        System.out.println("  |                *** VALID  PROMOTION  CODE  RULES ***               |");
        System.out.println("  |                                                                    |");
        System.out.println("  |      - Promotion Code must have at least 7 character               |");
        System.out.println("  |      - Promotion Code consist of only letters and digits           |");
        System.out.println("  |      - Promotion Code must contain at least one letter and at      |");
        System.out.println("  |        least one digit                                             |");
        System.out.println("  |      - Promotion Code must be unique                               |");
        System.out.println("  |                                                                    |");
        System.out.println("  +--------------------------------------------------------------------+\n");
        do {
            promo = promptString("  Enter promotion code > ");
            if (userpassValidation(promo) && checkPromoCode(promo, promotions)) {
                return promo;
            }
            System.out.println("  Invalid promotion code format...");
        }
        while (true);
    }

    public static double inputRate() {
        double promoRate;
        do {
            promoRate = Main.promptDouble("\n  Enter promotion rate (0.01 - 0.99) > ");
            if (promoRate < 1.00 && promoRate > 0.00) {
                return promoRate;
            }
            System.out.println("  Invalid Promotion Rate...");
        } while (true);
    }

    public static void pressAnyKeyToContinue() {
        System.out.println("  Press Enter key to continue...");
        try {
            System.in.read();
        } catch (Exception ignored) {
        }
    }

    public static Level promptLevel(String title) {
        do {
            System.out.println("\n\n    " + title);//TanShiJing
            System.out.println("  -----------------------------");//TanShiJing
            System.out.println("  1. Low");
            System.out.println("  2. Medium");
            System.out.println("  3. High");
            switch (promptInt("  Please enter a selection > ")) {
                case 1 -> {
                    return Level.LOW;
                }
                case 2 -> {
                    return Level.MEDIUM;
                }
                case 3 -> {
                    return Level.HIGH;
                }
                default -> System.out.println("  Invalid choice entered...");
            }
        }
        while (true);
    }

    public static Size promptSize(String title) {
        do {
            System.out.println("\n\n    " + title);
            System.out.println("  -------------------");
            System.out.println("  1. Extra Small");
            System.out.println("  2. Small");
            System.out.println("  3. Medium");
            System.out.println("  4. Large");
            System.out.println("  5. Extra Large");
            switch (promptInt("  Please enter a selection > ")) {
                case 1 -> {
                    return Size.XSMALL;
                }
                case 2 -> {
                    return Size.SMALL;
                }
                case 3 -> {
                    return Size.MEDIUM;
                }
                case 4 -> {
                    return Size.LARGE;
                }
                case 5 -> {
                    return Size.XLARGE;
                }
                default -> System.out.println("  Invalid choice entered...");
            }
        }
        while (true);
    }

    public static String displaySize(Size size) {
        switch (size) {
            case XSMALL -> {
                return "Extra Small";
            }
            case SMALL -> {
                return "Small";
            }
            case MEDIUM -> {
                return "Medium";
            }
            case LARGE -> {
                return "Large";
            }
            case XLARGE -> {
                return "Extra Large";
            }
        }
        return "Extra Small";
    }

    public static String displayLevel(Level level) {
        switch (level) {
            case LOW -> {
                return "Low";
            }
            case MEDIUM -> {
                return "Medium";
            }
            case HIGH -> {
                return "High";
            }
        }
        return "Low";
    }

    public static boolean promptYesNo(String promptMessage) {
        String input;
        do {
            try {

                input = promptString(promptMessage);
                if (input.length() != 1) {
                    System.out.println("\n  Invalid choice selected...\n");
                    continue;
                }
                // if input is yes
                if (input.charAt(0) == 'y' || input.charAt(0) == 'Y') {
                    return true;
                } else if (input.charAt(0) == 'n' || input.charAt(0) == 'N') {
                    return false;
                } else {
                    System.out.println("\n  Invalid choice selected...\n");
                }
            } catch (StringIndexOutOfBoundsException e) {
                System.out.println("  Please enter a selection...");
            }
        }
        while (true);
    }

    public static int inputPetAge(String promptMessage) {
        int age;
        do {
            age = promptInt(promptMessage);
            if (petAgeValidation(age)) {
                return age;
            }
            System.out.println("  Invalid pet age...");
        }
        while (true);
    }

    public static LocalDate inputStartDate(String text) {
        LocalDate startDate;
        do {
            try {
                System.out.println(text);
                startDate = LocalDate.of(Main.promptInt("  Year  > "), Main.promptInt("  Month > "), Main.promptInt("  Day   > "));//TanShiJing
                if (startDate.isBefore(LocalDate.now())) {
                    System.out.println("  Date entered must be in the future...");
                    continue;
                }
                return startDate;
            } catch (DateTimeException e) {
                System.out.println("Invalid date entered...");
            }
        }
        while (true);
    }

    public static LocalDate inputEndDate(String text, LocalDate startDate) {
        LocalDate endDate;
        do {
            try {
                System.out.println(text);
                endDate = LocalDate.of(Main.promptInt("  Year  > "), Main.promptInt("  Month > "), Main.promptInt("  Day   > "));//TanShiJing
                if (endDate.isBefore(startDate)) {
                    System.out.println("  End date should not earlier than start date...");
                    continue;
                }
                return endDate;
            } catch (DateTimeException e) {
                System.out.println("  Invalid date entered...");
            }
        }
        while (true);
    }

    // format date to String
    public static String dateToString(LocalDateTime date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return date.format(formatter);
    }


    public static String dateToString(LocalDate date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return date.format(formatter);
    }


    // format date time to String
    public static String datetimeToString(LocalDateTime date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        return date.format(formatter);
    }

    // format time to String
    public static String timeToString(LocalDateTime date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        return date.format(formatter);
    }

    public static boolean cvvValidation(String cvv) {
        return cvv.matches("[0-9]+") && cvv.length() == 3;
    }

    public static boolean cardValidation(String cardNumber) {
        String cardRegex = "^(?:4[0-9]{12}(?:[0-9]{3})?|[25][1-7][0-9]{14}|6(?:011|5[0-9][0-9])[0-9]{12}|3[47][0-9]{13}|3(?:0[0-5]|[68][0-9])[0-9]{11}|(?:2131|1800|35\\d{3})\\d{11})$";

        Pattern pattern = Pattern.compile(cardRegex);

        Matcher match = pattern.matcher(cardNumber);

        return match.matches();
    }

    // Validate pet age 1 - 20
    public static boolean petAgeValidation(int age) {
        return age > 0 && age < 21;
    }

    // Validate username and password
    public static boolean userpassValidation(String userpass) {
        boolean letter = false, digit = false;
        if (userpass.length() >= 7) {
            for (int index = 0; index < userpass.length(); index++) {
                if (Character.isLetter(userpass.charAt(index))) {
                    letter = true;
                } else if (Character.isDigit(userpass.charAt(index))) {
                    digit = true;
                }
                // not letter or digit
                else return false;
            }
            // after iteration
            return letter && digit;
        }
        // not within length
        return false;
    }

    public static boolean emailValidation(String email) {
        String emailRegex = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";

        Pattern pattern = Pattern.compile(emailRegex);

        Matcher match = pattern.matcher(email);

        return match.matches();
    }

    public static boolean telValidation(String tel) {
        String telRegex = "^(\\+?6?01)[02-46-9]-*[0-9]{7}$|^(\\+?6?01)[1]-*[0-9]{8}$";

        Pattern pattern = Pattern.compile(telRegex);

        Matcher match = pattern.matcher(tel);

        return match.matches();
    }

    public static boolean nameValidation(String name) {
        // Check for only alphabet in names
        String nameRegex = "[a-zA-Z][a-zA-Z ]+";

        Pattern pattern = Pattern.compile(nameRegex, Pattern.CASE_INSENSITIVE);

        Matcher match = pattern.matcher(name);

        return match.matches();
    }

    public static String inputFirstname() {
        String firstname;
        boolean firstnameFlag = true;
        do {
            firstname = convertCapitalize(promptString("\n  Enter first name    > "));//TanShiJing
            // first name validation - set to false if correct
            if (nameValidation(firstname)) {
                firstnameFlag = false;
            } else {
                System.out.println("  Invalid first name...");
            }
        }
        while (firstnameFlag);
        return firstname;
    }

    public static String inputLastname() {
        String lastname;
        boolean lastnameFlag = true;
        do {
            lastname = convertCapitalize(promptString("\n  Enter last name     > "));//TanShiJing
            // last name validation
            if (nameValidation(lastname)) {
                lastnameFlag = false;
            } else {
                System.out.println("  Invalid last name...");
            }
        }
        while (lastnameFlag);
        return lastname;
    }

    public static char inputGender() {
        char gender = Character.MIN_VALUE;
        boolean genderFlag;// wait user response to continue
        do {
            genderFlag = false;
            System.out.println("\n\n\t  Gender selection");
            System.out.println("  -------------------------");
            System.out.println("  1. Male");
            System.out.println("  2. Female");
            switch (promptInt("  Please select a gender > ")) {
                case 1 -> gender = 'M';
                case 2 -> gender = 'F';
                default -> {
                    System.out.println("  Invalid Selection...");
                    genderFlag = true; // continue loop
                }
            }
        }
        while (genderFlag);
        return gender;
    }

    public static LocalDate inputDOB() {
        LocalDate dob;
        do {
            try {
                System.out.println("\n\n  Enter date of birth : ");
                dob = LocalDate.of(promptInt("  Year  > "), promptInt("  Month > "), promptInt("  Day   > "));//TanShiJing
                // if date entered is in the future or is before year 1905
                if (dob.isAfter(LocalDate.now().minusYears(16)) || dob.isBefore(LocalDate.of(1905, 1, 1))) {
                    System.out.println("  Invalid date entered...");
                    continue;
                }
                break;
            } catch (DateTimeException e) {
                System.out.println("  Invalid date entered...");
            }
        }
        while (true);
        return dob;
    }

    public static String inputTel() {
        String tel;
        boolean telFlag = true;
        do {
            tel = promptString("\n  Enter phone number  > ");//TanShiJing
            // Tel validation
            if (telValidation(tel)) {
                telFlag = false;
            } else {
                System.out.println("  Invalid phone number...");
            }
        }
        while (telFlag);
        return tel;
    }

    public static String inputEmail() {
        String email;
        boolean emailFlag = true;
        do {
            email = promptString("\n  Enter email address > ");
            // Email validation
            if (emailValidation(email)) {
                emailFlag = false;
            } else {
                System.out.println("  Invalid email address...");
            }
        }
        while (emailFlag);
        return email;
    }

    public static void displayUsernameRules() {
        System.out.println("\n  +--------------------------------------------------------------------+");
        System.out.println("  |                   *** VALID  USERNAME  RULES ***                   |");
        System.out.println("  |                                                                    |");
        System.out.println("  |         - Username must have at least 7 character                  |");
        System.out.println("  |         - Username consist of only letters and digits              |");
        System.out.println("  |         - Username must contain at least one letter and at         |");
        System.out.println("  |           least one digit                                          |");
        System.out.println("  |         - Username must be unique                                  |");
        System.out.println("  |                                                                    |");
        System.out.println("  +--------------------------------------------------------------------+\n");
    }

    public static String inputCustomerUsername(ArrayList<Customer> customerList) {
        String username;
        boolean usernameFlag;
        do {
            usernameFlag = false;
            displayUsernameRules();
            username = promptString("  Enter your desired username > ");
            // If username is in correct format
            if (userpassValidation(username)) {
                try {
                    for (Customer obj : customerList) {
                        // If input username exist in array
                        if (obj.username.equals(username)) {
                            System.out.println("  Username already exists...");
                            usernameFlag = true;
                            break;
                        }
                    }
                } catch (NullPointerException ignored) {
                } // ignore checking if arraylist is empty
            }
            // If not in correct format
            else {
                System.out.println("  Username format incorrect...");
                usernameFlag = true;
            }
        }
        while (usernameFlag);
        return username;
    }

    public static String inputEmployeeUsername(ArrayList<Employee> employeeList) {
        String username;
        boolean usernameFlag;
        do {
            usernameFlag = false;
            displayUsernameRules();
            username = promptString("  Enter your desired username > ");
            // If username is in correct format
            if (userpassValidation(username)) {
                try {
                    for (Employee obj : employeeList) {
                        // If input username exist in array
                        if (obj.username.equals(username)) {
                            System.out.println("  Username already exists...");
                            usernameFlag = true;
                            break;
                        }
                    }
                } catch (NullPointerException ignored) {
                } // ignore checking if arraylist is empty
            }
            // If not in correct format
            else {
                System.out.println("  Username format incorrect...");
                usernameFlag = true;
            }
        }
        while (usernameFlag);
        return username;
    }

    public static String inputOwnerUsername(ArrayList<Owner> ownerList) {
        String username;
        boolean usernameFlag;
        do {
            usernameFlag = false;
            displayUsernameRules();
            username = promptString("  Enter your desired username > ");
            // If username is in correct format
            if (userpassValidation(username)) {
                try {
                    for (Owner obj : ownerList) {
                        // If input username exist in array
                        if (obj.username.equals(username)) {
                            System.out.println("  Username already exists...");
                            usernameFlag = true;
                            break;
                        }
                    }
                } catch (NullPointerException ignored) {
                } // ignore checking if arraylist is empty
            }
            // If not in correct format
            else {
                System.out.println("  Username format incorrect...");
                usernameFlag = true;
            }
        }
        while (usernameFlag);
        return username;
    }

    public static String inputPassword() {
        String password, confirmPassword;
        boolean passwordFlag = true;
        do {
            System.out.println("\n  +--------------------------------------------------------------------+");
            System.out.println("  |                   *** VALID  PASSWORD  RULES ***                   |");
            System.out.println("  |                                                                    |");
            System.out.println("  |         - Password must have at least 7 character                  |");
            System.out.println("  |         - Password consist of only letters and digits              |");
            System.out.println("  |         - Password must contain at least one letter and at         |");
            System.out.println("  |           least one digit                                          |");
            System.out.println("  |                                                                    |");
            System.out.println("  +--------------------------------------------------------------------+\n");
            password = promptString("  Enter your desired password   > ");//TanShiJing
            confirmPassword = promptString("  Confirm your desired password > ");

            // If input password not equals to input confirm password
            if (!(password.equals(confirmPassword))) {
                System.out.println("  Both password does not match...");
                continue;
            }

            // If password is in correct format
            if (userpassValidation(password)) {
                passwordFlag = false;
            }
            // If not in correct format
            else {
                System.out.println("  Password format incorrect...");
            }
        }
        while (passwordFlag);
        return password;
    }

    public static void displayEmployee(ArrayList<Employee> employeeList) {
        System.out.println("""
                \n  +-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+
                  | Emp.ID |      Name       |  Age  | Gender | Contact Number | Birth Date |   Salary   |             Email            |    Username    | Register Date |                           Address                          |
                  +--------+-----------------+-------+--------+----------------+------------+------------+------------------------------+----------------+---------------+------------------------------------------------------------+""");
        for (Employee employee : employeeList) {
            System.out.println("  "+employee.displayRow()); // display employees
            System.out.println("  +-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+");
        }
    }

    public static boolean checkEmployeeRecord(ArrayList<Employee> employeeList) {
        return !employeeList.isEmpty();
    }

    public static boolean checkPromotionRecord(ArrayList<Promotion> promotions) {
        return !promotions.isEmpty();
    }

    public static boolean checkCustomerRecord(ArrayList<Customer> customerList) {
        return !customerList.isEmpty();
    }

    public static Employee inputEmployee(ArrayList<Employee> employeeList) {
        Employee selected;
        // choose which employee to book with
        for (int i = 0; i < employeeList.size(); i++) {
            System.out.println("  "+(i + 1) + ". " +
                    employeeList.get(i).getID() +
                    " " +
                    employeeList.get(i).getFirstName() +
                    " " +
                    employeeList.get(i).getLastName()); // display employees
        }
        // prompt user to choose employee
        do {
            try {
                selected = employeeList.get(Main.promptInt("  Choose an employee > ") - 1);
                return selected;
            } catch (IndexOutOfBoundsException e) {
                System.out.println("  Invalid choice selected...");
            }
        }
        while (true);
    }

    public static void displayCustReservation(Customer customer) {
        if (customer.getReservation().isEmpty()) {
            System.out.println("  No reservation records found...");
            pressAnyKeyToContinue();
            return;
        }
        System.out.println("\n\n\t  Reservation Records");
        System.out.println("  -----------------------------");

        for (int index = 0; index < customer.getReservation().size(); index++) {
            System.out.printf("  %d.\t%-8s  %s\n",index+1,customer.getReservation().get(index).getServices().getClass().getSimpleName(),
                    datetimeToString(customer.getReservation().get(index).getReserveDateTime()));
        }
        do {
            try {
                System.out.println(customer.getReservation().get(Main.promptInt("  Choose a reservation > ")-1));
                pressAnyKeyToContinue();
                break;
            }
            catch (IndexOutOfBoundsException e) {
                System.out.println("  Invalid choice selected...");
            }
        }
        while (true);
    }

    public static Customer inputCustomer(ArrayList<Customer> customerList) {
        Customer selected;
        // choose which employee to book with
        for (int i = 0; i < customerList.size(); i++) {
            System.out.println("  "+(i + 1) + ". " +
                    customerList.get(i).getFirstName() +
                    " " +
                    customerList.get(i).getLastName()); // display customers
        }
        // prompt user to choose employee
        do {
            try {
                selected = customerList.get(Main.promptInt("  Choose a customer > ") - 1);
                return selected;
            } catch (IndexOutOfBoundsException e) {
                System.out.println("  Invalid choice selected...");
            }
        }
        while (true);
    }

    public static void logout(Person currentUser) {
        currentUser = null;
    }

    public static String promptString(String promptMessage) {
        Scanner input = new Scanner(System.in);
        while (true) {
            System.out.print(promptMessage);
            String string = input.nextLine();
            if (string.isBlank()) {
                System.out.println("  No input detected...");
                continue;
            }
            return string.trim();
        }
    }

    public static int promptInt(String promptMessage) {
        Scanner input = new Scanner(System.in);
        while (true) {
            try {
                System.out.print(promptMessage);
                int value = input.nextInt();
                input.nextLine();
                return value;
            } catch (InputMismatchException e) {
                System.out.println("  Invalid value entered...");
                input.nextLine();
            }
        }
    }

    public static double promptDouble(String promptMessage) {
        Scanner input = new Scanner(System.in);
        while (true) {
            try {
                System.out.print(promptMessage);
                double value = input.nextDouble();
                input.nextLine();
                return value;
            } catch (InputMismatchException e) {
                System.out.println("  Invalid value entered...");
                input.nextLine();
            }
        }
    }

    public static char promptChar(String promptMessage) {
        Scanner input = new Scanner(System.in);
        System.out.print(promptMessage);
        return input.nextLine().charAt(0);
    }

    public static void inputPromoStartDate(Promotion promo) {
        do {
            try {
                System.out.println("\n  Enter promotion start date : ");
                promo.setPromoStartDate(LocalDate.of(Main.promptInt("  Year  > "), Main.promptInt("  Month > "), Main.promptInt("  Day   > ")));//TanShiJing
                if (promo.getPromoStartDate().isBefore(LocalDate.now())) {
                    System.out.println("  Date entered must be in the future...");
                    continue;
                } else if (promo.getPromoStartDate().isAfter(LocalDate.now().plusYears(2))) {
                    System.out.println("  Date entered is invalid...");
                    continue;
                }
                break;
            } catch (DateTimeException e) {
                System.out.println("  Invalid date entered...");
            }
        }
        while (true);
    }

    public static void inputPromoEndDate(Promotion promo) {
        do {
            try {
                System.out.println("\n  Enter promotion end date : ");
                promo.setPromoEndDate(LocalDate.of(Main.promptInt("  Year  > "), Main.promptInt("  Month > "), Main.promptInt("  Day   > ")));//TanShiJing
                if (promo.getPromoEndDate().isBefore(promo.getPromoStartDate())) {
                    System.out.println("  End date should not earlier than start date...");
                    continue;
                }
                // if the difference of start date and end date is more than 2 years
                else if ((promo.getPromoEndDate().getYear() - promo.getPromoStartDate().getYear()) > 2) {
                    System.out.println("  Date entered is invalid...");
                    continue;
                }
                break;
            } catch (DateTimeException e) {
                System.out.println("  Invalid date entered...");
            }
        }
        while (true);
    }

    // enter money value as double to return formatted RM value as string
    public static String convertCurrency(double amount) {
        return NumberFormat.getCurrencyInstance(new Locale("ms", "MY")).format(amount);
    }

    public static char booleanToSymbol(boolean bool) {
        return bool ? 'O' : 'X';
    }

    public static void checkOut(Person currentUser, ArrayList<Promotion> promotions, ArrayList<Employee> employeeList) {
        boolean promoYesNo;
        boolean reenterPromoCode;
        String promoCodeEntered;
        ArrayList<Double> amt;
        double totalAmt = 0;
        double totalAmtAfterPromo = 0;
        double promoRate = 0;
        System.out.println("\n\n  *** Check Out ***");//TanShiJing
        //System.out.println("------------------------------");TanShiJing
        if (((Customer) currentUser).getBill() != null) {
            Billing cb = ((Customer) currentUser).getBill();
            /**/
            amt = cb.getEachSubtotal();
            totalAmt = cb.calcTotalAmount();
            cb.setTotalAmount(totalAmt);
            dispBillSummary(cb, amt, totalAmt, LocalDate.now());

            // get promo code and its origin
            promoYesNo = promptYesNo("\n  Do you have any promo code to apply (Y/N) > ");
            if (promoYesNo) {
                do {
                    reenterPromoCode = false;
                    promoCodeEntered = Main.promptString("\n  Promo code > ");
                    if (!searchPromo(promoCodeEntered, promotions)) {
                        System.out.println("  No such promo code!");
                        reenterPromoCode = promptYesNo("  Would you like to re-enter promo code? (Y/N) > ");
                        if (!reenterPromoCode) {
                            promoYesNo = false;
                        }
                    } else if (!validatePromoExpiry(promoCodeEntered, promotions)) {
                        System.out.println("  Promo code not in valid period!");
                        reenterPromoCode = promptYesNo("  Would you like to re-enter promo code? (Y/N) > ");
                        if (!reenterPromoCode) {
                            promoYesNo = false;
                        }
                    }
                } while (reenterPromoCode);

                if (promoYesNo) {
                    System.out.println("\n  Please share the source of promo code with us");//TanShiJing
                    System.out.println("  1. Youtube\n  2. Instagram\n  3. Facebook\n  4. Twitter\n  5. Friends");
                    switch (Main.promptInt("  Source > ")) {
                        case 1 -> cb.setPromoOrigin("Youtube");
                        case 2 -> cb.setPromoOrigin("Instagram");
                        case 3 -> cb.setPromoOrigin("Facebook");
                        case 4 -> cb.setPromoOrigin("Twitter");
                        case 5 -> cb.setPromoOrigin("Friends");
                    }
                    // add promotion to customer billing
                    ((Customer) currentUser).getBill().setPromoApplied(searchAssignPromo(promoCodeEntered, promotions));
                    promoRate = ((Customer) currentUser).getBill().getPromoApplied().getPromoRate();
                    totalAmtAfterPromo = totalAmt * (1 - promoRate);
                }
            }
            dispGrandTotal(cb, promoYesNo, totalAmt, totalAmtAfterPromo, promoRate);

            // get payment method
            boolean paymentFlag = false;
            do {
                switch (selectPaymentMethod()) {
                    case 1 -> {
                        ((Customer) currentUser).getBill().setPaymentMethod("Cash");
                        paymentFlag = payByCash(amt, totalAmt, promoYesNo, totalAmtAfterPromo, promoRate, currentUser, employeeList);
                    }
                    case 2 -> {
                        ((Customer) currentUser).getBill().setPaymentMethod("Card");
                        paymentFlag = payByCard(amt, totalAmt, promoYesNo, totalAmtAfterPromo, promoRate, currentUser);
                    }
                    case 3 -> {
                        paymentFlag = true;
                        System.out.println("  Payment not processed");
                    }
                    default -> System.out.println("  Invalid choice. Please re-enter.\n");
                }
            } while (!paymentFlag);
        } else {
            System.out.println("  There are no pending bills.");
        }
    }

    public static void dispGrandTotal(Billing cb, boolean promoYesNo, double totalAmt, double totalAmtAfterPromo, double promoRate) {
        if (promoYesNo) {
            System.out.println("\n  Total amount             : " + Main.convertCurrency(totalAmt));
            System.out.println("  Promotion Code           : " + String.format("%s", cb.getPromoApplied().getPromoCode()));
            System.out.println("  Promotion Rate           : " + String.format("%d%%", (int) (promoRate * 100)));
            System.out.println("  Total Amount after promo : " + Main.convertCurrency(totalAmtAfterPromo));
            System.out.println("  SST Tax (10%)            : " + Main.convertCurrency(totalAmtAfterPromo * 0.1));
            System.out.println("  GRAND TOTAL              : " + Main.convertCurrency(totalAmtAfterPromo * 1.1));
            cb.setGrandTotal(totalAmtAfterPromo * 1.10);
        } else {
            System.out.println("\n  Total amount             : " + Main.convertCurrency(totalAmt));
            System.out.println("  SST Tax (10%)            : " + Main.convertCurrency(totalAmt * 0.1));
            System.out.println("  GRAND TOTAL              : " + Main.convertCurrency(totalAmt * 1.1));
            cb.setGrandTotal(totalAmt * 1.10);
        }
    }

    public static void dispBillSummary(Billing cb, ArrayList<Double> amt, double totalAmt, LocalDate dateTime) {
        System.out.println("\n");//TanShiJing
        System.out.println("  ====================================================================================================================");
        System.out.println("  |\t\t                             Transaction ID: " + cb.getTransactionID() + "       Date: " + Main.dateToString(dateTime) + "                                   |");//TanShiJing
        System.out.println("  ====================================================================================================================");
        System.out.println("  |    Resv. ID   |    Pet ID     |     Date      |  Charges(RM)  |  Service Details                                 |");
        System.out.println("  ====================================================================================================================");
        for (int i = 0; i < cb.getBillDetails().size(); i++) {
            if (i > 0) {
                System.out.println("  |------------------------------------------------------------------------------------------------------------------|");
            }
            System.out.print("  |    " + cb.getBillDetails().get(i).getReserveID() +
                    "     " +
                    "|    " + cb.getBillDetails().get(i).getPet().getID() +
                    "    " +
                    "|   " + Main.dateToString(cb.getBillDetails().get(i).getReserveDateTime()) +
                    "  " +
                    String.format("| RM%9.2f ", amt.get(i))
            );
            dispServiceDetails(cb.getBillDetails().get(i).getServices());
        }
        System.out.println("  ====================================================================================================================");
        System.out.println("  |  Total Amount                                 :  RM" + String.format("%9.2f", totalAmt) + "                                                     |");
        System.out.println("  ====================================================================================================================");

    }

    public static void dispServiceDetails(Service s) {
        if (s instanceof Bath) {
            System.out.println("  |  Bath Basic               RM  40.00              |");
            for (String addOnz : s.getAddOnPrice().keySet()) {
                System.out.printf("  |               |               |               |               |  %-25sRM%7.2f              |\n", addOnz, s.getAddOnPrice().get(addOnz));
            }
        } else if (s instanceof Groom) {
            System.out.println("  |  Groom Basic              RM  80.00              |");
            for (String addOnz : s.getAddOnPrice().keySet()) {
                System.out.printf("  |               |               |               |               |  %-25sRM%7.2f              |\n", addOnz, s.getAddOnPrice().get(addOnz));
            }
        } else if (s instanceof Massage) {
            System.out.println("  |  Massage Basic            RM  50.00              |");
            for (String addOnz : s.getAddOnPrice().keySet()) {
                System.out.printf("  |               |               |               |               |  %-25sRM%7.2f              |\n", addOnz, s.getAddOnPrice().get(addOnz));
            }
        } else {
            System.out.println("  |  Check-In Date          : " + Main.dateToString(((Shelter) s).getCheckInDate()) + "             |");
            System.out.println("  |               |               |               |               |  Check-Out Date         : " + Main.dateToString(((Shelter) s).getCheckOutDate()) + "             |");
            System.out.println("  |               |               |               |               |  Days of stay           : " + String.format("%2d", ((Shelter) s).getTotalNumOfDays()) + "                     |");
            System.out.println("  |               |               |               |               |  Shelter Per Night      : RM 120.00              |");
            System.out.println("  |               |               |               |               |                                                  |");
            System.out.println("  |               |               |               |               |  Total Basic Price      : RM" + String.format("%7.2f", ((Shelter) s).getTotalPerNightPrice()) + "              |");
            String food = Main.displaySize(((Shelter) s).getFoodPortion());
            String foodPo;
            switch (food) {
                case "Extra Small" -> foodPo = "XS";
                case "Small" -> foodPo = "S";
                case "Medium" -> foodPo = "M";
                case "Large" -> foodPo = "L";
                default -> foodPo = "XL";
            }
            System.out.print("  |               |               |               |               |  Food portion (" + String.format("%2s", foodPo) + ")      ");
            switch (food) {
                case "Extra Small" -> System.out.println("  RM  15.00              |");
                case "Small" -> System.out.println("  RM  20.00              |");
                case "Medium" -> System.out.println("  RM  25.00              |");
                case "Large" -> System.out.println("  RM  30.00              |");
                default -> System.out.println(  "  RM  35.00              |");
            }
            if (((Shelter) s).isVegetarian()) {
                System.out.println("  |               |               |               |               |  Vegetarian               RM   0.00              |");
            }
            if (((Shelter) s).isHalalFood()) {
                System.out.println("  |               |               |               |               |  Halal Food               RM   0.00              |");
            }
            if (((Shelter) s).isAircond()) {
                System.out.println("  |               |               |               |               |  Air Conditioning         RM  50.00              |");
            }
            if (((Shelter) s).isPlayRoom()) {
                System.out.println("  |               |               |               |               |  Play Room                RM  10.00              |");
            }
            if (((Shelter) s).isOneOnOneCare()) {
                System.out.println("  |               |               |               |               |  One on One Care          RM 100.00              |");
            }
        }
    }

    public static boolean payByCash(ArrayList<Double> amt, double totalAmt, boolean promoYesNo, double totalAmtAfterPromo, double promoRate, Person currentUser, ArrayList<Employee> employeeList) {
        Billing cb = ((Customer) currentUser).getBill();
        dispBillSummary(cb, amt, totalAmt, LocalDate.now());
        dispGrandTotal(cb, promoYesNo, totalAmt, totalAmtAfterPromo, promoRate);

        do {
            System.out.println("\n  Employee confirmation\n  ---------------------");
            String username = Main.promptString("  Username (0 to cancel payment): ");
            String password = Main.promptString("  Password (0 to cancel payment): ");
            if (username.equals("0")) {
                System.out.println("  Payment not processed");
                return true;
            }
            // Check for username and password exist
            for (Employee obj : employeeList) {
                // If correct username and password
                if (obj.username.equals(username) && obj.password.equals(password)) {
                    // set paymentDate in Billing object, move bill to bill history
                    ((Customer) currentUser).getBill().setPaymentDate(LocalDate.now());
                    ((Customer) currentUser).getBillHistory().add(((Customer) currentUser).getBill());
                    ((Customer) currentUser).setBill(null);
                    System.out.println("  Successful Payment");
                    Main.pressAnyKeyToContinue();
                    return true;
                }
            }
            if (!Main.promptYesNo("  Invalid employee credentials!\n  Re-enter? (Y/N) > ")) {
                return false;
            }

        } while (true);
    }

    public static boolean payByCard(ArrayList<Double> amt, double totalAmt, boolean promoYesNo, double totalAmtAfterPromo, double promoRate, Person currentUser) {
        if (((Customer) currentUser).getCards().size() != 0) {
            selectCard(currentUser);
            Billing cb = ((Customer) currentUser).getBill();
            dispBillSummary(cb, amt, totalAmt, LocalDate.now());
            dispGrandTotal(cb, promoYesNo, totalAmt, totalAmtAfterPromo, promoRate);
            if (!Main.promptYesNo("\n  Final confirmation\n  ------------------\n  Confirm to pay? > ")) {
                System.out.println("  Payment not processed");
                return true;
            }
            System.out.print("  Processing your payment ");
            wait(1000);
            System.out.print(".  ");
            wait(1000);
            System.out.print(".  ");
            wait(1000);
            System.out.println(".  ");
            wait(1000);


            for (Reservation reserve : cb.getBillDetails()) {
                reserve.setPaymentStatus(true);
            }
            // set paymentDate in Billing object, move bill to bill history
            ((Customer) currentUser).getBill().setPaymentDate(LocalDate.now());
            ((Customer) currentUser).getBillHistory().add(((Customer) currentUser).getBill());
            ((Customer) currentUser).setBill(null);
            System.out.println("  Successful Payment");
            Main.pressAnyKeyToContinue();
            return true;
        }
        System.out.println("  There are no cards associated with this account. Please choose other payment method.");
        return false;
    }

    public static void wait(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
    }

    // select card for payment
    public static Card selectCard(Person currentUser) {
        System.out.println("\n\n\t\t  Cards in record");
        System.out.println("  ------------------------------");
        System.out.println("  No.\t\tCard Number\t\t\tType\t\t\tIssuer");
        for (int i = 0; i < ((Customer)currentUser).getCards().size(); i++) {
            System.out.println("  "+(i + 1) + ". " +
                    "\t   " +
                    ((Customer) currentUser).getCards().get(i).getNumber() +
                    "\t\t" +
                    ((Customer) currentUser).getCards().get(i).getType() +
                    "\t\t" +
                    ((Customer) currentUser).getCards().get(i).getIssuer()
            );
        }
        do {
            try {
                return ((Customer) currentUser).getCards().get(Main.promptInt("  Choose a card > ") - 1);
            } catch (IndexOutOfBoundsException e) {
                System.out.println("  Invalid choice selected...");
            }
        }
        while (true);
    }

    // choose payment method
    public static int selectPaymentMethod() {
        do {
            try {
                System.out.println("\n  Payment Method\n  --------------");
                System.out.println("  1. Cash\n  2. Card\n  3. Cancel");
                return Main.promptInt("  Choice > ");
            } catch (IndexOutOfBoundsException e) {
                System.out.println("  Invalid choice selected...");
            }
        } while (true);
    }

    // search and assign the promo object to billing
    public static Promotion searchAssignPromo(String promoCodeEntered, ArrayList<Promotion> promotions) {
        for (Promotion promotion : promotions) {
            if (promotion.getPromoCode().equals(promoCodeEntered)) {
                return promotion;
            }
        }
        return null;
    }

    // search whether promo obj of the entered promo code exists
    public static boolean searchPromo(String promoCodeEntered, ArrayList<Promotion> promotions) {
        String p;
        for (Promotion promotion : promotions) {
            p = promotion.getPromoCode();
            if (p.equals(promoCodeEntered)) {
                return true;
            }
        }
        return false;
    }

    public static boolean validatePromoExpiry(String promoCodeEntered, ArrayList<Promotion> promotions) {
        Promotion p;
        for (Promotion promotion : promotions) {
            p = promotion;
            if (p.getPromoCode().equals(promoCodeEntered)) {
                return !LocalDate.now().isBefore(p.getPromoStartDate()) && !LocalDate.now().isAfter(p.getPromoEndDate());
            }
        }
        return false;
    }

    // view billHistory
    public static void displayBillingHistory(Person currentUser) {
        ArrayList<Double> amt = new ArrayList<>();
        double totalAmt;
        int choice;
        boolean anymore;
        Customer c = ((Customer) currentUser);
        do {
            amt.clear();
            System.out.println("\n\n  *** Bill History ***");//TanShiJing
            //System.out.println("------------------------------");TanShiJing
            if (c.getBillHistory().size() == 0) {
                System.out.println("  No billing history yet.");
                return;
            }
            System.out.println("\n  -----------------------------------------------------------");
            System.out.println("  | Index |  Transaction ID  |  Date Paid   |  Grand Total  |");
            System.out.println("  -----------------------------------------------------------");
            for (int i = 0; i < c.getBillHistory().size(); i++) {
                if (i > 0) {
                    System.out.println("  |---------------------------------------------------------|");
                }
                System.out.println(String.format("  |  %3d  |", i + 1) +
                        String.format("  %-6s          |", c.getBillHistory().get(i).getTransactionID()) +
                        String.format("  %s  |", Main.dateToString(c.getBillHistory().get(i).getPaymentDate())) +
                        String.format("  RM%9.2f  |", c.getBillHistory().get(i).getGrandTotal())
                );
            }
            System.out.println("  -----------------------------------------------------------");

            do {
                choice = (Main.promptInt("  Choose a record to view in detail (0 - Back) > ") - 1);
                if (choice >= 0 && choice < c.getBillHistory().size()) {
                    break;
                } else if (choice == -1) {
                    return;
                } else {
                    System.out.println("  Invalid choice! Please re-enter.");
                }
            } while (true);

            Billing cb = c.getBillHistory().get(choice);
            amt = cb.getEachSubtotal();
            totalAmt = cb.calcTotalAmount();
            dispBillSummary(cb, amt, totalAmt, cb.getPaymentDate());

            boolean p = (!Objects.isNull(cb.getPromoApplied()));
            if (p) {
                dispGrandTotal(cb, p, totalAmt, (cb.getTotalAmount() * (1 - cb.getPromoApplied().getPromoRate())), cb.getPromoApplied().getPromoRate());
            } else {
                dispGrandTotal(cb, p, totalAmt, cb.getTotalAmount(), 0);
            }

            //display payment method
            System.out.println("  Payment method           : " + cb.getPaymentMethod());

            anymore = Main.promptYesNo("\n  Continue view other bill history records? (Y/N) > ");

        } while (anymore);
    }

    // displayBilling
    public static void displayBilling(Person currentUser) {
        ArrayList<Double> amt;
        double totalAmt;
        Billing c = ((Customer) currentUser).getBill();
        System.out.println("\n\n  *** Bill Records ***");//TanShiJing
        //System.out.println("------------------------------");TanShiJing
        if (((Customer) currentUser).getBill() == null) {
            System.out.println("  There are no pending bills.");
            return;
        }
        amt = c.getEachSubtotal();
        totalAmt = c.calcTotalAmount();
        dispBillSummary(c, amt, totalAmt, LocalDate.now());
        pressAnyKeyToContinue();
    }

    public static void searchCurrentCustBillingHistory(Person currentUser) {
        //boolean searchFlag;
        double min = 0;
        double max = 0;
        LocalDate minDate;
        LocalDate maxDate;
        boolean cont;
        if (((Customer) currentUser).getBillHistory().size() == 0) {
            System.out.println("\n\n\t  Search Billing History");
            System.out.println("  ------------------------------------");
            System.out.println("  There are no billing history yet.");
            return;
        }

        do {
            System.out.println("\n\n\t  Search Billing History");
            System.out.println("  ----------------------------------");
            System.out.println("  1. Search by Grand Total Amount");
            System.out.println("  2. Search by Payment Date");
            System.out.println("  3. Back");

            switch (promptInt("  Please enter a selection > ")) {
                case 1 -> {
                    do {
                        do {
                            //get min amount
                            min = Main.promptDouble("\n  Enter minimum amount > RM");

                            //get max amount
                            max = Main.promptDouble("\n  Enter maximum amount > RM");
                            if (max <= min) {
                                System.out.println("\n  Maximum amount must be larger than minimum amount!\n");
                            }
                        } while (max <= min);

                        searchCurrentCustBillingHistoryGrandTotal(min, max, ((Customer) currentUser).getBillHistory());
                        cont = Main.promptYesNo("\n\n  Continue search by grand total amount in billing history? (Y/N) > ");
                        //searchFlag = true; // loop
                    } while (cont);
                }
                case 2 -> {
                    do {
                        do {
                            //get min date
                            if (Main.promptYesNo("\n  Is there a minimum date (Y/N) > ")) {
                                minDate = LocalDate.of(Main.promptInt("  Year  > "),
                                        Main.promptInt("  Month > "),
                                        1);
                            } else {
                                minDate = LocalDate.of(2000, 1, 1);
                            }

                            //get max date
                            if (Main.promptYesNo("\n  Is there a maximum date (Y/N) > ")) {
                                maxDate = LocalDate.of(Main.promptInt("  Year  > "),
                                        (Main.promptInt("  Month > ") + 1),
                                        1);
                            } else {
                                maxDate = LocalDate.of(2200, 1, 1);
                            }
                            if (maxDate.isBefore(minDate)) {
                                System.out.println("\n  Maximum date must be later than minimum date!\n");
                            }
                        } while (maxDate.isBefore(minDate));

                        searchCurrentCustBillingHistoryPaymentDate(minDate, maxDate, ((Customer) currentUser).getBillHistory());
                        cont = Main.promptYesNo("\n  Continue search by payment date in billing history? (Y/N) > ");
                        //searchFlag = true; // loop
                    } while (cont);
                }
                case 3 -> {
                    return;
                }
                default -> {
                    System.out.println("  Invalid choice entered...");
                    //searchFlag = true; // loop
                }
            }
        } while (true);
    }

    public static void searchCurrentCustBillingHistoryGrandTotal(double min, double max, ArrayList<Billing> cbh) {
        int choice;
        double totalAmt;
        ArrayList<Double> amt = new ArrayList<>();
        ArrayList<Integer> choiceArr = new ArrayList<>();
        int resultsCounter;
        //ArrayList<Billing> cbh = ((Customer)Main.getCurrentUser()).getBillHistory();

        resultsCounter = 0;
        System.out.println("\n  -----------------------------------------------------------");
        System.out.println("  | Index |  Transaction ID  |  Date Paid   |  Grand Total  |");
        System.out.println("  -----------------------------------------------------------");
        for (int i = 0; i < cbh.size(); i++) {
            if (cbh.get(i).getGrandTotal() >= min && cbh.get(i).getGrandTotal() <= max) {
                choiceArr.add(i);
                if (resultsCounter > 0) {
                    System.out.println("  |---------------------------------------------------------|");
                }
                System.out.println(String.format("  |  %3d  |", choiceArr.indexOf(i) + 1) +
                        String.format("  %-6s          |", cbh.get(i).getTransactionID()) +
                        String.format("  %s  |", Main.dateToString(cbh.get(i).getPaymentDate())) +
                        String.format("  RM%9.2f  |", cbh.get(i).getGrandTotal())
                );
                resultsCounter++;
            }
        }

        if (resultsCounter == 0) {
            System.out.println("|                                                         |");
            System.out.println("  -----------------------------------------------------------");
            System.out.printf("  %d search result(s)", resultsCounter);
            return;
        }
        System.out.println("  -----------------------------------------------------------");
        System.out.printf("  %d search result(s)", resultsCounter);

        do {
            choice = (Main.promptInt("\n  Choose a record to view in detail (0 - Back) > ") - 1);
            if (choice >= 0 && choice < choiceArr.size()) {
                break;
            } else if (choice == -1) {
                return;
            } else {
                System.out.println("  Invalid choice! Please re-enter.");
            }
        } while (true);

        amt = cbh.get(choiceArr.get(choice)).getEachSubtotal();
        totalAmt = cbh.get(choiceArr.get(choice)).getTotalAmount();
        dispBillSummary(cbh.get(choiceArr.get(choice)), amt, totalAmt, cbh.get(choiceArr.get(choice)).getPaymentDate());
        boolean p = (!Objects.isNull(cbh.get(choiceArr.get(choice)).getPromoApplied()));
        if (p) {
            dispGrandTotal(cbh.get(choiceArr.get(choice)), p, totalAmt, (cbh.get(choiceArr.get(choice)).getTotalAmount() * (1 - cbh.get(choiceArr.get(choice)).getPromoApplied().getPromoRate())), cbh.get(choiceArr.get(choice)).getPromoApplied().getPromoRate());
        } else {
            dispGrandTotal(cbh.get(choiceArr.get(choice)), p, totalAmt, cbh.get(choiceArr.get(choice)).getTotalAmount(), 0);
        }

        //display payment method
        System.out.println("  Payment method           : " + cbh.get(choiceArr.get(choice)).getPaymentMethod());
    }

    public static void searchCurrentCustBillingHistoryPaymentDate(LocalDate minDate, LocalDate maxDate, ArrayList<Billing> cbh) {
        int choice;
        double totalAmt;
        ArrayList<Double> amt = new ArrayList<>();
        ArrayList<Integer> choiceArr = new ArrayList<>();
        int resultsCounter;

        resultsCounter = 0;
        System.out.println("\n  -----------------------------------------------------------");
        System.out.println("  | Index |  Transaction ID  |  Date Paid   |  Grand Total  |");
        System.out.println("  -----------------------------------------------------------");
        for (int i = 0; i < cbh.size(); i++) {
            if ((cbh.get(i).getPaymentDate().isAfter(minDate) || cbh.get(i).getPaymentDate().isEqual(minDate)) && (cbh.get(i).getPaymentDate().isBefore(maxDate))) {
                choiceArr.add(i);
                if (resultsCounter > 0) {
                    System.out.println("  |---------------------------------------------------------|");
                }
                System.out.println(String.format("  |  %3d  |", choiceArr.indexOf(i) + 1) +
                        String.format("  %-6s          |", cbh.get(i).getTransactionID()) +
                        String.format("  %s  |", Main.dateToString(cbh.get(i).getPaymentDate())) +
                        String.format("  RM%9.2f  |", cbh.get(i).getGrandTotal())
                );
                resultsCounter++;
            }
        }
        if (resultsCounter == 0) {
            System.out.println("  |                                                         |");
            System.out.println("  -----------------------------------------------------------");
            System.out.printf("  %d search result(s)", resultsCounter);
            return;
        }
        System.out.println("  -----------------------------------------------------------");
        System.out.printf("  %d search result(s)", resultsCounter);

        do {
            choice = (Main.promptInt("\n  Choose a record to view in detail (0 - Back) > ") - 1);
            if (choice >= 0 && choice < choiceArr.size()) {
                break;
            } else if (choice == -1) {
                return;
            } else {
                System.out.println("  Invalid choice! Please re-enter.");
            }
        } while (true);

        amt = cbh.get(choiceArr.get(choice)).getEachSubtotal();
        totalAmt = cbh.get(choiceArr.get(choice)).getTotalAmount();
        dispBillSummary(cbh.get(choiceArr.get(choice)), amt, totalAmt, cbh.get(choiceArr.get(choice)).getPaymentDate());
        boolean p = (!Objects.isNull(cbh.get(choiceArr.get(choice)).getPromoApplied()));
        if (p) {
            dispGrandTotal(cbh.get(choiceArr.get(choice)), p, totalAmt, (cbh.get(choiceArr.get(choice)).getTotalAmount() * (cbh.get(choiceArr.get(choice)).getPromoApplied().getPromoRate())), cbh.get(choiceArr.get(choice)).getPromoApplied().getPromoRate());
        } else {
            dispGrandTotal(cbh.get(choiceArr.get(choice)), p, totalAmt, cbh.get(choiceArr.get(choice)).getTotalAmount(), 0);
        }

        //display payment method
        System.out.println("  Payment method           : " + cbh.get(choiceArr.get(choice)).getPaymentMethod());
    }
}