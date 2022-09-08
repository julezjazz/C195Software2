package controller;

import helper.ListMaker;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.Appointment;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * A class for controlling <code>../view/AppointmentCountsByType.fxml</code>.
 * @author Julez Hudson
 */
public class AppointmentCountsByType implements Initializable {
    public ComboBox appointmentTypeCB;
    public Text countReport;

    /**
     * Sets the text for count report to blank and sets the appointment type combo box to the list of appointment types.
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        countReport.setText("");
        appointmentTypeCB.setItems(ListMaker.populateAppointmentTypes());
    }

    /**
     * Navigates to the Reports Menu page.
     * @param actionEvent Clicking on the reports menu button.
     * @throws IOException In Case of input or output exception.
     */
    public void onReportsBtn(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../view/ReportsMenu.fxml"));
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 280, 244);
        stage.setTitle("Reports Menu");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Sets text to give the number of appointments of the type, selected by the user, within that day and the next
     * 30 days.
     */
    public void onSelectType() {
        String type = appointmentTypeCB.getSelectionModel().getSelectedItem().toString();
        int appointmentCount = 0;
        for (Appointment appointment : dao.AppointmentDao.populateAppointmentList()) {
            if (appointment.getType().equals(type)){
                appointmentCount = appointmentCount + 1;
            }
        }
        if(appointmentCount == 1){
            countReport.setText("There is 1 appointment with Type: " + type + ".");
        }
        else {
            countReport.setText("There are " + appointmentCount + " appointments with Type: " + type + ".");
        }
    }
}
