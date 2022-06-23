package model;
/** Represents a contact person for appointments. */
public class Contact {
    private int contactId;
    private String contactName;
    /** Class constructor. */
    public Contact(int contactId, String contactName) {
        this.contactId = contactId;
        this.contactName = contactName;
        }
    /** Setter for contact ID. */
    public void setContactId(int contactId) {
        this.contactId = contactId;
    }
    /** Setter for contact name. */
    public void setContactName(String contactName) {
        this.contactName = contactName;
    }
    /** Getter for contact ID. */
    public int getContactId() {
        return contactId;
    }
    /** Getter for contact name. */
    public String getContactName() {
        return contactName;
    }
}
