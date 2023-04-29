package com.example.demo.service;

import com.example.demo.model.ClientModel;
import com.example.demo.model.ClientRecord;

public interface ClientService {
    ClientModel save(ClientRecord clinet) throws Exception;
}
