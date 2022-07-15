package dao;

import helper.JDBC;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Appointment;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
                    Appointment newAppointment = new Appointment(appointmentId, title, description, location, type,
                            startDateTime, endDateTime, customerId, userId, contactName);
                    allAppointments.add(newAppointment);
                    //DELETE BELOW SOUT
                    ZoneId utcZi = ZoneId.of("UTC");
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                    ZonedDateTime appointmentStartZdt = ZonedDateTime.parse(startDateTime, formatter.withZone(utcZi));
                   // LocalDateTime appointmentLdt = LocalDateTime.parse(startDateTime, formatter);
                   // System.out.println(appointmentStartZdt);
                    System.out.println(appointmentStartZdt);
                    System.out.println(appointmentStartZdt.toLocalDate());
                    System.out.println(appointmentStartZdt.toLocalTime());
                   // ZoneId localZid = ZoneId.systemDefault();
                    // ZonedDateTime localZdt = ZonedDateTime.ofInstant(appointmentLdt.toInstant(), localZid);
                }

                return allAppointments;

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

        }
        return null;
    }
}
