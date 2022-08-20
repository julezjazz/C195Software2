package helper;

import dao.AppointmentDao;
import dao.CustomerDao;
import javafx.collections.ObservableList;
import model.Appointment;
import model.Customer;

import java.time.LocalDate;

public class ListMaker {

    public static ObservableList<String> populateAllCustomerNames(){
        ListManager.allCustomerNames.clear();
        for (Customer customer : CustomerDao.populateCustomerList()){
            ListManager.allCustomerNames.add(customer.getCustomerName());
        }
        return ListManager.allCustomerNames;
    }
    public static ObservableList<Appointment> populateAppointmentsByCustomers (int customerId) {
        ListManager.appointmentsByCustomer.clear();
        for (Appointment appointment : ListMaker.populateAppointmentsByMonth()){
            if (appointment.getCustomerId() == customerId) {
                ListManager.appointmentsByCustomer.add(appointment);
            }
        }
        return ListManager.appointmentsByCustomer;
    }

    public static ObservableList<Appointment> populateContactSchedule (int contactId) {
        ListManager.contactSchedule.clear();
        for(Appointment appointment : populateAppointmentsByMonth()){
            if (appointment.getContactId() == contactId) {
                ListManager.contactSchedule.add(appointment);
            }
        }
        return ListManager.contactSchedule;
    }

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

    public static ObservableList<Appointment> populateAppointmentsByMonth(){
        LocalDate curDate = LocalDate.now();
        LocalDate oneMonth = curDate.plusDays(29);
        ListManager.appointmentsByMonth.clear();
        for (Appointment appointment : AppointmentDao.populateAppointmentList()) {
            LocalDate compDate = appointment.getStartDT().toLocalDate();
            Boolean boolVal = TimeComparison.compareDates(curDate, oneMonth, compDate);
            if(boolVal == true) {
                ListManager.appointmentsByMonth.add(appointment);
            }
        }
        return ListManager.appointmentsByMonth;
    }
    public static ObservableList<Appointment> populateAppointmentsByWeek() {
        LocalDate curDate = LocalDate.now();
        LocalDate oneWeek = curDate.plusDays(6);
        ListManager.appointmentsByWeek.clear();
        for (Appointment appointment : AppointmentDao.populateAppointmentList()) {
            LocalDate compDate = appointment.getStartDT().toLocalDate();
            Boolean boolVal = TimeComparison.compareDates(curDate, oneWeek, compDate);
            if(boolVal == true) {
                ListManager.appointmentsByWeek.add(appointment);
            }
        }
        return ListManager.appointmentsByWeek;
    }
}
