package dao;

import helper.JDBC;
import helper.ListManager;
import model.User;
import java.sql.*;

/**
 * A class for working with the Users table in the database.
 * @author Julez Hudson
 */
public class UserDao {

    /**
     * Retrieves all rows from the Users table in the database and creates a User object for each then add this object
     * to the list for all users.
     */
    public static void populateUserList() {
        String sql = "select * from users";

        PreparedStatement ps;

        {
            try {
                ps = JDBC.getConnection().prepareStatement(sql);

                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    int userId = rs.getInt("User_ID");
                    String userName = rs.getString("User_Name");
                    String password = rs.getString("Password");

                    User newUser = new User(userId, userName, password);
                    ListManager.allUsers.add(newUser);
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }
}

