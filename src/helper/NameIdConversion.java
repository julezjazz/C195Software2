package helper;

import model.Contact;
import model.ListManager;

public class NameIdConversion {
    public static int returnContactID(String contactName) {
        for (Contact contact : ListManager.allContacts) {
            if (contact.getContactName().equals(contactName)) {
                return contact.getContactId();
            }
        }
        return 0;
    }
}
