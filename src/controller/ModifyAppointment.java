package controller;

import dao.AppointmentDao;
import helper.TimeComparison;
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
    public LocalDateTime startLDT;
    public LocalDateTime endLDT;
    public Timestamp startTS;
    public Timestamp endTS;
    public String updatedBy;
    public int customerId;
    public int userId;
    public int contactId;
    public int appointmentId;

    boolean boolValue;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        appointmentIdTF.setText(String.valueOf(selectedAppointment.getAppointmentId()));
        titleTF.setText(selectedAppointment.getTitle());
        descriptionTF.setText(selectedAppointment.getDescription());
        locationTF.setText(selectedAppointment.getLocation());
        contactCB.setItems(ListManager.allContactNames);
        contactCB.getSelectionModel().select(selectedAppointment.getContactName());
        typeTF.setText(selectedAppointment.getType());
        startDateDP.setValue(selectedAppointment.getStartDT().toLocalDate());
        endDateDP.setValue(selectedAppointment.getEndDT().toLocalDate());
        errorText.setText(" ");

        startHourCB.setItems(ListManager.hours);
        startHourCB.getSelectionModel().select(selectedAppointment.getStartDT().toLocalTime().toString().substring(0,2));
        startMinuteCB.setItems(ListManager.minutes);
        startMinuteCB.getSelectionModel().select(selectedAppointment.getStartDT().toLocalTime().toString().substring(3,5));
        endHourCB.setItems(ListManager.hours);
        endHourCB.getSelectionModel().select(selectedAppointment.getEndDT().toLocalTime().toString().substring(0,2));
        endMinuteCB.setItems(ListManager.minutes);
        endMinuteCB.getSelectionModel().select(selectedAppointment.getEndDT().toLocalTime().toString().substring(3,5));

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
        startLDT = LocalDateTime.parse(startDate + startTime);

        endHour = endHourCB.getValue().toString();
        endMinute = endMinuteCB.getValue().toString();
        endTime = " " + endHour + ":" + endMinute + ":00";
        endLDT = LocalDateTime.parse(endDate + endTime);

        boolValue = TimeComparison.checkBusinessHours(startLDT);
        if (boolValue == true) {
            errorText.setText("Start time must be within business hours.");
            return;
        }

        boolValue = TimeComparison.checkBusinessHours(endLDT);
        if (boolValue == true) {
            errorText.setText("End time must be within business hours.");
            return;
        }

        for (Appointment appointment : AppointmentDao.allAppointments) {
            if (customerId == appointment.getCustomerId()) {
                LocalDateTime existingStartTime = appointment.getStartDT();
                LocalDateTime existingEndTime = appointment.getEndDT();

                boolValue = TimeComparison.compareWindow(startLDT, existingStartTime, existingEndTime);
                if (boolValue == true) {
                    errorText.setText("Appointment start time conflicts with another appointment for selected" +
                            " customer");
                    return;
                }
                boolValue = TimeComparison.compareWindow(endLDT, existingStartTime, existingEndTime);
                if (boolValue == true) {
                    errorText.setText("Appointment end time conflicts with another appointment for selected" +
                            " customer");
                    return;
                }
            }
        }

        startTS = Timestamp.valueOf(startLDT);
        endTS = Timestamp.valueOf(endLDT);

        AppointmentDao.update(title, description, location, type, startTS, endTS, updatedBy, customerId,
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
