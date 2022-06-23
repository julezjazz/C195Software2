package dao;

import helper.JDBC;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Contact;
import model.ListManager;
import model.User;

import java.sql.*;

public class dbContact {

    /** List for all objects representing contacts for appointments. */
    public ObservableList<Contact> allContacts = FXCollections.observableArrayList();

    String sql = "select * from contacts";

    PreparedStatement ps;

    {
        try {
            ps = JDBC.getConnection().prepareStatement(sql);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int contactId = rs.getInt("Contact_ID");
                String contactName = rs.getString("Contact_Name");
                String email = rs.getString("Email");

                Contact newContact = new Contact(contactId, contactName, email);
                allContacts.add(newContact);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }
}
