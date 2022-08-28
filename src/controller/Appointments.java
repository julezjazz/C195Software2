package controller;

import dao.AppointmentDao;
import helper.ListMaker;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.Appointment;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

/**
 * A class for controlling <code>../view/Appointments.fxml</code>.
 * @author Julez Hudson
 */
public class Appointments implements Initializable {
    public TableView appointmentsTable;
    public TableColumn appointmentIdCol;
    public TableColumn titleCol;
    public TableColumn descriptionCol;
    public TableColumn locationCol;
    public TableColumn contactNameCol;
    public TableColumn contactIdCol;
    public TableColumn typeCol;
    public TableColumn startCol;
    public TableColumn endCol;
    public TableColumn customerIdCol;
    public TableColumn userIdCol;
    public Text messageText;

    public static Appointment selectedAppointment;

    /**
     * Fills the table view with the list of appointments by month upon initialization of the page.
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        messageText.setText(" ");

        appointmentsTable.setItems(ListMaker.populateAppointmentsByMonth());
        appointmentIdCol.setCellValueFactory(new PropertyValueFactory<>("appointmentId"));
        titleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
        descriptionCol.setCellValueFactory(new PropertyValueFactory<>("description"));
        locationCol.setCellValueFactory(new PropertyValueFactory<>("location"));
        contactNameCol.setCellValueFactory(new PropertyValueFactory<>("contactName"));
        contactIdCol.setCellValueFactory(new PropertyValueFactory<>("contactId"));
        typeCol.setCellValueFactory(new PropertyValueFactory<>("type"));
        startCol.setCellValueFactory(new PropertyValueFactory<>("startDT"));
        endCol.setCellValueFactory(new PropertyValueFactory<>("endDT"));
        customerIdCol.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        userIdCol.setCellValueFactory(new PropertyValueFactory<>("userId"));
    }

    /**
     * Fills the table view with the list of appointments by month upon the Month radio button being clicked.
      */
   public void onMonthView() {
        appointmentsTable.setItems(ListMaker.populateAppointmentsByMonth());
        appointmentIdCol.setCellValueFactory(new PropertyValueFactory<>("appointmentId"));
        titleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
        descriptionCol.setCellValueFactory(new PropertyValueFactory<>("description"));
        locationCol.setCellValueFactory(new PropertyValueFactory<>("location"));
        contactNameCol.setCellValueFactory(new PropertyValueFactory<>("contactName"));
        contactIdCol.setCellValueFactory(new PropertyValueFactory<>("contactId"));
        typeCol.setCellValueFactory(new PropertyValueFactory<>("type"));
        startCol.setCellValueFactory(new PropertyValueFactory<>("startDT"));
        endCol.setCellValueFactory(new PropertyValueFactory<>("endDT"));
        customerIdCol.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        userIdCol.setCellValueFactory(new PropertyValueFactory<>("userId"));
    }
    /**
     * Fills the table view with the list of appointments by week upon the week radio button being clicked.
     */
    public void onWeekView() {
        appointmentsTable.setItems(ListMaker.populateAppointmentsByWeek());
        appointmentIdCol.setCellValueFactory(new PropertyValueFactory<>("appointmentId"));
        titleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
        descriptionCol.setCellValueFactory(new PropertyValueFactory<>("description"));
        locationCol.setCellValueFactory(new PropertyValueFactory<>("location"));
        contactNameCol.setCellValueFactory(new PropertyValueFactory<>("contactName"));
        contactIdCol.setCellValueFactory(new PropertyValueFactory<>("contactId"));
        typeCol.setCellValueFactory(new PropertyValueFactory<>("type"));
        startCol.setCellValueFactory(new PropertyValueFactory<>("startDT"));
        endCol.setCellValueFactory(new PropertyValueFactory<>("endDT"));
        customerIdCol.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        userIdCol.setCellValueFactory(new PropertyValueFactory<>("userId"));
    }

    /**
     * Navigates to the add appointment page.
     * @param actionEvent Clicking the add appointment button.
     * @throws IOException In case of an input or output exception.
     */
    public void onAddAppointmentBtn(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../view/AddAppointment.fxml"));
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 900, 600);
        stage.setTitle("Add Appointment");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Navigates to the modify appointment page. If no appointment is selected, this method changes the display text
     * to alert the user that they must select an appointment to modify.
     * @param actionEvent Clicking the modify appointment button.
     * @throws IOException In case of input or output exception.
     */
    public void onModAppointmentBtn(ActionEvent actionEvent) throws IOException {
        selectedAppointment = (Appointment) appointmentsTable.getSelectionModel().getSelectedItem();
        if (selectedAppointment == null){
            messageText.setText("Please select an appointment to modify.");
            return;
        }

        Parent root = FXMLLoader.load(getClass().getResource("../view/ModifyAppointment.fxml"));
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 900, 600);
        stage.setTitle("Modify Appointment");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Deletes the selected appointment from the Appointments table of the database and updates the Appointments table
     * view to reflect the changes. If no appointment is selected when the delete button is clicked, this method updates
     * the display text to alert the user to select an appointment to delete.
     * @throws SQLException In case of a sql exception.
     */
    public void onDelAppointmentBtn() throws SQLException {
        selectedAppointment = (Appointment) appointmentsTable.getSelectionModel().getSelectedItem();
        if (selectedAppointment == null){
            messageText.setText("Please select an appointment to delete.");
            return;
        }

        int appointmentId = selectedAppointment.getAppointmentId();
        String type = selectedAppointment.getType();
        AppointmentDao.delete(appointmentId);
        messageText.setText("Appointment with ID " + appointmentId + " and of type " + type + " has been " +
                "deleted.");

        appointmentsTable.setItems(AppointmentDao.populateAppointmentList());
        appointmentIdCol.setCellValueFactory(new PropertyValueFactory<>("appointmentId"));
        titleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
        descriptionCol.setCellValueFactory(new PropertyValueFactory<>("description"));
        locationCol.setCellValueFactory(new PropertyValueFactory<>("location"));
        contactNameCol.setCellValueFactory(new PropertyValueFactory<>("contactName"));
        contactIdCol.setCellValueFactory(new PropertyValueFactory<>("contactId"));
        typeCol.setCellValueFactory(new PropertyValueFactory<>("type"));
        startCol.setCellValueFactory(new PropertyValueFactory<>("startDT"));
        endCol.setCellValueFactory(new PropertyValueFactory<>("endDT"));
        customerIdCol.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        userIdCol.setCellValueFactory(new PropertyValueFactory<>("userId"));
    }

    /**
     * Navigates to the Reports Menu.
     * @param actionEvent Clicking the reports menu button.
     * @throws IOException In case of input or output exception.
     */
    public void onReportsBtn(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../view/ReportsMenu.fxml"));
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 280, 244);
        stage.setTitle("Reports Menu");
        stage.setScene(scene);
        stage.show();
    }
}
