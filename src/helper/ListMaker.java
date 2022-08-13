package helper;

import dao.AppointmentDao;
import javafx.collections.ObservableList;
import model.Appointment;

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

    public static ObservableList<String> populateAppointmentTypes(){
        ListManager.allAppointmentTypes.clear();
        String appointmentType;
        for (Appointment appointment : AppointmentDao.populateAppointmentList()){
            appointmentType = appointment.getType();
            if (ListManager.allAppointmentTypes.contains(appointmentType){
                continue;
            }
            ListManager.allAppointmentTypes.add(appointmentType);
        }
        return ListManager.allAppointmentTypes;
    }
}
