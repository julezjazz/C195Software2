package dao;

import helper.JDBC;
import model.Division;
import model.ListManager;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DivisionDao {
   /* public static void populateDivisionList() {

        String sql = "select * from first-level divisions";

        PreparedStatement ps;

        {
            try {
                ps = JDBC.getConnection().prepareStatement(sql);

                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    int divisionId = rs.getInt("Division_ID");
                    String divisionName = rs.getString("Division");
                    int countryId = rs.getInt("Country_ID");
                    Division newDivision = new Division(divisionId, divisionName, countryId);
                    ListManager.allDivisions.add(newDivision);
                }

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

        }
    }*/
}
