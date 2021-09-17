import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;


/**
 * This main class is for records generating purposes
 *
 * @author Lee Khoon Hong
 */

public class Main2 {

    // Methods

    /**
     * To generate employee accounts for pet shop program
     *
     * @param employeeList An array of employee objects
     */
    public static void generateEmployeeProfile(ArrayList<Employee> employeeList) {
        // number of employees to generate
        int generateCount = 10;

        for (int count = 0; count < generateCount; count++) {
            // generate username and password
            String username = generateRandomEmployeeUsername(employeeList);
            String password = generateRandomName();

            // write username and password to txt file
            writeCredentials((count + 1) + ". " + username + " " + password, "employee_credentials.txt");

            // generate employee account object
            employeeList.add(new Employee(generateRandomName(),
                    generateRandomName(),
                    "0" + generateRandomNo() + generateRandomNo(),
                    new Random().nextBoolean() ? 'M' : 'F',
                    generateRandomDOB(),
                    generateRandomAddress(),
                    generateRandomName() + "@gmail.com",
                    username,
                    password,
                    ThreadLocalRandom.current().nextInt(1000, 9000 + 1), generateLocalDate()));
        }
    }

    /**
     * To generate customer accounts for pet shop program
     *
     * @param customerList An array of {@code Customer} objects
     * @param employeeList An array of {@code Employee} objects
     * @param promotions An array of {@code Promotion} objects
     */
    public static void generateCustomerProfile(ArrayList<Customer> customerList, ArrayList<Employee> employeeList, ArrayList<Promotion> promotions) {
        // number of customers to generate
        int generateCount = 100;

        for (int count = 0; count < generateCount; count++) {
            // generate username and password
            String username = generateRandomCustomerUsername(customerList);
            String password = generateRandomName();

            // write username and password to txt file
            writeCredentials((count + 1) + ". " + username + " " + password, "customer_credentials.txt");

            // generate customer account object
            customerList.add(new Customer(Main.convertCapitalize(generateRandomName()),
                    Main.convertCapitalize(generateRandomName()),
                    "0" + generateRandomNo() + generateRandomNo(),
                    new Random().nextBoolean() ? 'M' : 'F',
                    generateRandomDOB(),
                    generateRandomAddress(),
                    generateRandomName() + "@gmail.com",
                    username,
                    password, generateLocalDate()));

            // add pet
            // random pet count 1 - 5
            int petCount = ThreadLocalRandom.current().nextInt(1, 5 + 1);
            for (int i = 0; i < petCount; i++) {
                customerList.get(customerList.size() - 1).addPet(generatePet());
            }

            // generate & add bill history records
            int billHistoryCount = ThreadLocalRandom.current().nextInt(0,  10 + 1); // random bill history count 0 - 10
            for (int i = 0; i < billHistoryCount ; i++) {
                generateBillHistory(customerList.get(customerList.size()-1), employeeList, promotions);
            }
        }
    }

    /**
     * Method to handle appending text into text file.
     *
     * @param input Text to be appended into the text file
     * @param path File path for the text to be written into
     */
    public static void writeCredentials(String input, String path) {
        try {
            Files.write(Paths.get(path), (input + "\n").getBytes(), StandardOpenOption.APPEND); // append text
        } catch (IOException e) {
            System.out.println("An error occurred while handling text file...");
            e.printStackTrace();
        }
    }

    /**
     * Generator method to generate random promotion source
     *
     * @return Promotion source
     */
    public static String generateSource() {
        switch (ThreadLocalRandom.current().nextInt(1,  5 + 1)) {
            case 1 -> {
                return "Youtube";
            }
            case 2 -> {
                return "Instagram";
            }
            case 3 -> {
                return "Facebook";
            }
            case 4 -> {
                return "Twitter";
            }
            default -> {
                return "Friends";
            }
        }
    }

