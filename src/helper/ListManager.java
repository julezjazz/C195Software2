package helper;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.*;

/**
 *  A class for storing lists.
 *  @author Julez Hudson
 */
public class ListManager {

    public static ObservableList<Country> allCountries = FXCollections.observableArrayList();
    public static ObservableList<String> allCountryNames = FXCollections.observableArrayList();

    public static ObservableList<Appointment> appointmentsByMonth = FXCollections.observableArrayList();
    public static ObservableList<Appointment> appointmentsByWeek = FXCollections.observableArrayList();
    public static ObservableList<Appointment> appointmentsByCustomer = FXCollections.observableArrayList();
    public static ObservableList<Appointment> contactSchedule = FXCollections.observableArrayList();

    public static ObservableList<String> allCustomerNames = FXCollections.observableArrayList();

    public static ObservableList<Division> allDivisions = FXCollections.observableArrayList();
    public static ObservableList<String> divisionsBySelectCountry = FXCollections.observableArrayList();

    public static ObservableList<User> allUsers = FXCollections.observableArrayList();

    public static ObservableList<Contact> allContacts = FXCollections.observableArrayList();
    public static ObservableList<String> allContactNames = FXCollections.observableArrayList();

    public static ObservableList<String> allAppointmentTypes = FXCollections.observableArrayList();

    public static ObservableList<String> hours = FXCollections.observableArrayList("00", "01", "02", "04", "05",
            "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23");
    public static ObservableList<String> minutes = FXCollections.observableArrayList("00", "15", "30", "45");
}

