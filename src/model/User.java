package model;
/** Represents a user. */
public class User {

    private int userId;
    private String userName;

/** Class constructor. */
    public User(int userId, String userName) {
        this.userId = userId;
        this.userName = userName;
    }
    /** Setter for user ID. */
    public void setUserId(int userId) {
        this.userId = userId;
    }
    /** Setter for user name. */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /** Getter for user ID. */
    public int getUserId() {
        return userId;
    }
    /** Getter for user name. */
    public String getUserName() {
        return userName;
    }

}
