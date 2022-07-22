package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Contact;
import model.ListManager;

import java.net.URL;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
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
       public String createdBy;
    public int customerId;
    public int userId;
    public int contactId;

    ZoneId utcZI = ZoneId.of("UTC");
    ZoneId userZI = ZoneId.systemDefault();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        contactCB.setItems(ListManager.allContactNames);
        startHourCB.setItems(ListManager.hours);
        endHourCB.setItems(ListManager.hours);
        startMinuteCB.setItems(ListManager.minutes);
        endMinuteCB.setItems(ListManager.minutes);
    }

    public void onSaveReturnBtn(ActionEvent actionEvent) throws Exception {

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


        //Converts start time from user's time zone to UTC
        ZonedDateTime startZDT = ZonedDateTime.parse(startDate + startTime, formatter.withZone(userZI));
        LocalDateTime startLDT = startZDT.toLocalDateTime();
        ZonedDateTime userStartZDT = ZonedDateTime.of(startLDT, userZI);
        startZDT = ZonedDateTime.ofInstant(userStartZDT.toInstant(), utcZI);


        endHour = endHourCB.getValue().toString();
        endMinute = endMinuteCB.getValue().toString();
        endTime = " " + endHour + ":" + endMinute + ":00";

        //Converts start time from user's time zone to UTC
        ZonedDateTime endZDT = ZonedDateTime.parse(endDate + endTime, formatter.withZone(userZI));
        LocalDateTime endLDT = endZDT.toLocalDateTime();
        ZonedDateTime userEndZDT = ZonedDateTime.of(endLDT, userZI);
        endZDT = ZonedDateTime.ofInstant(userEndZDT.toInstant(), utcZI);




        createdBy = currentUser;
        customerId = Integer.parseInt(customerIdTF.getText());
        userId = Integer.parseInt(userIdTF.getText());

        for(Contact contact : ListManager.allContacts) {
            if(contact.getContactName().equals(contactName)){
                contactId = contact.getContactId();
            }
        }




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
