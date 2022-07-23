package controller;

import dao.AppointmentDao;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.stage.Stage;
import model.Appointment;
import model.ListManager;
import model.User;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ResourceBundle;

import static controller.LogIn.currentUser;

public class AppointmentAlert implements Initializable {

    public int userId;
    LocalDate currentDate;
    LocalTime currentTime;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        for (User user : ListManager.allUsers) {
            if (user.getUserName().equals(currentUser)){
                userId = user.getUserId();
            }
        }
        currentDate = LocalDate.now();
        currentTime = LocalTime.now();

        System.out.println(currentDate);
        System.out.println(currentTime);

        AppointmentDao.populateAppointmentList();

        for (Appointment appointment : AppointmentDao.allAppointments) {
            if (appointment.getUserId() == userId) {
                System.out.println(appointment.getStartDate());
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
