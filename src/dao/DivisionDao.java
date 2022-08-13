package dao;

import helper.JDBC;
import model.Division;
import helper.ListManager;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DivisionDao {
   public static void populateDivisionList() {

        String sql = "select * from first_level_divisions";

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
                    if(countryId == 1) {
                        ListManager.usDivisionNames.add(divisionName);
                    }
                    if(countryId == 2) {
                        ListManager.ukDivisionNames.add(divisionName);
                    }
                    if(countryId == 3) {
                        ListManager.canadaDivisionNames.add(divisionName);
                    }
                }

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

        }
    }
}
