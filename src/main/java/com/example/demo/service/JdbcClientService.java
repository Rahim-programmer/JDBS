package com.example.demo.service;

import com.example.demo.model.Client;
import com.example.demo.model.ClientRecord;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class JdbcClientService {
    private final DataSource dataSource;

    public JdbcClientService(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public List<Client> findAll() {
        List<Client> clients = new ArrayList<>();

        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM clients");

            while (resultSet.next()) {
                UUID id = resultSet.getObject("id", UUID.class);
                String first_name = resultSet.getString("first_name");
                String last_name = resultSet.getString("last_name");
                String phone_number = resultSet.getString("phone_number");
                clients.add(new Client(id, first_name, last_name, phone_number));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return clients;
    }

    public Client findById(UUID id) {
        Connection connection;
        PreparedStatement statement;

        try {
            connection = dataSource.getConnection();
            statement = connection.prepareStatement("SELECT * FROM clients WHERE id = ?");
            statement.setObject(1, id);

            try {
                ResultSet resultSet = statement.executeQuery();
                if (resultSet.next()) {
                    return new Client((UUID) resultSet.getObject("id"),
                            resultSet.getString("first_name"),
                            resultSet.getString("last_name"),
                            resultSet.getString("phone_number"));
                } else {
                    return null;
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Client> findByFirstName(String first_name) {
        List<Client> clients = new ArrayList<>();
        Connection connection;
        PreparedStatement statement;

        try {
            connection = dataSource.getConnection();
            statement = connection.prepareStatement("SELECT * FROM clients WHERE first_name = ?");
            statement.setString(1, first_name);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                UUID uuid = resultSet.getObject("id", UUID.class);
                String firstName = resultSet.getString("first_name");
                String last_name = resultSet.getString("last_name");
                String phone_number = resultSet.getString("phone_number");
                clients.add(new Client(uuid, firstName, last_name, phone_number));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return clients;
    }

    public Client saveClient(ClientRecord client) throws Exception {
        Connection connection;
        PreparedStatement statement;

        try {
            connection = dataSource.getConnection();
            String sqlQuery = "INSERT INTO clients (id, first_name, last_name, phone_number) VALUES (?, ?, ?, ?)";
            statement = connection.prepareStatement(sqlQuery, Statement.RETURN_GENERATED_KEYS);
            UUID uuid = UUID.randomUUID();
            statement.setObject(1, uuid);
            statement.setString(2, client.firstName());
            statement.setString(3, client.lastName());
            statement.setString(4, client.phoneNumber());
            if (statement.executeUpdate() == 1) {
                Client _client = new Client(
                        uuid,
                        client.firstName(),
                        client.lastName(),
                        client.phoneNumber()
                );
                return _client;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        throw new Exception("Error");
    }

    public Client updateClient(UUID id, ClientRecord client) {
        Connection connection;
        PreparedStatement statement;

        try {
            connection = dataSource.getConnection();
            String sqlQuery = "UPDATE clients SET first_name=?, last_name=?, phone_number=? WHERE id = ?";
            statement = connection.prepareStatement(sqlQuery);
            statement.setString(1, client.firstName());
            statement.setString(2, client.lastName());
            statement.setString(3, client.phoneNumber());
            statement.setObject(4, id);
            if (statement.executeUpdate() == 1) {
                return new Client(id,
                        client.firstName(),
                        client.lastName(),
                        client.phoneNumber());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void deleteById(UUID id) {
        Connection connection;
        PreparedStatement statement;
        try {
            connection = dataSource.getConnection();
            statement = connection.prepareStatement("DELETE FROM clients WHERE id = ?");
            statement.setObject(1, id);
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteAll() {
        Connection connection;
        PreparedStatement statement;
        try {
            connection = dataSource.getConnection();
            statement = connection.prepareStatement("DELETE FROM clients");
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
