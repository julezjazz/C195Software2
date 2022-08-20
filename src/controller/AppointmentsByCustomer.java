package controller;

import helper.ListMaker;
import helper.ListManager;
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

import java.net.URL;
import java.util.ResourceBundle;

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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        customerCB.setItems(ListMaker.populateAllCustomerNames());
    }

    public void onSelectCustomer(ActionEvent actionEvent) {
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

    public void onReportsBtn(ActionEvent actionEvent) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("../view/ReportsMenu.fxml"));
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 280, 244);
        stage.setTitle("Reports Menu");
        stage.setScene(scene);
        stage.show();
    }
}
