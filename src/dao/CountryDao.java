package dao;

import helper.JDBC;
import model.Country;
import model.ListManager;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CountryDao {
    public static void populateCountryList() {

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
                }

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

        }
    }


}
