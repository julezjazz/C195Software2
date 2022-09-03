package helper;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * A class for connecting to the MySQL database.
 * @author Julez Hudson
 */
public abstract class JDBC {

    private static final String protocol = "jdbc";
    private static final String vendor = ":mysql:";
    private static final String location = "//localhost/";
    private static final String databaseName = "client_schedule";
    private static final String jdbcUrl = protocol + vendor + location + databaseName + "?connectionTimeZone = SERVER"; // LOCAL
    private static final String driver = "com.mysql.cj.jdbc.Driver"; // Driver reference
    private static final String userName = "sqlUser"; // Username
    private static String password = "Passw0rd!"; // Password
    public static Connection connection;  // Connection Interface

    /**
     * Initiates a connection with the database. This method prints a message indicating whether the connection worked
     * or if there was an exception.
     */
    public static void openConnection()
    {
        try {
            Class.forName(driver); // Locate Driver
            connection = DriverManager.getConnection(jdbcUrl, userName, password); // Reference Connection object
            System.out.println("Connection successful!");
        }
        catch(Exception e)
        {
            System.out.println("Error:" + e.getMessage());
        }
    }

    /**
     * Allows the connection to the database to be accessed.
     * @return the connection to the database
     */
    public static Connection getConnection(){
        return connection;
    }

    /**
     * Closes the connection to the database. This method prints a message to indicate whether the connection closed or
     * if there was an exception.
     */
    public static void closeConnection() {
        try {
            connection.close();
            System.out.println("Connection closed!");
        }
        catch(Exception e)
        {
            System.out.println("Error:" + e.getMessage());
        }
    }
}
