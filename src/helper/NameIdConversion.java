package helper;

import dao.CustomerDao;
import model.Contact;
import model.Customer;

public class NameIdConversion {
    public static int returnContactID(String contactName) {
        for (Contact contact : ListManager.allContacts) {
            if (contact.getContactName().equals(contactName)) {
                return contact.getContactId();
            }
        }
        return 0;
    }
    public static int returnCustomerID(String customerName) {
        for (Customer customer : CustomerDao.populateCustomerList()) {
            if(customer.getCustomerName().equals(customerName)) {
                return customer.getCustomerId();
            }
        }
        return 0;
    }
}
