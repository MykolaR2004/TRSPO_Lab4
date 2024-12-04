package org.example.trspo_lab4.model;

import javafx.beans.property.*;

public class Meeting {
    private final IntegerProperty id;
    private final IntegerProperty client1Id;
    private final IntegerProperty client2Id;
    private final StringProperty date;
    private final StringProperty country;

    public Meeting(int id, int client1Id, int client2Id, String date, String country) {
        this.id = new SimpleIntegerProperty(id);
        this.client1Id = new SimpleIntegerProperty(client1Id);
        this.client2Id = new SimpleIntegerProperty(client2Id);
        this.date = new SimpleStringProperty(date);
        this.country = new SimpleStringProperty(country);
    }

    public int getId() {
        return id.get();
    }

    public IntegerProperty idProperty() {
        return id;
    }

    public int getClient1Id() {
        return client1Id.get();
    }

    public IntegerProperty client1IdProperty() {
        return client1Id;
    }

    public void setClient1Id(int client1Id) {
        this.client1Id.set(client1Id);
    }

    public int getClient2Id() {
        return client2Id.get();
    }

    public IntegerProperty client2IdProperty() {
        return client2Id;
    }

    public void setClient2Id(int client2Id) {
        this.client2Id.set(client2Id);
    }

    public String getDate() {
        return date.get();
    }

    public StringProperty dateProperty() {
        return date;
    }

    public void setDate(String date) {
        this.date.set(date);
    }

    public String getCountry() {
        return country.get();
    }

    public StringProperty countryProperty() {
        return country;
    }

    public void setCountry(String country) {
        this.country.set(country);
    }
}
