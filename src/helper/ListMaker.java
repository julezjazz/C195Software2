package helper;

import dao.AppointmentDao;
import javafx.collections.ObservableList;
import model.Appointment;
import model.Contact;
import model.ListManager;

public class ListMaker {
    public static ObservableList<Appointment> populateContactSchedule (int contactId) {
        for(Appointment appointment : AppointmentDao.populateAppointmentList()){
            if (appointment.getContactId() == contactId) {
                ListManager.contactSchedule.add(appointment);
            }
        }
        return ListManager.contactSchedule;
    }
}
