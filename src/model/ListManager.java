package model;

import dao.ContactDao;
import dao.CountryDao;
import dao.DivisionDao;
import dao.UserDao;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
/** Creates and manages lists for other class objects.*/
public class ListManager {


    /** List for all objects representing countries. */
    public static ObservableList<Country> allCountries = FXCollections.observableArrayList();
    public static ObservableList<String> allCountryNames = FXCollections.observableArrayList();
    /** List for all objects representing first divisions of countries. */
    public static ObservableList<Division> allDivisions = FXCollections.observableArrayList();
    /** List for all objects representing users. */
    public static ObservableList<User> allUsers = FXCollections.observableArrayList();
    /** List for all objects representing contacts for appointments. */
    public static ObservableList<Contact> allContacts = FXCollections.observableArrayList();




}

