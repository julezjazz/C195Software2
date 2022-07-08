package controller;

import dao.UserDao;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import main.Main;
import model.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

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

    public void onLogInBtn(ActionEvent actionEvent) {
        tempUsername = userNameTxt.getText();
        tempPassword = passwordTxt.getText();

        //Delete test sout statements
        if (UserDao.verifyPassword(tempUsername, tempPassword) == true) {
            System.out.println("YOU SOLVED IT");
        } else {
            errorLbl.setText(Main.rb.getString("ErrorMessage"));
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


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            userNameLbl.setText(Main.rb.getString("Username"));
            logInBtn.setText(Main.rb.getString("Login"));
            passwordLbl.setText(Main.rb.getString("Password"));
            locationLbl.setText(Locale.getDefault().getDisplayCountry());




        } catch (NullPointerException e) {

        }
    }
}
