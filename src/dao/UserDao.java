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

                    User newUser = new User(userId, userName);
                    ListManager.allUsers.add(newUser);
                }

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

        }
    }

    public static boolean verifyLogIn (String username, String password) {
        String sql = "select * from users" ;

        PreparedStatement ps;

        {
            try {
                ps = JDBC.getConnection().prepareStatement(sql);

                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    if (username == rs.getString("User_Name")) {
                        if (password == rs.getString("Password")) {
                            System.out.println("SUCCESS!");
                            return true;
                        }
                    }

                }

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

        }
        System.out.println("Failure.");
        return false;
    }
}

