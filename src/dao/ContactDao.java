package dao;

import helper.JDBC;
import model.Contact;
import helper.ListManager;
import java.sql.*;

/**
 * A class for working with the Contacts table in the database.
 * @author Julez Hudson
 */
public class ContactDao {

    /**
     * Retrieves all rows from the Contacts table of the database and creates a Contact object for each then adds each
     * to the list for all contacts.
     */
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
                    ListManager.allContactNames.add(contactName);
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }
}


