package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ListManager {
    public ObservableList<Customer> allCustomers = FXCollections.observableArrayList();
    public ObservableList<Appointment> allAppointments = FXCollections.observableArrayList();
    public ObservableList<Country> allCountries = FXCollections.observableArrayList();
    public ObservableList<Division> allDivisions = FXCollections.observableArrayList();
    public ObservableList<User> allUsers = FXCollections.observableArrayList();
    public ObservableList<Contact> allContacts = FXCollections.observableArrayList();
}
