package helper;

import dao.AppointmentDao;
import javafx.collections.ObservableList;
import model.Appointment;
import model.ListManager;

public class ListMaker {
    public static ObservableList<Appointment> populateContactSchedule (int contactId) {
        ListManager.contactSchedule.clear();
        for(Appointment appointment : AppointmentDao.populateAppointmentList()){
            if (appointment.getContactId() == contactId) {
                ListManager.contactSchedule.add(appointment);
            }
        }
        return ListManager.contactSchedule;
    }
}
