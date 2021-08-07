import java.text.NumberFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Locale;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Schedule obj1 = new Schedule();
        Date birthday = new Date(2001,12,15);

        LocalDateTime sien = LocalDateTime.of(2015,
                3, 29, 0, 0 ,0);
        System.out.println(sien.getDayOfMonth()+"/"+sien.getMonth()+"/"+sien.getYear());

        Address add = new Address();
        Customer obj2 = new Customer();
        for (String i : obj1.obtainSchedules()) {
            System.out.println(i);
        }
        obj1.addToSchedule(4,1,obj2);
        for (String i : obj1.obtainSchedules()) {
            System.out.println(i);
        }
        obj1.removeFromSchedule(obj2);
        for (String i : obj1.obtainSchedules()) {
            System.out.println(i);
        }
        // lolllllllllllllll

    }

    public static String promptString(String promptMessage) {
        Scanner input = new Scanner(System.in);
        System.out.println(promptMessage);
        return input.nextLine();
    }

    public static int promptInt(String promptMessage) {
        Scanner input = new Scanner(System.in);
        while(true) {
            try {
                System.out.println(promptMessage);
                int value = input.nextInt();
                input.nextLine();
                return value;
            } catch(InputMismatchException e) {
                input.nextLine();
            }
        }
    }

    public static double promptDouble(String promptMessage) {
        Scanner input = new Scanner(System.in);
        while(true) {
            try {
                System.out.println(promptMessage);
                double value = input.nextDouble();
                input.nextLine();
                return value;
            } catch(InputMismatchException e) {
                input.nextLine();
            }
        }
    }

    public static char promptChar(String promptMessage) {
        Scanner input = new Scanner(System.in);
        System.out.println(promptMessage);
        return input.nextLine().charAt(0);
    }

    // enter money value as double to return formatted RM value as string
    public static String convertCurrency(double amount) {
        return NumberFormat.getCurrencyInstance(new Locale("ms", "MY")).format(amount);
    }
}
