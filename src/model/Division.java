package model;
/**
 * A class to represent a first level division of a country where a customer could reside.
 * @author Julez Hudson
 */
public class Division {

    private int divisionId;
    private String divisionName;
    private int countryId;
    /** Class constructor. */
    public Division(int divisionId, String divisionName, int countryId){
        this.divisionId = divisionId;
        this.divisionName = divisionName;
        this.countryId = countryId;
    }
    /** Setter for division ID */
    public void setDivisionId(int divisionId) {
        this.divisionId = divisionId;
    }
    /** Setter for name of first level division. */
    public void setDivisionName(String divisionName) {
        this.divisionName = divisionName;
    }
    /** Setter for the ID number of the country where the first-level division is located. */
    public void setCountryId(int countryId) { this.countryId = countryId; }

    /** Getter for division ID. */
    public int getDivisionId() {
        return divisionId;
    }
    /** Getter for name of first level division. */
    public String getDivisionName() {
        return divisionName;
    }
    /** Getter for the ID number of the country where the first-level division is located. */
    public int getCountryId() {
        return countryId;
    }
}