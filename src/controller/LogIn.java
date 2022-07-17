package controller;

import dao.UserDao;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import main.Main;
import model.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.IOException;
import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;


public class LogIn implements Initializable {


    public TextField userNameTxt;
    public TextField passwordTxt;
    public Button logInBtn;
    public Label locationLbl;
    public Label userNameLbl;
    public Label passwordLbl;
    public Label errorLbl;

    public String tempUsername;
    public String tempPassword;

    public static String currentUser;

    public void onLogInBtn(ActionEvent actionEvent) throws IOException {
        tempUsername = userNameTxt.getText();
        tempPassword = passwordTxt.getText();


        if (UserDao.verifyPassword(tempUsername, tempPassword)) {
            tempPassword = " ";
            currentUser = tempUsername;

            Parent root = FXMLLoader.load(getClass().getResource("../view/AppointmentAlert.fxml"));
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            Scene scene = new Scene(root, 350, 300);
            stage.setTitle("Upcoming Appointment");
            stage.setScene(scene);
            stage.show();
        } else {
            errorLbl.setText(Main.rb.getString("ErrorMessage"));
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            userNameLbl.setText(Main.rb.getString("Username"));
            logInBtn.setText(Main.rb.getString("Login"));
            passwordLbl.setText(Main.rb.getString("Password"));
            locationLbl.setText(Locale.getDefault().getDisplayCountry());



    //I'M UNSURE of what should be done with this catch, since the text should not be null, as it's not being provided by the user.
        } catch (NullPointerException e) {

        }
    }

    // DELETE test for variable System.out.println(tempUsername);
       /* //DELETE Testing  for (User users : ListManager.getAllUsers()) {
           System.out.println(users.getUserName());
        }
        for (Contact contacts : ListManager.getAllContacts()) {
            System.out.println(contacts.getContactName());
        }
        for (Country countries : ListManager.getAllCountries()) {
            System.out.println(countries.getCountryName());
        }
        for (Division division : ListManager.getAllDivisions()) {
            System.out.println(division.getDivisionName());
        } */

}
