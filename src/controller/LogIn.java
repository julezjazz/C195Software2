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

import java.io.*;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * A class for controlling <code>../view/Login.fxml</code>.
 * @author Julez Hudson
 */
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

    PrintWriter pwLogInActivity = new PrintWriter(new FileOutputStream("login_activity.txt", true));

    /**
     * @throws FileNotFoundException In case of the login activity text file not being found.
     */
    public LogIn() throws FileNotFoundException {
    }

    /**
     * Sets all of the text based on the location and language of the user.
     * @param url
     * @param resourceBundle
     */
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

    /**
     * Verifies the username and password and records the time and the results of the login to the
     * <code>login_activity.txt</code> file. If the login is successful, this method saves the username for later use
     * and navigates to the Appointment Alert page. If the login fails, an error message displays in the language
     * of the user.
     * @param actionEvent Clicking the log in button.
     * @throws IOException In case of an input or output exception.
     */
    public void onLogInBtn(ActionEvent actionEvent) throws IOException {
        tempUsername = userNameTxt.getText();
        tempPassword = passwordTxt.getText();

        pwLogInActivity.append("Login attempt was made by " + tempUsername + " on " +
                LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")) + ". Attempt was ");

        if (UserDao.verifyPassword(tempUsername, tempPassword)) {
            tempPassword = " ";
            currentUser = tempUsername;
            pwLogInActivity.append("successful.\n");
            pwLogInActivity.close();

            Parent root = FXMLLoader.load(getClass().getResource("../view/AppointmentAlert.fxml"));
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            Scene scene = new Scene(root, 350, 300);
            stage.setTitle("Upcoming Appointment");
            stage.setScene(scene);
            stage.show();
        } else {
            errorLbl.setText(Main.rb.getString("ErrorMessage"));
            pwLogInActivity.append("unsuccessful.\n");
        }
    }
}

