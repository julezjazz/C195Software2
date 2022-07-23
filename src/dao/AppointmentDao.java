package dao;

import helper.JDBC;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Appointment;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class AppointmentDao {

    public static ObservableList<Appointment> allAppointments = FXCollections.observableArrayList();

    public static ObservableList<Appointment> populateAppointmentList(){
        String sql = "select * from appointments left join contacts " +
                "on appointments.contact_id = contacts.contact_id";

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
                    String type = rs.getString("Type");
                    //NEED TO CHANGE TYPE FOR NEXT TWO
                    String startDateTime = rs.getString("Start");
                    String endDateTime = rs.getString("End");
                    int customerId = rs.getInt("Customer_ID");
                    int userId = rs.getInt("User_ID");
                    String contactName = rs.getString("Contact_Name");

                    ZoneId utcZi = ZoneId.of("UTC");
                    ZoneId userZi = ZoneId.systemDefault();
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

                    ZonedDateTime appointmentStartZdt = ZonedDateTime.parse(startDateTime, formatter.withZone(utcZi));

                    LocalDateTime appointmentStartLdt = appointmentStartZdt.toLocalDateTime();
                    ZonedDateTime utcStartZdt = ZonedDateTime.of(appointmentStartLdt, utcZi);
                    appointmentStartZdt = ZonedDateTime.ofInstant(utcStartZdt.toInstant(), userZi);

                    String startDate = appointmentStartZdt.toLocalDate().toString();
                    String startTime = appointmentStartZdt.toLocalTime().toString();


                    ZonedDateTime appointmentEndZdt = ZonedDateTime.parse(endDateTime, formatter.withZone(utcZi));


                    LocalDateTime appointmentEndLdt = appointmentEndZdt.toLocalDateTime();
                    ZonedDateTime utcEndZdt = ZonedDateTime.of(appointmentEndLdt, utcZi);
                    appointmentEndZdt = ZonedDateTime.ofInstant(utcEndZdt.toInstant(), userZi);

                    String endDate = appointmentEndZdt.toLocalDate().toString();
                    String endTime = appointmentEndZdt.toLocalTime().toString();



                    Appointment newAppointment = new Appointment(appointmentId, title, description, location, type,
                            startDate, startTime, endDate, endTime, customerId, userId, contactName);
                    allAppointments.add(newAppointment);
                }

                return allAppointments;

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return null;
    }

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

    public static void update(String title, String description, String location, String type, Timestamp start,
                              Timestamp end, String updatedBy, int customerId, int userId, int contactId,
                              int appointmentId)
            throws SQLException {
        String sql = "update appointments set title = ?, description = ?, location = ?, type = ?, start = ?, " +
                "end = ?, last_update = NOW(), last_updated_by = ?, customer_id = ?, user_id = ?, contact_id = ? where " +
                "appointment_id = ?";

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

}
