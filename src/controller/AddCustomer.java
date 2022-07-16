package controller;

import dao.CustomerDao;
import helper.JDBC;
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

import java.net.URL;
import java.util.ResourceBundle;

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
    public int divisionId;
    public String divisionName;


    public void onCountrySelect(ActionEvent actionEvent) throws Exception {
        String countrySelection = countryCB.getSelectionModel().getSelectedItem().toString();
        if(countrySelection.equals("U.S")) {
            stateProvCB.setItems(ListManager.usDivisionNames.sorted());
            stateProvCB.getSelectionModel().selectFirst();
        }
        if(countrySelection.equals("UK")) {
            stateProvCB.setItems(ListManager.ukDivisionNames.sorted());
            stateProvCB.getSelectionModel().selectFirst();
        }
        if(countrySelection.equals("Canada")) {
            stateProvCB.setItems(ListManager.canadaDivisionNames.sorted());
            stateProvCB.getSelectionModel().selectFirst();
        }
    }


    public void onSaveReturnBtn(ActionEvent actionEvent) throws Exception {

        customerName = nameTF.getText();
        address = addressTF.getText();
        postalCode = postalCodeTF.getText();
        phone = phoneTF.getText();
        divisionName = stateProvCB.getSelectionModel().getSelectedItem().toString();

        for(Division division : ListManager.allDivisions) {
            if (division.getDivisionName().equals(divisionName)){
                divisionId = division.getDivisionId();
            }
        }

        int rowsAffected = CustomerDao.insert(customerName, address, postalCode, phone, divisionId);

        if(rowsAffected > 0){
            System.out.println("insert successful");
        }
        else{
            System.out.println("Insert failed");
        }





        Parent root = FXMLLoader.load(getClass().getResource("../view/Home.fxml"));
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 1000, 600);
        stage.setTitle("Home");
        stage.setScene(scene);
        stage.show();
    }

    public void onCancelBtn(ActionEvent actionEvent) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("../view/Home.fxml"));
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 1000, 600);
        stage.setTitle("Home");
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        countryCB.setItems(ListManager.allCountryNames);
        countryCB.getSelectionModel().selectFirst();

        stateProvCB.setItems(ListManager.usDivisionNames.sorted());
        stateProvCB.getSelectionModel().selectFirst();

    }


}
