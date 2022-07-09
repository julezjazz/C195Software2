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
    //public static void populateCustomerList(){
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
                    Customer newCustomer = new Customer(customerId, customerName, address, postalCode, phone, divisionId);
                    allCustomers.add(newCustomer);
                } //DELETE TEST LOOP BELOW
               // for(Customer customer : allCustomers) {
                 //   System.out.println(customer.getCustomerName());
                //}
                return allCustomers;

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

        }
        return null;
    }
}
