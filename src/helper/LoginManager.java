package helper;

import model.User;

/**
 * A class for verification of Login credentials.
 * @author Julez Hudson
 */
public class LoginManager {

    /**
     *Compares username and password parameters to those in the list of all users to verify whether the combination is
     * valid.
     * @param username The string that was entered into the username text field of the login page.
     * @param password The string that was entered in the password text field of the login page.
     * @return A boolean value to indicate whether the parameters were verified as correct or incorrect.
     */
    public static boolean verifyPassword(String username, String password) {
        for (User user : ListManager.allUsers) {
            if (user.getUserName().equals(username)){
                if (user.getPassword().equals(password)) {
                    return true;
                }
            }
        }
        return false;
    }
}
