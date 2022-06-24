package dao;

import helper.JDBC;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Contact;
import model.ListManager;


import java.sql.*;

public class ContactDao {
    public static void populateContactList() {

        String sql = "select * from contacts";

        PreparedStatement ps;

        {
            try {
                ps = JDBC.getConnection().prepareStatement(sql);

                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    int contactId = rs.getInt("Contact_ID");
                    String contactName = rs.getString("Contact_Name");
                    Contact newContact = new Contact(contactId, contactName);
                    ListManager.allContacts.add(newContact);
                }

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

        }
    }
/* This doesn't work.
        public static void printContacts () {
            for (Contact contacts : ListManager.getAllContacts()) {
                System.out.println(contacts.getContactName());
            }
        }
  */
}


