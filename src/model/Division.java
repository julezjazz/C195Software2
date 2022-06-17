package model;
/** Represents a first level division of a country. */
public class Division {

    private int divisionId;
    private String divisionName;
    /** Class constructor. */
    public Division(int divisionId, String divisionName){
        this.divisionId = divisionId;
        this.divisionName = divisionName;
    }
    /** Setter for division ID */
    public void setDivisionId(int divisionId) {
        this.divisionId = divisionId;
    }
    /** Setter for name of division. */
    public void setDivisionName(String divisionName) {
        this.divisionName = divisionName;
    }
    /** Getter for division ID. */
    public int getDivisionId() {
        return divisionId;
    }
    /** Getter for name of division. */
    public String getDivisionName() {
        return divisionName;
    }
}
