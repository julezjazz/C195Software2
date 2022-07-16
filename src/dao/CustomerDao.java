package dao;

import helper.JDBC;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Customer;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerDao {

    /** List for all objects representing customers. */
    public static ObservableList<Customer> allCustomers = FXCollections.observableArrayList();

    public static ObservableList<Customer> populateCustomerList(){
        String sql = "select * from customers left join first_level_divisions on customers.division_id = " +
                "first_level_divisions.division_id";

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
                    String division = rs.getString("Division");
                    Customer newCustomer = new Customer(customerId, customerName, address, postalCode, phone, divisionId, division);
                    allCustomers.add(newCustomer);
                }
                return allCustomers;

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

        }
        return null;
    }

     public static void insert(String customerName, String address, String postalCode, String phone,
                             int divisionId) throws SQLException {
        String sql = "insert into customers (customer_name, address, postal_code, phone, division_id) values(?, ?, ?, " +
                "?, ?)";
        PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
        ps.setString(1,customerName);
        ps.setString(2, address);
        ps.setString(3, postalCode);
        ps.setString(4, phone);
        ps.setInt(5, divisionId);

        ps.execute();
    }
 /*
    public static void update(String customerName, String address, String postalCode, String phone, int
                              divisionId, int customerId) throws SQLException {
        String sql = "update customers set customer_name = ?, address = ?, postal_code = ?, phone = ?, division_id = ? " +
                "where customer_id = ?";
        PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
        ps.setString(1,customerName);
        ps.setString(2, address);
        ps.setString(3, postalCode);
        ps.setString(4, phone);
        ps.setInt(5, divisionId);
        ps.setInt(6, customerId);
    } */
}