    /**
     * Generator method to generate randomized bill history for the {@code Customer} object passed in by parameter.
     *
     * @param customer The customer to generate bill history for
     * @param employeeList An array of {@code Employee} objects
     * @param promotions An array of {@code Promotion} objects
     */
    public static void generateBillHistory(Customer customer, ArrayList<Employee> employeeList, ArrayList<Promotion> promotions) {
        // to store generated reservations
        ArrayList<Reservation> reserve = new ArrayList<>();
        Billing bill;

        // generate the amount of reservation to be added into
        int reserveAmount = ThreadLocalRandom.current().nextInt(0,  5 + 1);

        Promotion promo = promotions.get(ThreadLocalRandom.current().nextInt(0,  promotions.size()));
        boolean applyPromo = ThreadLocalRandom.current().nextBoolean();

        // there will be at least one reservation in a bill
        reserve.add(generateReservation(customer, employeeList,null));

        // generate a specific amount of reservations
        for (int i = 0; i < reserveAmount; i++) {
            reserve.add((generateReservation(customer, employeeList,null)));
        }

        // add the entire reservation arraylist content to the customer reservation arraylist
        customer.getReservation().addAll(reserve);
        // have promo applied
        if (applyPromo) {
            bill = new Billing(reserve, new Random().nextBoolean() ? "Cash" : "Card",
                    promo, generateSource(), generateLocalDate());

            bill.setTotalAmount(bill.calcTotalAmount());
            bill.setGrandTotal((bill.getTotalAmount() - (bill.getTotalAmount()*promo.getPromoRate()))*1.10); // with promo
            // add in new billing object into the customer bill history
        }
        // doesn't have promo applied
        else {
            // add in new billing object into the customer bill history
            bill = new Billing(reserve, new Random().nextBoolean() ? "Cash" : "Card",
                    null, null, generateLocalDate());
            bill.setTotalAmount(bill.calcTotalAmount());
            bill.setGrandTotal(bill.getTotalAmount()*1.10); // without promo
        }
        customer.getBillHistory().add(bill);
    }

    /**
     * Generator method to generate randomized {@code Reservation} object for customer
     *
     * @param customer The target customer to generate reservation for
     * @param employeeList An array of {@code Employee} objects
     * @param date Start date for generating randomized reservation date time
     * @return {@code Reservation} object with randomized data
     */
    public static Reservation generateReservation(Customer customer, ArrayList<Employee> employeeList, LocalDateTime date) {
        int petIndex = ThreadLocalRandom.current().nextInt(0,  customer.getPets().size());
        Pet pet = customer.getPets().get(petIndex);
        Service service;
        ArrayList<Integer> capableService = new ArrayList<>();

        if (pet instanceof Bird) {
            capableService.add(4);
        }
        else if (pet instanceof Cat) {
            capableService.add(1);
            capableService.add(2);
            capableService.add(3);
            capableService.add(4);
        }
        else if (pet instanceof Dog) {
            capableService.add(1);
            capableService.add(2);
            capableService.add(3);
            capableService.add(4);
        }
        else if (pet instanceof Rabbit) {
            capableService.add(1);
            capableService.add(2);
            capableService.add(4);
        }

        LocalDate startDate = generateLocalDate();
        LocalDate endDate = startDate.plusDays(ThreadLocalRandom.current().nextInt(1, 19 + 1));

        switch (ThreadLocalRandom.current().nextInt(0, capableService.size())) {
            case 1 -> service = new Bath(new Random().nextBoolean(),
                    new Random().nextBoolean(), new Random().nextBoolean(),
                    new Random().nextBoolean(), new Random().nextBoolean(),
                    new Random().nextBoolean(), new Random().nextBoolean(),
                    new Random().nextBoolean(), new HashMap<>());

            case 2 -> service = new Groom(new Random().nextBoolean(),
                    new Random().nextBoolean(), new Random().nextBoolean(),
                    new Random().nextBoolean(), new Random().nextBoolean(),
                    new Random().nextBoolean(), new Random().nextBoolean(), new HashMap<>());

            case 3 -> service = new Massage(new Random().nextBoolean(),
                    new Random().nextBoolean(),
                    new Random().nextBoolean(), new HashMap<>());

            default -> service = new Shelter(new Random().nextBoolean(),
                        new Random().nextBoolean(), new Random().nextBoolean(), generateSize(),
                        startDate, endDate, (int) ChronoUnit.DAYS.between(startDate, endDate),
                        new Random().nextBoolean(), new Random().nextBoolean());
        }

        Employee employee = employeeList.get(ThreadLocalRandom.current().nextInt(0, employeeList.size()));

        LocalDateTime reserveDateTime = date == null ? generateLocalDateTime(2021, 7, 1) : date;

        int sessionHrs = ThreadLocalRandom.current().nextInt(1, 4 + 1);

        return new Reservation(reserveDateTime.with(LocalTime.of(Reservation.sessionToTime(sessionHrs), 0)), service,
                pet, "No remarks", sessionHrs,
                employee, reserveDateTime.minusDays(ThreadLocalRandom.current().nextInt(1,7 + 1)));
    }

