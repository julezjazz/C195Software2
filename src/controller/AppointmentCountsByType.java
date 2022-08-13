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
    public void onReportsBtn(ActionEvent actionEvent) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("../view/ReportsMenu.fxml"));
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 280, 244);
        stage.setTitle("Reports Menu");
        stage.setScene(scene);
        stage.show();
    }
    public void onSelectType(ActionEvent actionEvent) {

    }
}
