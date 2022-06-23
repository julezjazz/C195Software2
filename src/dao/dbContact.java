package dao;

import helper.JDBC;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Contact;
import model.ListManager;


import java.sql.*;

public class dbContact {


    String sql = "select * from contacts";

    PreparedStatement ps;

    {
        try {
            ps = JDBC.getConnection().prepareStatement(sql);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int contactId = rs.getInt("Contact_ID");
                String contactName = rs.getString("Contact_Name");
                // Do I need email? Can I just delete it from Contact class and from here?
                String email = rs.getString("Email");

                Contact newContact = new Contact(contactId, contactName, email);
                ListManager.allContacts.add(newContact);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }
// This doesn't work.
    {
        for (Contact contacts : ListManager.getAllContacts()) {
            System.out.println(contacts.getContactName());
        }
    }
}
