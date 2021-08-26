import java.time.LocalDateTime;


/**
 * This Report Class is to generate reports that are believed will bring lots of useful information to the pet shop
 * in terms of profits earning and reducing unnecessary expenditure.
 * In this class, there will be a few report type for selection and some allow the user to manage the time duration
 * for the report.
 *
 * @author Lee Khoon Hong
 * @author Chan Jia Wei
 * @author Tan Shi Jing
 * @author Ong Jia Hui
 */
public class Report {
    private static LocalDateTime dateCreated;
    private static LocalDateTime reportStartDate;
    private static LocalDateTime reportEndDate;

    // Getter & Setter
    public static LocalDateTime getDateCreated() {
        return dateCreated;
    }

    public static void setDateCreated(LocalDateTime dateCreated) {
        Report.dateCreated = dateCreated;
    }

    public static LocalDateTime getReportStartDate() {
        return reportStartDate;
    }

    public static void setReportStartDate(LocalDateTime reportStartDate) {
        Report.reportStartDate = reportStartDate;
    }

    public static LocalDateTime getReportEndDate() {
        return reportEndDate;
    }

    public static void setReportEndDate(LocalDateTime reportEndDate) {
        Report.reportEndDate = reportEndDate;
    }

    // Methods

}
