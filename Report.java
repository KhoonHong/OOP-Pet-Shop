import java.time.LocalDateTime;

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
