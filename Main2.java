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
 * This class is for records generating purposes
 */

public class Main2 {

    // Methods
    public static void generateEmployeeProfile(ArrayList<Employee> employeeList) {
        int generateCount = 10;
        for (int count = 0; count < generateCount; count++) {
            String username = generateRandomEmployeeUsername(employeeList);
            String password = generateRandomName();
            writeCredentials((count + 1) + ". " + username + " " + password, "employee_credentials.txt");
            employeeList.add(new Employee(generateRandomName(),
                    generateRandomName(),
                    generateRandomNo() + generateRandomNo(),
                    new Random().nextBoolean() ? 'M' : 'F',
                    generateRandomDOB(),
                    generateRandomAddress(),
                    generateRandomName() + "@gmail.com",
                    username,
                    password,
                    ThreadLocalRandom.current().nextInt(1000, 9000 + 1), generateLocalDate()));
        }
    }

    public static void generateCustomerProfile(ArrayList<Customer> customerList, ArrayList<Employee> employeeList, ArrayList<Promotion> promotions) {
        int generateCount = 100;
        for (int count = 0; count < generateCount; count++) {
            String username = generateRandomCustomerUsername(customerList);
            String password = generateRandomName();

            // write username and password to txt file
            writeCredentials((count + 1) + ". " + username + " " + password, "customer_credentials.txt");

            // generate customer account
            customerList.add(new Customer(Main.convertCapitalize(generateRandomName()),
                    Main.convertCapitalize(generateRandomName()),
                    generateRandomNo() + generateRandomNo(),
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
            int billHistoryCount = ThreadLocalRandom.current().nextInt(0,  10 + 1);
            for (int i = 0; i < billHistoryCount ; i++) {
                generateBillHistory(customerList.get(customerList.size()-1), employeeList, promotions);
            }

            //customerList.get(customerList.size()-1).addReservation(generateReservation(customerList.get(customerList.size()-1), employeeList,generateLocalDateTime(LocalDate.now().getYear(),LocalDate.now().minusMonths(1).getMonthValue(), LocalDate.now().getDayOfMonth())));
        }
    }

    public static void writeCredentials(String input, String path) {
        try {
            Files.write(Paths.get(path), (input + "\n").getBytes(), StandardOpenOption.APPEND);
        } catch (IOException e) {
            System.out.println("An error occurred...");
            e.printStackTrace();
        }
    }

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

    public static void generateBillHistory(Customer customer, ArrayList<Employee> employeeList, ArrayList<Promotion> promotions) {
        int reserveAmount = ThreadLocalRandom.current().nextInt(0,  5 + 1);
        ArrayList<Reservation> reserve = new ArrayList<>();
        int price = ThreadLocalRandom.current().nextInt(100,  1000 + 1);
        Promotion promo = promotions.get(ThreadLocalRandom.current().nextInt(0,  promotions.size()));
        boolean applyPromo = ThreadLocalRandom.current().nextBoolean();

        reserve.add(generateReservation(customer, employeeList,null));
        for (int i = 0; i < reserveAmount; i++) {
            reserve.add((generateReservation(customer, employeeList,null)));
        }
        customer.getReservation().addAll(reserve);
        // have promo applied
        if (applyPromo) {
            customer.getBillHistory().add(new Billing(reserve, price, (double) price*110/100, new Random().nextBoolean() ? "Cash" : "Card",
                    promo, generateSource(), generateLocalDate()));
        }
        // doesn't have promo applied
        else {
            customer.getBillHistory().add(new Billing(reserve, price, (double) price*110/100, new Random().nextBoolean() ? "Cash" : "Card",
                    null, null, generateLocalDate()));
        }
    }

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

        return new Reservation(reserveDateTime.with(LocalTime.of(generateSessionHours(), 0)), service,
                pet, "No remarks", ThreadLocalRandom.current().nextInt(1, 4 + 1),
                employee, reserveDateTime.minusDays(ThreadLocalRandom.current().nextInt(1,7 + 1)));
    }

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

    public static LocalDateTime generateLocalDateTime(int year, int month, int day) {
        long minDay = LocalDate.of(year, month, day).toEpochDay();
        long maxDay = LocalDate.now().toEpochDay();
        long randomDay = ThreadLocalRandom.current().nextLong(minDay, maxDay);
        LocalDate randomDate = LocalDate.ofEpochDay(randomDay);
        int hours = ThreadLocalRandom.current().nextInt(0, 23 + 1);
        int minutes = ThreadLocalRandom.current().nextInt(0, 59 + 1);
        return randomDate.atTime(hours, minutes);
    }
    public static LocalDate generateLocalDate() {
        long minDay = LocalDate.of(2018, 1, 1).toEpochDay();
        long maxDay = LocalDate.now().toEpochDay();
        long randomDay = ThreadLocalRandom.current().nextLong(minDay, maxDay);
        return LocalDate.ofEpochDay(randomDay);
    }

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

    public static String generateRandomNo() {
        int number = new Random().nextInt(99999);
        return String.format("%05d", number);
    }

    public static LocalDate generateRandomDOB() {
        int day = ThreadLocalRandom.current().nextInt(1, 28 + 1);
        int month = ThreadLocalRandom.current().nextInt(1, 12 + 1);
        int year = ThreadLocalRandom.current().nextInt(1930, 2000 + 1);
        return LocalDate.of(year, month, day);
    }

    public static Address generateRandomAddress() {
        return new Address(generateRandomName() + generateRandomName(),
                generateRandomName(), generateRandomNo(),
                generateRegion(), generateRandomName(), "Malaysia");
    }
}
