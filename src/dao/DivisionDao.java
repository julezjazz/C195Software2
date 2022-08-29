package dao;

import helper.JDBC;
import model.Division;
import helper.ListManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * A class for working with the First_Level_Divisions table in the database.
 * @author Julez Hudson
 */
public class DivisionDao {

    /**
     * Retrieves all rows from the First_Level_Divisions table of the database and creates a Division object for each
     * then adds this object to the list for all divisions.
     */
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
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }
}
