package dao;

import helper.JDBC;
import model.Country;
import helper.ListManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * A class for working with the Countries table in the database.
 * @author Julez Hudson
 */
public class CountryDao {

    /**
     * Retrieves all rows from the Countries table of the database and creates a Country object for each then adds each
     * to the list for all contacts.
     */
    public static void populateCountryLists() {

        String sql = "select * from countries";

        PreparedStatement ps;

        {
            try {
                ps = JDBC.getConnection().prepareStatement(sql);

                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    int countryId = rs.getInt("Country_ID");
                    String countryName = rs.getString("Country");
                    Country newCountry = new Country(countryId, countryName);
                    ListManager.allCountries.add(newCountry);
                    ListManager.allCountryNames.add(countryName);
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }
}