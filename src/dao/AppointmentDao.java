package dao;

import helper.JDBC;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Appointment;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 * A class for working with the Appointment table in the database.
 * @author Julez Hudson
 */
public class AppointmentDao {

    public static ObservableList<Appointment> allAppointments = FXCollections.observableArrayList();

    /**
     * Retrieves all rows from the Appointments table of the database and creates an Appointment object for each then
     * adds each to the list for all appointments.
     * @return The list of all appointments.
     */
    public static ObservableList<Appointment> populateAppointmentList(){
        String sql = "select * from appointments";

        PreparedStatement ps;

        {
            try {
                ps = JDBC.getConnection().prepareStatement(sql);

                ResultSet rs = ps.executeQuery();
                allAppointments.clear();
                while (rs.next()) {
                    int appointmentId = rs.getInt("Appointment_ID");
                    String title = rs.getString("Title");
                    String description = rs.getString("Description");
                    String location = rs.getString("Location");
                    int contactId = rs.getInt("Contact_ID");
                    String type = rs.getString("Type");
                    Timestamp startDateTime = rs.getTimestamp("Start");
                    Timestamp endDateTime = rs.getTimestamp("End");
                    int customerId = rs.getInt("Customer_ID");
                    int userId = rs.getInt("User_ID");

                    LocalDateTime startDT = startDateTime.toLocalDateTime();
                    LocalDateTime endDT = endDateTime.toLocalDateTime();


                    Appointment newAppointment = new Appointment(appointmentId, title, description, location, contactId,
                            type, startDT, endDT, customerId, userId);
                    allAppointments.add(newAppointment);
                }

                return allAppointments;

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return null;
    }

    /**
     * Inserts a new row into the Appointments table of the database.
     * @param title The appointment title.
     * @param description The appointment description.
     * @param location The location where the appointment will be held.
     * @param type The type of appointment that will be held.
     * @param start The date and time that the appointment will begin.
     * @param end The date and time that the appointment will end.
     * @param createdBy The user who add the appointment to the table.
     * @param customerId The ID number of the customer who will attend the appointment.
     * @param userId The ID number of the user who will attend the appointment.
     * @param contactId The ID number of the contact who will attend the appointment.
     * @throws SQLException In case of a sql exception.
     */
    public static void insert(String title, String description, String location,
                              String type, Timestamp start, Timestamp end, String createdBy, int customerId,
                              int userId, int contactId) throws SQLException {
        String sql = "insert into appointments (title, description, location, type, start, end, create_date, " +
                "created_by, customer_id, user_id, contact_id) values(?, ?, ?, ?, ?, ?, NOW(), ?, ?, ?, ?)";
        PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
        ps.setString(1, title);
        ps.setString(2, description);
        ps.setString(3, location);
        ps.setString(4, type);
        ps.setTimestamp(5, start);
        ps.setTimestamp(6, end);
        ps.setString(7, createdBy);
        ps.setInt(8, customerId);
        ps.setInt(9, userId);
        ps.setInt(10, contactId);

        ps.execute();
    }

    /**
     * Updates a row in the Appointments table of the database.
     * @param title The appointment title.
     * @param description The appointment description.
     * @param location The location where the appointment will be held.
     * @param type The type of appointment that will be held.
     * @param start The date and time that the appointment will begin.
     * @param end The date and time that the appointment will end.
     * @param updatedBy The user who modified the appointment in the database.
     * @param customerId The ID number of the customer who will attend the appointment.
     * @param userId The ID number of the user who will attend the appointment.
     * @param contactId The ID number of the contact who will attend the appointment.
     * @param appointmentId The ID of the appointment that is being changed in the database.
     * @throws SQLException In case of a sql exception.
     */
    public static void update(String title, String description, String location, String type, Timestamp start,
                              Timestamp end, String updatedBy, int customerId, int userId, int contactId,
                              int appointmentId)
            throws SQLException {
        String sql = "update appointments set title = ?, description = ?, location = ?, type = ?, start = ?, " +
                "end = ?, last_update = NOW(), last_updated_by = ?, customer_id = ?, user_id = ?, contact_id = ? " +
                "where appointment_id = ?";

        PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
        ps.setString(1, title);
        ps.setString(2, description);
        ps.setString(3, location);
        ps.setString(4, type);
        ps.setTimestamp(5, start);
        ps.setTimestamp(6, end);
        ps.setString(7, updatedBy);
        ps.setInt(8, customerId);
        ps.setInt(9, userId);
        ps.setInt(10, contactId);
        ps.setInt(11, appointmentId);

        ps.execute();
    }

    /**
     * Deletes a row from the Appointments table of the database
     * @param appointmentId The ID number of the appointment row that is being deleted.
     * @throws SQLException In case of a sql exception.
     */
    public static void delete(int appointmentId) throws SQLException {
        String sql = "delete from appointments where appointment_id = ?";

        PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
        ps.setInt(1, appointmentId);

        ps.execute();
    }
}
