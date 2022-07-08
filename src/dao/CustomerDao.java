package dao;

import helper.JDBC;
import model.Customer;
import model.ListManager;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class CustomerDao {
    public static void populateCustomerList() {
        String sql = "select * from customers";

        PreparedStatement ps;

        {
            try {
                ps = JDBC.getConnection().prepareStatement(sql);

                ResultSet rs = ps.executeQuery();
                ListManager.allCustomers.clear();
                while (rs.next()) {
                    int customerId = rs.getInt("Customer_ID");
                    String customerName = rs.getString("Customer_Name");
                    String address = rs.getString("Address");
                    String postalCode = rs.getString("Postal_Code");
                    String phone = rs.getString("Phone");
                    int divisionId = rs.getInt("Division_ID");
                    Customer newCustomer = new Customer(customerId, customerName, address, postalCode, phone, divisionId);
                    ListManager.allCustomers.add(newCustomer);
                }

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

        }
    }
}
