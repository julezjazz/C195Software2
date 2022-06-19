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

    /** Adds new customer to list of customers. */
    public void addCustomer(Customer newCustomer) {allCustomers.add(newCustomer);}
    /** Adds new appointment to list of appointments. */
    public void addAppointment(Appointment newAppointment) {allAppointments.add(newAppointment);}

    /** Outputs list of all customers.*/
    public ObservableList<Customer> getAllCustomers(){return allCustomers;}
    /**Outputs list of all appointments. */
    public ObservableList<Appointment> getAllAppointments(){return allAppointments;}


}

