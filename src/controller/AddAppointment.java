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
import java.time.LocalDateTime;
import java.util.ResourceBundle;

import static controller.LogIn.currentUser;

public class AddAppointment implements Initializable {

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
    public String createdBy;
    public int customerId;
    public int userId;
    public int contactId;

    boolean boolValue;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        contactCB.setItems(ListManager.allContactNames);
        startHourCB.setItems(ListManager.hours);
        endHourCB.setItems(ListManager.hours);
        startMinuteCB.setItems(ListManager.minutes);
        endMinuteCB.setItems(ListManager.minutes);
        errorText.setText(" ");
    }

    public void onSaveReturnBtn(ActionEvent actionEvent) throws Exception {
        errorText.setText(" ");
        title = titleTF.getText();
        description = descriptionTF.getText();
        location = locationTF.getText();
        contactName = contactCB.getSelectionModel().getSelectedItem().toString();
        type = typeTF.getText();
        startDate = startDateDP.getValue().toString();
        endDate = endDateDP.getValue().toString();
        createdBy = currentUser;
        customerId = Integer.parseInt(customerIdTF.getText());
        userId = Integer.parseInt(userIdTF.getText());

        for (Contact contact : ListManager.allContacts) {
            if (contact.getContactName().equals(contactName)) {
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

        AppointmentDao.insert(title, description, location, type, startTS, endTS, createdBy, customerId,
                        userId, contactId);

        Parent root = FXMLLoader.load(getClass().getResource("../view/Home.fxml"));
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 1000, 600);
        stage.setTitle("Home");
        stage.setScene(scene);
        stage.show();
    }

    public void onCancelBtn (ActionEvent actionEvent) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("../view/Home.fxml"));
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 1000, 600);
        stage.setTitle("Home");
        stage.setScene(scene);
        stage.show();
    }
}

