package com.example.demo.repository;

import com.example.demo.model.Client;
import com.example.demo.model.ClientRecord;
import com.example.demo.service.ClientService;
import com.example.demo.service.JdbcClientService;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public class ClientRepository implements ClientService {

    private final JdbcClientService jdbcClientService;

    public ClientRepository(JdbcClientService jdbcClientService) {
        this.jdbcClientService = jdbcClientService;
    }

    @Override
    public List<Client> findAll() {
        return jdbcClientService.findAll();
    }

    @Override
    public Client findById(UUID id) {
        return jdbcClientService.findById(id);
    }

    @Override
    public List<Client> findByFirstName(String first_name) {
        return jdbcClientService.findByFirstName(first_name);
    }

    @Override
    public Client updateClient(UUID id, ClientRecord client) throws Exception {
        if (jdbcClientService.findById(id) != null) {
            return jdbcClientService.updateClient(id, client);
        }
        throw new Exception("Client not found");
    }

    @Override
    public Client save(ClientRecord client) throws Exception {
        return jdbcClientService.saveClient(client);
    }

    @Override
    public void deleteById(UUID id) {
        if (jdbcClientService.findById(id) != null) {
            jdbcClientService.deleteById(id);
        }
    }

    @Override
    public void deleteAll() {
        jdbcClientService.deleteAll();
    }
}
