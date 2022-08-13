package controller;

import helper.ListMaker;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

public class AppointmentCountsByType implements Initializable {
    public ComboBox appointmentTypeCB;
    public Text countReport;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        countReport.setText("");
        appointmentTypeCB.setItems(ListMaker.populateAppointmentTypes());
    }
}
