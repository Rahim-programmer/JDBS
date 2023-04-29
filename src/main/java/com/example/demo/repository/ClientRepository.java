package com.example.demo.repository;

import com.example.demo.model.ClientModel;
import com.example.demo.model.ClientRecord;
import com.example.demo.service.ClientService;
import org.springframework.stereotype.Service;

@Service
public class ClientRepository implements ClientService {

    private final JdbcAPIClientService repository;

    public ClientRepository(JdbcAPIClientService repository) {
        this.repository = repository;
    }

    @Override
    public ClientModel save(ClientRecord client) throws Exception {
        return repository.save(client);
    }
}
