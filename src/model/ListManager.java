package model;

import dao.ContactDao;
import dao.CountryDao;
import dao.DivisionDao;
import dao.UserDao;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
/** Creates and manages lists for other class objects.*/
public class ListManager {
   // /** List for all objects representing customers. */
    //public ObservableList<Customer> allCustomers = FXCollections.observableArrayList();
    ///** List for all objects representing appointments. */
    //public ObservableList<Appointment> allAppointments = FXCollections.observableArrayList();
    /** List for all objects representing countries. */
    public static ObservableList<Country> allCountries = FXCollections.observableArrayList();
    /** List for all objects representing first divisions of countries. */
    public static ObservableList<Division> allDivisions = FXCollections.observableArrayList();
    /** List for all objects representing users. */
    public static ObservableList<User> allUsers = FXCollections.observableArrayList();
    /** List for all objects representing contacts for appointments. */
    public static ObservableList<Contact> allContacts = FXCollections.observableArrayList();

   // /** Adds new customer to list of customers. */
    //public void addCustomer(Customer newCustomer) {allCustomers.add(newCustomer);}
    ///** Adds new appointment to list of appointments. */
    //public void addAppointment(Appointment newAppointment) {allAppointments.add(newAppointment);}

    ///** Outputs list of all customers.*/
    //public ObservableList<Customer> getAllCustomers(){return allCustomers;}
    ///**Outputs list of all appointments. */
    //public ObservableList<Appointment> getAllAppointments(){return allAppointments;}



  //  /** Outputs list of all users. */
    //public static ObservableList<User> getAllUsers(){
      //  allUsers.clear();
        //UserDao.populateUserList();
        //return allUsers;
    //}
//    /** Outputs list of all contacts. */
  //  public static ObservableList<Contact> getAllContacts(){
    //    allContacts.clear();
      //  ContactDao.populateContactList();
        //return allContacts;
    //}
//    /** Outputs list of all countries. */
  //  public static ObservableList<Country> getAllCountries() {
    //    allCountries.clear();
      //  CountryDao.populateCountryList();
        //return allCountries;
    //}
//    /** Outputs list of all divisions. */
  //  public static ObservableList<Division> getAllDivisions() {
    //    allDivisions.clear();
      // DivisionDao.populateDivisionList();
      //  return allDivisions;
    //}
}

