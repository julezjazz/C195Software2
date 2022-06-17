package model;
/** Represents a contact person for appointments. */
public class Contact {
    private int contactId;
    private String contactName;
    private String email;
    /** Class constructor. */
    public Contact(int contactId, String contactName, String email) {
        this.contactId = contactId;
        this.contactName = contactName;
        this.email = email;
    }
    /** Setter for contact ID. */
    public void setContactId(int contactId) {
        this.contactId = contactId;
    }
    /** Setter for contact name. */
    public void setContactName(String contactName) {
        this.contactName = contactName;
    }
    /** Setter for contact email. */
    public void setEmail(String email) {
        this.email = email;
    }
    /** Getter for contact ID. */
    public int getContactId() {
        return contactId;
    }
    /** Getter for contact name. */
    public String getContactName() {
        return contactName;
    }
    /** Getter for contact email. */
    public String getEmail() {
        return email;
    }
}
