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
import model.Contact;
import model.ListManager;

import java.net.URL;
import java.sql.Timestamp;
import java.time.*;
import java.time.format.DateTimeFormatter;
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
    public Timestamp startDateTime;
    public Timestamp endDateTime;
    public String createdBy;
    public int customerId;
    public int userId;
    public int contactId;

    ZoneId utcZI = ZoneId.of("UTC");
    ZoneId userZI = ZoneId.systemDefault();
    ZoneId estZI = ZoneId.of("America/New_York");
    LocalTime businessOpen = LocalTime.parse("08:00:00");
    LocalTime businessClose = LocalTime.parse("22:00:00");
    int comparisonValue;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

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
        startHour = startHourCB.getValue().toString();
        startMinute = startMinuteCB.getValue().toString();
        startTime = " " + startHour + ":" + startMinute + ":00";


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
            errorText.setText("End time must be within business hours.");
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

        createdBy = currentUser;
        customerId = Integer.parseInt(customerIdTF.getText());
        userId = Integer.parseInt(userIdTF.getText());

        for(Contact contact : ListManager.allContacts) {
            if(contact.getContactName().equals(contactName)){
                contactId = contact.getContactId();
            }
        }

       AppointmentDao.insert(title, description, location, type, startDateTime, endDateTime, createdBy, customerId,
                userId, contactId);

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
