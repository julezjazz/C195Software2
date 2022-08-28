package controller;

import helper.ListMaker;
import helper.NameIdConversion;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * A class for controlling <code>../view/AppointmentsByCustomer.fxml</code>.
 * @author Julez Hudson
 */
public class AppointmentsByCustomer implements Initializable {
    public ComboBox customerCB;
    public TableView appointmentTable;
    public TableColumn appointmentIdCol;
    public TableColumn titleCol;
    public TableColumn descriptionCol;
    public TableColumn typeCol;
    public TableColumn startCol;
    public TableColumn endCol;
    public TableColumn contactIdCol;

    /**
     * Fills the customer combo box with the list of all customer names.
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        customerCB.setItems(ListMaker.populateAllCustomerNames());
    }

    /**
     * Fills the appointment table with a list of appointments by customer based on the selected customer.
     */
    public void onSelectCustomer() {
        String customerName = customerCB.getSelectionModel().getSelectedItem().toString();
        int customerId = NameIdConversion.returnCustomerID(customerName);
        appointmentTable.setItems(ListMaker.populateAppointmentsByCustomers(customerId));

        appointmentIdCol.setCellValueFactory(new PropertyValueFactory<>("appointmentId"));
        titleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
        descriptionCol.setCellValueFactory(new PropertyValueFactory<>("description"));
        typeCol.setCellValueFactory(new PropertyValueFactory<>("type"));
        startCol.setCellValueFactory(new PropertyValueFactory<>("startDT"));
        endCol.setCellValueFactory(new PropertyValueFactory<>("endDT"));
        contactIdCol.setCellValueFactory(new PropertyValueFactory<>("contactId"));
    }

    /**
     * Navigates to the reports menu.
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
