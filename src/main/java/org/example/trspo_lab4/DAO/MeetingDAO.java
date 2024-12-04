package org.example.trspo_lab4.DAO;

import org.example.trspo_lab4.model.Meeting;
import org.example.trspo_lab4.model.MeetingView;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MeetingDAO {
    private final Connection connection;

    public MeetingDAO(Connection connection) {
        this.connection = connection;
    }

    public void addMeeting(Meeting meeting) throws SQLException {
        String query = "INSERT INTO dating_table (User_1_ID, User_2_ID, Date, Registration_country) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, meeting.getClient1Id());
            stmt.setInt(2, meeting.getClient2Id());
            stmt.setString(3, meeting.getDate());
            stmt.setString(4, meeting.getCountry());
            stmt.executeUpdate();
        }
    }

    public void deleteMeetingById(int meetingId) throws SQLException {
        String query = "DELETE FROM dating_table WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, meetingId);
            stmt.executeUpdate();
        }
    }

    public List<MeetingView> getMeetingsWithClientNames(int clientId) throws SQLException {
        String query = """
                SELECT m.id, c1.name AS client1, m.User_1_ID, c2.name AS client2, m.User_2_ID, m.Date, m.Registration_country
                FROM dating_table m
                JOIN client c1 ON m.User_1_ID = c1.id
                JOIN client c2 ON m.User_2_ID = c2.id
                WHERE m.User_1_ID = ? OR m.User_2_ID = ?;
                """;

        List<MeetingView> meetings = new ArrayList<>();
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, clientId);
            stmt.setInt(2, clientId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                meetings.add(new MeetingView(
                        rs.getInt("id"),
                        rs.getString("client1"),
                        rs.getInt("User_1_ID"),
                        rs.getString("client2"),
                        rs.getInt("User_2_ID"),
                        rs.getString("Date"),
                        rs.getString("Registration_country")
                ));
            }
        }
        return meetings;
    }

    public void updateMeetingDate(int meetingId, String newDate) throws SQLException {
        String query = "UPDATE dating_table SET date = ? WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, newDate);
            statement.setInt(2, meetingId);
            statement.executeUpdate();
        }
    }

}
