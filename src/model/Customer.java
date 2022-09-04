package model;

/**
 * A class to represent a customer.
 * @author Julez Hudson
 */
public class Customer {

    private int customerId;
    private String customerName;
    private String address;
    private String postalCode;
    private String phone;
    private int divisionId;

    /** Class constructor. */
    public Customer(int customerId, String customerName, String address, String postalCode, String phone,
                         int divisionId){
        this.customerId = customerId;
        this.customerName = customerName;
        this.address = address;
        this.postalCode = postalCode;
        this.phone = phone;
        this.divisionId = divisionId;
    }

    /** Setter for customer ID. */
    public void setCustomerId(int customerId) { this.customerId = customerId; }

    /** Setter for customer name */
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    /** Setter for a customer's street address. */
    public void setAddress(String address) {
        this.address = address;
    }

    /** Setter for a customer's postal code. */
    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    /** Setter for a customer's phone number. */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /** Setter for the ID of the first-level division where the customer resides. */
    public void setDivisionId(int divisionId) {
        this.divisionId = divisionId;
    }

    /** Getter for the customer's ID number. */
    public int getCustomerId() { return customerId; }

    /** Getter for the customer's name. */
    public String getCustomerName() {
        return customerName;
    }

    /** Getter for the customer's street address. */
    public String getAddress() {
        return address;
    }

    /** Getter for the customer's postal code. */
    public String getPostalCode() {
        return postalCode;
    }

    /** Getter for the customer's phone number. */
    public String getPhone() {
        return phone;
    }

    /** Getter for the ID of the first-level division where the customer resides. */
    public int getDivisionId() {
        return divisionId;
    }

    public String getDivisionName() {
        return helper.NameIdConversion.convertDivIdToName(getDivisionId());
    }
}