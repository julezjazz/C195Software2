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
import helper.ListManager;

import java.net.URL;
import java.util.ResourceBundle;

public class ContactSchedule implements Initializable {

    public ComboBox contactCB;
    public TableView scheduleTable;
    public TableColumn appointmentIdCol;
    public TableColumn titleCol;
    public TableColumn descriptionCol;
    public TableColumn typeCol;
    public TableColumn startCol;
    public TableColumn endCol;
    public TableColumn customerIdCol;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        contactCB.setItems(ListManager.allContactNames);
    }
    public void onSelectContact(ActionEvent actionEvent) {
        String contactName = contactCB.getSelectionModel().getSelectedItem().toString();
        int contactId = NameIdConversion.returnContactID(contactName);
        scheduleTable.setItems(ListMaker.populateContactSchedule(contactId));

        appointmentIdCol.setCellValueFactory(new PropertyValueFactory<>("appointmentId"));
        titleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
        descriptionCol.setCellValueFactory(new PropertyValueFactory<>("description"));
        typeCol.setCellValueFactory(new PropertyValueFactory<>("type"));
        startCol.setCellValueFactory(new PropertyValueFactory<>("startDT"));
        endCol.setCellValueFactory(new PropertyValueFactory<>("endDT"));
        customerIdCol.setCellValueFactory(new PropertyValueFactory<>("customerId"));
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
