package model;
/** Represents a country. */
public class Country {
    private int countryId;
    private String countryName;
    /** Class constructor. */
    public Country(int countryId, String countryName) {
        this.countryId = countryId;
        this.countryName = countryName;
    }
    /** Setter for country ID. */
    public void setCountryId(int countryId) {
        this.countryId = countryId;
    }
    /** Setter for name of country. */
    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }
    /** Getter for country ID. */
    public int getCountryId() {
        return countryId;
    }
    /** Getter for name of country.  */
    public String getCountryName() {
        return countryName;
    }
}
