package dao;

import helper.JDBC;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Customer;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * A class for working with the Customers table in the database.
 * @author Julez Hudson
 */
public class CustomerDao {

    /** List for all objects representing customers. */
    public static ObservableList<Customer> allCustomers = FXCollections.observableArrayList();

    /**
     * Retrieves all rows from the Customers table of hte database and creates a Customer object for each then adds this
     * object to the list for all Customers.
     * @return The list of all customers.
     */
    public static ObservableList<Customer> populateCustomerList(){
        String sql = "select * from customers";

        PreparedStatement ps;

        {
            try {
                ps = JDBC.getConnection().prepareStatement(sql);

                ResultSet rs = ps.executeQuery();
                allCustomers.clear();
                while (rs.next()) {
                    int customerId = rs.getInt("Customer_ID");
                    String customerName = rs.getString("Customer_Name");
                    String address = rs.getString("Address");
                    String postalCode = rs.getString("Postal_Code");
                    String phone = rs.getString("Phone");
                    int divisionId = rs.getInt("Division_ID");
                    Customer newCustomer = new Customer(customerId, customerName, address, postalCode, phone,
                            divisionId);
                    allCustomers.add(newCustomer);
                }
                return allCustomers;
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return null;
    }

    /**
     * Inserts a new row into the Customers table of the database.
     * @param customerName The customer's first and last name.
     * @param address The address where the customer resides.
     * @param postalCode The postal code for where the customer resides.
     * @param phone The customer's phone number.
     * @param createdBy The user who added the row to the table.
     * @param divisionId The ID number for the first-level division where the customer resides.
     * @throws SQLException In case of a sql exception.
     */
     public static void insert(String customerName, String address, String postalCode, String phone,
                               String createdBy, int divisionId) throws SQLException {

        String sql = "insert into customers (customer_name, address, postal_code, phone, create_date, created_by, " +
                "division_id) values(?, ?, ?, ?, NOW(), ?, ?)";
        PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
        ps.setString(1,customerName);
        ps.setString(2, address);
        ps.setString(3, postalCode);
        ps.setString(4, phone);
        ps.setString(5, createdBy);
        ps.setInt(6, divisionId);

        ps.execute();
    }

    /**
     * Updates a row in the Customers table of the database.
     * @param customerName The customer's first and last name.
     * @param address The address where the customer resides.
     * @param postalCode The postal code for where the customer resides.
     * @param phone The customer's phone number.
     * @param updatedBy The user who modified the row in the table.
     * @param divisionId The ID number for the first-level division where the customer resides.
     * @param customerId The ID number of the customer whose row is being modified.
     * @throws SQLException In case of a sql exception.
     */
    public static void update(String customerName, String address, String postalCode, String phone, String updatedBy,
                              int divisionId, int customerId) throws SQLException {
        String sql = "update customers set customer_name = ?, address = ?, postal_code = ?, phone = ?, " +
                "last_update = NOW(), last_updated_by = ?, division_id = ? where customer_id = ?";
        PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
        ps.setString(1,customerName);
        ps.setString(2, address);
        ps.setString(3, postalCode);
        ps.setString(4, phone);
        ps.setString(5, updatedBy);
        ps.setInt(6, divisionId);
        ps.setInt(7, customerId);

        ps.execute();
    }

    /**
     * Deletes a row from the Customers table of the database.
     * @param customerId The ID number of the customer row that is being deleted.
     * @throws SQLException In case of a sql exception.
     */
    public static void delete(int customerId) throws SQLException {
        String sql = "delete from appointments where customer_id = ?";
        PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
        ps.setInt(1, customerId);
        ps.execute();

        sql = "delete from customers where customer_id = ?";
        ps = JDBC.getConnection().prepareStatement(sql);
        ps.setInt(1, customerId);
        ps.execute();
    }
}
