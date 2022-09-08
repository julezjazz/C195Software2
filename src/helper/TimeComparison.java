package helper;

import java.time.*;

/**
 * A class for storing methods that compare various times.
 * @author Julez Hudson
 */
public class TimeComparison {

    /**
     * Compares a date-time parameter to established business hours. This method returns true if it is outside of
     * business hours and false if it inside business hours.
     * @param dateTime The time that is being compared to business hours.
     * @return True if dateTime is outside business hours and false if dateTime is inside business hours.
     */
    public static boolean checkBusinessHours (LocalDateTime dateTime) {
        ZoneId userZI = ZoneId.systemDefault();
        ZoneId estZI = ZoneId.of("America/New_York");
        LocalTime businessOpen = LocalTime.parse("08:00");
        LocalTime businessClose = LocalTime.parse("22:00");

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

    /**
     * Compares a date-time parameter to two other date-time parameters.
     * @param dateTime The date-time value that is compared to the other parameters.
     * @param windowStartLDT The start date-time value that the the dateTime parameter is compared to.
     * @param windowEndLDT The end date-time value that the dateTime parameter is compared to.
     * @return True if the dateTime parameter is equal to or greater than the start date-time parameter and less than
     * the end date-time parameter but returns false if either of those conditions are not met.
     */
    public static boolean compareWindow(LocalDateTime dateTime, LocalDateTime windowStartLDT, LocalDateTime
            windowEndLDT){
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

    /**
     * Compares a date parameter to two other date parameters.
     * @param startDate A start date that the compDate parameter is compared to.
     * @param endDate An end date that the compDate parameter is compared to.
     * @param compDate A date parameter that is compared to the startDate and endDate parameters.
     * @return True if the compDate parameter at the same time or later than the startDate parameter and at the same
     * time or earlier than the endDate parameter, but it returns false if either of those conditions are not met.
     */
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

    /**
     * Compares a DateTime parameter to another DateTime parameter.
     * @param start A DateTime parameter representing the start of an appointment which is compared to the end parameter.
     * @param end A DateTime parameter representing the end of an appointment to which the start parameter is compared.
     * @return True if the start is after or at the same time as the end and false if the start time is before the end.
     */
    public static boolean compareStartAndEnd (LocalDateTime start, LocalDateTime end) {
        int comparisonValue;

        comparisonValue = start.compareTo(end);
        if (comparisonValue >= 0){
            return true;
        }
        return false;
    }

    /**
     * Compares start DateTime parameter to current time.
     * @param start A DateTime parameter representing the start of an appointment.
     * @return True if the start date is before the current time and false if not.
     */
    public static boolean compareStartToCurrent (LocalDateTime start) {
        int comparisonValue;

        comparisonValue = start.compareTo(LocalDateTime.now());
        if (comparisonValue < 0) {
            return true;
        }
        return false;
    }
}
