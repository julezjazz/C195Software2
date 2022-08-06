package helper;

import java.time.*;
import java.time.format.DateTimeFormatter;

public class TimeComparison {

    public static boolean checkBusinessHours (LocalDateTime dateTime) {
        ZoneId userZI = ZoneId.systemDefault();
        ZoneId estZI = ZoneId.of("America/New_York");
        LocalTime businessOpen = LocalTime.parse("08:00");
        LocalTime businessClose = LocalTime.parse("22:00");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        int comparisonValue;

        ZonedDateTime userZDT = ZonedDateTime.of(dateTime, userZI);
        ZonedDateTime estZDT = ZonedDateTime.ofInstant(userZDT.toInstant(), estZI);
        LocalTime estLT = estZDT.toLocalTime();

        comparisonValue = estLT.compareTo(businessOpen);
        if (comparisonValue < 0) {
            return true;
        }
        comparisonValue = estLT.compareTo(businessClose);
        if (comparisonValue > 0) {
            return true;
        }

        return false;
    }
    public static boolean compareWindow(LocalDateTime dateTime, LocalDateTime windowStartLDT, LocalDateTime windowEndLDT){
        int comparisonValue;

        comparisonValue = dateTime.compareTo(windowStartLDT);
        if (comparisonValue >= 0) {
            comparisonValue = dateTime.compareTo(windowEndLDT);
            if (comparisonValue < 0) {
                return true;
            }
        }
        return false;
    }
    public static boolean compareDates(LocalDate startDate, LocalDate endDate, LocalDate compDate) {
        int comparisonValue;

        comparisonValue = compDate.compareTo(startDate);
        if (comparisonValue >= 0){
            comparisonValue = compDate.compareTo(endDate);
            if (comparisonValue <= 0) {
                return true;
            }
        }
        return false;
    }
}
