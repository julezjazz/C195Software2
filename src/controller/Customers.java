package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import dao.CustomerDao;
import model.Customer;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

/**
 * A class for controlling <code>../view/Customers.fxml</code>.
 * @author Julez Hudson
 */
public class Customers implements Initializable {
    public TableView customersTable;
    public TableColumn customerCol;
    public TableColumn nameCol;
    public TableColumn addressCol;
    public TableColumn stateCol;
    public TableColumn postalCol;
    public TableColumn phoneCol;
    public Text messageText;

    public static Customer selectedCustomer;

    /**
     * Fills the customer table view with a list of all customers and sets the display text to blank"
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        messageText.setText(" ");
        customersTable.setItems(CustomerDao.populateCustomerList());
        customerCol.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        nameCol.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        addressCol.setCellValueFactory(new PropertyValueFactory<>("address"));
        stateCol.setCellValueFactory(new PropertyValueFactory<>("divisionName"));
        postalCol.setCellValueFactory(new PropertyValueFactory<>("postalCode"));
        phoneCol.setCellValueFactory(new PropertyValueFactory<>("phone"));
    }

    /**
     * Navigates to the Add Customer page.
     * @param actionEvent Clicking the add customer button.
     * @throws IOException In case of input or output exception.
     */
    public void onAddCustomerBtn(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../view/AddCustomer.fxml"));
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 900, 600);
        stage.setTitle("Add Customer");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Navigates to the modify customer page. If no customer is selected, this method displays text alerting the user
     * to select a customer to modify and returns without navigating to another page.
     * @param actionEvent Clicking the modify customer button.
     * @throws IOException In case of an input or output exception.
     */
    public void onModCustomerBtn(ActionEvent actionEvent) throws IOException {
        selectedCustomer = (Customer) customersTable.getSelectionModel().getSelectedItem();

        if(selectedCustomer == null){
            messageText.setText("Please select a customer to modify.");
            return;
        }

        Parent root = FXMLLoader.load(getClass().getResource("../view/ModifyCustomer.fxml"));
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 900, 600);
        stage.setTitle("Modify Customer");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Deletes selected customer from the Customers table of the database and updates the table view to reflect the
     * change. If no customer is selected when the delete button is pressed, this method sets the display text to alert
     * the user that they need to select a customer to delete. Otherwise, this method sets the display text to notify
     * the user that customer has been deleted.
     * @throws SQLException In case of a sql exception.
     */
    public void onDelCustomerBtn() throws SQLException {
        selectedCustomer = (Customer) customersTable.getSelectionModel().getSelectedItem();

        if(selectedCustomer == null){
            messageText.setText("Please select a customer to delete.");
            return;
        }

        int customerId = selectedCustomer.getCustomerId();
        String customerName = selectedCustomer.getCustomerName();

        CustomerDao.delete(customerId);

        messageText.setText("Customer, " + customerName + ", has been deleted.");

        customersTable.setItems(CustomerDao.populateCustomerList());
        customerCol.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        nameCol.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        addressCol.setCellValueFactory(new PropertyValueFactory<>("address"));
        stateCol.setCellValueFactory(new PropertyValueFactory<>("divisionName"));
        postalCol.setCellValueFactory(new PropertyValueFactory<>("postalCode"));
        phoneCol.setCellValueFactory(new PropertyValueFactory<>("phone"));
    }

    /**
     * Navigates to the reports menu.
     * @param actionEvent Clicking the reports menu button.
     * @throws IOException In case of input or output exception.
     */
    public void onReportsBtn(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../view/ReportsMenu.fxml"));
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 280, 244);
        stage.setTitle("Reports Menu");
        stage.setScene(scene);
        stage.show();
    }
}