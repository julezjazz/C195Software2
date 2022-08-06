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
import model.Customer;
import model.Division;
import model.ListManager;

import static controller.Customers.*;
import static controller.LogIn.currentUser;


import java.net.URL;
import java.util.ResourceBundle;

public class ModifyCustomer implements Initializable {

    public TextField customerIdTF;
    public TextField nameTF;
    public TextField addressTF;
    public TextField postalCodeTF;
    public TextField phoneTF;
    public ComboBox countryCB;
    public ComboBox stateProvCB;

    public int customerId;
    public String customerName;
    public String address;
    public String postalCode;
    public String phone;
    public String updatedBy;
    public int divisionId;
    public String divisionName;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        customerIdTF.setText(String.valueOf(selectedCustomer.getCustomerId()));
        nameTF.setText(selectedCustomer.getCustomerName());
        addressTF.setText(selectedCustomer.getAddress());
        postalCodeTF.setText(selectedCustomer.getPostalCode());
        phoneTF.setText(selectedCustomer.getPhone());
        countryCB.setItems(ListManager.allCountryNames);

        if(selectedCustomer.getDivisionId() < 55){
            stateProvCB.setItems(ListManager.usDivisionNames.sorted());
            stateProvCB.getSelectionModel().select(selectedCustomer.getDivision());
            countryCB.getSelectionModel().select("U.S");
        }
        if(selectedCustomer.getDivisionId() >= 60){
            if(selectedCustomer.getDivisionId() <= 72) {
                stateProvCB.setItems(ListManager.canadaDivisionNames.sorted());
                stateProvCB.getSelectionModel().select(selectedCustomer.getDivision());
                countryCB.getSelectionModel().select("Canada");
            }
        }
        if(selectedCustomer.getDivisionId() >= 101){
            if(selectedCustomer.getDivisionId() <= 104) {
                stateProvCB.setItems(ListManager.ukDivisionNames.sorted());
                stateProvCB.getSelectionModel().select(selectedCustomer.getDivision());
                countryCB.getSelectionModel().select("UK");
            }
        }
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

        customerId = selectedCustomer.getCustomerId();
        customerName = nameTF.getText();
        address = addressTF.getText();
        postalCode = postalCodeTF.getText();
        phone = phoneTF.getText();
        updatedBy = currentUser;
        divisionName = stateProvCB.getSelectionModel().getSelectedItem().toString();

        for(Division division : ListManager.allDivisions) {
            if (division.getDivisionName().equals(divisionName)){
                divisionId = division.getDivisionId();
            }
        }

        CustomerDao.update(customerName, address, postalCode, phone, updatedBy, divisionId, customerId);

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
