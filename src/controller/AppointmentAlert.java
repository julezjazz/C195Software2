package controller;

import dao.AppointmentDao;
import helper.TimeComparison;
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
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ResourceBundle;

import static controller.LogIn.currentUser;

public class AppointmentAlert implements Initializable {

    public Text messageText;

    public int userId;
    LocalDateTime currentDT;
    Timestamp currentTS;
    LocalDateTime windowDT;
    Timestamp windowTS;
    Timestamp appointmentStartDateTime;

    boolean boolVal;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        for (User user : ListManager.allUsers) {
            if (user.getUserName().equals(currentUser)){
                userId = user.getUserId();
            }
        }

        currentDT = LocalDateTime.now();
        currentTS = Timestamp.valueOf(currentDT);
        windowDT = currentDT.plusMinutes(16);
        windowTS = Timestamp.valueOf(windowDT);

        messageText.setText("Please Note: You have no appointments at this time.");

        AppointmentDao.populateAppointmentList();

        for (Appointment appointment : AppointmentDao.allAppointments) {
            if (appointment.getUserId() == userId) {
                appointmentStartDateTime = Timestamp.valueOf(appointment.getStartDate() + " "
                        + appointment.getStartTime() + ":00");

                boolVal = TimeComparison.compareWindow(appointmentStartDateTime, currentTS, windowTS);

                if (boolVal == true) {
                    messageText.setText("Please Note: Appointment " + appointment.getAppointmentId() + " begins on " +
                            appointment.getStartDate() + " at " + appointment.getStartTime() + ".");
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
