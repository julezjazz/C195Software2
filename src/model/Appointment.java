package model;

import java.time.LocalDateTime;

/** Represents an appointment. */
public class Appointment {
    private int appointmentId;
    private String title;
    private String description;
    private String location;
    private String type;
    private LocalDateTime startDT;
    private LocalDateTime endDT;
    private int customerId;
    private int userId;
    private String contactName;
/** Class constructor. */
    public Appointment(int appointmentId, String title, String description, String location, String type,
                       LocalDateTime startDT, LocalDateTime endDT, int customerId,
                       int userId, String contactName) {
        this.appointmentId = appointmentId;
        this.title = title;
        this.description = description;
        this.location = location;
        this.type = type;
        this.startDT = startDT;
        this.endDT = endDT;
        this.customerId = customerId;
        this.userId = userId;
        this.contactName = contactName;
    }
/** Setter for appointment ID. */
    public void setAppointmentId(int appointmentId) {
        this.appointmentId = appointmentId;
    }
/** Setter for appointment title. */
    public void setTitle(String title) {
        this.title = title;
    }
    /** Setter for appointment description. */
    public void setDescription(String description) {
        this.description = description;
    }
    /** Setter for appointment location. */
    public void setLocation(String location) {
        this.location = location;
    }
    /** Setter for appointment type. */
    public void setType(String type) {
        this.type = type;
    }
    /** Setter for appointment start date and time. */
    public void setStartDT(LocalDateTime startDT) {
        this.startDT = startDT;
    }

    /** Setter for appointment end date and time. */
    public void setEndDT(LocalDateTime endDateTime) {
        this.endDT = endDT;
    }

    /** Setter for customer ID. */
    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }
    /** Setter for user ID. */
    public void setUserId(int userId) {
        this.userId = userId;
    }
    /** Setter for appointment contact ID. */
    public void setContactName(String contactName) {
        this.contactName = contactName;
    }
    /** Getter for appointment ID. */
    public int getAppointmentId() {
        return appointmentId;
    }
    /** Getter for appointment title. */
    public String getTitle() {
        return title;
    }
    /** Getter for appointment description. */
    public String getDescription() {
        return description;
    }
    /** Getter for appointment location. */
    public String getLocation() {
        return location;
    }
    /** Getter for appointment type. */
    public String getType() {
        return type;
    }
    /** Getter for appointment start date and time. */
    public LocalDateTime getStartDT() {
        return startDT;
    }
    /** Getter for appointment end date and time. */
    public LocalDateTime getEndDT() {
        return endDT;
    }
    /** Getter for customer ID. */
    public int getCustomerId() {
        return customerId;
    }
    /** Getter for user ID. */
    public int getUserId() {
        return userId;
    }
    /** Getter for appointment contact name. */
    public String getContactName() {
        return contactName;
    }
}
