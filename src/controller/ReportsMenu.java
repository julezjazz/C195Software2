package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * A class for controlling <code>../view/ReportsMenu.fxml</code>.
 * @author Julez Hudson
 */
public class ReportsMenu {

    /**
     * Navigates to Appointment Counts by Type page.
     * @param actionEvent Clicking appointment counts by type button.
     * @throws IOException In case of input or output exception.
     */
    public void onAppointmentCountsBtn (ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../view/AppointmentCountsByType.fxml"));
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 300, 300);
        stage.setTitle("Customer Appointment Counts by Type");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Navigates to contact schedule page.
     * @param actionEvent Clicking the contact schedule button.
     * @throws IOException In case of input or output exception.
     */
    public void onContactSchedBtn (ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../view/ContactSchedule.fxml"));
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 1065, 600);
        stage.setTitle("Contact Schedule");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Navigates to the appointments by customer page.
     * @param actionEvent Clicking the Appointments by Customer button.
     * @throws IOException In case of input or output exception.
     */
    public void onAppsByCustomerBtn (ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../view/AppointmentsByCustomer.fxml"));
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 1065, 600);
        stage.setTitle("Appointments by Customer");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Navigates to the appointments page.
     * @param actionEvent Clicking the appointments button.
     * @throws IOException In case of input or output exception.
     */
    public void onAppointmentsBtn (ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../view/Appointments.fxml"));
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 1100, 600);
        stage.setTitle("Appointments");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Navigates to the customers page.
     * @param actionEvent Clicking the customers button.
     * @throws IOException In case of input or output exception.
     */
    public void onCustomersBtn (ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../view/Customers.fxml"));
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 1000, 600);
        stage.setTitle("Customers");
        stage.setScene(scene);
        stage.show();
    }
}
