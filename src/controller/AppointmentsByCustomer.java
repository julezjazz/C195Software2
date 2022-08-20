package controller;

import helper.ListMaker;
import helper.ListManager;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;

import java.net.URL;
import java.util.ResourceBundle;

public class AppointmentsByCustomer implements Initializable {
    public ComboBox customerCB;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        customerCB.setItems(ListMaker.populateAllCustomerNames());
    }

    public void onSelectContact(ActionEvent actionEvent) {
    }
}