    /**
     * Generator method to generate random session hours for customer reservation
     *
     * @return Session hours
     */
    public static int generateSessionHours() {
        int output;
        switch (ThreadLocalRandom.current().nextInt(1, 4 + 1)) {
            case 1 -> output = 9;
            case 2 -> output = 11;
            case 3 -> output = 14;
            case 4 -> output = 16;
            default -> output = ThreadLocalRandom.current().nextBoolean() ? 11 : 14;
        }
        return output;
    }

    /**
     * Generator method to generate random {@code Pet} object for customer
     *
     * @return {@code Pet} object with randomized data
     */
    public static Pet generatePet() {
        int age = ThreadLocalRandom.current().nextInt(1, 20 + 1);
        switch(ThreadLocalRandom.current().nextInt(1, 4 + 1)) {
            case 1 -> {
                return new Dog(new Random().nextBoolean(),
                        age, new Random().nextBoolean() ? 'M' : 'F', "Black", generateLevel(), generateSize(), false);
            }
            case 2 -> {
                return new Cat(new Random().nextBoolean(),
                        age, new Random().nextBoolean() ? 'M' : 'F', "White", generateLevel(), generateSize(), false);
            }
            case 3 -> {
                return new Rabbit(new Random().nextBoolean(),
                        age, new Random().nextBoolean() ? 'M' : 'F', "Black", generateLevel(), generateSize(), false);
            }
            case 4 -> {
                return new Bird(age, new Random().nextBoolean() ? 'M' : 'F', "White", generateLevel(), generateSize());
            }
        }
        return new Cat(new Random().nextBoolean(),
                age, new Random().nextBoolean() ? 'M' : 'F', "White", generateLevel(), generateSize(), false);
    }

    /**
     * Generator method to generate random {@code Level} enum
     *
     * @return {@code Level} enum
     */
    public static Level generateLevel() {
        switch (ThreadLocalRandom.current().nextInt(1, 3 + 1)) {
            case 1 -> {
                return Level.LOW;
            }
            case 2 -> {
                return Level.MEDIUM;
            }
            case 3 -> {
                return Level.HIGH;
            }
        }
        return Level.MEDIUM;
    }

    /**
     * Generator method to generate random {@code Size} enum
     *
     * @return {@code Size} enum
     */
    public static Size generateSize() {
        switch (ThreadLocalRandom.current().nextInt(1, 5 + 1)) {
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
        }
        return Size.MEDIUM;
    }

    /**
     * Generator method to generate specific region for customer address
     *
     * @return Region cardinal direction
     */
    public static String generateRegion() {
        String region = "";
        switch (ThreadLocalRandom.current().nextInt(1, 9 + 1)) {
            case 1 -> region = "North";
            case 2 -> region = "North East";
            case 3 -> region = "East";
            case 4 -> region = "South East";
            case 5 -> region = "South";
            case 6 -> region = "South West";
            case 7 -> region = "West";
            case 8 -> region = "North West";
            case 9 -> region = "Other states";
        }
        return region;
    }

