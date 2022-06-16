package model;

public class Division {

    private int divisionId;
    private String divisionName;

    public Division(int divisionId, String divisionName){
        this.divisionId = divisionId;
        this.divisionName = divisionName;
    }

    public void setDivisionId(int divisionId) {
        this.divisionId = divisionId;
    }

    public void setDivisionName(String divisionName) {
        this.divisionName = divisionName;
    }

    public int getDivisionId() {
        return divisionId;
    }

    public String getDivisionName() {
        return divisionName;
    }
}
