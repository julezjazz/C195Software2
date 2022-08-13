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
import java.time.LocalDateTime;
import java.util.ResourceBundle;
import static controller.LogIn.currentUser;

public class AppointmentAlert implements Initializable {
    public Text messageText;

    public int userId;

    LocalDateTime currentLDT;
    LocalDateTime windowLDT;
    LocalDateTime appointmentStartLDT;

    boolean boolVal;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        for (User user : ListManager.allUsers) {
            if (user.getUserName().equals(currentUser)){
                userId = user.getUserId();
            }
        }
        currentLDT = LocalDateTime.now();
        windowLDT = currentLDT.plusMinutes(16);

        messageText.setText("Please Note: You have no appointments at this time.");

        for (Appointment appointment : AppointmentDao.populateAppointmentList()) {
            if (appointment.getUserId() == userId) {
                appointmentStartLDT = appointment.getStartDT();

                boolVal = TimeComparison.compareWindow(appointmentStartLDT, currentLDT, windowLDT);

                if (boolVal == true) {
                    messageText.setText("Please Note: Appointment " + appointment.getAppointmentId() + " begins on " +
                            appointment.getStartDT().toLocalDate() + " at " + appointment.getStartDT().toLocalTime()
                            + ".");
                }
            }
        }
    }
    public void onOkayBtn(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../view/ReportsMenu.fxml"));
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 280, 244);
        stage.setTitle("Reports Menu");
        stage.setScene(scene);
        stage.show();
    }
}
