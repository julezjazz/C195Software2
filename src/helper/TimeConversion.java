package helper;

import java.sql.Timestamp;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class TimeConversion {

    public static boolean checkBusinessHours (String date, String time) {
        ZoneId userZI = ZoneId.systemDefault();
        ZoneId estZI = ZoneId.of("America/New_York");
        LocalTime businessOpen = LocalTime.parse("08:00:00");
        LocalTime businessClose = LocalTime.parse("22:00:00");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        int comparisonValue;

        ZonedDateTime userStartZDT = ZonedDateTime.parse(date + time, formatter.withZone(userZI));
        ZonedDateTime estStartZDT = ZonedDateTime.ofInstant(userStartZDT.toInstant(), estZI);
        LocalTime estStartLT = estStartZDT.toLocalTime();

        comparisonValue = estStartLT.compareTo(businessOpen);
        if (comparisonValue < 0) {
            return true;
        }
        comparisonValue = estStartLT.compareTo(businessClose);
        if (comparisonValue > 0) {
            return true;
        }

        return false;
    }
}
