package helper;

import dao.CustomerDao;
import model.Contact;
import model.Country;
import model.Customer;
import model.Division;

/**
 * A class for storing methods that take name or ID parameters and return the corresponding ID or name.
 * @author Julez Hudson
 */
public class NameIdConversion {

    /**
     * Takes a contact name and returns the corresponding contact ID.
     * @param contactName The name of the contact for which the ID is needed.
     * @return The ID number of the contact for which the name has been provided.
     */
    public static int returnContactID(String contactName) {
        for (Contact contact : ListManager.allContacts) {
            if (contact.getContactName().equals(contactName)) {
                return contact.getContactId();
            }
        }
        return 0;
    }

    /**
     * Takes a customer name and returns the corresponding customer ID.
     * @param customerName The name of the customer for which the ID is needed.
     * @return The ID number of the customer for which the name has been provided.
     */
    public static int returnCustomerID(String customerName) {
        for (Customer customer : CustomerDao.populateCustomerList()) {
            if(customer.getCustomerName().equals(customerName)) {
                return customer.getCustomerId();
            }
        }
        return 0;
    }

    /**
     * Takes a country name and returns the corresponding country ID.
     * @param countryName The name of the country for which the ID is needed.
     * @return The ID number of the country for which the name has been provided.
     */
    public static int returnCountryID(String countryName) {
        for (Country country : ListManager.allCountries) {
            if (country.getCountryName().equals(countryName)) {
                return country.getCountryId();
            }
        }
        return 0;
    }

    /**
     * Takes a country ID and returns the corresponding country name.
     * @param countryId The ID of the country for which the name is needed.
     * @return The name of the country for which the ID number has been provided.
     */
    public static String convertCountryIdToName(int countryId){
        for(Country country : ListManager.allCountries) {
            if (country.getCountryId() == countryId) {
                return country.getCountryName();
            }
        }
        return null;
    }

    /**
     * Takes a division name and returns the corresponding division ID.
     * @param divisionName The name of the division for which the ID is needed.
     * @return The ID number of the division for which the name has been provided.
     */
    public static int convertDivNameToId(String divisionName){
        for(Division division : ListManager.allDivisions) {
            if (division.getDivisionName().equals(divisionName)){
                return division.getDivisionId();
            }
        }
        return 0;
    }

    /**
     * Takes a division ID and returns the corresponding country ID.
     * @param divisionId The ID of the division for which the country ID is needed.
     * @return The country ID for which a corresponding division ID has been provided.
     */
    public static int convertDivIdToCountryId(int divisionId){
        for (Division division : ListManager.allDivisions) {
            if (division.getDivisionId() == divisionId) {
                return division.getCountryId();
            }
        }
        return 0;
    }

    /**
     * Takes a division ID and returns the corresponding division name.
     * @param divisionId The ID of the division for which the name is needed.
     * @return The Name of hte division for which the ID number has been provided.
     */
    public static String convertDivIdToName(int divisionId){
        for (Division division : ListManager.allDivisions) {
            if (division.getDivisionId() == divisionId) {
                return division.getDivisionName();
            }
        }
        return null;
    }
}
