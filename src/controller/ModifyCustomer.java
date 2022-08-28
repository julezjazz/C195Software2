package controller;

import dao.CustomerDao;
import helper.ListMaker;
import helper.NameIdConversion;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import helper.ListManager;
import static controller.Customers.*;
import static controller.LogIn.currentUser;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * A class for controlling <code>../view/ModifyCustomer.fxml</code>.
 * @author Julez Hudson
 */
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

    /**
     * Fills all the text fields and combo boxes based on the customer that was selected from the
     * Customers page.
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        customerIdTF.setText(String.valueOf(selectedCustomer.getCustomerId()));
        nameTF.setText(selectedCustomer.getCustomerName());
        addressTF.setText(selectedCustomer.getAddress());
        postalCodeTF.setText(selectedCustomer.getPostalCode());
        phoneTF.setText(selectedCustomer.getPhone());
        divisionId = selectedCustomer.getDivisionId();
        divisionName = NameIdConversion.convertDivIdToName(divisionId);
        int countryId = NameIdConversion.convertDivIdToCountryId(divisionId);
        String countryName = NameIdConversion.convertCountryIdToName(countryId);
        countryCB.setItems(ListManager.allCountryNames.sorted());
        countryCB.getSelectionModel().select(countryName);
        stateProvCB.setItems(ListMaker.populateDivisionsBySelectCountry(countryId));
        stateProvCB.getSelectionModel().select(divisionName);
    }
    public void onSelectCountry() {
        if (countryCB.getSelectionModel().getSelectedItem() == null){
            return;
        }
        String countrySelection = countryCB.getSelectionModel().getSelectedItem().toString();
        int countryId = NameIdConversion.returnCountryID(countrySelection);
        stateProvCB.setItems(ListMaker.populateDivisionsBySelectCountry(countryId));
    }
    public void onSaveReturnBtn(ActionEvent actionEvent) throws Exception {
        customerId = selectedCustomer.getCustomerId();
        customerName = nameTF.getText();
        address = addressTF.getText();
        postalCode = postalCodeTF.getText();
        phone = phoneTF.getText();
        updatedBy = currentUser;
        divisionName = stateProvCB.getSelectionModel().getSelectedItem().toString();
        divisionId = NameIdConversion.convertDivNameToId(divisionName);
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
