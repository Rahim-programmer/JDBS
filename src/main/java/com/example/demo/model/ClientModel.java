package com.example.demo.model;

import java.util.Objects;
import java.util.UUID;

public class ClientModel {
    private final UUID idClient;
    private final String firstName;
    private final String lastName;
    private final String phoneNumber;


    public ClientModel(UUID idClient, String firstName, String lastName, String phoneNumber) {
        this.idClient = idClient;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
    }

    public UUID getIdClient() {
        return idClient;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    @Override
    public String toString() {
        return "ClientModel{" +
                "idClient=" + idClient +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClientModel that = (ClientModel) o;
        return Objects.equals(idClient, that.idClient) && Objects.equals(firstName, that.firstName) && Objects.equals(lastName, that.lastName) && Objects.equals(phoneNumber, that.phoneNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idClient, firstName, lastName, phoneNumber);
    }
}
