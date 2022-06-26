package model;
/** Represents a user. */
public class User {

    private int userId;
    private String userName;
    private String password;

/** Class constructor. */
    public User(int userId, String userName, String password) {
        this.userId = userId;
        this.userName = userName;
        this.password = password;
    }
    /** Setter for user ID. */
    public void setUserId(int userId) {
        this.userId = userId;
    }
    /** Setter for user name. */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    /** Getter for user ID. */
    public int getUserId() {
        return userId;
    }
    /** Getter for user name. */
    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }
}
