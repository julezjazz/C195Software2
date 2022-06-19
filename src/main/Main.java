package main;

import helper.JDBC;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import model.ListManager;
import model.User;

import java.sql.*;

/** This class creates an app that displays screens. */
public class Main extends Application {


    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("../view/LogIn.fxml"));
        primaryStage.setTitle("Log In");
        primaryStage.setScene(new Scene(root, 500, 400));
        primaryStage.show();

    }


    public static void main(String[] args) {
        launch(args);
        JDBC.openConnection();

        String sql = "select * from users";

        PreparedStatement ps;

        {
            try {
                ps = JDBC.getConnection().prepareStatement(sql);

                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    int userId = rs.getInt("User_ID");
                    String userName = rs.getString("User_Name");
                    String password = rs.getString("Password");
                    User newUser = new User(userId, userName, password);
                    ListManager.allUsers.add(newUser);
                }

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
/* Test to make sure data is being collected. DELETE LATER
            for (User i : ListManager.allUsers){
                System.out.println(i.getUserName());
            }

 */

        }


        JDBC.closeConnection();

    }
}
