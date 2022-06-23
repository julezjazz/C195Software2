package dao;

import helper.JDBC;
import model.ListManager;
import model.User;

import java.sql.*;

public class dbUser {
//Need to go back in and redo this so that it doesn't store the password, etc
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

