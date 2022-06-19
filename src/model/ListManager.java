package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
/** Creates and manages lists for other class objects.*/
public class ListManager {
    /** List for all objects representing customers. */
    public ObservableList<Customer> allCustomers = FXCollections.observableArrayList();
    /** List for all objects representing appointments. */
    public ObservableList<Appointment> allAppointments = FXCollections.observableArrayList();
    /** List for all objects representing countries. */
    public ObservableList<Country> allCountries = FXCollections.observableArrayList();
    /** List for all objects representing first divisions of countries. */
    public ObservableList<Division> allDivisions = FXCollections.observableArrayList();
    /** List for all objects representing users. */
    public ObservableList<User> allUsers = FXCollections.observableArrayList();
    /** List for all objects representing contacts for appointments. */
    public ObservableList<Contact> allContacts = FXCollections.observableArrayList();
}
