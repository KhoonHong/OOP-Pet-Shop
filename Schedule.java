import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Schedule {

    private GregorianCalendar today = new GregorianCalendar();
    private ArrayList<String> dayInWeek;

    private static final char AVAILABLE = '0';
    private static final char UNAVAILABLE = 'X';

    private ArrayList<ArrayList<Character>> symbols = new ArrayList<>();
    private HashMap<Customer, HashMap<Integer, Integer>> recordPosition = new HashMap<>();


    Schedule() {
        this.defaultSlot();
        this.setDayInWeek(dayInWeekCalc(today.get(Calendar.DAY_OF_WEEK)));// monday - sunday sorting

        setSlotStatus(2, 3, UNAVAILABLE);
        setSlotStatus(5, 1, UNAVAILABLE);
    }

    // Getter & Setter
    public HashMap<Customer, HashMap<Integer, Integer>> getRecordPosition() {
        return recordPosition;
    }

    public void setRecordPosition(HashMap<Customer, HashMap<Integer, Integer>> recordPosition) {
        this.recordPosition = recordPosition;
    }

    public GregorianCalendar getToday() {
        return today;
    }

    public void setToday(GregorianCalendar today) {
        this.today = today;
    }

    public ArrayList<String> getDayInWeek() {
        return dayInWeek;
    }

    public void setDayInWeek(ArrayList<String> dayInWeek) {
        this.dayInWeek = dayInWeek;
    }

    public ArrayList<ArrayList<Character>> getSymbols() {
        return symbols;
    }

    public void setSymbols(ArrayList<ArrayList<Character>> symbols) {
        this.symbols = symbols;
    }

    // Methods

    public void addToSchedule(int x, int y, Customer obj) {
        recordPosition.put(obj, new HashMap<>());
        recordPosition.get(obj).put(x,y);
        setSlotStatus(x, y, UNAVAILABLE);
    }

    public void removeFromSchedule(Customer obj) {
        HashMap<Integer, Integer> position = recordPosition.get(obj);
        int x = (int) position.keySet().toArray()[0];
        int y = (int) position.values().toArray()[0];
        setSlotStatus(x, y, AVAILABLE);
        recordPosition.remove(obj);
    }

    public String futureDate(int count) {
        return LocalDate.now().plusDays(count).format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }

    // Shorten obtainSchedules method code
    public String slotStatusByRow(int index) {
        StringBuilder str = new StringBuilder("+  ");
        str.append(dayInWeek.get(index)).append(" ");
        str.append(futureDate(index+1));
        str.append(" +    ");

        for (int i = 0; i < 4; i++ ) {
            str.append(getSlotStatus(index, i));
            // last part in row
            if (i != 3) {
                str.append("     +    ");
            }
            else {
                str.append("     +");
            }
        }
        return str.toString();
    }

    public String[] obtainSchedules() {
        return new String[] {
                "---------------------------------------------------------------",
                "+                 +  Ses1    +  Ses2    +  Ses3    +  Ses4    +",
                "+                 + 9am-11am + 11am-1pm + 2pm-4pm  + 4pm-6pm  +",
                "---------------------------------------------------------------" + "\t\t\tLegends",
                slotStatusByRow(0),
                "---------------------------------------------------------------" + "\t\t\t--------",
                slotStatusByRow(1),
                "---------------------------------------------------------------" + "\t\t\tAvailable -> 'O'",
                slotStatusByRow(2),
                "---------------------------------------------------------------" + "\t\t\tUnavailable -> 'X'",
                slotStatusByRow(3),
                "---------------------------------------------------------------",
                slotStatusByRow(4),
                "---------------------------------------------------------------",
                slotStatusByRow(5),
                "---------------------------------------------------------------",
                slotStatusByRow(6),
                "---------------------------------------------------------------"
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

    // Modify slot status
    public void setSlotStatus(int x, int y, char symbol) {
        symbols.get(x).set(y, symbol);
    }

    // Check slot status
    public char getSlotStatus(int x, int y) {
        return symbols.get(x).get(y);
    }

}
