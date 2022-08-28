package helper;

import dao.CustomerDao;
import model.Contact;
import model.Country;
import model.Customer;
import model.Division;

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
    public static int returnCountryID(String countryName) {
        for (Country country : ListManager.allCountries) {
            if (country.getCountryName().equals(countryName)) {
                return country.getCountryId();
            }
        }
        return 0;
    }
    public static String convertCountryIdToName(int countryId){
        for(Country country : ListManager.allCountries) {
            if (country.getCountryId() == countryId) {
                return country.getCountryName();
            }
        }
        return null;
    }
    public static int convertDivNameToId(String divisionName){
        for(Division division : ListManager.allDivisions) {
            if (division.getDivisionName().equals(divisionName)){
                return division.getDivisionId();
            }
        }
        return 0;
    }

    public static int convertDivIdToCountryId(int divisionId){
        for (Division division : ListManager.allDivisions) {
            if (division.getDivisionId() == divisionId) {
                return division.getCountryId();
            }
        }
        return 0;
    }
    public static String convertDivIdToName(int divisionId){
        for (Division division : ListManager.allDivisions) {
            if (division.getDivisionId() == divisionId) {
                return division.getDivisionName();
            }
        }
        return null;
    }
}
