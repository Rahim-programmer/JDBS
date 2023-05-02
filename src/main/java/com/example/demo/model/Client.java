package com.example.demo.model;

import java.util.Objects;
import java.util.UUID;

public class Client {
    private UUID uuid;
    private String first_name;
    private String last_name;
    private String phone_number;

    public Client(UUID uuid, String first_name, String last_name, String phone_number) {
        this.uuid = uuid;
        this.first_name = first_name;
        this.last_name = last_name;
        this.phone_number = phone_number;
    }

    public Client() {
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public String getFirstName() {
        return first_name;
    }

    public void setFirstName(String first_name) {
        this.first_name = first_name;
    }

    public String getLastName() {
        return last_name;
    }

    public void setLastName(String last_name) {
        this.last_name = last_name;
    }

    public String getPhoneNumber() {
        return phone_number;
    }

    public void setPhoneNumber(String phone_number) {
        this.phone_number = phone_number;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Client client = (Client) o;
        return Objects.equals(uuid, client.uuid) && Objects.equals(first_name, client.first_name) && Objects.equals(last_name, client.last_name) && Objects.equals(phone_number, client.phone_number);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uuid, first_name, last_name, phone_number);
    }

    @Override
    public String toString() {
        return "Client{" +
                "uuid=" + uuid +
                ", first_name='" + first_name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", phone_number='" + phone_number + '\'' +
                '}';
    }
}

