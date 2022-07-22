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

import java.net.URL;
import java.sql.Timestamp;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

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
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public void onSaveReturnBtn(ActionEvent actionEvent) throws Exception {
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
