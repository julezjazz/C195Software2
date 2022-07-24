package controller;

import dao.AppointmentDao;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.Appointment;
import model.Contact;
import model.ListManager;

import java.net.URL;
import java.sql.Timestamp;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

import static controller.Home.selectedAppointment;
import static controller.LogIn.currentUser;

public class ModifyAppointment implements Initializable {

    public TextField appointmentIdTF;
    public TextField titleTF;
    public TextField descriptionTF;
    public TextField locationTF;
    public ComboBox contactCB;
    public TextField typeTF;
    public DatePicker startDateDP;
    public ComboBox startHourCB;
    public ComboBox startMinuteCB;
    public DatePicker endDateDP;
    public ComboBox endHourCB;
    public ComboBox endMinuteCB;
    public TextField customerIdTF;
    public TextField userIdTF;
    public Text errorText;

    public String title;
    public String description;
    public String location;
    public String contactName;
    public String type;
    public String startDate;
    public String endDate;
    public String startHour;
    public String endHour;
    public String startMinute;
    public String endMinute;
    public String startTime;
    public String endTime;
    public Timestamp startDateTime;
    public Timestamp endDateTime;
    public String updatedBy;
    public int customerId;
    public int userId;
    public int contactId;
    public int appointmentId;

    ZoneId utcZI = ZoneId.of("UTC");
    ZoneId userZI = ZoneId.systemDefault();
    ZoneId estZI = ZoneId.of("America/New_York");
    LocalTime businessOpen = LocalTime.parse("08:00:00");
    LocalTime businessClose = LocalTime.parse("22:00:00");
    int comparisonValue;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        appointmentIdTF.setText(String.valueOf(selectedAppointment.getAppointmentId()));
        titleTF.setText(selectedAppointment.getTitle());
        descriptionTF.setText(selectedAppointment.getDescription());
        locationTF.setText(selectedAppointment.getLocation());
        contactCB.setItems(ListManager.allContactNames);
        contactCB.getSelectionModel().select(selectedAppointment.getContactName());
        typeTF.setText(selectedAppointment.getType());
        startDateDP.setValue(LocalDate.parse(selectedAppointment.getStartDate()));
        endDateDP.setValue(LocalDate.parse(selectedAppointment.getEndDate()));
        errorText.setText(" ");

        startHourCB.setItems(ListManager.hours);
        startHourCB.getSelectionModel().select(selectedAppointment.getStartTime().substring(0,2));
        startMinuteCB.setItems(ListManager.minutes);
        startMinuteCB.getSelectionModel().select(selectedAppointment.getStartTime().substring(3,5));
        endHourCB.setItems(ListManager.hours);
        endHourCB.getSelectionModel().select(selectedAppointment.getEndTime().substring(0,2));
        endMinuteCB.setItems(ListManager.minutes);
        endMinuteCB.getSelectionModel().select(selectedAppointment.getEndTime().substring(3,5));

