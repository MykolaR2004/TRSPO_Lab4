package org.example.trspo_lab4.DAO;

import org.example.trspo_lab4.model.Client;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClientDAO {
    private final Connection connection;

    public ClientDAO(Connection connection) {
        this.connection = connection;
    }

    public List<Client> getAllClients() throws SQLException {
        String query = "SELECT C.id, C.name, C.surname, C.aboutYourself, " +
                "TIMESTAMPDIFF(YEAR, C.birthdate, CURDATE()) AS age, " +
                "C.sex, C.birthdate, H.Hobby, R.Requirement " +
                "FROM client C " +
                "JOIN hobbylist HL ON HL.User_ID = C.id " +
                "JOIN hobbies H ON HL.Hobby_ID = H.ID " +
                "JOIN requirements_list RL ON RL.User_ID = C.id " +
                "JOIN requirements R ON R.ID = RL.requirement_id " +
                "ORDER BY C.ID";
        List<Client> clients = new ArrayList<>();
        try (PreparedStatement stmt = connection.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                System.out.println(rs.getString("name"));
                // Вывод клиентов убрать потом
                Client client = new Client();
                client.setId(rs.getInt("id"));
                client.setName(rs.getString("name"));
                client.setSurname(rs.getString("surname"));
                client.setAboutYourself(rs.getString("aboutYourself"));
                client.setAge(rs.getInt("age"));
                client.setSex(rs.getString("sex"));
                client.setBirthdate(rs.getString("birthdate"));
                client.setHobby(rs.getString("Hobby"));
                client.setRequirement(rs.getString("Requirement"));
                clients.add(client);
            }
        }
        return clients;
    }

}

