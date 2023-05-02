package com.example.springjdbc.controller.api;

import com.example.demo.model.Client;
import com.example.demo.model.ClientRecord;
import com.example.demo.repository.ClientRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/clients")
public class ClientController {

    private final ClientRepository clientRepository;

    public ClientController(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Client>> findAll(@RequestParam(required = false) String firstName) {
        if (firstName == null) {
            return new ResponseEntity<>(clientRepository.findAll(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(clientRepository.findByFirstName(firstName), HttpStatus.OK);
        }
    }

    @GetMapping("/{id}")
    public Client findClientById(@PathVariable UUID id) {
        return clientRepository.findById(id);
    }

    @PostMapping
    public ResponseEntity<ClientRecord> addClient(@RequestBody ClientRecord client) {
        try {
            clientRepository.save(client);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return new ResponseEntity<>(client, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Client> updateClient(@PathVariable UUID id, @RequestBody ClientRecord client) throws Exception {
        Client updateClient = clientRepository.updateClient(id, client);
        if (updateClient== null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updateClient);
    }

    @DeleteMapping("/{id}")
    public String deleteClientById(@PathVariable UUID id) {
        clientRepository.deleteById(id);
        return "Client " + id + " " + "deleted";
    }

    @DeleteMapping("/")
    public String deleteAll() {
        clientRepository.deleteAll();
        return "All clients deleted";
    }
}
