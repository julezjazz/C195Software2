package controller;

import dao.AppointmentDao;
import helper.ListMaker;
import helper.ListManager;
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
import java.time.Month;
import java.util.ResourceBundle;

/**
 * A class for controlling <code>../view/AppointmentCountsByType.fxml</code>.
 * @author Julez Hudson
 */
public class AppointmentCountsByType implements Initializable {
    public ComboBox appointmentTypeCB;
    public ComboBox appointmentMonthCB;
    public Text countReport;

    public Month selectedMonth;
    public String type;

    /**
     * Sets the text for count report to blank and sets the appointment type combo box to the list of appointment types.
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        countReport.setText("");
        appointmentTypeCB.setItems(ListMaker.populateAppointmentTypes());
        appointmentMonthCB.setItems(ListManager.months);
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
     * Sets selectedMonth variable to the month chosen by the user.
     */
    public void onSelectMonth(){
        selectedMonth = (Month) appointmentMonthCB.getSelectionModel().getSelectedItem();
    }

    /**
     * Sets type variable to the type selected by the user.
     */
    public void onSelectType() {
        type = appointmentTypeCB.getSelectionModel().getSelectedItem().toString();
    }

    /**
     * Sets text to give the number of appointments, within the selected month, of the type selected by the user.
     */
    public void onGenerateBtn() {
        if (selectedMonth == null) {
            countReport.setText("Please select a month.");
            return;
        }
        if (type == null) {
            countReport.setText("Please select a type.");
            return;
        }
        int appointmentCount = 0;
        for (Appointment appointment : AppointmentDao.populateAppointmentList()) {
            if (appointment.getStartDT().getMonth().equals(selectedMonth)){
                if (appointment.getType().equals(type)){
                    appointmentCount = appointmentCount + 1;
                }
            }
        }
        if(appointmentCount == 1){
            countReport.setText("There is 1 appointment with Type: " + type + " in " + selectedMonth.toString() +
                    ".");
        }
        else {
            countReport.setText("There are " + appointmentCount + " appointments with Type: " + type + " in " +
                    selectedMonth.toString() +".");
        }
    }
}
