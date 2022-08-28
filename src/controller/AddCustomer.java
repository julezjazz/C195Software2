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
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Division;
import helper.ListManager;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import static controller.LogIn.currentUser;

/**
 * A class for controlling <code>../view/AddCustomer.fxml</code>.
 * @author Julez Hudson
 */
public class AddCustomer implements Initializable {
    public TextField nameTF;
    public TextField addressTF;
    public TextField postalCodeTF;
    public TextField phoneTF;
    public ComboBox countryCB;
    public ComboBox stateProvCB;
    public Button clearBtn;

    public String customerName;
    public String address;
    public String postalCode;
    public String phone;
    public String createdBy;
    public int divisionId;
    public String divisionName;

    /**
     * Sets combo box with list of country names and uses lambda expression to clear the entire form when the clear
     * button is clicked. Lambda is used to avoid writing a complete, separate method.
      * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        countryCB.setItems(ListManager.allCountryNames);

        clearBtn.setOnAction(e ->{
            nameTF.clear();
            addressTF.clear();
            postalCodeTF.clear();
            phoneTF.clear();
            countryCB.setValue(null);
            stateProvCB.setValue(null);
        });
    }

    /**
     * Sets combo box for division info with list based on selected country. If the country combo box is set to null,
     * this method returns without making any changes.
     */
    public void onSelectCountry() {
        if (countryCB.getSelectionModel().getSelectedItem() == null){
            return;
        }
        String countrySelection = countryCB.getSelectionModel().getSelectedItem().toString();
        int countryId = NameIdConversion.returnCountryID(countrySelection);
        stateProvCB.setItems(ListMaker.populateDivisionsBySelectCountry(countryId));
    }

    /**
     * Adds a new customer to the Customer table of the database. User input is collected and used to add this new
     * customer. This method then navigates to the main customers page.
     * @param actionEvent Clicking the save and return button.
     * @throws Exception In case of an input or output or sql error.
     */
    public void onSaveReturnBtn(ActionEvent actionEvent) throws Exception {
        customerName = nameTF.getText();
        address = addressTF.getText();
        postalCode = postalCodeTF.getText();
        phone = phoneTF.getText();
        createdBy = currentUser;
        divisionName = stateProvCB.getSelectionModel().getSelectedItem().toString();

        divisionId = NameIdConversion.convertDivNameToId(divisionName);

        CustomerDao.insert(customerName, address, postalCode, phone, createdBy, divisionId);

        Parent root = FXMLLoader.load(getClass().getResource("../view/Customers.fxml"));
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 1000, 600);
        stage.setTitle("Customers");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Navigates to the main customers page.
     * @param actionEvent Clicking the cancel button.
     * @throws IOException In the case of an input or output exception.
     */
    public void onCancelBtn(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../view/Customers.fxml"));
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 1000, 600);
        stage.setTitle("Customers");
        stage.setScene(scene);
        stage.show();
    }
}
