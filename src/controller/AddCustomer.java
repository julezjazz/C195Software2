package controller;

import dao.CustomerDao;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Division;
import helper.ListManager;
import java.net.URL;
import java.util.ResourceBundle;
import static controller.LogIn.currentUser;

public class AddCustomer implements Initializable {
    public TextField nameTF;
    public TextField addressTF;
    public TextField postalCodeTF;
    public TextField phoneTF;
    public ComboBox countryCB;
    public ComboBox stateProvCB;

    public String customerName;
    public String address;
    public String postalCode;
    public String phone;
    public String createdBy;
    public int divisionId;
    public String divisionName;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        countryCB.setItems(ListManager.allCountryNames);
        stateProvCB.setItems(ListManager.usDivisionNames.sorted());
    }
    public void onSelectCountry(ActionEvent actionEvent) throws Exception {
        String countrySelection = countryCB.getSelectionModel().getSelectedItem().toString();
        if(countrySelection.equals("U.S")) {
            stateProvCB.setItems(ListManager.usDivisionNames.sorted());
        }
        if(countrySelection.equals("UK")) {
            stateProvCB.setItems(ListManager.ukDivisionNames.sorted());
        }
        if(countrySelection.equals("Canada")) {
            stateProvCB.setItems(ListManager.canadaDivisionNames.sorted());
        }
    }
    public void onSaveReturnBtn(ActionEvent actionEvent) throws Exception {
        customerName = nameTF.getText();
        address = addressTF.getText();
        postalCode = postalCodeTF.getText();
        phone = phoneTF.getText();
        createdBy = currentUser;
        divisionName = stateProvCB.getSelectionModel().getSelectedItem().toString();

        for(Division division : ListManager.allDivisions) {
            if (division.getDivisionName().equals(divisionName)){
                divisionId = division.getDivisionId();
            }
        }
        CustomerDao.insert(customerName, address, postalCode, phone, createdBy, divisionId);

        Parent root = FXMLLoader.load(getClass().getResource("../view/Customers.fxml"));
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 1000, 600);
        stage.setTitle("Customers");
        stage.setScene(scene);
        stage.show();
    }
    public void onCancelBtn(ActionEvent actionEvent) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("../view/Customers.fxml"));
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 1000, 600);
        stage.setTitle("Customers");
        stage.setScene(scene);
        stage.show();
    }
}
