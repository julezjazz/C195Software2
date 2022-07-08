package dao;

import helper.JDBC;
import model.ListManager;
import model.User;

import java.sql.*;

public class UserDao {

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

    //DELETE sout test statements
    public static boolean verifyPassword(String username, String password) {
        for (User user : ListManager.allUsers) {
            if (user.getUserName().equals(username)){
                if (user.getPassword().equals(password)) {
                    return true;
                }
            }
        }
        return false;
    }
}

