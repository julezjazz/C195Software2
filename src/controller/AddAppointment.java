package controller;

import dao.AppointmentDao;
import helper.NameIdConversion;
import helper.TimeComparison;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.Appointment;
import helper.ListManager;

import java.io.IOException;
import java.net.URL;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ResourceBundle;
import static controller.LogIn.currentUser;

/** A class for controlling <code>../view/AddAppointment.fxml</code>.
 * @author Julez Hudson
 */
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
    public Button cancelBtn;

    public String title;
    public String description;
    public String location;
    public String contactName;
    public String type;
    public LocalDate startDate;
    public LocalDate endDate;
    public String startHour;
    public String endHour;
    public String startMinute;
    public String endMinute;
    public LocalTime startTime;
    public LocalTime endTime;
    public LocalDateTime startLDT;
    public LocalDateTime endLDT;
    public Timestamp startTS;
    public Timestamp endTS;
    public String createdBy;
    public int customerId;
    public int userId;
    public int contactId;

    boolean boolValue;

    /**
     * Sets all combo boxes with appropriate lists and uses a lambda expression to navigate back to Appointments page
     * upon clicking the cancel button. Lambda is used to avoid creating another method.
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        contactCB.setItems(ListManager.allContactNames);
        startHourCB.setItems(ListManager.hours);
        endHourCB.setItems(ListManager.hours);
        startMinuteCB.setItems(ListManager.minutes);
        endMinuteCB.setItems(ListManager.minutes);
        errorText.setText(" ");

        cancelBtn.setOnAction(e -> {
            Parent root = null;
            try {
                root = FXMLLoader.load(getClass().getResource("../view/Appointments.fxml"));
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
            Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
            Scene scene = new Scene(root, 1100, 600);
            stage.setTitle("Appointments");
            stage.setScene(scene);
            stage.show();
        });
    }

    /**
     * Gets all user input, tests time input for errors, uses input to add a new appointment to the Appointment table of
     * the database, and navigates back to Appointments page.
     * @param actionEvent Clicking the save and return button.
     * @throws Exception In case of sql or input or output exception.
     */
    public void onSaveReturnBtn(ActionEvent actionEvent) throws Exception {
        title = titleTF.getText();
        description = descriptionTF.getText();
        location = locationTF.getText();

        try{contactName = contactCB.getSelectionModel().getSelectedItem().toString();}
        catch (NullPointerException e){
            errorText.setText("Please select a contact.");
            return;
        }

        type = typeTF.getText();

        if(startDateDP.getValue() == null){
            errorText.setText("Please select a start date.");
            return;
        }

        startDate = startDateDP.getValue();

        if(endDateDP.getValue() == null){
            errorText.setText("Please select an end date.");
            return;
        }

        endDate = endDateDP.getValue();
        createdBy = currentUser;

        try {
            customerId = Integer.parseInt(customerIdTF.getText());
        }
        catch (NumberFormatException e){
            errorText.setText("Please enter a valid customer ID");
            return;
        }

        try {
            userId = Integer.parseInt(userIdTF.getText());
        }
        catch (NumberFormatException e){
            errorText.setText("Please enter a valid user ID");
            return;
        }

        contactId = NameIdConversion.returnContactID(contactName);

        try{startHour = startHourCB.getValue().toString();}
        catch (NullPointerException e) {
            errorText.setText("Please select a start time.");
            return;
        }

        try{startMinute = startMinuteCB.getValue().toString();}
        catch (NullPointerException e) {
            errorText.setText("Please select a start time.");
            return;
        }

        startTime = LocalTime.parse(startHour + ":" + startMinute);
        startLDT = LocalDateTime.of(startDate, startTime);

        try{endHour = endHourCB.getValue().toString();}
        catch (NullPointerException e){
           errorText.setText("Please select an end time.");
           return;
        }

        try{endMinute = endMinuteCB.getValue().toString();}
        catch (NullPointerException e){
            errorText.setText("Please select an end time.");
            return;
        }

        endTime = LocalTime.parse(endHour + ":" + endMinute);
        endLDT = LocalDateTime.of(endDate, endTime);

        boolValue = TimeComparison.checkBusinessHours(startLDT);
        if (boolValue) {
            errorText.setText("Start time must be within business hours.");
            return;
        }

        boolValue = TimeComparison.checkBusinessHours(endLDT);
        if (boolValue) {
            errorText.setText("End time must be within business hours.");
            return;
        }

        boolValue = TimeComparison.compareStartAndEnd(startLDT, endLDT);
        if (boolValue) {
            errorText.setText("End time must be after start time.");
            return;
        }

        boolValue = TimeComparison.compareStartToCurrent(startLDT);
        if (boolValue) {
            errorText.setText("Start time must not be in the past.");
            return;
        }

        for (Appointment appointment : AppointmentDao.populateAppointmentList()) {
            if (customerId == appointment.getCustomerId()) {
                LocalDateTime otherStart = appointment.getStartDT();
                LocalDateTime otherEnd = appointment.getEndDT();
                boolValue = TimeComparison.compareWindow(startLDT, otherStart, otherEnd);
                if (boolValue) {
                    errorText.setText("Appointment conflicts with another appointment starting on "
                            + otherStart.toLocalDate() + " at " + otherStart.toLocalTime() + " and ending on "
                            + otherEnd.toLocalDate() + " at " + otherEnd.toLocalTime() + ".");
                    return;
                }
                boolValue = TimeComparison.compareWindow(endLDT, otherStart, otherEnd);
                if (boolValue) {
                    errorText.setText("Appointment conflicts with another appointment starting on "
                            + otherStart.toLocalDate() + " at " + otherStart.toLocalTime() + " and ending on "
                            + otherEnd.toLocalDate() + " at " + otherEnd.toLocalTime() + ".");
                    return;
                }
                boolValue = TimeComparison.compareWindow(otherStart, startLDT, endLDT);
                if (boolValue) {
                    errorText.setText("Appointment conflicts with another appointment starting on "
                            + otherStart.toLocalDate() + " at " + otherStart.toLocalTime() + " and ending on "
                            + otherEnd.toLocalDate() + " at " + otherEnd.toLocalTime() + ".");
                    return;
                }
                boolValue = TimeComparison.compareWindow(otherEnd, startLDT, endLDT);
                if (boolValue) {
                    errorText.setText("Appointment conflicts with another appointment starting on "
                            + otherStart.toLocalDate() + " at " + otherStart.toLocalTime() + " and ending on "
                            + otherEnd.toLocalDate() + " at " + otherEnd.toLocalTime() + ".");
                    return;
                }
            }
        }

        startTS = Timestamp.valueOf(startLDT);
        endTS = Timestamp.valueOf(endLDT);

        AppointmentDao.insert(title, description, location, type, startTS, endTS, createdBy, customerId,
                        userId, contactId);

        Parent root = FXMLLoader.load(getClass().getResource("../view/Appointments.fxml"));
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 1100, 600);
        stage.setTitle("Appointments");
        stage.setScene(scene);
        stage.show();
    }
}

