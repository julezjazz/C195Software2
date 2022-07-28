package model;
/** Represents an appointment. */
public class Appointment {
    private int appointmentId;
    private String title;
    private String description;
    private String location;
    private String type;
    private String startDate;
    private String startTime;
    private String endDate;
    private String endTime;
    private int customerId;
    private int userId;
    private String contactName;
/** Class constructor. */
    public Appointment(int appointmentId, String title, String description, String location, String type,
                       String startDate, String startTime, String endDate, String endTime, int customerId,
                       int userId, String contactName) {
        this.appointmentId = appointmentId;
        this.title = title;
        this.description = description;
        this.location = location;
        this.type = type;
        this.startDate = startDate;
        this.startTime = startTime;
        this.endDate = endDate;
        this.endTime = endTime;
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
    /** Setter for appointment start date. */
    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }
    /** Setter for appointment start time. */
    public void setStartTime(String startTime) {this.startTime = startTime;}
    /** Setter for appointment end date. */
    public void setEndDate(String endDateTime) {
        this.endDate = endDate;
    }
    /** Setter for appointment end time */
    public void setEndTime(String endTime) {
        this.endTime = endTime;
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
    /** Getter for appointment start date. */
    public String getStartDate() {
        return startDate;
    }
    /** Getter for appointment start time. */
    public String getStartTime() {
        return startTime;
    }
    /** Getter for appointment end time and end date. */
    public String getEndDate() {
        return endDate;
    }
    /** Getter for appointment end time. */
    public String getEndTime() {
        return endTime;
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
