package main;

import helper.JDBC;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Locale;
import java.util.ResourceBundle;


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
        JDBC.openConnection();
      /*  ResourceBundle rb = ResourceBundle.getBundle("main/Lang", Locale.getDefault());
        if(Locale.getDefault().getLanguage().equals("fr")){
            System.out.println(rb.getString());
        } */
        launch(args);
        JDBC.closeConnection();
    }
}
