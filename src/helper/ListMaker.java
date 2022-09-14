package helper;

import dao.AppointmentDao;
import dao.CustomerDao;
import javafx.collections.ObservableList;
import model.Appointment;
import model.Customer;
import model.Division;
import java.time.LocalDate;

/**
 * A class for populating lists.
 * @author Julez Hudson
 */
public class ListMaker {

    /**
     * Clears the list of all customer names and populates it from the list of all customers.
     * @return the list of all customer names
     */
    public static ObservableList<String> populateAllCustomerNames(){
        ListManager.allCustomerNames.clear();
        for (Customer customer : CustomerDao.populateCustomerList()){
            ListManager.allCustomerNames.add(customer.getCustomerName());
        }
        return ListManager.allCustomerNames;
    }

    /**
     * Clears then populates the appointments by customer list. Appointments are added based on the list of all
     * appointments and based on the customer ID provided.
     * @param customerId the ID number of the customer on which the appointments list being populated is based
     * @return the list of appointments by customer
     */
    public static ObservableList<Appointment> populateAppointmentsByCustomers (int customerId) {
        ListManager.appointmentsByCustomer.clear();
        for (Appointment appointment : dao.AppointmentDao.populateAppointmentList()){
            if (appointment.getCustomerId() == customerId) {
                ListManager.appointmentsByCustomer.add(appointment);
            }
        }
        return ListManager.appointmentsByCustomer;
    }

    /**
     * Clears then populates the contact schedule list of appointments. Appointments are added to the list based on the
     * list of all appointments and the contact ID provided.
     * @param contactId the ID number of the contact on which the appointments being added to the list are based
     * @return the contact schedule list of appointments
     */
    public static ObservableList<Appointment> populateContactSchedule (int contactId) {
        ListManager.contactSchedule.clear();
        for(Appointment appointment : dao.AppointmentDao.populateAppointmentList()){
            if (appointment.getContactId() == contactId) {
                ListManager.contactSchedule.add(appointment);
            }
        }
        return ListManager.contactSchedule;
    }

    /**
     * Clears then populates the all appointment types list. Appointment types are added to the list based on the
     * list of all appointments.
     * @return the list of all appointment types
     */
    public static ObservableList<String> populateAppointmentTypes(){
        ListManager.allAppointmentTypes.clear();
        String appointmentType;
        for (Appointment appointment : AppointmentDao.populateAppointmentList()){
            appointmentType = appointment.getType();
            if (ListManager.allAppointmentTypes.contains(appointmentType)){
                continue;
            }
            ListManager.allAppointmentTypes.add(appointmentType);
        }
        return ListManager.allAppointmentTypes;
    }

    /**
     * Clears then populates the appointments by month list. Adds appointments from the list of all appointments if they
     * have start dates within a 30 day range starting from the current date and ending 29 days after the current date.
     * @return the appointments by month list
     */
    public static ObservableList<Appointment> populateAppointmentsByMonth(){
        LocalDate curMonth = LocalDate.now();
        ListManager.appointmentsByMonth.clear();
        for (Appointment appointment : AppointmentDao.populateAppointmentList()) {
            LocalDate compDate = appointment.getStartDT().toLocalDate();
            if (compDate.getMonth() == curMonth.getMonth()){
                if (compDate.getYear() == curMonth.getYear()) {
                    ListManager.appointmentsByMonth.add(appointment);
                }
            }
        }
        return ListManager.appointmentsByMonth;
    }

    /**
     * Clears then populates the appointments by week list. Adds appointments from the list of all appointments if they
     * have start dates within a 7 day range starting from the current date and ending 6 days after the current date.
     * @return the list of appointments by week
     */
    public static ObservableList<Appointment> populateAppointmentsByWeek() {
        LocalDate curDate = LocalDate.now();
        LocalDate oneWeek = curDate.plusDays(6);
        ListManager.appointmentsByWeek.clear();
        for (Appointment appointment : AppointmentDao.populateAppointmentList()) {
            LocalDate compDate = appointment.getStartDT().toLocalDate();
            boolean boolVal = TimeComparison.compareDates(curDate, oneWeek, compDate);
            if(boolVal) {
                ListManager.appointmentsByWeek.add(appointment);
            }
        }
        return ListManager.appointmentsByWeek;
    }

    /**
     * Clears then populates the divisions by select country list. Divisions are added from the list of all divisions,
     * but only divisions with a country ID matching the one provided are added to the list.
     * @param countryId the ID number of the country on which the divisions are based
     * @return the list of divisions by select country
     */
    public static ObservableList<String> populateDivisionsBySelectCountry(int countryId) {
        ListManager.divisionsBySelectCountry.clear();
        for (Division division : ListManager.allDivisions) {
            if (division.getCountryId() == countryId) {
                ListManager.divisionsBySelectCountry.add(division.getDivisionName());
            }
        }
        return ListManager.divisionsBySelectCountry.sorted();
    }
}
