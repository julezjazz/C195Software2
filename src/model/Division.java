package model;
/** Represents a first level division of a country. */
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
    /** Setter for name of division. */
    public void setDivisionName(String divisionName) {
        this.divisionName = divisionName;
    }
    /** Setter for countryId */
    public void setCountryId(int countryId) {
        this.countryId = countryId;
    }

    /** Getter for division ID. */
    public int getDivisionId() {
        return divisionId;
    }
    /** Getter for name of division. */
    public String getDivisionName() {
        return divisionName;
    }
    /** Getter for country ID */
    public int getCountryId() {
        return countryId;
    }
}
