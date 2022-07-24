package controller;

import dao.AppointmentDao;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.Appointment;
import model.ListManager;
import model.User;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

import static controller.LogIn.currentUser;

public class AppointmentAlert implements Initializable {

    public Text messageText;

    public int userId;
    LocalDate currentDate;
    LocalTime currentTime;
    LocalDateTime currentDateTime;
    LocalDateTime windowDateTime;
    LocalDateTime appointmentStartDateTime;
    LocalDateTime appointmentEndDateTime;
    int comparisonValue;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        for (User user : ListManager.allUsers) {
            if (user.getUserName().equals(currentUser)){
                userId = user.getUserId();
            }
        }

        currentDateTime = LocalDateTime.now();
        windowDateTime = currentDateTime.plusMinutes(16);

       messageText.setText("Please Note: You have no appointments at this time.");

        AppointmentDao.populateAppointmentList();

        for (Appointment appointment : AppointmentDao.allAppointments) {
            if (appointment.getUserId() == userId) {
                appointmentStartDateTime = LocalDateTime.parse(appointment.getStartDate() + " "
                        + appointment.getStartTime() + ":00", formatter);
                //appointmentEndDateTime = LocalDateTime.parse(appointment.getEndDate() + " " + appointment.getEndTime());

                comparisonValue = appointmentStartDateTime.compareTo(currentDateTime);
                if (comparisonValue >= 0) {
                    comparisonValue = appointmentStartDateTime.compareTo(windowDateTime);
                    if (comparisonValue < 0) {
                        messageText.setText("Please Note: Appointment " + appointment.getAppointmentId() + " begins" +
                                " on " + appointment.getStartDate() + " at " + appointment.getStartTime() + ".");
                        return;
                    }
                }
            }
        }
    }
    public void onOkayBtn(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../view/Home.fxml"));
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 1000, 600);
        stage.setTitle("Home");
        stage.setScene(scene);
        stage.show();
    }
}