        customerIdTF.setText(String.valueOf(selectedAppointment.getCustomerId()));
        userIdTF.setText(String.valueOf(selectedAppointment.getUserId()));
    }

    public void onSaveReturnBtn(ActionEvent actionEvent) throws Exception {
        errorText.setText(" ");
        appointmentId = Integer.parseInt(appointmentIdTF.getText());
        title = titleTF.getText();
        description = descriptionTF.getText();
        location = locationTF.getText();
        contactName = contactCB.getSelectionModel().getSelectedItem().toString();
        type = typeTF.getText();
        startDate = startDateDP.getValue().toString();
        endDate = endDateDP.getValue().toString();
        updatedBy = currentUser;
        customerId = Integer.parseInt(customerIdTF.getText());
        userId = Integer.parseInt(userIdTF.getText());

        for(Contact contact : ListManager.allContacts) {
            if(contact.getContactName().equals(contactName)){
                contactId = contact.getContactId();
            }
        }

        startHour = startHourCB.getValue().toString();
        startMinute = startMinuteCB.getValue().toString();
        startTime = " " + startHour + ":" + startMinute + ":00";

        //Converts start time from user's time zone to UTC
        ZonedDateTime userStartZDT = ZonedDateTime.parse(startDate + startTime, formatter.withZone(userZI));
        ZonedDateTime estStartZDT = ZonedDateTime.ofInstant(userStartZDT.toInstant(), estZI);

        LocalTime estStartLT = estStartZDT.toLocalTime();

        comparisonValue = estStartLT.compareTo(businessOpen);

        if(comparisonValue < 0) {
            errorText.setText("Start time must be within business hours.");
            return;
        }

        comparisonValue = estStartLT.compareTo(businessClose);

        if(comparisonValue > 0) {
            errorText.setText("Start time must be within business hours.");
            return;
        }

        ZonedDateTime startZDT = ZonedDateTime.ofInstant(userStartZDT.toInstant(), utcZI);
        LocalDateTime startLDT = startZDT.toLocalDateTime();
        startDateTime = Timestamp.valueOf(startLDT);

        endHour = endHourCB.getValue().toString();
        endMinute = endMinuteCB.getValue().toString();
        endTime = " " + endHour + ":" + endMinute + ":00";

        //Converts end time from user's time zone to UTC
        ZonedDateTime userEndZDT = ZonedDateTime.parse(endDate + endTime, formatter.withZone(userZI));
        ZonedDateTime endZDT = ZonedDateTime.ofInstant(userEndZDT.toInstant(), utcZI);
        ZonedDateTime estEndZDT = ZonedDateTime.ofInstant(userEndZDT.toInstant(), estZI);

        LocalTime estEndLT = estEndZDT.toLocalTime();

        comparisonValue = estEndLT.compareTo(businessOpen);

        if(comparisonValue < 0) {
            errorText.setText("End time must be within business hours.");
            return;
        }

        comparisonValue = estEndLT.compareTo(businessClose);

        if(comparisonValue > 0) {
            errorText.setText("End time must be within business hours.");
            return;
        }

        LocalDateTime endLDT = endZDT.toLocalDateTime();
        endDateTime = Timestamp.valueOf(endLDT);

        LocalDateTime userStartLDT = userStartZDT.toLocalDateTime();
        LocalDateTime userEndLDT = userEndZDT.toLocalDateTime();

        for (Appointment appointment : AppointmentDao.allAppointments) {
            if (customerId == appointment.getCustomerId()) {
                if(appointment.getAppointmentId() == appointmentId) {
                    continue;
                }
                LocalDateTime existingStartTime = LocalDateTime.parse(appointment.getStartDate() + " "
                        + appointment.getStartTime() + ":00", formatter);
                comparisonValue = userStartLDT.compareTo(existingStartTime);
                if (comparisonValue >= 0) {
                    LocalDateTime existingEndTime = LocalDateTime.parse(appointment.getEndDate() + " "
                            + appointment.getEndTime() + ":00", formatter);
                    comparisonValue = userStartLDT.compareTo(existingEndTime);
                    if (comparisonValue <= 0) {
                        errorText.setText("Appointment start time conflicts with another appointment for selected" +
                                " customer");
                        return;
                    }
                }
                comparisonValue = userEndLDT.compareTo(existingStartTime);
                if (comparisonValue >= 0) {
                    LocalDateTime existingEndTime = LocalDateTime.parse(appointment.getEndDate() + " "
                            + appointment.getEndTime() + ":00", formatter);
                    comparisonValue = userEndLDT.compareTo(existingEndTime);
                    if (comparisonValue <= 0) {
                        errorText.setText("Appointment end time conflicts with another appointment for selected" +
                                " customer");
                        return;
                    }
                }
            }
        }

        AppointmentDao.update(title, description, location, type, startDateTime, endDateTime, updatedBy, customerId,
                userId, contactId, appointmentId);

        Parent root = FXMLLoader.load(getClass().getResource("../view/Home.fxml"));
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 1000, 600);
        stage.setTitle("Home");
        stage.setScene(scene);
        stage.show();
    }

    public void onCancelBtn(ActionEvent actionEvent) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("../view/Home.fxml"));
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 1000, 600);
        stage.setTitle("Home");
        stage.setScene(scene);
        stage.show();
    }
}
