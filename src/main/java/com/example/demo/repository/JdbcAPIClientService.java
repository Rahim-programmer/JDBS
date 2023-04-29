package com.example.demo.repository;

import com.example.demo.model.ClientModel;
import com.example.demo.model.ClientRecord;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Repository
public class JdbcAPIClientService {


    private static final Logger log = LoggerFactory.getLogger(JdbcAPIClientService.class);
    private final DataSource dbms;

    public JdbcAPIClientService(DataSource dbms) {
        this.dbms = dbms;
    }

    public ClientModel save(ClientRecord client) throws Exception {
        try {
            Connection connection = dbms.getConnection();
            String sqlQuery = """
                    INSERT INTO clients (id, first_name, last_name, phone_number)
                    VALUES(?, ?, ?, ?)
                    """;
            PreparedStatement statement = connection.prepareStatement(sqlQuery);
            UUID uuid = UUID.randomUUID();
            statement.setObject(1, uuid);
            statement.setString(2, client.firstName());
            statement.setString(3, client.lastName());
            statement.setSt Петр       |ring(4, client.phoneNumber());
            int answer = statement.executeUpdate();
            if(answer == 1) {
                System.out.println("answer " + answer);
                ClientModel _client = new ClientModel (
                        uuid,
                        client.firstName(),
                        client.lastName(),
                        client.phoneNumber()
                );
                log.info("Client is created into DBMS : " + _client);
                return _client;
            }
        } catch (SQLException e) {
            System.out.println("123 " + e);
            throw new RuntimeException(e);
        }

        log.error("Hello, World! ");
        throw new Exception("Error " + "Hello> World!");
    }
}