    /**
     * Generator method to generate random {@code LocalDateTime} object
     *
     * @param year Starting year to generate
     * @param month Starting month to generate
     * @param day Starting day to generate
     * @return Randomized {@code LocalDateTime}
     */
    public static LocalDateTime generateLocalDateTime(int year, int month, int day) {
        long minDay = LocalDate.of(year, month, day).toEpochDay();
        long maxDay = LocalDate.now().toEpochDay();
        long randomDay = ThreadLocalRandom.current().nextLong(minDay, maxDay);
        LocalDate randomDate = LocalDate.ofEpochDay(randomDay);
        int hours = ThreadLocalRandom.current().nextInt(0, 23 + 1);
        int minutes = ThreadLocalRandom.current().nextInt(0, 59 + 1);
        return randomDate.atTime(hours, minutes);
    }

    /**
     * Generator method to generate {@code LocalDate} object
     *
     * @return Randomized {@code LocalDate}
     */
    public static LocalDate generateLocalDate() {
        long minDay = LocalDate.of(2018, 1, 1).toEpochDay();
        long maxDay = LocalDate.now().toEpochDay();
        long randomDay = ThreadLocalRandom.current().nextLong(minDay, maxDay);
        return LocalDate.ofEpochDay(randomDay);
    }

    /**
     * Generator method to generate random customer username
     *
     * @param customerList An array of {@code Customer} objects
     * @return Randomized customer username
     */
    public static String generateRandomCustomerUsername(ArrayList<Customer> customerList) {
        String name;
        boolean restart;
        do {
            restart = false;
            name = generateRandomName();
            for (Customer cust : customerList) {
                if (cust.username.equals(name)) {
                    restart = true;
                    break;
                }
            }
        }
        while (restart);
        return name;
    }

    /**
     * Generator method to generate random username for employee
     *
     * @param employeeList An array of {@code Employee} object
     * @return Randomized employee username
     */
    public static String generateRandomEmployeeUsername(ArrayList<Employee> employeeList) {
        String name;
        boolean restart;
        do {
            restart = false;
            name = generateRandomName();
            for (Employee emp : employeeList) {
                if (emp.username.equals(name)) {
                    restart = true;
                    break;
                }
            }
        }
        while (restart);
        return name;
    }

    /**
     * Generator method to generate random name
     *
     * @return Randomized name
     */
    public static String generateRandomName() {
        int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 6;
        Random random = new Random();
        StringBuilder buffer = new StringBuilder(targetStringLength);
        for (int i = 0; i < targetStringLength; i++) {
            int randomLimitedInt = leftLimit + random.nextInt(rightLimit - leftLimit + 1);
            buffer.append((char) randomLimitedInt);
        }
        return buffer.toString();
    }

    /**
     * Generator method to generate random 5 digit in {@code String}
     *
     * @return Randomized 5 digits in {@code String}
     */
    public static String generateRandomNo() {
        int number = new Random().nextInt(99999);
        return String.format("%05d", number);
    }

    /**
     * Generator method to generate {@code LocalDate} object for date of birth
     *
     * @return Randomized {@code LocalDate} for date of birth
     */
    public static LocalDate generateRandomDOB() {
        int day = ThreadLocalRandom.current().nextInt(1, 28 + 1);
        int month = ThreadLocalRandom.current().nextInt(1, 12 + 1);
        int year = ThreadLocalRandom.current().nextInt(1940, 2000 + 1);
        return LocalDate.of(year, month, day);
    }

    /**
     * Generator method to generate {@code Address} object
     *
     * @return Randomized {@code Address} object
     */
    public static Address generateRandomAddress() {
        return new Address(generateRandomName() + generateRandomName(),
                generateRandomName(), generateRandomNo(),
                generateRegion(), generateRandomName(), "Malaysia");
    }
}
