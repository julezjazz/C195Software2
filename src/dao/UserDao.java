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

    public static boolean verifyLogIn (String username, String password) {
        //String sql = "select * from users" ;
        //tried "select * from users where User_Name = ?" and ps.setInt, but it didn't work.


        //PreparedStatement ps;

        {
            try {
                PreparedStatement ps = JDBC.getConnection().prepareStatement("select * from users where User_Name = '?'");
                ps.setString(1, username);
                //ps = JDBC.getConnection().prepareStatement(sql);
                 //ps = JDBC.getConnection().prepareStatement("select * from users where User_Name = ?")
                 //ps.setInt(1, username);

                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    if (password == rs.getString("Password")){
                        System.out.println("SUCCESS!");
                        return true;
                    }
                    //if (username == rs.getString("User_Name")) {
                      //  if (password == rs.getString("Password")) {
                        //    System.out.println("SUCCESS!");
                          //  return true;
                        }


            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

        }
        System.out.println("FAIL");
        return false;
    }
}

