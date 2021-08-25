import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Schedule {

    private GregorianCalendar today = new GregorianCalendar();
    private static ArrayList<String> dayInWeek;

    private static final char AVAILABLE = 'O';
    private static final char UNAVAILABLE = 'X';

    private ArrayList<ArrayList<Character>> symbols = new ArrayList<>();
    private HashMap<Reservation, HashMap<Integer, Integer>> recordPosition = new HashMap<>();


    Schedule() {
        this.defaultSlot();
        setDayInWeek(dayInWeekCalc(today.get(Calendar.DAY_OF_WEEK)));// monday - sunday sorting
    }

    // Getter & Setter
    public static char getAvailable() {
        return AVAILABLE;
    }

    public static char getUnavailable() {
        return UNAVAILABLE;
    }

    public HashMap<Reservation, HashMap<Integer, Integer>> getRecordPosition() {
        return recordPosition;
    }

    public void setRecordPosition(HashMap<Reservation, HashMap<Integer, Integer>> recordPosition) {
        this.recordPosition = recordPosition;
    }

    public GregorianCalendar getToday() {
        return today;
    }

    public void setToday(GregorianCalendar today) {
        this.today = today;
    }

    public static ArrayList<String> getDayInWeek() {
        return dayInWeek;
    }

    public static void setDayInWeek(ArrayList<String> dayInWeek) {
        Schedule.dayInWeek = dayInWeek;
    }

    public ArrayList<ArrayList<Character>> getSymbols() {
        return symbols;
    }

    public void setSymbols(ArrayList<ArrayList<Character>> symbols) {
        this.symbols = symbols;
    }

    // Methods
    public void addToSchedule(int x, int y, Reservation obj) {
        recordPosition.put(obj, new HashMap<>());
        recordPosition.get(obj).put(x,y);
        setSlotStatus(x, y, UNAVAILABLE);
    }

    // Remove reservation from schedule
    public void removeFromSchedule(Reservation obj) {
        HashMap<Integer, Integer> position = recordPosition.get(obj);
        int x = (int) position.keySet().toArray()[0];
        int y = (int) position.values().toArray()[0];
        setSlotStatus(x, y, AVAILABLE);
        recordPosition.remove(obj);
    }

    // check if reservation is in the employee record
    public boolean getReservation(Reservation reservation) {
        for (var obj : recordPosition.entrySet()) {
            if (reservation.equals(obj.getKey())) {
                return true;
            }
        }
        return false;
    }

    public static String futureDate(int count) {
        return LocalDate.now().plusDays(count).format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }

    public static LocalDateTime futureDateLocal(int count) {
        return LocalDateTime.now().plusDays(count);
    }

    // Shorten obtainSchedules method code
    public String slotStatusByRow(int index) {
        StringBuilder str = new StringBuilder("| ");
        str.append(dayInWeek.get(index)).append(" ");
        str.append(futureDate(index+1));
        str.append("  |     ");

        for (int i = 0; i < 4; i++ ) {
            str.append(getSlotStatus(index, i));
            // last part in row
            if (i != 3) {
                str.append("      |     ");
            }
            else {
                str.append("      |   ");
            }
        }
        return str.toString();
    }

    public String[] obtainSchedules() {
        return new String[] {
                "+---------------------------------------------------------------------+",
                "|                 |    Ses1    |    Ses2    |    Ses3    |    Ses4    |",
                "|                 |  9am-11am  |  11am-1pm  |   2pm-4pm  |   4pm-6pm  |",
                "+---------------------------------------------------------------------+" + "\t\t\tLegends",
                slotStatusByRow(0),
                "|---------------------------------------------------------------------|" + "\t\t\t--------",
                slotStatusByRow(1),
                "|---------------------------------------------------------------------|" + "\t\t\tAvailable -> 'O'",
                slotStatusByRow(2),
                "|---------------------------------------------------------------------|" + "\t\t\tUnavailable -> 'X'",
                slotStatusByRow(3),
                "|---------------------------------------------------------------------|",
                slotStatusByRow(4),
                "|---------------------------------------------------------------------|",
                slotStatusByRow(5),
                "|---------------------------------------------------------------------|",
                slotStatusByRow(6),
                "+---------------------------------------------------------------------+"
        };
    }

    // Sort days in week
    public ArrayList<String> dayInWeekCalc(int dayOfWeek) {
        String[] daysInWeeks = {"Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"};
        ArrayList<String> results = new ArrayList<>();

        for (int index = dayOfWeek-1, count = 0; count < 7; index++, count++) {
            results.add(daysInWeeks[index]);
            if (index == 6) {
                index = -1; // reset week from sunday to monday
            }
        }
        return results;
    }

    // Reset all slot to available
    public void defaultSlot() {
        // Vertical
        for (int outer = 0; outer < 7 ; outer++) {
            // Initialize internal arraylist
            symbols.add(outer, new ArrayList<>());

            // Horizontal
            for (int inner = 0; inner < 4; inner++) {
                // Set all slot to available
                symbols.get(outer).add(inner, AVAILABLE);
            }
        }
    }

    // prints out schedule
    public void displaySchedule() {
        for (String line : this.obtainSchedules()) {
            System.out.println(line);
        }
    }

    // Modify slot status
    public void setSlotStatus(int x, int y, char symbol) {
        symbols.get(x).set(y, symbol);
    }

    // Check slot status
    public char getSlotStatus(int x, int y) {
        return symbols.get(x).get(y);
    }
}
