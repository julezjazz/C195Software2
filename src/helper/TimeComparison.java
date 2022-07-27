package helper;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class TimeComparison {

    public static boolean checkBusinessHours (String date, String time) {
        ZoneId userZI = ZoneId.systemDefault();
        ZoneId estZI = ZoneId.of("America/New_York");
        LocalTime businessOpen = LocalTime.parse("08:00:00");
        LocalTime businessClose = LocalTime.parse("22:00:00");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        int comparisonValue;

        ZonedDateTime userZDT = ZonedDateTime.parse(date + time, formatter.withZone(userZI));
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
    public static boolean compareWindow(Timestamp dateTime, Timestamp windowStartDT, Timestamp windowEndDT){
        int comparisonValue;

        comparisonValue = dateTime.compareTo(windowStartDT);
        if (comparisonValue >= 0) {
            comparisonValue = dateTime.compareTo(windowEndDT);
            if (comparisonValue < 0) {
                return true;
            }
        }
        return false;
    }
}
