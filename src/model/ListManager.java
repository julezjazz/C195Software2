package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
/** Creates and manages lists for other class objects.*/
public class ListManager {
    /** Creates list of all objects representing customers. */
    public ObservableList<Customer> allCustomers = FXCollections.observableArrayList();
    /** Creates list of all objects representing appointments. */
    public ObservableList<Appointment> allAppointments = FXCollections.observableArrayList();
    /** Creates list of all objects representing countries. */
    public ObservableList<Country> allCountries = FXCollections.observableArrayList();
    /** Creates list of all objects representing first divisions of countries. */
    public ObservableList<Division> allDivisions = FXCollections.observableArrayList();
    /** Creates list of all objects representing users. */
    public ObservableList<User> allUsers = FXCollections.observableArrayList();
    /** Creates list of all objects representing contacts for appointments. */
    public ObservableList<Contact> allContacts = FXCollections.observableArrayList();
}
