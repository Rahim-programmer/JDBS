package com.example.demo.service;

import com.example.demo.model.Client;
import com.example.demo.model.ClientRecord;

import java.util.List;
import java.util.UUID;

public interface ClientService {

    List<Client> findAll();

    Client findById(UUID id);

    List<Client> findByFirstName(String first_name);

    Client updateClient(UUID id, ClientRecord client) throws  Exception;

    Client save(ClientRecord client) throws Exception;

    void deleteById(UUID id);

    void deleteAll();
}
