package controller;

import dao.UserDao;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import model.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


public class LogIn {

    public TextField userNameTxt;
    public TextField passwordTxt;
    public Button logInBtn;
    public Label locationLbl;

    public String tempUsername;
    public String tempPassword;

    public void onLogInBtn(ActionEvent actionEvent) {
        tempUsername = userNameTxt.getText();
        tempPassword = passwordTxt.getText();

        UserDao.verifyLogIn(tempUsername, tempPassword);




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

}
